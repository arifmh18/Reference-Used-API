package com.ardat.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_film.*

class FilmAdapter(private val context: Context, private val items:
List<RespondsAPIItem>, private val listener: (RespondsAPIItem)-> Unit) :
    RecyclerView.Adapter<FilmAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(context, LayoutInflater.from(context).inflate(R.layout.item_film,
            parent, false))

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items.get(position), listener)
    }


    class ViewHolder(val context: Context, override val containerView : View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bindItem(item: RespondsAPIItem, listener: (RespondsAPIItem) -> Unit) {
            val titleItem = containerView.findViewById<TextView>(R.id.itemTitle)
            val DirectorItem = containerView.findViewById<TextView>(R.id.itemDirector)

            titleItem.text = item.title
            DirectorItem.text = item.director
            containerView.setOnClickListener { listener(item) }
        }
    }
}