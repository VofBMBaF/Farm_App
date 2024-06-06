package com.example.goatdatabase

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Goat::class], version = 1)
abstract class GoatDatabase: RoomDatabase() {
    abstract val dao: GoatDao
}