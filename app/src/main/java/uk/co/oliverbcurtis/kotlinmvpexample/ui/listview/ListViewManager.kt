package uk.co.oliverbcurtis.kotlinmvpexample.ui.listview

import io.reactivex.Single
import uk.co.oliverbcurtis.kotlinmvpexample.async.MealAPI
import uk.co.oliverbcurtis.kotlinmvpexample.model.Meal
import uk.co.oliverbcurtis.kotlinmvpexample.model.MealResponse

class ListViewManager(private val apiService: MealAPI) {


    internal fun getMeals(): Single<List<Meal>> {
        return apiService.getMealList()
            .map(MealResponse::meals)
    }


    internal fun getMeals(mealID: String): Single<List<Meal>> {
        return apiService.getMeal(mealID)
            .map(MealResponse::meals)
    }
}