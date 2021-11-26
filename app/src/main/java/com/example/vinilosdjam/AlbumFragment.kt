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
import com.example.vinilosdjam.adapters.AlbumListsAdapter
import com.example.vinilosdjam.models.Album
import androidx.recyclerview.widget.RecyclerView
import com.example.vinilosdjam.viewmodels.AlbumViewModel


class AlbumFragment : Fragment(), AlbumListsAdapter.OnAlbumClickListener {
    var list = mutableListOf<Album>()

    private lateinit var viewModel: AlbumViewModel
    private lateinit var recyclerView: RecyclerView
    private var viewModelAdapter: AlbumListsAdapter? = null


    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_album, container, false)
        viewModelAdapter = AlbumListsAdapter(this)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = view.findViewById(R.id.rvFragmentAlbumList)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = viewModelAdapter
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity) {

        }
        viewModel = ViewModelProvider(this, AlbumViewModel.Factory(activity.application)).get(AlbumViewModel::class.java)
        viewModel.albums.observe(viewLifecycleOwner, Observer<List<Album>> {
            it.apply {
                viewModelAdapter!!.albums = this
                list = this as MutableList<Album>
            }
        })
        viewModel.eventNetworkError.observe(viewLifecycleOwner, Observer<Boolean> { isNetworkError ->
            if (isNetworkError) onNetworkError()
        })
    }


    override fun onAlbumClick(position: Int) {
        val clickedAlbum = list[position]
        val intent = Intent(activity, AlbumDetailActivity::class.java)
        intent.putExtra("ID", clickedAlbum.id)
        startActivity(intent)

    }

    private fun onNetworkError() {
        if(!viewModel.isNetworkErrorShown.value!!) {
            Toast.makeText(activity, "Network Error", Toast.LENGTH_LONG).show()
            viewModel.onNetworkErrorShown()
        }
    }

}