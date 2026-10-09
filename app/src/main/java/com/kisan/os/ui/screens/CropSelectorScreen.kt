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
import com.kisan.os.api.KisanApiService
import com.kisan.os.models.*
import com.kisan.os.ui.theme.KisanAmber
import com.kisan.os.ui.theme.KisanEmerald
import com.kisan.os.ui.theme.KisanLeafGreen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

data class SeasonItem(val id: String, val nameHi: String, val nameEn: String)
data class CategoryItem(val id: String, val nameHi: String, val nameEn: String)
data class CropListItem(val id: String, val nameHi: String, val nameEn: String, val season: String, val category: String)

val ALL_SEASONS = listOf(
    SeasonItem("rabi", "रबी (सर्दियों की फसल)", "Rabi (Winter)"),
    SeasonItem("kharif", "खरीफ (मानसून की फसल)", "Kharif (Monsoon)"),
    SeasonItem("zaid", "जायद (ग्रीष्मकालीन फसल)", "Zaid (Summer)"),
    SeasonItem("perennial", "फल एवं बागवानी (बारहमासी)", "Fruit Orchards (Perennial)")
)

val ALL_CATEGORIES = listOf(
    CategoryItem("all", "सभी फसल वर्ग (All)", "All Categories"),
    CategoryItem("vegetables", "सब्जियां (Vegetables)", "Vegetables"),
    CategoryItem("grains", "अनाज व मोटे अनाज (Grains)", "Grains & Millets"),
    CategoryItem("pulses", "दालें (Pulses)", "Pulses"),
    CategoryItem("oilseeds", "तिलहन (Oilseeds)", "Oilseeds"),
    CategoryItem("fruits", "फल एवं बागवानी (Fruits)", "Fruit Orchards")
)

