package nl.ad.taxibedrijf

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var licensePlateEdittext: EditText
    private lateinit var searchButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        licensePlateEdittext = findViewById(R.id.edittext_numberplate)
        searchButton = findViewById(R.id.button_find_result)

        searchButton.setOnClickListener {

            if (licensePlateEdittext.text.isNotBlank()) {
                val intent = Intent(this, CarDetailsActivity::class.java)
                intent.putExtra("license", licensePlateEdittext.text.toString())
                startActivity(intent)
            }
        }
    }

}