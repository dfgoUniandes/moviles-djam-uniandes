package com.example.vinilosdjam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_create_prize.*
import org.json.JSONObject

class CreatePrizeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_prize)

        btCancelPrize.setOnClickListener {
            finish()
        }
        btAddPrize.setOnClickListener {
            addPrize()
        }
    }

    private fun addPrize(){
        val queue = Volley.newRequestQueue(this)
        val url = "https://backvynils17.herokuapp.com/prizes"
        val jsonData =  JSONObject()

        jsonData.put("name", inputPrizeName.text.toString())
        jsonData.put("description", inputPrizeDescription.text.toString())
        jsonData.put("organization", inputPrizeOrganization.text.toString())

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.POST, url, jsonData,
            Response.Listener { response ->
                Toast.makeText(this, "Premio creado", Toast.LENGTH_SHORT).show()
                finish()
            },
            Response.ErrorListener { error ->
                Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show()
            })

        queue.add(jsonObjectRequest)
    }
}