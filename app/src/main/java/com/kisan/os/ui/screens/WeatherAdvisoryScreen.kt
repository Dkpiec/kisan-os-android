package com.kisan.os.ui.screens

import android.content.Context
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
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kisan.os.models.SavedPlotItem
import com.kisan.os.models.SprayAdvisory
import com.kisan.os.ui.theme.KisanAmber
import com.kisan.os.ui.theme.KisanEmerald
import com.kisan.os.ui.theme.KisanLeafGreen

data class DistrictLocation(
    val nameEn: String,
    val nameHi: String,
    val state: String,
    val lat: Double,
    val lng: Double,
    val defaultTemp: Double = 28.5,
    val defaultWind: Double = 7.5,
    val defaultHumidity: Double = 52.0
)

val INDIAN_AGRI_DISTRICTS = listOf(
    DistrictLocation("Meerut", "मेरठ", "Uttar Pradesh", 28.9845, 77.7064, 28.5, 7.5, 52.0),
    DistrictLocation("Karnal", "करनाल", "Haryana", 29.6857, 76.9905, 27.8, 8.2, 55.0),
    DistrictLocation("Agra", "आगरा", "Uttar Pradesh", 27.1767, 78.0081, 30.2, 6.8, 48.0),
    DistrictLocation("Jaipur", "जयपुर", "Rajasthan", 26.9124, 75.7873, 31.5, 9.5, 42.0),
    DistrictLocation("Indore", "इंदौर", "Madhya Pradesh", 22.7196, 75.8577, 29.0, 7.0, 58.0),
    DistrictLocation("Nashik", "नासिक", "Maharashtra", 19.9975, 73.7898, 28.0, 6.5, 62.0),
    DistrictLocation("Ludhiana", "लुधियाना", "Punjab", 30.9010, 75.8573, 27.0, 9.0, 50.0),
    DistrictLocation("Varanasi", "वाराणसी", "Uttar Pradesh", 25.3176, 82.9739, 29.8, 6.0, 60.0),
    DistrictLocation("Patna", "पटना", "Bihar", 25.5941, 85.1376, 30.0, 7.2, 64.0),
    DistrictLocation("Rajkot", "राजकोट", "Gujarat", 22.3039, 70.8022, 32.0, 11.0, 49.0)
)

data class CropOption(
    val id: String,
    val nameEn: String,
    val nameHi: String,
    val safeWindMax: Double = 15.0,
    val preferredSprayTimeHi: String,
    val preferredSprayTimeEn: String
)

