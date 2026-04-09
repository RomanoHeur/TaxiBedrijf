package nl.ad.taxibedrijf

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var licensePlateEdittext: EditText
    private lateinit var searchButton: Button
    private lateinit var textResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        licensePlateEdittext = findViewById(R.id.edittext_numberplate)
        searchButton = findViewById(R.id.button_find_result)
        textResult = findViewById(R.id.textview_result)


        searchButton.setOnClickListener {

            if (licensePlateEdittext.text.isNotBlank()) {
                textResult.text = licensePlateEdittext.text
            }

        }
    }

}