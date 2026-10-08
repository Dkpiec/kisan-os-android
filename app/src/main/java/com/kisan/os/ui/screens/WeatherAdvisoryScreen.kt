package com.kisan.os.ui.screens

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kisan.os.models.SprayAdvisory
import com.kisan.os.ui.theme.KisanAmber
import com.kisan.os.ui.theme.KisanEmerald
import com.kisan.os.ui.theme.KisanLeafGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherAdvisoryScreen(
    advisory: SprayAdvisory?,
    currentLang: String,
    onBack: () -> Unit
) {
    val isHi = currentLang == "hi"

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = if (isHi) "मौसम व छिड़काव परामर्श" else "Weather & Spray Advisory",
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
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(6.dp))
                // Spray Window Verdict Card
                Card(
                    shape = RoundedCornerShape(22.dp),
                    colors = CardDefaults.cardColors(containerColor = if (advisory?.canSpray != false) KisanEmerald else Color(0xFFDC2626)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = if (advisory?.canSpray != false) Icons.Default.CheckCircle else Icons.Default.Warning,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(48.dp)
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = if (isHi) "छिड़काव स्थिति: अनुकूल (Favorable)" else "Spray Window: HIGHLY RECOMMENDED",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 17.sp
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = if (isHi) "अगले 12 घंटे में बारिश की संभावना नगण्य है। हवा की गति 8 km/h शांत है।" else "Low rain probability (<10%) for next 12 hours. Calm wind speed at 8 km/h.",
                            color = Color.White.copy(alpha = 0.9f),
                            fontSize = 13.sp,
                            lineHeight = 18.sp
                        )
                    }
                }
            }

            item {
                Text(
                    text = if (isHi) "वर्तमान मौसम आंकड़े (Current Metrics)" else "Current Hyperlocal Metrics",
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
                        value = "28.5°C",
                        subtitle = if (isHi) "दिन का अधिकतम 32°C" else "Max today 32°C",
                        icon = Icons.Default.Thermostat,
                        modifier = Modifier.weight(1f)
                    )
                    WeatherMetricCard(
                        title = if (isHi) "हवा की गति (Wind)" else "Wind Speed",
                        value = "8.2 km/h",
                        subtitle = if (isHi) "पश्चिम दिशा (West)" else "Direction: West",
                        icon = Icons.Default.Air,
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
                        value = "54%",
                        subtitle = if (isHi) "फफूंद जोखिम कम" else "Low Fungal Risk",
                        icon = Icons.Default.WaterDrop,
                        modifier = Modifier.weight(1f)
                    )
                    WeatherMetricCard(
                        title = if (isHi) "वर्षा संभावना (Rain)" else "Precipitation",
                        value = "5%",
                        subtitle = if (isHi) "आसमान साफ रहेगा" else "Clear Skies",
                        icon = Icons.Default.CloudQueue,
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = if (isHi) "7-दिवसीय कृषि परामर्श (7-Day Field Action)" else "7-Day Agronomic Advisory",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                AdvisoryAlertCard(
                    title = if (isHi) "गेहूं बुवाई नमी संरक्षण (Wheat Sowing Prep)" else "Soil Moisture Prep for Wheat Sowing",
                    desc = if (isHi) "अगले 4 दिन मौसम शुष्क रहेगा। पलेवा (Pre-sowing irrigation) लगाने का सबसे उत्तम समय है।" else "Dry conditions expected. Ideal window for pre-sowing irrigation (Palewa) across UP/Haryana plains.",
                    urgency = "High"
                )
                Spacer(modifier = Modifier.height(8.dp))
                AdvisoryAlertCard(
                    title = if (isHi) "सरसों में आरा मक्खी व एफिड निगरानी" else "Mustard Sawfly & Aphid Scout",
                    desc = if (isHi) "तापमान 28°C होने से नई अंकुरित सरसों में आरा मक्खी की रोकथाम हेतु सुबह नीमास्त्र का छिड़काव करें।" else "Scout early germinated mustard seedlings for sawfly larvae. Apply Neemastra early morning.",
                    urgency = "Normal"
                )
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
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = modifier
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = title, fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f))
                Icon(imageVector = icon, contentDescription = null, tint = KisanEmerald, modifier = Modifier.size(18.dp))
            }
            Spacer(modifier = Modifier.height(6.dp))
            Text(text = value, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
            Spacer(modifier = Modifier.height(2.dp))
            Text(text = subtitle, fontSize = 10.sp, color = KisanLeafGreen, fontWeight = FontWeight.Medium)
        }
    }
}

@Composable
fun AdvisoryAlertCard(
    title: String,
    desc: String,
    urgency: String
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.Top
        ) {
            Surface(
                shape = CircleShape,
                color = if (urgency == "High") KisanAmber.copy(alpha = 0.2f) else KisanEmerald.copy(alpha = 0.2f),
                modifier = Modifier.size(36.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = if (urgency == "High") Icons.Default.PriorityHigh else Icons.Default.TipsAndUpdates,
                        contentDescription = null,
                        tint = if (urgency == "High") KisanAmber else KisanEmerald,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = title, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                Spacer(modifier = Modifier.height(2.dp))
                Text(text = desc, fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f), lineHeight = 16.sp)
            }
        }
    }
}
