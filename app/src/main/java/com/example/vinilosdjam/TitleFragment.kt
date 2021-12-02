package com.example.vinilosdjam

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.*
import androidx.navigation.fragment.findNavController
import com.example.vinilosdjam.R
import com.example.vinilosdjam.databinding.FragmentTitle2Binding

class TitleFragment : Fragment() {
    private var _binding: FragmentTitle2Binding? = null
    private val binding get() = _binding!!

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.fragment_title2)
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//         Inflate the layout for this fragment
        _binding = FragmentTitle2Binding.inflate(inflater, container, false)
//        val binding: FragmentTitle2Binding = DataBindingUtil.inflate(
//            inflater, R.layout.fragment_title2, container, false)

        binding.button4.setOnClickListener {
            findNavController().navigate(TitleFragmentDirections.actionTitleToAlbums())
        }
        return binding.root
    }
}
