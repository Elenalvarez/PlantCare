package com.yao.plantcare.database.MyPlants

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MyPlantEntity::class], version = 2)
abstract class MyPlantDatabase: RoomDatabase() {
    
    abstract fun myPlantDao(): MyPlantDao

    companion object{
        @Volatile
        private var INSTANCE: MyPlantDatabase? = null

        fun getDatabase(context: Context): MyPlantDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyPlantDatabase::class.java,
                    "my_plant_database_2"
                ).build()
                MyPlantDatabase.INSTANCE = instance
                return instance
            }
        }
    }
}