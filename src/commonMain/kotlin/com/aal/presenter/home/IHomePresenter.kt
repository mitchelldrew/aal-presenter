package com.aal.presenter.home


interface IHomePresenter {
    fun setView(view: IHomeView)
    fun show(lat:Double,lng:Double)
    fun saveFav(name:String)
    fun deleteFav(name:String)
    fun query(name:String)
    fun shutdown()
}