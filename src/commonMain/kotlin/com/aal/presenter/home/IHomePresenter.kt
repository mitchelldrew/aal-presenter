package com.aal.presenter.home

import com.aal.presenter.LatLon

interface IHomePresenter {
    fun setView(view: IHomeView)
    fun show(ne:LatLon, sw:LatLon)
    fun saveFav(restaurant:String)
    fun deleteFav(restaurant:String)
    fun query(name:String)
    fun shutdown()
}