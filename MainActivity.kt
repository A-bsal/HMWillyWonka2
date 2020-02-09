package com.example.hmwillywonka

import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import java.net.URL

class MainActivity : AppCompatActivity() {

    var _PAGE_NUM = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FetchJsonData().execute()

        findViewById<TextView>(R.id.txtNumberOfPage).setText("1")

        val _BtnPageDown = findViewById(R.id.BtnPageDown) as ImageButton
        _BtnPageDown.setOnClickListener {
            this.TurnPage(_PAGE_NUM - 1)
        }

        val _btnPageUp = findViewById(R.id.BtnPageUp) as ImageButton
        _btnPageUp.setOnClickListener {
            this.TurnPage(_PAGE_NUM + 1)
        }

        findViewById<ListView>(R.id.LstOompaLoompers).setOnItemClickListener { _, view, _, _ ->

            val _strId = view.findViewById<TextView>(R.id.txtId).text

            val _intIntent = Intent(this, DataOompaLoompaActivity::class.java)
            _intIntent.putExtra("id", _strId)
            startActivity(_intIntent)
        }
    }

    private fun TurnPage(intNumberPage : Int) {
        var _intNumberPage = intNumberPage

        if (_intNumberPage <= 0) {
            _intNumberPage = 1
        }

        _PAGE_NUM = _intNumberPage

        //Assign value in txtNumberPage
        findViewById<TextView>(R.id.txtNumberOfPage).setText(_PAGE_NUM.toString())

        FetchJsonData().execute()
    }

    inner class FetchJsonData() : AsyncTask<String, Void, String>() {
        override fun onPreExecute() {
            super.onPreExecute()
        }

        override fun doInBackground(vararg params: String?): String? {
            return URL("https://2q2woep105.execute-api.eu-west-1.amazonaws.com/napptilus/oompa-loompas?page=${_PAGE_NUM}").readText(
                Charsets.UTF_8
            )
        }

        override fun onPostExecute(strResult: String?) {
            super.onPostExecute(strResult)

            val _gsnData: OompaLoomper = Gson().fromJson(strResult, OompaLoomper::class.java)

            findViewById<ListView>(R.id.LstOompaLoompers).adapter =
                DataAdapterItemList(this@MainActivity, _gsnData.OompaLoomper)
        }
    }

    class DataAdapterItemList (private val context: Context,
                               private val dataList: ArrayList<ItemOompaLoomper>?) : BaseAdapter()
    {

        private val inflater: LayoutInflater =
            this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        override fun getCount(): Int {
            return dataList!!.count()
        }

        override fun getItem(position: Int): Int {
            return position
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            var _objOompaLoomper = dataList?.get(position)

            val _rowView = inflater.inflate(R.layout.item_oompa_loomper, parent, false)

            _rowView.findViewById<TextView>(R.id.txtId).text =
                _objOompaLoomper?.id.toString()

            val _imgImage = _rowView.findViewById(R.id.imgImage) as ImageView
            Picasso.get().load(_objOompaLoomper?.image).into(_imgImage)

            _rowView.findViewById<TextView>(R.id.txtFirst_name).text =
                _objOompaLoomper?.first_name + " "

            _rowView.findViewById<TextView>(R.id.txtLast_name).text =
                _objOompaLoomper?.last_name

            _rowView.findViewById<TextView>(R.id.lblProfession).text =
                _objOompaLoomper?.profession

            val _imgGender = _rowView.findViewById(R.id.imgGender) as ImageView
            Common.getIconGender(_objOompaLoomper?.gender.toString(),_imgGender)

            _rowView.tag = position

            return _rowView
        }
    }
}