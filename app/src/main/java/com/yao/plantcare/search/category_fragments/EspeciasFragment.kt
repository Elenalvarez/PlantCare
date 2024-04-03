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
import com.yao.plantcare.R
import com.yao.plantcare.database.AllDatabase
import com.yao.plantcare.database.AllRepository
import com.yao.plantcare.database.AllViewModel
import com.yao.plantcare.databinding.FragmentEspeciasBinding
import com.yao.plantcare.list.ListCategoryPlantsAdapter
import com.yao.plantcare.records.PlantFragment

class EspeciasFragment : Fragment() {
    private var _binding: FragmentEspeciasBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEspeciasBinding.inflate(inflater, container, false)
        val root = binding.root

        val db = activity?.let {
            Room.databaseBuilder(it, AllDatabase::class.java, "all_db").build()
        }
        val allDao = db?.AllDao()
        val repository = allDao?.let { AllRepository(it) }
        val viewModel = repository?.let { AllViewModel(it) }

        val adapter = ListCategoryPlantsAdapter()
        val recyclerView: RecyclerView = binding.rvSelectionPlant
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel?.readCategoryData("especias")?.observe(viewLifecycleOwner, Observer { plant ->
            adapter.setData(plant)
        })

        return root
    }

    fun toPlantFragment(id: Int){
        val fragment = PlantFragment(id)
        val transaction = fragmentManager?.beginTransaction()
        transaction?.replace(R.id.flFragment, fragment)?.commit()
    }
}