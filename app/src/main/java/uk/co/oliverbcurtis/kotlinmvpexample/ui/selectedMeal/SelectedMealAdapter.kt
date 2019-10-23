package uk.co.oliverbcurtis.kotlinmvpexample.ui.selectedMeal

import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.squareup.picasso.Picasso
import uk.co.oliverbcurtis.kotlinmvpexample.R
import uk.co.oliverbcurtis.kotlinmvpexample.model.Meal
import java.util.ArrayList

class SelectedMealAdapter(context: Context, mealArrayList: List<Meal>) : ArrayAdapter<Meal>(context, 0, mealArrayList) {

    fun updateList(meal: List<Meal>) {
        this.addAll(meal)
        notifyDataSetChanged()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView


        // Get the data item for this position
        val meal = getItem(position)
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.selected_meal, parent, false)
        }


        // Lookup view for data population
        val iv_meal = convertView!!.findViewById<ImageView>(R.id.iv_meal)
        val tv_meal = convertView.findViewById<TextView>(R.id.tv_meal)
        val tv_cuisineType = convertView.findViewById<TextView>(R.id.tv_cuisineType)
        val tv_instructions = convertView.findViewById<TextView>(R.id.tv_instructions)
        val dynamicSelectedMealLayout = convertView.findViewById<LinearLayout>(R.id.dynamicSelectedMealLayout)

        /*
        Due to the API returning individual strings for each ingredient, we first add them to an array.
        The API will not return a recipe with more than 20 ingredients. We also check for null values before adding them.
        */
        val mealIngredient = ArrayList<String>()
        meal!!.strIngredient1?.let { mealIngredient.add(it) }
        meal.strIngredient2?.let { mealIngredient.add(it) }
        meal.strIngredient3?.let { mealIngredient.add(it) }
        meal.strIngredient4?.let { mealIngredient.add(it) }
        meal.strIngredient5?.let { mealIngredient.add(it) }
        meal.strIngredient6?.let { mealIngredient.add(it) }
        meal.strIngredient7?.let { mealIngredient.add(it) }
        meal.strIngredient8?.let { mealIngredient.add(it) }
        meal.strIngredient9?.let { mealIngredient.add(it) }
        meal.strIngredient10?.let { mealIngredient.add(it) }
        meal.strIngredient11?.let { mealIngredient.add(it) }
        meal.strIngredient12?.let { mealIngredient.add(it) }
        meal.strIngredient13?.let { mealIngredient.add(it) }
        meal.strIngredient14?.let { mealIngredient.add(it) }
        meal.strIngredient15?.let { mealIngredient.add(it) }
        meal.strIngredient16?.let { mealIngredient.add(it) }
        meal.strIngredient17?.let { mealIngredient.add(it) }
        meal.strIngredient18?.let { mealIngredient.add(it) }
        meal.strIngredient19?.let { mealIngredient.add(it) }
        meal.strIngredient20?.let { mealIngredient.add(it) }

        //Then do the same for the measurements of each ingredient needed
        val mealMeasure = ArrayList<String>()
        meal.strMeasure1?.let { mealMeasure.add(it) }
        meal.strMeasure2?.let { mealMeasure.add(it) }
        meal.strMeasure3?.let { mealMeasure.add(it) }
        meal.strMeasure4?.let { mealMeasure.add(it) }
        meal.strMeasure5?.let { mealMeasure.add(it) }
        meal.strMeasure6?.let { mealMeasure.add(it) }
        meal.strMeasure7?.let { mealMeasure.add(it) }
        meal.strMeasure8?.let { mealMeasure.add(it) }
        meal.strMeasure9?.let { mealMeasure.add(it) }
        meal.strMeasure10?.let { mealMeasure.add(it) }
        meal.strMeasure11?.let { mealMeasure.add(it) }
        meal.strMeasure12?.let { mealMeasure.add(it) }
        meal.strMeasure13?.let { mealMeasure.add(it) }
        meal.strMeasure14?.let { mealMeasure.add(it) }
        meal.strMeasure15?.let { mealMeasure.add(it) }
        meal.strMeasure16?.let { mealMeasure.add(it) }
        meal.strMeasure17?.let { mealMeasure.add(it) }
        meal.strMeasure18?.let { mealMeasure.add(it) }
        meal.strMeasure19?.let { mealMeasure.add(it) }
        meal.strMeasure20?.let { mealMeasure.add(it) }

        /*
        Then we will loop through the ingredients/measurements, adding them to the screen as long as
        the ingredient section is not null
        */
        var measureCount = 0

        if (!hasRun) {
            hasRun = true
            for (value in mealIngredient) {
                if (value != "") {

                    //Log.i("Ingredient: ", mealIngredient.toString());

                    val tv_ingredient = TextView(context)

                    val params = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        1f
                    )
                    tv_ingredient.text = value + " (" + mealMeasure[measureCount] + ")"
                    tv_ingredient.layoutParams = params
                    tv_ingredient.textSize = 18f
                    tv_ingredient.setTextColor(Color.parseColor("#000000"))
                    tv_ingredient.gravity = Gravity.CENTER
                    dynamicSelectedMealLayout.addView(tv_ingredient)

                    measureCount++
                }
            }
        }


        //Finally, populate the remaining data (like meal image etc) into the template view using the data object
        Picasso.get().load(meal.strMealThumb).into(iv_meal)
        tv_meal.setText(meal.strMeal)
        tv_cuisineType.setText(meal.strArea)
        tv_instructions.setText(meal.strInstructions)

        // Return the completed view to render on screen
        return convertView

    }

    companion object {

        var hasRun = false
    }
}
