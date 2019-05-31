package uk.co.oliverbcurtis.kotlinmvpexample.ui.listview

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import kotlinx.android.synthetic.main.listview_view.*
import uk.co.oliverbcurtis.kotlinmvpexample.R
import uk.co.oliverbcurtis.kotlinmvpexample.model.Meal
import uk.co.oliverbcurtis.kotlinmvpexample.ui.BaseActivity
import uk.co.oliverbcurtis.kotlinmvpexample.ui.selectedMeal.SelectedMealView
import java.util.ArrayList


//This class relates to all the views/fragments etc used
class ListViewActivity : BaseActivity(), ListViewContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Set the content view to the listview_view.xml
        setContentView(R.layout.listview_view)

        // presenter = new ListViewPresenter();
        //attach the view as this creates a link between this class and the listViewPresenter (without this would cause a null pointer)
        presenter!!.attachView(this)
        initView()
    }


    override fun showToast(toast: String) {
        Toast.makeText(this, toast, Toast.LENGTH_LONG).show()
    }

    override fun initView() {

        presenter!!.requestAllMeals()
    }


    override fun populateListView(meal: List<Meal>) {

        list_view!!.adapter = mealListAdapter
        mealListAdapter!!.updateList(meal)

        //Get string value of selected item
        list_view!!.onItemClickListener = AdapterView.OnItemClickListener { arg0, view, position, arg3 ->
            //first get the position of the item clicked, then collect the ID (in presenter class)
            val meal1 = meal[position]

            //Then pass it to the presenter class for logic handling
            presenter!!.onClick(meal1)
        }
    }


    override fun selectedMeal(mealResponse: List<Meal>) {

        val i = Intent(this, SelectedMealView::class.java)
        val bundle = Bundle()
        bundle.putParcelableArrayList("selectedMeal", mealResponse as ArrayList<out Parcelable>?)
        i.putExtras(bundle)
        startActivity(i)

    }
}

