package com.yao.plantcare.database

import androidx.lifecycle.LiveData
import com.yao.plantcare.database.MyPlants.MyPlantEntity
import com.yao.plantcare.database.Plant.PlantEntity

class AllRepository(private val allDao: AllDao) {

    val getAllMyPlant: LiveData<List<AllPlantEntity>> = allDao.getAllMyPlant()
    val getPlants: LiveData<List<PlantEntity>> = allDao.getPlants()
    val getMyPlants: LiveData<List<MyPlantEntity>> = allDao.getMyPlants()

    suspend fun insertPlant(plant: PlantEntity){
        allDao.insertPlant(plant)
    }

    suspend fun insertMyPlant(myPlant: MyPlantEntity){
        allDao.insertMyPlant(myPlant)
    }

    fun readCategoryData(category: String): LiveData<List<PlantEntity>>{
        return allDao.getPlantListInfoByType(category)
    }

    fun readPlantById(id: Int): LiveData<PlantEntity>{
        return allDao.getPlantById(id)
    }

    fun readMyPlantById(id: Int): LiveData<AllPlantEntity>{
        return allDao.getAllMyPlantById(id)
    }

    fun readIrrigationMyPlant(): LiveData<List<MyPlantEntity>>{
        return allDao.getIrrigationMyPlant()
    }

    fun readFertilizeMyPlant(): LiveData<List<MyPlantEntity>>{
        return allDao.getFertilizeMyPlant()
    }

    fun getMyPlantById(id: Int): LiveData<MyPlantEntity>{
        return allDao.getMyPlantById(id)
    }

    fun deleteMyPlant(myPlant: MyPlantEntity){
        allDao.deleteMyPlant(myPlant)
    }

    fun updateIrrigation(id: Int){
        allDao.updateIrrigation(id)
    }

    fun updateFertilize(id: Int){
        allDao.updateFertilize(id)
    }
}