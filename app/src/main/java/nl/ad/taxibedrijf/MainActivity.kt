package nl.ad.taxibedrijf

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import nl.ad.taxibedrijf.fragment.CarDetailsFragment
import java.lang.reflect.Method

class MainActivity : AppCompatActivity() {

    private lateinit var numberPlateEdittext: EditText
    private lateinit var searchButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        numberPlateEdittext = findViewById(R.id.edittext_numberplate)
        searchButton = findViewById(R.id.button_find_result)

        searchButton.setOnClickListener {
            val fragment = CarDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString("numberPlate", numberPlateEdittext.text.toString())
                }
            }

            supportFragmentManager.beginTransaction().replace(R.id.fragment_car_details, fragment).commit()
        }

        val requestQueue: RequestQueue = Volley.newRequestQueue(this.applicationContext)

        val request = StringRequest(
            Request.Method.GET,
            "https://opendata.rdw.nl/resource/m9d7-ebf2.json",
            { response -> println(response) },
            { error -> println(error) }
        )

        requestQueue.add(request)
    }

}