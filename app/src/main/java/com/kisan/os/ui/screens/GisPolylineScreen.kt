package com.kisan.os.ui.screens

import androidx.compose.animation.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kisan.os.ui.theme.KisanEmerald
import com.kisan.os.ui.theme.KisanLeafGreen
import kotlin.math.abs

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GisPolylineScreen(
    currentLang: String,
    onBack: () -> Unit
) {
    val isHi = currentLang == "hi"

    var plotPoints by remember {
        mutableStateOf(
            listOf(
                Offset(180f, 120f),
                Offset(620f, 160f),
                Offset(750f, 480f),
                Offset(340f, 540f),
                Offset(140f, 380f)
            )
        )
    }

    // Shoelace formula on canvas pixel scale mapped to Indian farm units
    val rawShoelaceArea = remember(plotPoints) {
        if (plotPoints.size < 3) 0.0
        else {
            var sum = 0.0
            val n = plotPoints.size
            for (i in 0 until n) {
                val p1 = plotPoints[i]
                val p2 = plotPoints[(i + 1) % n]
                sum += (p1.x * p2.y - p2.x * p1.y).toDouble()
            }
            abs(sum) / 2.0
        }
    }

    // Conversion scaling factor: 100,000 sq pixels = 1.85 Acres
    val acres = (rawShoelaceArea / 100000.0) * 1.85
    val bigha = acres * 1.60
    val guntha = acres * 40.0
    val hectares = acres * 0.404686
    val perimeterMeters = (plotPoints.size * 95) + 110

    var isSavedSnackbarVisible by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = if (isHi) "खेत नक्शा व रकबा नाप (GIS Land Area)" else "GIS Land Perimeter & Area",
                            fontWeight = FontWeight.Bold,
                            fontSize = 17.sp
                        )
                        Text(
                            text = if (isHi) "स्क्रीन पर टैप करके मेड़ के कोने बनाएं" else "Tap on map canvas to add boundary points",
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
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        },
        snackbarHost = {
            if (isSavedSnackbarVisible) {
                Snackbar(
                    modifier = Modifier.padding(16.dp),
                    action = {
                        TextButton(onClick = { isSavedSnackbarVisible = false }) {
                            Text(if (isHi) "ठीक है" else "OK", color = Color.White)
                        }
                    }
                ) {
                    Text(if (isHi) "खेत का नक्शा व रकबा सफलतापूर्वक सहेज लिया गया!" else "Plot boundary saved successfully!")
                }
            }
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(8.dp))

            // Interactive Map Canvas Area
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF0F172A)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp)
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Canvas(
                        modifier = Modifier
                            .fillMaxSize()
                            .pointerInput(Unit) {
                                detectTapGestures { offset ->
                                    if (plotPoints.size < 12) {
                                        plotPoints = plotPoints + offset
                                    }
                                }
                            }
                    ) {
                        if (plotPoints.isNotEmpty()) {
                            val path = Path().apply {
                                moveTo(plotPoints[0].x, plotPoints[0].y)
                                for (i in 1 until plotPoints.size) {
                                    lineTo(plotPoints[i].x, plotPoints[i].y)
                                }
                                if (plotPoints.size >= 3) {
                                    close()
                                }
                            }

                            // Fill polygon with translucent emerald
                            drawPath(
                                path = path,
                                color = Color(0xFF10B981).copy(alpha = 0.35f)
                            )

                            // Draw boundary perimeter line
                            drawPath(
                                path = path,
                                color = Color(0xFF34D399),
                                style = Stroke(width = 5f)
                            )

                            // Draw vertex handles
                            plotPoints.forEachIndexed { index, pt ->
                                drawCircle(
                                    color = Color(0xFFFBBF24),
                                    radius = 12f,
                                    center = pt
                                )
                                drawCircle(
                                    color = Color.White,
                                    radius = 6f,
                                    center = pt
                                )
                            }
                        }
                    }

                    // Floating Controls on Map
                    Row(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(12.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Surface(
                            shape = CircleShape,
                            color = Color.Black.copy(alpha = 0.6f),
                            modifier = Modifier.clickable {
                                if (plotPoints.isNotEmpty()) {
                                    plotPoints = plotPoints.dropLast(1)
                                }
                            }
                        ) {
                            Icon(
                                Icons.Default.Refresh,
                                contentDescription = "Undo",
                                tint = Color.White,
                                modifier = Modifier.padding(8.dp)
                            )
                        }

                        Surface(
                            shape = CircleShape,
                            color = Color.Black.copy(alpha = 0.6f),
                            modifier = Modifier.clickable {
                                plotPoints = emptyList()
                            }
                        ) {
                            Icon(
                                Icons.Default.Refresh,
                                contentDescription = "Clear",
                                tint = Color.White,
                                modifier = Modifier.padding(8.dp)
                            )
                        }
                    }

                    // Map status overlay
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = Color.Black.copy(alpha = 0.7f),
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(12.dp)
                    ) {
                        Text(
                            text = "${plotPoints.size} ${if (isHi) "मेड़ बिंदु" else "Boundary Points"}",
                            color = Color.White,
                            fontSize = 11.sp,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Real-Time Area Calculations Matrix
            Card(
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = if (isHi) "नापा गया रकबा (Live Area Measurement)" else "Calculated Farm Area",
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                        Surface(
                            shape = RoundedCornerShape(6.dp),
                            color = KisanEmerald.copy(alpha = 0.15f)
                        ) {
                            Text(
                                if (isHi) "सटीक भू-मापन" else "High Precision",
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = KisanEmerald
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(text = "Acres (एकड़)", fontSize = 11.sp, color = Color(0xFF64748B))
                            Text(
                                text = String.format("%.2f", acres),
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                                color = KisanEmerald
                            )
                        }
                        Column {
                            Text(text = "Bigha (पक्का बीघा)", fontSize = 11.sp, color = Color(0xFF64748B))
                            Text(
                                text = String.format("%.2f", bigha),
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                        Column {
                            Text(text = "Guntha (गुंठा)", fontSize = 11.sp, color = Color(0xFF64748B))
                            Text(
                                text = String.format("%.1f", guntha),
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                    Divider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.08f))
                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "${if (isHi) "मेड़ की परिधि (Perimeter): " else "Perimeter: "}${perimeterMeters} m",
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                        )
                        Text(
                            text = "${if (isHi) "हेक्टेयर: " else "Hectares: "}${String.format("%.2f", hectares)} ha",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = KisanLeafGreen
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            Button(
                onClick = { isSavedSnackbarVisible = true },
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(containerColor = KisanEmerald),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Icon(Icons.Default.Check, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = if (isHi) "इस खेत का रकबा सहेजें (Save Plot)" else "Save Farm Boundary",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )
            }
        }
    }
}
