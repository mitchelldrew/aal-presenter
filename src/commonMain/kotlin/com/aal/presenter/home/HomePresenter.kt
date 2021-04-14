package com.aal.presenter.home

import com.aal.model.Restaurant
import com.aal.presenter.IFavProvider
import com.aal.presenter.IFreezer
import com.aal.presenter.IImageProvider
import com.aal.presenter.IRestaurantProvider


class HomePresenter(private var freezer: IFreezer?, private var restProvider: IRestaurantProvider?, private var favoritesProvider: IFavProvider?, private var imageProvider: IImageProvider?, private val searchRadius:Double):
    IHomePresenter {
    private var view: IHomeView? = null
    private var restListener: IRestaurantProvider.Listener? = null
    private var favListener: IFavProvider.Listener? = null
    private var imageListener: IImageProvider.Listener? = null


    private fun attachListeners(){
        restListener = getRestaurantListener()
        imageListener = getImageListener()
        favListener = getFavListener()
        restProvider?.addListener(freezer?.freeze(restListener!!) as IRestaurantProvider.Listener)
        imageProvider?.addListener(freezer?.freeze(imageListener!!) as IImageProvider.Listener)
        favoritesProvider?.addListener(freezer?.freeze(favListener!!) as IFavProvider.Listener)
    }

    private fun getImageListener(): IImageProvider.Listener {
        return object : IImageProvider.Listener {
            override fun onReceive(imgRef: String, imgBase64: String) { view?.displayImg(imgRef,imgBase64) }
            override fun onError(imgRef: String, error: Exception) { view?.error(error) }
        }
    }

    private fun getFavListener(): IFavProvider.Listener {
        return object: IFavProvider.Listener {
            override fun onReceive(names: List<String>) {view?.displayFavs(names)}
            override fun onError(error: Exception) {view?.error(error)}
        }
    }

    private fun getRestaurantListener(): IRestaurantProvider.Listener {
        return object : IRestaurantProvider.Listener {
            override fun onReceive(elements: List<Restaurant>) {
                view?.displayRests(elements)
                for(rest in elements){
                    imageProvider?.get(rest.iconRef)
                }
            }
            override fun onReceive(query: String, elements: List<Restaurant>) { view?.displayRests(elements) }
            override fun onError(error: Exception) {view?.error(error)}
        }
    }

    override fun setView(view: IHomeView) {
        this.view = view
        attachListeners()
        favoritesProvider?.get()
    }

    override fun show(lat:Double,lng:Double) {
        restProvider?.get(lat,lng,searchRadius)
    }

    override fun saveFav(name:String) {
        favoritesProvider?.save(name)
        favoritesProvider?.get()
    }

    override fun deleteFav(name:String) {
        favoritesProvider?.delete(name)
        favoritesProvider?.get()
    }

    override fun query(name: String) {
        restProvider?.search(name)
    }

    override fun shutdown() {
        favListener?.let{favoritesProvider?.removeListener(it)}
        restListener?.let{restProvider?.removeListener(it)}
        imageListener?.let{imageProvider?.removeListener(it)}
        view = null
        restProvider = null
        imageProvider = null
        favoritesProvider = null
        freezer = null
    }
}