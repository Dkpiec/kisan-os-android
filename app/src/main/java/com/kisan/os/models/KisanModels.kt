package com.kisan.os.models

import com.google.gson.annotations.SerializedName

data class DynamicTile(
    @SerializedName("tile_id") val tileId: String,
    @SerializedName("title") val title: String = "",
    @SerializedName("title_en") val titleEn: String,
    @SerializedName("title_hi") val titleHi: String,
    @SerializedName("category") val category: String,
    @SerializedName("icon") val icon: String,
    @SerializedName("accent_color") val accentColor: String,
    @SerializedName("route") val route: String,
    @SerializedName("display_order") val displayOrder: Int = 0
)

data class SeedVariety(
    @SerializedName("id") val id: String,
    @SerializedName("category") val category: String,
    @SerializedName("crop_name") val cropName: String,
    @SerializedName("variety_name") val varietyName: String,
    @SerializedName("breeder_or_company") val breederOrCompany: String,
    @SerializedName("culinary_taste_profile") val tasteProfile: String?,
    @SerializedName("sowing_window") val sowingWindow: String,
    @SerializedName("maturity_duration_days") val maturityDays: Int,
    @SerializedName("seed_rate_per_acre") val seedRate: String,
    @SerializedName("expected_yield_quintal_per_acre") val expectedYield: String,
    @SerializedName("disease_resistances") val diseaseResistances: String?,
    @SerializedName("special_features") val specialFeatures: String?,
    @SerializedName("market_preference_rating") val rating: Double = 4.8
)

data class OrganicRecipe(
    @SerializedName("id") val id: String,
    @SerializedName("category") val category: String,
    @SerializedName("name") val name: String,
    @SerializedName("purpose") val purpose: String,
    @SerializedName("preparation_days") val preparationDays: Int,
    @SerializedName("shelf_life_days") val shelfLifeDays: Int?,
    @SerializedName("ingredients") val ingredients: List<Map<String, String>> = emptyList(),
    @SerializedName("step_by_step") val stepByStep: String,
    @SerializedName("dosage_per_acre") val dosagePerAcre: String,
    @SerializedName("dosage_per_pump_15l") val dosagePerPump: String,
    @SerializedName("targeted_pests_or_benefits") val targetedPests: String,
    @SerializedName("precautions") val precautions: String?
)

data class MandiArbitrageItem(
    @SerializedName("mandi_name") val mandiName: String,
    @SerializedName("district") val district: String,
    @SerializedName("state") val state: String,
    @SerializedName("distance_km") val distanceKm: Double,
    @SerializedName("modal_price_per_q") val modalPrice: Double,
    @SerializedName("gross_revenue") val grossRevenue: Double,
    @SerializedName("estimated_transport_cost") val transportCost: Double,
    @SerializedName("mandi_cess") val mandiCess: Double,
    @SerializedName("net_in_hand_payout") val netPayout: Double,
    @SerializedName("net_rate_per_quintal") val netRate: Double,
    @SerializedName("profit_rank") val profitRank: Int,
    @SerializedName("is_best_deal") val isBestDeal: Boolean = false
)

data class SprayAdvisory(
    @SerializedName("can_spray") val canSpray: Boolean,
    @SerializedName("spray_verdict_en") val verdictEn: String,
    @SerializedName("spray_verdict_hi") val verdictHi: String,
    @SerializedName("current_conditions") val conditions: Map<String, Double> = emptyMap(),
    @SerializedName("active_alerts") val alerts: List<Map<String, String>> = emptyList()
) {
    val temperatureC: Double get() = conditions["temperature_c"] ?: 26.5
    val windSpeedKmh: Double get() = conditions["wind_kph"] ?: conditions["wind_speed_kmh"] ?: 7.5
    val humidityPct: Double get() = conditions["humidity"] ?: conditions["humidity_pct"] ?: 58.0
}

data class PlotAreaResult(
    @SerializedName("plot_name") val plotName: String,
    @SerializedName("centroid") val centroid: Map<String, Double>,
    @SerializedName("measurements") val measurements: Map<String, Any>
)

data class PolyhouseCrop(
    val titleEn: String,
    val titleHi: String,
    val structureType: String,
    val recommendedCrops: List<String>,
    val expectedReturnPerAcre: String,
    val subsidyAvailable: String,
    val climateControlTips: String
)

data class CropSelectionAdvisory(
    val cropNameEn: String,
    val cropNameHi: String,
    val season: String,
    val soilType: String,
    val waterLevel: String,
    val estimatedProfit: String,
    val bestVarieties: String,
    val durationDays: Int
)

