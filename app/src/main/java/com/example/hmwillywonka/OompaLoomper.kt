package com.example.hmwillywonka

import com.google.gson.annotations.SerializedName

public class OompaLoomper{
    @SerializedName("results")
    public var OompaLoomper: ArrayList<ItemOompaLoomper>? = null
}

class ItemOompaLoomper {
    @SerializedName("id") val id: Int=0
    @SerializedName("image") val image: String=""
    @SerializedName("first_name") val first_name: String=""
    @SerializedName("last_name") val last_name:String=""
    @SerializedName("profession") val profession:String=""
    @SerializedName("gender") val gender:String=""
    @SerializedName("email") val email:String=""
    @SerializedName("age") val age:String=""
    @SerializedName("country") val country:String=""
    @SerializedName("height") val height:String=""
    @SerializedName("favorite") val favorite:favorite? = null
}

class favorite {
    @SerializedName("color") val color:String=""
    @SerializedName("food") val food:String=""
    @SerializedName("song") val song:String=""
    @SerializedName("random_string") val random_string:String=""
}