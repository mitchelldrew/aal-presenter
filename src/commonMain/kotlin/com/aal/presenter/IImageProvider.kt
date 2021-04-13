package com.aal.presenter

interface IImageProvider {
    fun get(imgRef:String)
    fun addListener(imgListener: Listener)
    fun removeListener(imgListener: Listener)

    interface Listener{
        fun onReceive(imgRef:String, imgBase64:String)
        fun onError(imgRef:String, error:Exception)
    }
}