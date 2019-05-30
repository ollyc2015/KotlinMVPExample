package uk.co.oliverbcurtis.kotlinmvpexample.ui.selectedMeal

interface SelectedMealContract {


    //View defines the rules for the ListViewActivity Class - the below methods will need to be implemented in ListViewActivity
    interface View {
        //Method used to set some data
        fun retrieveSelectedMealData()
        fun showToast(toast: String)

    }
}