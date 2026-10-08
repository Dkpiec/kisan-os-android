package com.kisan.os.ui.screens

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
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
import com.kisan.os.models.SeedVariety
import com.kisan.os.ui.theme.KisanAmber
import com.kisan.os.ui.theme.KisanEmerald
import com.kisan.os.ui.theme.KisanLeafGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SeedCatalogScreen(
    seeds: List<SeedVariety>,
    currentLang: String,
    onBack: () -> Unit
) {
    var selectedCategory by remember { mutableStateOf<String?>(null) }
    var searchQuery by remember { mutableStateOf("") }

    val categories = listOf(
        "all" to if (currentLang == "hi") "सभी किस्में" else "All Seeds",
        "vegetable" to if (currentLang == "hi") "सब्जियां (Vegetables)" else "Vegetables",
        "grain" to if (currentLang == "hi") "अनाज (Grains)" else "Grains",
        "pulse" to if (currentLang == "hi") "दालें (Pulses)" else "Pulses",
        "fruit" to if (currentLang == "hi") "फल (Fruits)" else "Fruits",
        "oilseed" to if (currentLang == "hi") "तिलहन (Mustard)" else "Oilseeds",
        "millet" to if (currentLang == "hi") "श्री अन्न (Millets)" else "Millets"
    )

    val filteredSeeds = seeds.filter { s ->
        val matchesCat = selectedCategory == null || selectedCategory == "all" || s.category.equals(selectedCategory, ignoreCase = true)
        val matchesQuery = searchQuery.isBlank() || 
                           s.cropName.contains(searchQuery, ignoreCase = true) || 
                           s.varietyName.contains(searchQuery, ignoreCase = true) ||
                           s.breederOrCompany.contains(searchQuery, ignoreCase = true)
        matchesCat && matchesQuery
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = if (currentLang == "hi") "उन्नत बीज एवं स्वाद किस्में" else "Certified Seed Varieties",
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

            // Search Box
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                label = { Text(if (currentLang == "hi") "बीज या फसल खोजें..." else "Search crop or variety...") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                singleLine = true,
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = KisanEmerald,
                    unfocusedBorderColor = Color(0xFFE2E8F0),
                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surface
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Category Filter Pills
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(categories) { (key, label) ->
                    val isSelected = (key == "all" && selectedCategory == null) || selectedCategory == key
                    FilterChip(
                        selected = isSelected,
                        onClick = { selectedCategory = if (key == "all") null else key },
                        label = { Text(label, fontSize = 12.sp, fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = KisanEmerald,
                            selectedLabelColor = Color.White
                        ),
                        shape = RoundedCornerShape(20.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Seed Cards List
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(filteredSeeds) { seed ->
                    SeedCard(seed = seed, currentLang = currentLang)
                }
            }
        }
    }
}

@Composable
fun SeedCard(
    seed: SeedVariety,
    currentLang: String
) {
    Card(
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "${seed.cropName} - ${seed.varietyName}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = seed.breederOrCompany,
                        fontSize = 12.sp,
                        color = KisanEmerald,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = KisanAmber.copy(alpha = 0.15f),
                    contentColor = KisanAmber
                ) {
                    Text(
                        text = "★ ${seed.rating}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }

            // Culinary Profile Banner
            if (!seed.tasteProfile.isNullOrBlank()) {
                Spacer(modifier = Modifier.height(10.dp))
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = Color(0xFFF1F5F9),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Restaurant,
                            contentDescription = null,
                            tint = Color(0xFF475569),
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = seed.tasteProfile,
                            fontSize = 12.sp,
                            color = Color(0xFF334155),
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Agronomic Stats Grid
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = if (currentLang == "hi") "बुवाई समय" else "Sowing Window",
                        fontSize = 11.sp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )
                    Text(
                        text = seed.sowingWindow,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Column {
                    Text(
                        text = if (currentLang == "hi") "अवधि" else "Duration",
                        fontSize = 11.sp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )
                    Text(
                        text = "${seed.maturityDays} ${if (currentLang == "hi") "दिन" else "days"}",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Column {
                    Text(
                        text = if (currentLang == "hi") "अनुमानित उपज" else "Est. Yield",
                        fontSize = 11.sp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )
                    Text(
                        text = seed.expectedYield,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = KisanLeafGreen
                    )
                }
            }

            if (!seed.diseaseResistances.isNullOrBlank()) {
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "${if (currentLang == "hi") "रोग प्रतिरोधक क्षमता: " else "Resistance: "}${seed.diseaseResistances}",
                    fontSize = 11.sp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                )
            }
        }
    }
}
