package com.yao.plantcare.database.Plant

import androidx.lifecycle.LiveData

class PlantRepository(private val plantDao : PlantDao) {

    val readAllData: LiveData<List<PlantEntity>> = plantDao.readAllData()

    suspend fun insertPlant(plant: PlantEntity){
        plantDao.insertPlant(plant)
    }
}