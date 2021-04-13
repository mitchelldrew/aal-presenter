package com.aal.presenter

interface IFavProvider {
    fun get()
    fun save(name:String)
    fun delete(name:String)
    fun addListener(favListener:Listener)
    fun removeListener(favListener:Listener)

    interface Listener{
        fun onReceive(names:List<String>)
        fun onError(error:Exception)
    }
}