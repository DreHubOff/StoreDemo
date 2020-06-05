package com.studying.storedemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.studying.storedemo.model.Models
import kotlinx.android.synthetic.main.item.view.*

class ResponseAdapter :
    RecyclerView.Adapter<ResponseAdapter.ResponseHolder>() {
    private val originalList = mutableListOf<Models>()

    fun update(newList: List<Models>) {
        originalList.apply {
            clear()
            addAll(newList)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ResponseHolder(LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false))

    override fun getItemCount() = originalList.size

    override fun onBindViewHolder(holder: ResponseHolder, position: Int) {
        holder.bind(originalList[position])
    }

    class ResponseHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(model: Models) {
            itemView.response_type.text = model.type
            itemView.number.text = model.number.toString()
        }
    }


}
