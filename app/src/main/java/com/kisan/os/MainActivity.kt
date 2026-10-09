package com.kisan.os

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.kisan.os.api.KisanApiService
import com.kisan.os.models.*
import com.kisan.os.ui.screens.*
import com.kisan.os.ui.theme.DarkColorScheme
import com.kisan.os.ui.theme.LightColorScheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

const val SERVER_BASE_URL = "https://pest-procurement-dynamic-eden.trycloudflare.com/"

class MainActivity : ComponentActivity() {

    private lateinit var apiService: KisanApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        apiService = KisanApiService.create(SERVER_BASE_URL)

        val prefs = getSharedPreferences("kisan_prefs", Context.MODE_PRIVATE)
        val hasSelectedLanguage = prefs.getBoolean("has_selected_language", false)
        val savedLang = prefs.getString("selected_language", "hi") ?: "hi"
        val savedTheme = prefs.getBoolean("is_dark_theme", false)

        setContent {
            // One-time language selection after fresh install
            var isFirstLaunch by remember { mutableStateOf(!hasSelectedLanguage) }
            var currentLang by remember { mutableStateOf(savedLang) }
            var isDarkTheme by remember { mutableStateOf(savedTheme) }
            var currentScreen by remember { mutableStateOf("hub") }

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
                        DynamicTile("t7", "GIS Land Area", "GIS Land & Polyline", "खेत नक्शा व रकबा नाप", "gis", "map", "#3B82F6", "gis_polyline", 7),
                        DynamicTile("t8", "Agri News & Schemes", "Agri News & Schemes", "कृषि समाचार व योजनाएं", "news", "newspaper", "#0D9488", "agri_news", 8)
                    )
                )
            }

            var seeds by remember {
                mutableStateOf(emptyList<SeedVariety>())
            }

            var recipes by remember {
                mutableStateOf(emptyList<OrganicRecipe>())
            }

            var newsArticles by remember {
                mutableStateOf(emptyList<AgriNewsItem>())
            }

            var sprayAdvisory by remember {
                mutableStateOf<SprayAdvisory?>(null)
            }

            var isNewsLoading by remember { mutableStateOf(false) }

            val scope = rememberCoroutineScope()

            fun fetchNews(forceRefresh: Boolean = false) {
                scope.launch {
                    isNewsLoading = true
                    try {
                        if (forceRefresh) {
                            withContext(Dispatchers.IO) { apiService.refreshAgriNews(force = true) }
                        }
                        val newsResp = withContext(Dispatchers.IO) {
                            apiService.getAgriNews(category = null, lang = currentLang, limit = 50)
                        }
                        val articles = if (newsResp.news.isNotEmpty()) newsResp.news else newsResp.articles
                        if (articles.isNotEmpty()) {
                            newsArticles = articles
                        }
                    } catch (e: Exception) {
                        // Fallback gracefully
                    } finally {
                        isNewsLoading = false
                    }
                }
            }

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

                        try {
                            val adv = withContext(Dispatchers.IO) {
                                apiService.getSprayAdvisory(temp = 28.5, humidity = 58.0, windSpeed = 8.2, rainForecast = false, stage = "vegetative", lang = currentLang)
                            }
                            sprayAdvisory = adv
                        } catch (e: Exception) {
                            // Local fallback
                        }
                    } catch (e: Exception) {
                        // Keep built-in seeds and recipes
                    }
                }
                fetchNews(forceRefresh = false)
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
                                prefs.edit()
                                    .putBoolean("has_selected_language", true)
                                    .putString("selected_language", lang)
                                    .apply()
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
                                onLanguageToggle = {
                                    val nextLang = if (currentLang == "hi") "en" else "hi"
                                    currentLang = nextLang
                                    prefs.edit().putString("selected_language", nextLang).apply()
                                },
                                onThemeToggle = {
                                    val nextTheme = !isDarkTheme
                                    isDarkTheme = nextTheme
                                    prefs.edit().putBoolean("is_dark_theme", nextTheme).apply()
                                },
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
                                apiService = apiService,
                                onBack = { currentScreen = "hub" }
                            )
                            "weather_advisory" -> WeatherAdvisoryScreen(
                                advisory = sprayAdvisory,
                                currentLang = currentLang,
                                onBack = { currentScreen = "hub" }
                            )
                            "gis_polyline" -> GisPolylineScreen(
                                currentLang = currentLang,
                                onBack = { currentScreen = "hub" }
                            )
                            "agri_news" -> AgriNewsScreen(
                                newsArticles = newsArticles,
                                currentLang = currentLang,
                                isLoading = isNewsLoading,
                                onRefresh = { fetchNews(forceRefresh = true) },
                                onBack = { currentScreen = "hub" }
                            )
                            else -> MainHubScreen(
                                tiles = tiles,
                                currentLang = currentLang,
                                isDarkTheme = isDarkTheme,
                                onLanguageToggle = {
                                    val nextLang = if (currentLang == "hi") "en" else "hi"
                                    currentLang = nextLang
                                    prefs.edit().putString("selected_language", nextLang).apply()
                                },
                                onThemeToggle = {
                                    val nextTheme = !isDarkTheme
                                    isDarkTheme = nextTheme
                                    prefs.edit().putBoolean("is_dark_theme", nextTheme).apply()
                                },
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
