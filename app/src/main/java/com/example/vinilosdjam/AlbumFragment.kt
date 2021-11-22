package com.example.vinilosdjam

import android.content.Intent
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
import org.json.JSONArray

import androidx.recyclerview.widget.RecyclerView





class AlbumFragment : Fragment(), AlbumListsAdapter.OnAlbumClickListener {
    var list = mutableListOf<Album>()


    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_album, container, false)
        initAlbums(view)
        return view
    }

    fun initAlbums(view: View){
        var queue = Volley.newRequestQueue(activity)
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
                val recyclerView = view.findViewById<RecyclerView>(R.id.rvFragmentAlbumList)
//                val rv = view.findViewById<>(R.id.rvAlbumList)
                recyclerView.layoutManager = LinearLayoutManager(activity)
                val adapter = AlbumListsAdapter(list, this)
                recyclerView.adapter = adapter
            },
            { error ->
                error.printStackTrace()
            })
        queue.add(stringRequest)
    }

    override fun onAlbumClick(position: Int) {
//        Toast.makeText(this, "Album $position", Toast.LENGTH_SHORT).show()
        val clickedAlbum = list[position]
        val intent = Intent(activity, AlbumDetailActivity::class.java)
        intent.putExtra("ID", clickedAlbum.id)
        startActivity(intent)

    }

}

