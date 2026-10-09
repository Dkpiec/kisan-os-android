package com.kisan.os.models

/**
 * Exhaustive Local Agronomy Catalog for Kisan Mitra (KisanOS).
 * Contains verified, authentic, distinct Package of Practices (POP)
 * for all 32 major Indian crops across Rabi, Kharif, Zaid, and Fruit Orchards.
 */
object CropAgronomyCatalog {

    fun getCropDetail(cropId: String): CropAgronomyDetail {
        return when (cropId.lowercase()) {
            "wheat" -> wheatDetail
            "mustard" -> mustardDetail
            "chickpea" -> chickpeaDetail
            "potato" -> potatoDetail
            "tomato_rabi" -> tomatoRabiDetail
            "chilli_rabi" -> chilliRabiDetail
            "onion_rabi" -> onionRabiDetail
            "garlic" -> garlicDetail
            "peas" -> peasDetail
            "cauliflower" -> cauliflowerDetail
            "lentil" -> lentilDetail
            "paddy_basmati" -> paddyBasmatiDetail
            "paddy_hybrid" -> paddyHybridDetail
            "maize" -> maizeDetail
            "soybean" -> soybeanDetail
            "cotton" -> cottonDetail
            "pigeon_pea" -> pigeonPeaDetail
            "bajra" -> bajraDetail
            "okra_kharif" -> okraKharifDetail
            "brinjal" -> brinjalDetail
            "groundnut" -> groundnutDetail
            "watermelon" -> watermelonDetail
            "muskmelon" -> muskmelonDetail
            "cucumber" -> cucumberDetail
            "bottle_gourd" -> bottleGourdDetail
            "bitter_gourd" -> bitterGourdDetail
            "moong_zaid" -> moongZaidDetail
            "mango" -> mangoDetail
            "papaya" -> papayaDetail
            "guava" -> guavaDetail
            "pomegranate" -> pomegranateDetail
            "banana" -> bananaDetail
            else -> wheatDetail
        }
    }

    // 1. Wheat (गेहूं)
    private val wheatDetail = CropAgronomyDetail(
        id = "wheat",
        nameEn = "Wheat",
        nameHi = "गेहूं",
        category = "grains",
        categoryHi = "अनाज",
        season = "rabi",
        seasonHi = "रबी",
        durationDays = 135,
        soilSuitability = "Well-drained loam to clay loam (pH 6.0 - 7.5)",
        soilSuitabilityHi = "अच्छी जल निकासी वाली दोमट व मटियार दोमट भूमि (pH 6.0 - 7.5)",
        seedRate = "40-45 kg / एकड़ (Broadcasting/Drill), 30-35 kg (Zero Till)",
        seedRateHi = "40-45 किग्रा प्रति एकड़ (ड्रिल/छिड़काव विधि)",
        spacing = "20 x 5 cm",
        spacingHi = "कतार से कतार 20 सेमी, पौधे से पौधा 5 सेमी",
        recommendedVarieties = listOf("DBW-327 (Karan Shivani)", "DBW-187 (Karan Vandana)", "HD-3226 (Pusa Yashasvi)", "HD-2967"),
        recommendedVarietiesHi = listOf("डीबीडब्ल्यू-327 (करन शिवानी)", "डीबीडब्ल्यू-187", "एचडी-3226 (पूसा यशस्वी)", "एचडी-2967"),
        stages = listOf(
            CropStageInfo(1, "0-7 DAS", "खेत तैयारी व पलेवा", "Sowing & Germination", "4-5 टन सड़ी गोबर खाद या 500 किग्रा घनजीवामृत मिलाएं। पलेवा देकर ओट आने पर बुवाई करें।", "Pre-sowing irrigation (Palewa) & sowing in optimal moisture."),
            CropStageInfo(2, "20-25 DAS", "सीआरआई / कल्ले फूटना (CRI)", "Crown Root Initiation", "पहली सबसे जरूरी सिंचाई (CRI अवस्था)। 25-30 किग्रा यूरिया प्रति एकड़ टॉप ड्रेसिंग।", "1st critical irrigation & Urea top dressing."),
            CropStageInfo(3, "40-45 DAS", "गाभा / कल्ले वृद्धि (Tillering)", "Tillering & Jointing", "दूसरी सिंचाई। एनपीके 19:19:19 @ 1 किग्रा + जिंक 100 ग्राम प्रति 150 लीटर पानी स्प्रे।", "2nd irrigation & foliar micronutrient spray."),
            CropStageInfo(4, "65-75 DAS", "बालियां निकलना (Booting / Heading)", "Booting & Heading", "तीसरी सिंचाई। एनपीके 0:52:34 @ 1 किग्रा + बोरॉन 20% @ 150 ग्राम स्प्रे परागण हेतु।", "3rd irrigation & 0:52:34 + Boron foliar spray."),
            CropStageInfo(5, "85-95 DAS", "दुग्ध व दाना भराव (Grain Filling)", "Milking & Dough", "चौथी हल्की सिंचाई (हवा शांत होने पर)। एनपीके 0:0:50 @ 1 किग्रा स्प्रे चमकदार दाने हेतु।", "4th light irrigation & 0:0:50 Potash spray."),
            CropStageInfo(6, "120-135 DAS", "परिपक्वता व कटाई (Harvesting)", "Maturity & Harvest", "पत्तियां व बालियां सुनहरी पीली होने पर कंबाइन/रीपर से कटाई व सुरक्षित भंडारण।", "Harvest when moisture drops below 12%.")
        ),
        fertigationSchedule = listOf(
            FertigationItem("At Sowing (Basal)", "बुवाई के समय (बेसल डोज)", "DAP 50 kg + MOP Potash 25 kg + Zinc Sulphate 10 kg / acre", "डीएपी: 50 किग्रा, पोटाश (MOP): 25 किग्रा, जिंक सल्फेट 21%: 10 किग्रा प्रति एकड़।", "500 किग्रा घनजीवामृत + 10 किग्रा नीम खली प्रति एकड़।"),
            FertigationItem("20-25 DAS (1st Top Dress)", "पहली सिंचाई (21 दिन)", "Urea 30-35 kg / acre", "यूरिया 30-35 किग्रा प्रति एकड़।", "जीवामृत 200 लीटर प्रति एकड़ सिंचाई पानी के साथ।"),
            FertigationItem("45 DAS (2nd Top Dress)", "दूसरी सिंचाई (45 दिन)", "Urea 25 kg + Micronutrient spray", "यूरिया 25 किग्रा + एनपीके 19:19:19 @ 1 किग्रा/एकड़ स्प्रे।", "खट्टी छाछ 5 लीटर + 200 लीटर पानी स्प्रे।"),
            FertigationItem("70 DAS (Booting Stage)", "बालियां आते समय", "NPK 0:52:34 1 kg + Boron 150 g / acre", "एनपीके 0:52:34 @ 1 किग्रा + बोरॉन 20% @ 150 ग्राम स्प्रे।", "बायो-पोटाश / राख का अर्क स्प्रे।"),
            FertigationItem("90 DAS (Grain Filling)", "दाना भराव के समय", "NPK 0:0:50 (Potash) 1 kg / acre", "एनपीके 0:0:50 (सल्फेट ऑफ पोटाश) @ 1 किग्रा स्प्रे।", "जीवामृत स्प्रे 10% घोल।")
        ),
        pestDiseaseManagement = listOf(
            PestManagementItem("Yellow Rust", "पीला रतुआ (Yellow Rust)", symptomsHi = "पत्तियों पर पीले रंग की धारियां व पाउडर जैसे दाने बनते हैं जो अंगुलियों पर लग जाते हैं।", symptomsEn = "Yellow stripe pustules on leaves.", organicRemedyHi = "खट्टी छाछ 5 लीटर + 200 ग्राम हींग प्रति 150 लीटर पानी, या ट्राइकोडर्मा विरिडी 1 किग्रा/एकड़।", chemicalControlHi = "प्रोपिकोनाजोल 25% EC (टिल्ट) @ 200 मिली प्रति 200 लीटर पानी में तुरंत स्प्रे करें।"),
            PestManagementItem("Aphids (Mahun)", "माहूं / चेपा (Aphids)", symptomsHi = "हरे-काले छोटे कीट बालियों व पत्तियों का रस चूसते हैं जिससे दाना कमजोर रह जाता है।", symptomsEn = "Sap sucking aphids on ears.", organicRemedyHi = "नीमास्त्र @ 5 लीटर प्रति 150 लीटर पानी या 5% नीम तेल स्प्रे।", chemicalControlHi = "थियामेथोक्सम 25% WG @ 80 ग्राम प्रति एकड़ 150 लीटर पानी में स्प्रे करें।"),
            PestManagementItem("Termites", "दीमक (Termites)", symptomsHi = "पौधे की जड़ें कट जाती हैं और पौधा सूखकर आसानी से खिंच जाता है।", symptomsEn = "Root damage causing wilting.", organicRemedyHi = "10 किग्रा नीम खली बुवाई के समय + बवेरिया बैसियाना 1 किग्रा गोबर में मिलाकर डालें।", chemicalControlHi = "क्लोरपायरीफॉस 20% EC @ 1 लीटर प्रति एकड़ सिंचाई पानी के साथ दें।")
        ),
        irrigationScheduleList = listOf("1. सीआरआई अवस्था (20-25 दिन) - सबसे महत्वपूर्ण", "2. कल्ले फूटते समय (40-45 दिन)", "3. गांठ बनते समय (60-65 दिन)", "4. फूल/बालियां निकलते समय (80-85 दिन)", "5. दाना भरते समय (100-105 दिन)"),
        financials = CropFinancials("22 - 26 क्विंटल / एकड़", "14,500 ₹ / एकड़", "58,000 ₹ (MSP ₹2,275/Q)", "43,500 ₹ / एकड़")
    )

    // 2. Mustard (सरसों)
    private val mustardDetail = CropAgronomyDetail(
        id = "mustard",
        nameEn = "Mustard",
        nameHi = "सरसों / राई",
        category = "oilseeds",
        categoryHi = "तिलहन",
        season = "rabi",
        seasonHi = "रबी",
        durationDays = 120,
        soilSuitability = "Sandy loam to clay loam with good drainage (pH 6.5 - 7.8)",
        soilSuitabilityHi = "बलुई दोमट से दोमट मिट्टी, जल भराव न हो (pH 6.5 - 7.8)",
        seedRate = "1.5 - 2.0 kg / एकड़",
        seedRateHi = "1.5 से 2.0 किग्रा प्रति एकड़ (बीज उपचारित)",
        spacing = "45 x 15 cm",
        spacingHi = "कतार 45 सेमी, पौधे की दूरी 15 सेमी",
        recommendedVarieties = listOf("Pusa Mustard 31 (PDZ-1)", "Pioneer 45S46", "Advanta Coral 432", "Giriraj (DRMRIJ-31)", "RH-725"),
        recommendedVarietiesHi = listOf("पूसा मस्टर्ड 31", "पायनियर 45S46 हाइब्रिड", "एडवांटा कोरल 432", "गिरिराज", "आरएच-725"),
        stages = listOf(
            CropStageInfo(1, "0-5 DAS", "बुवाई व अंकुरण", "Germination", "खेत में नमी होने पर 1.5-2 किग्रा बीज ट्राइकोडर्मा उपचारित कर बोएं।", "Sow treated seed at 3-4 cm depth in moisture."),
            CropStageInfo(2, "15-20 DAS", "विरलीकरण (Thinning)", "Thinning & Weeding", "अतिरिक्त घने पौधे उखाड़कर 15 सेमी की दूरी बनाएं। निराई-गुड़ाई करें।", "Thinning to maintain 15cm plant-to-plant spacing."),
            CropStageInfo(3, "30-35 DAS", "शाखाएं फूटना व पहली सिंचाई", "Branching Stage", "पहली सिंचाई फूल आने से ठीक पहले। यूरिया 30 किग्रा प्रति एकड़ दें।", "1st irrigation before flowering & Urea application."),
            CropStageInfo(4, "55-65 DAS", "पूर्ण फूल अवस्था", "Full Bloom", "इस समय सिंचाई न करें ताकि फूल न गिरें। मांहू कीट की निगरानी रखें।", "Scout for aphids; avoid flood irrigation."),
            CropStageInfo(5, "75-85 DAS", "फलियां दाना भराव (Siliqua)", "Pod Filling", "दूसरी हल्की सिंचाई यदि आवश्यकता हो। 0:0:50 @ 1 किग्रा स्प्रे तेल प्रतिशत बढ़ाने हेतु।", "2nd light irrigation & 0:0:50 spray for oil %."),
            CropStageInfo(6, "110-120 DAS", "परिपक्वता व कटाई", "Harvesting", "फलियां 75% पीली होने पर सुबह के समय कटाई करें ताकि दाने न झड़ें।", "Harvest in morning hours to prevent pod shattering.")
        ),
        fertigationSchedule = listOf(
            FertigationItem("Basal Sowing", "बुवाई के समय", "SSP 150 kg + MOP 20 kg + Sulphur 90% 10 kg / acre", "सिंगल सुपर फॉस्फेट (SSP): 150 किग्रा (सल्फर हेतु), पोटाश: 20 किग्रा, बेंटोनाइट सल्फर 90%: 10 किग्रा।", "घनजीवामृत 400 किग्रा + 20 किग्रा अरंडी खली।"),
            FertigationItem("30-35 DAS (1st Irrigation)", "पहली सिंचाई पर", "Urea 30 kg / acre", "यूरिया 30-35 किग्रा प्रति एकड़।", "जीवामृत 200 लीटर प्रति एकड़।"),
            FertigationItem("60 DAS (Flowering)", "फूल आते समय", "Boron 20% 150 g + NPK 19:19:19 1 kg", "बोरॉन 20% @ 150 ग्राम + एनपीके 19:19:19 @ 1 किग्रा प्रति एकड़ स्प्रे।", "दशपर्णी अर्क 5 लीटर स्प्रे।"),
            FertigationItem("80 DAS (Pod Filling)", "फली दाना भराव", "0:0:50 Potash 1 kg + Sulphur WDG 500 g", "एनपीके 0:0:50 @ 1 किग्रा + सल्फर 80% WDG @ 500 ग्राम स्प्रे (तेल मात्रा बढ़ाता है)।", "राख का पानी 10% स्प्रे।")
        ),
        pestDiseaseManagement = listOf(
            PestManagementItem("Mustard Aphid (Mahun)", "सरसों का चेपा / माहू (Aphids)", symptomsHi = "कोमल शाखाओं व फूलों पर लाखों कीट चिपककर रस चूसते हैं, चिपचिपा लिक्विड छोड़ते हैं।", symptomsEn = "Dense colonies of aphids covering shoots & inflorescence.", organicRemedyHi = "नीमास्त्र 5 लीटर + 150 लीटर पानी, या पीला चिपचिपा ट्रैप (Yellow Sticky Trap) 15 प्रति एकड़ लगाएं।", chemicalControlHi = "इमिडाक्लोप्रिड 17.8% SL @ 60 मिली या थियामेथोक्सम 25% WG @ 80 ग्राम प्रति 150 लीटर पानी।"),
            PestManagementItem("White Rust / Blight", "सफेद रतुआ व झुलसा (White Rust)", symptomsHi = "पत्तियों की निचली सतह पर सफेद उभरे हुए फफोले बनते हैं और फूल विकृत (staghead) हो जाते हैं।", symptomsEn = "White blister-like pustules under leaves.", organicRemedyHi = "खट्टी छाछ 5 लीटर + तांबे का बर्तन 5 दिन रखा हुआ घोल स्प्रे करें।", chemicalControlHi = "मैनकोजेब 75% WP @ 400 ग्राम + मेटालैक्सिल 8% @ 250 ग्राम प्रति 200 लीटर पानी।"),
            PestManagementItem("Sawfly", "आरा मक्खी (Sawfly)", symptomsHi = "काले रंग की सुंडी पत्तियों को किनारों से काटकर जालीनुमा बना देती है।", symptomsEn = "Black larvae defoliating young seedlings.", organicRemedyHi = "अग्निअस्त्र @ 3 लीटर प्रति 150 लीटर पानी स्प्रे।", chemicalControlHi = "डाइमेथोएट 30% EC @ 250 मिली प्रति 150 लीटर पानी।")
        ),
        irrigationScheduleList = listOf("1. बुवाई से पूर्व पलेवा (खेत तैयारी में)", "2. शाखाएं निकलते समय (30-35 दिन) - अति आवश्यक", "3. फली में दाना भरते समय (75-80 दिन) - हल्की सिंचाई"),
        financials = CropFinancials("10 - 14 क्विंटल / एकड़", "11,000 ₹ / एकड़", "60,000 ₹ (Mandi ₹5,500/Q)", "49,000 ₹ / एकड़")
    )

