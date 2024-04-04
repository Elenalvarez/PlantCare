package com.yao.plantcare.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.yao.plantcare.database.MyPlants.MyPlantEntity
import com.yao.plantcare.database.Plant.PlantEntity

@Dao
interface AllDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlant(plant: PlantEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMyPlant(myPlant: MyPlantEntity)

    @Query("SELECT * FROM plants")
    fun getPlants(): LiveData<List<PlantEntity>>

    @Query("SELECT * FROM my_plants")
    fun getMyPlants(): LiveData<List<MyPlantEntity>>

    @Transaction
    @Query("SELECT * FROM plants")
    fun getAllMyPlant(): LiveData<List<AllPlantEntity>>

    @Query("SELECT * FROM plants WHERE type = :type")
    fun getPlantListInfoByType(type: String): LiveData<List<PlantEntity>>

    @Query("SELECT * FROM plants WHERE id = :id")
    fun getPlantById(id: Int): LiveData<PlantEntity>

    @Query("SELECT * FROM plants WHERE plants.id = :id")
    fun getMyPlantById(id: Int): LiveData<AllPlantEntity>
}