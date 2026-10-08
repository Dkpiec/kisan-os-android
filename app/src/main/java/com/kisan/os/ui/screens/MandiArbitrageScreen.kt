package com.kisan.os.ui.screens

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.kisan.os.models.MandiArbitrageItem
import com.kisan.os.ui.theme.KisanAmber
import com.kisan.os.ui.theme.KisanEmerald
import com.kisan.os.ui.theme.KisanLeafGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MandiArbitrageScreen(
    currentLang: String,
    onBack: () -> Unit
) {
    var commodity by remember { mutableStateOf("Tomato (टमाटर)") }
    var produceQuintals by remember { mutableStateOf("50") }

    val sampleMandis = listOf(
        MandiArbitrageItem(
            mandiName = "Azadpur APMC",
            district = "Delhi",
            state = "Delhi",
            distanceKm = 42.0,
            modalPrice = 2850.0,
            grossRevenue = 142500.0,
            transportCost = 1008.0,
            mandiCess = 2137.5,
            netPayout = 139354.5,
            netRate = 2787.09,
            profitRank = 1,
            isBestDeal = true
        ),
        MandiArbitrageItem(
            mandiName = "Sonipat Mandi",
            district = "Sonipat",
            state = "Haryana",
            distanceKm = 18.0,
            modalPrice = 2520.0,
            grossRevenue = 126000.0,
            transportCost = 432.0,
            mandiCess = 1890.0,
            netPayout = 123678.0,
            netRate = 2473.56,
            profitRank = 2,
            isBestDeal = false
        ),
        MandiArbitrageItem(
            mandiName = "Meerut Mandi",
            district = "Meerut",
            state = "Uttar Pradesh",
            distanceKm = 78.0,
            modalPrice = 2700.0,
            grossRevenue = 135000.0,
            transportCost = 1872.0,
            mandiCess = 2025.0,
            netPayout = 131103.0,
            netRate = 2622.06,
            profitRank = 3,
            isBestDeal = false
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = if (currentLang == "hi") "150 km मंडी भाव व शुद्ध मुनाफा (Arbitrage)" else "150km Mandi Arbitrage",
                        fontWeight = FontWeight.Bold,
                        fontSize = 17.sp
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

            // Produce Input Card
            Card(
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = if (currentLang == "hi") "फसल एवं मात्रा का चयन" else "Select Crop & Quantity",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        OutlinedTextField(
                            value = commodity,
                            onValueChange = { commodity = it },
                            label = { Text(if (currentLang == "hi") "फसल" else "Commodity") },
                            singleLine = true,
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.weight(1.3f)
                        )
                        OutlinedTextField(
                            value = produceQuintals,
                            onValueChange = { produceQuintals = it },
                            label = { Text(if (currentLang == "hi") "क्विंटल" else "Quintals") },
                            singleLine = true,
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.weight(0.7f)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = if (currentLang == "hi") "150 km के दायरे में मंडियां (किराया काटकर शुद्ध कमाई)" else "Nearby Mandis (Ranked by Net In-Hand Profit)",
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(10.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(sampleMandis) { item ->
                    MandiArbitrageCard(item = item, currentLang = currentLang)
                }
            }
        }
    }
}

@Composable
fun MandiArbitrageCard(
    item: MandiArbitrageItem,
    currentLang: String
) {
    val borderColor = if (item.isBestDeal) KisanEmerald else Color.Transparent

    Card(
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (item.isBestDeal) Color(0xFFF0FDF4) else MaterialTheme.colorScheme.surface
        ),
        border = if (item.isBestDeal) androidx.compose.foundation.BorderStroke(2.dp, KisanEmerald) else null,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = item.mandiName,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Color(0xFF0F172A)
                    )
                    Text(
                        text = "${item.district}, ${item.state} • ${item.distanceKm} km",
                        fontSize = 12.sp,
                        color = Color(0xFF64748B)
                    )
                }
                if (item.isBestDeal) {
                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = KisanEmerald,
                        contentColor = Color.White
                    ) {
                        Text(
                            text = if (currentLang == "hi") "★ सबसे ज्यादा मुनाफा" else "★ Highest Net Profit",
                            fontWeight = FontWeight.Bold,
                            fontSize = 11.sp,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(text = if (currentLang == "hi") "मंडी भाव" else "Mandi Rate", fontSize = 11.sp, color = Color(0xFF64748B))
                    Text(text = "₹${item.modalPrice.toInt()}/Q", fontWeight = FontWeight.SemiBold, fontSize = 13.sp)
                }
                Column {
                    Text(text = if (currentLang == "hi") "अनुमानित किराया" else "Freight Cost", fontSize = 11.sp, color = Color(0xFF64748B))
                    Text(text = "-₹${item.transportCost.toInt()}", fontWeight = FontWeight.SemiBold, fontSize = 13.sp, color = Color(0xFFDC2626))
                }
                Column(horizontalAlignment = Alignment.End) {
                    Text(text = if (currentLang == "hi") "हाथ में शुद्ध कमाई" else "Net Payout", fontSize = 11.sp, color = Color(0xFF64748B))
                    Text(text = "₹${item.netPayout.toInt()}", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = KisanEmerald)
                }
            }
        }
    }
}
