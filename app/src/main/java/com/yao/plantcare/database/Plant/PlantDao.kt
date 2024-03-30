package com.yao.plantcare.database.Plant

import androidx.lifecycle.LiveData
import androidx.room.ColumnInfo
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
    @Query("SELECT commonName, species, level, location, image FROM plants WHERE type = :type")
    fun getPlantListInfoByType(type: String): Array<infoList>
    @Query("SELECT commonName, species, level, location, image FROM plants")
    fun getPlantsListInfo(): Array<infoList>
}

data class infoList(
    @ColumnInfo(name = "common_name") val commonName: String?,
    @ColumnInfo(name = "Specie") val specie: String?,
    @ColumnInfo(name = "level") val level: String?,
    @ColumnInfo(name = "location") val location: String?,
    @ColumnInfo(name = "image") val image: String?
)