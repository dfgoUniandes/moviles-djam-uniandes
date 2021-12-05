package com.example.vinilosdjam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_user_detail.*
import org.json.JSONObject

class UserDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        getUserId()
    }

    fun getUserId(){
        val bundle = intent.extras
        val id = bundle?.get("ID")
        val queue = Volley.newRequestQueue(this)
        val url = "https://backvynils17.herokuapp.com/collectors/$id"

        val stringRequest = StringRequest( url,
            { response ->
                val resp = JSONObject(response)

                tvUserName.text = resp["name"] as CharSequence?
                tvUserTelephone.text = resp["telephone"] as CharSequence?
                tvUserEmail.text = resp["email"] as CharSequence?
            },
            { error ->
                error.printStackTrace()
            })
        queue.add(stringRequest)

    }
}