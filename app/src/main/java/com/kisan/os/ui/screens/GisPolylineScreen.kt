package com.kisan.os.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kisan.os.ui.theme.KisanEmerald
import com.kisan.os.ui.theme.KisanLeafGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GisPolylineScreen(
    currentLang: String,
    onBack: () -> Unit
) {
    var plotName by remember { mutableStateOf("Main Farm (मुख्य खेत)") }
    var pointsCount by remember { mutableStateOf(5) }
    var selectedUnit by remember { mutableStateOf("Bigha (बीघा)") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = if (currentLang == "hi") "खेत नक्शा व रकबा नाप (GIS)" else "GIS Land Area Mapper",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(10.dp))

            // Map Preview Simulation Box
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            imageVector = Icons.Default.SatelliteAlt,
                            contentDescription = null,
                            tint = KisanEmerald,
                            modifier = Modifier.size(48.dp)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = if (currentLang == "hi") "उपग्रह व भू-भाग नक्शा (Satellite & Terrain)" else "High-Res Satellite View",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                        Text(
                            text = if (currentLang == "hi") "मेड़ पर बिंदु लगाकर रकबा नापें ($pointsCount बिंदु सक्रिय)" else "Tap boundary corners to trace perimeter ($pointsCount points)",
                            color = Color(0xFF94A3B8),
                            fontSize = 12.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            // Calculated Area Display Card
            Card(
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = if (currentLang == "hi") "नापा गया रकबा (Calculated Land Area)" else "Measured Plot Area",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(text = "Acres (एकड़)", fontSize = 11.sp, color = Color(0xFF64748B))
                            Text(text = "4.20", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = KisanEmerald)
                        }
                        Column {
                            Text(text = "Bigha (पक्का बीघा)", fontSize = 11.sp, color = Color(0xFF64748B))
                            Text(text = "6.72", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color(0xFF0F172A))
                        }
                        Column {
                            Text(text = "Guntha (गुंठा)", fontSize = 11.sp, color = Color(0xFF64748B))
                            Text(text = "168.0", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color(0xFF0F172A))
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                    Divider(color = Color(0xFFE2E8F0))
                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "${if (currentLang == "hi") "मेड़ की परिधि (Perimeter): " else "Perimeter: "}580 meters",
                            fontSize = 12.sp,
                            color = Color(0xFF64748B)
                        )
                        Text(
                            text = "${if (currentLang == "hi") "हेक्टेयर: " else "Hectares: "}1.70 ha",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = KisanLeafGreen
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { /* Save Plot */ },
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(containerColor = KisanEmerald),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Icon(Icons.Default.Save, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = if (currentLang == "hi") "इस खेत को सहेजें (Save Field)" else "Save Farm Boundary",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )
            }
        }
    }
}
