package com.example.beautymnl.ui.adapter

import android.view.DragEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.beautymnl.R
import com.example.beautymnl.data.model.Developer

class MainAdapter(
    private val developers: ArrayList<Developer>, private val viewDeveloper : (dev : Developer) -> Unit
) : RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_developer, parent,
                false
            )
        )

    override fun getItemCount(): Int = developers.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(developers[position])

    fun setData(list: List<Developer>) {
        developers.clear()
        developers.addAll(list)
    }

    fun addData(list: List<Developer>) {
        developers.addAll(list)
    }

    inner class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(dev: Developer) {

            itemView.setOnClickListener {
                viewDeveloper(dev)
            }
            itemView.findViewById<TextView>(R.id.tv_name).text = dev.name
            itemView.findViewById<TextView>(R.id.tv_company).text = dev.company
            itemView.findViewById<TextView>(R.id.tv_email).text = dev.email
            itemView.findViewById<TextView>(R.id.tv_phone).text = dev.phone
            Glide.with(itemView.context).load(dev.photo).into(itemView.findViewById<ImageView>(R.id.iv_image));
        }
    }
}