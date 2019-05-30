package uk.co.oliverbcurtis.kotlinmvpexample.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class MealResponse : Serializable {

    @SerializedName("meals")
    @Expose
    var meals: List<Meal>? = null
}
