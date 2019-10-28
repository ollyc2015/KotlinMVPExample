package uk.co.oliverbcurtis.kotlinmvpexample.async

import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import uk.co.oliverbcurtis.kotlinmvpexample.model.MealResponse

interface MealAPI {

    // Returns list of all the latest meals
    @GET("search.php?f=c")
    fun getMealList(): Single<MealResponse>

    // Returns details of the selected meal by ID
    @GET("lookup.php")
    fun getMeal(@Query("i") idMeal: String): Single<MealResponse>
}