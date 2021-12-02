package com.example.vinilosdjam.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vinilosdjam.R
import com.example.vinilosdjam.models.Artist
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.artist_list_item.view.*


class ArtistListAdapter(
//    val artists:List<Artist>,
    val listener: OnArtistClickListener) : RecyclerView.Adapter<ArtistListAdapter.ViewHolder>(){

    var artists :List<Artist> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.artist_list_item, parent, false))

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.render(artists[position])
    }

    override fun getItemCount(): Int {
        return artists.size
    }

    inner class ViewHolder(val view:View):RecyclerView.ViewHolder(view), View.OnClickListener {
        fun render(artist:Artist){
            view.artistDate.text = artist.birthDate
            view.artistName.text = artist.name
            Picasso.get().load(artist.image).into(view.ivArtistCover)
        }
        init {
            view.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position : Int = adapterPosition
            if (position != RecyclerView.NO_POSITION){
                listener.onArtistClick(position)
            }
        }
    }

    interface OnArtistClickListener {
        fun onArtistClick(position: Int){


        }
    }

}