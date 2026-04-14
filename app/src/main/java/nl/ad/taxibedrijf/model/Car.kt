package nl.ad.taxibedrijf.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Car(
    val kenteken: String,
    val voertuigsoort: String,
    val merk: String,
    val eerste_kleur: String,
    val aantal_cilinders: Int,
    val cilinderinhoud: Int
): Serializable {

    override fun toString(): String {
        return "Kenteken: $kenteken \nVoertuigsoort: $voertuigsoort"
    }

}