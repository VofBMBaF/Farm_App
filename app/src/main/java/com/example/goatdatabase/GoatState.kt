package com.example.goatdatabase

data class GoatState(
    val goats: List<Goat> = emptyList(),
    val goatName: String = "",
    val goatAge: String = "",
    val goatBreed: String = "",
    var goatInfo: String = "",
    val goatGender: String = "",
    val goatId: Int = 1,
    val sortType: SortType = SortType.NAME,
    val isAddingGoat: Boolean = false,
    var isUpdatingGoat: Boolean = false,
)