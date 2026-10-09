package com.kisan.os.api

import com.kisan.os.models.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit

interface KisanApiService {

    @GET("api/v1/tiles")
    suspend fun getDynamicTiles(
        @Query("lang") lang: String = "hi"
    ): Map<String, List<DynamicTile>>

    @GET("api/v1/seeds")
    suspend fun getSeedVarieties(
        @Query("category") category: String? = null,
        @Query("q") query: String? = null,
        @Query("lang") lang: String = "hi"
    ): Map<String, List<SeedVariety>>

    @GET("api/v1/organic/recipes")
    suspend fun getOrganicRecipes(
        @Query("category") category: String? = null,
        @Query("lang") lang: String = "hi"
    ): Map<String, List<OrganicRecipe>>

    @POST("api/v1/gis/calculate-area")
    suspend fun calculateLandArea(
        @Body payload: Map<String, Any>
    ): PlotAreaResult

    @POST("api/v1/mandi/arbitrage")
    suspend fun getMandiArbitrage(
        @Body payload: Map<String, Any>
    ): Map<String, Any>

    @GET("api/v1/weather/spray-advisory")
    suspend fun getSprayAdvisory(
        @Query("temp_c") temp: Double,
        @Query("humidity_pct") humidity: Double,
        @Query("wind_speed_kmh") windSpeed: Double,
        @Query("rain_forecast_12h") rainForecast: Boolean = false,
        @Query("crop_stage") stage: String = "vegetative",
        @Query("lang") lang: String = "hi"
    ): SprayAdvisory

    @GET("api/v1/news")
    suspend fun getAgriNews(
        @Query("category") category: String? = null,
        @Query("lang") lang: String = "hi",
        @Query("limit") limit: Int = 30,
        @Query("offset") offset: Int = 0
    ): AgriNewsResponse

    @POST("api/v1/news/refresh")
    suspend fun refreshAgriNews(
        @Query("force") force: Boolean = true
    ): Map<String, Any>

    @GET("api/v1/crops/seasons")
    suspend fun getCropSeasons(): Map<String, Any>

    @GET("api/v1/crops/list")
    suspend fun getCropsList(
        @Query("season") season: String? = null,
        @Query("category") category: String? = null
    ): Map<String, Any>

    @GET("api/v1/crops/{crop_id}/pop")
    suspend fun getCropPOP(
        @Path("crop_id") cropId: String
    ): CropAgronomyDetail

    @GET("api/v1/land/plots")
    suspend fun getSavedPlots(
        @Query("user_id") userId: String = "usr_guest"
    ): Map<String, Any>

    @POST("api/v1/land/plots")
    suspend fun savePlot(
        @Body payload: Map<String, Any>
    ): Map<String, Any>

    @DELETE("api/v1/land/plots/{plot_id}")
    suspend fun deletePlot(
        @Path("plot_id") plotId: String
    ): Map<String, Any>

    companion object {
        fun create(baseUrl: String): KisanApiService {
            val logging = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .build()

            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(KisanApiService::class.java)
        }
    }
}
