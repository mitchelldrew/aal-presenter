package com.aal.presenter

import com.aal.model.Restaurant

interface IFavProvider {
    fun get()
    fun save(element:Restaurant)
    fun delete(element:Restaurant)
    fun addListener(favListener:Listener)
    fun removeListener(favListener:Listener)

    interface Listener{
        fun onReceive(elements:List<Restaurant>)
        fun onError(error:Exception)
    }
}