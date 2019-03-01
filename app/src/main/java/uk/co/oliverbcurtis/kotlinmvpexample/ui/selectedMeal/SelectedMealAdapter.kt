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
        The API will not return a recipe with more than 20 ingredients.
        */
        val mealIngredient = ArrayList<String>()
        mealIngredient.add(meal!!.strIngredient1!!)
        mealIngredient.add(meal.strIngredient2!!)
        mealIngredient.add(meal.strIngredient3!!)
        mealIngredient.add(meal.strIngredient4!!)
        mealIngredient.add(meal.strIngredient5!!)
        mealIngredient.add(meal.strIngredient6!!)
        mealIngredient.add(meal.strIngredient7!!)
        mealIngredient.add(meal.strIngredient8!!)
        mealIngredient.add(meal.strIngredient9!!)
        mealIngredient.add(meal.strIngredient10!!)
        mealIngredient.add(meal.strIngredient11!!)
        mealIngredient.add(meal.strIngredient12!!)
        mealIngredient.add(meal.strIngredient13!!)
        mealIngredient.add(meal.strIngredient14!!)
        mealIngredient.add(meal.strIngredient15!!)
        mealIngredient.add(meal.strIngredient16!!)
        mealIngredient.add(meal.strIngredient17!!)
        mealIngredient.add(meal.strIngredient18!!)
        mealIngredient.add(meal.strIngredient19!!)
        mealIngredient.add(meal.strIngredient20!!)

        //Then do the same for the measurements of each ingredient needed
        val mealMeasure = ArrayList<String>()
        mealMeasure.add(meal.strMeasure1!!)
        mealMeasure.add(meal.strMeasure2!!)
        mealMeasure.add(meal.strMeasure3!!)
        mealMeasure.add(meal.strMeasure4!!)
        mealMeasure.add(meal.strMeasure5!!)
        mealMeasure.add(meal.strMeasure6!!)
        mealMeasure.add(meal.strMeasure7!!)
        mealMeasure.add(meal.strMeasure8!!)
        mealMeasure.add(meal.strMeasure9!!)
        mealMeasure.add(meal.strMeasure10!!)
        mealMeasure.add(meal.strMeasure11!!)
        mealMeasure.add(meal.strMeasure12!!)
        mealMeasure.add(meal.strMeasure13!!)
        mealMeasure.add(meal.strMeasure14!!)
        mealMeasure.add(meal.strMeasure15!!)
        mealMeasure.add(meal.strMeasure16!!)
        mealMeasure.add(meal.strMeasure17!!)
        mealMeasure.add(meal.strMeasure18!!)
        mealMeasure.add(meal.strMeasure19!!)
        mealMeasure.add(meal.strMeasure20!!)

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
