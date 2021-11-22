package com.example.vinilosdjam

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.toolbox.Volley
import com.android.volley.toolbox.StringRequest
import com.example.vinilosdjam.adapters.AlbumListsAdapter
import com.example.vinilosdjam.models.Album
import kotlinx.android.synthetic.main.album_list.*
import org.json.JSONArray

class AlbumListActivity : AppCompatActivity(), AlbumListsAdapter.OnAlbumClickListener {
    val list = mutableListOf<Album>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView((R.layout.album_list))
        initAlbums()
    }

    @SuppressLint("RestrictedApi")
    fun initAlbums(){
        var queue = Volley.newRequestQueue(this)
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
                rvAlbumList.layoutManager = LinearLayoutManager(this)
                val adapter = AlbumListsAdapter(list, this)
                rvAlbumList.adapter = adapter
            },
            { error ->
                error.printStackTrace()
            })
        queue.add(stringRequest)
    }

    override fun onAlbumClick(position: Int) {
//        Toast.makeText(this, "Album $position", Toast.LENGTH_SHORT).show()
        val clickedAlbum = list[position]
        val intent = Intent(this, AlbumDetailActivity::class.java)
        intent.putExtra("ID", clickedAlbum.id)
        startActivity(intent)

    }

}