package com.example.vinilosdjam

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.vinilosdjam.adapters.ArtistListAdapter

import com.example.vinilosdjam.models.Artist

import org.json.JSONArray


class ArtistFragment : Fragment(), ArtistListAdapter.OnArtistClickListener {

    var list = mutableListOf<Artist>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater!!.inflate(R.layout.fragment_artist, container, false)
        initArtists(view)
        return view
    }

    fun initArtists(view: View){
        var queue = Volley.newRequestQueue(activity)
        var url = "https://backvynils17.herokuapp.com/musicians"
        val stringRequest = StringRequest( url,
            { response ->
                val resp = JSONArray(response)

                for (i in 0 until resp.length()) {
                    val item = resp.getJSONObject(i)
                    list.add(i, Artist(
                        id = item.getInt("id"),
                        name = item.getString("name"),
                        image = item.getString("image"),
                        birthDate = item.getString("birthDate"),
                        description = item.getString("description"),
                        albums = item.getJSONArray("albums"),
                        performerPrizes = item.getJSONArray("performerPrizes")))
                }
                val recyclerView = view.findViewById<RecyclerView>(R.id.rvFragmentArtistList)
//                val rv = view.findViewById<>(R.id.rvAlbumList)
                recyclerView.layoutManager = LinearLayoutManager(activity)
                val adapter = ArtistListAdapter(list, this)
                recyclerView.adapter = adapter
            },
            { error ->
                error.printStackTrace()
            })
        queue.add(stringRequest)
    }

    override fun onArtistClick(position: Int) {
        val clickedArtist = list[position]
        val intent = Intent(activity, ArtistDetailActivity::class.java)
        intent.putExtra("ID", clickedArtist.id)
        startActivity(intent)

    }

}