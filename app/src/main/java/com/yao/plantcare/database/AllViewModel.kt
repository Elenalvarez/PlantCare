package com.yao.plantcare.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yao.plantcare.database.MyPlants.MyPlantEntity
import com.yao.plantcare.database.Plant.PlantEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AllViewModel(private val allRepository: AllRepository): ViewModel() {

    val getAllMyPlant: LiveData<List<AllPlantEntity>> = allRepository.getAllMyPlant
    val getPlants: LiveData<List<PlantEntity>> = allRepository.getPlants
    val getMyPlants: LiveData<List<MyPlantEntity>> = allRepository.getMyPlants

    fun addPlant(plant: PlantEntity){
        viewModelScope.launch(Dispatchers.IO) {
            allRepository.insertPlant(plant)
        }
    }

    fun addMyPlant(myPlant: MyPlantEntity){
        viewModelScope.launch(Dispatchers.IO) {
            allRepository.insertMyPlant(myPlant)
        }
    }

    fun readCategoryData(category: String): LiveData<List<PlantEntity>>{
        return allRepository.readCategoryData(category)
    }

    fun readPlantById(id: Int): LiveData<PlantEntity>{
        return allRepository.readPlantById(id)
    }

    fun readMyPlantById(id: Int): LiveData<AllPlantEntity>{
        return allRepository.readMyPlantById(id)
    }

    fun readIrrigationMyPlant(): LiveData<List<MyPlantEntity>>{
        return allRepository.readIrrigationMyPlant()
    }

    fun readFertilizeMyPlant(): LiveData<List<MyPlantEntity>>{
        return allRepository.readFertilizeMyPlant()
    }

    fun getMyPlantById(id: Int): LiveData<MyPlantEntity>{
        return allRepository.getMyPlantById(id)
    }

    fun deleteMyPlant(myPlant: MyPlantEntity){
        viewModelScope.launch(Dispatchers.IO) {
            allRepository.deleteMyPlant(myPlant)
        }
    }

    fun updateIrrigation(id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            allRepository.updateIrrigation(id)
        }
    }

    fun updateFertilize(id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            allRepository.updateFertilize(id)
        }
    }
}