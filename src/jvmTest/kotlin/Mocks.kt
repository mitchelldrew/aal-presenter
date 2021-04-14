import com.aal.model.Restaurant
import com.aal.presenter.IFreezer
import com.aal.presenter.IImageProvider
import com.aal.presenter.IRestaurantProvider
import com.aal.presenter.home.IHomeView
import java.util.concurrent.CompletableFuture

class MockFreezer: IFreezer {
    override fun freeze(obj: Any): Any {
        return obj
    }
}

class MockImageProvider(var future:CompletableFuture<Int>): IImageProvider {
    var getCounter = 0

    override fun get(imgRef: String) {
        getCounter++
        future.complete(getCounter)
    }
    override fun addListener(imgListener: IImageProvider.Listener) {}
    override fun removeListener(imgListener: IImageProvider.Listener) {}
}

class MockRestProvider(val restaurants: List<Restaurant>): IRestaurantProvider {
    private var listeners = ArrayList<IRestaurantProvider.Listener>()

    override fun get(lat: Double, lng: Double, rad: Double) {
        for(listener in listeners){
            listener.onReceive(restaurants)
        }
    }

    override fun search(query: String) {}

    override fun addListener(restListener: IRestaurantProvider.Listener) {
        listeners.add(restListener)
    }

    override fun removeListener(restListener: IRestaurantProvider.Listener) {
        TODO("Not yet implemented")
    }
}

class MockHomeView(): IHomeView {
    override fun displayRests(rests: List<Restaurant>) {
    }
    override fun displayFavs(favs: List<String>) {
    }
    override fun displayImg(imgRef: String, imgBase64: String) {
    }
    override fun error(error: Exception) {
    }
}