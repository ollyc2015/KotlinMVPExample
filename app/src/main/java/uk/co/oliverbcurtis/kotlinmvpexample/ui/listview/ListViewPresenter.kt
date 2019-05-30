package uk.co.oliverbcurtis.kotlinmvpexample.ui.listview

import android.annotation.SuppressLint
import android.view.View
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
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
class ListViewPresenter(manager: ListViewManager) : BaseActivity(), ListViewContract.Presenter {

    //Gets the view of the class that ListViewContract.View is being implemented by
    private var view: ListViewContract.View? = null

    init {
        this.manager = manager
    }

    //Below deals with assigning the pointer view to the view
    override fun attachView(view: ListViewContract.View) {
        this.view = view
    }

    @SuppressLint("CheckResult")
    override fun requestAllMeals() {
        manager!!.getMeals()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response -> view!!.populateListView(response) }, { t -> view!!.showToast(t.localizedMessage) })
    }


    @SuppressLint("CheckResult")
    override fun onClick(position: Meal) {
        manager!!.getMeals(position.idMeal.toString())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response -> view!!.selectedMeal(response) }, { t -> view!!.showToast(t.localizedMessage) })
    }
}

