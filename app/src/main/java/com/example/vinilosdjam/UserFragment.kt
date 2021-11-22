package com.example.vinilosdjam

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.vinilosdjam.adapters.UserListAdapter
import com.example.vinilosdjam.models.User
import org.json.JSONArray


class UserFragment : Fragment(), UserListAdapter.OnUserClickListener {

    var list = mutableListOf<User>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater!!.inflate(R.layout.fragment_user, container, false)
        initUsers(view)
        return view
    }

    fun initUsers(view: View){
        var queue = Volley.newRequestQueue(activity)
        var url = "https://backvynils17.herokuapp.com/collectors"
        val stringRequest = StringRequest( url,
            { response ->
                val resp = JSONArray(response)

                for (i in 0 until resp.length()) {
                    val item = resp.getJSONObject(i)
                    list.add(i, User(
                        id = item.getInt("id"),
                        name = item.getString("name"),
                        email = item.getString("email"),
                        telephone = item.getString("telephone"),
                        collectorAlbums = item.getJSONArray("collectorAlbums"),
                        favoritePerformers = item.getJSONArray("favoritePerformers"),
                        comments = item.getJSONArray("comments")
                        ))
                }
                val recyclerView = view.findViewById<RecyclerView>(R.id.rvFragmentUserList)
                recyclerView.layoutManager = LinearLayoutManager(activity)
                val adapter = UserListAdapter(list, this)
                recyclerView.adapter = adapter
            },
            { error ->
                error.printStackTrace()
            })
        queue.add(stringRequest)
    }


    override fun onUserClick(position: Int) {
        val clickedArtist = list[position]


    }



}