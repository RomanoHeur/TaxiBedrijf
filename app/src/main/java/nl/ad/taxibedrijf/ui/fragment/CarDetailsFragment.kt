package nl.ad.taxibedrijf.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import nl.ad.taxibedrijf.R
import nl.ad.taxibedrijf.model.Car

class CarDetailsFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Haalt de meegegeven auto op, als er geen auto is stopt die ermee.
        val car = arguments?.getSerializable("car") as? Car ?: return

        view.findViewById<TextView>(R.id.textview_car_details_no_car_selected_title).visibility = View.GONE
        view.findViewById<TextView>(R.id.textview_car_details_no_car_selected_subtitle).visibility = View.GONE
        view.findViewById<CardView>(R.id.cardview_car_details_header).visibility = View.VISIBLE
        view.findViewById<CardView>(R.id.cardview_car_details_hero).visibility = View.VISIBLE

        view.findViewById<TextView>(R.id.car_details_number_plate).text = car.kenteken
        view.findViewById<TextView>(R.id.car_details_vehicle_type_wrapper).text = getString(R.string.car_details_vehicle_type_result_wrapper,car.voertuigsoort, car.merk)

        view.findViewById<TextView>(R.id.textview_car_details_brand_result).text = car.merk
        view.findViewById<TextView>(R.id.textview_car_details_type_result).text = car.voertuigsoort
        view.findViewById<TextView>(R.id.textview_car_details_first_color_result).text = car.eerste_kleur
        view.findViewById<TextView>(R.id.textview_car_details_total_cylinders_result).text = "" + car.aantal_cilinders
        view.findViewById<TextView>(R.id.textview_car_details_cylinder_content_result).text = getString(R.string.car_details_vehicle_cylinder_content_result, car.cilinderinhoud)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_car_details, container, false)
    }

}