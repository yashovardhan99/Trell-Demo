package com.yashovardhan99.trelldemo.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.yashovardhan99.trelldemo.R

class ImagesViewAdapter (val imagesList:List<ImageInfo>) : RecyclerView.Adapter<ImagesViewAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesViewAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_image, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: ImagesViewAdapter.ViewHolder, position: Int) {
        holder.bindItems(imagesList[position])
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return imagesList.size
    }

    //the class is hodling the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(image: ImageInfo) {
            val  imgView=itemView.findViewById<ImageView>(R.id.image_view);
            imgView.load(image?.downloadUrl) {
                crossfade(true)
                placeholder(R.drawable.image_placeholder)
            }
        }
    }
}