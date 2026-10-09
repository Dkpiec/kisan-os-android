package com.kisan.os.ui.screens

import android.annotation.SuppressLint
import android.content.Context
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kisan.os.models.SavedPlotItem
import com.kisan.os.ui.theme.KisanAmber
import com.kisan.os.ui.theme.KisanEmerald
import com.kisan.os.ui.theme.KisanLeafGreen
import kotlin.math.*

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("SetJavaScriptEnabled")
@Composable
fun GisPolylineScreen(
    currentLang: String,
    onBack: () -> Unit
) {
    val context = LocalContext.current
    val isHi = currentLang == "hi"
    val gson = remember { Gson() }
    val prefs = remember { context.getSharedPreferences("kisan_gis_prefs", Context.MODE_PRIVATE) }

    // Load saved plots from SharedPreferences
    var savedPlots by remember {
        val savedJson = prefs.getString("saved_plots_list", null)
        val initialList = if (!savedJson.isNullOrEmpty()) {
            try {
                val type = object : TypeToken<List<SavedPlotItem>>() {}.type
                gson.fromJson<List<SavedPlotItem>>(savedJson, type) ?: emptyList()
            } catch (e: Exception) {
                emptyList()
            }
        } else {
            listOf(
                SavedPlotItem("plot_1", "खेत #1 (नहर वाला रकबा)", 2.45, 3.92, "Uttar Pradesh", "Meerut", 28.9845, 77.7064),
                SavedPlotItem("plot_2", "खेत #2 (ट्यूबवेल प्लॉट)", 1.20, 1.92, "Uttar Pradesh", "Meerut", 28.9820, 77.7010)
            )
        }
        mutableStateOf(initialList)
    }

    fun persistPlots(plots: List<SavedPlotItem>) {
        savedPlots = plots
        prefs.edit().putString("saved_plots_list", gson.toJson(plots)).apply()
    }

    var selectedPlot by remember { mutableStateOf<SavedPlotItem?>(savedPlots.firstOrNull()) }
    var plotCoordinates by remember {
        mutableStateOf(
            listOf(
                listOf(28.9845, 77.7064),
                listOf(28.9858, 77.7082),
                listOf(28.9839, 77.7095),
                listOf(28.9828, 77.7071)
            )
        )
    }

    var webViewRef by remember { mutableStateOf<WebView?>(null) }
    var isSatelliteView by remember { mutableStateOf(true) }
    var showSaveDialog by remember { mutableStateOf(false) }
    var showPlotsListSheet by remember { mutableStateOf(false) }
    var newPlotNameInput by remember { mutableStateOf("") }
    var selectedState by remember { mutableStateOf("Uttar Pradesh") }
    var snackbarMessage by remember { mutableStateOf<String?>(null) }

    // Real Geodesic Area Calculation (WGS84 Spherical Polygon in Sq Meters)
    val calculatedAreaSqMeters = remember(plotCoordinates) {
        if (plotCoordinates.size < 3) 0.0
        else {
            val radius = 6378137.0 // Earth radius in meters
            var total = 0.0
            val n = plotCoordinates.size
            for (i in 0 until n) {
                val p1 = plotCoordinates[i]
                val p2 = plotCoordinates[(i + 1) % n]
                val lat1 = Math.toRadians(p1[0])
                val lat2 = Math.toRadians(p2[0])
                val lng1 = Math.toRadians(p1[1])
                val lng2 = Math.toRadians(p2[1])
                total += (lng2 - lng1) * (2.0 + sin(lat1) + sin(lat2))
            }
            abs(total * radius * radius / 2.0)
        }
    }

    val acres = calculatedAreaSqMeters / 4046.8564224
    val bigha = when (selectedState) {
        "Punjab", "Haryana" -> calculatedAreaSqMeters / 1011.7
        "Rajasthan" -> calculatedAreaSqMeters / 2529.3
        "Gujarat" -> calculatedAreaSqMeters / 2378.0
        else -> calculatedAreaSqMeters / 2529.285 // UP Pucca Bigha
    }
    val guntha = calculatedAreaSqMeters / 101.17
    val kanal = acres * 8.0
    val marla = kanal * 20.0
    val perimeterMeters = remember(plotCoordinates) {
        if (plotCoordinates.size < 2) 0.0
        else {
            var p = 0.0
            val n = plotCoordinates.size
            for (i in 0 until n) {
                val p1 = plotCoordinates[i]
                val p2 = plotCoordinates[(i + 1) % n]
                val dLat = Math.toRadians(p2[0] - p1[0])
                val dLng = Math.toRadians(p2[1] - p1[1])
                val a = sin(dLat / 2).pow(2) + cos(Math.toRadians(p1[0])) * cos(Math.toRadians(p2[0])) * sin(dLng / 2).pow(2)
                val c = 2 * atan2(sqrt(a), sqrt(1 - a))
                p += 6378137.0 * c
            }
            p
        }
    }

    // HTML Map Template with Leaflet.js and Satellite Imagery
    val mapHtml = remember(isSatelliteView) {
        """
        <!DOCTYPE html>
        <html>
        <head>
            <meta charset="utf-8" />
            <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
            <link rel="stylesheet" href="https://unpkg.com/leaflet@1.9.4/dist/leaflet.css" />
            <script src="https://unpkg.com/leaflet@1.9.4/dist/leaflet.js"></script>
            <style>
                body, html, #map { height: 100%; width: 100%; margin: 0; padding: 0; }
                .leaflet-control-attribution { display: none !important; }
            </style>
        </head>
        <body>
            <div id="map"></div>
            <script>
                var map = L.map('map', { zoomControl: false }).setView([28.9845, 77.7064], 16);
                
                var tileLayer = ${
                    if (isSatelliteView) {
                        "L.tileLayer('https://server.arcgisonline.com/ArcGIS/rest/services/World_Imagery/MapServer/tile/{z}/{y}/{x}', { maxZoom: 19 }).addTo(map);"
                    } else {
                        "L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', { maxZoom: 19 }).addTo(map);"
                    }
                }

                var markers = [];
                var polygon = null;
                var polyline = null;

                function updatePolygon() {
                    var latlngs = markers.map(function(m) { return m.getLatLng(); });
                    if (polygon) { map.removeLayer(polygon); }
                    if (polyline) { map.removeLayer(polyline); }

                    if (latlngs.length >= 3) {
                        polygon = L.polygon(latlngs, { color: '#10B981', fillColor: '#10B981', fillOpacity: 0.35, weight: 3 }).addTo(map);
                    } else if (latlngs.length >= 2) {
                        polyline = L.polyline(latlngs, { color: '#F59E0B', weight: 3, dashArray: '5, 5' }).addTo(map);
                    }

                    var coords = latlngs.map(function(ll) { return [ll.lat, ll.lng]; });
                    if (window.AndroidBridge) {
                        window.AndroidBridge.onCoordinatesUpdated(JSON.stringify(coords));
                    }
                }

                map.on('click', function(e) {
                    var marker = L.circleMarker(e.latlng, { radius: 7, color: '#FFFFFF', fillColor: '#10B981', fillOpacity: 1, weight: 2 }).addTo(map);
                    markers.push(marker);
                    updatePolygon();
                });

                function loadPlot(coordsJson) {
                    markers.forEach(function(m) { map.removeLayer(m); });
                    markers = [];
                    var coords = JSON.parse(coordsJson);
                    coords.forEach(function(c) {
                        var marker = L.circleMarker([c[0], c[1]], { radius: 7, color: '#FFFFFF', fillColor: '#10B981', fillOpacity: 1, weight: 2 }).addTo(map);
                        markers.push(marker);
                    });
                    updatePolygon();
                    if (coords.length > 0) {
                        map.fitBounds(L.polygon(coords).getBounds(), { padding: [30, 30] });
                    }
                }

                function undoLastPoint() {
                    if (markers.length > 0) {
                        var last = markers.pop();
                        map.removeLayer(last);
                        updatePolygon();
                    }
                }

                function clearAllPoints() {
                    markers.forEach(function(m) { map.removeLayer(m); });
                    markers = [];
                    updatePolygon();
                }

                // Initial Load
                loadPlot('${gson.toJson(plotCoordinates)}');
            </script>
        </body>
        </html>
        """.trimIndent()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = if (isHi) "खेत नक्शा व रकबा नाप (GIS Land Area)" else "GIS Satellite Land Area & Map",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                        Text(
                            text = if (isHi) "सैटेलाइट मैप पर मेड़ के कोने टैप करें" else "Tap boundary corners on Satellite Map",
                            fontSize = 11.sp,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    // Multi-Land List Button
                    IconButton(onClick = { showPlotsListSheet = true }) {
                        Icon(Icons.Default.List, contentDescription = "Saved Lands", tint = KisanEmerald)
                    }
                    // Satellite vs Normal Map Toggle
                    IconButton(onClick = { isSatelliteView = !isSatelliteView }) {
                        Icon(
                            imageVector = if (isSatelliteView) Icons.Default.Info else Icons.Default.LocationOn,
                            contentDescription = "Layer Toggle",
                            tint = KisanAmber
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        },
        snackbarHost = {
            if (snackbarMessage != null) {
                Snackbar(
                    modifier = Modifier.padding(16.dp),
                    action = {
                        TextButton(onClick = { snackbarMessage = null }) {
                            Text(if (isHi) "ठीक है" else "OK", color = Color.White)
                        }
                    }
                ) {
                    Text(snackbarMessage ?: "")
                }
            }
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            // Active Plot Indicator Strip
            Surface(
                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Place, contentDescription = null, tint = KisanEmerald, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = selectedPlot?.plotName ?: (if (isHi) "नया खेत (Unsaved Plot)" else "New Plot"),
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                    TextButton(
                        onClick = { showPlotsListSheet = true },
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Text(
                            text = if (isHi) "सभी खेत (${savedPlots.size}) ▾" else "My Lands (${savedPlots.size}) ▾",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = KisanEmerald
                        )
                    }
                }
            }

            // Real Interactive Leaflet Satellite Map
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                AndroidView(
                    factory = { ctx ->
                        WebView(ctx).apply {
                            settings.javaScriptEnabled = true
                            settings.domStorageEnabled = true
                            webViewClient = WebViewClient()
                            addJavascriptInterface(object {
                                @JavascriptInterface
                                fun onCoordinatesUpdated(json: String) {
                                    try {
                                        val type = object : TypeToken<List<List<Double>>>() {}.type
                                        val list = gson.fromJson<List<List<Double>>>(json, type)
                                        if (list != null) {
                                            plotCoordinates = list
                                        }
                                    } catch (e: Exception) {}
                                }
                            }, "AndroidBridge")
                            loadDataWithBaseURL("https://unpkg.com", mapHtml, "text/html", "UTF-8", null)
                            webViewRef = this
                        }
                    },
                    update = { view ->
                        webViewRef = view
                    },
                    modifier = Modifier.fillMaxSize()
                )

                // Map Overlay Floating Action Buttons
                Column(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(12.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Undo Last Point
                    Surface(
                        shape = CircleShape,
                        color = Color.Black.copy(alpha = 0.7f),
                        modifier = Modifier
                            .size(40.dp)
                            .clickable {
                                webViewRef?.evaluateJavascript("undoLastPoint()", null)
                            }
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(Icons.Default.Refresh, contentDescription = "Undo", tint = Color.White, modifier = Modifier.size(20.dp))
                        }
                    }

                    // Clear All Points
                    Surface(
                        shape = CircleShape,
                        color = Color.Black.copy(alpha = 0.7f),
                        modifier = Modifier
                            .size(40.dp)
                            .clickable {
                                webViewRef?.evaluateJavascript("clearAllPoints()", null)
                            }
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(Icons.Default.Clear, contentDescription = "Clear", tint = Color(0xFFEF4444), modifier = Modifier.size(20.dp))
                        }
                    }
                }
            }

            // Bottom Area & Indian Measurement Results Card
            Card(
                shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp)
                ) {
                    // Main Area & Bigha Big Number
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = if (isHi) "कुल मापा गया रकबा (Total Area)" else "Calculated Land Area",
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Row(verticalAlignment = Alignment.Bottom) {
                                Text(
                                    text = String.format("%.2f", acres),
                                    fontSize = 28.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = KisanEmerald
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = if (isHi) "एकड़ (Acres)" else "Acres",
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }

                        // Regional Bigha Pill
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = KisanEmerald.copy(alpha = 0.15f)
                        ) {
                            Column(
                                modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp),
                                horizontalAlignment = Alignment.End
                            ) {
                                Text(
                                    text = String.format("%.2f", bigha) + (if (isHi) " बीघा" else " Bigha"),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 17.sp,
                                    color = KisanEmerald
                                )
                                Text(
                                    text = "$selectedState",
                                    fontSize = 10.sp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // Secondary Indian Land Metrics Row
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        GisUnitMetricPill(
                            title = if (isHi) "गुंठा (Guntha)" else "Guntha",
                            value = String.format("%.1f", guntha),
                            modifier = Modifier.weight(1f)
                        )
                        GisUnitMetricPill(
                            title = if (isHi) "कनाल / मरला" else "Kanal / Marla",
                            value = "${String.format("%.1f", kanal)} / ${String.format("%.0f", marla)}",
                            modifier = Modifier.weight(1f)
                        )
                        GisUnitMetricPill(
                            title = if (isHi) "वर्ग मीटर (m²)" else "Sq. Meters",
                            value = String.format("%.0f", calculatedAreaSqMeters),
                            modifier = Modifier.weight(1f)
                        )
                        GisUnitMetricPill(
                            title = if (isHi) "मेड़ घेरा (Perimeter)" else "Perimeter",
                            value = "${perimeterMeters.roundToInt()}m",
                            modifier = Modifier.weight(1f)
                        )
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // Action Buttons: Save Plot & Switch Land
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        OutlinedButton(
                            onClick = { showPlotsListSheet = true },
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier
                                .weight(1f)
                                .height(46.dp)
                        ) {
                            Icon(Icons.Default.List, contentDescription = null, modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(if (isHi) "खेत सूची" else "My Lands", fontSize = 13.sp)
                        }

                        Button(
                            onClick = {
                                if (plotCoordinates.size < 3) {
                                    snackbarMessage = if (isHi) "कृपया पहले नक्शे पर कम से कम 3 कोने जोड़ें!" else "Please mark at least 3 boundary corners on map!"
                                } else {
                                    newPlotNameInput = "खेत #${savedPlots.size + 1}"
                                    showSaveDialog = true
                                }
                            },
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = KisanEmerald),
                            modifier = Modifier
                                .weight(1.5f)
                                .height(46.dp)
                        ) {
                            Icon(Icons.Default.Check, contentDescription = null, modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = if (isHi) "यह खेत सहेजें" else "Save Farm Plot",
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp
                            )
                        }
                    }
                }
            }
        }
    }

    // Save Plot Dialog
    if (showSaveDialog) {
        AlertDialog(
            onDismissRequest = { showSaveDialog = false },
            title = {
                Text(if (isHi) "खेत का नाम दर्ज करें" else "Save Farm Boundary", fontWeight = FontWeight.Bold)
            },
            text = {
                Column {
                    Text(
                        text = if (isHi) "रकबा: ${String.format("%.2f", acres)} एकड़ (${String.format("%.2f", bigha)} बीघा)" else "Area: ${String.format("%.2f", acres)} Acres",
                        fontSize = 13.sp,
                        color = KisanEmerald,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    OutlinedTextField(
                        value = newPlotNameInput,
                        onValueChange = { newPlotNameInput = it },
                        label = { Text(if (isHi) "खेत का नाम (उदा. नहर वाला खेत)" else "Plot Name (e.g. North Canal Field)") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        val newPlot = SavedPlotItem(
                            id = "plot_${System.currentTimeMillis()}",
                            plotName = if (newPlotNameInput.isNotBlank()) newPlotNameInput else "खेत #${savedPlots.size + 1}",
                            areaAcres = acres,
                            areaBigha = bigha,
                            state = selectedState,
                            centroid_lat = plotCoordinates.firstOrNull()?.get(0) ?: 28.9845,
                            centroid_lng = plotCoordinates.firstOrNull()?.get(1) ?: 77.7064,
                            coordinates = plotCoordinates
                        )
                        val updated = savedPlots + newPlot
                        persistPlots(updated)
                        selectedPlot = newPlot
                        showSaveDialog = false
                        snackbarMessage = if (isHi) "खेत '${newPlot.plotName}' सफलतापूर्वक सहेज लिया गया!" else "Plot '${newPlot.plotName}' saved!"
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = KisanEmerald)
                ) {
                    Text(if (isHi) "सहेजें" else "Save")
                }
            },
            dismissButton = {
                TextButton(onClick = { showSaveDialog = false }) {
                    Text(if (isHi) "रद्द करें" else "Cancel")
                }
            }
        )
    }

    // Multi-Land Management Bottom Sheet
    if (showPlotsListSheet) {
        ModalBottomSheet(
            onDismissRequest = { showPlotsListSheet = false },
            containerColor = MaterialTheme.colorScheme.surface
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = if (isHi) "मेरे सहेजे गए खेत (Saved Lands)" else "My Saved Farm Plots",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "${savedPlots.size} ${if (isHi) "खेत" else "Plots"}",
                        fontSize = 13.sp,
                        color = KisanEmerald,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                if (savedPlots.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(30.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = if (isHi) "कोई सहेजा हुआ खेत नहीं है। नक्शे पर बिंदु बनाकर सहेजें।" else "No saved plots. Mark points on map and save.",
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            fontSize = 14.sp
                        )
                    }
                } else {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        items(savedPlots) { plot ->
                            Card(
                                shape = RoundedCornerShape(14.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = if (selectedPlot?.id == plot.id) KisanEmerald.copy(alpha = 0.12f) else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                                ),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        selectedPlot = plot
                                        if (plot.coordinates.isNotEmpty()) {
                                            plotCoordinates = plot.coordinates
                                            webViewRef?.evaluateJavascript("loadPlot('${gson.toJson(plot.coordinates)}')", null)
                                        }
                                        showPlotsListSheet = false
                                    }
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(14.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(
                                            text = plot.plotName,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 15.sp,
                                            color = MaterialTheme.colorScheme.onSurface
                                        )
                                        Spacer(modifier = Modifier.height(2.dp))
                                        Text(
                                            text = "${String.format("%.2f", plot.areaAcres)} ${if (isHi) "एकड़" else "Acres"} • ${String.format("%.2f", plot.areaBigha ?: (plot.areaAcres * 1.6))} ${if (isHi) "बीघा" else "Bigha"}",
                                            fontSize = 12.sp,
                                            color = KisanEmerald,
                                            fontWeight = FontWeight.SemiBold
                                        )
                                    }

                                    Row {
                                        IconButton(
                                            onClick = {
                                                val updated = savedPlots.filter { it.id != plot.id }
                                                persistPlots(updated)
                                                if (selectedPlot?.id == plot.id) {
                                                    selectedPlot = updated.firstOrNull()
                                                }
                                            }
                                        ) {
                                            Icon(Icons.Default.Delete, contentDescription = "Delete", tint = Color(0xFFEF4444), modifier = Modifier.size(20.dp))
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}

@Composable
fun GisUnitMetricPill(
    title: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(10.dp),
        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(vertical = 6.dp, horizontal = 6.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = title, fontSize = 9.sp, color = MaterialTheme.colorScheme.onSurfaceVariant, maxLines = 1)
            Text(text = value, fontSize = 11.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface, maxLines = 1)
        }
    }
}
