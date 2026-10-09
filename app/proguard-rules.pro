# Kisan Mitra ProGuard Rules
-dontwarn **
-keepattributes *Annotation*
-keepclassmembers class * {
    @com.google.gson.annotations.SerializedName <fields>;
}
-keep class com.kisan.os.models.** { *; }
-keep class com.kisan.os.api.** { *; }
-keep class retrofit2.** { *; }
-keep class okhttp3.** { *; }
