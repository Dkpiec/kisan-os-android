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

    val fallbackSeeds = remember {
        listOf(
            SeedVariety(
                id = "veg-tom-01",
                category = "vegetable",
                cropName = "टमाटर (Tomato)",
                varietyName = "Arka Rakshak F1 (अर्क रक्षक)",
                breederOrCompany = "IIHR Bengaluru / ICAR",
                tasteProfile = "देसी खट्टा-मीठा स्वाद (Tangy-Rich gravy taste for Indian curries), गहरा लाल रंग",
                sowingWindow = "Aug-Nov (Rabi) & May-June (Kharif)",
                maturityDays = 140,
                seedRate = "40-50 gm / acre",
                expectedYield = "38-45 tonnes / acre",
                diseaseResistances = "Triple Resistance: ToLCV (Leaf Curl), Bacterial Wilt, Early Blight",
                specialFeatures = "मजबूत छिलका, 12-14 दिन लंबी शेल्फ लाइफ, लंबी दूरी के परिवहन हेतु उत्तम",
                rating = 4.9
            ),
            SeedVariety(
                id = "veg-bhi-01",
                category = "vegetable",
                cropName = "भिंडी (Okra)",
                varietyName = "Advanta Radhika F1 (राधिका भिंडी)",
                breederOrCompany = "Advanta Seeds / UPL",
                tasteProfile = "मुलायम, कम रेशा (Zero fiber tender bhindi fry profile)",
                sowingWindow = "Feb-Apr (Summer) & Jun-Aug (Rainy)",
                maturityDays = 45,
                seedRate = "2.5-3.0 kg / acre",
                expectedYield = "8-11 tonnes / acre",
                diseaseResistances = "High resistance to YVMV (Yellow Vein Mosaic Virus) & ELCV",
                specialFeatures = "पहला तुड़ान 42-45 दिन में, गहरा हरा रंग, हर गांठ पर फल",
                rating = 4.8
            ),
            SeedVariety(
                id = "veg-chl-01",
                category = "vegetable",
                cropName = "हरी मिर्च (Chilli)",
                varietyName = "US 341 F1 Hot Pepper",
                breederOrCompany = "Bayer CropScience (Nunhems)",
                tasteProfile = "तीखी चटनी एवं तड़का मिर्च (High pungency SHU 65,000+)",
                sowingWindow = "May-Jul & Oct-Nov",
                maturityDays = 150,
                seedRate = "70-80 gm / acre",
                expectedYield = "12-15 tonnes (green) / acre",
                diseaseResistances = "Anthracnose and Phytophthora tolerant",
                specialFeatures = "सख्त फल, निर्यात व लंबी दूरी की मंडियों में सर्वोच्च दाम",
                rating = 4.9
            ),
            SeedVariety(
                id = "grn-wht-01",
                category = "grain",
                cropName = "गेहूँ (Wheat)",
                varietyName = "DBW-327 (Karan Shivani)",
                breederOrCompany = "IIWBR Karnal",
                tasteProfile = "मीठी व फुल्का रोटी (High chapati score 8.2/10, rich zinc & iron)",
                sowingWindow = "01 Nov - 15 Nov (Timely Sown)",
                maturityDays = 155,
                seedRate = "40 kg / acre",
                expectedYield = "32-35.5 quintals / acre",
                diseaseResistances = "Immune to Yellow Rust (पीला रतुआ) & Brown Rust",
                specialFeatures = "अक्टूबर 2026 में भारत का सबसे अधिक उपज देने वाला जलवायु-सहिष्णु गेहूँ",
                rating = 5.0
            ),
            SeedVariety(
                id = "grn-wht-02",
                category = "grain",
                cropName = "देसी गेहूँ (Desi Wheat)",
                varietyName = "C-306 (Sharbati Golden)",
                breederOrCompany = "IARI New Delhi",
                tasteProfile = "अत्यधिक मीठी एवं 12 घंटे तक मुलायम रहने वाली रोटी",
                sowingWindow = "20 Oct - 05 Nov",
                maturityDays = 140,
                seedRate = "35-40 kg / acre",
                expectedYield = "18-22 quintals / acre",
                diseaseResistances = "Drought tolerant, low input variety",
                specialFeatures = "बाजार में प्रीमियम भाव (₹3,500-₹4,200/क्विंटल)",
                rating = 4.8
            ),
            SeedVariety(
                id = "grn-ric-01",
                category = "grain",
                cropName = "बासमती धान (Basmati Rice)",
                varietyName = "Pusa Basmati 1885 (1885 बासमती)",
                breederOrCompany = "ICAR - IARI New Delhi",
                tasteProfile = "शाही सुगंध, पकने पर 2.5 गुना लम्बा दाना (Extra-long slender grain)",
                sowingWindow = "20 May - 15 Jun (Nursery)",
                maturityDays = 140,
                seedRate = "4-5 kg / acre",
                expectedYield = "25-28 quintals / acre",
                diseaseResistances = "BLB (Bacterial Leaf Blight) & Blast resistant (Non-lodging)",
                specialFeatures = "पूसा 1401 का उन्नत रोग-प्रतिरोधी विकल्प",
                rating = 4.9
            ),
            SeedVariety(
                id = "pls-arh-01",
                category = "pulse",
                cropName = "अरहर / तुअर (Pigeon Pea)",
                varietyName = "Pusa Arhar 16 (पूसा अरहर 16)",
                breederOrCompany = "IARI New Delhi",
                tasteProfile = "मीठी गाढ़ी दाल, शीघ्र पकने वाली (Quick cooking high-protein dal)",
                sowingWindow = "15 Jun - 05 Jul",
                maturityDays = 120,
                seedRate = "6-8 kg / acre",
                expectedYield = "8-10 quintals / acre",
                diseaseResistances = "Wilt and Sterility Mosaic resistant",
                specialFeatures = "मात्र 120 दिन में तैयार! गेहूं की समय पर बुवाई संभव",
                rating = 4.7
            ),
            SeedVariety(
                id = "pls-chn-01",
                category = "pulse",
                cropName = "चना (Chickpea)",
                varietyName = "JG 14 (जे.जी. 14)",
                breederOrCompany = "JNKVV Jabalpur & ICRISAT",
                tasteProfile = "स्वादिष्ट सत्तू, बेसन एवं छोले हेतु सर्वोत्तम",
                sowingWindow = "15 Oct - 15 Nov",
                maturityDays = 105,
                seedRate = "30-35 kg / acre",
                expectedYield = "10-12 quintals / acre",
                diseaseResistances = "High heat and terminal drought tolerant",
                specialFeatures = "देरी से बुवाई के लिए भारत का नंबर 1 चना",
                rating = 4.8
            ),
            SeedVariety(
                id = "oil-mus-01",
                category = "oilseed",
                cropName = "सरसों (Mustard)",
                varietyName = "Pusa Mustard 31 (PDZ-1)",
                breederOrCompany = "IARI New Delhi",
                tasteProfile = "तीखा कड़वा तेल स्वाद (Strong pungency mustard oil 41.5% oil content)",
                sowingWindow = "05 Oct - 25 Oct",
                maturityDays = 142,
                seedRate = "1.5 kg / acre",
                expectedYield = "10-12 quintals / acre",
                diseaseResistances = "White rust tolerant",
                specialFeatures = "00-कनोला क्वालिटी (Low erucic acid, दिल के लिए सुरक्षित)",
                rating = 4.8
            ),
            SeedVariety(
                id = "mlt-jow-01",
                category = "millet",
                cropName = "ज्वार (Sorghum Millet)",
                varietyName = "Maldandi M 35-1 (मालदांडी ज्वार)",
                breederOrCompany = "Mahatma Phule Krishi Vidyapeeth",
                tasteProfile = "सफेद चमकदार दाना, मीठी भाकरी/रोटी (Sweet wholesome millet roti)",
                sowingWindow = "15 Sep - 15 Oct (Rabi)",
                maturityDays = 125,
                seedRate = "4 kg / acre",
                expectedYield = "12-14 quintals grain + 35 q dry fodder",
                diseaseResistances = "Drought tolerant, low pest incidence",
                specialFeatures = "प्रीमियम रबी ज्वार, स्वास्थ्य के लिए वरदान",
                rating = 4.9
            )
        )
    }

    val effectiveSeeds = if (seeds.isNotEmpty()) seeds else fallbackSeeds

    val categories = listOf(
        "all" to if (currentLang == "hi") "सभी किस्में" else "All Seeds",
        "vegetable" to if (currentLang == "hi") "सब्जियां (Vegetables)" else "Vegetables",
        "grain" to if (currentLang == "hi") "अनाज (Grains)" else "Grains",
        "pulse" to if (currentLang == "hi") "दालें (Pulses)" else "Pulses",
        "oilseed" to if (currentLang == "hi") "तिलहन (Mustard)" else "Oilseeds",
        "millet" to if (currentLang == "hi") "श्री अन्न (Millets)" else "Millets"
    )

    val filteredSeeds = effectiveSeeds.filter { s ->
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
                        text = if (currentLang == "hi") "उन्नत बीज कैटलॉग (Seeds)" else "Certified Seed Catalogue",
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
                Column(modifier = Modifier.weight(1f)) {
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