    // 3. Tomato Rabi (टमाटर)
    private val tomatoRabiDetail = CropAgronomyDetail(
        id = "tomato_rabi",
        nameEn = "Tomato (Rabi / Winter)",
        nameHi = "टमाटर (रबी / शरदकालीन)",
        category = "vegetables",
        categoryHi = "सब्जियां",
        season = "rabi",
        seasonHi = "रबी",
        durationDays = 140,
        soilSuitability = "Rich well-drained sandy loam or clay loam with pH 6.0 - 7.0",
        soilSuitabilityHi = "जीवांश युक्त दोमट भूमि, जल निकास उत्तम हो (pH 6.0 - 7.0)",
        seedRate = "60 - 80 ग्राम / एकड़ (हाइब्रिड पौध)",
        seedRateHi = "60 से 80 ग्राम प्रति एकड़ (प्रोट्रे में नर्सरी तैयार)",
        spacing = "60 x 45 cm (या ड्रिप बेड 90 x 45 cm)",
        spacingHi = "कतार 60 सेमी, पौधे की दूरी 45 सेमी (मल्चिंग पर)",
        recommendedVarieties = listOf("Seminis SV8931", "Syngenta Abhinav (TO-1057)", "Syngenta Saaho", "Namdhari NS-501", "Pusa Ruby"),
        recommendedVarietiesHi = listOf("सेमिनिस SV8931", "सिंजेंटा अभिनव (TO-1057)", "सिंजेंटा साहो", "नामधारी NS-501", "पूसा रूबी"),
        stages = listOf(
            CropStageInfo(1, "0-25 DAS", "नर्सरी व पौध तैयारी", "Nursery Stage", "प्रोट्रे में कोकोपीट + वर्मीकुलाइट में 25 दिन तक स्वस्थ पौध तैयार करें।", "Nursery raising in pro-trays with shade net."),
            CropStageInfo(2, "25-30 DAS", "खेत में रोपाई (Transplanting)", "Transplanting", "शाम के समय रोपाई करें। रोपाई के तुरंत बाद हल्की सिंचाई व 19:19:19 ड्रेंचिंग।", "Transplant 25-day seedlings on raised beds with mulch."),
            CropStageInfo(3, "45-55 DAS", "वानस्पतिक वृद्धि व बंधाई (Staking)", "Vegetative & Staking", "पौधों को बांस व सुतली से सहारा (Staking) दें। शाखाओं की छंटाई (Pruning)।", "Staking with bamboo poles & trellising."),
            CropStageInfo(4, "65-80 DAS", "फूल व फल सेटिंग", "Flowering & Fruit Set", "बोरॉन व कैल्शियम का छिड़काव ताकि फल फटने से बचें व फूल न गिरें।", "Foliar Boron & Calcium to prevent Blossom End Rot."),
            CropStageInfo(5, "90-140 DAS", "तुड़ाई व विपणन (Harvesting)", "Fruit Harvesting", "प्रत्येक 3-4 दिन में लाल पके टमाटरों की तुड़ाई (15-20 तुड़ाई चक्र)।", "Multiple pickings every 3-4 days at pink/red stage.")
        ),
        fertigationSchedule = listOf(
            FertigationItem("Basal Bed Prep", "बेड बनाते समय", "DAP 50 kg + MOP 50 kg + SSP 100 kg + Micronutrient Mix 10 kg", "डीएपी 50 किग्रा, पोटाश 50 किग्रा, एसएसपी 100 किग्रा, जिंक व बोरॉन 10 किग्रा।", "5-6 टन सड़ी गोबर खाद या 1 टन केंचुआ खाद।"),
            FertigationItem("10-30 DAT (Rooting & Growth)", "रोपाई के 10-30 दिन", "NPK 19:19:19 @ 3 kg/acre twice a week via Drip", "एनपीके 19:19:19 @ 3 किग्रा प्रति एकड़ सप्ताह में 2 बार ड्रिप द्वारा।", "जीवामृत 200 लीटर ड्रिप द्वारा।"),
            FertigationItem("35-60 DAT (Flowering)", "फूल आते समय (35-60 दिन)", "NPK 12:61:00 @ 3 kg + Boron 20% @ 250 g", "एनपीके 12:61:00 @ 3 किग्रा + बोरॉन 20% @ 250 ग्राम प्रति एकड़।", "अमृतपानी ड्रेंचिंग।"),
            FertigationItem("65-100 DAT (Fruiting)", "फल विकास के समय", "NPK 13:0:45 @ 4 kg + Calcium Nitrate @ 3 kg/acre", "एनपीके 13:0:45 @ 4 किग्रा + कैल्शियम नाइट्रेट @ 3 किग्रा (अलग ड्रेंचिंग)।", "दशपर्णी अर्क स्प्रे।"),
            FertigationItem("100+ DAT (Peak Pickings)", "तुड़ाई के दौरान", "NPK 0:0:50 @ 4 kg/acre weekly", "एनपीके 0:0:50 (पोटाश) @ 4 किग्रा प्रति एकड़ प्रति सप्ताह फल चमक व वजन हेतु।", "खट्टी छाछ स्प्रे।")
        ),
        pestDiseaseManagement = listOf(
            PestManagementItem("Fruit Borer (Helicoverpa)", "फल छेदक सुंडी (Fruit Borer)", symptomsHi = "सुंडी फल में गोल छेद करके अंदर का गूदा खाती है, फल सड़कर गिर जाता है।", symptomsEn = "Caterpillar bores into green and ripe fruits.", organicRemedyHi = "फेरोमोन ट्रैप (हेली-ल्यूर) 8 प्रति एकड़ लगाएं + ब्रह्मास्त्र 3 लीटर/150 लीटर पानी।", chemicalControlHi = "कोराजन (Chlorantraniliprole 18.5% SC) @ 60 मिली प्रति 150 लीटर पानी में स्प्रे करें।"),
            PestManagementItem("Early & Late Blight", "अगेती व पछेती झुलसा (Blight)", symptomsHi = "पत्तियों पर भूरे-काले छल्लेदार धब्बे (Target spots) बनते हैं व पत्तियां झुलस जाती हैं।", symptomsEn = "Concentric dark brown rings on foliage and stem lesions.", organicRemedyHi = "जादम सल्फर (JS) 200 मिली + जादम वेटिंग एजेंट (JWA) 300 मिली प्रति 100 लीटर पानी।", chemicalControlHi = "एक्रॉस / कस्टोडिया (Azoxystrobin + Difenoconazole) @ 200 मिली प्रति 200 लीटर पानी।"),
            PestManagementItem("Leaf Curl Virus (TLCV)", "पत्ती मरोड़ विषाणु (Leaf Curl)", symptomsHi = "पत्तियां ऊपर की ओर मुड़कर छोटी व पीली पड़ जाती हैं। सफेद मक्खी (Whitefly) द्वारा फैलता है।", symptomsEn = "Leaves curl upward, stunted bushy growth spread by whiteflies.", organicRemedyHi = "पीले चिपचिपे कार्ड 25 प्रति एकड़ + नीमास्त्र 5 लीटर/150L पानी।", chemicalControlHi = "डायफेंथियूरॉन 50% WP (पेगासस) @ 250 ग्राम प्रति 200 लीटर पानी में स्प्रे करें।")
        ),
        irrigationScheduleList = listOf("ड्रिप सिंचाई: प्रतिदिन या 1 दिन छोड़कर 1.5 - 2 घंटे (मौसम अनुसार)", "खुली नाली: रोपाई के बाद, फिर 6-8 दिन के अंतराल पर हल्की सिंचाई"),
        financials = CropFinancials("280 - 350 क्विंटल / एकड़", "48,000 ₹ / एकड़", "1,80,000 ₹ (Avg ₹600/Q)", "1,32,000 ₹ / एकड़")
    )

    // 4. Chickpea (चना)
    private val chickpeaDetail = CropAgronomyDetail(
        id = "chickpea",
        nameEn = "Chickpea / Gram",
        nameHi = "चना / देसी व काबुली",
        category = "pulses",
        categoryHi = "दालें",
        season = "rabi",
        seasonHi = "रबी",
        durationDays = 115,
        soilSuitability = "Sandy loam to medium black soil with good aeration (pH 6.0 - 7.5)",
        soilSuitabilityHi = "हल्की दोमट से मध्यम काली मिट्टी, हवादार व जल निकास युक्त",
        seedRate = "30 - 35 kg (Desi) / 45-50 kg (Kabuli) / एकड़",
        seedRateHi = "देसी चना: 30-35 किग्रा, काबुली: 45-50 किग्रा प्रति एकड़",
        spacing = "30 x 10 cm",
        spacingHi = "कतार 30 सेमी, पौधे की दूरी 10 सेमी",
        recommendedVarieties = listOf("Pusa 3022 (Kabuli)", "Pusa 2024", "Jaki 9218", "RVG 202", "DCP 92-3"),
        recommendedVarietiesHi = listOf("पूसा 3022 (काबुली)", "पूसा 2024", "जाकी 9218", "आरवीजी 202", "डीक्यूपी 92-3"),
        stages = listOf(
            CropStageInfo(1, "0-8 DAS", "बुवाई व अंकुरण", "Germination", "बीज को राइजोबियम व ट्राइकोडर्मा से उपचारित कर 8-10 सेमी गहराई पर बोएं।", "Seed treatment with Rhizobium & Trichoderma, sow at 8-10cm."),
            CropStageInfo(2, "30-35 DAS", "शिखर तुड़ाई (Nipping / Topping)", "Branching & Nipping", "पौधे का ऊपरी सिरा (2-3 सेमी) खोटने (Nipping) से 4 गुना अधिक शाखाएं फूटती हैं।", "Nipping of main shoot tip to trigger lateral branching."),
            CropStageInfo(3, "45-50 DAS", "शाखा विकास व पहली सिंचाई", "Pre-Flowering", "फूल आने से ठीक पहले हल्की सिंचाई (यदि वर्षा न हो)। एनपीके 19:19:19 स्प्रे।", "Light irrigation strictly before flowering."),
            CropStageInfo(4, "65-80 DAS", "फूल व घेंटी बनना (Podding)", "Pod Formation", "फूल अवस्था में सिंचाई न करें। घेंटी बनते समय बोरॉन @ 1g/L स्प्रे दाना भराव हेतु।", "Avoid irrigation during bloom; spray Boron at podding."),
            CropStageInfo(5, "105-115 DAS", "परिपक्वता व कटाई", "Harvesting", "पौधे व घंटियां भूरी-सुनहरी होने पर कटाई करें।", "Harvest when pods rattle and turn golden brown.")
        ),
        fertigationSchedule = listOf(
            FertigationItem("Basal Sowing", "बुवाई के समय", "DAP 35 kg + SSP 50 kg + MOP 15 kg / acre", "डीएपी 35 किग्रा, सिंगल सुपर फॉस्फेट 50 किग्रा, पोटाश 15 किग्रा (नाइट्रोजन कम दें)।", "घनजीवामृत 300 किग्रा प्रति एकड़।"),
            FertigationItem("35 DAS (Post Nipping)", "खोटने के बाद", "NPK 19:19:19 @ 1 kg / acre spray", "एनपीके 19:19:19 @ 1 किग्रा प्रति 150 लीटर पानी स्प्रे।", "जीवामृत स्प्रे 10%।"),
            FertigationItem("70 DAS (Podding)", "घेंटी बनते समय", "0:52:34 @ 1 kg + Boron 20% @ 150 g / acre", "एनपीके 0:52:34 @ 1 किग्रा + बोरॉन 20% @ 150 ग्राम प्रति एकड़ स्प्रे।", "राख का अर्क 5% स्प्रे।")
        ),
        pestDiseaseManagement = listOf(
            PestManagementItem("Pod Borer (Gheti Kida)", "चने की इल्ली / घेंटी छेदक (Pod Borer)", symptomsHi = "हरी-भूरी सुंडी घेंटी में सिर घुसाकर दाना चट कर जाती है।", symptomsEn = "Helicoverpa armigera caterpillar feeding inside pods.", organicRemedyHi = "टी-आकार की पक्षी खूंटियां (Bird perches) 20/एकड़ + फेरोमोन ट्रैप 6/एकड़ + अग्निअस्त्र 3L/150L पानी।", chemicalControlHi = "एमामेक्टिन बेंजोएट 5% SG @ 100 ग्राम या कोराजन @ 50 मिली प्रति 150 लीटर पानी।"),
            PestManagementItem("Wilt / Root Rot", "उकठा / उखटा रोग (Wilt)", symptomsHi = "पौधा अचानक मुरझाकर सूख जाता है, तना चीरने पर अंदर काली धारी दिखती है।", symptomsEn = "Fusarium wilt causing sudden vascular browning & wilting.", organicRemedyHi = "ट्राइकोडर्मा विरिडी 2 किग्रा को 100 किग्रा गोबर में मिलाकर बुवाई पूर्व खेत में डालें।", chemicalControlHi = "कार्बेन्डाजिम 50% WP @ 2 ग्राम प्रति किग्रा बीज उपचार।")
        ),
        irrigationScheduleList = listOf("1. बुवाई से पूर्व पलेवा (अति महत्वपूर्ण)", "2. फूल आने से पहले (45 दिन पर हल्की सिंचाई)", "3. घेंटी में दाना भरते समय (75 दिन पर) - आवश्यकतानुसार"),
        financials = CropFinancials("9 - 13 क्विंटल / एकड़", "10,500 ₹ / एकड़", "58,500 ₹ (Mandi ₹5,400/Q)", "48,000 ₹ / एकड़")
    )

    // 5. Potato (आलू)
    private val potatoDetail = CropAgronomyDetail(
        id = "potato",
        nameEn = "Potato",
        nameHi = "आलू",
        category = "vegetables",
        categoryHi = "सब्जियां",
        season = "rabi",
        seasonHi = "रबी",
        durationDays = 95,
        soilSuitability = "Loose friable sandy loam rich in organic matter (pH 5.2 - 6.8)",
        soilSuitabilityHi = "भुरभुरी बलुई दोमट भूमि, जीवांश से भरपूर (pH 5.2 - 6.8)",
        seedRate = "12 - 15 क्विंटल कंद / एकड़",
        seedRateHi = "12 से 15 क्विंटल मध्यम आकार (35-45 ग्राम) कंद प्रति एकड़",
        spacing = "60 x 20 cm",
        spacingHi = "मेड़ से मेड़ 60 सेमी, आलू से आलू 20 सेमी",
        recommendedVarieties = listOf("Kufri Pukhraj", "Kufri Jyoti", "Kufri Mohan", "Kufri Chipsona-1 (Processing)", "Kufri Bahar"),
        recommendedVarietiesHi = listOf("कुफरी पुखराज (अगेती)", "कुफरी ज्योति", "कुफरी मोहन", "कुफरी चिप्सोना-1", "कुफरी बहार"),
        stages = listOf(
            CropStageInfo(1, "0-10 DAS", "कंद तैयारी व बुवाई", "Sprouting & Planting", "कोल्ड स्टोरेज से निकले आलू को 5 दिन छायादार जगह रखें, अंकुरित कंदों की बुवाई करें।", "Plant sprouted seed tubers on ridges at 60x20 cm."),
            CropStageInfo(2, "20-25 DAS", "जमाव व पहली सिंचाई", "Emergence", "पौधे 10-15 सेमी ऊंचे होने पर पहली सिंचाई व खरपतवार नियंत्रण।", "First light irrigation & weeding."),
            CropStageInfo(3, "30-35 DAS", "मिट्टी चढ़ाना (Earthing Up)", "Earthing Up & Stolon", "यूरिया टॉप ड्रेसिंग देकर कंदों पर अच्छी तरह मिट्टी चढ़ाएं ताकि धूप से आलू हरा न हो।", "Earthing up ridges to protect developing tubers from sun."),
            CropStageInfo(4, "50-65 DAS", "कंद फुलाव (Tuber Bulking)", "Tuber Bulking", "कंदों के तेजी से मोटे होने की अवस्था। पोटाश व बोरॉन स्प्रे। नमी बनाए रखें।", "Critical tuber expansion; maintain moisture & 0:0:50 spray."),
            CropStageInfo(5, "85-95 DAS", "बेल काटना (Dehaulming) व खुदाई", "Maturity & Digging", "खुदाई से 10 दिन पहले ऊपर की बेल काट दें ताकि आलू का छिलका मजबूत हो जाए।", "Dehaulming 10-12 days before digging to harden tuber skin.")
        ),
        fertigationSchedule = listOf(
            FertigationItem("Basal Planting", "बुवाई के समय", "NPK 12:32:16 100 kg + MOP 50 kg + Zinc 10 kg / acre", "एनपीके 12:32:16: 100 किग्रा, पोटाश (MOP): 50 किग्रा, जिंक सल्फेट: 10 किग्रा प्रति एकड़।", "6-8 टन सड़ी गोबर खाद या 1.5 टन वर्मीकम्पोस्ट।"),
            FertigationItem("30 DAS (Earthing Up)", "मिट्टी चढ़ाते समय", "Urea 45 kg / acre", "यूरिया 45 किग्रा प्रति एकड़ मेड़ पर डालकर मिट्टी चढ़ाएं।", "जीवामृत 200 लीटर प्रति एकड़।"),
            FertigationItem("50 DAS (Bulking)", "कंद बनते समय", "NPK 0:52:34 @ 1 kg + Boron 20% @ 200 g / acre", "एनपीके 0:52:34 @ 1 किग्रा + बोरॉन 20% @ 200 ग्राम प्रति 150 लीटर पानी।", "दशपर्णी अर्क स्प्रे।"),
            FertigationItem("65 DAS (Tuber Expansion)", "कंद फुलाव के समय", "NPK 0:0:50 @ 1.5 kg / acre", "एनपीके 0:0:50 (सल्फेट ऑफ पोटाश) @ 1.5 किग्रा प्रति 150 लीटर पानी स्प्रे।", "राख का घोल स्प्रे।")
        ),
        pestDiseaseManagement = listOf(
            PestManagementItem("Late Blight (Picheti Jhulsa)", "पछेती झुलसा (Late Blight)", symptomsHi = "पत्तियों पर जल-भीगे (water-soaked) काले धब्बे, निचली सतह पर सफेद फफूंद, आलू सड़ जाता है।", symptomsEn = "Phytophthora infestans causing water-soaked foliar rot and tuber decay.", organicRemedyHi = "जादम सल्फर (JS) 200 मिली + जादम वेटिंग एजेंट (JWA) 300 मिली प्रति 100 लीटर पानी।", chemicalControlHi = "साइमोक्सानिल 8% + मैनकोजेब 64% (कर्जेट) @ 400 ग्राम या सेक्टिन @ 300 ग्राम प्रति 150L पानी।"),
            PestManagementItem("Aphids (Vectors of Virus)", "माहू / चेपा (Aphids)", symptomsHi = "पत्तियों से रस चूसते हैं और आलू में विषाणु रोग फैलाते हैं।", symptomsEn = "Green peach aphids spreading leaf roll virus.", organicRemedyHi = "नीमास्त्र 5 लीटर प्रति 150 लीटर पानी।", chemicalControlHi = "इमिडाक्लोप्रिड 17.8% SL @ 50 मिली प्रति 150 लीटर पानी।")
        ),
        irrigationScheduleList = listOf("1. बुवाई के 5-7 दिन बाद पहली हल्की सिंचाई", "2. मिट्टी चढ़ाने के बाद (30 दिन पर)", "3. कंद बनते व फूलते समय (45, 60, 75 दिन) - हर 8-10 दिन पर हल्की सिंचाई"),
        financials = CropFinancials("130 - 160 क्विंटल / एकड़", "38,000 ₹ / एकड़", "1,20,000 ₹ (Mandi ₹850/Q)", "82,000 ₹ / एकड़")
    )

