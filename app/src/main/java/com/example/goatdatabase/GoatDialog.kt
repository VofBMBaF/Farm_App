package com.example.goatdatabase

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddGoatDialog(state: GoatState, onEvent: (GoatEvent) -> Unit, modifier: Modifier) {
    Dialog(onDismissRequest = {onEvent(GoatEvent.HideDialog)}) {
        Column {


            TextField(value = state.goatName, onValueChange = {onEvent(GoatEvent.SetGoatName(it))},
                placeholder = { Text(text = "Goat Name")})
            TextField(value = state.goatAge, onValueChange = {onEvent(GoatEvent.SetGoatAge(it))},
                placeholder = { Text(text = "Goat Age")})
            TextField(value = state.goatBreed, onValueChange = {onEvent(GoatEvent.SetGoatBreed(it))},
                placeholder = { Text(text = "Goat Breed")})
            TextField(value = state.goatInfo, onValueChange = {onEvent(GoatEvent.SetGoatInfo(it))},
                placeholder = { Text(text = "Goat Info")})
            TextField(value = state.goatGender, onValueChange = {onEvent(GoatEvent.SetGoatGender(it))},
                placeholder = { Text(text = "Goat Gender")})
            Button(onClick = { onEvent(GoatEvent.SaveGoat) }) {
                Text(text = "Save")

            }



        }
    }
}