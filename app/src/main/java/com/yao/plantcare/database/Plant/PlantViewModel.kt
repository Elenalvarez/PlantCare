package com.yao.plantcare.database.Plant

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PlantViewModel(private val plantRepository: PlantRepository): ViewModel() {
    val readAllData: LiveData<List<PlantEntity>> = plantRepository.readAllData

    fun addPlant(plant: PlantEntity){
        viewModelScope.launch(Dispatchers.IO) {
            plantRepository.insertPlant(plant)
        }
    }
}