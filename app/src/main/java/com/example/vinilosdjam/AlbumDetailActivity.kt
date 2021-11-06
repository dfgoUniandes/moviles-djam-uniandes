package com.example.vinilosdjam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.vinilosdjam.adapters.AlbumListsAdapter
import com.example.vinilosdjam.models.Album
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.album_detail.*
import kotlinx.android.synthetic.main.album_list.*
import kotlinx.android.synthetic.main.album_list_item.view.*
import org.json.JSONArray
import org.json.JSONObject
import java.net.URL

class AlbumDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.album_detail)
        getAlbumId()
    }

    fun getAlbumId(){
        val bundle = intent.extras
        val id = bundle?.get("ID")
        var queue = Volley.newRequestQueue(this)
        var url = "https://backvynils17.herokuapp.com/albums/$id"

        val stringRequest = StringRequest( url,
            { response ->
                val resp = JSONObject(response)
                val cover = resp["cover"] as String?

                tvAlbumDetail.text = resp["name"] as CharSequence?
                tvGenreDetail.text = resp["genre"] as CharSequence?
                tvReleaseDate.text = resp["releaseDate"] as CharSequence?
                tvDescription.text = resp["description"] as CharSequence?
                Picasso.get().load(cover).into(ivCoverDetail)


//                for (i in 0 until resp.length()) {
//                    val item = resp.getJSONObject(i)
//                    list.add(i, Album(
//                        id = item.getInt("id"),
//                        name = item.getString("name"),
//                        cover = item.getString("cover"),
//                        recordLabel = item.getString("recordLabel"),
//                        releaseDate = item.getString("releaseDate"),
//                        genre = item.getString("genre"),
//                        description = item.getString("description"),
//                        performers = item.getJSONArray("performers"),
//                        tracks = item.getJSONArray("tracks"),
//                        comments = item.getJSONArray("comments"))
//                    )
//                }
//                rvAlbumList.layoutManager = LinearLayoutManager(this)
//                val adapter = AlbumListsAdapter(list, this)
//                rvAlbumList.adapter = adapter
            },
            { error ->
                error.printStackTrace()
            })
        queue.add(stringRequest)

    }
}


