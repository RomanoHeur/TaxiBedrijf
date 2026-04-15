package nl.ad.taxibedrijf.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import nl.ad.taxibedrijf.R
import nl.ad.taxibedrijf.ui.fragment.CarDetailsFragment
import nl.ad.taxibedrijf.ui.fragment.HomeFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Start bij de Home pagina
        switchPage(HomeFragment())

        findViewById<BottomNavigationView>(R.id.bottom_navigation_menu).setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_menu_home_overview -> switchPage(HomeFragment())
                R.id.navigation_menu_car_details_overview -> switchPage(CarDetailsFragment())
            }

            return@setOnItemSelectedListener true
        }
    }

    // Functie voor het wisselen van fragments.
    private fun switchPage(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.navigation_menu_container, fragment)
            .commit()
    }


}