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
import com.kisan.os.models.OrganicRecipe
import com.kisan.os.ui.theme.KisanAmber
import com.kisan.os.ui.theme.KisanEmerald
import com.kisan.os.ui.theme.KisanLeafGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrganicHubScreen(
    recipes: List<OrganicRecipe>,
    currentLang: String,
    onBack: () -> Unit
) {
    var selectedCat by remember { mutableStateOf<String?>(null) }
    var expandedRecipeId by remember { mutableStateOf<String?>(null) }

    val categories = listOf(
        "all" to if (currentLang == "hi") "सभी नुस्खे" else "All Inputs",
        "jadam" to if (currentLang == "hi") "जादम (JADAM)" else "JADAM Organic",
        "zbnf" to if (currentLang == "hi") "प्राकृतिक खेती (ZBNF)" else "ZBNF / Vedic",
        "compost" to if (currentLang == "hi") "कम्पोस्ट (Composting)" else "Rapid Compost"
    )

    val filteredRecipes = recipes.filter { r ->
        selectedCat == null || selectedCat == "all" || r.category.equals(selectedCat, ignoreCase = true)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = if (currentLang == "hi") "जैविक व जादम इनपुट ज्ञान केंद्र" else "Organic & JADAM Recipes",
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

            // Category Tabs
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(categories) { (key, label) ->
                    val isSelected = (key == "all" && selectedCat == null) || selectedCat == key
                    FilterChip(
                        selected = isSelected,
                        onClick = { selectedCat = if (key == "all") null else key },
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

            // Recipes List
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(14.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(filteredRecipes) { recipe ->
                    OrganicRecipeCard(
                        recipe = recipe,
                        currentLang = currentLang,
                        isExpanded = expandedRecipeId == recipe.id,
                        onToggleExpand = {
                            expandedRecipeId = if (expandedRecipeId == recipe.id) null else recipe.id
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun OrganicRecipeCard(
    recipe: OrganicRecipe,
    currentLang: String,
    isExpanded: Boolean,
    onToggleExpand: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onToggleExpand() }
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
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = recipe.name,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = recipe.purpose,
                        fontSize = 12.sp,
                        color = KisanEmerald,
                        fontWeight = FontWeight.Medium
                    )
                }
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = KisanEmerald.copy(alpha = 0.15f),
                    contentColor = KisanEmerald
                ) {
                    Text(
                        text = "${recipe.preparationDays} ${if (currentLang == "hi") "दिन" else "days"}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Dosages Badges
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = Color(0xFFF1F5F9)
                ) {
                    Text(
                        text = "🌱 ${if (currentLang == "hi") "प्रति एकड़: " else "Per Acre: "}${recipe.dosagePerAcre}",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF334155),
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = Color(0xFFF1F5F9)
                ) {
                    Text(
                        text = "💧 ${if (currentLang == "hi") "प्रति पम्प: " else "Per 15L: "}${recipe.dosagePerPump}",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF334155),
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }

            // Expanded Recipe Details & Step-by-Step Instructions
            AnimatedVisibility(visible = isExpanded) {
                Column(modifier = Modifier.padding(top = 14.dp)) {
                    Divider(color = Color(0xFFE2E8F0))
                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = if (currentLang == "hi") "आवश्यक सामग्री (Ingredients):" else "Required Ingredients:",
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    recipe.ingredients.forEach { item ->
                        val ingName = item["item"] ?: ""
                        val ingQty = item["qty"] ?: ""
                        Text(
                            text = "• $ingName: $ingQty",
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = if (currentLang == "hi") "बनाने की विधि (Preparation Steps):" else "Step-by-Step Method:",
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = recipe.stepByStep,
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.85f),
                        lineHeight = 18.sp
                    )

                    if (!recipe.precautions.isNullOrBlank()) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "⚠️ ${if (currentLang == "hi") "सावधानियां: " else "Precautions: "}${recipe.precautions}",
                            fontSize = 11.sp,
                            color = KisanAmber,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }
    }
}
