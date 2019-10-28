package uk.co.oliverbcurtis.kotlinmvpexample.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import uk.co.oliverbcurtis.kotlinmvpexample.dagger.DaggerApplication
import uk.co.oliverbcurtis.kotlinmvpexample.ui.listview.ListViewManager
import uk.co.oliverbcurtis.kotlinmvpexample.ui.listview.ListViewPresenter
import uk.co.oliverbcurtis.kotlinmvpexample.ui.listview.MealListAdapter
import uk.co.oliverbcurtis.kotlinmvpexample.ui.selectedMeal.SelectedMealAdapter

import javax.inject.Inject

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {


    var presenter: ListViewPresenter? = null
        @Inject set


    var mealListAdapter: MealListAdapter? = null
        @Inject set

    var selectedMealAdapter: SelectedMealAdapter? = null
        @Inject set

    var manager: ListViewManager? = null
        @Inject set


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*
        Here we are saying cast the getApplication() from the DaggerApplication, get the app component from it,
        because in this case we already have the appComponent defined in the DaggerApplication class as
        we are first injecting from the DaggerApplication class
         */

        (application as DaggerApplication).appComponent.inject(this)
    }

}
