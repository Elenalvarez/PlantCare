package com.yao.plantcare.my_plants

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.yao.plantcare.R
import com.yao.plantcare.database.MyPlants.MyPlantDatabase
import com.yao.plantcare.database.MyPlants.MyPlantRepository
import com.yao.plantcare.database.MyPlants.MyPlantViewModel
import com.yao.plantcare.databinding.FragmentMyPlantsBinding
import com.yao.plantcare.list.ListMyPlantsAdapter
import com.yao.plantcare.list.ListPlantsFragment

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

        val db = activity?.let {
            Room.databaseBuilder(it, MyPlantDatabase::class.java, "my_plants_db_2").build()
        }

        val myPlantDao = db?.myPlantDao()
        val myPlantRepository = myPlantDao?.let { MyPlantRepository(it) }
        val MyPlantViewModel = myPlantRepository?.let { MyPlantViewModel(it) }

        val adapter = ListMyPlantsAdapter()
        val recyclerView: RecyclerView = binding.rvSelectionMyPlant
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        MyPlantViewModel?.readAllCompleteData()?.observe(viewLifecycleOwner, Observer {plant ->
            adapter.setData(plant)
        })

        binding.fabAddMyPlant.setOnClickListener {
            val fragment = ListPlantsFragment()
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.flFragment, fragment)?.commit()
        }

        return root
    }

}