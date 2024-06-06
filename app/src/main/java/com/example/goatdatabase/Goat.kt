package com.example.goatdatabase

import androidx.room.Entity
import androidx.room.PrimaryKey

//Entity
//@Fts4(contentEntity = Goat::class)
//data class GoatFTS(
   // @ColumnInfo(name = "rowid")
   // @PrimaryKey val id: Int,
    //val goatName1: String

//)

@Entity
data class Goat(
    val goatName: String,
    val goatAge: String,
    val goatBreed: String,
    val goatInfo: String,
    val goatGender: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)




//data class GoatInfo(val id: Int = 1, val info: String?)


//data class GoatAge(val id: Int = 1, val age: String)