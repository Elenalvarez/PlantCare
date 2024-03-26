package com.yao.plantcare.domain

data class Plant(
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