    // 6. Paddy Basmati (बासमती धान)
    private val paddyBasmatiDetail = CropAgronomyDetail(
        id = "paddy_basmati",
        nameEn = "Basmati Rice",
        nameHi = "बासमती धान",
        category = "grains",
        categoryHi = "अनाज",
        season = "kharif",
        seasonHi = "खरीफ",
        durationDays = 135,
        soilSuitability = "Clay loam to heavy clay soil retaining standing water (pH 6.5 - 8.0)",
        soilSuitabilityHi = "मटियार दोमट से चिकनी मिट्टी जिसमें पानी रोकने की क्षमता हो",
        seedRate = "5 - 6 kg / एकड़ (नर्सरी हेतु)",
        seedRateHi = "5 से 6 किग्रा प्रति एकड़ (1/10 एकड़ में नर्सरी तैयार)",
        spacing = "20 x 15 cm",
        spacingHi = "कतार से कतार 20 सेमी, पौधे से पौधा 15 सेमी (2-3 पौधे प्रति थान)",
        recommendedVarieties = listOf("Pusa Basmati 1847", "Pusa Basmati 1885", "Pusa Basmati 1718", "Pusa Basmati 1121", "Pusa Basmati 1509"),
        recommendedVarietiesHi = listOf("पूसा बासमती 1847 (झुलसा रोधी)", "पूसा बासमती 1885 (ब्लास्ट रोधी)", "पूसा बासमती 1718", "पूसा बासमती 1121", "पूसा बासमती 1509 (अगेती)"),
        stages = listOf(
            CropStageInfo(1, "0-25 DAS", "नर्सरी व बीज उपचार", "Nursery Stage", "बीज को नमक के पानी (10%) में डुबोकर हल्के बीज निकालें, ट्राइकोडर्मा से उपचार कर नर्सरी डालें।", "Nursery raising with seed treatment."),
            CropStageInfo(2, "25-30 DAS", "लेव लगाना व रोपाई (Puddling & Transplant)", "Transplanting", "खेत में पानी भरकर 2-3 बार जुताई (कद्दू/लेव) करें व 25 दिन की पौध 20x15 सेमी पर रोपें।", "Puddling & transplanting 2-3 seedlings per hill."),
            CropStageInfo(3, "45-55 DAT", "कल्ले फूटना (Active Tillering)", "Tillering", "खेत में 2-3 सेमी पानी रखें। यूरिया व जिंक टॉप ड्रेसिंग करें।", "Maintain shallow water depth & apply Zinc/Urea."),
            CropStageInfo(4, "75-85 DAT", "गोभ / बाली निकलना (Panicle Initiation)", "Panicle Initiation", "बाली निकलते समय खेत में पानी की कमी न होने दें। 0:52:34 स्प्रे।", "Critical moisture stage during heading."),
            CropStageInfo(5, "115-135 DAT", "दाना पकना व कटाई", "Maturity & Harvest", "बालियां 85% सुनहरी होने पर कटाई से 10 दिन पूर्व पानी निकाल दें व कटाई करें।", "Drain field 10 days before harvest when 85% grains turn golden.")
        ),
        fertigationSchedule = listOf(
            FertigationItem("Basal Puddling", "कद्दू / लेव लगाते समय", "DAP 35 kg + MOP 25 kg + Zinc Sulphate 21% 15 kg / acre", "डीएपी 35 किग्रा, पोटाश 25 किग्रा, जिंक सल्फेट 21%: 15 किग्रा प्रति एकड़।", "घनजीवामृत 400 किग्रा + हरी खाद (ढैंचा) पलटें।"),
            FertigationItem("20-25 DAT (Tillering)", "रोपाई के 20-25 दिन", "Urea 30 kg / acre", "यूरिया 30-35 किग्रा प्रति एकड़।", "जीवामृत 200 लीटर पानी के साथ।"),
            FertigationItem("45-50 DAT (Panicle)", "गोभ अवस्था (45 दिन)", "Urea 20 kg + NPK 19:19:19 @ 1 kg spray", "यूरिया 20 किग्रा + 19:19:19 @ 1 किग्रा प्रति एकड़ स्प्रे।", "दशपर्णी अर्क स्प्रे।"),
            FertigationItem("75 DAT (Heading)", "बालियां आते समय", "NPK 0:52:34 @ 1 kg + Boron 20% @ 150 g / acre", "एनपीके 0:52:34 @ 1 किग्रा + बोरॉन @ 150 ग्राम प्रति 150 लीटर पानी स्प्रे।", "खट्टी छाछ स्प्रे।")
        ),
        pestDiseaseManagement = listOf(
            PestManagementItem("Bacterial Leaf Blight (BLB)", "जीवाणु झुलसा (BLB)", symptomsHi = "पत्तियों के किनारों से पीले-सफेद लहरदार धब्बे सूखते हुए नीचे की ओर बढ़ते हैं।", symptomsEn = "Yellow wavy margins on leaves turning straw coloured.", organicRemedyHi = "खट्टी छाछ 5 लीटर + तांबा बर्तन अर्क + 200 ग्राम हींग स्प्रे।", chemicalControlHi = "स्ट्रेप्टोसाइक्लिन 12 ग्राम + कॉपर ऑक्सीक्लोराइड 500 ग्राम प्रति 200 लीटर पानी।"),
            PestManagementItem("Stem Borer (Tana Chhedak)", "तना छेदक / सफेद बाली (Stem Borer)", symptomsHi = "वानस्पतिक अवस्था में डेड हार्ट (बीच की पत्ती सूखना) व बाली अवस्था में सफेद बालियां (White Earhead) बनती हैं।", symptomsEn = "Dead heart in vegetative stage, white empty ears at maturity.", organicRemedyHi = "ट्राइकोग्रामा कार्ड 5 प्रति एकड़ + प्रकाश प्रपंच (Light trap) लगाएं।", chemicalControlHi = "कार्टाप हाइड्रोक्लोराइड 4% G @ 7.5 किग्रा प्रति एकड़ या कोराजन @ 60 मिली स्प्रे।"),
            PestManagementItem("False Smut (Haldia Rog)", "हल्दी रोग / कंडुआ (False Smut)", symptomsHi = "दाने पीले-हरे मखमली हल्दी जैसे बड़े गोलों में बदल जाते हैं।", symptomsEn = "Grains transform into yellow-green velvety spore balls.", organicRemedyHi = "ट्राइकोडर्मा विरिडी 1 किग्रा/एकड़ बाली निकलने से पूर्व स्प्रे।", chemicalControlHi = "प्रोपिकोनाजोल 25% EC @ 200 मिली प्रति 200 लीटर पानी में गोभ अवस्था पर स्प्रे करें।")
        ),
        irrigationScheduleList = listOf("1. रोपाई के पहले सप्ताह 4-5 सेमी पानी स्थिर रखें", "2. कल्ले फूटते समय (20-40 दिन) 2-3 सेमी पानी", "3. गोभ व बाली निकलते समय (60-80 दिन) - पानी की कमी कभी न होने दें", "4. कटाई से 10 दिन पूर्व पानी पूरी तरह निकाल दें"),
        financials = CropFinancials("18 - 24 क्विंटल / एकड़", "22,000 ₹ / एकड़", "82,000 ₹ (Mandi ₹3,800/Q)", "60,000 ₹ / एकड़")
    )

    // 7. Watermelon Zaid (तरबूज)
    private val watermelonDetail = CropAgronomyDetail(
        id = "watermelon",
        nameEn = "Watermelon (Zaid / Summer)",
        nameHi = "तरबूज (जायद / ग्रीष्मकालीन)",
        category = "fruits",
        categoryHi = "फल व बागवानी",
        season = "zaid",
        seasonHi = "जायद",
        durationDays = 85,
        soilSuitability = "Sandy loam to alluvial river bed soil with high warmth (pH 6.5 - 7.5)",
        soilSuitabilityHi = "रेतीली दोमट या कछारी भूमि, अच्छी धूप व जल निकास युक्त",
        seedRate = "1.2 - 1.5 kg / एकड़",
        seedRateHi = "1.2 से 1.5 किग्रा प्रति एकड़ (हाइब्रिड ब्लैक/स्ट्राइप्ड)",
        spacing = "2.0 x 0.6 m (बेड विधि)",
        spacingHi = "बेड से बेड 2 मीटर, पौधे से पौधा 60 सेमी (सिल्वर मल्चिंग पर)",
        recommendedVarieties = listOf("Sakata Madhubala", "Syngenta Sugar Queen", "Seminis Bahubali", "Known-You Black Boy", "Pusa Bedana"),
        recommendedVarietiesHi = listOf("साकाटा मधुबाला", "सिंजेंटा शुगर क्वीन", "सेमिनिस बाहुबली", "नोन-यू ब्लैक बॉय", "पूसा बेदाना (बीजरहित)"),
        stages = listOf(
            CropStageInfo(1, "0-6 DAS", "बुवाई व अंकुरण", "Germination", "बीज को गुनगुने पानी में 12 घंटे भिगोकर मल्चिंग बेड के छेदों में 2-3 सेमी गहराई पर बोएं।", "Sow pre-soaked seeds in mulch hole pockets with drip."),
            CropStageInfo(2, "15-20 DAS", "बेल वृद्धि (Vine Running)", "Vine Development", "3-4 पत्ती अवस्था, ड्रिप द्वारा 19:19:19 व ह्यूमिक एसिड चलाएं।", "Fertigation with 19:19:19 & Humic acid for root spread."),
            CropStageInfo(3, "35-45 DAS", "फूल व परागण (Pollination)", "Flowering", "नर व मादा फूल खिलते हैं। मधुमक्खियों के परागण हेतु सुबह कीटनाशक स्प्रे न करें।", "Avoid spraying pesticides in morning hours to save bees."),
            CropStageInfo(4, "50-65 DAS", "फल विकास (Fruit Bulking)", "Fruit Development", "फल तेजी से बड़ा होता है। कैल्शियम + बोरॉन + 13:0:45 ड्रिप द्वारा दें ताकि मिठास बढ़े।", "Calcium Nitrate & 13:0:45 fertigation for fruit weight & Brix."),
            CropStageInfo(5, "75-85 DAS", "परिपक्वता व तुड़ाई", "Harvesting", "फल का निचला हिस्सा पीला पड़ना व डंठल का छल्ला सूखना परिपक्वता का पक्का संकेत है।", "Harvest when ground spot turns yellow and tendril dries.")
        ),
        fertigationSchedule = listOf(
            FertigationItem("Basal Bed Prep", "बेड बनाते समय", "DAP 50 kg + MOP 40 kg + SSP 50 kg + Magnesium Sulphate 10 kg", "डीएपी 50 किग्रा, पोटाश 40 किग्रा, मैग्नीशियम सल्फेट 10 किग्रा प्रति एकड़।", "सड़ी गोबर खाद 4 टन + नीम खली 100 किग्रा।"),
            FertigationItem("10-30 DAS (Vine Growth)", "बेल वृद्धि अवस्था", "NPK 19:19:19 @ 3 kg/acre twice a week via Drip", "एनपीके 19:19:19 @ 3 किग्रा प्रति एकड़ प्रति सप्ताह 2 बार।", "जीवामृत 200 लीटर ड्रिप द्वारा।"),
            FertigationItem("35-50 DAS (Flowering)", "फूल व फल सेट", "NPK 12:61:00 @ 3 kg + Boron 20% @ 250 g", "एनपीके 12:61:00 (मोनो अमोनियम फॉस्फेट) @ 3 किग्रा + बोरॉन @ 250 ग्राम।", "अमृतपानी ड्रेंचिंग।"),
            FertigationItem("55-75 DAS (Fruit Sizing)", "फल विकास व मिठास", "NPK 13:0:45 @ 4 kg + Calcium Nitrate @ 3 kg/acre", "एनपीके 13:0:45 (पोटैशियम नाइट्रेट) @ 4 किग्रा + कैल्शियम नाइट्रेट 3 किग्रा।", "दशपर्णी अर्क स्प्रे।"),
            FertigationItem("75+ DAS (Final Ripening)", "तुड़ाई पूर्व", "NPK 0:0:50 @ 4 kg/acre", "एनपीके 0:0:50 @ 4 किग्रा प्रति एकड़ पानी बंद करने से 5 दिन पूर्व।", "राख का अर्क।")
        ),
        pestDiseaseManagement = listOf(
            PestManagementItem("Fruit Fly (Phal Makhi)", "फल मक्खी (Fruit Fly)", symptomsHi = "मक्खी फल में डंक मारकर अंडा देती है, अंदर कीड़े पड़ जाते हैं और फल सड़ जाता है।", symptomsEn = "Bactrocera cucurbitae ovipositing in young melons causing rotting.", organicRemedyHi = "क्यू-ल्यूर (Fruit fly trap) 8 प्रति एकड़ लगाएं + नीम तेल स्प्रे।", chemicalControlHi = "मैलाथियान 50% EC @ 300 मिली + 500 ग्राम गुड़ प्रति 150 लीटर पानी में विष चुग्गा (Bait spray) बनाएं।"),
            PestManagementItem("Downy Mildew", "डाउनी मिल्ड्यू / रोमिल फफूंद", symptomsHi = "पत्तियों पर नसों के बीच पीले कोणीय धब्बे (Angular spots) व निचली सतह पर बैगनी फफूंद।", symptomsEn = "Yellow angular lesions bounded by leaf veins with purplish down underneath.", organicRemedyHi = "जादम सल्फर 200 मिली + जादम वेटिंग एजेंट 300 मिली प्रति 100 लीटर पानी।", chemicalControlHi = "एमिस्टार टॉप (Azoxystrobin + Difenoconazole) @ 200 मिली या प्रोफाइलर @ 400 ग्राम प्रति 200L पानी।")
        ),
        irrigationScheduleList = listOf("ड्रिप सिंचाई: गर्मियों में प्रतिदिन सुबह 1.5 - 2 घंटे", "तुड़ाई से 5 दिन पहले पानी कम कर दें ताकि मिठास (TSS Brix) बढ़ सके"),
        financials = CropFinancials("200 - 260 क्विंटल / एकड़", "35,000 ₹ / एकड़", "1,35,000 ₹ (Mandi ₹600/Q)", "1,00,000 ₹ / एकड़")
    )

    // 8. Mango Orchard (आम का बाग)
    private val mangoDetail = CropAgronomyDetail(
        id = "mango",
        nameEn = "Mango Orchard",
        nameHi = "आम का बाग (फल एवं बागवानी)",
        category = "fruits",
        categoryHi = "फल व बागवानी",
        season = "perennial",
        seasonHi = "बारहमासी",
        durationDays = 365,
        soilSuitability = "Deep rich alluvial or red loamy soil with depth > 2 meters (pH 5.5 - 7.5)",
        soilSuitabilityHi = "गहरी दोमट या जलोढ़ मिट्टी, 2 मीटर गहराई तक कंकड़ पत्थर न हों",
        seedRate = "40 - 50 पौधे / एकड़ (पारंपरिक 10x10m) या 100 पौधे (सघन 6x6m)",
        seedRateHi = "40-50 कलमी पौधे प्रति एकड़ (पारंपरिक) या 100 पौधे (सघन बागवानी)",
        spacing = "8 x 8 m या 6 x 6 m",
        spacingHi = "कतार से कतार 8 मीटर, पौधे से पौधा 8 मीटर",
        recommendedVarieties = listOf("Amrapali (High Density)", "Dasheri (Malihabad)", "Langra", "Chausa", "Mallika", "Kesar"),
        recommendedVarietiesHi = listOf("आम्रपाली (सघन बागवानी हेतु सर्वोत्तम)", "दशहरी (मलिहाबादी)", "लंगड़ा", "चौसा", "मल्लिका", "केसर"),
        stages = listOf(
            CropStageInfo(1, "अक्टूबर - नवंबर", "शाखा छंटाई व आराम अवस्था", "Post Harvest Care", "पुरानी सूखी व रोगग्रस्त टहनियों की कटाई-छंटाई (Pruning)। थाले बनाकर खाद देना।", "Pruning dead branches, basin preparation & basal manuring."),
            CropStageInfo(2, "दिसंबर - जनवरी", "फूल कली विभेदन (Bud Break)", "Floral Bud Emergence", "इस समय सिंचाई बिल्कुल बंद रखें ताकि वानस्पतिक वृद्धि रुककर बौर (Inflorescence) निकले।", "Withhold irrigation to encourage flower bud induction."),
            CropStageInfo(3, "फरवरी - मार्च", "बौर व पूर्ण फूल अवस्था", "Full Bloom", "बौर पर भुनगा (Hopper) व चूर्णी फफूंद (Powdery Mildew) की कड़ी निगरानी रखें।", "Scout for hoppers and powdery mildew; protect pollinators."),
            CropStageInfo(4, "मार्च - अप्रैल", "फल सेटिंग व मटर अवस्था (Pea Stage)", "Fruit Set", "फल मटर के दाने जितने होने पर पहली सिंचाई व बोरॉन + एनपीके 13:0:45 स्प्रे।", "First irrigation at pea stage & foliar nutrient sprays."),
            CropStageInfo(5, "मई - जुलाई", "फल परिपक्वता व तुड़ाई (Harvesting)", "Harvesting", "फल का गद्दार होना व रंग बदलना। डंठल सहित 8-10 मिमी डंडी रखकर तुड़ाई।", "Harvest with 8-10mm pedicel using mechanical harvesters.")
        ),
        fertigationSchedule = listOf(
            FertigationItem("October (Basal Manuring)", "अक्टूबर (बेसल खाद थाले में)", "FYM 50 kg + DAP 1 kg + Potash 1 kg + SSP 2 kg / tree (for 10+ yr tree)", "सड़ी गोबर खाद 50 किग्रा, डीएपी 1 किग्रा, पोटाश 1 किग्रा, एसएसपी 2 किग्रा प्रति वयस्क पेड़।", "घनजीवामृत 5 किग्रा + 2 किग्रा नीम खली प्रति पेड़ थाले में।"),
            FertigationItem("Feb (Bud Emergence)", "बौर निकलते समय", "Urea 500g / tree + Boron 20% 1.5 g/L spray", "यूरिया 500 ग्राम प्रति पेड़ + बोरॉन 20% @ 1.5 ग्राम प्रति लीटर पानी स्प्रे।", "जीवामृत 20 लीटर प्रति पेड़ थाले में।"),
            FertigationItem("March (Pea Stage Fruit)", "मटर दाना अवस्था", "NPK 13:0:45 @ 5 g/L + Planofix 1 ml / 4.5 L (फल झड़ने से रोके)", "एनपीके 13:0:45 @ 5 ग्राम प्रति लीटर + प्लानोफिक्स 1 मिली/4.5 लीटर पानी स्प्रे।", "दशपर्णी अर्क स्प्रे।"),
            FertigationItem("April (Marble Stage)", "कंचे आकार अवस्था", "NPK 0:0:50 @ 5 g/L + Potassium Schoenite", "एनपीके 0:0:50 @ 5 ग्राम प्रति लीटर पानी स्प्रे फल का वजन व मिठास बढ़ाने हेतु।", "खट्टी छाछ स्प्रे।")
        ),
        pestDiseaseManagement = listOf(
            PestManagementItem("Mango Hopper (Bhurra / Bhunga)", "आम का भुनगा / चेपा (Mango Hopper)", symptomsHi = "हजारों छोटे भुनगे बौर व पत्तियों का रस चूसते हैं, बौर पर चिपचिपा शहद जैसा पदार्थ निकलता है और बौर सूख जाता है।", symptomsEn = "Amritodus atkinsoni nymphs & adults sucking sap from panicles causing blossom drop.", organicRemedyHi = "नीमास्त्र 5 लीटर + जादम वेटिंग एजेंट 200 मिली प्रति 150 लीटर पानी।", chemicalControlHi = "इमिडाक्लोप्रिड 17.8% SL @ 1 मिली प्रति 3 लीटर पानी या थायोमेथोक्सम 25% WG @ 1 ग्राम/3L पानी।"),
            PestManagementItem("Powdery Mildew (Kharra Rog)", "चूर्णी फफूंद (Powdery Mildew)", symptomsHi = "बौर व छोटे फलों पर सफेद पाउडर जैसा चूर्ण छा जाता है और बौर जलकर काला हो जाता है।", symptomsEn = "White powdery fungal growth covering inflorescence and tiny fruits.", organicRemedyHi = "जादम सल्फर (JS) 200 मिली प्रति 100 लीटर पानी या खट्टी छाछ 5%।", chemicalControlHi = "घुलनशील गंधक (Wettable Sulphur 80% WDG) @ 2 ग्राम प्रति लीटर या हेक्साकोनाजोल 5% SC @ 1.5 मिली/लीटर।"),
            PestManagementItem("Fruit Fly (Bactrocera dorsalis)", "फल मक्खी (Fruit Fly)", symptomsHi = "पके आमों में डंक मारकर कीड़े पैदा करती है, आम अंदर से सड़ जाता है।", symptomsEn = "Bactrocera dorsalis maggots feeding inside pulp of mature fruits.", organicRemedyHi = "मिथाइल यूजेनॉल ट्रैप 10 प्रति एकड़ बाग में लटकाएं।", chemicalControlHi = "मैलाथियान 50% EC @ 2 मिली + 20 ग्राम गुड़ प्रति लीटर पानी का छिड़काव।")
        ),
        irrigationScheduleList = listOf("1. बौर आने से पहले (दिसंबर-जनवरी) सिंचाई बिल्कुल न करें", "2. फल मटर दाने के आकार का होने पर पहली सिंचाई करें", "3. अप्रैल-मई में फल विकास के दौरान हर 10-12 दिन में थाला सिंचाई करें", "4. तुड़ाई से 15 दिन पूर्व पानी बंद कर दें"),
        financials = CropFinancials("50 - 80 क्विंटल / एकड़", "28,000 ₹ / एकड़ / वर्ष", "1,80,000 ₹ (Mandi ₹3,000/Q)", "1,52,000 ₹ / एकड़")
    )