data class AgriNewsItem(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("title_hi") val titleHi: String? = null,
    @SerializedName("title_en") val titleEn: String? = null,
    @SerializedName("summary") val summary: String,
    @SerializedName("summary_hi") val summaryHi: String? = null,
    @SerializedName("summary_en") val summaryEn: String? = null,
    @SerializedName("content") val content: String? = null,
    @SerializedName("source_name") val sourceName: String,
    @SerializedName("source_url") val sourceUrl: String? = null,
    @SerializedName("image_url") val imageUrl: String? = null,
    @SerializedName("category") val category: String = "general",
    @SerializedName("published_at") val publishedAt: String
)

data class AgriNewsResponse(
    @SerializedName("total") val total: Int = 0,
    @SerializedName("count") val count: Int = 0,
    @SerializedName("articles") val articles: List<AgriNewsItem> = emptyList(),
    @SerializedName("news") val news: List<AgriNewsItem> = emptyList(),
    @SerializedName("operating_hours") val operatingHours: String? = null,
    @SerializedName("current_time_ist") val currentTimeIst: String? = null
)

data class CropStageInfo(
    @SerializedName("stage_num") val stageNum: Int = 1,
    @SerializedName("name_en") val nameEn: String = "",
    @SerializedName("name_hi") val nameHi: String = "",
    @SerializedName("days_after_sowing") val das: String = "",
    @SerializedName("key_operations_en") val operationsEn: String = "",
    @SerializedName("key_operations_hi") val operationsHi: String = ""
) {
    val stageNumber: Int get() = stageNum
    val stageNameHi: String get() = nameHi
    val stageNameEn: String get() = nameEn
    val daysRange: String get() = das
    val keyOperationsHi: String get() = operationsHi
    val keyOperationsEn: String get() = operationsEn
}

data class FertigationItem(
    @SerializedName("timing_en") val timingEn: String = "",
    @SerializedName("timing_hi") val timingHi: String = "",
    @SerializedName("fertilizers_en") val fertilizersEn: String = "",
    @SerializedName("fertilizers_hi") val fertilizersHi: String = "",
    @SerializedName("organic_alternative_hi") val organicAlternativeHi: String? = null,
    @SerializedName("chemical_dose") val chemicalDose: String = "",
    @SerializedName("organic_alternative") val organicAlternative: String = ""
) {
    val stageNameHi: String get() = timingHi
    val stageNameEn: String get() = timingEn
    val dosageNPKHi: String get() = fertilizersHi
    val dosageNPKEn: String get() = fertilizersEn
}

data class PestManagementItem(
    @SerializedName("name_en") val nameEn: String = "",
    @SerializedName("name_hi") val nameHi: String = "",
    @SerializedName("pest_name_hi") val pestNameHiVal: String = "",
    @SerializedName("pest_name_en") val pestNameEnVal: String = "",
    @SerializedName("symptoms_en") val symptomsEn: String = "",
    @SerializedName("symptoms_hi") val symptomsHi: String = "",
    @SerializedName("etl_threshold") val etlThreshold: String = "",
    @SerializedName("organic_remedy_hi") val organicRemedyHi: String = "",
    @SerializedName("organic_remedy_en") val organicRemedyEnVal: String = "",
    @SerializedName("chemical_control_hi") val chemicalControlHi: String? = null,
    @SerializedName("chemical_ipm_hi") val chemicalIPMHiVal: String = "",
    @SerializedName("chemical_ipm_en") val chemicalIPMEnVal: String = ""
) {
    val pestNameHi: String get() = if (pestNameHiVal.isNotEmpty()) pestNameHiVal else nameHi
    val pestNameEn: String get() = if (pestNameEnVal.isNotEmpty()) pestNameEnVal else nameEn
    val organicRemedyEn: String get() = organicRemedyEnVal
    val chemicalIPMHi: String get() = chemicalControlHi ?: chemicalIPMHiVal
    val chemicalIPMEn: String get() = chemicalIPMEnVal
}

