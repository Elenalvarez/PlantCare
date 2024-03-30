package com.yao.plantcare.my_plants.list_plants

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
import com.yao.plantcare.databinding.FragmentListPlantsBinding

class ListPlantsFragment : Fragment() {
    private  var _binding: FragmentListPlantsBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListPlantsBinding.inflate(inflater, container, false)
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

        viewModel?.readAllData?.observe(viewLifecycleOwner, Observer { plant ->
            adapter.setData(plant)
        })

        return root
    }
}