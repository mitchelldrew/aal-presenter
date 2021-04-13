package com.aal.presenter

import com.aal.model.Restaurant

interface IRestaurantProvider {
    fun get(ne:LatLon, sw:LatLon)
    fun search(query:String)
    fun addListener(restListener: Listener)
    fun removeListener(restListener: Listener)

    interface Listener {
        fun onReceive(ne:LatLon, sw:LatLon, elements:List<Restaurant>)
        fun onReceive(query:String, elements:List<Restaurant>)
        fun onError(error:Exception)
    }
}