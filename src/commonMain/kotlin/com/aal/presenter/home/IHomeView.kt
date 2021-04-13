package com.aal.presenter.home

import com.aal.model.Restaurant

interface IHomeView {
    fun displayRests(rests:List<Restaurant>)
    fun displayFavs(favs:List<String>)
    fun displayImg(url:String, imgBase64:String)
    fun error(error:Exception)
}