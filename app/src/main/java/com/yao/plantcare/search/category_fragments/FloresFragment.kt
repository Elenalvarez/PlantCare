package com.yao.plantcare.search.category_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.yao.plantcare.database.Plant.PlantDatabase
import com.yao.plantcare.database.Plant.PlantRepository
import com.yao.plantcare.database.Plant.PlantViewModel
import com.yao.plantcare.databinding.FragmentFloresBinding
import com.yao.plantcare.list.ListPlantsAdapter


class FloresFragment : Fragment() {
    private var _binding: FragmentFloresBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFloresBinding.inflate(inflater, container, false)
        val root = binding.root

        val db = activity?.let {
            Room.databaseBuilder(it, PlantDatabase::class.java, "plants_db").build()
        }
        val plantDao = db?.plantDao()
        val repository = plantDao?.let { PlantRepository(it) }
        val viewModel = repository?.let { PlantViewModel(it) }

        val adapter = ListPlantsAdapter()
        val recyclerView: RecyclerView = binding.rvSelectionPlant
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel?.readCategoryData("flores")?.observe(viewLifecycleOwner, Observer { plant ->
            adapter.setData(plant)
        })

        return root
    }

}