package nl.ad.taxibedrijf.ui.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import nl.ad.taxibedrijf.R
import nl.ad.taxibedrijf.model.Car
import kotlin.collections.get

class HomeFragment : Fragment() {

    private val carList = mutableListOf<Car>()
    private lateinit var showAllButton: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showAllButton = view.findViewById(R.id.button_total_overview)

        showAllButton.setOnClickListener {
            fetchCars(view)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    private fun fetchCars(view: View) {
        val url = "https://opendata.rdw.nl/resource/m9d7-ebf2.json"
        val requestQueue: RequestQueue = Volley.newRequestQueue(requireContext())
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
                    requireContext(),
                    android.R.layout.simple_list_item_1,
                    carList
                )

                // Stelt de adapter in op de Listview en wordt ook weergegeven.
                val listView = view.findViewById<ListView>(R.id.listview_cars)
                listView.adapter = arrayAdapter
                listView.visibility = ListView.VISIBLE

                listView.setOnItemClickListener { _, _, position, _ ->

                    // Maakt een nieuwe fragment aan en stuurt de geselecteerde auto mee.
                    val detailsFragment = CarDetailsFragment().apply {
                        arguments = Bundle().apply {
                            putSerializable("car", carList[position])
                        }
                    }

                    // Vervangt huidige fragment met het details fragment.
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.navigation_menu_container, detailsFragment)
                        .commit()

                    // Zorgt ervoor dat menu item geselecteerd blijft.
                    val navigation = requireActivity().findViewById<BottomNavigationView>(R.id.bottom_navigation_menu)
                    navigation.menu.findItem(R.id.navigation_menu_car_details_overview).isChecked = true
                }
            },
            { error -> println(error)}
        )

        requestQueue.add(request)
    }

}