    // 9. Papaya (पपीता)
    private val papayaDetail = CropAgronomyDetail(
        id = "papaya",
        nameEn = "Papaya (Red Lady 786)",
        nameHi = "पपीता (रेड लेडी 786)",
        category = "fruits",
        categoryHi = "फल व बागवानी",
        season = "perennial",
        seasonHi = "बारहमासी",
        durationDays = 270,
        soilSuitability = "Well-drained rich sandy loam, highly sensitive to waterlogging (pH 6.5 - 7.5)",
        soilSuitabilityHi = "उत्तम जल निकास वाली बलुई दोमट, 1 घंटे भी जल भराव न हो",
        seedRate = "20 - 25 ग्राम / एकड़ (900-1000 पौधे)",
        seedRateHi = "20-25 ग्राम प्रति एकड़ (प्रोट्रे नर्सरी में तैयार 1000 पौधे)",
        spacing = "1.8 x 1.8 m (6 x 6 फीट)",
        spacingHi = "कतार से कतार 6 फीट, पौधे से पौधा 6 फीट",
        recommendedVarieties = listOf("Taiwan 786 (Red Lady)", "Pusa Delicious", "Pusa Dwarf", "Arka Prabhat", "Coorg Honey Dew"),
        recommendedVarietiesHi = listOf("ताइवान 786 (रेड लेडी हाइब्रिड - उभयलिंगी)", "पूसा ड्वार्फ", "पूसा डिलीशियस", "अर्का प्रभात", "कूर्ग हनी ड्यू"),
        stages = listOf(
            CropStageInfo(1, "0-45 DAS", "नर्सरी पौध तैयार करना", "Nursery", "पॉलीथीन बैग या प्रोट्रे में 45 दिन तक वायरस-मुक्त नेट हाउस में पौध तैयार करें।", "Raise 45-day sturdy seedlings inside insect-proof net house."),
            CropStageInfo(2, "45-60 DAS", "खेत में रोपाई (Transplanting)", "Transplanting", "ऊंचे उठी क्यारियों/बेड पर 6x6 फीट पर रोपाई करें व थाले में मिट्टी ऊंची रखें।", "Transplant on raised mounds to prevent collar rot."),
            CropStageInfo(3, "90-120 DAT", "वानस्पतिक वृद्धि व फूल आना", "Flowering Stage", "रोपाई के 3-4 माह बाद फूल आना शुरू। मादा व उभयलिंगी (Hermaphrodite) पौधों की पहचान।", "First flower flush at 3-4 months; scout for PRSV aphids."),
            CropStageInfo(4, "150-200 DAT", "फल विकास व वजन बढ़ना", "Fruit Sizing", "तने पर नीचे से ऊपर तक फलों की लड़ियां लगती हैं। पोटैशियम व सूक्ष्म पोषक ड्रिप से दें।", "Fruit cluster expansion; supply high Potassium via fertigation."),
            CropStageInfo(5, "240-300 DAT", "फलों की नियमित तुड़ाई (Harvesting)", "Continuous Harvest", "फल पर पीली धारियां दिखने पर तुड़ाई। प्रति पौधा 40-70 किग्रा फल उत्पादन।", "Pick fruits when slight yellow streak appears at apex.")
        ),
        fertigationSchedule = listOf(
            FertigationItem("Basal Pit Prep", "गड्ढा भरते समय", "FYM 10 kg + SSP 250 g + Neem Cake 500 g + Trichoderma 50 g / pit", "सड़ी गोबर खाद 10 किग्रा, एसएसपी 250 ग्राम, नीम खली 500 ग्राम, ट्राइकोडर्मा 50 ग्राम प्रति गड्ढा।", "घनजीवामृत 1 किग्रा प्रति गड्ढा।"),
            FertigationItem("1-3 Months (Vegetative)", "रोपाई के 1-3 माह", "NPK 19:19:19 @ 3 kg/acre weekly via Drip", "एनपीके 19:19:19 @ 3 किग्रा प्रति एकड़ प्रति सप्ताह ड्रिप द्वारा।", "जीवामृत 200 लीटर प्रति एकड़ ड्रिप से।"),
            FertigationItem("4-6 Months (Flowering)", "फूल व फल सेटिंग", "NPK 12:61:00 @ 3 kg + 13:0:45 @ 3 kg + Boron 200 g weekly", "एनपीके 12:61:00 @ 3 किग्रा + 13:0:45 @ 3 किग्रा + बोरॉन 200 ग्राम प्रति सप्ताह।", "अमृतपानी ड्रेंचिंग।"),
            FertigationItem("7-10 Months (Fruit Bulking)", "फल विकास व तुड़ाई", "NPK 0:0:50 @ 4 kg + Calcium Nitrate @ 3 kg/acre weekly", "एनपीके 0:0:50 @ 4 किग्रा + कैल्शियम नाइट्रेट 3 किग्रा (अलग-अलग दिन)।", "दशपर्णी अर्क स्प्रे।")
        ),
        pestDiseaseManagement = listOf(
            PestManagementItem("Papaya Ring Spot Virus (PRSV)", "पपीता रिंग स्पॉट वायरस (PRSV)", symptomsHi = "पत्तियों पर पीला मोज़ेक, गहरे हरे छल्ले (Rings) व तने/फल पर पानी जैसे गोल छल्ले बनते हैं।", symptomsEn = "Severe mosaic, distorted shoestring leaves and oily water-soaked rings on fruit.", organicRemedyHi = "खेत के चारों तरफ मक्का/ज्वार की 3 कतारें बॉर्डर क्रॉप लगाएं + नीमास्त्र 5% स्प्रे।", chemicalControlHi = "विषाणु फैलाने वाले एफिड (माहू) के नियंत्रण हेतु डायमेथोएट 30% EC @ 2 मिली/लीटर स्प्रे।"),
            PestManagementItem("Collar Rot / Root Rot (Gala Rog)", "तना गलन / कॉलर रॉट (Collar Rot)", symptomsHi = "तने का जमीन से सटा हिस्सा सड़ जाता है, पौधा हवा से टूटकर गिर जाता है।", symptomsEn = "Pythium aphanidermatum rotting stem collar at soil line.", organicRemedyHi = "पौधे के तने पर मिट्टी चढ़ाएं ताकि पानी तने को न छुए + ट्राइकोडर्मा विरिडी 50 ग्राम/पौधा ड्रेंचिंग।", chemicalControlHi = "रिडोमिल गोल्ड (Metalaxyl + Mancozeb) @ 2.5 ग्राम प्रति लीटर पानी से तने के पास ड्रेंचिंग करें।")
        ),
        irrigationScheduleList = listOf("ड्रिप सिंचाई: प्रतिदिन 1-1.5 घंटे (तने से 15 सेमी दूर ड्रिपर रखें)", "जल भराव से तुरंत 100% पौधों में कॉलर रॉट आ सकता है, निकास अनिवार्य रखें"),
        financials = CropFinancials("350 - 450 क्विंटल / एकड़", "65,000 ₹ / एकड़", "2,80,000 ₹ (Mandi ₹700/Q)", "2,15,000 ₹ / एकड़")
    )

    // 10. Chilli (मिर्च)
    private val chilliRabiDetail = CropAgronomyDetail(
        id = "chilli_rabi",
        nameEn = "Chilli (Green & Dry)",
        nameHi = "हरी मिर्च व लाल मिर्च",
        category = "vegetables",
        categoryHi = "सब्जियां",
        season = "rabi",
        seasonHi = "रबी",
        durationDays = 150,
        soilSuitability = "Well-drained loamy soil rich in organic matter (pH 6.5 - 7.5)",
        soilSuitabilityHi = "जीवांश युक्त दोमट भूमि, जल भराव से मुक्त",
        seedRate = "80 - 100 ग्राम / एकड़",
        seedRateHi = "80 से 100 ग्राम प्रति एकड़ (हाइब्रिड पौध)",
        spacing = "60 x 45 cm",
        spacingHi = "कतार 60 सेमी, पौधे की दूरी 45 सेमी",
        recommendedVarieties = listOf("Syngenta Hot Pepper HPH-5531", "Seminis Sitara", "VNR Sunidhi", "Advanta AK-47", "Pusa Jwala"),
        recommendedVarietiesHi = listOf("सिंजेंटा HPH-5531", "सेमिनिस सितारा", "वीएनआर सुनीधि", "एडवांटा AK-47", "पूसा ज्वाला"),
        stages = listOf(
            CropStageInfo(1, "0-30 DAS", "नर्सरी में पौध तैयार करना", "Nursery", "प्रोट्रे में 30 दिन की स्वस्थ 4-5 पत्ती वाली पौध तैयार करें।", "Raise seedlings in insect-proof nursery."),
            CropStageInfo(2, "30-35 DAS", "खेत में रोपाई", "Transplanting", "शाम के समय बेड पर रोपाई करें व इमिडाक्लोप्रिड ड्रेंचिंग करें।", "Transplant seedlings on mulch beds."),
            CropStageInfo(3, "50-65 DAS", "शाखाएं व फूल आना", "Branching & Bloom", "19:19:19 व सूक्ष्म पोषक का स्प्रे। थ्रिप्स व माइट्स की रोकथाम।", "Manage thrips and mites to prevent leaf curl."),
            CropStageInfo(4, "75-150 DAS", "नियमित मिर्च तुड़ाई", "Pickings", "हरी मिर्च की हर 7-10 दिन में तुड़ाई। पोटाश व कैल्शियम नाइट्रेट स्प्रे।", "Continuous pickings of green/red chillies every 8-10 days.")
        ),
        fertigationSchedule = listOf(
            FertigationItem("Basal Dressing", "बुवाई पूर्व", "DAP 50 kg + MOP 40 kg + SSP 50 kg + Zinc 10 kg", "डीएपी 50 किग्रा, पोटाश 40 किग्रा, एसएसपी 50 किग्रा, जिंक सल्फेट 10 किग्रा।", "सड़ी गोबर खाद 5 टन + नीम खली 100 किग्रा।"),
            FertigationItem("15-45 DAT", "वानस्पतिक वृद्धि", "19:19:19 @ 3 kg/acre weekly", "एनपीके 19:19:19 @ 3 किग्रा प्रति एकड़ प्रति सप्ताह।", "जीवामृत 200 लीटर ड्रिप से।"),
            FertigationItem("50-90 DAT", "फूल व फल लगना", "12:61:00 @ 3 kg + 13:0:45 @ 3 kg + Boron 200 g", "एनपीके 12:61:00 @ 3 किग्रा + 13:0:45 @ 3 किग्रा + बोरॉन 200 ग्राम प्रति सप्ताह।", "दशपर्णी अर्क स्प्रे।")
        ),
        pestDiseaseManagement = listOf(
            PestManagementItem("Thrips & Mites (Murda Rog / Churda)", "थ्रिप्स व माइट्स (मुर्रा / चुरड़ा रोग)", symptomsHi = "पत्तियां नाव के आकार में ऊपर (Thrips) या नीचे (Mites) की ओर मुड़ जाती हैं और पौधा बौना रह जाता है।", symptomsEn = "Upward curling by thrips and downward curling by yellow mites.", organicRemedyHi = "नीली व पीली चिपचिपी पट्टियां 20/एकड़ + अग्निअस्त्र 3 लीटर प्रति 150L पानी।", chemicalControlHi = "फिपरोनिल 5% SC @ 300 मिली या स्पाइरोमेसिफेन (ओबेरॉन) @ 200 मिली प्रति 150 लीटर पानी।"),
            PestManagementItem("Anthracnose / Dieback", "फल सड़न व डाइबैक (Anthracnose)", symptomsHi = "टहनियां ऊपर से नीचे की ओर सूखने लगती हैं और पकी मिर्च पर काले धब्बे पड़कर फल सड़ता है।", symptomsEn = "Twig dieback from tip downward and sunken circular spots on fruits.", organicRemedyHi = "जादम सल्फर 200 मिली + जादम वेटिंग एजेंट 300 मिली प्रति 100 लीटर पानी।", chemicalControlHi = "एजोक्सीस्ट्रोबिन + डाइफेनोकोनाजोल (कस्टोडिया) @ 200 मिली प्रति 200 लीटर पानी।")
        ),
        irrigationScheduleList = listOf("ड्रिप सिंचाई: 1 दिन छोड़कर 1.5 घंटे", "फूल अवस्था में अधिक पानी न दें ताकि फूल न झड़ें"),
        financials = CropFinancials("45 - 65 क्विंटल (हरी) / एकड़", "38,000 ₹ / एकड़", "1,40,000 ₹ (Avg ₹2,500/Q)", "1,02,000 ₹ / एकड़")
    )

    // 11. Cotton (कपास)
    private val cottonDetail = CropAgronomyDetail(
        id = "cotton",
        nameEn = "Cotton (Bt Cotton)",
        nameHi = "कपास (बीटी कॉटन)",
        category = "grains",
        categoryHi = "नकदी फसल",
        season = "kharif",
        seasonHi = "खरीफ",
        durationDays = 165,
        soilSuitability = "Deep black cotton soils (Vertisols) or alluvial loam (pH 7.0 - 8.5)",
        soilSuitabilityHi = "गहरी काली मिट्टी (रेगुर) या दोमट भूमि",
        seedRate = "1.5 - 2 पैकेट (750g - 1kg) / एकड़",
        seedRateHi = "1.5 से 2 पैकेट बीटी हाइब्रिड प्रति एकड़",
        spacing = "90 x 60 cm या 120 x 45 cm",
        spacingHi = "कतार 90 सेमी, पौधे की दूरी 60 सेमी",
        recommendedVarieties = listOf("Rasi RCH-659 BG-II", "Kaveri Jadoo", "Bhakti BG-II", "Ankur 3028", "Ajeet 155"),
        recommendedVarietiesHi = listOf("रासी RCH-659 BG-II", "कावेरी जादू", "भक्ति BG-II", "अंकुर 3028", "अजीत 155"),
        stages = listOf(
            CropStageInfo(1, "0-10 DAS", "बुवाई व अंकुरण", "Germination", "खेत में अच्छी नमी होने पर 4-5 सेमी गहराई पर बीटी बीज की डिबलिंग करें।", "Dibble seeds at 90x60 cm in moist soil."),
            CropStageInfo(2, "35-45 DAS", "वर्गाकार कली (Square Formation)", "Square Formation", "पहली टॉप ड्रेसिंग यूरिया + मैग्नीशियम सल्फेट। रसचूसक कीटों की रोकथाम।", "First top dressing & sucking pest scout."),
            CropStageInfo(3, "65-80 DAS", "फूल व टिंडे बनना (Boll Setting)", "Boll Formation", "13:0:45 व बोरॉन स्प्रे। गुलाबी सुंडी (Pink Bollworm) फेरोमोन ट्रैप लगाएं।", "Critical boll setting stage; monitor Pink bollworm."),
            CropStageInfo(4, "120-165 DAS", "टिंडे खिलना व चुनाई (Picking)", "Pickings", "टिंडे खिलने पर धूप निकलने के बाद सूखी साफ रूई की 3-4 चुनाई करें।", "Pick clean, dry seed cotton in sunny hours.")
        ),
        fertigationSchedule = listOf(
            FertigationItem("Basal Sowing", "बुवाई के समय", "DAP 50 kg + MOP 35 kg + Magnesium Sulphate 10 kg / acre", "डीएपी 50 किग्रा, पोटाश 35 किग्रा, मैग्नीशियम सल्फेट 10 किग्रा प्रति एकड़।", "गोबर खाद 4 टन प्रति एकड़।"),
            FertigationItem("35-40 DAS", "शाखाएं बनते समय", "Urea 35 kg / acre", "यूरिया 35 किग्रा प्रति एकड़।", "जीवामृत 200 लीटर पानी के साथ।"),
            FertigationItem("70 DAS (Boll Formation)", "टिंडे बनते समय", "Urea 25 kg + 0:52:34 1 kg + Boron 150 g spray", "यूरिया 25 किग्रा + एनपीके 0:52:34 @ 1 किग्रा + बोरॉन 150 ग्राम स्प्रे।", "दशपर्णी अर्क स्प्रे।")
        ),
        pestDiseaseManagement = listOf(
            PestManagementItem("Pink Bollworm (Gulabi Sundi)", "गुलाबी सुंडी (Pink Bollworm)", symptomsHi = "सुंडी फूल व टिंडे में घुसकर दाना खा जाती है, फूल रोजेट (गुलाब जैसा) बन जाता है।", symptomsEn = "Rosetted flowers and damaged stained lint inside bolls.", organicRemedyHi = "पेक्टिनो-ल्यूर फेरोमोन ट्रैप 8/एकड़ लगाएं + नीम तेल 5 मिली/लीटर स्प्रे।", chemicalControlHi = "प्रोफेनोफॉस 50% EC @ 400 मिली या एमामेक्टिन बेंजोएट @ 100 ग्राम प्रति 150 लीटर पानी।"),
            PestManagementItem("Whitefly & Jassids", "सफेद मक्खी व हरा तेला", symptomsHi = "पत्तियों का रस चूसते हैं, पत्तियां पीली पड़कर नीचे मुड़ जाती हैं व कालिख (Sooty mold) छा जाती है।", symptomsEn = "Nymphs sucking sap causing yellowing and sooty mold.", organicRemedyHi = "पीले चिपचिपे कार्ड 20/एकड़ + नीमास्त्र 5L/150L पानी।", chemicalControlHi = "पायरीप्रॉक्सीफेन 10% + बाइफेन्थ्रिन 10% EC @ 300 मिली प्रति 150 लीटर पानी।")
        ),
        irrigationScheduleList = listOf("1. बुवाई से पूर्व पलेवा", "2. कली बनते समय (40-45 दिन)", "3. फूल व टिंडे बनते समय (70-75 दिन) - सबसे महत्वपूर्ण", "4. टिंडे फूलते समय (100-110 दिन)"),
        financials = CropFinancials("10 - 15 क्विंटल / एकड़", "26,000 ₹ / एकड़", "84,000 ₹ (Mandi ₹7,000/Q)", "58,000 ₹ / एकड़")
    )

    // 12. Soybean (सोयाबीन)
    private val soybeanDetail = CropAgronomyDetail(
        id = "soybean",
        nameEn = "Soybean",
        nameHi = "सोयाबीन",
        category = "oilseeds",
        categoryHi = "तिलहन",
        season = "kharif",
        seasonHi = "खरीफ",
        durationDays = 95,
        soilSuitability = "Well drained medium to deep black soils with good fertility (pH 6.5 - 7.5)",
        soilSuitabilityHi = "मध्यम से गहरी काली मिट्टी, जल निकास की उत्तम व्यवस्था हो",
        seedRate = "28 - 32 kg / एकड़",
        seedRateHi = "28 से 32 किग्रा प्रति एकड़ (बीज उपचारित)",
        spacing = "45 x 7 cm",
        spacingHi = "कतार 45 सेमी, पौधे की दूरी 7 सेमी",
        recommendedVarieties = listOf("JS-2034", "JS-9560", "JS-2098", "NRC-142", "RVS-2001-4"),
        recommendedVarietiesHi = listOf("जेएस-2034 (सर्वाधिक प्रचलित)", "जेएस-9560 (अगेती)", "जेएस-2098", "एनआरसी-142", "आरवीएस-2001-4"),
        stages = listOf(
            CropStageInfo(1, "0-5 DAS", "बुवाई व अंकुरण", "Germination", "बीज को ब्रैडीराइजोबियम कल्चर व ट्राइकोडर्मा से उपचारित कर 3 सेमी गहराई पर बोएं।", "Sow Bradyrhizobium treated seeds at 3cm depth."),
            CropStageInfo(2, "20-25 DAS", "निराई व वानस्पतिक वृद्धि", "Vegetative", "खरपतवार नियंत्रण करें। गर्डल बीटल (चक्र भृंग) की निगरानी रखें।", "Weeding and girdle beetle monitoring."),
            CropStageInfo(3, "40-50 DAS", "फूल आना (Flowering)", "Flowering", "फूल आते समय खेत में जल भराव न होने दें। 19:19:19 स्प्रे करें।", "Avoid waterlogging; spray NPK 19:19:19."),
            CropStageInfo(4, "65-75 DAS", "फली दाना भराव (Pod Filling)", "Pod Filling", "फलियों में दाना भरते समय नमी बनाए रखें। 0:52:34 + बोरॉन स्प्रे।", "Ensure moisture during pod filling; 0:52:34 spray."),
            CropStageInfo(5, "90-95 DAS", "पत्तियां झड़ना व कटाई", "Maturity", "पत्तियां पीली पड़कर झड़ जाएं व फलियां भूरी होने पर कटाई करें।", "Harvest when leaves drop and pods turn brown.")
        ),
        fertigationSchedule = listOf(
            FertigationItem("Basal Sowing", "बुवाई के समय", "DAP 35 kg + SSP 50 kg + MOP 20 kg + Sulphur 10 kg / acre", "डीएपी 35 किग्रा, सिंगल सुपर फॉस्फेट 50 किग्रा, पोटाश 20 किग्रा, सल्फर 10 किग्रा प्रति एकड़।", "घनजीवामृत 300 किग्रा प्रति एकड़।"),
            FertigationItem("35 DAS (Pre-Flowering)", "फूल आने से पूर्व", "NPK 19:19:19 @ 1 kg / acre spray", "एनपीके 19:19:19 @ 1 किग्रा प्रति 150 लीटर पानी स्प्रे।", "जीवामृत 10% स्प्रे।"),
            FertigationItem("65 DAS (Pod Filling)", "फली दाना भराव", "0:52:34 @ 1 kg + Boron 20% @ 150 g / acre", "एनपीके 0:52:34 @ 1 किग्रा + बोरॉन 150 ग्राम प्रति 150 लीटर पानी स्प्रे।", "दशपर्णी अर्क स्प्रे।")
        ),
        pestDiseaseManagement = listOf(
            PestManagementItem("Girdle Beetle (Chakra Bhrang)", "गर्डल बीटल / चक्र भृंग", symptomsHi = "कीट शाखा या तने पर दो गोल छल्ले (Girdle) काटकर अंडा देता है, जिससे ऊपर की शाखा सूख जाती है।", symptomsEn = "Oberea brevis creating two ring girdles on petiole/stem causing drying.", organicRemedyHi = "ग्रसित शाखाओं को काटकर नष्ट करें + नीमास्त्र 5 लीटर प्रति 150L पानी।", chemicalControlHi = "कोराजन @ 60 मिली या क्लोरेंट्रानिलीप्रोल 18.5% SC @ 60 मिली प्रति 150 लीटर पानी।"),
            PestManagementItem("Semilooper / Spodoptera", "तम्बाकू की इल्ली व सेमीलूपर", symptomsHi = "हरी इल्लियां पत्तियों को छलनी कर देती हैं।", symptomsEn = "Green defoliator caterpillars skeletonizing leaves.", organicRemedyHi = "अग्निअस्त्र 3 लीटर + 150 लीटर पानी स्प्रे।", chemicalControlHi = "एमामेक्टिन बेंजोएट 5% SG @ 100 ग्राम प्रति 150 लीटर पानी।")
        ),
        irrigationScheduleList = listOf("1. मानसून की वर्षा पर आधारित", "2. यदि वर्षा न हो तो फली दाना भराव (65-70 दिन) पर 1 जीवन रक्षक सिंचाई अति आवश्यक"),
        financials = CropFinancials("10 - 14 क्विंटल / एकड़", "12,500 ₹ / एकड़", "54,000 ₹ (Mandi ₹4,500/Q)", "41,500 ₹ / एकड़")
    )

