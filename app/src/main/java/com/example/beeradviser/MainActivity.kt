package com.example.beeradviser

import android.os.Bundle
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject
import org.json.JSONArray


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val findBeer = findViewById<Button>(R.id.find_beer)
        findBeer.setOnClickListener {
            val beerColor = findViewById<Spinner>(R.id.beer_color)
            val color = beerColor.selectedItem

            val beerTaste = findViewById<Spinner>(R.id.beer_taste)
            val taste = beerTaste.selectedItem

            val beerList = getBeers(color.toString().lowercase(), taste.toString().lowercase())
            val beers = beerList.reduce { str, item -> str + '\n' + item }
            val brands = findViewById<TextView>(R.id.brands)
            brands.text = beers
        }
    }

    fun getBeers(color: String, taste: String): List<String> {
        val beersData: JSONObject = getBeersData();

        for (beersDataColor in beersData.keys()) {
            val beersDataTastes: JSONObject = beersData.get(beersDataColor) as JSONObject
            for (beersDataTaste in beersDataTastes.keys()) {
                if (beersDataColor == color
                    && beersDataTaste == taste
                    && beersDataTastes.get(beersDataTaste) is JSONArray) {

                    val beers = beersDataTastes.get(beersDataTaste) as JSONArray
                    val list = mutableListOf<String>()
                    for (i in 0 until beers.length()) {
                        val beer: String = beers[i] as String
                        list.add(beer)
                    }
                    return list
                }
            }
        }

        return listOf()
    }

    fun getBeersData(): JSONObject {
        val fileContent: String = applicationContext.assets.open("beers.json").bufferedReader().use { it.readText() }
        return JSONObject(fileContent)
    }
}