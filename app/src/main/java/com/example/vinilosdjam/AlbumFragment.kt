package com.example.vinilosdjam

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.vinilosdjam.adapters.AlbumListsAdapter
import com.example.vinilosdjam.models.Album
import kotlinx.android.synthetic.main.album_list.*
import kotlinx.android.synthetic.main.fragment_album.*
import org.json.JSONArray


class AlbumFragment : Fragment(), AlbumListsAdapter.OnAlbumClickListener {
    var list = mutableListOf<Album>()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initAlbums()
        return inflater.inflate(R.layout.fragment_album, container, false)
    }

    private fun initAlbums() {
        var queue = Volley.newRequestQueue()
        var url = "https://backvynils17.herokuapp.com/albums"
        val stringRequest = StringRequest( url,
            { response ->
                val resp = JSONArray(response)

                for (i in 0 until resp.length()) {
                    val item = resp.getJSONObject(i)
                    list.add(i, Album(
                        id = item.getInt("id"),
                        name = item.getString("name"),
                        cover = item.getString("cover"),
                        recordLabel = item.getString("recordLabel"),
                        releaseDate = item.getString("releaseDate"),
                        genre = item.getString("genre"),
                        description = item.getString("description"),
                        performers = item.getJSONArray("performers"),
                        tracks = item.getJSONArray("tracks"),
                        comments = item.getJSONArray("comments")))
                }
                rvAlbumFragment.layoutManager = LinearLayoutManager(activity)
                val adapter = AlbumListsAdapter(list, this)
                rvAlbumFragment.adapter = adapter
            },
            { error ->
                error.printStackTrace()
            })
        queue.add(stringRequest)
    }

}