package com.yao.plantcare.database.Plant

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Plants")
data class PlantEntity(
    @PrimaryKey(autoGenerate = true) val id : Int? = null,
    val commonName: String, //nombre común que se le da a la planta
    val species: String, // especie a la que pertenece
    val level: String, // nivel de dificultad de cuidados: sencillo, medio, dificil
    val temperature: String, // rango de temperaturas a las que sobrevive la planta
    val sunLevel: String, // nivel de sol recomendado: nulo, parcial, total
    val location: String, // si debe estar al exterior o en interior
    val irrigation: Int, // cada cuantos días hay que regarla
    val fertilize: Int, // cada cuantos días hay que fertilizarla
    val image: String, // imagen de planta genérica
    val type: String // el tipo de planta que es: hortaliza, flor, follaje, especia o cactus
)