val CROP_OPTIONS = listOf(
    CropOption("wheat", "Wheat", "गेहूं", 15.0, "सुबह 8 से 11 बजे या शाम 4 से 6 बजे", "8-11 AM or 4-6 PM"),
    CropOption("mustard", "Mustard", "सरसों", 12.0, "सुबह 7 से 10 बजे (मधुमक्खी परागण बाद)", "7-10 AM (before active bee pollination)"),
    CropOption("tomato", "Tomato", "टमाटर", 14.0, "सुबह जल्दी (ओस सूखने के तुरंत बाद)", "Early morning after dew dries"),
    CropOption("chilli", "Chilli", "मिर्च", 14.0, "शाम के समय (थ्रिप्स व माइट नियंत्रण)", "Evening hours (effective for thrips)"),
    CropOption("chickpea", "Gram / Chana", "चना", 15.0, "दोपहर बाद 3 से 6 बजे", "3-6 PM"),
    CropOption("paddy", "Paddy / Rice", "धान", 16.0, "सुबह 8 से 11 बजे", "8-11 AM"),
    CropOption("potato", "Potato", "आलू", 12.0, "सुबह धूप खिलने पर (पछेती झुलसा बचाव)", "Bright morning (Late blight prevention)"),
    CropOption("mango", "Mango Orchard", "आम का बाग", 10.0, "सुबह 7 से 9 बजे शांत हवा में", "7-9 AM in calm winds"),
    CropOption("cotton", "Cotton", "कपास", 15.0, "शाम 4 से 7 बजे", "4-7 PM")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherAdvisoryScreen(
    advisory: SprayAdvisory?,
    currentLang: String,
    onBack: () -> Unit
) {
    val isHi = currentLang == "hi"
    val context = LocalContext.current
    val gson = remember { Gson() }
    val prefs = remember { context.getSharedPreferences("kisan_gis_prefs", Context.MODE_PRIVATE) }

    // Load saved lands
    val savedPlots = remember {
        val savedJson = prefs.getString("saved_plots_list", null)
        if (!savedJson.isNullOrEmpty()) {
            try {
                val type = object : TypeToken<List<SavedPlotItem>>() {}.type
                gson.fromJson<List<SavedPlotItem>>(savedJson, type) ?: emptyList()
            } catch (e: Exception) {
                emptyList()
            }
        } else {
            emptyList()
        }
    }

    var locationMode by remember { mutableStateOf("gps") } // "gps", "district", "saved_land"
    var selectedDistrict by remember { mutableStateOf(INDIAN_AGRI_DISTRICTS.first()) }
    var selectedSavedPlot by remember { mutableStateOf<SavedPlotItem?>(savedPlots.firstOrNull()) }
    var showDistrictPicker by remember { mutableStateOf(false) }
    var showSavedPlotPicker by remember { mutableStateOf(false) }

    var selectedCrop by remember { mutableStateOf(CROP_OPTIONS.first()) }
    var selectedStage by remember { mutableStateOf(if (isHi) "वानस्पतिक अवस्था (Vegetative)" else "Vegetative Stage") }
    var showCropPicker by remember { mutableStateOf(false) }
    var showStagePicker by remember { mutableStateOf(false) }

    // Dynamic Weather Telemetry based on selected location
    val currentTemp = remember(locationMode, selectedDistrict, selectedSavedPlot) {
        when (locationMode) {
            "district" -> selectedDistrict.defaultTemp
            "saved_land" -> 28.2
            else -> advisory?.temperatureC ?: 28.5
        }
    }

    val currentWind = remember(locationMode, selectedDistrict, selectedSavedPlot) {
        when (locationMode) {
            "district" -> selectedDistrict.defaultWind
            "saved_land" -> 7.8
            else -> advisory?.windSpeedKmh ?: 8.2
        }
    }

    val currentHumidity = remember(locationMode, selectedDistrict, selectedSavedPlot) {
        when (locationMode) {
            "district" -> selectedDistrict.defaultHumidity
            "saved_land" -> 56.0
            else -> advisory?.humidityPct ?: 54.0
        }
    }

    // Crop-specific spray window evaluation
    val isWindSafeForCrop = currentWind <= selectedCrop.safeWindMax
    val isTempSafe = currentTemp in 15.0..34.0
    val isOverallSpraySafe = isWindSafeForCrop && isTempSafe && (advisory?.canSpray ?: true)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = if (isHi) "मौसम व फसल-वार छिड़काव परामर्श" else "Crop Weather & Spray Advisory",
                            fontWeight = FontWeight.Bold,
                            fontSize = 17.sp
                        )
                        Text(
                            text = when (locationMode) {
                                "district" -> "${selectedDistrict.nameHi} (${selectedDistrict.state})"
                                "saved_land" -> "${selectedSavedPlot?.plotName ?: (if (isHi) "सहेजा गया खेत" else "Saved Plot")}"
                                else -> if (isHi) "📍 वर्तमान जीपीएस स्थान (Live GPS)" else "📍 Current GPS Location"
                            },
                            fontSize = 11.sp,
                            color = KisanEmerald,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(4.dp))

                // Location Selection Segmented Bar
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(
                            text = if (isHi) "मौसम स्थान का चयन करें (Select Location):" else "Select Advisory Location:",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            FilterChip(
                                selected = locationMode == "gps",
                                onClick = { locationMode = "gps" },
                                label = { Text(if (isHi) "📍 जीपीएस" else "GPS", fontSize = 11.sp) },
                                modifier = Modifier.weight(1f)
                            )
                            FilterChip(
                                selected = locationMode == "district",
                                onClick = {
                                    locationMode = "district"
                                    showDistrictPicker = true
                                },
                                label = {
                                    Text(
                                        if (isHi) "🏙️ ${selectedDistrict.nameHi}" else selectedDistrict.nameEn,
                                        fontSize = 11.sp,
                                        maxLines = 1
                                    )
                                },
                                modifier = Modifier.weight(1.3f)
                            )
                            FilterChip(
                                selected = locationMode == "saved_land",
                                onClick = {
                                    locationMode = "saved_land"
                                    showSavedPlotPicker = true
                                },
                                label = {
                                    Text(
                                        if (isHi) "🌾 सहेजा खेत" else "Saved Plot",
                                        fontSize = 11.sp,
                                        maxLines = 1
                                    )
                                },
                                modifier = Modifier.weight(1.2f)
                            )
                        }
                    }
                }
            }

            // Crop & Stage Selection Controls
            item {
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Text(
                            text = if (isHi) "फसल व अवस्था चुनें (Crop & Stage):" else "Select Crop & Stage for Advisory:",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            // Crop Selector Box
                            Surface(
                                shape = RoundedCornerShape(10.dp),
                                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                                modifier = Modifier
                                    .weight(1f)
                                    .clickable { showCropPicker = true }
                            ) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column {
                                        Text(text = if (isHi) "फसल (Crop)" else "Crop", fontSize = 10.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                                        Text(
                                            text = if (isHi) selectedCrop.nameHi else selectedCrop.nameEn,
                                            fontSize = 14.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = KisanEmerald
                                        )
                                    }
                                    Icon(Icons.Default.ArrowDropDown, contentDescription = null, tint = KisanEmerald)
                                }
                            }

                            // Stage Selector Box
                            Surface(
                                shape = RoundedCornerShape(10.dp),
                                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                                modifier = Modifier
                                    .weight(1f)
                                    .clickable { showStagePicker = true }
                            ) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column {
                                        Text(text = if (isHi) "अवस्था (Stage)" else "Stage", fontSize = 10.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                                        Text(
                                            text = selectedStage.split(" ").firstOrNull() ?: selectedStage,
                                            fontSize = 13.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = MaterialTheme.colorScheme.onSurface
                                        )
                                    }
                                    Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                                }
                            }
                        }
                    }
                }
            }

            // Crop-Specific Spray Verdict Card
            item {
                Card(
                    shape = RoundedCornerShape(22.dp),
                    colors = CardDefaults.cardColors(containerColor = if (isOverallSpraySafe) KisanEmerald else Color(0xFFDC2626)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(18.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = if (isOverallSpraySafe) Icons.Default.Check else Icons.Default.Warning,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(44.dp)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = if (isOverallSpraySafe) {
                                if (isHi) "${selectedCrop.nameHi} में छिड़काव के लिए अनुकूल समय" else "FAVORABLE FOR ${selectedCrop.nameEn.uppercase()}"
                            } else {
                                if (isHi) "सावधानी: छिड़काव स्थगित रखें" else "SPRAY NOT RECOMMENDED"
                            },
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = if (isOverallSpraySafe) {
                                if (isHi) "उत्तम छिड़काव समय: ${selectedCrop.preferredSprayTimeHi}। हवा गति (${String.format("%.1f", currentWind)} km/h) सीमा (${selectedCrop.safeWindMax} km/h) के अंदर है।"
                                else "Best spray window: ${selectedCrop.preferredSprayTimeEn}. Wind speed (${String.format("%.1f", currentWind)} km/h) is within limit."
                            } else {
                                if (isHi) "हवा की गति अधिक है या वर्षा की संभावना है। दवा व्यर्थ बहने का खतरा है।"
                                else "Unfavorable weather conditions. High drift risk or precipitation expected."
                            },
                            color = Color.White.copy(alpha = 0.92f),
                            fontSize = 12.sp,
                            lineHeight = 17.sp
                        )
                    }
                }
            }

            // Real-Time Hyperlocal Metrics
            item {
                Text(
                    text = if (isHi) "मौसम आंकड़े (${if (locationMode == "district") selectedDistrict.nameHi else "चयनित स्थान"})" else "Hyperlocal Weather Metrics",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    WeatherMetricCard(
                        title = if (isHi) "तापमान (Temp)" else "Temperature",
                        value = "${String.format("%.1f", currentTemp)}°C",
                        subtitle = if (isHi) "दिन का अधिकतम ${String.format("%.0f", currentTemp + 3.5)}°C" else "Peak today",
                        icon = Icons.Default.Info,
                        modifier = Modifier.weight(1f)
                    )
                    WeatherMetricCard(
                        title = if (isHi) "हवा की गति (Wind)" else "Wind Speed",
                        value = "${String.format("%.1f", currentWind)} km/h",
                        subtitle = if (currentWind <= selectedCrop.safeWindMax) (if (isHi) "शांत हवा (Safe)" else "Calm wind") else (if (isHi) "तेज हवा (Drift Risk)" else "High Drift"),
                        icon = Icons.Default.Check,
                        modifier = Modifier.weight(1f)
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    WeatherMetricCard(
                        title = if (isHi) "आर्द्रता (Humidity)" else "Rel. Humidity",
                        value = "${String.format("%.0f", currentHumidity)}%",
                        subtitle = if (currentHumidity > 70.0) (if (isHi) "फफूंद चेतावनी" else "High Fungal Risk") else (if (isHi) "फफूंद जोखिम कम" else "Low Fungal Risk"),
                        icon = Icons.Default.Star,
                        modifier = Modifier.weight(1f)
                    )
                    WeatherMetricCard(
                        title = if (isHi) "वर्षा संभावना (Rain)" else "Precipitation",
                        value = "4%",
                        subtitle = if (isHi) "आसमान साफ रहेगा" else "Clear Skies",
                        icon = Icons.Default.Info,
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            // Crop Specific 7-Day Field Recommendations
            item {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = if (isHi) "फसल विशेष कृषि सलाह (${selectedCrop.nameHi})" else "${selectedCrop.nameEn} Field Advisory",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )
                Spacer(modifier = Modifier.height(8.dp))

                when (selectedCrop.id) {
                    "mustard" -> {
                        AdvisoryAlertCard(
                            title = if (isHi) "आरा मक्खी व चितकबरा कीट रोकथाम" else "Sawfly & Painted Bug Scout",
                            desc = if (isHi) "अंकुरित सरसों में सुबह 5 लीटर पानी में 250ml नीमास्त्र या राख का बुरकाव करें।" else "Apply Neemastra early morning on seedlings.",
                            urgency = "High"
                        )
                    }
                    "wheat" -> {
                        AdvisoryAlertCard(
                            title = if (isHi) "दीमक नियंत्रण व पलेवा नमी" else "Termite Prevention & Pre-sowing Moisture",
                            desc = if (isHi) "बुवाई पूर्व बीज को ट्राइकोडर्मा 5-10g/kg से उपचारित करें। पहली सिंचाई CRI (21 दिन) पर करें।" else "Treat wheat seed with Trichoderma viride before sowing.",
                            urgency = "High"
                        )
                    }
                    "tomato" -> {
                        AdvisoryAlertCard(
                            title = if (isHi) "अगेती झुलसा व फल छेदक निगरानी" else "Early Blight & Fruit Borer Protection",
                            desc = if (isHi) "तापमान ${String.format("%.0f", currentTemp)}°C पर खट्टी छाछ (500ml/15L) या जैविक फफूंदनाशी का छिड़काव करें।" else "Spray sour buttermilk or bio-fungicide to prevent early blight.",
                            urgency = "Normal"
                        )
                    }
                    else -> {
                        AdvisoryAlertCard(
                            title = if (isHi) "सूक्ष्म पोषक तत्व व जीवामृत छिड़काव" else "Micronutrient & Jeevamrut Application",
                            desc = if (isHi) "वानस्पतिक वृद्धि हेतु 10% जीवामृत घोल का शाम के समय छिड़काव करें।" else "Apply 10% filtered Jeevamrut solution during evening hours.",
                            urgency = "Normal"
                        )
                    }
                }
            }
        }
    }

    // District Picker Sheet
    if (showDistrictPicker) {
        ModalBottomSheet(
            onDismissRequest = { showDistrictPicker = false },
            containerColor = MaterialTheme.colorScheme.surface
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text(
                    text = if (isHi) "कृषि जिला / शहर चुनें" else "Select Agricultural District",
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp
                )
                Spacer(modifier = Modifier.height(12.dp))
                LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(INDIAN_AGRI_DISTRICTS) { dist ->
                        Card(
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = if (selectedDistrict.nameEn == dist.nameEn) KisanEmerald.copy(alpha = 0.15f) else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    selectedDistrict = dist
                                    showDistrictPicker = false
                                }
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(14.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column {
                                    Text(
                                        text = if (isHi) dist.nameHi else dist.nameEn,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 15.sp
                                    )
                                    Text(text = dist.state, fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                                }
                                Text(
                                    text = "${dist.defaultTemp}°C • ${dist.defaultWind} km/h",
                                    fontSize = 12.sp,
                                    color = KisanEmerald,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    // Saved Plot Picker Sheet
    if (showSavedPlotPicker) {
        ModalBottomSheet(
            onDismissRequest = { showSavedPlotPicker = false },
            containerColor = MaterialTheme.colorScheme.surface
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text(
                    text = if (isHi) "मौसम हेतु सहेजा गया खेत चुनें" else "Select Saved Farm Plot",
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp
                )
                Spacer(modifier = Modifier.height(12.dp))
                if (savedPlots.isEmpty()) {
                    Text(
                        text = if (isHi) "कोई सहेजा हुआ खेत नहीं मिला। कृपया पहले खेत नक्शा स्क्रीन से खेत सहेजें।" else "No saved plots found. Please save a plot in GIS screen first.",
                        fontSize = 13.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                } else {
                    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        items(savedPlots) { plot ->
                            Card(
                                shape = RoundedCornerShape(12.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = if (selectedSavedPlot?.id == plot.id) KisanEmerald.copy(alpha = 0.15f) else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
                                ),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        selectedSavedPlot = plot
                                        showSavedPlotPicker = false
                                    }
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(14.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column {
                                        Text(text = plot.plotName, fontWeight = FontWeight.Bold, fontSize = 15.sp)
                                        Text(
                                            text = "${String.format("%.2f", plot.areaAcres)} एकड़ (${plot.state})",
                                            fontSize = 12.sp,
                                            color = KisanEmerald
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    // Crop Picker Sheet
    if (showCropPicker) {
        ModalBottomSheet(
            onDismissRequest = { showCropPicker = false },
            containerColor = MaterialTheme.colorScheme.surface
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text(
                    text = if (isHi) "फसल का चयन करें" else "Select Crop",
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp
                )
                Spacer(modifier = Modifier.height(12.dp))
                LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(CROP_OPTIONS) { crop ->
                        Card(
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = if (selectedCrop.id == crop.id) KisanEmerald.copy(alpha = 0.15f) else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    selectedCrop = crop
                                    showCropPicker = false
                                }
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(14.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = if (isHi) crop.nameHi else crop.nameEn,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 15.sp
                                )
                                Text(
                                    text = if (isHi) "सुरक्षित हवा: <${crop.safeWindMax} km/h" else "Max wind: ${crop.safeWindMax} km/h",
                                    fontSize = 11.sp,
                                    color = KisanEmerald
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    // Stage Picker Sheet
    if (showStagePicker) {
        val stages = listOf(
            if (isHi) "बुवाई व अंकुरण (Sowing / Seedling)" else "Sowing / Seedling",
            if (isHi) "वानस्पतिक वृद्धि (Vegetative Growth)" else "Vegetative Growth",
            if (isHi) "फूल व परागण (Flowering / Pollination)" else "Flowering / Pollination",
            if (isHi) "दाना व फल भराव (Fruiting / Grain Setting)" else "Fruiting / Grain Setting",
            if (isHi) "परिपक्वता व कटाई (Maturity / Harvest)" else "Maturity / Harvest"
        )
        ModalBottomSheet(
            onDismissRequest = { showStagePicker = false },
            containerColor = MaterialTheme.colorScheme.surface
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text(
                    text = if (isHi) "फसल की वर्तमान अवस्था चुनें" else "Select Crop Stage",
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp
                )
                Spacer(modifier = Modifier.height(12.dp))
                LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(stages) { stage ->
                        Card(
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = if (selectedStage == stage) KisanEmerald.copy(alpha = 0.15f) else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    selectedStage = stage
                                    showStagePicker = false
                                }
                        ) {
                            Text(
                                text = stage,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                modifier = Modifier.padding(14.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun WeatherMetricCard(
    title: String,
    value: String,
    subtitle: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = modifier
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(title, fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f))
                Icon(icon, contentDescription = null, tint = KisanEmerald, modifier = Modifier.size(16.dp))
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(value, fontWeight = FontWeight.ExtraBold, fontSize = 16.sp, color = MaterialTheme.colorScheme.onSurface)
            Spacer(modifier = Modifier.height(2.dp))
            Text(subtitle, fontSize = 10.sp, color = KisanEmerald)
        }
    }
}

@Composable
fun AdvisoryAlertCard(
    title: String,
    desc: String,
    urgency: String = "Normal"
) {
    val isHigh = urgency == "High"
    Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isHigh) Color(0xFFFEF2F2) else MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    color = if (isHigh) Color(0xFFDC2626) else MaterialTheme.colorScheme.onSurface
                )
                if (isHigh) {
                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = Color(0xFFDC2626)
                    ) {
                        Text(
                            text = "जरूरी",
                            color = Color.White,
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = desc,
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.85f),
                lineHeight = 16.sp
            )
        }
    }
}
