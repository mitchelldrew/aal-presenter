package com.aal.presenter

import com.aal.model.Restaurant

interface IRestaurantProvider {
    fun get(neLat:Double, neLng:Double, swLat:Double, swLng:Double)
    fun search(query:String)
    fun addListener(restListener: Listener)
    fun removeListener(restListener: Listener)

    interface Listener {
        fun onReceive(elements:List<Restaurant>)
        fun onReceive(query:String, elements:List<Restaurant>)
        fun onError(error:Exception)
    }
}