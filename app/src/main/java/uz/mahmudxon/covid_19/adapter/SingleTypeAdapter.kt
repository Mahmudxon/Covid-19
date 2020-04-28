package uz.mahmudxon.covid_19.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView


abstract class SingleTypeAdapter<T>(
    @LayoutRes val layout: Int, var data: ArrayList<T>,
    val context: Context? = null
) : RecyclerView.Adapter<SingleTypeAdapter<T>.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return ViewHolder(view = view)
    }

    fun swapdata(newdata: ArrayList<T>) {
        data.clear()
        data.addAll(newdata)
        notifyDataSetChanged()
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    abstract fun bindData(itemView: View, position: Int)


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind() {
            bindData(itemView, adapterPosition)
        }
    }
}