data class CropFinancials(
    @SerializedName("expected_yield_quintal_per_acre") val expectedYield: String = "",
    @SerializedName("cost_of_cultivation_per_acre_inr") val costOfCultivation: String = "",
    @SerializedName("gross_revenue_inr") val grossRevenue: String = "",
    @SerializedName("net_profit_per_acre_inr") val netProfit: String = "",
    @SerializedName("yield_acre_quintals") val yieldAcreQuintalsVal: String = "",
    @SerializedName("avg_market_price_quintal") val avgMarketPriceQuintalVal: String = "",
    @SerializedName("cost_of_cultivation_acre") val costOfCultivationAcreVal: String = "",
    @SerializedName("gross_revenue_acre") val grossRevenueAcreVal: String = "",
    @SerializedName("net_profit_acre") val netProfitAcreVal: String = "",
    @SerializedName("profitability_rating") val profitabilityRating: Double = 4.8
) {
    val yieldAcreQuintals: String get() = if (yieldAcreQuintalsVal.isNotEmpty()) yieldAcreQuintalsVal else expectedYield
    val avgMarketPriceQuintal: String get() = avgMarketPriceQuintalVal
    val costOfCultivationAcre: String get() = if (costOfCultivationAcreVal.isNotEmpty()) costOfCultivationAcreVal else costOfCultivation
    val grossRevenueAcre: String get() = if (grossRevenueAcreVal.isNotEmpty()) grossRevenueAcreVal else grossRevenue
    val netProfitAcre: String get() = if (netProfitAcreVal.isNotEmpty()) netProfitAcreVal else netProfit
}

data class CropAgronomyDetail(
    @SerializedName("id") val id: String,
    @SerializedName("name_en") val nameEn: String,
    @SerializedName("name_hi") val nameHi: String,
    @SerializedName("category") val category: String = "",
    @SerializedName("category_hi") val categoryHi: String = "",
    @SerializedName("season") val season: String = "",
    @SerializedName("season_hi") val seasonHi: String = "",
    @SerializedName("duration_days") val durationDays: Int = 100,
    @SerializedName("soil_suitability") val soilSuitability: String = "",
    @SerializedName("soil_suitability_hi") val soilSuitabilityHi: String = "",
    @SerializedName("seed_rate_per_acre") val seedRate: String = "",
    @SerializedName("seed_rate_per_acre_hi") val seedRateHi: String = "",
    @SerializedName("spacing") val spacing: String = "",
    @SerializedName("spacing_hi") val spacingHi: String = "",
    @SerializedName("sowing_details") val sowingDetailsMap: Map<String, String>? = null,
    @SerializedName("recommended_varieties") val recommendedVarieties: List<String> = emptyList(),
    @SerializedName("recommended_varieties_hi") val recommendedVarietiesHi: List<String> = emptyList(),
    @SerializedName("stages") val stages: List<CropStageInfo> = emptyList(),
    @SerializedName("growth_stages") val growthStagesList: List<CropStageInfo>? = null,
    @SerializedName("fertigation_schedule") val fertigationSchedule: List<FertigationItem> = emptyList(),
    @SerializedName("pest_disease_management") val pestDiseaseManagement: List<PestManagementItem> = emptyList(),
    @SerializedName("irrigation_schedule") val irrigationScheduleList: List<String>? = null,
    @SerializedName("irrigation_details_hi") val irrigationDetailsHi: String? = null,
    @SerializedName("financials") val financials: CropFinancials? = null,
    @SerializedName("financial_and_yield") val financialAndYieldVal: CropFinancials? = null
) {
    val sowingDetails: Map<String, String> get() = sowingDetailsMap ?: mapOf("seed_rate" to seedRate, "spacing" to spacing)
    val growthStages: List<CropStageInfo> get() = growthStagesList ?: stages
    val irrigationSchedule: List<String> get() = irrigationScheduleList ?: listOf(irrigationDetailsHi ?: "नियमित सिंचाई")
    val financialAndYield: CropFinancials get() = financialAndYieldVal ?: financials ?: CropFinancials()
}

data class SavedPlotItem(
    @SerializedName("id") val id: String = "",
    @SerializedName("plot_id") val plotIdVal: String = "",
    @SerializedName("plot_name") val plotName: String = "",
    @SerializedName("area_acres") val areaAcres: Double = 0.0,
    @SerializedName("area_bigha") val areaBigha: Double? = null,
    @SerializedName("state") val state: String = "Uttar Pradesh",
    @SerializedName("district") val district: String = "Meerut",
    @SerializedName("centroid_lat") val centroid_lat: Double = 28.6139,
    @SerializedName("centroid_lng") val centroid_lng: Double = 77.2090,
    @SerializedName("points_count") val points_count: Int = 0,
    @SerializedName("coordinates") val coordinates: List<List<Double>> = emptyList()
) {
    val plot_id: String get() = if (plotIdVal.isNotEmpty()) plotIdVal else id
}
