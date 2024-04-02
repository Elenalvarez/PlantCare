package com.yao.plantcare.database.MyPlants

import androidx.lifecycle.LiveData

class MyPlantRepository(private val myPlantDao: MyPlantDao) {

    val readAllData: LiveData<List<MyPlantEntity>> = myPlantDao.readAllData()

    suspend fun insertMyPlant(myPlant: MyPlantEntity){
        myPlantDao.insertMyPlant(myPlant)
    }

    fun readMyPlantById(id: Int): LiveData<MyPlantEntity>{
        return myPlantDao.getMyPlantById(id)
    }
}