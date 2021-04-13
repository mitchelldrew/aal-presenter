package com.aal.presenter.home

import com.aal.presenter.LatLon

interface IHomePresenter {
    fun setView(view: IHomeView)
    fun show(ne:LatLon, sw:LatLon)
    fun saveFav(name:String)
    fun deleteFav(name:String)
    fun query(name:String)
    fun shutdown()
}