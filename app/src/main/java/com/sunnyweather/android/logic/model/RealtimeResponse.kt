package com.sunnyweather.android.logic.model

import com.google.gson.annotations.SerializedName

/**
 * @author cw
 * @date 2021-12-28 16:12
 */
data class RealtimeResponse(val status: String, val result: Result) {

    data class Result(val realtime: Realtime)

    data class Realtime(
        val temperature: Float,
        val skycon: String,
        @SerializedName("air_quality") val airQuality: AirQuality
    )

    data class AirQuality(val aqi: AQI)

    data class AQI(val chn: Float)
}
