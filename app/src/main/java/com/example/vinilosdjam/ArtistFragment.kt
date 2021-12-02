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
import com.example.vinilosdjam.adapters.ArtistListAdapter
import com.example.vinilosdjam.models.Artist
import com.example.vinilosdjam.viewmodels.ArtistViewModel


class ArtistFragment : Fragment(), ArtistListAdapter.OnArtistClickListener {

    var list = mutableListOf<Artist>()
    private lateinit var viewModel: ArtistViewModel
    private lateinit var recyclerView: RecyclerView
    private var viewModelAdapter: ArtistListAdapter? = null


    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_artist, container, false)

        viewModelAdapter = ArtistListAdapter(this)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = view.findViewById(R.id.rvFragmentArtistList)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = viewModelAdapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity) {

        }
        viewModel = ViewModelProvider(this, ArtistViewModel.Factory(activity.application)).get(ArtistViewModel::class.java)
        viewModel.artist.observe(viewLifecycleOwner, Observer<List<Artist>> {
            it.apply {
                viewModelAdapter!!.artists = this
                list = this as MutableList<Artist>
            }
        })
        viewModel.eventNetworkError.observe(viewLifecycleOwner, Observer<Boolean> { isNetworkError ->
            if (isNetworkError) onNetworkError()
        })
    }

    override fun onArtistClick(position: Int) {
        val clickedArtist = list[position]
        val intent = Intent(activity, ArtistDetailActivity::class.java)
        intent.putExtra("ID", clickedArtist.id)
        startActivity(intent)
    }

    private fun onNetworkError() {
        if(!viewModel.isNetworkErrorShown.value!!) {
            Toast.makeText(activity, "Network Error", Toast.LENGTH_LONG).show()
            viewModel.onNetworkErrorShown()
        }
    }

}