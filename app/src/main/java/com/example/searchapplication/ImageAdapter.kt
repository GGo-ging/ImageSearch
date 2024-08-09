package com.example.searchapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ImageAdapter(private val onImageClick: (Image) -> Unit) :
    RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    private val images = mutableListOf<Image>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val image = images[position]
        holder.bind(image)
        holder.itemView.setOnClickListener { onImageClick}
        holder.bind(images[position])
    }

    override fun getItemCount(): Int = images.size

    fun submitList(newImages: List<Image>) {
        images.clear()
        images.addAll(newImages)
        notifyDataSetChanged()
    }

    inner class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val imageThumnail : ImageView = itemView.findViewById(R.id.item_thumbnailImageView)
        private val textSiteName : TextView = itemView.findViewById(R.id.item_text_SiteName)
        private val textDateTime : TextView = itemView.findViewById(R.id.item_text_DateTime)

        fun bind(image: Image) {
            Glide.with(itemView.context).load(image.thumbnailUrl).into(imageThumnail)
            textSiteName.text = image.displaySitename
            textDateTime.text = formatDate(image.datetime)
        }

        private fun formatDate(dateTime : String) : String {
            return dateTime
        }
    }
}
