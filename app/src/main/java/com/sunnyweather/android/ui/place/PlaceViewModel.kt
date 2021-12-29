package com.sunnyweather.android.ui.place

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.sunnyweather.android.logic.Repository
import com.sunnyweather.android.logic.dao.PlaceDao
import com.sunnyweather.android.logic.model.Place

/**
 * 用来存储数据的ViewModel层
 */
class PlaceViewModel : ViewModel() {

    private val searchLiveData = MutableLiveData<String>()

    val placeList = ArrayList<Place>()

    // 监听searchLiveData的变化，每次改变都调用Repository.searchPlace()查询，并将返回的liveData转换成可观察的liveData
    val placeLiveData = Transformations.switchMap(searchLiveData){
        Repository.searchPlace(it)
    }

    // 每次调用searchPlace()就改变searchLiveData
    fun searchPlace(query: String) {
        searchLiveData.value = query
    }

    fun savePlace(place: Place) = Repository.savePlace(place)

    fun getSavedPlace() = Repository.getSavedPlace()

    fun isPlaceSaved() = Repository.isPlaceSaved()
}
