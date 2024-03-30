package com.yao.plantcare.search.add_plant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.yao.plantcare.database.Plant.PlantDatabase
import com.yao.plantcare.database.Plant.PlantEntity
import com.yao.plantcare.database.Plant.PlantRepository
import com.yao.plantcare.database.Plant.PlantViewModel
import com.yao.plantcare.databinding.FragmentAddPlantBinding

class AddPlantFragment : Fragment() {
    private var _binding: FragmentAddPlantBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddPlantBinding.inflate(inflater, container, false)
        val root = binding.root

        val db =
            activity?.let {
                Room.databaseBuilder(it, PlantDatabase::class.java, "plants_db").build()
            }
        val plantDao = db?.plantDao()
        val repository = plantDao?.let { PlantRepository(it) }
        val viewModel = repository?.let { PlantViewModel(it) }

        binding.btnAddPlant.setOnClickListener {
            //como estos datos los voy a meter yo para rellenar la base de datos, no voy a comprobarlos
            val commonName = binding.etCommonName.text.toString()
            val specie = binding.etSpecie.text.toString()
            val level = binding.etLevel.text.toString()
            val temperature = binding.etTemperature.text.toString()
            val sunLevel = binding.etSunLevel.text.toString()
            val location = binding.etLocation.text.toString()
            val irrigation = Integer.parseInt(binding.etIrrigation.text.toString())
            val fertilize = Integer.parseInt(binding.etFertilize.text.toString())
            val image = binding.etImage.text.toString()
            val type = binding.etType.text.toString()

            val plant= PlantEntity(null,commonName,specie,level,temperature,sunLevel,location,irrigation, fertilize, image, type)
            viewModel?.addPlant(plant)
            Toast.makeText(requireContext(), "Añadido correctamente", Toast.LENGTH_LONG).show()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}