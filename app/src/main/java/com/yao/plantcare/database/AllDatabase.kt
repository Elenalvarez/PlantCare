package com.yao.plantcare.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.yao.plantcare.database.MyPlants.MyPlantEntity
import com.yao.plantcare.database.Plant.PlantEntity

@Database(entities = [PlantEntity::class, MyPlantEntity::class], version = 1)
abstract class AllDatabase : RoomDatabase() {

    abstract fun AllDao(): AllDao

    companion object{
        @Volatile
        private var INSTANCE: AllDatabase? = null

        fun getDatabase(context: Context): AllDatabase{
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AllDatabase::class.java,
                    "database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}