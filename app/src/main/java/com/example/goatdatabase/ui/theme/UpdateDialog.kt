package com.example.goatdatabase.ui.theme

import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.Dialog
import com.example.goatdatabase.Goat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateDialog(goat: Goat, onDismiss: () -> Unit, onUpdate: (Goat) -> Unit) {
    var updatedGoatInfo by remember { mutableStateOf(goat.goatInfo) }
    var updatedGoatAge by remember { mutableStateOf(goat.goatAge) }
    var updatedGoatName by remember {mutableStateOf(goat.goatName)}
    var updatedGoatBreed by remember {mutableStateOf(goat.goatBreed)}
    var updatedGoatGender by remember {mutableStateOf(goat.goatGender)}

    Dialog(onDismissRequest = { onDismiss() }) {

        Card(
            colors = CardDefaults.cardColors(
                containerColor = md_theme_dark_surfaceVariant
            )
        ) {


            TextField(
                value = updatedGoatName,
                onValueChange = { updatedGoatName = it },
                label = { Text("Goat Name", color = Color.Black) },
                placeholder = { Text(goat.goatName)},
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = md_theme_dark_outline,
                    focusedContainerColor = md_theme_dark_outline
                )
            )

            TextField(
                value = updatedGoatAge,
                onValueChange = { updatedGoatAge = it },
                label = { Text("Goat Age", color = Color.Black) },
                placeholder = { Text(goat.goatAge)},
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = md_theme_dark_outline,
                    focusedContainerColor = md_theme_dark_outline
                )
            )

            TextField(
                value = updatedGoatBreed,
                onValueChange = { updatedGoatBreed = it },
                label = { Text("Goat Breed", color = Color.Black) },
                placeholder = { Text(goat.goatBreed)},
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = md_theme_dark_outline,
                    focusedContainerColor = md_theme_dark_outline
                )
            )

            TextField(
                value = updatedGoatInfo,
                onValueChange = { updatedGoatInfo = it },
                label = { Text("Goat Info", color = Color.Black) },
                placeholder = { Text(goat.goatInfo)},
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = md_theme_dark_outline,
                    focusedContainerColor = md_theme_dark_outline
                )
            )

            TextField(
                value = updatedGoatGender,
                onValueChange = { updatedGoatGender = it },
                label = { Text("Goat Gender", color = Color.Black) },
                placeholder = { Text(goat.goatGender)},
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = md_theme_dark_outline,
                    focusedContainerColor = md_theme_dark_outline
                )
            )

            Button(onClick = {
                // Create a new Goat object with the updated info to pass back
                val updatedGoat = goat.copy(goatInfo = updatedGoatInfo, goatGender = updatedGoatGender, goatAge = updatedGoatAge, goatBreed = updatedGoatBreed, goatName = updatedGoatName)
                onUpdate(updatedGoat)
                onDismiss()
            }) {
                Text("Save")
            }
        }



        }


        /**

        Column(Modifier.background(colorResource(R.color.ScaffoldGreen))) {
            TextField(
                value = updatedGoatInfo,
                onValueChange = { updatedGoatInfo = it },
                label = { Text("Goat Info") },
                placeholder = { Text(goat.goatInfo)},
                modifier = Modifier.background(colorResource(R.color.grey))
            )
            // Other TextFields for goatName, goatBreed, etc., as needed
            TextField(value = updatedGoatAge, onValueChange = {updatedGoatAge = it}, label = { Text(
                "Goat Age")}, placeholder = { Text(goat.goatAge)}
            , modifier = Modifier.background(colorResource(R.color.grey)))

                TextField( modifier = Modifier.background(colorResource(R.color.grey)),
                    value = updatedGoatName, onValueChange = {updatedGoatName = it}, label = {Text("Goat Name")}, placeholder = {Text(goat.goatName)} )
                TextField( modifier = Modifier.background(colorResource(R.color.grey)), value = updatedGoatBreed, onValueChange = {updatedGoatBreed = it}, label = {Text("Goat Breed")}, placeholder = {Text(goat.goatBreed)})
            Button(onClick = {
                // Create a new Goat object with the updated info to pass back
                val updatedGoat = goat.copy(goatInfo = updatedGoatInfo, goatGender = updatedGoatInfo, goatAge = updatedGoatAge, goatBreed = updatedGoatBreed, goatName = updatedGoatName)
                onUpdate(updatedGoat)
                onDismiss()
            }) {
                Text("Save")
            }
        }

        **/
    }
