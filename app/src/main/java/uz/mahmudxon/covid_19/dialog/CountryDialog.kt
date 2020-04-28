package uz.mahmudxon.covid_19.dialog

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.dialog_country.view.*
import uz.mahmudxon.covid_19.R
import uz.mahmudxon.covid_19.model.Country

class CountryDialog(context: Context, val data: Country) : AlertDialog(context) {
    init {
        val view = LayoutInflater.from(context).inflate(R.layout.dialog_country, null, false)
        view.apply {
            country?.text = data.Country
            confirmed?.text = data.TotalConfirmed.toString()
            new_confirmed?.text = "+" + data.NewConfirmed.toString()
            recover?.text = data.TotalRecovered.toString()
            new_recover?.text = "+" + data.NewRecovered.toString()
            deaths?.text = data.TotalDeaths.toString()
            new_deaths?.text = "+" + data.NewDeaths.toString()
        }
        this.setCancelable(true)
        setView(view)
    }
}