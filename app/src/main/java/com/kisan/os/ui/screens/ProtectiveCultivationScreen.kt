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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kisan.os.models.PolyhouseCrop

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProtectiveCultivationScreen(
    currentLang: String,
    onBack: () -> Unit
) {
    val isHi = currentLang == "hi"

    val structures = remember {
        listOf(
            PolyhouseCrop(
                titleEn = "Naturally Ventilated Polyhouse (NVPH)",
                titleHi = "प्राकृतिक हवादार पॉलीहाउस (NVPH)",
                structureType = if (isHi) "200 माइक्रोन UV-स्टैबिलाइज्ड पॉलीथीन + 40 मेश जाली" else "200 Micron UV Diffused Film + 40 Mesh Insect Net",
                recommendedCrops = listOf(
                    if (isHi) "लाल व पीली शिमला मिर्च (Colored Capsicum) - 35-40 टन/एकड़" else "Colored Bell Peppers (Red & Yellow) - 35-40 Ton/Acre",
                    if (isHi) "पार्थेनोकार्पिक सीडलेस खीरा (Parthenocarpic Cucumber) - 45-50 टन/एकड़" else "Parthenocarpic Seedless Cucumber - 45-50 Ton/Acre",
                    if (isHi) "डच गुलाब एवं जरबेरा (Dutch Roses / Gerbera) - 2 लाख कट फ्लावर/एकड़" else "Dutch Roses & Gerbera - 2 Lakh Stems/Acre"
                ),
                expectedReturnPerAcre = if (isHi) "₹12 - ₹18 लाख प्रति एकड़ शुद्ध लाभ" else "₹12 - ₹18 Lakhs Net Return / Acre",
                subsidyAvailable = if (isHi) "50% NHB (राष्ट्रीय बागवानी बोर्ड) सब्सिडी उपलब्ध" else "50% NHB (National Horticulture Board) Subsidy Available",
                climateControlTips = if (isHi) "तापमान: 24-28°C, आर्द्रता (Humidity): 65-75%, VPD: 0.8-1.2 kPa रखें। गर्मियों में साइड पर्दे खोलें व टॉप फॉगर्स चलाएं।" else "Target Temp: 24-28°C, Humidity: 65-75%, VPD: 0.8-1.2 kPa. Operate top foggers during high heat."
            ),
            PolyhouseCrop(
                titleEn = "Green / White Shade Net House (50%)",
                titleHi = "शेड नेट हाउस (50% ग्रीन/व्हाइट नेट)",
                structureType = if (isHi) "50% UV-ट्रीटेड एग्रो शेड नेट + माइक्रो-स्प्रिंकलर सिस्टम" else "50% UV-Treated Agro Net + Overhead Micro Sprinklers",
                recommendedCrops = listOf(
                    if (isHi) "बेमौसमी हरा धनिया (Summer Off-season Coriander) - ₹80-₹150/kg भाव" else "Summer Off-season Coriander - Commands ₹80-₹150/kg in mandis",
                    if (isHi) "ब्रोकोली एवं रेड कैबेज (Broccoli & Red Cabbage) - 12-15 टन/एकड़" else "Broccoli & Red Cabbage - 12-15 Ton/Acre",
                    if (isHi) "स्ट्रॉबेरी (Winter Strawberry) - 10-12 टन/एकड़" else "Winter Strawberry Cultivation - 10-12 Ton/Acre"
                ),
                expectedReturnPerAcre = if (isHi) "₹6 - ₹10 लाख प्रति एकड़ शुद्ध लाभ" else "₹6 - ₹10 Lakhs Net Return / Acre",
                subsidyAvailable = if (isHi) "50% राज्य बागवानी मिशन (SHM) सब्सिडी" else "50% State Horticulture Mission (SHM) Subsidy",
                climateControlTips = if (isHi) "मई-जून की तेज धूप से 50% रक्षा। दोपहर 12 से 3 बजे के बीच 5-5 मिनट के अंतराल पर फॉगर चलाएं।" else "Protects 50% solar radiation in peak summer. Pulse foggers for 5 mins every hour."
            ),
            PolyhouseCrop(
                titleEn = "Low Tunnel & Walk-in Tunnels",
                titleHi = "लो-टनल एवं वॉक-इन टनल (सब्जी अगेती खेती)",
                structureType = if (isHi) "GI आर्च पाइप + 25 माइक्रोन पारदर्शी मल्च/फिल्म" else "GI Arches + 25-50 Micron Transparent Mulch/Film",
                recommendedCrops = listOf(
                    if (isHi) "अगेती लौकी, तोरी व कद्दू (Early Cucurbits) - 30 दिन पहले आवक" else "Early Cucurbits (Bottle Gourd, Bitter Gourd) - 30 days early harvest",
                    if (isHi) "तरबूज एवं खरबूजा (Early Watermelon & Muskmelon)" else "Early Watermelon & Muskmelon"
                ),
                expectedReturnPerAcre = if (isHi) "₹3 - ₹5 लाख प्रति एकड़" else "₹3 - ₹5 Lakhs / Acre",
                subsidyAvailable = if (isHi) "ड्रिप व मल्चिंग पर 45-55% सरकारी अनुदान" else "45-55% Subsidy on Drip & Plastic Mulch",
                climateControlTips = if (isHi) "दिसंबर-जनवरी के पाले (Frost) और ठंड से 100% सुरक्षा। फरवरी में धूप तेज होने पर किनारे खोलें।" else "100% protection against winter frost and cold wave. Open edges in Feb sunshine."
            )
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            if (isHi) "संरक्षित खेती एवं पॉलीहाउस" else "Protective Cultivation",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                        Text(
                            if (isHi) "पॉलीहाउस, शेडनेट, फसल चयन व सब्सिडी" else "Polyhouse, Shadenet & High Value Crops",
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
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f)
                    )
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.primary),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Default.Info, contentDescription = null, tint = Color.White)
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Column {
                            Text(
                                if (isHi) "3x से 5x अधिक मुनाफा" else "3x to 5x Higher Farm Profit",
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )
                            Text(
                                if (isHi) "मौसम की मार से मुक्ति, 80% कम कीटनाशक खर्च और बेमौसम उच्च मंडी भाव।" else "Controlled climate, zero weather risk, 80% less pesticide, high off-season pricing.",
                                fontSize = 13.sp,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
                            )
                        }
                    }
                }
            }

            items(structures) { item ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f)
                    ),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = if (isHi) item.titleHi else item.titleEn,
                            fontWeight = FontWeight.Bold,
                            fontSize = 17.sp,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = item.structureType,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                        )

                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = if (isHi) "अनुशंसित फसलें (Top Crops):" else "Recommended High Yield Crops:",
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                        item.recommendedCrops.forEach { crop ->
                            Row(
                                modifier = Modifier.padding(vertical = 2.dp),
                                verticalAlignment = Alignment.Top
                            ) {
                                Text("• ", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                                Text(crop, fontSize = 13.sp)
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    if (isHi) "अनुमानित आय" else "Expected Net Return",
                                    fontSize = 11.sp,
                                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                                )
                                Text(
                                    item.expectedReturnPerAcre,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF059669)
                                )
                            }
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    if (isHi) "सरकारी अनुदान" else "Govt Subsidy",
                                    fontSize = 11.sp,
                                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                                )
                                Text(
                                    item.subsidyAvailable,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF2563EB)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = MaterialTheme.colorScheme.surface
                        ) {
                            Row(
                                modifier = Modifier.padding(10.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    Icons.Default.Star,
                                    contentDescription = null,
                                    tint = Color(0xFFF59E0B),
                                    modifier = Modifier.size(18.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = item.climateControlTips,
                                    fontSize = 12.sp,
                                    lineHeight = 16.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
