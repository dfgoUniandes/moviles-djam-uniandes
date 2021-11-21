package com.example.vinilosdjam.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide.init
import com.example.vinilosdjam.R
import com.example.vinilosdjam.models.Album
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.album_list_item.view.*


class AlbumListsAdapter(
    val albums:List<Album>,
    val listener: OnAlbumClickListener) : RecyclerView.Adapter<AlbumListsAdapter.ViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.album_list_item, parent, false))

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.render(albums[position])
    }

    override fun getItemCount(): Int {
        return albums.size
    }

    inner class ViewHolder(val view:View):RecyclerView.ViewHolder(view), View.OnClickListener {
        fun render(album:Album){
            view.tvName.text = album.name
            view.tvgenre.text = album.genre
            Picasso.get().load(album.cover).into(view.ivCover)
        }
        init {
            view.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position : Int = adapterPosition
            if (position != RecyclerView.NO_POSITION){
                listener.onAlbumClick(position)
            }
        }
    }

    interface OnAlbumClickListener {
        fun onAlbumClick(position: Int){


        }
    }

}