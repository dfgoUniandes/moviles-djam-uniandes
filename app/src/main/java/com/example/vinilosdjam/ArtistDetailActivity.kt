package com.example.vinilosdjam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_artist_detail.*
import org.json.JSONObject

class ArtistDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artist_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        getArtistId()
    }

    fun getArtistId(){
        val bundle = intent.extras
        val id = bundle?.get("ID")
        val queue = Volley.newRequestQueue(this)
        val url = "https://backvynils17.herokuapp.com/musicians/$id"

        val stringRequest = StringRequest( url,
            { response ->
                val resp = JSONObject(response)
                val cover = resp["image"] as String?

                tvArtistName.text = resp["name"] as CharSequence?
                tvArtistDescription.text = resp["description"] as CharSequence?
                tvBirthDate.text = resp["birthDate"] as CharSequence?
                Picasso.get().load(cover).into(ivArtistCoverDetail)

            },
            { error ->
                error.printStackTrace()
            })
        queue.add(stringRequest)

    }
}