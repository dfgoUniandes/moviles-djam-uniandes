package com.example.vinilosdjam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.album_detail.*
import org.json.JSONObject

class AlbumDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.album_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
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

            },
            { error ->
                error.printStackTrace()
            })
        queue.add(stringRequest)

    }
}


