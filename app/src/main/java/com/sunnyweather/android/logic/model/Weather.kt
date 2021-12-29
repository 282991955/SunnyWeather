package com.sunnyweather.android.logic.model

/**
 * @author cw
 * @date 2021-12-28 16:35
 */
data class Weather(val realtime: RealtimeResponse.Realtime, val daily: DailyResponse.Daily)

