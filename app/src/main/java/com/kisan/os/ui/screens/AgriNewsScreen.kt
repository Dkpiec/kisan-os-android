package com.kisan.os.ui.screens

import android.content.Intent
import android.net.Uri
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kisan.os.models.AgriNewsItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgriNewsScreen(
    newsArticles: List<AgriNewsItem>,
    currentLang: String,
    isLoading: Boolean = false,
    onRefresh: () -> Unit = {},
    onBack: () -> Unit
) {
    var selectedCategory by remember { mutableStateOf("all") }
    val context = LocalContext.current

    val categories = if (currentLang == "hi") {
        listOf(
            "all" to "सभी समाचार",
            "schemes" to "सरकारी योजनाएं",
            "market" to "मंडी व बाजार",
            "crops" to "फसल व अनुसंधान",
            "weather" to "मौसम व आपदा",
            "tech" to "कृषि तकनीक"
        )
    } else {
        listOf(
            "all" to "All News",
            "schemes" to "Govt Schemes",
            "market" to "Mandi & Prices",
            "crops" to "Crops & Research",
            "weather" to "Weather Alerts",
            "tech" to "Agri Tech"
        )
    }

    val filteredArticles = remember(newsArticles, selectedCategory) {
        if (selectedCategory == "all") {
            newsArticles
        } else {
            newsArticles.filter { it.category.equals(selectedCategory, ignoreCase = true) }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = if (currentLang == "hi") "कृषि समाचार व योजनाएं" else "Indian Agriculture News",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                        Text(
                            text = if (currentLang == "hi") "अपडेट: सुबह 5:00 से रात 8:00 IST प्रति घंटा" else "Hourly Update: 5 AM - 8 PM IST",
                            fontSize = 11.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = onRefresh) {
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = "Refresh News"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.background)
        ) {
            // Category Filter Chips
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(categories) { (key, label) ->
                    val isSelected = selectedCategory == key
                    FilterChip(
                        selected = isSelected,
                        onClick = { selectedCategory = key },
                        label = {
                            Text(
                                text = label,
                                fontSize = 13.sp,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                            )
                        },
                        leadingIcon = if (isSelected) {
                            {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = null,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        } else null,
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = Color(0xFF0D9488),
                            selectedLabelColor = Color.White,
                            selectedLeadingIconColor = Color.White
                        )
                    )
                }
            }

            // Info / Live Header Banner
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 4.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFF0FDF4)
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(10.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF16A34A))
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = if (currentLang == "hi")
                            "ताज़ा अपडेट: ICAR, PIB कृषि, व राष्ट्रीय समाचार स्रोतों से सत्यापित"
                        else
                            "Verified Live Feed from ICAR, PIB Agriculture & National Ag-Desks",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF15803D)
                    )
                }
            }

            if (isLoading && filteredArticles.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = Color(0xFF0D9488))
                }
            } else if (filteredArticles.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = null,
                            modifier = Modifier.size(48.dp),
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = if (currentLang == "hi") "इस श्रेणी में कोई समाचार उपलब्ध नहीं है" else "No news available in this category",
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(filteredArticles, key = { it.id }) { article ->
                        NewsCard(
                            article = article,
                            currentLang = currentLang,
                            onOpenUrl = { url ->
                                try {
                                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                                    context.startActivity(intent)
                                } catch (e: Exception) {
                                    // Ignore if no browser
                                }
                            },
                            onShare = { title, url ->
                                try {
                                    val shareIntent = Intent(Intent.ACTION_SEND).apply {
                                        type = "text/plain"
                                        putExtra(Intent.EXTRA_SUBJECT, title)
                                        putExtra(Intent.EXTRA_TEXT, "$title\n\n$url\n\nShared via Kisan Mitra (किसान मित्र)")
                                    }
                                    context.startActivity(Intent.createChooser(shareIntent, "Share News"))
                                } catch (e: Exception) {
                                    // Ignore
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun NewsCard(
    article: AgriNewsItem,
    currentLang: String,
    onOpenUrl: (String) -> Unit,
    onShare: (String, String) -> Unit
) {
    val displayTitle = if (currentLang == "hi") (article.titleHi ?: article.title) else (article.titleEn ?: article.title)
    val displaySummary = if (currentLang == "hi") (article.summaryHi ?: article.summary) else (article.summaryEn ?: article.summary)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                article.sourceUrl?.let { onOpenUrl(it) }
            },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(14.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Source and category row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Source badge
                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = Color(0xFFF1F5F9)
                ) {
                    Text(
                        text = article.sourceName,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF334155)
                    )
                }

                // Category pill
                val (catColor, catBg) = when (article.category.lowercase()) {
                    "schemes" -> Color(0xFF0D9488) to Color(0xFFCCFBF1)
                    "market" -> Color(0xFFD97706) to Color(0xFFFEF3C7)
                    "weather" -> Color(0xFF2563EB) to Color(0xFFDBEAFE)
                    "crops" -> Color(0xFF16A34A) to Color(0xFFDCFCE7)
                    "tech" -> Color(0xFF7C3AED) to Color(0xFFEDE9FE)
                    else -> Color(0xFF475569) to Color(0xFFF1F5F9)
                }

                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = catBg
                ) {
                    Text(
                        text = article.category.uppercase(),
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                        fontSize = 10.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = catColor
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Headline Title
            Text(
                text = displayTitle,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                lineHeight = 20.sp
            )

            if (displaySummary.isNotBlank() && displaySummary != displayTitle) {
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = displaySummary,
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    lineHeight = 18.sp
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Footer row (Date + Actions)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = article.publishedAt.take(16),
                    fontSize = 11.sp,
                    color = MaterialTheme.colorScheme.outline
                )

                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(
                        onClick = {
                            onShare(displayTitle, article.sourceUrl ?: "")
                        },
                        modifier = Modifier.size(32.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Share,
                            contentDescription = "Share",
                            modifier = Modifier.size(16.dp),
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    Spacer(modifier = Modifier.width(4.dp))

                    TextButton(
                        onClick = {
                            article.sourceUrl?.let { onOpenUrl(it) }
                        },
                        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = if (currentLang == "hi") "पूरी खबर पढ़ें →" else "Read More →",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF0D9488)
                        )
                    }
                }
            }
        }
    }
}
