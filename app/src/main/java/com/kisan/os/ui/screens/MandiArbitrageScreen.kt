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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kisan.os.ui.theme.KisanEmerald

data class MandiRecord(
    val state: String,
    val district: String,
    val mandiName: String,
    val commodity: String,
    val modalPrice: Double,
    val minPrice: Double,
    val maxPrice: Double,
    val arrivalQ: Double,
    val distanceKm: Double,
    val netPayout: Double
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MandiArbitrageScreen(
    currentLang: String,
    onBack: () -> Unit
) {
    val isHi = currentLang == "hi"

    var selectedState by remember { mutableStateOf("All") }
    var selectedDistrict by remember { mutableStateOf("All") }
    var produceQuintals by remember { mutableStateOf("40") }

    val masterMandis = remember {
        listOf(
            // Delhi Mandis
            MandiRecord("Delhi", "North Delhi", "Azadpur Mandi (आजादपुर मंडी)", "Tomato (टमाटर)", 2850.0, 2400.0, 3200.0, 1450.0, 45.0, 2690.0),
            MandiRecord("Delhi", "North Delhi", "Azadpur Mandi (आजादपुर मंडी)", "Chilli (हरी मिर्च)", 5800.0, 5200.0, 6400.0, 620.0, 45.0, 5640.0),
            MandiRecord("Delhi", "East Delhi", "Ghazipur Mandi (गाजीपुर मंडी)", "Tomato (टमाटर)", 2720.0, 2300.0, 3050.0, 920.0, 35.0, 2580.0),
            MandiRecord("Delhi", "North Delhi", "Narela Mandi (नरेला अनाज मंडी)", "Wheat (गेहूँ)", 2650.0, 2450.0, 2800.0, 2800.0, 40.0, 2510.0),
            MandiRecord("Delhi", "North Delhi", "Narela Mandi (नरेला अनाज मंडी)", "Basmati Rice (धान)", 4250.0, 3900.0, 4500.0, 1850.0, 40.0, 4110.0),
            MandiRecord("Delhi", "West Delhi", "Keshopur Mandi (केशोपुर मंडी)", "Onion (प्याज)", 3400.0, 2900.0, 3700.0, 1100.0, 28.0, 3290.0),

            // Haryana Mandis
            MandiRecord("Haryana", "Sonipat", "Sonipat Mandi (सोनीपत अनाज मंडी)", "Wheat (गेहूँ)", 2580.0, 2400.0, 2700.0, 1800.0, 55.0, 2430.0),
            MandiRecord("Haryana", "Sonipat", "Ganaur Mandi (गन्नौर फल मंडी)", "Tomato (टमाटर)", 2500.0, 2100.0, 2850.0, 780.0, 65.0, 2320.0),
            MandiRecord("Haryana", "Karnal", "Karnal Mandi (करनाल मंडी)", "Basmati Rice (धान)", 4380.0, 4000.0, 4650.0, 3200.0, 125.0, 4090.0),
            MandiRecord("Haryana", "Karnal", "Taraori Mandi (तरावड़ी बासमती मंडी)", "Basmati Rice (धान)", 4450.0, 4100.0, 4750.0, 2400.0, 138.0, 4140.0),
            MandiRecord("Haryana", "Panipat", "Panipat Mandi (पानीपत मंडी)", "Mustard (सरसों)", 5650.0, 5200.0, 5900.0, 1200.0, 90.0, 5420.0),
            MandiRecord("Haryana", "Gurugram", "Gurugram Mandi (गुरुग्राम मंडी)", "Vegetables (सब्जियां)", 3100.0, 2600.0, 3500.0, 650.0, 32.0, 2980.0),
            MandiRecord("Haryana", "Faridabad", "Ballabhgarh Mandi (बल्लभगढ़ मंडी)", "Wheat (गेहूँ)", 2520.0, 2350.0, 2650.0, 950.0, 42.0, 2390.0),

            // Uttar Pradesh (UP) Mandis
            MandiRecord("Uttar Pradesh", "Gautam Buddha Nagar", "Noida Phase-2 Mandi (नोएडा मंडी)", "Vegetables (सब्जियां)", 3200.0, 2700.0, 3600.0, 890.0, 18.0, 3130.0),
            MandiRecord("Uttar Pradesh", "Ghaziabad", "Sahibabad Mandi (साहिबाबाद सब्जी मंडी)", "Tomato (टमाटर)", 2780.0, 2350.0, 3150.0, 1300.0, 22.0, 2690.0),
            MandiRecord("Uttar Pradesh", "Ghaziabad", "Ghaziabad Grain Mandi (गाजियाबाद अनाज मंडी)", "Wheat (गेहूँ)", 2540.0, 2380.0, 2680.0, 1600.0, 25.0, 2445.0),
            MandiRecord("Uttar Pradesh", "Meerut", "Meerut APMC (मेरठ नवीन मंडी)", "Basmati Rice (धान)", 4150.0, 3800.0, 4400.0, 2100.0, 72.0, 3960.0),
            MandiRecord("Uttar Pradesh", "Meerut", "Meerut APMC (मेरठ गुड़ मंडी)", "Jaggery / Gur (गुड़)", 3900.0, 3600.0, 4200.0, 1400.0, 72.0, 3720.0),
            MandiRecord("Uttar Pradesh", "Bulandshahr", "Bulandshahr Mandi (बुलंदशहर मंडी)", "Mustard (सरसों)", 5550.0, 5100.0, 5800.0, 950.0, 78.0, 5340.0),
            MandiRecord("Uttar Pradesh", "Hapur", "Hapur Mandi (हापुड़ कृषि मंडी)", "Wheat (गेहूँ)", 2590.0, 2420.0, 2720.0, 1750.0, 60.0, 2440.0),
            MandiRecord("Uttar Pradesh", "Aligarh", "Aligarh Mandi (अलीगढ़ अनाज मंडी)", "Pigeon Pea / Arhar (तुअर दाल)", 7400.0, 6800.0, 7900.0, 600.0, 130.0, 7060.0),
            MandiRecord("Uttar Pradesh", "Agra", "Agra Mandi (आगरा आलू मंडी)", "Potato (आलू)", 1650.0, 1400.0, 1850.0, 4200.0, 180.0, 1320.0)
        )
    }

    val states = listOf("All", "Delhi", "Haryana", "Uttar Pradesh")

    val districtsForState = remember(selectedState) {
        if (selectedState == "All") {
            listOf("All") + masterMandis.map { it.district }.distinct()
        } else {
            listOf("All") + masterMandis.filter { it.state == selectedState }.map { it.district }.distinct()
        }
    }

    val filteredMandis = masterMandis.filter {
        (selectedState == "All" || it.state == selectedState) &&
        (selectedDistrict == "All" || it.district == selectedDistrict)
    }.sortedByDescending { it.netPayout }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            if (isHi) "मंडी भाव एवं आर्बिट्राज (Arbitrage)" else "Mandi Price & Spatial Arbitrage",
                            fontWeight = FontWeight.Bold,
                            fontSize = 17.sp
                        )
                        Text(
                            if (isHi) "दिल्ली, हरियाणा व उत्तर प्रदेश (UP) 150km तुलना" else "Delhi, Haryana & UP 150km APMC Network",
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
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp, vertical = 6.dp)
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                )
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text(
                        if (isHi) "📍 राज्य चुनें (Filter by State):" else "📍 Filter by State:",
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        items(states) { st ->
                            FilterChip(
                                selected = selectedState == st,
                                onClick = {
                                    selectedState = st
                                    selectedDistrict = "All"
                                },
                                label = { Text(if (st == "Uttar Pradesh") "UP (उत्तर प्रदेश)" else st, fontSize = 12.sp) }
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        if (isHi) "🏙️ जिला चुनें (District):" else "🏙️ Filter by District:",
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        items(districtsForState) { dst ->
                            FilterChip(
                                selected = selectedDistrict == dst,
                                onClick = { selectedDistrict = dst },
                                label = { Text(dst, fontSize = 11.sp) }
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (isHi) "सक्रिय मंडियां (${filteredMandis.size})" else "Active Mandis (${filteredMandis.size})",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = if (isHi) "शुद्ध कमाई क्रम" else "Ranked by Net Profit",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(filteredMandis) { item ->
                    val isTopDeal = filteredMandis.indexOf(item) == 0

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(14.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = if (isTopDeal) Color(0xFFF0FDF4) else MaterialTheme.colorScheme.surface
                        ),
                        elevation = CardDefaults.cardElevation(if (isTopDeal) 3.dp else 1.dp)
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = item.mandiName,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 15.sp
                                    )
                                    Text(
                                        text = "${item.district}, ${item.state} • ${item.distanceKm.toInt()} km दूर",
                                        fontSize = 12.sp,
                                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                                    )
                                }
                                if (isTopDeal) {
                                    Surface(
                                        shape = RoundedCornerShape(6.dp),
                                        color = KisanEmerald
                                    ) {
                                        Text(
                                            if (isHi) "सर्वोत्तम मुनाफा" else "Best Deal",
                                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                                            color = Color.White,
                                            fontSize = 11.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(8.dp))
                            Divider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.08f))
                            Spacer(modifier = Modifier.height(8.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Column {
                                    Text(
                                        item.commodity,
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                    Text(
                                        "${if (isHi) "मॉडल भाव: " else "Modal: "}₹${item.modalPrice.toInt()}/Q",
                                        fontSize = 12.sp,
                                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
                                    )
                                }
                                Column(horizontalAlignment = Alignment.End) {
                                    Text(
                                        if (isHi) "हाथ में शुद्ध भाव (Net)" else "Net Realization",
                                        fontSize = 11.sp,
                                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                                    )
                                    Text(
                                        "₹${item.netPayout.toInt()}/Q",
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold,
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
