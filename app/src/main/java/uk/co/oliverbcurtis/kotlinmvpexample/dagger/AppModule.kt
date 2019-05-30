package uk.co.oliverbcurtis.kotlinmvpexample.dagger

import android.content.Context
import java.util.ArrayList
import javax.inject.Singleton
import dagger.Module
import dagger.Provides
import uk.co.oliverbcurtis.kotlinmvpexample.async.ApiUtils
import uk.co.oliverbcurtis.kotlinmvpexample.async.MealAPI
import uk.co.oliverbcurtis.kotlinmvpexample.model.Meal
import uk.co.oliverbcurtis.kotlinmvpexample.ui.listview.ListViewManager
import uk.co.oliverbcurtis.kotlinmvpexample.ui.listview.ListViewPresenter
import uk.co.oliverbcurtis.kotlinmvpexample.ui.listview.MealListAdapter
import uk.co.oliverbcurtis.kotlinmvpexample.ui.selectedMeal.SelectedMealAdapter;


/*
Here is the collection of dependencies
 */
@Module
class AppModule//This is where context is being defined, singleton behaviour is specified as we only need once instance to be created
    (private val application: DaggerApplication) {

    @Provides
    @Singleton
    internal fun providesApplicationContext(): Context {
        return application
    }

    //Below creates reference to a new ListViewPresenter object
    @Provides
    fun providesListViewPresenter(manager: ListViewManager): ListViewPresenter {
        return ListViewPresenter(manager)
    }

    //Below provides an empty ArrayList so that providesMealListAdapter and providesSelectedMealAdapter can be initialised
    @Provides
    fun providesMealList(): List<Meal> {
        return ArrayList()
    }

    //This initialises the MealListAdapter, passing the variables needed to the constructor
    @Provides
    fun providesMealListAdapter(meal: List<Meal>): MealListAdapter {
        return MealListAdapter(application, meal)
    }

    //This initialises the SelectedMealAdapter, passing the variables needed to the constructor
    @Provides
    fun providesSelectedMealAdapter(selectedMeal: List<Meal>): SelectedMealAdapter {
        return SelectedMealAdapter(application, selectedMeal)
    }

    @Provides
    internal fun mealAPI(): MealAPI {
        return ApiUtils.apiService
    }

    @Provides
    internal fun manager(mealAPI: MealAPI): ListViewManager {
         return ListViewManager(mealAPI)
    }

}
