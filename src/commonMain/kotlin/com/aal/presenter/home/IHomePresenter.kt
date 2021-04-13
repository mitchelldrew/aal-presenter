package com.aal.presenter.home

import com.aal.presenter.LatLon
import com.aal.model.Restaurant

interface IHomePresenter {
    fun setView(view: IHomeView)
    fun show(ne:LatLon, sw:LatLon)
    fun saveFav(restaurant:Restaurant)
    fun deleteFav(restaurant:Restaurant)
    fun query(name:String)
    fun shutdown()
}