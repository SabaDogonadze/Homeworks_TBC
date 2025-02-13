package com.example.tbchomework18.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UsersEntity::class], version = 1, exportSchema = false)
abstract class UserDataBase: RoomDatabase() {
    abstract fun userDao():UserDao

}

// inside in abstract class
/* companion object{
     @Volatile
     private var INSTANCE:UserDataBase? = null

     fun getDatabase(context: Context):UserDataBase{
         val tempInstance = INSTANCE
         if(tempInstance != null){
             return tempInstance
         }
         synchronized(this){
             val instance = Room.databaseBuilder(
                 context.applicationContext,
                 UserDataBase::class.java,
                 "user_database"
             ).build()
             INSTANCE = instance
             return instance
         }
     }
 }*/