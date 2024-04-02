package com.yao.plantcare.database.MyPlants

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MyPlantDao {
    @Query("SELECT * FROM My_Plants")
    fun readAllData(): LiveData<List<MyPlantEntity>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMyPlant(myPlant: MyPlantEntity)
    @Query("SELECT * FROM my_plants WHERE id = :id")
    fun getMyPlantById(id: Int): LiveData<MyPlantEntity>

}

