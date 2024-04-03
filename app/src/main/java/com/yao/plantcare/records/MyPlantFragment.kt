package com.yao.plantcare.records

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.room.Room
import com.yao.plantcare.R
import com.yao.plantcare.database.AllDatabase
import com.yao.plantcare.database.AllRepository
import com.yao.plantcare.database.AllViewModel
import com.yao.plantcare.databinding.FragmentMyPlantBinding

class MyPlantFragment(arg: Int) : Fragment() {

    private var _binding: FragmentMyPlantBinding? = null
    private val binding get() = _binding!!
    private val id= arg
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMyPlantBinding.inflate(inflater, container, false)
        val root = binding.root

        val db = activity?.let {
            Room.databaseBuilder(it, AllDatabase::class.java, "all_db").build()
        }
        val allDao = db?.AllDao()
        val repository = allDao?.let { AllRepository(it) }
        val viewModel = repository?.let { AllViewModel(it) }

        viewModel?.readMyPlantById(id)?.observe(viewLifecycleOwner, Observer {
            val image = getDrawable(it.plant.image)
            binding.myPlantImage.setImageResource(image)
            binding.myPlantName.text = it.MyPlants.get(id).name
        })

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