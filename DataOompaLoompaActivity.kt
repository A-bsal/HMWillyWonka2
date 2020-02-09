package com.example.hmwillywonka

import android.os.AsyncTask
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import java.net.URL

class DataOompaLoompaActivity : AppCompatActivity() {

    var _ID_OOMPA_LOOMPA = "0"

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_oompa_loompa)

        val _ineItent = intent.extras
        _ID_OOMPA_LOOMPA = _ineItent?.get("id").toString()

        FetchJsonData().execute()
    }

    inner class FetchJsonData() : AsyncTask<String, Void, String>() {
        override fun onPreExecute() {
            super.onPreExecute()
        }

        override fun doInBackground(vararg params: String?): String? {
            return URL("https://2q2woep105.execute-api.eu-west-1.amazonaws.com/napptilus/oompa-loompas/${_ID_OOMPA_LOOMPA}").readText(
                Charsets.UTF_8
            )
        }

        override fun onPostExecute(strResult: String?) {
            super.onPostExecute(strResult)
            val _gsnData: ItemOompaLoomper = Gson().fromJson(strResult, ItemOompaLoomper::class.java)
            val _favData = _gsnData.favorite

            val _imgImage = findViewById(R.id.imgImage) as ImageView
            Picasso.get().load( _gsnData.image).into(_imgImage)
            findViewById<TextView>(R.id.txtFirst_name).text = _gsnData.first_name
            findViewById<TextView>(R.id.txtLast_name).text = _gsnData.last_name
            findViewById<TextView>(R.id.txtProfession).text = _gsnData.profession
            val _imgGender = findViewById(R.id.imgGender) as ImageView
            Common.getIconGender(_gsnData.gender,_imgGender)
            findViewById<TextView>(R.id.txtContry).text = _gsnData.country
            findViewById<TextView>(R.id.txtHeight).text = _gsnData.height
            findViewById<TextView>(R.id.txtEmail).text = _gsnData.email
            findViewById<TextView>(R.id.txtAge).text = _gsnData.age

            //Favorite
            findViewById<TextView>(R.id.txtColor).text = _favData?.color
            findViewById<TextView>(R.id.txtFood).text = _favData?.food
            findViewById<TextView>(R.id.txtSong).text = _favData?.song
            findViewById<TextView>(R.id.txtRandom_string).text = _favData?.random_string
        }
    }
}
