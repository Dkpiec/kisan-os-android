package com.kisan.os.models

import com.google.gson.annotations.SerializedName

data class DynamicTile(
    @SerializedName("tile_id") val tileId: String,
    @SerializedName("title") val title: String,
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
)

data class PlotAreaResult(
    @SerializedName("plot_name") val plotName: String,
    @SerializedName("centroid") val centroid: Map<String, Double>,
    @SerializedName("measurements") val measurements: Map<String, Any>
)
