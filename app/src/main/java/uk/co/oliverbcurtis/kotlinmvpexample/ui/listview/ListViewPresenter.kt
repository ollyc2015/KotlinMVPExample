package uk.co.oliverbcurtis.kotlinmvpexample.ui.listview

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uk.co.oliverbcurtis.kotlinmvpexample.async.ApiUtils
import uk.co.oliverbcurtis.kotlinmvpexample.model.Meal
import uk.co.oliverbcurtis.kotlinmvpexample.model.MealResponse
import uk.co.oliverbcurtis.kotlinmvpexample.ui.BaseActivity

/*
The presenter class holds all of the business logic and acts as a mediator between the view and model
*/
class ListViewPresenter : BaseActivity(), ListViewContract.Presenter {

    //Gets the view of the class that ListViewContract.View is being implemented by
    private lateinit var view:ListViewContract.View
    private val apiService = ApiUtils.apiService
    private var returned_meals: MealResponse? = null
    lateinit var mealResponse: List<Meal>


    override fun getMeal() {

        //Call the external DB to load all the latest returned_meals
        apiService.mealList

        // Retrofit call to API, returns list of returned_meals
        apiService.mealList.enqueue(object : Callback<MealResponse> {
            override fun onResponse(call: Call<MealResponse>, response: Response<MealResponse>) {
                if (response.isSuccessful) {

                    returned_meals = response.body()!!

                    mealResponse = returned_meals!!.meals

                    view.populateListView(mealResponse)

                }
            }

            override fun onFailure(call: Call<MealResponse>, t: Throwable) {
                view!!.showToast(t.toString())
            }
        })

    }

    //Below deals with assigning the pointer view to the view
    override fun attachView(view: ListViewContract.View) {
        this.view = view
    }


    override fun onClick(position: Meal) {

        val mealID = position.idMeal!!.toString()

        // Retrofit call to API, returns the meal details of the selected meal - DB queries meal ID
        apiService.getMeal(mealID).enqueue(object : Callback<MealResponse> {
            override fun onResponse(call: Call<MealResponse>, response: Response<MealResponse>) {
                if (response.isSuccessful) {

                    returned_meals = response.body()!!

                    mealResponse = returned_meals!!.meals

                     view.selectedMeal(mealResponse)
                }
            }

            override fun onFailure(call: Call<MealResponse>, t: Throwable) {
                view.showToast(t.toString())
            }
        })
    }
}