val ALL_CROPS_LIST = listOf(
    // Rabi
    CropListItem("wheat", "गेहूं (Wheat)", "Wheat", "rabi", "grains"),
    CropListItem("mustard", "सरसों / राई (Mustard)", "Mustard", "rabi", "oilseeds"),
    CropListItem("chickpea", "चना / काबुली (Chickpea)", "Chickpea", "rabi", "pulses"),
    CropListItem("potato", "आलू (Potato)", "Potato", "rabi", "vegetables"),
    CropListItem("tomato_rabi", "टमाटर (Tomato)", "Tomato", "rabi", "vegetables"),
    CropListItem("chilli_rabi", "हरी मिर्च (Chilli)", "Chilli", "rabi", "vegetables"),
    CropListItem("onion_rabi", "रबी प्याज (Onion)", "Onion", "rabi", "vegetables"),
    CropListItem("garlic", "लहसुन (Garlic)", "Garlic", "rabi", "vegetables"),
    CropListItem("peas", "हरी मटर (Green Peas)", "Green Peas", "rabi", "vegetables"),
    CropListItem("cauliflower", "फूलगोभी (Cauliflower)", "Cauliflower", "rabi", "vegetables"),
    CropListItem("lentil", "मसूर (Lentil)", "Lentil", "rabi", "pulses"),

    // Kharif
    CropListItem("paddy_basmati", "बासमती धान (Basmati Rice)", "Basmati Rice", "kharif", "grains"),
    CropListItem("paddy_hybrid", "हाइब्रिड धान (Paddy)", "Hybrid Paddy", "kharif", "grains"),
    CropListItem("maize", "मक्का (Maize)", "Maize", "kharif", "grains"),
    CropListItem("soybean", "सोयाबीन (Soybean)", "Soybean", "kharif", "oilseeds"),
    CropListItem("cotton", "कपास (Cotton)", "Cotton", "kharif", "grains"),
    CropListItem("pigeon_pea", "अरहर / तुअर (Pigeon Pea)", "Pigeon Pea", "kharif", "pulses"),
    CropListItem("bajra", "बाजरा (Pearl Millet)", "Pearl Millet", "kharif", "grains"),
    CropListItem("okra_kharif", "भिंडी (Okra)", "Okra", "kharif", "vegetables"),
    CropListItem("brinjal", "बैंगन (Brinjal)", "Brinjal", "kharif", "vegetables"),
    CropListItem("groundnut", "मूंगफली (Groundnut)", "Groundnut", "kharif", "oilseeds"),

    // Zaid
    CropListItem("watermelon", "तरबूज (Watermelon)", "Watermelon", "zaid", "fruits"),
    CropListItem("muskmelon", "खरबूजा (Muskmelon)", "Muskmelon", "zaid", "fruits"),
    CropListItem("cucumber", "खीरा (Cucumber)", "Cucumber", "zaid", "vegetables"),
    CropListItem("bottle_gourd", "लौकी (Bottle Gourd)", "Bottle Gourd", "zaid", "vegetables"),
    CropListItem("bitter_gourd", "करेला (Bitter Gourd)", "Bitter Gourd", "zaid", "vegetables"),
    CropListItem("moong_zaid", "जायद मूंग (Summer Moong)", "Summer Moong", "zaid", "pulses"),

    // Fruit Orchards
    CropListItem("mango", "आम का बाग (Mango Orchard)", "Mango Orchard", "perennial", "fruits"),
    CropListItem("papaya", "रेड लेडी पपीता (Papaya)", "Papaya", "perennial", "fruits"),
    CropListItem("guava", "अमरूद बाग (Guava Orchard)", "Guava", "perennial", "fruits"),
    CropListItem("pomegranate", "अनार (Pomegranate)", "Pomegranate", "perennial", "fruits"),
    CropListItem("banana", "केला (Banana)", "Banana", "perennial", "fruits")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CropSelectorScreen(
    currentLang: String,
    apiService: KisanApiService,
    onBack: () -> Unit
) {
    val isHi = currentLang == "hi"

    var selectedSeason by remember { mutableStateOf(ALL_SEASONS.first()) }
    var selectedCategory by remember { mutableStateOf(ALL_CATEGORIES.first()) }
    var showSeasonSheet by remember { mutableStateOf(false) }
    var showCategorySheet by remember { mutableStateOf(false) }
    var showCropSheet by remember { mutableStateOf(false) }

    // Filter available crops
    val filteredCrops = remember(selectedSeason, selectedCategory) {
        ALL_CROPS_LIST.filter { crop ->
            (crop.season == selectedSeason.id || selectedSeason.id == "all") &&
            (selectedCategory.id == "all" || crop.category == selectedCategory.id)
        }
    }

    var selectedCropItem by remember { mutableStateOf(filteredCrops.firstOrNull() ?: ALL_CROPS_LIST.first()) }

    // When season changes, reset selected crop if not in filtered list
    LaunchedEffect(selectedSeason, selectedCategory) {
        if (filteredCrops.none { it.id == selectedCropItem.id }) {
            selectedCropItem = filteredCrops.firstOrNull() ?: ALL_CROPS_LIST.first()
        }
    }

    // Active Tab in POP
    var activePopTab by remember { mutableStateOf("stages") } // "stages", "fertigation", "pests", "irrigation", "economics"

    // Detailed Crop Agronomy State
    var cropDetail by remember { mutableStateOf<CropAgronomyDetail?>(null) }
    var isLoadingDetail by remember { mutableStateOf(false) }

    // Fetch POP from Backend on crop selection
    LaunchedEffect(selectedCropItem.id) {
        isLoadingDetail = true
        try {
            val res = withContext(Dispatchers.IO) {
                apiService.getCropPOP(selectedCropItem.id)
            }
            cropDetail = res
        } catch (e: Exception) {
            // Fallback mock detail if offline
            cropDetail = null
        } finally {
            isLoadingDetail = false
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = if (isHi) "स्मार्ट फसल चयन व सम्पूर्ण कृषि कार्यमाला" else "Smart Crop Selector & Complete POP",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                        Text(
                            text = if (isHi) "मौसम, खाद-उर्वरक, कीट प्रबंधन व मुनाफा" else "Lifecycle, Fertigation, Pest Control & Yield",
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
        containerColor = MaterialTheme.colorScheme.background
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // Dropdown Controls Card (Season, Category, Crop)
            item {
                Spacer(modifier = Modifier.height(4.dp))
                Card(
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Text(
                            text = if (isHi) "सीजन व फसल का चयन करें:" else "Filter Season & Select Crop:",
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(modifier = Modifier.height(10.dp))

                        // Row 1: Season & Category Selectors
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            // Season Dropdown Box
                            Surface(
                                shape = RoundedCornerShape(10.dp),
                                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                                modifier = Modifier
                                    .weight(1f)
                                    .clickable { showSeasonSheet = true }
                            ) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column {
                                        Text(text = if (isHi) "सीजन (Season)" else "Season", fontSize = 9.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                                        Text(
                                            text = if (isHi) selectedSeason.nameHi.split(" ")[0] else selectedSeason.nameEn.split(" ")[0],
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = KisanEmerald
                                        )
                                    }
                                    Icon(Icons.Default.ArrowDropDown, contentDescription = null, tint = KisanEmerald)
                                }
                            }

                            // Category Dropdown Box
                            Surface(
                                shape = RoundedCornerShape(10.dp),
                                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                                modifier = Modifier
                                    .weight(1f)
                                    .clickable { showCategorySheet = true }
                            ) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column {
                                        Text(text = if (isHi) "वर्ग (Category)" else "Category", fontSize = 9.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                                        Text(
                                            text = if (isHi) selectedCategory.nameHi.split(" ")[0] else selectedCategory.nameEn.split(" ")[0],
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = MaterialTheme.colorScheme.onSurface
                                        )
                                    }
                                    Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        // Row 2: Crop Dropdown Button
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = KisanEmerald.copy(alpha = 0.12f),
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { showCropSheet = true }
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 14.dp, vertical = 12.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(Icons.Default.Eco, contentDescription = null, tint = KisanEmerald, modifier = Modifier.size(20.dp))
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Column {
                                        Text(text = if (isHi) "चयनित फसल (Selected Crop)" else "Selected Crop", fontSize = 10.sp, color = KisanEmerald)
                                        Text(
                                            text = if (isHi) selectedCropItem.nameHi else selectedCropItem.nameEn,
                                            fontWeight = FontWeight.ExtraBold,
                                            fontSize = 16.sp,
                                            color = MaterialTheme.colorScheme.onSurface
                                        )
                                    }
                                }
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(
                                        text = if (isHi) "बदलें ▾" else "Change ▾",
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = KisanEmerald
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // Crop Header Banner Card (Duration, Spacing, Sowing Window)
            item {
                Card(
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(containerColor = KisanEmerald),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = if (isHi) selectedCropItem.nameHi else selectedCropItem.nameEn,
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp
                            )
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = Color.White.copy(alpha = 0.25f)
                            ) {
                                Text(
                                    text = "${cropDetail?.durationDays ?: 120} ${if (isHi) "दिन" else "Days"}",
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 12.sp,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column {
                                Text(if (isHi) "बुवाई समय (Window)" else "Sowing Time", fontSize = 10.sp, color = Color.White.copy(alpha = 0.8f))
                                Text(
                                    cropDetail?.sowingDetails?.get("sowing_window_${if (isHi) "hi" else "en"}") ?: (if (isHi) "अक्टूबर - नवंबर" else "Oct - Nov"),
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                            }
                            Column {
                                Text(if (isHi) "बीज दर (Seed Rate)" else "Seed Rate", fontSize = 10.sp, color = Color.White.copy(alpha = 0.8f))
                                Text(
                                    cropDetail?.sowingDetails?.get("seed_rate_${if (isHi) "hi" else "en"}") ?: "40-45 kg / एकड़",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                            }
                            Column {
                                Text(if (isHi) "दूरी (Spacing)" else "Spacing", fontSize = 10.sp, color = Color.White.copy(alpha = 0.8f))
                                Text(
                                    cropDetail?.sowingDetails?.get("spacing") ?: "20 x 5 cm",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                            }
                        }
                    }
                }
            }

            // POP Navigation Segmented Tabs
            item {
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    item {
                        FilterChip(
                            selected = activePopTab == "stages",
                            onClick = { activePopTab = "stages" },
                            label = { Text(if (isHi) "📅 जीवन चक्र (Stages)" else "Lifecycle", fontSize = 12.sp) }
                        )
                    }
                    item {
                        FilterChip(
                            selected = activePopTab == "fertigation",
                            onClick = { activePopTab = "fertigation" },
                            label = { Text(if (isHi) "🧪 खाद व उर्वरक (Fertigation)" else "Fertigation", fontSize = 12.sp) }
                        )
                    }
                    item {
                        FilterChip(
                            selected = activePopTab == "pests",
                            onClick = { activePopTab = "pests" },
                            label = { Text(if (isHi) "🐛 कीट व रोग नियंत्रण" else "Pests & IPM", fontSize = 12.sp) }
                        )
                    }
                    item {
                        FilterChip(
                            selected = activePopTab == "irrigation",
                            onClick = { activePopTab = "irrigation" },
                            label = { Text(if (isHi) "💧 सिंचाई प्रबंधन" else "Irrigation", fontSize = 12.sp) }
                        )
                    }
                    item {
                        FilterChip(
                            selected = activePopTab == "economics",
                            onClick = { activePopTab = "economics" },
                            label = { Text(if (isHi) "💰 लागत व मुनाफा" else "Economics & Yield", fontSize = 12.sp) }
                        )
                    }
                }
            }

            // Tab 1: Lifecycle Stages Schedule
            if (activePopTab == "stages") {
                val stages = cropDetail?.growthStages ?: listOf(
                    CropStageInfo(1, "0-7 DAS", "बुवाई व अंकुरण (Germination)", "Seedling", "बीज उपचार ट्राइकोडर्मा व पलेवा नमी में बुवाई।", "Sow in moisture."),
                    CropStageInfo(2, "20-25 DAS", "सीआरआई / कल्ले निकलना (Tillering)", "Tillering", "पहली सिंचाई (CRI) व यूरिया 25kg/एकड़ टॉप ड्रेसिंग।", "First irrigation and Urea top dressing."),
                    CropStageInfo(3, "45-50 DAS", "गाभा अवस्था (Jointing)", "Jointing", "दूसरी सिंचाई व 19:19:19 + सूक्ष्म पोषक 2g/L स्प्रे।", "NPK spray."),
                    CropStageInfo(4, "65-75 DAS", "बालियां निकलना (Booting / Heading)", "Heading", "तीसरी सिंचाई, 0:52:34 व बोरॉन 1g/L स्प्रे।", "Boron spray."),
                    CropStageInfo(5, "85-95 DAS", "दाना भराव (Milking / Grain Filling)", "Grain Filling", "चौथी सिंचाई व 0:0:50 स्प्रे दाने में चमक हेतु।", "Potash spray."),
                    CropStageInfo(6, "115-125 DAS", "परिपक्वता व कटाई (Harvest)", "Maturity", "पत्तियां पीली पड़ने पर कटाई व गहाई।", "Harvest.")
                )

                items(stages) { stage ->
                    Card(
                        shape = RoundedCornerShape(14.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(14.dp),
                            verticalAlignment = Alignment.Top
                        ) {
                            Surface(
                                shape = CircleShape,
                                color = KisanEmerald.copy(alpha = 0.15f),
                                modifier = Modifier.size(36.dp)
                            ) {
                                Box(contentAlignment = Alignment.Center) {
                                    Text(
                                        text = "${stage.stageNumber}",
                                        fontWeight = FontWeight.ExtraBold,
                                        color = KisanEmerald,
                                        fontSize = 14.sp
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.width(12.dp))

                            Column(modifier = Modifier.weight(1f)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = if (isHi) stage.stageNameHi else stage.stageNameEn,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 14.sp,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                    Surface(
                                        shape = RoundedCornerShape(6.dp),
                                        color = MaterialTheme.colorScheme.surfaceVariant
                                    ) {
                                        Text(
                                            text = stage.daysRange,
                                            fontSize = 11.sp,
                                            fontWeight = FontWeight.SemiBold,
                                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = if (isHi) stage.operationsHi else stage.operationsEn,
                                    fontSize = 12.sp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    lineHeight = 16.sp
                                )
                            }
                        }
                    }
                }
            }

            // Tab 2: Fertigation & Nutrient Schedule
            if (activePopTab == "fertigation") {
                val fertItems = cropDetail?.fertigationSchedule ?: listOf(
                    FertigationItem("बुवाई पूर्व (Basal Dose)", "Basal Application", "DAP 50kg + MOP 20kg + SSP 50kg + Zinc 33% 5kg प्रति एकड़", "Basal NPK dose", "घनजीवामृत 100kg प्रति एकड़"),
                    FertigationItem("प्रथम सिंचाई (21 दिन)", "1st Top Dressing (21 DAS)", "यूरिया 30kg + सल्फर 90% 3kg प्रति एकड़", "Urea top dress", "जीवामृत 200 लीटर पानी के साथ"),
                    FertigationItem("कल्ले निकलने पर (45 दिन)", "Tillering Stage (45 DAS)", "19:19:19 घुलनशील 1kg + सूक्ष्म पोषक 250g/150L पानी", "Foliar NPK", "खट्टी छाछ 3 लीटर + जीवामृत स्प्रे"),
                    FertigationItem("फूल व बाली (70 दिन)", "Booting Stage (70 DAS)", "0:52:34 1kg + बोरॉन 20% 150g प्रति एकड़ स्प्रे", "Boron booster", "दशपर्णी अर्क 5 लीटर स्प्रे"),
                    FertigationItem("दाना भराव (90 दिन)", "Grain Filling (90 DAS)", "0:0:50 (पोटाश) 1kg प्रति एकड़ स्प्रे", "Potash foliar", "जीवामृत 10% फोलियर स्प्रे")
                )

                items(fertItems) { item ->
                    Card(
                        shape = RoundedCornerShape(14.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = if (isHi) item.stageNameHi else item.stageNameEn,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp,
                                    color = KisanEmerald
                                )
                                Icon(Icons.Default.Science, contentDescription = null, tint = KisanEmerald, modifier = Modifier.size(18.dp))
                            }

                            Spacer(modifier = Modifier.height(6.dp))

                            Text(
                                text = "${if (isHi) "रासायनिक खुराक: " else "Nutrient Dose: "}${item.chemicalDose}",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.onSurface
                            )

                            if (!item.organicAlternative.isNullOrBlank()) {
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "🌿 ${if (isHi) "जैविक विकल्प: " else "Organic Alt: "}${item.organicAlternative}",
                                    fontSize = 11.sp,
                                    color = Color(0xFF059669),
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        }
                    }
                }
            }

            // Tab 3: Pest & Disease Management
            if (activePopTab == "pests") {
                val pestItems = cropDetail?.pestDiseaseManagement ?: listOf(
                    PestManagementItem(
                        pestNameHi = "माहू / चेपा (Aphids)",
                        pestNameEn = "Aphids",
                        symptomsHi = "पत्तियों व बालियों से रस चूसना, पत्तियां मुड़ना व चिपचिपापन।",
                        symptomsEn = "Sucking sap from leaves and pods.",
                        organicRemedyHi = "नीमास्त्र 5ml/L या 5% नीम तेल स्प्रे।",
                        organicRemedyEn = "Neemastra 5ml/L or 5% Neem oil.",
                        chemicalIPMHi = "इमिडाक्लोप्रिड 17.8% SL 0.5ml/L (केवल गंभीर प्रकोप में)।",
                        chemicalIPMEn = "Imidacloprid 17.8% SL 0.5ml/L."
                    ),
                    PestManagementItem(
                        pestNameHi = "पीला रतुआ / झुलसा (Yellow Rust / Blight)",
                        pestNameEn = "Yellow Rust",
                        symptomsHi = "पत्तियों पर पीले रंग की धारियां व पाउडर जैसा चूर्ण।",
                        symptomsEn = "Yellow stripe powdery pustules on leaves.",
                        organicRemedyHi = "खट्टी छाछ (500ml/15L पानी) या जीवामृत फोलियर स्प्रे।",
                        organicRemedyEn = "Sour buttermilk 500ml/15L water spray.",
                        chemicalIPMHi = "प्रोपिकोनाजोल 25% EC 1ml/L पानी में मिलाकर छिड़कें।",
                        chemicalIPMEn = "Propiconazole 25% EC 1ml/L."
                    )
                )

                items(pestItems) { p ->
                    Card(
                        shape = RoundedCornerShape(14.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = if (isHi) p.pestNameHi else p.pestNameEn,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 15.sp,
                                    color = Color(0xFFDC2626)
                                )
                                Icon(Icons.Default.BugReport, contentDescription = null, tint = Color(0xFFDC2626), modifier = Modifier.size(18.dp))
                            }

                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "${if (isHi) "लक्षण: " else "Symptoms: "}${if (isHi) p.symptomsHi else p.symptomsEn}",
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )

                            Spacer(modifier = Modifier.height(6.dp))
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = Color(0xFF059669).copy(alpha = 0.12f),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = "🌿 ${if (isHi) "जैविक उपचार: " else "Organic: "}${if (isHi) p.organicRemedyHi else p.organicRemedyEn}",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color(0xFF059669),
                                    modifier = Modifier.padding(8.dp)
                                )
                            }
                        }
                    }
                }
            }

            // Tab 4: Irrigation Schedule
            if (activePopTab == "irrigation") {
                val irrigationTips = cropDetail?.irrigationSchedule ?: listOf(
                    "1. पलेवा सिंचाई: बुवाई से पूर्व खेत में पर्याप्त नमी हेतु पहली सिंचाई करें।",
                    "2. पहली सिंचाई (21 दिन - CRI स्टेज): यह गेहूं की सबसे महत्वपूर्ण सिंचाई है।",
                    "3. दूसरी सिंचाई (45 दिन - कल्ले निकलना): कल्ले व जड़ों के फैलाव हेतु।",
                    "4. तीसरी सिंचाई (70 दिन - बाली निकलना): दाना बनने की शुरुआत में।",
                    "5. चौथी सिंचाई (90 दिन - दाना भराव): दाना मोटा व चमकदार बनने हेतु।"
                )

                item {
                    Card(
                        shape = RoundedCornerShape(14.dp),
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
                                    text = if (isHi) "क्रिटिकल सिंचाई अवस्थाएं (Critical Irrigation)" else "Critical Irrigation Schedule",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 15.sp,
                                    color = KisanEmerald
                                )
                                Icon(Icons.Default.WaterDrop, contentDescription = null, tint = KisanEmerald)
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                            irrigationTips.forEach { tip ->
                                Row(modifier = Modifier.padding(vertical = 4.dp)) {
                                    Icon(Icons.Default.CheckCircle, contentDescription = null, tint = KisanEmerald, modifier = Modifier.size(16.dp))
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = tip,
                                        fontSize = 12.sp,
                                        color = MaterialTheme.colorScheme.onSurface,
                                        lineHeight = 17.sp
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // Tab 5: Economics & Financial Projections
            if (activePopTab == "economics") {
                val fin = cropDetail?.financials ?: CropFinancials(
                    yieldAcreQuintals = "22 - 26 क्विंटल / एकड़",
                    avgMarketPriceQuintal = "₹2,275 - ₹2,500 / क्विंटल",
                    costOfCultivationAcre = "₹14,500 / एकड़",
                    grossRevenueAcre = "₹55,000 - ₹65,000 / एकड़",
                    netProfitAcre = "₹40,500 - ₹50,500 / एकड़"
                )

                item {
                    Card(
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = if (isHi) "लागत, उत्पादन व शुद्ध मुनाफा (प्रति एकड़)" else "Economics & Net Profit (Per Acre)",
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp,
                                color = KisanEmerald
                            )

                            Spacer(modifier = Modifier.height(12.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(if (isHi) "अनुमानित पैदावार (Yield):" else "Estimated Yield:", fontSize = 13.sp)
                                Text(fin.yieldAcreQuintals, fontWeight = FontWeight.Bold, fontSize = 13.sp, color = MaterialTheme.colorScheme.onSurface)
                            }

                            Spacer(modifier = Modifier.height(8.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(if (isHi) "बाजार भाव (Mandi Price):" else "Avg Price:", fontSize = 13.sp)
                                Text(fin.avgMarketPriceQuintal, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                            }

                            Spacer(modifier = Modifier.height(8.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(if (isHi) "कुल खेती लागत (Cost):" else "Cultivation Cost:", fontSize = 13.sp)
                                Text(fin.costOfCultivationAcre, fontWeight = FontWeight.Bold, fontSize = 13.sp, color = Color(0xFFDC2626))
                            }

                            Spacer(modifier = Modifier.height(12.dp))
                            Divider()
                            Spacer(modifier = Modifier.height(12.dp))

                            Surface(
                                shape = RoundedCornerShape(12.dp),
                                color = Color(0xFF059669).copy(alpha = 0.15f),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Row(
                                    modifier = Modifier.padding(14.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column {
                                        Text(if (isHi) "अनुमानित शुद्ध मुनाफा" else "Estimated Net Profit", fontSize = 11.sp, color = Color(0xFF059669))
                                        Text(
                                            text = fin.netProfitAcre,
                                            fontWeight = FontWeight.ExtraBold,
                                            fontSize = 18.sp,
                                            color = Color(0xFF059669)
                                        )
                                    }
                                    Icon(Icons.Default.MonetizationOn, contentDescription = null, tint = Color(0xFF059669), modifier = Modifier.size(32.dp))
                                }
                            }
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }

    // Season Picker BottomSheet
    if (showSeasonSheet) {
        ModalBottomSheet(
            onDismissRequest = { showSeasonSheet = false },
            containerColor = MaterialTheme.colorScheme.surface
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text(
                    text = if (isHi) "सीजन का चयन करें" else "Select Crop Season",
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp
                )
                Spacer(modifier = Modifier.height(12.dp))
                LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(ALL_SEASONS) { season ->
                        Card(
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = if (selectedSeason.id == season.id) KisanEmerald.copy(alpha = 0.15f) else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    selectedSeason = season
                                    showSeasonSheet = false
                                }
                        ) {
                            Text(
                                text = if (isHi) season.nameHi else season.nameEn,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                modifier = Modifier.padding(14.dp)
                            )
                        }
                    }
                }
            }
        }
    }

    // Category Picker BottomSheet
    if (showCategorySheet) {
        ModalBottomSheet(
            onDismissRequest = { showCategorySheet = false },
            containerColor = MaterialTheme.colorScheme.surface
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text(
                    text = if (isHi) "फसल वर्ग चुनें" else "Select Crop Category",
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp
                )
                Spacer(modifier = Modifier.height(12.dp))
                LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(ALL_CATEGORIES) { cat ->
                        Card(
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = if (selectedCategory.id == cat.id) KisanEmerald.copy(alpha = 0.15f) else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    selectedCategory = cat
                                    showCategorySheet = false
                                }
                        ) {
                            Text(
                                text = if (isHi) cat.nameHi else cat.nameEn,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                modifier = Modifier.padding(14.dp)
                            )
                        }
                    }
                }
            }
        }
    }

    // Crop Picker BottomSheet
    if (showCropSheet) {
        ModalBottomSheet(
            onDismissRequest = { showCropSheet = false },
            containerColor = MaterialTheme.colorScheme.surface
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text(
                    text = if (isHi) "फसल चुनें (${filteredCrops.size} उपलब्ध)" else "Select Crop (${filteredCrops.size} Available)",
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp
                )
                Spacer(modifier = Modifier.height(12.dp))
                LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(filteredCrops) { crop ->
                        Card(
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = if (selectedCropItem.id == crop.id) KisanEmerald.copy(alpha = 0.15f) else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    selectedCropItem = crop
                                    showCropSheet = false
                                }
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(14.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = if (isHi) crop.nameHi else crop.nameEn,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 15.sp
                                )
                                Icon(Icons.Default.ChevronRight, contentDescription = null, tint = KisanEmerald)
                            }
                        }
                    }
                }
            }
        }
    }
}
