package com.intuitchallenge.intuitcodingchallenge.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.intuitchallenge.intuitcodingchallenge.R
import com.intuitchallenge.intuitcodingchallenge.model.breed_response.CatBreedWrapper
import kotlinx.android.synthetic.main.cat_item_layout.view.*
import java.lang.NullPointerException

class CatRecyclerViewAdapter(private val applicationContext: Context?)
    : RecyclerView.Adapter<CatRecyclerViewAdapter.CatViewHolder>() {
    class CatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val textView : TextView = itemView.findViewById(R.id.catNameTextView)
    }

    var listOfCatBreed : List<CatBreedWrapper> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val catView = LayoutInflater.from(parent.context)
            .inflate(R.layout.cat_item_layout, parent, false)
        return CatViewHolder(catView)
    }

    override fun getItemCount(): Int {
        return listOfCatBreed.size
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        applicationContext?.let {
            Glide.with(it)
                .load(listOfCatBreed[position].catBreed.url)
                .into(holder.itemView.catImageView)
        }
        try {
            val catName = listOfCatBreed[position].catBreed.breeds?.get(0)?.name
            applicationContext?.let {
                holder.textView.setTextColor(
                    ContextCompat.getColor(
                        it,
                        listOfCatBreed[position].color
                    )
                )
            }
            holder.textView.text = catName
        }catch (exception: NullPointerException){
            Log.d("TAG_C", "onBindViewHolder: null pointer exception: ${exception.message}")
        }
    }
}