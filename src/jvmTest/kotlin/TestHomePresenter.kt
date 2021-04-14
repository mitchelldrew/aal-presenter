import com.aal.model.Restaurant
import com.aal.presenter.home.HomePresenter
import org.junit.Assert
import org.junit.Test
import java.util.concurrent.CompletableFuture

class TestHomePresenter {

    @Test
    fun testShowAlsoCallsImageProvider(){
        var rest1 = Restaurant("","","",1,0.0,0,0.0,0.0)
        var rest2 = Restaurant("2","","",3,0.0,0,0.0,0.0)
        var restList = ArrayList<Restaurant>()
        restList.add(rest1)
        restList.add(rest2)

        var future = CompletableFuture<Int>()
        var mockRestProvider = MockRestProvider(restList)
        var mockImageProvider = MockImageProvider(future)

        var presenter = HomePresenter(MockFreezer(),mockRestProvider,null,mockImageProvider,0.0)
        presenter.setView(MockHomeView())
        Assert.assertEquals(0,mockImageProvider.getCounter)
        presenter.show(0.0,0.0)
        Assert.assertTrue(future.get() > 0)
    }
}