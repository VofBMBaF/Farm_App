package com.example.goatdatabase

sealed interface GoatEvent {
    object SaveGoat: GoatEvent
    data class SetGoatName (val goatName: String): GoatEvent
    data class SetGoatBreed (val goatBreed: String): GoatEvent
    data class SetGoatAge (val goatAge: String): GoatEvent
    data class SetGoatInfo(val goatInfo: String): GoatEvent
    data class SetGoatGender(val goatGender: String): GoatEvent
    object ShowDialog: GoatEvent
    object ShowNewDialog: GoatEvent
    object HideNewDialog: GoatEvent
    object HideDialog: GoatEvent
    data class DeleteGoat (val goat: Goat): GoatEvent
    data class SortGoats(val sortType: SortType):GoatEvent
 // data class UpdateGoatInfo(val goatInfo: String, val goatId: Int): GoatEvent
    //data class UpdateGoatInfo(val goat: Goat, val goatId: Int): GoatEvent
}