package com.kisan.os.ui.screens

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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kisan.os.models.CropSelectionAdvisory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CropSelectorScreen(
    currentLang: String,
    onBack: () -> Unit
) {
    val isHi = currentLang == "hi"

    var selectedSeason by remember { mutableStateOf("Rabi (रबी)") }
    var selectedSoil by remember { mutableStateOf("Alluvial/Loam (दोमट)") }
    var selectedWater by remember { mutableStateOf("Medium (मध्यम सिंचाई)") }

    val crops = remember {
        listOf(
            CropSelectionAdvisory(
                cropNameEn = "High-Yield Biofortified Wheat",
                cropNameHi = "उन्नत जिंक युक्त बायो-फोर्टिफाइड गेहूँ",
                season = "Rabi (रबी)",
                soilType = "Alluvial/Loam (दोमट)",
                waterLevel = "Medium (मध्यम सिंचाई)",
                estimatedProfit = "₹45,000 - ₹55,000 / Acre",
                bestVarieties = "DBW-327, DBW-187 (Karan Vandana), C-306",
                durationDays = 150
            ),
            CropSelectionAdvisory(
                cropNameEn = "Pungent Hybrid Chilli (हरी मिर्च)",
                cropNameHi = "तीखी संकर हरी मिर्च (उच्च मुनाफा)",
                season = "Rabi (रबी)",
                soilType = "Alluvial/Loam (दोमट)",
                waterLevel = "Medium (मध्यम सिंचाई)",
                estimatedProfit = "₹1,20,000 - ₹1,80,000 / Acre",
                bestVarieties = "US 341, VNR 332, Byadgi Kaddi",
                durationDays = 130
            ),
            CropSelectionAdvisory(
                cropNameEn = "Desi Sharbati Wheat (शरबती गेहूँ)",
                cropNameHi = "देशी शरबती गेहूँ (कम पानी, 50% प्रीमियम भाव)",
                season = "Rabi (रबी)",
                soilType = "Sandy Loam (बलुई दोमट)",
                waterLevel = "Low (कम पानी/असिंचित)",
                estimatedProfit = "₹40,000 - ₹52,000 / Acre",
                bestVarieties = "C-306, Sujata (HI-617)",
                durationDays = 140
            ),
            CropSelectionAdvisory(
                cropNameEn = "Pusa Double Zero Mustard (राई/सरसों)",
                cropNameHi = "पूसा डबल जीरो सरसों (42% तेल, कम खर्च)",
                season = "Rabi (रबी)",
                soilType = "Sandy Loam (बलुई दोमट)",
                waterLevel = "Low (कम पानी/असिंचित)",
                estimatedProfit = "₹38,000 - ₹48,000 / Acre",
                bestVarieties = "Pusa Mustard 31, Giriraj (DRMRIJ-31)",
                durationDays = 135
            ),
            CropSelectionAdvisory(
                cropNameEn = "Triple Disease Resistant Tomato",
                cropNameHi = "अर्क रक्षक टमाटर (खट्टा-मीठा, गाढ़ी ग्रेवी)",
                season = "Rabi (रबी)",
                soilType = "Alluvial/Loam (दोमट)",
                waterLevel = "High (भरपूर पानी/ड्रिप)",
                estimatedProfit = "₹1,50,000 - ₹2,20,000 / Acre",
                bestVarieties = "Arka Rakshak F1, Abhilash F1, Heemsohna",
                durationDays = 135
            ),
            CropSelectionAdvisory(
                cropNameEn = "Pusa Extra Long Basmati Rice",
                cropNameHi = "पूसा 1885 रोग-रोधी बासमती धान",
                season = "Kharif (खरीफ)",
                soilType = "Clay Loam (मटियारी दोमट)",
                waterLevel = "High (भरपूर पानी/ड्रिप)",
                estimatedProfit = "₹65,000 - ₹85,000 / Acre",
                bestVarieties = "Pusa Basmati 1885, PB 1847, PB 1121",
                durationDays = 135
            )
        )
    }

    val filteredCrops = crops.filter {
        it.season.contains(selectedSeason.split(" ")[0]) || selectedSeason.contains("All")
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            if (isHi) "फसल चयन सलाहकार" else "Smart Crop Selector",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                        Text(
                            if (isHi) "मिट्टी, मौसम व पानी के अनुसार सर्वोत्तम फसल" else "AI Recommendations by Soil, Water & Season",
                            fontSize = 12.sp,
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
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                )
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Text(
                        if (isHi) "अपने खेत की शर्तें चुनें:" else "Select Your Farm Conditions:",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        listOf("Rabi (रबी)", "Kharif (खरीफ)", "Zaid (जायद)").forEach { s ->
                            FilterChip(
                                selected = selectedSeason == s,
                                onClick = { selectedSeason = s },
                                label = { Text(s.split(" ")[0], fontSize = 12.sp) }
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        listOf("Alluvial/Loam (दोमट)", "Sandy Loam (बलुई दोमट)").forEach { s ->
                            FilterChip(
                                selected = selectedSoil == s,
                                onClick = { selectedSoil = s },
                                label = { Text(if (isHi) s.split(" ")[1] else s.split(" ")[0], fontSize = 11.sp) }
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = if (isHi) "आपके लिए अनुशंसित शीर्ष फसलें (${filteredCrops.size})" else "Recommended Top Crops (${filteredCrops.size})",
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(filteredCrops) { item ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surface
                        ),
                        elevation = CardDefaults.cardElevation(2.dp)
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    if (isHi) item.cropNameHi else item.cropNameEn,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Surface(
                                    shape = RoundedCornerShape(8.dp),
                                    color = Color(0xFF059669).copy(alpha = 0.15f)
                                ) {
                                    Text(
                                        item.estimatedProfit,
                                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFF059669)
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = "${if (isHi) "सर्वोत्तम किस्में: " else "Top Varieties: "}${item.bestVarieties}",
                                fontSize = 13.sp,
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.SemiBold
                            )

                            Spacer(modifier = Modifier.height(6.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    "⏳ ${item.durationDays} ${if (isHi) "दिन अवधि" else "Days Duration"}",
                                    fontSize = 12.sp,
                                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                                )
                                Text(
                                    "💧 ${item.waterLevel}",
                                    fontSize = 12.sp,
                                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