    // 13. Maize (मक्का)
    private val maizeDetail = CropAgronomyDetail(
        id = "maize",
        nameEn = "Maize (Kharif / Spring)",
        nameHi = "मक्का",
        category = "grains",
        categoryHi = "अनाज",
        season = "kharif",
        seasonHi = "खरीफ",
        durationDays = 100,
        soilSuitability = "Well drained fertile loam to silt loam (pH 6.0 - 7.5)",
        soilSuitabilityHi = "उर्वर दोमट भूमि, जल निकास की अच्छी सुविधा हो",
        seedRate = "7 - 8 kg / एकड़ (हाइब्रिड)",
        seedRateHi = "7 से 8 किग्रा प्रति एकड़ (हाइब्रिड)",
        spacing = "60 x 20 cm",
        spacingHi = "कतार 60 सेमी, पौधे की दूरी 20 सेमी",
        recommendedVarieties = listOf("Pioneer P3396", "Dekalb 9108", "Syngenta NK-6240", "Pusa HM-4 (Baby Corn)", "HQPM-1"),
        recommendedVarietiesHi = listOf("पायनियर P3396", "डीकाल्ब 9108", "सिंजेंटा NK-6240", "पूसा एचएम-4 (बेबी कॉर्न)", "एचक्यूपीएम-1 (उच्च प्रोटीन)"),
        stages = listOf(
            CropStageInfo(1, "0-6 DAS", "बुवाई व अंकुरण", "Germination", "खेत में नमी होने पर 4 सेमी गहराई पर बोएं। फॉल आर्मीवर्म की निगरानी।", "Sow treated seed at 4cm depth."),
            CropStageInfo(2, "20-25 DAS", "घुटने तक ऊंचाई (Knee High)", "Knee High Stage", "पहली यूरिया टॉप ड्रेसिंग व मिट्टी चढ़ाना। फॉल आर्मीवर्म नियंत्रण।", "Top dress Urea & earth up soil around base."),
            CropStageInfo(3, "45-55 DAS", "नर मंजरी (Tasseling)", "Tasseling", "पौधे के शीर्ष से नर फूल (Tassel) निकलना। सिंचाई का सबसे संवेदनशील समय।", "Tassel emergence; critical water stage."),
            CropStageInfo(4, "60-70 DAS", "मादा फूल / सिल्क व भुट्टा भराव", "Silking & Cob Filling", "सिल्क निकलना व दाना भराव। 0:52:34 + जिंक स्प्रे।", "Silking & pollination; maintain soil moisture."),
            CropStageInfo(5, "95-100 DAS", "भुट्टे पकना व कटाई", "Harvesting", "भुट्टे के छिलके पीले-सूखे होने पर कटाई।", "Harvest when husk covers turn dry paper-white.")
        ),
        fertigationSchedule = listOf(
            FertigationItem("Basal Sowing", "बुवाई के समय", "DAP 50 kg + MOP 25 kg + Zinc Sulphate 10 kg / acre", "डीएपी 50 किग्रा, पोटाश 25 किग्रा, जिंक सल्फेट 21%: 10 किग्रा प्रति एकड़।", "गोबर खाद 4 टन प्रति एकड़।"),
            FertigationItem("25 DAS (Knee High)", "घुटने की ऊंचाई पर", "Urea 35 kg / acre", "यूरिया 35 किग्रा प्रति एकड़।", "जीवामृत 200 लीटर पानी के साथ।"),
            FertigationItem("45 DAS (Tasseling)", "मंजरी निकलते समय", "Urea 30 kg + 19:19:19 @ 1 kg spray", "यूरिया 30 किग्रा + एनपीके 19:19:19 @ 1 किग्रा स्प्रे।", "दशपर्णी अर्क स्प्रे।")
        ),
        pestDiseaseManagement = listOf(
            PestManagementItem("Fall Armyworm (FAW)", "फॉल आर्मीवर्म (सैनिक कीट)", symptomsHi = "इल्ली पोंगे (Whorl) में घुसकर पत्तियों को छलनी कर देती है और चूरा जैसा मल छोड़ती है।", symptomsEn = "Spodoptera frugiperda larvae feeding in leaf whorls creating large ragged holes.", organicRemedyHi = "नीमास्त्र 5 लीटर प्रति 150L पानी या पोंगे में सूखी राख/मिट्टी डालें।", chemicalControlHi = "कोराजन (Chlorantraniliprole) @ 60 मिली या स्पिनटोर (Spinosad 45% SC) @ 60 मिली प्रति 150L पानी।"),
            PestManagementItem("Stem Borer (Chilo partellus)", "तना छेदक", symptomsHi = "पत्तियों पर कतार में छेद (Pin holes) व डेड हार्ट बनता है।", symptomsEn = "Dead heart in seedling stage and pin holes in open leaves.", organicRemedyHi = "ट्राइकोग्रामा कार्ड 3 प्रति एकड़ लगाएं।", chemicalControlHi = "कार्बोफ्यूरॉन 3% G @ 7 किग्रा प्रति एकड़ पोंगे में डालें।")
        ),
        irrigationScheduleList = listOf("1. घुटने की ऊंचाई अवस्था (25-30 दिन)", "2. मंजरी निकलते समय (45-50 दिन) - सबसे महत्वपूर्ण", "3. भुट्टा दाना भराव (65-70 दिन)"),
        financials = CropFinancials("25 - 32 क्विंटल / एकड़", "15,500 ₹ / एकड़", "60,000 ₹ (Mandi ₹2,100/Q)", "44,500 ₹ / एकड़")
    )

    // Other vegetables, fruits, and pulses mapped dynamically
    private val onionRabiDetail = CropAgronomyDetail(
        id = "onion_rabi", nameEn = "Onion (Rabi)", nameHi = "रबी प्याज", category = "vegetables", categoryHi = "सब्जियां", season = "rabi", seasonHi = "रबी", durationDays = 130,
        soilSuitabilityHi = "जीवांश युक्त भुरभुरी दोमट भूमि (pH 6.5 - 7.5)", seedRateHi = "3.5 से 4 किग्रा प्रति एकड़ (नर्सरी पौध)", spacingHi = "15 x 10 सेमी",
        recommendedVarietiesHi = listOf("भीमा सुपर", "भीमा शक्ति", "एग्रीफाउंड लाइट रेड", "एन-53", "पूसा रेड"),
        stages = listOf(
            CropStageInfo(1, "0-45 DAS", "नर्सरी पौध तैयार करना", "Nursery", "45 दिन की स्वस्थ पौध तैयार करें।", "Nursery raising."),
            CropStageInfo(2, "45-50 DAS", "खेत में रोपाई", "Transplanting", "15x10 सेमी पर सपाट क्यारियों में रोपाई।", "Transplant at 15x10cm."),
            CropStageInfo(3, "75-90 DAT", "गांठ बनना (Bulb Initiation)", "Bulb Formation", "सल्फर व पोटाश का प्रयोग। थ्रिप्स नियंत्रण।", "Bulb development stage."),
            CropStageInfo(4, "120-130 DAT", "गर्दन झुकना (Neck Fall) व खुदाई", "Harvesting", "50% पौधों की गर्दन झुकने पर पानी बंद करें व खुदाई करें।", "Harvest at 50% neck fall.")
        ),
        fertigationSchedule = listOf(
            FertigationItem("Basal Sowing", "रोपाई पूर्व", "DAP 50 kg + MOP 40 kg + Sulphur 90% 15 kg", "डीएपी 50 किग्रा, पोटाश 40 किग्रा, सल्फर 90% 15 किग्रा प्रति एकड़।", "गोबर खाद 5 टन प्रति एकड़।"),
            FertigationItem("30 DAT", "रोपाई के 30 दिन", "Urea 35 kg / acre", "यूरिया 35 किग्रा प्रति एकड़।", "जीवामृत 200 लीटर।"),
            FertigationItem("60 DAT", "गांठ बनते समय", "NPK 0:52:34 @ 1 kg + 0:0:50 @ 1 kg spray", "0:52:34 @ 1 किग्रा + 0:0:50 @ 1 किग्रा प्रति 150 लीटर पानी स्प्रे।", "राख का अर्क स्प्रे।")
        ),
        pestDiseaseManagement = listOf(
            PestManagementItem("Thrips", "प्याज का थ्रिप्स", symptomsHi = "पत्तियों पर सफेद-चांदी जैसे धब्बे बनते हैं, पत्तियां मुड़ जाती हैं।", symptomsEn = "Silvery patches on leaves.", organicRemedyHi = "नीली चिपचिपी पट्टियां 20/एकड़ + नीमास्त्र 5L/150L पानी।", chemicalControlHi = "फिपरोनिल 5% SC @ 300 मिली प्रति 150 लीटर पानी।"),
            PestManagementItem("Purple Blotch", "बैगनी धब्बा रोग (Purple Blotch)", symptomsHi = "पत्तियों पर नाव के आकार के बैगनी-भूरे धब्बे बनते हैं।", symptomsEn = "Purple sunken lesions on leaves.", organicRemedyHi = "जादम सल्फर 200 मिली प्रति 100L पानी।", chemicalControlHi = "मैनकोजेब 75% WP @ 400 ग्राम प्रति 150 लीटर पानी।")
        ),
        irrigationScheduleList = listOf("1. रोपाई के तुरंत बाद पहली सिंचाई", "2. गांठ बनते समय हर 7-8 दिन पर हल्की सिंचाई", "3. खुदाई से 12 दिन पहले पानी पूरी तरह बंद कर दें"),
        financials = CropFinancials("120 - 160 क्विंटल / एकड़", "32,000 ₹ / एकड़", "1,80,000 ₹ (Avg ₹1,300/Q)", "1,48,000 ₹ / एकड़")
    )

    private val garlicDetail = CropAgronomyDetail(
        id = "garlic", nameEn = "Garlic", nameHi = "लहसुन", category = "vegetables", categoryHi = "सब्जियां", season = "rabi", seasonHi = "रबी", durationDays = 150,
        soilSuitabilityHi = "जीवांश युक्त उपजाऊ दोमट भूमि (pH 6.5 - 7.5)", seedRateHi = "200 से 250 किग्रा स्वस्थ कलियां प्रति एकड़", spacingHi = "15 x 8 सेमी",
        recommendedVarietiesHi = listOf("जी-282 (यमुना सफेद-3)", "जी-50 (यमुना सफेद-2)", "भीमा ओंकार", "रियावन लहसुन", "ऊटी-1"),
        stages = listOf(
            CropStageInfo(1, "0-10 DAS", "कलियों की बुवाई", "Planting", "स्वस्थ मोटी कलियों को ट्राइकोडर्मा से उपचारित कर 5 सेमी गहराई पर लगाएं।", "Plant cloves at 15x8cm."),
            CropStageInfo(2, "30-40 DAS", "निराई व वानस्पतिक वृद्धि", "Vegetative", "पहली यूरिया व सल्फर टॉप ड्रेसिंग।", "First top dressing with Urea & Sulphur."),
            CropStageInfo(3, "70-90 DAS", "गांठ व कली विकास", "Clove Bulking", "पोटाश व बोरॉन स्प्रे। नमी बनाए रखें।", "Clove bulking with Potash & Boron spray."),
            CropStageInfo(4, "140-150 DAS", "परिपक्वता व खुदाई", "Harvesting", "ऊपरी पत्तियां सूखकर पीली होने पर पानी बंद कर खुदाई करें।", "Harvest when tops turn dry and yellow.")
        ),
        fertigationSchedule = listOf(
            FertigationItem("Basal Planting", "बुवाई के समय", "DAP 60 kg + MOP 50 kg + Sulphur 20 kg + Zinc 10 kg", "डीएपी 60 किग्रा, पोटाश 50 किग्रा, बेंटोनाइट सल्फर 20 किग्रा, जिंक 10 किग्रा।", "गोबर खाद 6 टन प्रति एकड़।"),
            FertigationItem("40 DAS", "निराई के बाद", "Urea 40 kg / acre", "यूरिया 40 किग्रा प्रति एकड़।", "जीवामृत 200 लीटर।"),
            FertigationItem("80 DAS", "गांठ बनते समय", "NPK 0:0:50 @ 1.5 kg + Boron 200 g spray", "0:0:50 @ 1.5 किग्रा + बोरॉन 20% @ 200 ग्राम प्रति 150 लीटर पानी।", "राख का अर्क।")
        ),
        pestDiseaseManagement = listOf(
            PestManagementItem("Thrips", "लहसुन का थ्रिप्स", symptomsHi = "पत्तियों से रस चूसते हैं जिससे पत्तियां पीली पड़कर सूखती हैं।", symptomsEn = "Thrips causing white silvery streaks.", organicRemedyHi = "दशपर्णी अर्क 5 लीटर प्रति 150L पानी।", chemicalControlHi = "इमिडाक्लोप्रिड 17.8% SL @ 60 मिली प्रति 150 लीटर पानी।"),
            PestManagementItem("Stem & Clove Rot", "तना व कली गलन रोग", symptomsHi = "जमीन के पास से तना सड़ जाता है व बदबू आती है।", symptomsEn = "Rotting of cloves at ground level.", organicRemedyHi = "ट्राइकोडर्मा विरिडी 2 किग्रा गोबर में मिलाकर डालें।", chemicalControlHi = "कॉपर ऑक्सीक्लोराइड 50% WP @ 500 ग्राम प्रति 200 लीटर पानी में ड्रेंचिंग।")
        ),
        irrigationScheduleList = listOf("1. बुवाई के तुरंत बाद हल्की सिंचाई", "2. कली बनते समय हर 8-10 दिन पर सिंचाई", "3. खुदाई से 15 दिन पूर्व पानी बंद"),
        financials = CropFinancials("40 - 55 क्विंटल / एकड़", "48,000 ₹ / एकड़", "2,40,000 ₹ (Avg ₹5,000/Q)", "1,92,000 ₹ / एकड़")
    )

    private val peasDetail = CropAgronomyDetail(
        id = "peas", nameEn = "Green Peas", nameHi = "हरी मटर", category = "vegetables", categoryHi = "सब्जियां", season = "rabi", seasonHi = "रबी", durationDays = 85,
        soilSuitabilityHi = "हल्की दोमट से दोमट भूमि (pH 6.0 - 7.5)", seedRateHi = "35 से 40 किग्रा प्रति एकड़", spacingHi = "30 x 7 सेमी",
        recommendedVarietiesHi = listOf("जीएस-10 (एडवांटा)", "पूसा प्रगति", "आजाद मटर-1", "आर्केल", "काशी नंदिनी"),
        stages = listOf(
            CropStageInfo(1, "0-6 DAS", "बुवाई व अंकुरण", "Germination", "राइजोबियम कल्चर से उपचारित कर बुवाई।", "Sow Rhizobium treated seed at 30x7cm."),
            CropStageInfo(2, "30-35 DAS", "फूल आना (Flowering)", "Flowering", "फूल आते समय हल्की सिंचाई। पाउडरी मिल्ड्यू की निगरानी।", "Light irrigation before flowering."),
            CropStageInfo(3, "55-85 DAS", "फलियों की हरी तुड़ाई", "Green Pod Pickings", "फलियां दानेदार व मीठी होने पर 2-3 तुड़ाई करें।", "Multiple green pod pickings.")
        ),
        fertigationSchedule = listOf(
            FertigationItem("Basal Sowing", "बुवाई के समय", "DAP 50 kg + MOP 20 kg + SSP 50 kg", "डीएपी 50 किग्रा, पोटाश 20 किग्रा, सिंगल सुपर फॉस्फेट 50 किग्रा प्रति एकड़।", "घनजीवामृत 300 किग्रा प्रति एकड़।"),
            FertigationItem("40 DAS", "फूल व फली बनते समय", "NPK 19:19:19 1 kg + Boron 150 g spray", "19:19:19 @ 1 किग्रा + बोरॉन 150 ग्राम प्रति 150 लीटर पानी।", "खट्टी छाछ 5 लीटर स्प्रे।")
        ),
        pestDiseaseManagement = listOf(
            PestManagementItem("Powdery Mildew (Churna Rog)", "चूर्णी फफूंद (Powdery Mildew)", symptomsHi = "पत्तियों व फलियों पर सफेद पाउडर जैसा चूर्ण छा जाता है।", symptomsEn = "White powdery coating on foliage & pods.", organicRemedyHi = "जादम सल्फर 200 मिली प्रति 100 लीटर पानी या खट्टी छाछ 5%।", chemicalControlHi = "घुलनशील गंधक 80% WDG @ 400 ग्राम प्रति 150 लीटर पानी।"),
            PestManagementItem("Pod Borer", "फली छेदक सुंडी", symptomsHi = "सुंडी फली में छेद करके मटर के मीठे दाने खाती है।", symptomsEn = "Caterpillar boring into pods.", organicRemedyHi = "अग्निअस्त्र 3 लीटर प्रति 150L पानी।", chemicalControlHi = "एमामेक्टिन बेंजोएट 5% SG @ 80 ग्राम प्रति 150 लीटर पानी।")
        ),
        irrigationScheduleList = listOf("1. बुवाई पूर्व पलेवा", "2. फूल आते समय (30-35 दिन)", "3. फली में दाना भरते समय (55-60 दिन)"),
        financials = CropFinancials("35 - 50 क्विंटल हरी फली / एकड़", "16,000 ₹ / एकड़", "90,000 ₹ (Mandi ₹2,200/Q)", "74,000 ₹ / एकड़")
    )

