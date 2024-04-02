package com.yao.plantcare.database.MyPlants

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "MyPlants")
data class MyPlantEntity (
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val plantId: Int, //el id que referencia a la planta de la tabla Plants (la foreignKey me da error así que no la pongo)
    val name: String, // el nombre que el dueño asigna a la planta
    val lastIrrigation: Int, // la última vez que la regó
    val lastFertilize: Int // la última vez que la fertilizó
)