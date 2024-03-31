package com.yao.plantcare.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.yao.plantcare.R
import com.yao.plantcare.databinding.FragmentSearchBinding
import com.yao.plantcare.search.add_plant.AddPlantFragment
import com.yao.plantcare.search.category_fragments.CactusFragment
import com.yao.plantcare.search.category_fragments.EspeciasFragment
import com.yao.plantcare.search.category_fragments.FloresFragment
import com.yao.plantcare.search.category_fragments.FollajeFragment
import com.yao.plantcare.search.category_fragments.HortalizasFragment

class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val root = binding.root

        binding.fabNewPlant.setOnClickListener {
            val fragment = AddPlantFragment()
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.flFragment, fragment)?.commit()
        }

        binding.imageFlores.setOnClickListener{
            val fragment = FloresFragment()
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.flFragment, fragment)?.commit()
        }

        binding.imageFollaje.setOnClickListener{
            val fragment = FollajeFragment()
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.flFragment, fragment)?.commit()
        }

        binding.imageCactus.setOnClickListener{
            val fragment = CactusFragment()
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.flFragment, fragment)?.commit()
        }

        binding.imageVegetables.setOnClickListener{
            val fragment = HortalizasFragment()
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.flFragment, fragment)?.commit()
        }

        binding.imageSpecies.setOnClickListener{
            val fragment = EspeciasFragment()
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.flFragment, fragment)?.commit()
        }

        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}