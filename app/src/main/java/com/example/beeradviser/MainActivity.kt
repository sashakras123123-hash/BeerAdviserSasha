package com.example.beeradviser

import android.os.Bundle
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val findBeer = findViewById<Button>(R.id.find_beer)
        val randomBeerButton = findViewById<Button>(R.id.random_beer_button)
        val beerColor = findViewById<Spinner>(R.id.beer_color)
        val brands = findViewById<TextView>(R.id.brands)
        val allBeers = getAllBeers()

        findBeer.setOnClickListener {
            val color = beerColor.selectedItem.toString()
            val beerList = getBeers(color)
            if (beerList.isNotEmpty()) {
                val beers = beerList.joinToString("\n")
                brands.text = beers
            } else {
                brands.text = "No beers found for this color."
            }
        }

        randomBeerButton.setOnClickListener {
            val randomBeer = allBeers.randomOrNull()
            if (randomBeer != null) {
                brands.text = "Random Beer:\n$randomBeer"
            } else {
                brands.text = "No beers available to recommend"
            }
        }
    }

    fun getBeers(color: String): List<String> {
        return when (color) {
            "Light" -> listOf("Jail Pale Ale", "Lager Lite", "Golden Ale")
            "Amber" -> listOf("Jack Amber", "Red Moose", "Amber Wave")
            "Brown" -> listOf("Brown Bear Beer", "Bock Brownie", "Nutty Brown")
            else -> listOf("Gout Stout", "Dark Daniel", "Black Hole Porter")
        }
    }

    private fun getAllBeers(): List<String> {
        return listOf(
            "Jail Pale Ale", "Lager Lite", "Golden Ale",
            "Jack Amber", "Red Moose", "Amber Wave",
            "Brown Bear Beer", "Bock Brownie", "Nutty Brown",
            "Gout Stout", "Dark Daniel", "Black Hole Porter"
        )
    }
}