    private val cauliflowerDetail = CropAgronomyDetail(
        id = "cauliflower", nameEn = "Cauliflower", nameHi = "फूलगोभी", category = "vegetables", categoryHi = "सब्जियां", season = "rabi", seasonHi = "रबी", durationDays = 85,
        soilSuitabilityHi = "जीवांश युक्त भारी दोमट भूमि (pH 6.0 - 7.0)", seedRateHi = "150 से 200 ग्राम प्रति एकड़", spacingHi = "45 x 45 सेमी",
        recommendedVarietiesHi = listOf("सिंजेंटा सुहासिनी", "सेमिनिस गिरिजा", "पूसा स्नोबॉल-16", "सिंजेंटा क्लॉज", "काशी कुंवारी"),
        stages = listOf(
            CropStageInfo(1, "0-25 DAS", "नर्सरी पौध तैयार करना", "Nursery", "प्रोट्रे में 25 दिन की पौध तैयार करें।", "Nursery stage in shade net."),
            CropStageInfo(2, "25-30 DAS", "खेत में रोपाई", "Transplanting", "45x45 सेमी पर मेड़ पर रोपाई।", "Transplant at 45x45cm."),
            CropStageInfo(3, "50-70 DAT", "फूल बनना (Curd Development)", "Curd Formation", "बोरॉन व पोटाश स्प्रे ताकि फूल सफेद व ठोस रहे।", "Curd expansion with Boron spray."),
            CropStageInfo(4, "75-85 DAT", "फूल कटाई", "Harvesting", "फूल ठोस व सफेद रहने पर ही पत्तियों सहित कटाई करें।", "Harvest firm white curds.")
        ),
        fertigationSchedule = listOf(
            FertigationItem("Basal Dressing", "रोपाई पूर्व", "DAP 50 kg + MOP 40 kg + Borax 4 kg / acre", "डीएपी 50 किग्रा, पोटाश 40 किग्रा, सुहागा (बोरेक्स) 4 किग्रा प्रति एकड़ (बोरॉन कमी से फूल भूरा होता है)।", "गोबर खाद 5 टन प्रति एकड़।"),
            FertigationItem("25 DAT", "रोपाई के 25 दिन", "Urea 35 kg / acre", "यूरिया 35 किग्रा प्रति एकड़।", "जीवामृत 200 लीटर।"),
            FertigationItem("50 DAT", "फूल बनते समय", "NPK 0:52:34 @ 1 kg + Boron 20% @ 200 g spray", "0:52:34 @ 1 किग्रा + बोरॉन 20% @ 200 ग्राम प्रति 150 लीटर पानी।", "दशपर्णी अर्क स्प्रे।")
        ),
        pestDiseaseManagement = listOf(
            PestManagementItem("Diamondback Moth (DBM)", "डायमंड बैक मोथ (DBM)", symptomsHi = "छोटी हरी सुंडी पत्तियों में गोल छेद बनाती है और फूल को खराब करती है।", symptomsEn = "Plutella xylostella larvae skeletonizing leaves.", organicRemedyHi = "फेरोमोन ट्रैप (DBM ल्यूर) 8/एकड़ + नीमास्त्र 5%।", chemicalControlHi = "कोराजन @ 60 मिली या स्पिनोसैड 45% SC @ 50 मिली प्रति 150L पानी।"),
            PestManagementItem("Browning / Hollow Stem (Boron Deficiency)", "फूल का भूरापन व खोखला तना (बोरॉन कमी)", symptomsHi = "फूल भूरा-काला पड़ जाता है व तना अंदर से खोखला हो जाता है।", symptomsEn = "Boron deficiency causing curd browning & hollow stem.", organicRemedyHi = "बोरेक्स 4 किग्रा प्रति एकड़ जमीन में दें।", chemicalControlHi = "बोरॉन 20% (डिसोडियम ऑक्टाबोरेट) @ 1.5 ग्राम प्रति लीटर पानी स्प्रे।")
        ),
        irrigationScheduleList = listOf("1. रोपाई के तुरंत बाद पहली सिंचाई", "2. हर 6-8 दिन के अंतराल पर हल्की सिंचाई"),
        financials = CropFinancials("100 - 140 क्विंटल / एकड़", "25,000 ₹ / एकड़", "1,10,000 ₹ (Mandi ₹900/Q)", "85,000 ₹ / एकड़")
    )

    private val lentilDetail = CropAgronomyDetail(
        id = "lentil", nameEn = "Lentil", nameHi = "मसूर", category = "pulses", categoryHi = "दालें", season = "rabi", seasonHi = "रबी", durationDays = 110,
        soilSuitabilityHi = "हल्की दोमट से मटियार दोमट (pH 6.0 - 7.5)", seedRateHi = "15 से 18 किग्रा प्रति एकड़", spacingHi = "25 x 5 सेमी",
        recommendedVarietiesHi = listOf("पूसा अगेती", "पूसा शिवालिक", "आईपीएल-81", "केएल-320", "एचयूएल-57"),
        stages = listOf(
            CropStageInfo(1, "0-8 DAS", "बुवाई व अंकुरण", "Germination", "राइजोबियम उपचारित बीज बोएं।", "Sow Rhizobium treated seed at 25x5cm."),
            CropStageInfo(2, "40-50 DAS", "शाखाएं व फूल आना", "Flowering", "फूल आने से पूर्व हल्की सिंचाई।", "Pre-flowering light irrigation."),
            CropStageInfo(3, "100-110 DAS", "फली पकना व कटाई", "Harvesting", "पौधे सुनहरे-भूरे होने पर कटाई।", "Harvest at maturity.")
        ),
        fertigationSchedule = listOf(
            FertigationItem("Basal Sowing", "बुवाई के समय", "DAP 30 kg + SSP 50 kg + MOP 15 kg", "डीएपी 30 किग्रा, सिंगल सुपर फॉस्फेट 50 किग्रा, पोटाश 15 किग्रा।", "घनजीवामृत 250 किग्रा प्रति एकड़।"),
            FertigationItem("45 DAS", "फूल आते समय", "19:19:19 1 kg + Boron 100 g spray", "19:19:19 @ 1 किग्रा + बोरॉन 100 ग्राम प्रति 150 लीटर पानी।", "जीवामृत 10% स्प्रे।")
        ),
        pestDiseaseManagement = listOf(
            PestManagementItem("Rust / Ukhata", "मसूर का रतुआ व उखटा", symptomsHi = "पत्तियों पर भूरे धब्बे व पौधा सूखना।", symptomsEn = "Rust pustules & root rot.", organicRemedyHi = "ट्राइकोडर्मा विरिडी 2 किग्रा गोबर में मिलाकर डालें।", chemicalControlHi = "मैनकोजेब 75% WP @ 400 ग्राम प्रति 150 लीटर पानी।")
        ),
        irrigationScheduleList = listOf("1. बुवाई पूर्व पलेवा", "2. फली बनते समय (60 दिन पर हल्की सिंचाई)"),
        financials = CropFinancials("7 - 10 क्विंटल / एकड़", "8,500 ₹ / एकड़", "48,000 ₹ (Mandi ₹5,500/Q)", "39,500 ₹ / एकड़")
    )

    private val paddyHybridDetail = CropAgronomyDetail(
        id = "paddy_hybrid", nameEn = "Hybrid Paddy", nameHi = "हाइब्रिड धान", category = "grains", categoryHi = "अनाज", season = "kharif", seasonHi = "खरीफ", durationDays = 125,
        soilSuitabilityHi = "मटियार दोमट से भारी चिकनी मिट्टी (pH 6.5 - 8.0)", seedRateHi = "6 किग्रा प्रति एकड़ (नर्सरी)", spacingHi = "20 x 15 सेमी",
        recommendedVarietiesHi = listOf("पायनियर 27P31", "पायनियर 27P37", "बायर अरिज 6444 गोल्ड", "सिंजेंटा NK 6302", "सवाना सवा-127"),
        stages = listOf(
            CropStageInfo(1, "0-25 DAS", "नर्सरी पौध तैयारी", "Nursery", "25 दिन की पौध तैयार करें।", "Nursery stage."),
            CropStageInfo(2, "25-30 DAS", "लेव व रोपाई", "Transplanting", "कद्दू करके 20x15 सेमी पर रोपाई।", "Transplant at 20x15cm."),
            CropStageInfo(3, "45-55 DAT", "कल्ले निकलना (Tillering)", "Tillering", "यूरिया व जिंक टॉप ड्रेसिंग।", "Tillering stage with Zinc/Urea."),
            CropStageInfo(4, "75-85 DAT", "बाली निकलना व दाना भराव", "Heading & Milking", "पानी की कमी न होने दें। 0:52:34 स्प्रे।", "Heading stage."),
            CropStageInfo(5, "115-125 DAT", "परिपक्वता व कटाई", "Harvesting", "बालियां सुनहरी होने पर कटाई।", "Harvest at 85% golden maturity.")
        ),
        fertigationSchedule = listOf(
            FertigationItem("Basal Puddling", "लेव लगाते समय", "DAP 50 kg + MOP 30 kg + Zinc 21% 15 kg", "डीएपी 50 किग्रा, पोटाश 30 किग्रा, जिंक सल्फेट 21%: 15 किग्रा।", "घनजीवामृत 400 किग्रा प्रति एकड़।"),
            FertigationItem("25 DAT", "कल्ले निकलते समय", "Urea 35 kg / acre", "यूरिया 35 किग्रा प्रति एकड़।", "जीवामृत 200 लीटर।"),
            FertigationItem("50 DAT", "गोभ अवस्था", "Urea 25 kg + 19:19:19 @ 1 kg spray", "यूरिया 25 किग्रा + 19:19:19 1 किग्रा स्प्रे।", "दशपर्णी अर्क।")
        ),
        pestDiseaseManagement = listOf(
            PestManagementItem("Brown Plant Hopper (BPH)", "भूरा फुदका (BPH / Madhua)", symptomsHi = "पौधों के आधार पर रस चूसते हैं जिससे गोल घेरे में फसल जलकर सूख जाती है (Hopper Burn)।", symptomsEn = "Nilaparvata lugens causing hopper burn.", organicRemedyHi = "खेत से 2-3 दिन के लिए पानी निकाल दें + नीमास्त्र 5%।", chemicalControlHi = "पाइमेट्रोजिन 50% WG (चेस) @ 120 ग्राम या पेक्सलोन @ 94 मिली प्रति 200 लीटर पानी।"),
            PestManagementItem("Stem Borer & Blast", "तना छेदक व ब्लास्ट रोग", symptomsHi = "सफेद बालियां व पत्तियों पर आंख के आकार के धब्बे (Spindle lesions)।", symptomsEn = "Stem borer whiteheads and blast leaf lesions.", organicRemedyHi = "ट्राइकोग्रामा कार्ड + ट्राइकोडर्मा विरिडी 1 किग्रा/एकड़।", chemicalControlHi = "कोराजन @ 60 मिली + ट्राइसाइक्लाजोल 75% WP @ 120 ग्राम प्रति 200 लीटर पानी।")
        ),
        irrigationScheduleList = listOf("1. रोपाई के बाद 4-5 सेमी पानी रखें", "2. कल्ले फूटते समय 2-3 सेमी पानी", "3. गोभ व बाली अवस्था में पानी भरा रहे", "4. कटाई से 10 दिन पूर्व पानी निकाल दें"),
        financials = CropFinancials("28 - 35 क्विंटल / एकड़", "24,000 ₹ / एकड़", "75,000 ₹ (Mandi ₹2,300/Q)", "51,000 ₹ / एकड़")
    )

    private val pigeonPeaDetail = CropAgronomyDetail(
        id = "pigeon_pea", nameEn = "Pigeon Pea / Arhar", nameHi = "अरहर / तुअर", category = "pulses", categoryHi = "दालें", season = "kharif", seasonHi = "खरीफ", durationDays = 180,
        soilSuitabilityHi = "गहरी दोमट से मध्यम काली भूमि, उत्तम जल निकास (pH 6.5 - 7.5)", seedRateHi = "4 से 5 किग्रा प्रति एकड़", spacingHi = "90 x 20 सेमी",
        recommendedVarietiesHi = listOf("पूसा 992", "आईसीपीएच-2671 (हाइब्रिड)", "बहार", "पूसा 2001", "मालवीय-13"),
        stages = listOf(
            CropStageInfo(1, "0-8 DAS", "बुवाई व अंकुरण", "Germination", "राइजोबियम व ट्राइकोडर्मा उपचारित बीज 90x20 सेमी पर बोएं।", "Sow treated seed at 90x20cm."),
            CropStageInfo(2, "30-45 DAS", "शाखा विकास व निराई", "Vegetative", "खरपतवार नियंत्रण व पहली हल्की गुड़ाई।", "Weeding and intercultural operations."),
            CropStageInfo(3, "90-110 DAS", "फूल आना (Flowering)", "Flowering", "फूल आते समय मारूका व फली मक्खी की रोकथाम। 19:19:19 स्प्रे।", "Flowering stage pest scout."),
            CropStageInfo(4, "130-150 DAS", "फली विकास (Pod Setting)", "Pod Setting", "0:52:34 + बोरॉन स्प्रे फली दाना भराव हेतु।", "Pod formation stage."),
            CropStageInfo(5, "170-180 DAS", "परिपक्वता व कटाई", "Harvesting", "फलियां 80% सूखने पर हंसिया से कटाई।", "Harvest when pods turn brown.")
        ),
        fertigationSchedule = listOf(
            FertigationItem("Basal Sowing", "बुवाई के समय", "DAP 40 kg + SSP 50 kg + MOP 15 kg", "डीएपी 40 किग्रा, सिंगल सुपर फॉस्फेट 50 किग्रा, पोटाश 15 किग्रा प्रति एकड़।", "घनजीवामृत 300 किग्रा प्रति एकड़।"),
            FertigationItem("90 DAS", "फूल आते समय", "19:19:19 @ 1 kg + Boron 150 g spray", "19:19:19 @ 1 किग्रा + बोरॉन 150 ग्राम प्रति 150 लीटर पानी।", "जीवामृत 10% स्प्रे।")
        ),
        pestDiseaseManagement = listOf(
            PestManagementItem("Pod Borer & Pod Fly", "फली छेदक व फली मक्खी (Pod Fly)", symptomsHi = "सुंडी फली में छेद करती है व मक्खी का कीड़ा दाने में सुरंगी बनाकर अंदर से खोखला कर देता है।", symptomsEn = "Maruca vitrata & Melanagromyza obtusa damaging pods.", organicRemedyHi = "फेरोमोन ट्रैप 6/एकड़ + अग्निअस्त्र 3 लीटर/150L पानी।", chemicalControlHi = "कोराजन @ 60 मिली या एमामेक्टिन बेंजोएट @ 100 ग्राम प्रति 150 लीटर पानी।"),
            PestManagementItem("Fusarium Wilt / Sterility Mosaic", "उकठा व बांझपन रोग (SMD)", symptomsHi = "पौधा सूख जाता है या पत्तियां छोटी रहकर फूल नहीं आते।", symptomsEn = "Sudden wilting or bushy sterility mosaic.", organicRemedyHi = "ट्राइकोडर्मा विरिडी 2 किग्रा गोबर में मिलाकर डालें।", chemicalControlHi = "प्रोपिकोनाजोल @ 1 मिली/लीटर स्प्रे।")
        ),
        irrigationScheduleList = listOf("1. बुवाई के समय पर्याप्त नमी", "2. शाखाएं बनते समय (40-45 दिन)", "3. फूल व फली बनते समय (100-110 दिन)"),
        financials = CropFinancials("7 - 10 क्विंटल / एकड़", "11,500 ₹ / एकड़", "56,000 ₹ (Mandi ₹7,000/Q)", "44,500 ₹ / एकड़")
    )

    private val bajraDetail = CropAgronomyDetail(
        id = "bajra", nameEn = "Pearl Millet / Bajra", nameHi = "बाजरा", category = "grains", categoryHi = "अनाज", season = "kharif", seasonHi = "खरीफ", durationDays = 85,
        soilSuitabilityHi = "हल्की बलुई से बलुई दोमट भूमि, कम पानी वाली (pH 6.5 - 8.0)", seedRateHi = "1.5 से 2.0 किग्रा प्रति एकड़", spacingHi = "45 x 12 सेमी",
        recommendedVarietiesHi = listOf("पायनियर 86M84", "पायनियर 86M88", "एचएचबी 67 इम्प्रूव्ड", "कावेरी सुपर बॉस", "प्रोएग्रो 9444"),
        stages = listOf(
            CropStageInfo(1, "0-5 DAS", "बुवाई व अंकुरण", "Germination", "नमी में 2-3 सेमी गहराई पर बोएं।", "Sow treated seed at 45x12cm."),
            CropStageInfo(2, "20-25 DAS", "विरलीकरण व कल्ले फूटना", "Thinning & Tillering", "अतिरिक्त पौधे उखाड़कर 12 सेमी दूरी रखें व यूरिया डालें।", "Thinning & Urea top dress."),
            CropStageInfo(3, "45-55 DAS", "सिट्टा निकलना (Heading)", "Booting & Heading", "सिट्टे में दाना भराव अवस्था।", "Panicle emergence stage."),
            CropStageInfo(4, "80-85 DAS", "सिट्टे पकना व कटाई", "Harvesting", "सिट्टे कड़े व सुनहरे होने पर हंसिया से सिट्टों की कटाई।", "Harvest mature panicles.")
        ),
        fertigationSchedule = listOf(
            FertigationItem("Basal Sowing", "बुवाई के समय", "DAP 30 kg + MOP 15 kg + Zinc 5 kg", "डीएपी 30 किग्रा, पोटाश 15 किग्रा, जिंक सल्फेट 5 किग्रा প্রতি एकड़।", "गोबर खाद 3 टन प्रति एकड़।"),
            FertigationItem("25 DAS", "कल्ले फूटते समय", "Urea 30 kg / acre", "यूरिया 30 किग्रा प्रति एकड़।", "जीवामृत 200 लीटर।")
        ),
        pestDiseaseManagement = listOf(
            PestManagementItem("Downy Mildew (Green Ear / Jogia)", "डाउनी मिल्ड्यू / जोगिया रोग", symptomsHi = "सिट्टा दाने की जगह हरी पत्तियों के गुच्छे में बदल जाता है (Green Ear)।", symptomsEn = "Sclerospora graminicola transforming earhead into leafy structure.", organicRemedyHi = "रोगग्रस्त पौधों को उखाड़कर गाड़ दें + बीज उपचार ट्राइकोडर्मा से करें।", chemicalControlHi = "मेटालैक्सिल 35% WS @ 6 ग्राम प्रति किग्रा बीज उपचार।"),
            PestManagementItem("Ergot (Gondia Rog)", "अरगट / गोंदिया रोग", symptomsHi = "सिट्टे से चिपचिपा गुलाबी-भूरा गोंद जैसा रस निकलता है जो बाद में काला हो जाता है।", symptomsEn = "Sticky pinkish honeydew oozing from spikelets.", organicRemedyHi = "नमक के 10% घोल में बीज डालकर तैरते अरगट वाले बीज अलग करें।", chemicalControlHi = "मैनकोजेब 75% WP @ 400 ग्राम प्रति 150 लीटर पानी में सिट्टा निकलते समय स्प्रे करें।")
        ),
        irrigationScheduleList = listOf("1. वर्षा आधारित फसल (कम पानी)", "2. यदि सूखा पड़े तो सिट्टा निकलते समय (45-50 दिन) 1 सिंचाई दें"),
        financials = CropFinancials("14 - 18 क्विंटल / एकड़", "8,500 ₹ / एकड़", "38,000 ₹ (Mandi ₹2,250/Q)", "29,500 ₹ / एकड़")
    )

