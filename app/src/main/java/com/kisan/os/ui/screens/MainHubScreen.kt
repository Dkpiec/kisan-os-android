package com.kisan.os.ui.screens

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kisan.os.models.DynamicTile
import com.kisan.os.ui.theme.KisanAmber
import com.kisan.os.ui.theme.KisanEmerald
import com.kisan.os.ui.theme.KisanLeafGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainHubScreen(
    tiles: List<DynamicTile>,
    currentLang: String,
    isDarkTheme: Boolean,
    onLanguageToggle: () -> Unit,
    onThemeToggle: () -> Unit,
    onTileClick: (String) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = KisanEmerald,
                            modifier = Modifier.size(38.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    imageVector = Icons.Default.Agriculture,
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(
                                text = if (currentLang == "hi") "किसान OS" else "Kisan OS",
                                fontWeight = FontWeight.Bold,
                                fontSize = 19.sp
                            )
                            Text(
                                text = if (currentLang == "hi") "स्मार्ट कृषि सहायक" else "Smart Farming Super-App",
                                fontSize = 11.sp,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                            )
                        }
                    }
                },
                actions = {
                    // Language Switcher Badge
                    OutlinedButton(
                        onClick = onLanguageToggle,
                        shape = RoundedCornerShape(20.dp),
                        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp),
                        modifier = Modifier.height(34.dp)
                    ) {
                        Text(
                            text = if (currentLang == "hi") "🇮🇳 हिन्दी" else "🇬🇧 ENG",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = KisanEmerald
                        )
                    }
                    Spacer(modifier = Modifier.width(6.dp))
                    // Theme Switcher Icon
                    IconButton(onClick = onThemeToggle) {
                        Icon(
                            imageVector = if (isDarkTheme) Icons.Default.LightMode else Icons.Default.DarkMode,
                            contentDescription = "Toggle Theme",
                            tint = if (isDarkTheme) KisanAmber else MaterialTheme.colorScheme.onSurface
                        )
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
            Spacer(modifier = Modifier.height(12.dp))

            // Weather & Spray Quick Pill Banner
            Card(
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onTileClick("weather_advisory") }
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        shape = CircleShape,
                        color = KisanEmerald.copy(alpha = 0.15f),
                        modifier = Modifier.size(46.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(
                                imageVector = Icons.Default.WbSunny,
                                contentDescription = null,
                                tint = KisanAmber,
                                modifier = Modifier.size(28.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(14.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = if (currentLang == "hi") "आज का मौसम व छिड़काव परामर्श" else "Today's Spray & Weather Advisory",
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                        Text(
                            text = if (currentLang == "hi") "अनुकूल हवा (8 km/h) • छिड़काव के लिए उत्तम समय" else "Favorable Wind (8 km/h) • Optimal Spray Window",
                            fontSize = 12.sp,
                            color = KisanLeafGreen,
                            fontWeight = FontWeight.Medium
                        )
                    }
                    Icon(
                        imageVector = Icons.Default.ChevronRight,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f)
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = if (currentLang == "hi") "मुख्य कृषि सेवाएं (Tiles)" else "Agricultural Services",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Dynamic Tile Grid
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(14.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(tiles) { tile ->
                    HubTileCard(
                        tile = tile,
                        currentLang = currentLang,
                        onClick = { onTileClick(tile.route) }
                    )
                }
            }
        }
    }
}

@Composable
fun HubTileCard(
    tile: DynamicTile,
    currentLang: String,
    onClick: () -> Unit
) {
    val accent = try {
        Color(android.graphics.Color.parseColor(tile.accentColor))
    } catch (e: Exception) {
        KisanEmerald
    }

    val iconVector: ImageVector = when (tile.icon) {
        "spa" -> Icons.Default.Spa
        "biotech" -> Icons.Default.Biotech
        "map" -> Icons.Default.Map
        "trending_up" -> Icons.Default.TrendingUp
        "cloud" -> Icons.Default.Cloud
        "home" -> Icons.Default.Home
        else -> Icons.Default.Eco
    }

    val displayTitle = if (currentLang == "hi") tile.titleHi else tile.titleEn

    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(145.dp)
            .clickable { onClick() }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Surface(
                    shape = RoundedCornerShape(14.dp),
                    color = accent.copy(alpha = 0.15f),
                    modifier = Modifier.size(44.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = iconVector,
                            contentDescription = null,
                            tint = accent,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }

                Column {
                    Text(
                        text = displayTitle,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurface,
                        maxLines = 2
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = tile.category.uppercase(),
                        fontSize = 10.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = accent
                    )
                }
            }
        }
    }
}
