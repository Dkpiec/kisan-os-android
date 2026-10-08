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
            var currentScreen by remember { mutableStateOf("hub") } // "hub", "auth", "seed_catalog", "organic_hub", "gis_polyline", "mandi_arbitrage", "protective_cultivation", "crop_selector", "weather_advisory"

            // Auth State
            var isLoggedIn by remember { mutableStateOf(false) }
            var userName by remember { mutableStateOf<String?>(null) }
            var authToken by remember { mutableStateOf<String?>(null) }

            var tiles by remember {
                mutableStateOf(
                    listOf(
                        DynamicTile("t1", "Seed Catalogue", "Seeds & Taste Catalog", "उन्नत बीज कैटलॉग", "agronomy", "spa", "#10B981", "seed_catalog", 1),
                        DynamicTile("t2", "Organic Farming", "Organic & JADAM Hub", "जैविक खेती एवं जादम", "organic", "biotech", "#059669", "organic_hub", 2),
                        DynamicTile("t3", "Protective Cultivation", "Polyhouse & Net House", "संरक्षित खेती तकनीक", "technology", "roofing", "#8B5CF6", "protective_cultivation", 3),
                        DynamicTile("t4", "Mandi Arbitrage", "150km Mandi Arbitrage", "150km मंडी आर्बिट्राज", "market", "trending_up", "#F59E0B", "mandi_arbitrage", 4),
                        DynamicTile("t5", "Crop Selector", "Smart Crop Planning", "फसल चयन व लाभ योजना", "planning", "psychology", "#EC4899", "crop_selector", 5),
                        DynamicTile("t6", "Weather Update", "Weather & Spray Advisories", "मौसम व छिड़काव परामर्श", "weather", "cloud", "#0EA5E9", "weather_advisory", 6),
                        DynamicTile("t7", "GIS Land Area", "GIS Land & Polyline", "खेत नक्शा व रकबा नाप", "gis", "map", "#3B82F6", "gis_polyline", 7)
                    )
                )
            }

            var seeds by remember {
                mutableStateOf(emptyList<SeedVariety>())
            }

            var recipes by remember {
                mutableStateOf(emptyList<OrganicRecipe>())
            }

            val scope = rememberCoroutineScope()

            LaunchedEffect(currentLang) {
                scope.launch {
                    try {
                        val tilesResp = withContext(Dispatchers.IO) { apiService.getDynamicTiles(currentLang) }
                        if (!tilesResp["tiles"].isNullOrEmpty()) {
                            tiles = tilesResp["tiles"]!!
                        }

                        val recipesResp = withContext(Dispatchers.IO) { apiService.getOrganicRecipes(lang = currentLang) }
                        if (!recipesResp["recipes"].isNullOrEmpty()) {
                            recipes = recipesResp["recipes"]!!
                        }

                        val seedsResp = withContext(Dispatchers.IO) { apiService.getSeedVarieties(lang = currentLang) }
                        if (!seedsResp["varieties"].isNullOrEmpty()) {
                            seeds = seedsResp["varieties"]!!
                        }
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
                            "protective_cultivation" -> ProtectiveCultivationScreen(
                                currentLang = currentLang,
                                onBack = { currentScreen = "hub" }
                            )
                            "mandi_arbitrage" -> MandiArbitrageScreen(
                                currentLang = currentLang,
                                onBack = { currentScreen = "hub" }
                            )
                            "crop_selector" -> CropSelectorScreen(
                                currentLang = currentLang,
                                onBack = { currentScreen = "hub" }
                            )
                            "weather_advisory" -> WeatherAdvisoryScreen(
                                advisory = null,
                                currentLang = currentLang,
                                onBack = { currentScreen = "hub" }
                            )
                            "gis_polyline" -> GisPolylineScreen(
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