    private val okraKharifDetail = CropAgronomyDetail(
        id = "okra_kharif", nameEn = "Okra / Bhindi", nameHi = "भिंडी", category = "vegetables", categoryHi = "सब्जियां", season = "kharif", seasonHi = "खरीफ", durationDays = 90,
        soilSuitabilityHi = "जीवांश युक्त दोमट भूमि (pH 6.0 - 7.5)", seedRateHi = "3 से 4 किग्रा प्रति एकड़ (हाइब्रिड)", spacingHi = "45 x 30 सेमी",
        recommendedVarietiesHi = listOf("एडवांटा राधिका", "सिंजेंटा विंडी", "महिको मह्या 10", "नोन-यू सिंघम", "पूसा सावनी"),
        stages = listOf(
            CropStageInfo(1, "0-5 DAS", "बुवाई व अंकुरण", "Germination", "बीज को 12 घंटे भिगोकर 2-3 सेमी गहराई पर बोएं।", "Sow pre-soaked seeds at 45x30cm."),
            CropStageInfo(2, "25-30 DAS", "शाखाएं व फूल आना", "Flowering", "पहला फूल आना शुरू। सफेद मक्खी व फल छेदक की निगरानी।", "First flower flush; scout for whitefly."),
            CropStageInfo(3, "45-90 DAS", "नियमित भिंडी तुड़ाई", "Pickings", "हर 2 दिन में कोमल हरी भिंडी की तुड़ाई (20-25 तुड़ाई चक्र)।", "Pick tender pods every 48 hours.")
        ),
        fertigationSchedule = listOf(
            FertigationItem("Basal Sowing", "बुवाई के समय", "DAP 40 kg + MOP 25 kg + SSP 50 kg", "डीएपी 40 किग्रा, पोटाश 25 किग्रा, सिंगल सुपर फॉस्फेट 50 किग्रा।", "गोबर खाद 4 टन प्रति एकड़।"),
            FertigationItem("25 DAS", "फूल आते समय", "Urea 30 kg + 19:19:19 @ 1 kg spray", "यूरिया 30 किग्रा + 19:19:19 1 किग्रा प्रति 150 लीटर पानी।", "जीवामृत 200 लीटर।"),
            FertigationItem("50 DAS", "तुड़ाई के दौरान", "13:0:45 @ 1 kg + Calcium Nitrate @ 1 kg spray", "13:0:45 @ 1 किग्रा + कैल्शियम नाइट्रेट 1 किग्रा प्रति 150 लीटर पानी।", "दशपर्णी अर्क।")
        ),
        pestDiseaseManagement = listOf(
            PestManagementItem("Yellow Vein Mosaic Virus (YVMV)", "पीला शिरा मोज़ेक रोग (YVMV)", symptomsHi = "पत्तियों की नसें पीली पड़ जाती हैं और पूरा पौधा पीला होकर फल कड़े व सफेद हो जाते हैं।", symptomsEn = "Vein clearing and complete yellowing spread by whiteflies.", organicRemedyHi = "पीले चिपचिपे कार्ड 25/एकड़ + नीमास्त्र 5L/150L पानी।", chemicalControlHi = "डायफेंथियूरॉन 50% WP @ 250 ग्राम प्रति 150 लीटर पानी।"),
            PestManagementItem("Fruit & Shoot Borer", "तना व फल छेदक सुंडी", symptomsHi = "शुरुआत में टहनी का सिरा मुरझा जाता है व बाद में सुंडी भिंडी में छेद कर देती है।", symptomsEn = "Earias vittella boring into terminal shoots & pods.", organicRemedyHi = "फेरोमोन ट्रैप 8/एकड़ + अग्निअस्त्र 3 लीटर स्प्रे।", chemicalControlHi = "स्पिनोसैड 45% SC @ 60 मिली प्रति 150 लीटर पानी।")
        ),
        irrigationScheduleList = listOf("गर्मियों/बरसात के अनुसार हर 4-5 दिन में हल्की सिंचाई", "तुड़ाई के तुरंत बाद सिंचाई करें"),
        financials = CropFinancials("45 - 65 क्विंटल / एकड़", "18,000 ₹ / एकड़", "95,000 ₹ (Mandi ₹1,800/Q)", "77,000 ₹ / एकड़")
    )

    private val brinjalDetail = CropAgronomyDetail(
        id = "brinjal", nameEn = "Brinjal / Eggplant", nameHi = "बैंगन", category = "vegetables", categoryHi = "सब्जियां", season = "kharif", seasonHi = "खरीफ", durationDays = 140,
        soilSuitabilityHi = "जीवांश युक्त दोमट से चिकनी दोमट (pH 6.0 - 7.5)", seedRateHi = "100 से 120 ग्राम प्रति एकड़", spacingHi = "75 x 60 सेमी",
        recommendedVarietiesHi = listOf("वीएनआर 212", "सिंजेंटा 704", "कलश ललिता", "पूसा पर्पल क्लस्टर", "पूसा श्यामला"),
        stages = listOf(
            CropStageInfo(1, "0-30 DAS", "नर्सरी पौध तैयार करना", "Nursery", "प्रोट्रे में 30 दिन की पौध तैयार करें।", "Nursery stage."),
            CropStageInfo(2, "30-35 DAS", "खेत में रोपाई", "Transplanting", "75x60 सेमी पर मेड़ बनाकर रोपाई।", "Transplant on ridges."),
            CropStageInfo(3, "60-140 DAT", "नियमित बैंगन तुड़ाई", "Pickings", "हर 4-5 दिन में चमकदार फलों की तुड़ाई। तना व फल छेदक से बचाव।", "Continuous pickings every 4-5 days.")
        ),
        fertigationSchedule = listOf(
            FertigationItem("Basal Sowing", "रोपाई पूर्व", "DAP 50 kg + MOP 40 kg + Zinc 10 kg", "डीएपी 50 किग्रा, पोटाश 40 किग्रा, जिंक 10 किग्रा प्रति एकड़।", "गोबर खाद 5 टन प्रति एकड़।"),
            FertigationItem("30 DAT", "शाखाएं फूटते समय", "Urea 35 kg + 19:19:19 1 kg spray", "यूरिया 35 किग्रा + 19:19:19 1 किग्रा प्रति एकड़।", "जीवामृत 200 लीटर।"),
            FertigationItem("60 DAT", "फल लगते समय", "13:0:45 @ 1.5 kg + Boron 200 g spray", "13:0:45 @ 1.5 किग्रा + बोरॉन 200 ग्राम प्रति 150 लीटर पानी।", "दशपर्णी अर्क।")
        ),
        pestDiseaseManagement = listOf(
            PestManagementItem("Shoot & Fruit Borer", "तना व फल छेदक सुंडी", symptomsHi = "टहनियों के सिरे लटक कर सूख जाते हैं व बैंगन में छेद होकर मल निकलता है।", symptomsEn = "Leucinodes orbonalis boring into shoots and fruits.", organicRemedyHi = "ल्यूसिन-ल्यूर फेरोमोन ट्रैप 10/एकड़ + मुरझाई टहनियां काटकर गाड़ें + अग्निअस्त्र।", chemicalControlHi = "कोराजन @ 60 मिली या एम्पलीगो @ 100 मिली प्रति 150 लीटर पानी।"),
            PestManagementItem("Little Leaf Disease", "लघु पर्ण रोग (Little Leaf)", symptomsHi = "पत्तियां बहुत छोटी होकर झाड़ीनुमा गुच्छे में बदल जाती हैं व फल नहीं लगते।", symptomsEn = "Phytoplasma spread by leafhoppers.", organicRemedyHi = "रोगग्रस्त पौधे उखाड़कर नष्ट करें।", chemicalControlHi = "इमिडाक्लोप्रिड 17.8% SL @ 50 मिली प्रति 150 लीटर पानी।")
        ),
        irrigationScheduleList = listOf("रोपाई के बाद हर 5-7 दिन पर नियमित सिंचाई"),
        financials = CropFinancials("150 - 220 क्विंटल / एकड़", "32,000 ₹ / एकड़", "1,70,000 ₹ (Mandi ₹900/Q)", "1,38,000 ₹ / एकड़")
    )

    private val groundnutDetail = CropAgronomyDetail(
        id = "groundnut", nameEn = "Groundnut / Peanut", nameHi = "मूंगफली", category = "oilseeds", categoryHi = "तिलहन", season = "kharif", seasonHi = "खरीफ", durationDays = 115,
        soilSuitabilityHi = "हल्की भुरभुरी बलुई दोमट, जिसमें सुइयां (Pegs) आसानी से धंस सकें (pH 6.0 - 7.5)", seedRateHi = "40 से 45 किग्रा गिरी (Kernels) प्रति एकड़", spacingHi = "30 x 10 सेमी",
        recommendedVarietiesHi = listOf("जीजी-20", "टीएजी-24", "कादिरी-6", "जीजेजी-9", "आईसीजीवी-91114"),
        stages = listOf(
            CropStageInfo(1, "0-8 DAS", "बुवाई व अंकुरण", "Germination", "राइजोबियम कल्चर से उपचारित कर 5 सेमी गहराई पर बोएं।", "Sow treated kernels at 30x10cm."),
            CropStageInfo(2, "30-40 DAS", "फूल व सुइयां निकलना (Pegging)", "Pegging Stage", "सुइयां जमीन में धंसने की अति महत्वपूर्ण अवस्था। जिप्सम 200 किग्रा प्रति एकड़ डालें।", "Apply Gypsum 200kg/acre at pegging for pod fill."),
            CropStageInfo(3, "70-90 DAS", "फली दाना भराव (Pod Bulking)", "Pod Filling", "नमी बनाए रखें ताकि दाना मोटा बने।", "Ensure moisture during pod expansion."),
            CropStageInfo(4, "110-115 DAS", "परिपक्वता व खुदाई", "Harvesting", "पत्तियां पीली होने व फली का अंदरूनी छिलका कत्थई होने पर खुदाई।", "Harvest when inner shell turns dark brown.")
        ),
        fertigationSchedule = listOf(
            FertigationItem("Basal Sowing", "बुवाई के समय", "DAP 35 kg + MOP 25 kg + Zinc 10 kg / acre", "डीएपी 35 किग्रा, पोटाश 25 किग्रा, जिंक सल्फेट 10 किग्रा প্রতি एकड़।", "घनजीवामृत 300 किग्रा प्रति एकड़।"),
            FertigationItem("35 DAS (Pegging)", "सुइयां निकलते समय", "Gypsum 200 kg / acre at root zone", "जिप्सम 200 किग्रा प्रति एकड़ थाले में डालें (कैल्शियम व सल्फर हेतु)।", "जीवामृत 200 लीटर।")
        ),
        pestDiseaseManagement = listOf(
            PestManagementItem("White Grub (Gobar Ka Keeda)", "सफेद लट (White Grub)", symptomsHi = "सफेद सी-आकार की लट जमीन के अंदर जड़ों व फलियों को कुतरकर चट कर जाती है।", symptomsEn = "Holotrichia consanguinea feeding on root system.", organicRemedyHi = "मेटाहाइजियम एनीसोप्ली (जैविक फफूंद) 2 किग्रा/एकड़ गोबर में मिलाकर डालें।", chemicalControlHi = "क्लोरपायरीफॉस 20% EC @ 1.5 लीटर प्रति एकड़ सिंचाई पानी के साथ।"),
            PestManagementItem("Tikka Leaf Spot", "टिक्का रोग (Tikka Disease)", symptomsHi = "पत्तियों पर गोल काले-भूरे धब्बे जिनके चारों तरफ पीला घेरा होता है।", symptomsEn = "Cercospora leaf spots with yellow halos.", organicRemedyHi = "खट्टी छाछ 5 लीटर + तांबा अर्क स्प्रे।", chemicalControlHi = "टेबुकोनाजोल + ट्राइफ्लॉक्सीस्ट्रोबिन (नेटिवो) @ 120 ग्राम प्रति 150 लीटर पानी।")
        ),
        irrigationScheduleList = listOf("1. बुवाई पूर्व पलेवा", "2. सुइयां धंसते समय (35-40 दिन) - सबसे महत्वपूर्ण", "3. फली दाना भराव (70-75 दिन)"),
        financials = CropFinancials("12 - 16 क्विंटल / एकड़", "18,000 ₹ / एकड़", "75,000 ₹ (Mandi ₹5,200/Q)", "57,000 ₹ / एकड़")
    )

    private val muskmelonDetail = CropAgronomyDetail(
        id = "muskmelon", nameEn = "Muskmelon / Cantaloupe", nameHi = "खरबूजा", category = "fruits", categoryHi = "फल व बागवानी", season = "zaid", seasonHi = "जायद", durationDays = 80,
        soilSuitabilityHi = "रेतीली दोमट या जलोढ़ कछारी भूमि (pH 6.5 - 7.5)", seedRateHi = "1.0 किग्रा प्रति एकड़", spacingHi = "1.8 x 0.5 m",
        recommendedVarietiesHi = listOf("कुंदन (नोन-यू)", "बॉबी (सेमिनिस)", "काजल (सिंजेंटा)", "पूसा शरबती", "हरा मधु"),
        stages = listOf(
            CropStageInfo(1, "0-6 DAS", "बुवाई व अंकुरण", "Germination", "मल्चिंग बेड पर 2 सेमी गहराई पर बीज लगाएं।", "Sow seeds at 1.8x0.5m on mulch beds."),
            CropStageInfo(2, "30-40 DAS", "फूल व फल सेटिंग", "Flowering", "19:19:19 + बोरॉन स्प्रे। मधुमक्खियों का संरक्षण।", "Flowering & fruit set."),
            CropStageInfo(3, "70-80 DAS", "फल पकना व तुड़ाई", "Harvesting", "फल की जाली पूरी उभरने व डंठल आसानी से अलग (Slip stage) होने पर तुड़ाई।", "Harvest at full slip stage when aroma develops.")
        ),
        fertigationSchedule = listOf(
            FertigationItem("Basal Dressing", "बेड बनाते समय", "DAP 40 kg + MOP 35 kg + SSP 50 kg", "डीएपी 40 किग्रा, पोटाश 35 किग्रा, सिंगल सुपर फॉस्फेट 50 किग्रा।", "गोबर खाद 4 टन प्रति एकड़।"),
            FertigationItem("35 DAS", "फल बनते समय", "13:0:45 @ 3 kg + Boron 200 g weekly", "13:0:45 @ 3 किग्रा + बोरॉन 200 ग्राम प्रति सप्ताह ड्रिप से।", "जीवामृत 200 लीटर।")
        ),
        pestDiseaseManagement = listOf(
            PestManagementItem("Fruit Fly & Powdery Mildew", "फल मक्खी व चूर्णी फफूंद", symptomsHi = "मक्खी फल में डंक मारती है व पत्तियों पर सफेद चूर्ण छा जाता है।", symptomsEn = "Fruit fly stings & white powdery mildew.", organicRemedyHi = "फ्रूट फ्लाई ट्रैप 8/एकड़ + जादम सल्फर 200ml/100L पानी।", chemicalControlHi = "एक्रॉस @ 200 मिली प्रति 150 लीटर पानी।")
        ),
        irrigationScheduleList = listOf("ड्रिप सिंचाई: प्रतिदिन 1-1.5 घंटे", "तुड़ाई से 4 दिन पूर्व पानी बंद करने से मिठास बढ़ती है"),
        financials = CropFinancials("100 - 140 क्विंटल / एकड़", "28,000 ₹ / एकड़", "1,10,000 ₹ (Mandi ₹900/Q)", "82,000 ₹ / एकड़")
    )

    private val cucumberDetail = CropAgronomyDetail(
        id = "cucumber", nameEn = "Cucumber / Kheera", nameHi = "खीरा", category = "vegetables", categoryHi = "सब्जियां", season = "zaid", seasonHi = "जायद", durationDays = 60,
        soilSuitabilityHi = "जीवांश युक्त बलुई दोमट भूमि (pH 6.0 - 7.5)", seedRateHi = "800 ग्राम से 1 किग्रा प्रति एकड़", spacingHi = "1.5 x 0.5 m",
        recommendedVarietiesHi = listOf("सेमिनिस मालिनी", "सिंजेंटा ग्लॉसी", "ईस्ट वेस्ट नाजिया", "पूसा बरखा", "प्रिया"),
        stages = listOf(
            CropStageInfo(1, "0-5 DAS", "बुवाई व अंकुरण", "Germination", "बेड पर बीज बोएं।", "Sow seeds on mulch."),
            CropStageInfo(2, "20-25 DAS", "बेल वृद्धि व पहला फूल", "Vine & Bloom", "19:19:19 ड्रेंचिंग।", "Fertigation for vine growth."),
            CropStageInfo(3, "35-60 DAS", "नियमित खीरा तुड़ाई", "Pickings", "हर 1-2 दिन में कोमल सीधे खीरे की तुड़ाई।", "Pick tender cucumbers alternate days.")
        ),
        fertigationSchedule = listOf(
            FertigationItem("Basal Sowing", "बेड बनाते समय", "DAP 35 kg + MOP 25 kg + Zinc 5 kg", "डीएपी 35 किग्रा, पोटाश 25 किग्रा, जिंक 5 किग्रा।", "गोबर खाद 3 टन प्रति एकड़।"),
            FertigationItem("20 DAS", "तुड़ाई शुरू होने पर", "19:19:19 @ 3 kg + 13:0:45 @ 3 kg weekly", "19:19:19 @ 3 किग्रा + 13:0:45 @ 3 किग्रा प्रति सप्ताह ड्रिप से।", "जीवामृत 200 लीटर।")
        ),
        pestDiseaseManagement = listOf(
            PestManagementItem("Downy Mildew & Red Beetle", "डाउनी मिल्ड्यू व लाल भृंग", symptomsHi = "पत्तियों पर पीले चौकोर धब्बे व लाल कीड़ा पत्तियां कुतरता है।", symptomsEn = "Downy mildew and red pumpkin beetle.", organicRemedyHi = "राख का बुरकाव + जादम सल्फर 200ml/100L पानी।", chemicalControlHi = "एमिस्टार टॉप @ 200 मिली प्रति 150 लीटर पानी।")
        ),
        irrigationScheduleList = listOf("गर्मियों में प्रतिदिन ड्रिप सिंचाई 1 घंटा"),
        financials = CropFinancials("80 - 120 क्विंटल / एकड़", "22,000 ₹ / एकड़", "90,000 ₹ (Mandi ₹850/Q)", "68,000 ₹ / एकड़")
    )

    private val bottleGourdDetail = CropAgronomyDetail(
        id = "bottle_gourd", nameEn = "Bottle Gourd / Lauki", nameHi = "लौकी / घिया", category = "vegetables", categoryHi = "सब्जियां", season = "zaid", seasonHi = "जायद", durationDays = 90,
        soilSuitabilityHi = "जीवांश युक्त दोमट भूमि (pH 6.5 - 7.5)", seedRateHi = "1.5 से 2 किग्रा प्रति एकड़", spacingHi = "2.5 x 0.8 m",
        recommendedVarietiesHi = listOf("माहिको वारद", "सिंजेंटा अनोकिया", "वीएनआर सरिता", "पूसा नवीन", "काशी गंगा"),
        stages = listOf(
            CropStageInfo(1, "0-6 DAS", "बुवाई व अंकुरण", "Germination", "गड्ढों या थालों में 3-4 बीज बोएं।", "Sow 3-4 seeds per pit."),
            CropStageInfo(2, "30-40 DAS", "मचान / जाल चढ़ाना", "Trellising", "बेल को बांस-तार के मचान पर चढ़ाएं।", "Trellising on wire mesh."),
            CropStageInfo(3, "50-90 DAS", "लौकी तुड़ाई", "Pickings", "हर 3-4 दिन में मुलायम हरी लौकी की तुड़ाई।", "Pick tender gourds every 3 days.")
        ),
        fertigationSchedule = listOf(
            FertigationItem("Basal Pits", "थाले भरते समय", "DAP 35 kg + MOP 25 kg + SSP 50 kg", "डीएपी 35 किग्रा, पोटाश 25 किग्रा, एसएसपी 50 किग्रा प्रति एकड़।", "सड़ी गोबर खाद 4 टन प्रति एकड़।"),
            FertigationItem("35 DAS", "फूल आते समय", "19:19:19 @ 1 kg + 0:52:34 @ 1 kg spray", "19:19:19 @ 1 किग्रा + 0:52:34 @ 1 किग्रा प्रति 150 लीटर पानी।", "जीवामृत 200 लीटर।")
        ),
        pestDiseaseManagement = listOf(
            PestManagementItem("Fruit Fly & Mosaic", "फल मक्खी व मोज़ेक रोग", symptomsHi = "लौकी टेढ़ी होकर अंदर से सड़ जाती है व पत्तियां पीली पड़ जाती हैं।", symptomsEn = "Fruit fly maggots and mosaic virus.", organicRemedyHi = "फ्रूट फ्लाई ट्रैप 8/एकड़ + नीमास्त्र 5L/150L पानी।", chemicalControlHi = "मैलाथियान 50% EC @ 300 मिली + 500 ग्राम गुड़ प्रति 150 लीटर पानी।")
        ),
        irrigationScheduleList = listOf("हर 4-5 दिन पर थाला सिंचाई"),
        financials = CropFinancials("120 - 180 क्विंटल / एकड़", "20,000 ₹ / एकड़", "95,000 ₹ (Mandi ₹600/Q)", "75,000 ₹ / एकड़")
    )

