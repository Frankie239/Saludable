package francisco.gimenez.istea.saludable.Model

import java.io.Serializable

data class Meal(
    val meal:String,
    val firstDish:String,
    val secondDish:String,
    val drink:String,
    val hadDessert:Boolean,
    val dessert:String,
    val hadTemptation:Boolean,
    val temptation:String,
    val stillHungry:Boolean
):Serializable
