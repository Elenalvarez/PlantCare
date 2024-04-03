package com.yao.plantcare.my_plants.add_my_plant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.yao.plantcare.database.AllDatabase
import com.yao.plantcare.database.AllRepository
import com.yao.plantcare.database.AllViewModel
import com.yao.plantcare.database.MyPlants.MyPlantEntity
import com.yao.plantcare.databinding.FragmentAddMyPlantBinding

class AddMyPlantFragment(arg: Int) : Fragment() {
    private var _binding: FragmentAddMyPlantBinding? = null
    private val binding get() = _binding!!
    private val idPlant = arg
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddMyPlantBinding.inflate(inflater, container, false)
        val root = binding.root

        val db = activity?.let {
            Room.databaseBuilder(it, AllDatabase::class.java, "all_db").build()
        }
        val allDao = db?.AllDao()
        val repository = allDao?.let { AllRepository(it) }
        val viewModel = repository?.let { AllViewModel(it) }

        binding.btnAddMyPlant.setOnClickListener {
            val name = binding.etName.text.toString()
            val last_irrigation = Integer.parseInt(binding.etLastIrrigation.text.toString())
            val last_fertilize = Integer.parseInt(binding.etLastFertilize.text.toString())
            val img = binding.etMyImage.text.toString()

            val myPlant = MyPlantEntity(null, idPlant, name, last_irrigation, last_fertilize, img)
            viewModel?.addMyPlant(myPlant)
            Toast.makeText(requireContext(), "Añadido correctamente", Toast.LENGTH_LONG).show()
        }

        return root
    }
}