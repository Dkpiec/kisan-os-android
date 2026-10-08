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
    val isHi = currentLang == "hi"
    var selectedCat by remember { mutableStateOf<String?>(null) }
    var expandedRecipeId by remember { mutableStateOf<String?>(null) }

    val masterRecipes = remember {
        listOf(
            OrganicRecipe(
                id = "jadam-js",
                category = "jadam",
                name = if (isHi) "जादम सल्फर (JADAM Sulfur - JS)" else "JADAM Sulfur (JS)",
                purpose = if (isHi) "प्राकृतिक फफूंदनाशक एवं लाल मकड़ी (Mites) नियंत्रण" else "Natural Fungicide & Mite Controller",
                preparationDays = 1,
                shelfLifeDays = 3650,
                ingredients = listOf(
                    mapOf("item" to if (isHi) "सल्फर पाउडर (99.9% शुद्ध)" else "Sulfur Powder (99.9% pure)", "qty" to "25 kg"),
                    mapOf("item" to if (isHi) "कॉस्टिक सोडा (NaOH 98%)" else "Caustic Soda (NaOH 98%)", "qty" to "20 kg"),
                    mapOf("item" to if (isHi) "लाल मिट्टी पाउडर (Red Clay)" else "Red Clay Powder", "qty" to "0.5 kg"),
                    mapOf("item" to if (isHi) "चूना पत्थर पाउडर (Limestone / Sea salt)" else "Limestone Powder", "qty" to "0.5 kg"),
                    mapOf("item" to if (isHi) "साफ पानी (Clean Water)" else "Clean Soft Water", "qty" to "50 Liters")
                ),
                stepByStep = if (isHi) 
                    "1. 100L प्लास्टिक या स्टेनलेस स्टील ड्रम लें।\n2. सल्फर, लाल मिट्टी, चूना और कॉस्टिक सोडा डालें।\n3. 50L पानी डालकर लकड़ी के डंडे से तुरंत हिलाएं (80-90°C ऊष्मा उत्पन्न होगी)।\n4. 20 मिनट में घोल गहरे लाल-भूरे रंग में पारदर्शी हो जाएगा। ठंडा होने पर छान लें।"
                    else "1. Take a 100L heat-resistant plastic/stainless steel barrel.\n2. Add sulfur, red clay, limestone and caustic soda.\n3. Add 50L water and stir immediately with wooden stick (Self-boiling reaction reaches 85°C).\n4. In 20 minutes, solution turns transparent dark ruby red. Cool and store in closed container.",
                dosagePerAcre = "500 ml - 1.0 Liter",
                dosagePerPump = "25 - 40 ml per 15L pump",
                targetedPests = if (isHi) "पाउडरी मिल्ड्यू, डाउनी मिल्ड्यू, एन्थ्रेक्नोज, स्केब, रतुआ, लाल मकड़ी" else "Powdery Mildew, Downy Mildew, Anthracnose, Leaf Blight, Red Spider Mites",
                precautions = if (isHi) "कॉस्टिक सोडा डालते समय चश्मा व दस्ताने पहनें। हमेशा JWA (गीला करने वाला एजेंट) के साथ मिलाकर छिड़कें।" else "Wear protective eye goggles & gloves during mixing. Always combine with JWA."
            ),
            OrganicRecipe(
                id = "jadam-jwa",
                category = "jadam",
                name = if (isHi) "जादम वेटिंग एजेंट (JADAM Wetting Agent - JWA)" else "JADAM Wetting Agent (JWA)",
                purpose = if (isHi) "प्राकृतिक स्टिकर, स्प्रेडर एवं कीट-छिद्रक" else "Natural Spreader, Sticker & Insect Pest Penetrator",
                preparationDays = 3,
                shelfLifeDays = 3650,
                ingredients = listOf(
                    mapOf("item" to if (isHi) "कैनोला या सरसों तेल" else "Canola or Mustard Oil", "qty" to "18 Liters"),
                    mapOf("item" to if (isHi) "कॉस्टिक पोटाश (KOH 90%)" else "Caustic Potash (KOH 90%)", "qty" to "3.2 kg"),
                    mapOf("item" to if (isHi) "मुलायम/बारिश का पानी" else "Soft or Rain Water", "qty" to "80 Liters")
                ),
                stepByStep = if (isHi)
                    "1. 110L ड्रम में 3.2 kg KOH और 2.5L पानी डालकर पूरी तरह घोलें।\n2. 18L सरसों/कैनोला तेल डालें और इलेक्ट्रिक ड्रिल मिक्सर से 10 मिनट फेंटें (मेयोनेज़ जैसा गाढ़ा होगा)।\n3. 3 दिन ढककर रखें। इसके बाद 80L गर्म/साफ पानी डालकर लकड़ी से हिलाकर पूरी तरह तरल बना लें।"
                    else "1. In 110L barrel, dissolve 3.2 kg KOH in 2.5L soft water.\n2. Pour 18L canola oil and mix thoroughly with drill mixer for 10 min until thick mayonnaise consistency.\n3. Let it cure for 3 days. Then add 80L clean water and dissolve completely into clear liquid soap.",
                dosagePerAcre = "1.5 - 2.5 Liters",
                dosagePerPump = "80 - 150 ml per 15L pump",
                targetedPests = if (isHi) "एफिड्स, थ्रिप्स, सफेद मक्खी, मिलीबग, स्पाइडर माइट्स का मोमी कवच घोलना" else "Aphids, Thrips, Whiteflies, Mealybugs, Spider Mites (dissolves protective waxy coat)",
                precautions = if (isHi) "कठोर पानी (Hard water) का उपयोग न करें।" else "Use only soft water / rainwater for preparation."
            ),
            OrganicRecipe(
                id = "zbnf-jeevamrut",
                category = "zbnf",
                name = if (isHi) "जीवामृत (ZBNF Liquid Jeevamrut)" else "Liquid Jeevamrut",
                purpose = if (isHi) "मृदा सूक्ष्मजीव सक्रियक एवं प्राकृतिक उर्वरक" else "Soil Microorganism Multiplier & Bio-Fertilizer",
                preparationDays = 3,
                shelfLifeDays = 12,
                ingredients = listOf(
                    mapOf("item" to if (isHi) "देसी गाय का ताजा गोबर" else "Fresh Desi Cow Dung", "qty" to "10 kg"),
                    mapOf("item" to if (isHi) "देसी गाय का गोमूत्र" else "Desi Cow Urine", "qty" to "10 Liters"),
                    mapOf("item" to if (isHi) "पुराना गुड़ (Jaggery)" else "Organic Jaggery", "qty" to "2 kg"),
                    mapOf("item" to if (isHi) "बेसन (दाल का आटा)" else "Besan (Gram Flour)", "qty" to "2 kg"),
                    mapOf("item" to if (isHi) "मेड़/बरगद के नीचे की सजीव मिट्टी" else "Virgin Soil (under Banyan tree)", "qty" to "Handful (250g)"),
                    mapOf("item" to if (isHi) "पानी" else "Water", "qty" to "200 Liters")
                ),
                stepByStep = if (isHi)
                    "1. 200L ड्रम में पानी भरें और गोबर, गोमूत्र, गुड़, बेसन व मिट्टी मिलाएं।\n2. लकड़ी के डंडे से घड़ी की सुई की दिशा (Clockwise) में 2 मिनट सुबह-शाम हिलाएं।\n3. ड्रम को जूट की बोरी से ढककर छाया में रखें। 48-72 घंटे में करोड़ों जीवाणुओं से युक्त जीवामृत तैयार।"
                    else "1. In a 200L drum, mix water, cow dung, urine, jaggery, gram flour and virgin soil.\n2. Stir clockwise for 2 minutes morning and evening.\n3. Cover with jute gunny bag and keep in shade. Ready in 48-72 hours with billions of active beneficial microbes.",
                dosagePerAcre = "200 Liters per irrigation / fertigation",
                dosagePerPump = "1.5 - 2.0 Liters per 15L pump (filtered for foliar)",
                targetedPests = if (isHi) "मिट्टी में केंचुओं की संख्या बढ़ाना, NPK एवं सूक्ष्म पोषक तत्वों की उपलब्धता" else "Soil carbon rejuvenation, earthworm multiplication, bio-available NPK & micronutrients",
                precautions = if (isHi) "7-10 दिन के अंदर उपयोग कर लें।" else "Use within 7-10 days of fermentation."
            ),
            OrganicRecipe(
                id = "zbnf-neemastra",
                category = "zbnf",
                name = if (isHi) "नीमास्त्र (Neemastra Organic Bio-Insecticide)" else "Neemastra Bio-Insecticide",
                purpose = if (isHi) "रस चूसक कीटों एवं प्रारंभिक सुंडी का संपूर्ण जैविक नियंत्रण" else "Broad-Spectrum Sucking Pest & Early Borer Control",
                preparationDays = 2,
                shelfLifeDays = 180,
                ingredients = listOf(
                    mapOf("item" to if (isHi) "नीम की पत्तियां / निंबोली चटनी" else "Crushed Neem Leaves / Kernels", "qty" to "10 kg"),
                    mapOf("item" to if (isHi) "देसी गाय का गोमूत्र" else "Desi Cow Urine", "qty" to "10 Liters"),
                    mapOf("item" to if (isHi) "देसी गाय का ताजा गोबर" else "Fresh Cow Dung", "qty" to "2 kg"),
                    mapOf("item" to if (isHi) "पानी" else "Water", "qty" to "200 Liters")
                ),
                stepByStep = if (isHi)
                    "1. 200L पानी में 10 kg कुटी हुई नीम की पत्ती/निंबोली, गोमूत्र और गोबर अच्छी तरह मिलाएं।\n2. छाया में रखें और 48 घंटे तक दिन में दो बार हिलाएं।\n3. कपड़े से छानकर सीधे फसल पर स्प्रे करें।"
                    else "1. In 200L water, mix 10 kg crushed neem leaves, cow urine and dung.\n2. Keep in shade and stir twice daily for 48 hours.\n3. Filter through cotton cloth and spray directly on crops.",
                dosagePerAcre = "200 Liters spray",
                dosagePerPump = "1.0 - 1.5 Liters per 15L pump",
                targetedPests = if (isHi) "माहू (Aphids), सफेद मक्खी, थ्रिप्स, इल्ली के अंडे एवं छोटी सुंडी" else "Aphids, Whiteflies, Thrips, Caterpillars, Fruit borer larvae & eggs",
                precautions = if (isHi) "शाम के समय 4 बजे के बाद ही छिड़काव करें।" else "Spray during late afternoon hours for best efficacy."
            ),
            OrganicRecipe(
                id = "compost-18day",
                category = "compost",
                name = if (isHi) "18-दिवसीय बर्कले कम्पोस्ट (18-Day Rapid Hot Compost)" else "18-Day Rapid Hot Compost",
                purpose = if (isHi) "मात्र 18 दिन में खरपतवार-मुक्त कार्बनिक समृद्ध ह्यूमस खाद" else "18-Day Weed-Free High Organic Humus Compost",
                preparationDays = 18,
                shelfLifeDays = 720,
                ingredients = listOf(
                    mapOf("item" to if (isHi) "सूखा भूरा पदार्थ (सूखे पत्ते, पराली, डंठल)" else "Carbon/Browns (Dry straw, stalks, sawdust)", "qty" to "30 parts (60%)"),
                    mapOf("item" to if (isHi) "हरा पदार्थ (हरी पत्तियां, घास, गोबर, छिलके)" else "Nitrogen/Greens (Fresh grass, manure, kitchen waste)", "qty" to "10 parts (40%)"),
                    mapOf("item" to if (isHi) "पानी (55% नमी बनाए रखने हेतु)" else "Water (Moisture maintain 50-60%)", "qty" to "As required")
                ),
                stepByStep = if (isHi)
                    "1. 1m x 1m x 1.5m ऊंचाई का ढेर भूरे और हरे कचरे की परतें बनाकर लगाएं।\n2. 4 दिन तक ढेर को बिना छेड़े गर्म होने दें (तापमान 55-65°C पहुंचेगा जिससे खरपतवार के बीज मर जाएंगे)।\n3. 4ठे, 6ठे, 8वें, 10वें, 12वें, 14वें, 16वें और 18वें दिन बाहर की परत को अंदर पलटें।\n4. 18वें दिन काले रंग की मीठी खुशबूदार कम्पोस्ट खाद तैयार!"
                    else "1. Build 1m x 1m x 1.5m heap layering browns (C) and greens (N) at 30:1 C:N ratio.\n2. Leave for 4 days to heat up to 55-65°C (kills weed seeds & pathogens).\n3. Turn pile on Days 4, 6, 8, 10, 12, 14, 16 and 18 (move outer layer to inner core).\n4. Rich dark forest-scented compost is ready on Day 18.",
                dosagePerAcre = "1.5 - 2.5 tonnes per acre",
                dosagePerPump = "N/A (Soil application)",
                targetedPests = if (isHi) "मृदा जीवांश कार्बन (SOC) को 0.4% से बढ़ाकर 1.2% तक ले जाना" else "Increases Soil Organic Carbon (SOC), improves water holding capacity & root aeration",
                precautions = if (isHi) "ढेर में नमी 50-60% रखें (हाथ में दबाने पर पानी की एक बूंद टपके)।" else "Maintain 50-60% moisture; turn pile regularly to prevent anaerobic smell."
            )
        )
    }

    val effectiveRecipes = if (recipes.isNotEmpty()) recipes else masterRecipes

    val categories = listOf(
        "all" to if (isHi) "सभी नुस्खे" else "All Inputs",
        "jadam" to if (isHi) "जादम (JADAM)" else "JADAM Organic",
        "zbnf" to if (isHi) "प्राकृतिक खेती (ZBNF)" else "ZBNF / Vedic",
        "compost" to if (isHi) "कम्पोस्ट (Composting)" else "Rapid Compost"
    )

    val filteredRecipes = effectiveRecipes.filter { r ->
        selectedCat == null || selectedCat == "all" || r.category.equals(selectedCat, ignoreCase = true)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = if (isHi) "जैविक खेती एवं जादम नुस्खे" else "Organic & JADAM Hub",
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