    private val bitterGourdDetail = CropAgronomyDetail(
        id = "bitter_gourd", nameEn = "Bitter Gourd / Karela", nameHi = "करेला", category = "vegetables", categoryHi = "सब्जियां", season = "zaid", seasonHi = "जायद", durationDays = 100,
        soilSuitabilityHi = "अच्छी जल निकासी वाली दोमट भूमि (pH 6.5 - 7.5)", seedRateHi = "1.5 किग्रा प्रति एकड़", spacingHi = "2.0 x 0.6 m",
        recommendedVarietiesHi = listOf("वीएनआर आकाश", "सिंजेंटा प्राची", "ईस्ट वेस्ट पालि", "पूसा विशेष", "पूसा दो मौसमी"),
        stages = listOf(
            CropStageInfo(1, "0-8 DAS", "बुवाई व अंकुरण", "Germination", "बीज को गुनगुने पानी में भिगोकर बोएं।", "Sow pre-soaked seeds."),
            CropStageInfo(2, "30-40 DAS", "मचान चढ़ाई व फूल", "Trellising & Bloom", "तार मचान पर बेल चढ़ाएं।", "Trellis vines on wire trellis."),
            CropStageInfo(3, "55-100 DAS", "नियमित करेला तुड़ाई", "Pickings", "हर 3 दिन में गहरे हरे करेले की तुड़ाई।", "Pick dark green spiny fruits.")
        ),
        fertigationSchedule = listOf(
            FertigationItem("Basal", "बुवाई पूर्व", "DAP 40 kg + MOP 30 kg + SSP 50 kg", "डीएपी 40 किग्रा, पोटाश 30 किग्रा, सिंगल सुपर फॉस्फेट 50 किग्रा।", "गोबर खाद 4 टन प्रति एकड़।"),
            FertigationItem("40 DAS", "फल लगते समय", "19:19:19 @ 3 kg + 13:0:45 @ 3 kg weekly", "19:19:19 @ 3 किग्रा + 13:0:45 @ 3 किग्रा ड्रिप से।", "जीवामृत 200 लीटर।")
        ),
        pestDiseaseManagement = listOf(
            PestManagementItem("Fruit Fly & Downy Mildew", "फल मक्खी व डाउनी मिल्ड्यू", symptomsHi = "करेले पर डंक के निशान व पीला पड़ना।", symptomsEn = "Fruit fly puncture marks and downy mildew.", organicRemedyHi = "फ्रूट फ्लाई ट्रैप 8/एकड़ + जादम सल्फर।", chemicalControlHi = "प्रोफाइलर @ 400 ग्राम प्रति 150 लीटर पानी।")
        ),
        irrigationScheduleList = listOf("ड्रिप सिंचाई 1 दिन छोड़कर 1.5 घंटे"),
        financials = CropFinancials("60 - 90 क्विंटल / एकड़", "26,000 ₹ / एकड़", "1,20,000 ₹ (Mandi ₹1,500/Q)", "94,000 ₹ / एकड़")
    )

    private val moongZaidDetail = CropAgronomyDetail(
        id = "moong_zaid", nameEn = "Summer Moong", nameHi = "जायद मूंग", category = "pulses", categoryHi = "दालें", season = "zaid", seasonHi = "जायद", durationDays = 65,
        soilSuitabilityHi = "हल्की दोमट से मटियार दोमट भूमि (pH 6.5 - 7.5)", seedRateHi = "8 से 10 किग्रा प्रति एकड़", spacingHi = "25 x 5 सेमी",
        recommendedVarietiesHi = listOf("विराट (IPM 205-7)", "शिखा (IPM 410-3)", "स्टार 444", "पूसा विशाल", "एसएमएल 668"),
        stages = listOf(
            CropStageInfo(1, "0-5 DAS", "बुवाई व अंकुरण", "Germination", "गेहूं कटाई के तुरंत बाद पलेवा देकर 25x5 सेमी पर बोएं।", "Sow immediately after wheat harvest."),
            CropStageInfo(2, "20-25 DAS", "शाखाएं व फूल आना", "Flowering", "सफेद मक्खी की रोकथाम (पीला मोज़ेक से बचाव)। 19:19:19 स्प्रे।", "First light irrigation & whitefly scout."),
            CropStageInfo(3, "55-65 DAS", "फलियां पकना व कटाई", "Harvesting", "फलियां 80% काली होने पर 1 या 2 बार में तुड़ाई/कटाई।", "Harvest when 80% pods turn black.")
        ),
        fertigationSchedule = listOf(
            FertigationItem("Basal Sowing", "बुवाई के समय", "DAP 30 kg + SSP 50 kg + MOP 15 kg", "डीएपी 30 किग्रा, सिंगल सुपर फॉस्फेट 50 किग्रा, पोटाश 15 किग्रा प्रति एकड़।", "घनजीवामृत 250 किग्रा प्रति एकड़।"),
            FertigationItem("30 DAS", "फूल आते समय", "19:19:19 @ 1 kg + Boron 100 g spray", "19:19:19 @ 1 किग्रा + बोरॉन 100 ग्राम प्रति 150 लीटर पानी।", "जीवामृत 10% स्प्रे।")
        ),
        pestDiseaseManagement = listOf(
            PestManagementItem("Yellow Mosaic Virus (YMV)", "पीला मोज़ेक वायरस (YMV)", symptomsHi = "पत्तियां चमकीली पीली पड़ जाती हैं और फलियां नहीं बनतीं। सफेद मक्खी द्वारा फैलता है।", symptomsEn = "Bright yellow leaf mosaic spread by whitefly.", organicRemedyHi = "पीले चिपचिपे ट्रैप 20/एकड़ + नीमास्त्र 5% स्प्रे।", chemicalControlHi = "थियामेथोक्सम 25% WG @ 80 ग्राम प्रति 150 लीटर पानी।")
        ),
        irrigationScheduleList = listOf("1. बुवाई पूर्व पलेवा", "2. पहली सिंचाई 20-25 दिन पर", "3. फली दाना भराव पर (45 दिन पर)"),
        financials = CropFinancials("5 - 7 क्विंटल / एकड़", "6,500 ₹ / एकड़", "38,000 ₹ (Mandi ₹6,800/Q)", "31,500 ₹ / एकड़")
    )

    private val guavaDetail = CropAgronomyDetail(
        id = "guava", nameEn = "Guava Orchard", nameHi = "अमरूद बाग", category = "fruits", categoryHi = "फल व बागवानी", season = "perennial", seasonHi = "बारहमासी", durationDays = 365,
        soilSuitabilityHi = "बलुई दोमट से चिकनी दोमट (pH 6.5 - 8.2)", seedRateHi = "110 पौधे (6x6m) या 400 पौधे (सघन 3x3m) / एकड़", spacingHi = "6 x 6 m या 3 x 3 m",
        recommendedVarietiesHi = listOf("वीएनआर बीही (जंबो आकार)", "इलाहाबाद सफेदा", "लखनऊ-49 (सरदार)", "अर्का किरण", "श्वेता"),
        stages = listOf(
            CropStageInfo(1, "मई - जून", "बहार उपचार (Bahar Treatment)", "Bahar Treatment", "मृग बहार (सर्दियों की फसल) लेने हेतु मई में पानी बंद करें व प्रूनिंग करें।", "Withhold water in May to force winter crop."),
            CropStageInfo(2, "जुलाई - अगस्त", "फूल व फल सेट", "Fruit Set", "खाद देना व सिंचाई शुरू। फल मक्खी नियंत्रण।", "Apply manuring & resume irrigation."),
            CropStageInfo(3, "नवंबर - जनवरी", "सर्दियों के मीठे अमरूदों की तुड़ाई", "Harvesting", "सर्दियों के क्रिस्पी अमरूद की तुड़ाई (कीट मुक्त व उत्तम भाव)।", "Winter harvest with high market price.")
        ),
        fertigationSchedule = listOf(
            FertigationItem("June (Basal)", "जून में खाद देना", "FYM 25 kg + DAP 500g + Potash 500g / tree", "सड़ी गोबर खाद 25 किग्रा, डीएपी 500 ग्राम, पोटाश 500 ग्राम प्रति पेड़ थाले में।", "घनजीवामृत 3 किग्रा प्रति पेड़।"),
            FertigationItem("August (Fruit Bulking)", "फल बढ़ते समय", "13:0:45 @ 5 g/L + Boron 1.5 g/L spray", "13:0:45 @ 5 ग्राम + बोरॉन 1.5 ग्राम प्रति लीटर पानी स्प्रे।", "दशपर्णी अर्क स्प्रे।")
        ),
        pestDiseaseManagement = listOf(
            PestManagementItem("Fruit Fly & Guava Wilt", "फल मक्खी व उकठा रोग (Wilt)", symptomsHi = "अमरूद में कीड़े पड़ना व पेड़ का अचानक पीला पड़कर सूखना।", symptomsEn = "Fruit fly maggots and Fusarium oxysporum wilt.", organicRemedyHi = "फ्रूट फ्लाई ट्रैप 10/एकड़ + ट्राइकोडर्मा विरिडी 50 ग्राम/पेड़ थाले में।", chemicalControlHi = "मैलाथियान 2 मिली + 20 ग्राम गुड़/लीटर स्प्रे।")
        ),
        irrigationScheduleList = listOf("मई में पानी बंद रखें, जुलाई से नवंबर हर 10-12 दिन में सिंचाई"),
        financials = CropFinancials("80 - 140 क्विंटल / एकड़", "25,000 ₹ / एकड़ / वर्ष", "1,60,000 ₹ (Mandi ₹1,400/Q)", "1,35,000 ₹ / एकड़")
    )

    private val pomegranateDetail = CropAgronomyDetail(
        id = "pomegranate", nameEn = "Pomegranate", nameHi = "अनार (भगवा)", category = "fruits", categoryHi = "फल व बागवानी", season = "perennial", seasonHi = "बारहमासी", durationDays = 365,
        soilSuitabilityHi = "उत्तम जल निकास वाली हल्की दोमट (pH 6.5 - 8.0)", seedRateHi = "300 - 400 पौधे / एकड़ (4x3m या 4.5x3m)", spacingHi = "4.5 x 3 m",
        recommendedVarietiesHi = listOf("भगवा (सिंदूरी - सर्वाधिक मांग)", "आरक्ता", "गणेश", "रूबी", "मृदुला"),
        stages = listOf(
            CropStageInfo(1, "बहार प्रबंधन", "हस्त / अंबे बहार प्रबंधन", "Bahar Selection", "पानी का तनाव देकर पतझड़ कराना व प्रूनिंग।", "Defoliation via water stress."),
            CropStageInfo(2, "फूल व फल विकास", "फूल व फल सेटिंग", "Fruit Growth", "बोरॉन व कैल्शियम स्प्रे ताकि दाना रसीला व लाल बने और फल फटने से बचे।", "Boron/Calcium to prevent fruit cracking."),
            CropStageInfo(3, "तुड़ाई", "चमकदार लाल अनार तुड़ाई", "Harvesting", "छिलका गहरा लाल-सिंदूरी होने पर कैंची से तुड़ाई।", "Harvest glossy deep-red pomegranates.")
        ),
        fertigationSchedule = listOf(
            FertigationItem("Bahar Basal", "बहार शुरू करते समय", "FYM 20 kg + DAP 500g + MOP 500g + Micronutrients 100g", "गोबर खाद 20 किग्रा, डीएपी 500 ग्राम, पोटाश 500 ग्राम, सूक्ष्म पोषक 100 ग्राम प्रति पौधा।", "घनजीवामृत 2 किग्रा प्रति पौधा।"),
            FertigationItem("Fruit Bulking", "फल बढ़ते समय", "13:0:45 @ 4 kg + Calcium Nitrate 3 kg weekly via Drip", "13:0:45 @ 4 किग्रा + कैल्शियम नाइट्रेट 3 किग्रा प्रति एकड़ प्रति सप्ताह ड्रिप से।", "जीवामृत 200 लीटर।")
        ),
        pestDiseaseManagement = listOf(
            PestManagementItem("Bacterial Blight (Telya)", "तेल्या रोग / बैक्टीरियल ब्लाइट (Telya)", symptomsHi = "पत्तियों व फलों पर तेल जैसे काले उभरे हुए चौकोर धब्बे और फल का फटना।", symptomsEn = "Xanthomonas axonopodis pv. punicae causing oily black spots on rind.", organicRemedyHi = "बोर्डो मिश्रण 1% या जादम सल्फर + जादम वेटिंग एजेंट स्प्रे।", chemicalControlHi = "स्ट्रेप्टोसाइक्लिन 20 ग्राम + कॉपर ऑक्सीक्लोराइड 500 ग्राम प्रति 200 लीटर पानी।"),
            PestManagementItem("Fruit Borer (Anar Butterfly)", "अनार तितली / फल छेदक (Anar Butterfly)", symptomsHi = "सुंडी फल में छेद करती है जिससे बदबूदार लिक्विड बाहर निकलता है।", symptomsEn = "Deudorix isocrates boring into fruit.", organicRemedyHi = "फलों पर बटर पेपर बैग बांधें (Fruit Bagging) + नीमास्त्र।", chemicalControlHi = "कोराजन @ 60 मिली प्रति 150 लीटर पानी।")
        ),
        irrigationScheduleList = listOf("ड्रिप सिंचाई: बहार के अनुसार नियमित 1.5 घंटे प्रतिदिन"),
        financials = CropFinancials("60 - 90 क्विंटल / एकड़", "55,000 ₹ / एकड़ / वर्ष", "3,20,000 ₹ (Mandi ₹4,000/Q)", "2,65,000 ₹ / एकड़")
    )

    private val bananaDetail = CropAgronomyDetail(
        id = "banana", nameEn = "Banana (Grand Naine)", nameHi = "केला (ग्रैंड नैने G-9)", category = "fruits", categoryHi = "फल व बागवानी", season = "perennial", seasonHi = "बारहमासी", durationDays = 330,
        soilSuitabilityHi = "जीवांश युक्त गहरी भारी दोमट मिट्टी (pH 6.5 - 7.5)", seedRateHi = "1200 - 1450 टिशू कल्चर पौधे / एकड़", spacingHi = "1.5 x 1.5 m या 1.8 x 1.5 m",
        recommendedVarietiesHi = listOf("ग्रैंड नैने (G-9 टिशू कल्चर)", "रोबस्टा", "महालक्ष्मी", "ड्वार्फ कैवेंडिश", "रेड बनाना"),
        stages = listOf(
            CropStageInfo(1, "0-30 DAT", "टिशू कल्चर पौध रोपाई", "Transplanting", "गड्ढों में टिशू कल्चर पौधे लगाएं व पहली ड्रिप सिंचाई।", "Plant hardened tissue culture plantlets."),
            CropStageInfo(2, "3-5 Months", "तीव्र वानस्पतिक वृद्धि", "Vegetative", "हर सप्ताह पोटाश व यूरिया ड्रिप द्वारा। बगल के कल्ले (Suckers) काटें।", "High Nitrogen/Potash fertigation & de-suckering."),
            CropStageInfo(3, "7-8 Months", "कमल निकलना (Shooting / Bunch Emergence)", "Bunch Emergence", "घार/घारी निकलना। नर फूल (दिल) तोड़ना (Denavelling) व बंच कवर लगाना।", "Bunch emergence, denavelling & bunch bagging."),
            CropStageInfo(4, "10-11 Months", "केले की घार कटाई", "Harvesting", "केले के फल गोल व धारियां मिटने पर घार की कटाई। प्रति पौधा 25-35 किग्रा।", "Harvest 25-35kg bunches at 85% maturity.")
        ),
        fertigationSchedule = listOf(
            FertigationItem("Basal Pit", "गड्ढा भरते समय", "FYM 10 kg + SSP 200g + Neem Cake 250g / pit", "गोबर खाद 10 किग्रा, एसएसपी 200 ग्राम, नीम खली 250 ग्राम प्रति गड्ढा।", "घनजीवामृत 1 किग्रा प्रति गड्ढा।"),
            FertigationItem("2-7 Months (Vegetative)", "वृद्धि काल में", "Urea 5 kg + NPK 19:19:19 5 kg + MOP 5 kg weekly per acre", "यूरिया 5 किग्रा + 19:19:19 5 किग्रा + पोटाश 5 किग्रा प्रति एकड़ प्रति सप्ताह ड्रिप से।", "जीवामृत 200 लीटर।"),
            FertigationItem("8-10 Months (Bunch Filling)", "घार दाना भराव", "0:0:50 (Potash) 8 kg + Calcium Nitrate 4 kg weekly", "एनपीके 0:0:50 @ 8 किग्रा + कैल्शियम नाइट्रेट 4 किग्रा प्रति एकड़ प्रति सप्ताह घार वजन हेतु।", "राख का अर्क।")
        ),
        pestDiseaseManagement = listOf(
            PestManagementItem("Sigatoka Leaf Spot", "सिगाटोका पत्ती धब्बा रोग", symptomsHi = "पत्तियों पर पीले-भूरे अण्डाकार धब्बे बनते हैं और पत्तियां समय से पहले सूख जाती हैं।", symptomsEn = "Pseudocercospora musae causing dark spindle leaf spots.", organicRemedyHi = "सूखी पत्तियां काटकर नष्ट करें + जादम सल्फर 200ml/100L पानी।", chemicalControlHi = "प्रोपिकोनाजोल 25% EC @ 200 मिली + मिनरल ऑयल (पेट्रोलियम स्प्रे ऑयल) 1 लीटर प्रति 200L पानी।"),
            PestManagementItem("Rhizome Weevil & Nematodes", "कंद घुन व सूत्रकृमि (Nematodes)", symptomsHi = "कीट कंद में छेद कर देता है, पत्तियां पीली पड़कर पौधा गिर जाता है।", symptomsEn = "Cosmopolites sordidus tunneling in corm.", organicRemedyHi = "पेसीलोमाइसिस लिलासिनस 2 किग्रा गोबर में मिलाकर डालें।", chemicalControlHi = "कार्बोफ्यूरॉन 3G @ 20 ग्राम प्रति पौधा रोपाई के समय डालें।")
        ),
        irrigationScheduleList = listOf("ड्रिप सिंचाई: गर्मियों में प्रतिदिन 2-2.5 घंटे (केला भारी जल मांग वाली फसल है)"),
        financials = CropFinancials("350 - 500 क्विंटल / एकड़", "75,000 ₹ / एकड़", "3,20,000 ₹ (Mandi ₹800/Q)", "2,45,000 ₹ / एकड़")
    )
}
