package com.example.vinilosdjam.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vinilosdjam.R
import com.example.vinilosdjam.models.Artist
import com.example.vinilosdjam.models.User
import kotlinx.android.synthetic.main.user_list_item.view.*


class UserListAdapter(
    val listener: OnUserClickListener) : RecyclerView.Adapter<UserListAdapter.ViewHolder>(){

    var users :List<User> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.user_list_item, parent, false))

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.render(users[position])
    }

    override fun getItemCount(): Int {
        return users.size
    }

    inner class ViewHolder(val view:View):RecyclerView.ViewHolder(view), View.OnClickListener {
        fun render(user:User){
            view.userName.text = user.name
            view.userTelephone.text = user.telephone
            view.userEmail.text = user.email
        }
        init {
            view.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position : Int = adapterPosition
            if (position != RecyclerView.NO_POSITION){
                listener.onUserClick(position)
            }
        }
    }

    interface OnUserClickListener {
        fun onUserClick(position: Int){


        }
    }

}