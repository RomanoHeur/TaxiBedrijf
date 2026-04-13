package nl.ad.taxibedrijf.ui

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import nl.ad.taxibedrijf.R
import nl.ad.taxibedrijf.model.Car
import nl.ad.taxibedrijf.ui.fragment.CarDetailsFragment

class MainActivity : AppCompatActivity() {

    private lateinit var numberPlateEdittext: EditText
    private lateinit var searchButton: Button
    private lateinit var showAllButton: Button
    private val carList = mutableListOf<Car>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        numberPlateEdittext = findViewById(R.id.edittext_numberplate)
        searchButton = findViewById(R.id.button_find_result)
        showAllButton = findViewById(R.id.button_total_overview)

        searchButton.setOnClickListener {

            val fragment = CarDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString("numberPlate", numberPlateEdittext.text.toString())
                }
            }

            supportFragmentManager.beginTransaction().replace(R.id.fragment_car_details, fragment)
                .commit()
        }

        showAllButton.setOnClickListener {
            fetchCars()
        }
    }

    private fun fetchCars() {
        val url = "https://opendata.rdw.nl/resource/m9d7-ebf2.json"
        val requestQueue: RequestQueue = Volley.newRequestQueue(this.applicationContext)
        val gson = Gson()

        val request = JsonArrayRequest(
            url,
            { response ->
                carList.clear() // Maakt lijst leeg, zodat er geen dubbele auto's in komen.

                // Loop over elke auto in de JSON repsonse
                for (index in 0 until response.length()) {

                    // JSONObject omzetten naar een Car object.
                    val car = gson.fromJson(
                        response.getJSONObject(index).toString(),
                        Car::class.java
                    )

                    carList.add(car) // Auto wordt toegevoegd aan de lijst
                }

                // ArrayAdapter aanmaken om de carlist te kunnen koppelen aan de ListView
                val arrayAdapter = ArrayAdapter(
                    applicationContext,
                    android.R.layout.simple_list_item_1,
                    carList
                )

                // Stelt de adapter in op de Listview en wordt ook weergegeven.
                findViewById<ListView>(R.id.listview_cars).adapter = arrayAdapter
                findViewById<ListView>(R.id.listview_cars).visibility = ListView.VISIBLE
            },
            { error -> println(error)}
        )

        requestQueue.add(request)
    }

}