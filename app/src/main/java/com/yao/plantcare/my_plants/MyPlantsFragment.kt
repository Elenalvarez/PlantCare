package com.yao.plantcare.my_plants

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.yao.plantcare.R
import com.yao.plantcare.databinding.FragmentMyPlantsBinding
import com.yao.plantcare.my_plants.list_plants.ListPlantsFragment

class MyPlantsFragment : Fragment() {
    private var _binding: FragmentMyPlantsBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMyPlantsBinding.inflate(inflater, container, false)
        val root = binding.root

        binding.fabAddMyPlant.setOnClickListener {
            val fragment = ListPlantsFragment()
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.flFragment, fragment)?.commit()
        }

        return root
    }

}