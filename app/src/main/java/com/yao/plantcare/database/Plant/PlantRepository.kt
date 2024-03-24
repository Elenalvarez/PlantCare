package com.yao.plantcare.database.Plant

import com.yao.plantcare.domain.Plant

class PlantRepository(private val plantDao : PlantDao) {

    suspend fun getPlants(): List<Plant>{
        val entities = plantDao.getPlants()
        return entities.map {
            Plant(commonName = it.commonName,
                species = it.species,
                level = it.level,
                temperature = it.temperature,
                sunLevel = it.sunLevel,
                location = it.location,
                irrigation = it.irrigation,
                fertilize = it.fertilize,
                image = it.image)
        }
    }

    suspend fun insertPlant(plant: Plant){
        val entity = PlantEntity(
            commonName = plant.commonName,
            species = plant.species,
            level = plant.level,
            temperature = plant.temperature,
            sunLevel = plant.sunLevel,
            location = plant.location,
            irrigation = plant.irrigation,
            fertilize = plant.fertilize,
            image = plant.image)
        plantDao.insertPlant(entity)
    }
}