package com.yao.plantcare.database.Plant

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PlantDao {
    @Query("SELECT * FROM plants")
    fun readAllData(): LiveData<List<PlantEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlant(plant: PlantEntity)
    @Query("SELECT * FROM plants WHERE type = :type")
    fun getPlantListInfoByType(type: String): LiveData<List<PlantEntity>>
}
