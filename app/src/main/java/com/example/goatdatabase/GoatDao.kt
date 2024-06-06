package com.example.goatdatabase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface GoatDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGoat(goat: Goat)

    @Delete
    suspend fun deleteGoat(goat: Goat)

    @Query("UPDATE goat SET goatInfo=:newInfo WHERE id=:id")
   suspend fun updateInfoByQuery(id: Int, newInfo: String)

   @Query("""
       SELECT * FROM goat WHERE goatName LIKE :query
   """)
   suspend fun findGoatName(query: String): List<Goat>

    @Query("SELECT * FROM goat ORDER BY goatName ASC")
    fun getGoatsOrderedByName(): Flow<List<Goat>>

    @Query("SELECT * FROM goat ORDER BY goatBreed ASC")
    fun getGoatsOrderedByBreed(): Flow<List<Goat>>

   @Query("SELECT * FROM goat ORDER BY goatGender ASC")
    fun getGoatsOrderedByGender(): Flow<List<Goat>>

    @Query("SELECT * FROM goat ORDER BY goatAge ASC")
    fun getGoatsOrderedByAge(): Flow<List<Goat>>
}