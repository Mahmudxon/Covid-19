package uz.mahmudxon.covid_19.adapter

import android.view.View
import kotlinx.android.synthetic.main.list_item_layout.view.*
import uz.mahmudxon.covid_19.R
import uz.mahmudxon.covid_19.model.Country

class CountryAdapter : SingleTypeAdapter<Country>(R.layout.list_item_layout, ArrayList()) {

    var listener: ((Country) -> Unit)? = null

    override fun bindData(itemView: View, position: Int) {
        itemView.apply {
            count?.text = "${position + 1}"
            country?.text = data[position].Country
            total?.text = data[position].TotalConfirmed.toString()
            setOnClickListener {
                listener?.invoke(data[position])
            }
        }
    }
}