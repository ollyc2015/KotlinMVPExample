package uk.co.oliverbcurtis.kotlinmvpexample.ui.selectedMeal

import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.listview_view.*
import uk.co.oliverbcurtis.kotlinmvpexample.model.Meal
import uk.co.oliverbcurtis.kotlinmvpexample.ui.BaseActivity



class SelectedMealView : BaseActivity(), SelectedMealContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Set the content view to the listview_view.xml
        setContentView(uk.co.oliverbcurtis.kotlinmvpexample.R.layout.listview_view)
        retrieveSelectedMealData()
    }


    override fun retrieveSelectedMealData() {

        if (intent != null) {

            val myList = intent.getSerializableExtra("selectedMeal") as? List<Meal>

            list_view!!.adapter = selectedMealAdapter


            if (myList != null) {
                selectedMealAdapter!!.updateList(myList)
            }



        }else{

            showToast("Intent Passed Null Object")
        }
    }

    override fun showToast(toast: String) {
        Toast.makeText(this, toast, Toast.LENGTH_LONG).show()
    }


    override fun onBackPressed() {
        super.onBackPressed()
        SelectedMealAdapter.hasRun = false
    }
}