package com.kisan.os

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.kisan.os.api.KisanApiService
import com.kisan.os.models.DynamicTile
import com.kisan.os.models.OrganicRecipe
import com.kisan.os.models.SeedVariety
import com.kisan.os.ui.screens.*
import com.kisan.os.ui.theme.DarkColorScheme
import com.kisan.os.ui.theme.LightColorScheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

const val SERVER_BASE_URL = "https://outcome-lift-future-holdings.trycloudflare.com/"

class MainActivity : ComponentActivity() {

    private lateinit var apiService: KisanApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        apiService = KisanApiService.create(SERVER_BASE_URL)

        setContent {
            var isFirstLaunch by remember { mutableStateOf(true) }
            var currentLang by remember { mutableStateOf("hi") }
            var isDarkTheme by remember { mutableStateOf(false) }
            var currentScreen by remember { mutableStateOf("hub") } // "hub", "auth", "seed_catalog", "organic_hub", "gis_polyline", "mandi_arbitrage"

            // Auth State
            var isLoggedIn by remember { mutableStateOf(false) }
            var userName by remember { mutableStateOf<String?>(null) }
            var authToken by remember { mutableStateOf<String?>(null) }

            var tiles by remember {
                mutableStateOf(
                    listOf(
                        DynamicTile("t1", "Seed Catalog", "Seeds & Taste Catalog", "उन्नत बीज एवं स्वाद किस्में", "knowledge", "spa", "#10B981", "seed_catalog", 1),
                        DynamicTile("t2", "Organic Recipes", "Organic & JADAM Hub", "जैविक व जादम खाद-दवा", "organic", "biotech", "#059669", "organic_hub", 2),
                        DynamicTile("t3", "GIS Land Area", "GIS Land & Polyline", "खेत नक्शा व रकबा नाप", "gis", "map", "#3B82F6", "gis_polyline", 3),
                        DynamicTile("t4", "Mandi Arbitrage", "150km Mandi Arbitrage", "150km मंडी भाव व मुनाफा", "market", "trending_up", "#F59E0B", "mandi_arbitrage", 4)
                    )
                )
            }

            var seeds by remember {
                mutableStateOf(
                    listOf(
                        SeedVariety("s1", "vegetable", "Tomato (टमाटर)", "Pusa Rohini", "ICAR-IARI", "Rich tangy flavor, ideal for gravy curries", "Aug-Nov & Dec-Feb", 110, "100-150g", "160-200 Q/Acre", "Early blight & fruit cracking resistant", "Semi-determinate, uniform red fruits", 4.9),
                        SeedVariety("s2", "vegetable", "Chilli (हरी मिर्च)", "US 341", "US Agriseeds", "High pungency (teekhi), glossy dark green", "June-July & Oct-Nov", 130, "80-100g", "80-120 Q/Acre (Green)", "LCV (Leaf Curl Virus) tolerant", "High market price in wholesale mandis", 4.8),
                        SeedVariety("s3", "grain", "Wheat (गेहूँ)", "DBW 327 (Karan Shivani)", "ICAR-IIWBR", "Superior chapati quality, sweet taste, high grain puffing", "Nov 01 - Nov 20", 155, "40-45 kg", "32-35 Q/Acre", "High resistance to Yellow & Brown Rust", "Biofortified with Zinc & Iron", 4.9),
                        SeedVariety("s4", "pulse", "Gram / Chana (चना)", "Pusa 3043", "ICAR-IARI", "Tender nutty desi taste, excellent for sattu/besan", "Oct 15 - Nov 10", 115, "25-30 kg", "10-12 Q/Acre", "High wilt and root rot resistance", "Semi-erect, drought resilient", 4.8)
                    )
                )
            }

            var recipes by remember {
                mutableStateOf(
                    listOf(
                        OrganicRecipe(
                            "r1", "jadam", "JADAM Sulfur (जादम सल्फर - JS)", "Organic broad-spectrum fungicide and miticide", 1, 365,
                            listOf(mapOf("item" to "Sulfur powder (99.9%)", "qty" to "25 kg"), mapOf("item" to "Caustic Soda (NaOH)", "qty" to "20 kg"), mapOf("item" to "Red clay powder", "qty" to "500 g")),
                            "Mix sulfur, clay, and rock salt in a 100L heat-resistant container. Add caustic soda and 50L water. Stir continuously with a wooden pole as self-boiling exothermic reaction reaches 80-100°C. Once liquid turns dark ruby red, allow to cool and settle for 24 hours. Decant clear supernatant.",
                            "1.5 to 2.0 Litres", "30 ml to 50 ml", "Powdery mildew, downy mildew, red mites, fungal spots", "Wear eye protection and gloves. Exothermic reaction produces intense heat."
                        ),
                        OrganicRecipe(
                            "r2", "zbnf", "Jeevamrut (जीवामृत)", "Soil biological inoculant & microbial booster", 3, 7,
                            listOf(mapOf("item" to "Desi Cow Dung", "qty" to "10 kg"), mapOf("item" to "Desi Cow Urine", "qty" to "10 L"), mapOf("item" to "Jaggery (Gud)", "qty" to "2 kg"), mapOf("item" to "Besan (Gram flour)", "qty" to "2 kg"), mapOf("item" to "Virgin Soil", "qty" to "Handful")),
                            "In a 200L barrel, mix cow dung and urine. Add jaggery and besan dissolved in water, plus virgin soil. Fill with 200L water. Stir clockwise 2-3 minutes twice daily in shade. Ready in 48-72 hours when sweet fermentation aroma appears.",
                            "200 Litres (Flood/Drip)", "100 ml (Foliar spray)", "Enriches soil microbiology, unlocks fixed NPK, promotes root growth", "Use within 7 days of preparation. Keep barrel in shade covered with jute bag."
                        )
                    )
                )
            }

            val scope = rememberCoroutineScope()

            LaunchedEffect(currentLang) {
                scope.launch {
                    try {
                        val tilesResp = withContext(Dispatchers.IO) { apiService.getDynamicTiles(currentLang) }
                        tiles = tilesResp["tiles"] ?: tiles

                        val recipesResp = withContext(Dispatchers.IO) { apiService.getOrganicRecipes(lang = currentLang) }
                        recipes = recipesResp["recipes"] ?: recipes
                    } catch (e: Exception) {
                        // Keep built-in seeds and recipes
                    }
                }
            }

            MaterialTheme(colorScheme = if (isDarkTheme) DarkColorScheme else LightColorScheme) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    if (isFirstLaunch) {
                        LanguageSelectionScreen(
                            onLanguageSelected = { lang ->
                                currentLang = lang
                                isFirstLaunch = false
                            }
                        )
                    } else {
                        when (currentScreen) {
                            "auth" -> AuthScreen(
                                lang = currentLang,
                                onAuthSuccess = { user, token ->
                                    isLoggedIn = true
                                    userName = user
                                    authToken = token
                                    currentScreen = "hub"
                                },
                                onSkip = { currentScreen = "hub" }
                            )
                            "hub" -> MainHubScreen(
                                tiles = tiles,
                                currentLang = currentLang,
                                isDarkTheme = isDarkTheme,
                                onLanguageToggle = { currentLang = if (currentLang == "hi") "en" else "hi" },
                                onThemeToggle = { isDarkTheme = !isDarkTheme },
                                onTileClick = { route -> currentScreen = route },
                                onAuthClick = { currentScreen = "auth" },
                                isLoggedIn = isLoggedIn,
                                userName = userName
                            )
                            "seed_catalog" -> SeedCatalogScreen(
                                seeds = seeds,
                                currentLang = currentLang,
                                onBack = { currentScreen = "hub" }
                            )
                            "organic_hub" -> OrganicHubScreen(
                                recipes = recipes,
                                currentLang = currentLang,
                                onBack = { currentScreen = "hub" }
                            )
                            "gis_polyline" -> GisPolylineScreen(
                                currentLang = currentLang,
                                onBack = { currentScreen = "hub" }
                            )
                            "mandi_arbitrage" -> MandiArbitrageScreen(
                                currentLang = currentLang,
                                onBack = { currentScreen = "hub" }
                            )
                            else -> MainHubScreen(
                                tiles = tiles,
                                currentLang = currentLang,
                                isDarkTheme = isDarkTheme,
                                onLanguageToggle = { currentLang = if (currentLang == "hi") "en" else "hi" },
                                onThemeToggle = { isDarkTheme = !isDarkTheme },
                                onTileClick = { route -> currentScreen = route },
                                onAuthClick = { currentScreen = "auth" },
                                isLoggedIn = isLoggedIn,
                                userName = userName
                            )
                        }
                    }
                }
            }
        }
    }
}
