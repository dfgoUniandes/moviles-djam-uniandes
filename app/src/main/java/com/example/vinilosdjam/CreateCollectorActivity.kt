package com.example.vinilosdjam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_create_collector.*
import org.json.JSONObject

class CreateCollectorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_collector)

        btCancelCollector.setOnClickListener {
            finish()
        }

        btAddCollector.setOnClickListener {
            createCollector()
        }
    }

    private fun createCollector(){
        val queue = Volley.newRequestQueue(this)
        val url = "https://backvynils17.herokuapp.com/collectors"
        val jsonData =  JSONObject()

        jsonData.put("name", inputCollectorName.text.toString())
        jsonData.put("telephone", inputCollectorPhone.text.toString())
        jsonData.put("email", inputCollectorEmail.text.toString())


        val jsonObjectRequest = JsonObjectRequest(Request.Method.POST, url, jsonData,
            Response.Listener { response ->
                print(response)
                finish()
            },
            Response.ErrorListener { error ->
                print(error.message)
            })

        queue.add(jsonObjectRequest)
    }
}