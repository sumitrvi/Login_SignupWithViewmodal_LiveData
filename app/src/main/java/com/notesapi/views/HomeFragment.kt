package com.notesapi.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.livedata.app.R
import com.livedata.app.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    var _binding:FragmentHomeBinding?=null;
    val binding get() = _binding!!;
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding=FragmentHomeBinding.inflate(inflater, container, false)
        return  binding.root;
    }


}