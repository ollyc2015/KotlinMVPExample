package uk.co.oliverbcurtis.kotlinmvpexample.ui.listview

import android.annotation.SuppressLint
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import uk.co.oliverbcurtis.kotlinmvpexample.model.Meal
import uk.co.oliverbcurtis.kotlinmvpexample.ui.BaseActivity

/*
The presenter class holds all of the business logic and acts as a mediator between the view and model
*/
@SuppressLint("Registered")
class ListViewPresenter(manager: ListViewManager) : BaseActivity(), ListViewContract.Presenter {

    //Gets the view of the class that ListViewContract.View is being implemented by
    private var view: ListViewContract.View? = null
    private val disposable = CompositeDisposable()

    init {
        this.manager = manager
    }

    //Below deals with assigning the pointer view to the view
    override fun attachView(view: ListViewContract.View) {
        this.view = view
    }

    //Disposable: There's no point in listening to a reactive data stream if it's no longer needed.
    //Marking something as disposable makes it easy to remove observers from an observable.
    override fun requestAllMeals() {

        disposable.add(
            manager!!.getMeals()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { response -> view!!.populateListView(response) },
                    { t -> view!!.showToast(t.localizedMessage) })
        )
    }

    override fun onClick(position: Meal) {

        disposable.add(
            manager!!.getMeals(position.idMeal.toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { response -> view!!.selectedMeal(response) },
                    { t -> view!!.showToast(t.localizedMessage) })
        )
    }


    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
    }
}

