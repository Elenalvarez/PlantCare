package com.yao.plantcare.database.MyPlants

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyPlantViewModel(private val myPlantRepository: MyPlantRepository): ViewModel() {
    val readAllData: LiveData<List<MyPlantEntity>> = myPlantRepository.readAllData

    fun addMyPlant(myPlant: MyPlantEntity){
        viewModelScope.launch(Dispatchers.IO) {
            myPlantRepository.insertMyPlant(myPlant)
        }
    }

    fun readMyPlantById(id: Int): LiveData<MyPlantEntity>{
        return myPlantRepository.readMyPlantById(id)
    }
}