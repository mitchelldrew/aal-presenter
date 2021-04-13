package com.aal.presenter

interface IImageProvider {
    fun get(url:String)
    fun addListener(imgListener: Listener)
    fun removeListener(imgListener: Listener)

    interface Listener{
        fun onReceive(url:String, imgBase64:String)
        fun onError(url:String, error:Exception)
    }
}