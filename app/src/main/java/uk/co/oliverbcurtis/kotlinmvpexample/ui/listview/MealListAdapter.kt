package uk.co.oliverbcurtis.kotlinmvpexample.ui.listview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import uk.co.oliverbcurtis.kotlinmvpexample.R
import uk.co.oliverbcurtis.kotlinmvpexample.model.Meal

class MealListAdapter(context: Context, mealArrayList: List<Meal>) : ArrayAdapter<Meal>(context, 0, mealArrayList) {


    fun updateList(meal: List<Meal>) {
        this.addAll(meal)
        notifyDataSetChanged()
    }


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView

        // Get the data item for this position
        val meal = getItem(position)
        notifyDataSetChanged()

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.meal_list_layout, parent, false)
        }
        // Lookup view for data population
        val ivCosmetic = convertView!!.findViewById(R.id.iv_meal) as ImageView
        val tvCosmetic = convertView.findViewById(R.id.tv_meal) as TextView

        // Populate the data into the template view using the data object
        Picasso.get().load(meal!!.strMealThumb).into(ivCosmetic)
        tvCosmetic.text = meal.strMeal

        // Return the completed view to render on screen
        return convertView

    }
}

