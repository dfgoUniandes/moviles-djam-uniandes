package com.example.vinilosdjam

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vinilosdjam.adapters.UserListAdapter
import com.example.vinilosdjam.models.User
import com.example.vinilosdjam.viewmodels.UserViewModel

class UserFragment : Fragment(), UserListAdapter.OnUserClickListener {

    var list = mutableListOf<User>()

        private lateinit var viewModel: UserViewModel
    private lateinit var recyclerView: RecyclerView
    private var viewModelAdapter: UserListAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user, container, false)
//        initUsers(view)
        viewModelAdapter = UserListAdapter(this)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = view.findViewById(R.id.rvFragmentUserList)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = viewModelAdapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity) {

        }
        viewModel = ViewModelProvider(this, UserViewModel.Factory(activity.application)).get(UserViewModel::class.java)
        viewModel.user.observe(viewLifecycleOwner, Observer<List<User>> {
            it.apply {
                viewModelAdapter!!.users = this
                list = this as MutableList<User>
            }
        })
        viewModel.eventNetworkError.observe(viewLifecycleOwner, Observer<Boolean> { isNetworkError ->
            if (isNetworkError) onNetworkError()
        })
//        btCreateCollector.setOnClickListener {
//            val intent = Intent(activity, CreateCollectorActivity::class.java)
//            startActivity(intent)
//        }
    }

    private fun onNetworkError() {
        if(!viewModel.isNetworkErrorShown.value!!) {
            Toast.makeText(activity, "Network Error", Toast.LENGTH_LONG).show()
            viewModel.onNetworkErrorShown()
        }
    }

    override fun onUserClick(position: Int) {
        val clickedArtist = list[position]
        val intent = Intent(activity, UserDetailActivity::class.java)
        intent.putExtra("ID", clickedArtist.id)
        startActivity(intent)
    }
}