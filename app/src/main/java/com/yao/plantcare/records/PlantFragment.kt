package com.yao.plantcare.records

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.room.Room
import com.yao.plantcare.R
import com.yao.plantcare.database.Plant.PlantDatabase
import com.yao.plantcare.database.Plant.PlantRepository
import com.yao.plantcare.database.Plant.PlantViewModel
import com.yao.plantcare.databinding.FragmentPlantBinding
import com.yao.plantcare.list.ListPlantsFragment
import com.yao.plantcare.my_plants.add_my_plant.AddMyPlantFragment

class PlantFragment(arg: Int) : Fragment() {
    private var _binding: FragmentPlantBinding? = null
    private val binding get() = _binding!!
    private val id= arg
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPlantBinding.inflate(inflater, container, false)
        val root = binding.root

        val db = activity?.let {
            Room.databaseBuilder(it, PlantDatabase::class.java, "plants_db").build()
        }
        val plantDao = db?.plantDao()
        val repository = plantDao?.let { PlantRepository(it) }
        val viewModel = repository?.let { PlantViewModel(it) }

        viewModel?.readPlantByIde(id)?.observe(viewLifecycleOwner, Observer {
            val image = getDrawable(it.image)
            binding.plantImage.setImageResource(image)
            binding.plantCommonName.text = it.commonName
            binding.plantSpecie.text = it.species
            binding.plantLevel.text = it.level
            binding.plantTemperature.text = it.temperature
            binding.plantSunLevel.text = it.sunLevel
            binding.plantLocation.text = it.location
            binding.plantIrrigation.text = it.irrigation.toString()
            binding.plantFertilize.text = it.fertilize.toString()
        })

        binding.backToList.setOnClickListener {
            val fragment = ListPlantsFragment()
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.flFragment, fragment)?.commit()
        }

        binding.btnAddMyPlant.setOnClickListener {
            val fragment = AddMyPlantFragment(id)
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.flFragment, fragment)?.commit()
        }

        return root
    }

    private fun getDrawable(param: String): Int{
        var d: Int = 0

        if (param == "aloe") d = R.drawable.aloe
        if (param == "fresas") d = R.drawable.fresas
        if (param == "hierbabuena") d = R.drawable.hierbabuena
        if (param == "jazmin") d = R.drawable.jazmin
        if (param == "poto") d = R.drawable.poto
        if (param == "romero") d = R.drawable.romero

        return d
    }
}