package uk.co.oliverbcurtis.kotlinmvpexample.async

object ApiUtils {

    val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"

    val apiService: MealAPI
        get() = RetrofitClient.getClient(BASE_URL)!!.create(MealAPI::class.java)

}


