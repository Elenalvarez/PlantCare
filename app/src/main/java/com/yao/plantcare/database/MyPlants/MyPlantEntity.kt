package com.yao.plantcare.database.MyPlants

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "My_Plants")
data class MyPlantEntity (
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val plantId: Int, //el id que referencia a la planta de la tabla Plants (la foreignKey me da error así que no la pongo)
    val name: String, // el nombre que el dueño asigna a la planta
    val lastIrrigation: Int, // la última vez que la regó
    val lastFertilize: Int, // la última vez que la fertilizó
    val image: String //imagen de la planta (por falta de tiempo, se añade del drawable en vez de tomar foto)
)