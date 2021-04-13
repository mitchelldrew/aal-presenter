package com.aal.presenter.home

import com.aal.model.Restaurant
import com.aal.presenter.IFavProvider
import com.aal.presenter.IImageProvider
import com.aal.presenter.IRestaurantProvider


class HomePresenter(private var restProvider: IRestaurantProvider?, private var favoritesProvider: IFavProvider?, private var imageProvider: IImageProvider?, private val searchRadius:Double):
    IHomePresenter {
    private var view: IHomeView? = null
    private var restListener: IRestaurantProvider.Listener? = null
    private var favListener: IFavProvider.Listener? = null
    private var imageListener: IImageProvider.Listener? = null


    private fun attachListeners(){
        restListener = getRestaurantListener()
        imageListener = getImageListener()
        favListener = getFavListener()
        restProvider?.addListener(restListener!!)
        imageProvider?.addListener(imageListener!!)
        favoritesProvider?.addListener(favListener!!)
    }

    private fun getImageListener(): IImageProvider.Listener {
        return object : IImageProvider.Listener {
            override fun onReceive(url: String, imgBase64: String) { view?.displayImg(url,imgBase64) }
            override fun onError(url: String, error: Exception) { view?.error(error) }
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
    }
}