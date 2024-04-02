package com.yao.plantcare.database.Plant

import androidx.lifecycle.LiveData

class PlantRepository(private val plantDao : PlantDao) {

    val readAllData: LiveData<List<PlantEntity>> = plantDao.readAllData()

    suspend fun insertPlant(plant: PlantEntity){
        plantDao.insertPlant(plant)
    }

    fun readCategoryData(category: String): LiveData<List<PlantEntity>>{
        return plantDao.getPlantListInfoByType(category)
    }

    fun readPlantById(id: Int): LiveData<PlantEntity>{
        return plantDao.getPlantById(id)
    }
}