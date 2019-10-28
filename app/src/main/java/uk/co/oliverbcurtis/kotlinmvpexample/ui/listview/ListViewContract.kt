package uk.co.oliverbcurtis.kotlinmvpexample.ui.listview

import uk.co.oliverbcurtis.kotlinmvpexample.model.Meal


//Contract holds the well defined methods for all of the 3 classes
interface ListViewContract {

    //View defines the rules for the ListViewActivity Class - the below methods will need to be implemented in ListViewActivity
    interface View {
        //Method used to set some data

        fun initView()
        fun populateListView(meal: List<Meal>)
        fun selectedMeal(mealResponse: List<Meal>)
        fun showToast(toast: String)

    }

    //The below methods will be defined in the ListViewPresenter class
    interface Presenter {

         fun requestAllMeals()
         fun attachView(view: View)
         fun onClick(position: Meal)

    }
}
