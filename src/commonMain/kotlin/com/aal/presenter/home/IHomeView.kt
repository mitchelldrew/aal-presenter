package com.aal.presenter.home

import com.aal.model.Restaurant
import com.aal.presenter.LatLon

interface IHomeView {
    fun displayRests(rests:List<Restaurant>)
    fun displayFavs(favs:List<Restaurant>)
    fun displayImg(url:String, imgBase64:String)
    fun error(error:Exception)
}