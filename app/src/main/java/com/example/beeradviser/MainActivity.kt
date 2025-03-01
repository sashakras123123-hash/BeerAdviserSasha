package com.example.beeradviser

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val findBeer = findViewById<Button>(R.id.find_beer)
        findBeer.setOnClickListener {
            val beerColor = findViewById<Spinner>(R.id.beer_color)
            val color = beerColor.selectedItem.toString()
            val beerList = getBeers(color)
            val beers = beerList.reduce { str, item -> str + '\n' + item }
            val brands = findViewById<TextView>(R.id.brands)
            brands.text = beers

            val beerImage = findViewById<ImageView>(R.id.beer_image)
            beerImage.setImageResource(getBeerImage(color))
        }
    }

    fun getBeers(color: String): List<String> {
        return when (color) {
            "Light" -> listOf("Jail Pale Ale", "Lager Lite")
            "Amber" -> listOf("Jack Amber", "Red Moose")
            "Brown" -> listOf("Brown Bear Beer", "Bock Brownie")
            else -> listOf("Gout Stout", "Dark Daniel")
        }
    }

    fun getBeerImage(color: String): Int {
        return when (color) {
            "Light" -> R.drawable.jail_pale_ale
            "Amber" -> R.drawable.jack_amber
            "Brown" -> R.drawable.brown_bear_beer
            else -> R.drawable.gout_stout
        }
    }
}
