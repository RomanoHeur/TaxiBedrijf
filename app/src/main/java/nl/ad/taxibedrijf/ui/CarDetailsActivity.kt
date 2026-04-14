package nl.ad.taxibedrijf.ui

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import nl.ad.taxibedrijf.R
import nl.ad.taxibedrijf.model.Car

class CarDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car_details)

        val car: Car = intent.getSerializableExtra("car") as Car

        findViewById<TextView>(R.id.car_details_number_plate).text = car.kenteken
        findViewById<TextView>(R.id.car_details_vehicle_type_wrapper).text = getString(R.string.car_details_vehicle_type_result_wrapper,car.voertuigsoort, car.merk)

        findViewById<TextView>(R.id.textview_car_details_brand_result).text = car.merk
        findViewById<TextView>(R.id.textview_car_details_type_result).text = car.voertuigsoort
        findViewById<TextView>(R.id.textview_car_details_first_color_result).text = car.eerste_kleur
        findViewById<TextView>(R.id.textview_car_details_total_cylinders_result).text = "" + car.aantal_cilinders
        findViewById<TextView>(R.id.textview_car_details_cylinder_content_result).text = getString(R.string.car_details_vehicle_cylinder_content_result, car.cilinderinhoud)


    }

}