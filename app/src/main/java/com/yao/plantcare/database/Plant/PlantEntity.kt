package com.yao.plantcare.database.Plant

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Plant")
data class PlantEntity(
    @PrimaryKey(autoGenerate = true) val id : Int? = null,
    val commonName: String,
    val species: String,
    val level: String,
    val temperature: String,
    val sunLevel: String,
    val location: String,
    val irrigation: Int,
    val fertilize: Int,
    val image: String
)


