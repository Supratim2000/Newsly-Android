package com.example.newsly

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

class Adapter(private val listener: onItemClicked): RecyclerView.Adapter<viewHolder>() {

    private val data: ArrayList<NewsDataClass> = ArrayList<NewsDataClass>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.news_item,parent,false)
        val viewHolderInstance: viewHolder = viewHolder(view)
        view.setOnClickListener{
            listener.onItemClicked(data[viewHolderInstance.adapterPosition])
        }
        return viewHolderInstance
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val currentItem = data[position]
        holder.newsTitle.text = currentItem.getTitle()
        holder.newsAuthor.text = "Author: ${currentItem.getAuthor()}"
        Glide.with(holder.itemView.context).load(currentItem.getUrlToImage()).placeholder(R.drawable.loading_image).listener(object: RequestListener<Drawable>{
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                holder.newsImage.setImageResource(R.drawable.img_not_found)
                return false
            }

            override fun onResourceReady(
                resource: Drawable,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                return false
            }
        }).into(holder.newsImage)
    }

    override fun getItemCount(): Int = data.size

    public fun updateNews(updatedNewsArray: ArrayList<NewsDataClass>) {
        data.clear()
        data.addAll(updatedNewsArray)
        notifyDataSetChanged()
    }

}

class viewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val newsTitle: TextView = itemView.findViewById<TextView>(R.id.news_title)
    val newsAuthor: TextView = itemView.findViewById<TextView>(R.id.news_author)
    val newsImage: ImageView = itemView.findViewById<ImageView>(R.id.news_img)
}

interface onItemClicked {
    fun onItemClicked(data: NewsDataClass)
}