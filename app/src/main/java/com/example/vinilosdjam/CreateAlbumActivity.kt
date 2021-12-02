package com.example.vinilosdjam

import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_create_album.*
import org.json.JSONObject

class CreateAlbumActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_album)
        val genres = resources.getStringArray(R.array.genres)
        val genreArrayAdapter = ArrayAdapter(this, R.layout.genre_dropdown_item, genres)
        autoCompleteGenre.setAdapter(genreArrayAdapter)
        val recordLabel = resources.getStringArray(R.array.record_label)
        val recordLabelArrayAdapter = ArrayAdapter(this, R.layout.record_label_dropdown_item, recordLabel)
        autoCompleteRecordLabel.setAdapter(recordLabelArrayAdapter)
        btCancelAlbum.setOnClickListener {
            finish()
        }

        btAddAlbum.setOnClickListener {
            createAlbum()
        }

        autoCompleteGenre.setOnClickListener {
            hideKeyboard(this)
        }

        autoCompleteRecordLabel.setOnClickListener {
            hideKeyboard(this)
        }
    }

    private fun hideKeyboard(activity: Activity) {
        val inputManager = activity
            .getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager

        // check if no view has focus:
        val currentFocusedView: View? = activity.currentFocus
        if (currentFocusedView != null) {
            inputManager.hideSoftInputFromWindow(
                currentFocusedView.getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS
            )
        }
    }

    private fun createAlbum(){
        val queue = Volley.newRequestQueue(this)
        val url = "https://backvynils17.herokuapp.com/albums"
        val jsonData =  JSONObject()

        jsonData.put("name", inputTitleAlbum.text.toString())
        jsonData.put("cover", inputCoverURL.text.toString())
        jsonData.put("releaseDate", inputYear.text.toString())
        jsonData.put("description", inputAlbumDescription.text.toString())
        jsonData.put("genre", autoCompleteGenre.text.toString())
        jsonData.put("recordLabel", autoCompleteRecordLabel.text.toString())


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