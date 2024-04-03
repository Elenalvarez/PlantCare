package com.yao.plantcare.database

import androidx.room.Embedded
import androidx.room.Relation
import com.yao.plantcare.database.MyPlants.MyPlantEntity
import com.yao.plantcare.database.Plant.PlantEntity

data class AllPlantEntity(
    @Embedded val plant: PlantEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "plantId"
    ) val MyPlants: List<MyPlantEntity>
)
