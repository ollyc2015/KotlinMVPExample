package uk.co.oliverbcurtis.kotlinmvpexample.ui

import io.reactivex.Single
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations.initMocks
import uk.co.oliverbcurtis.kotlinmvpexample.model.Meal
import uk.co.oliverbcurtis.kotlinmvpexample.ui.listview.ListViewActivity
import uk.co.oliverbcurtis.kotlinmvpexample.ui.listview.ListViewManager
import uk.co.oliverbcurtis.kotlinmvpexample.ui.listview.ListViewPresenter
import java.io.IOException
import java.util.ArrayList

class ListViewPresenterTest {

    // Mock the dependencies; mock object simulates the data source and ensures that the test conditions are always the same.
    @Mock
    internal lateinit var view: ListViewActivity
    @Mock
    internal lateinit var manager: ListViewManager

    // The below is a rule set for how the RX Java Scheduler is handled during testing
    @Rule @JvmField
    var rule = TrampolineSchedulerRule()

    // Create a list of 3 meals
    private val MEALS_THREE = createMealList(3)
    private var presenter: ListViewPresenter? = null


    // Do something before @Test method starts
    @Before
    fun setUp() {
        initMocks(this)
        presenter = ListViewPresenter(manager)
        presenter!!.attachView(view)

    }

    // @Test tells Android to test the below methods
    @Test
    fun givenMealsRequested_whenSuccessfulResponse_thenMealsReturned() {
        /*
        Mocks can return different values depending on arguments passed into a method.
        The when(…​.).thenReturn(…​.) method chain is used to specify a a return value
        for a method call with pre-defined parameters.
         */
        `when`(manager.getMeals()).thenReturn(Single.just(MEALS_THREE))

        presenter!!.requestAllMeals()
        /*
        The ArgumentCaptor class allows us to access the arguments of method calls during the verification.
        This allows us to capture these arguments of method calls and to use them for tests.
         */
        val listCaptor = ArgumentCaptor.forClass(List::class.java)

        /*
        Mockito keeps track of all the method calls and their parameters to the mock object.
        You can use the verify() method on the mock object to verify that the specified conditions are met.
         */
        verify(view, times(1)).populateListView(listCaptor.capture())
        val mealList = listCaptor.value
        // The below confirms that expected value is the same as actual value
        assertEquals(mealList, MEALS_THREE)
    }

    @Test
    fun givenMealsRequested_whenUnsuccessfulResponse_thenErrorThrownAndMessageDisplayed() {

        `when`(manager.getMeals()).thenReturn(Single.error(IOException("Error message")))

        presenter!!.requestAllMeals()

        verify(view, times(1)).showToast(anyString())
    }

    private fun createMealList(count: Int?): List<Meal> {
        val meals = ArrayList<Meal>()
        for (i in 0 until count!!) {
            meals.add(Meal())
        }
        return meals
    }
}