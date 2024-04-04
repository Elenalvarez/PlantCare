package com.yao.plantcare.homework

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.yao.plantcare.database.AllDatabase
import com.yao.plantcare.database.AllRepository
import com.yao.plantcare.database.AllViewModel
import com.yao.plantcare.databinding.FragmentHomeworkBinding
import com.yao.plantcare.list.ListFertilizeTaskAdapter
import com.yao.plantcare.list.ListIrrigationTaskAdapter

class HomeworkFragment : Fragment() {
    private var _binding: FragmentHomeworkBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeworkBinding.inflate(inflater, container, false)
        val root = binding.root

        val db = activity?.let {
            Room.databaseBuilder(it, AllDatabase::class.java, "all_db").build()
        }
        val allDao = db?.AllDao()
        val repository = allDao?.let { AllRepository(it) }
        val viewModel = repository?.let { AllViewModel(it) }

        val adapterIrrigation = ListIrrigationTaskAdapter()
        val recyclerViewIrrigation: RecyclerView = binding.rvSelectionIrrigationTask
        recyclerViewIrrigation.adapter = adapterIrrigation
        recyclerViewIrrigation.layoutManager = LinearLayoutManager(requireContext())

        val adapterFertilize = ListFertilizeTaskAdapter()
        val recyclerViewFertilize: RecyclerView = binding.rvFertilizeSelectionTask
        recyclerViewFertilize.adapter = adapterFertilize
        recyclerViewFertilize.layoutManager = LinearLayoutManager(requireContext())

        viewModel?.readIrrigationMyPlant()?.observe(viewLifecycleOwner, Observer { plant ->
            adapterIrrigation.setData(plant)
        })

        viewModel?.readFertilizeMyPlant()?.observe(viewLifecycleOwner, Observer { plant ->
            adapterFertilize.setData(plant)
        })

        return root
    }

}