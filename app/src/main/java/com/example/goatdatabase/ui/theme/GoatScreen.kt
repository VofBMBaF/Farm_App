package com.example.goatdatabase.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.goatdatabase.AddGoatDialog
import com.example.goatdatabase.GoatEvent
import com.example.goatdatabase.GoatState
import com.example.goatdatabase.GoatViewModel
import com.example.goatdatabase.R
import com.example.goatdatabase.SortType


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GoatScreen(state: GoatState, onEvent: (GoatEvent) -> Unit, viewModel: GoatViewModel) {

    val aladin = FontFamily(
        Font(R.font.alegreyaa, FontWeight.Black, FontStyle.Normal)
    )

    val monserratbold = FontFamily(
        Font(R.font.montserrat_bold, FontWeight.Black, FontStyle.Normal)
    )

    val abril = FontFamily(
        Font(R.font.abril_fatface, FontWeight.Black, FontStyle.Normal)
    )
   // val monserrat = FontFamily(
      //  Font(R.font.montserrat)
   // )
    var showDropDownMenu by remember { mutableStateOf(false) }

    Scaffold(
        containerColor = md_theme_dark_surfaceVariant,
        topBar = { TopAppBar(
            navigationIcon = { Text(text = "${state.goats.size}", color = md_theme_dark_onSurface, fontFamily = abril,
                modifier = Modifier
                    .background(
                        color = md_theme_dark_surfaceVariant,
                        shape = RoundedCornerShape(0.dp, 15.dp, 15.dp, 0.dp)
                    )
                    .padding(30.dp)
            )},
            title = { Text(text = "Goats", fontFamily = abril,  color =  md_theme_dark_onSurface, fontSize = 36.sp, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())},
            actions = {

                IconButton(
                    onClick = { showDropDownMenu = true },
                    modifier = Modifier
                        .background(
                            color = md_theme_dark_surfaceVariant,
                            shape = RoundedCornerShape(5.dp, 0.dp, 5.dp, 5.dp)
                        )
                        .padding(10.dp)
                    ) {
                    Icon(Icons.Filled.Menu, null, tint = md_theme_dark_onSurface) }

                   DropdownMenu(
                        showDropDownMenu, { showDropDownMenu = false },
                       Modifier.background(color = md_theme_dark_outline)


                    ) {
                       Column(

                       ) {
                           SortType.entries.forEach { sortType ->
                               Row(modifier = Modifier
                                   .fillMaxWidth()
                                   .clip(RoundedCornerShape(12.dp))
                                   .padding(10.dp)
                                   .background(
                                       color = md_theme_dark_surfaceVariant,
                                       RoundedCornerShape(12.dp)
                                   )

                                   .clickable { onEvent(GoatEvent.SortGoats(sortType)) },
                                  // verticalAlignment = Alignment.CenterVertically
                                   )
                                   {
                                   RadioButton(
                                       selected = state.sortType == sortType,
                                       onClick = { onEvent(GoatEvent.SortGoats(sortType)) },
                                       colors = RadioButtonDefaults.colors(
                                           selectedColor = md_theme_dark_primary,
                                           unselectedColor = md_theme_dark_outline
                                       )
                                   )
                                   Text(

                                       text = sortType.name,
                                       color = md_theme_light_secondaryContainer,
                                       modifier = Modifier.padding(2.dp)

                                   )
                               }

                           }



                       }
                    }
                },


            colors = TopAppBarDefaults.largeTopAppBarColors(
                md_theme_dark_surfaceVariant), modifier = Modifier
                .padding(0.dp, 0.dp, 0.dp, 12.dp)
                .shadow(elevation = 12.dp)

        )

              /* Text(text = "${state.goats.size}", textAlign = TextAlign.Center, modifier = Modifier
                   .fillMaxWidth()
                   .background(color = Color.White)
                   .clip(
                       RoundedCornerShape(12.dp)
                   ))*/

                 },
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.shadow(elevation = 20.dp, shape = RoundedCornerShape(10.dp)),
                onClick = { onEvent(GoatEvent.ShowDialog) },
                containerColor = md_theme_dark_tertiaryContainer,


            ) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        },

    ) {padding ->



        if(state.isAddingGoat) {
            AddGoatDialog(state = state, onEvent = onEvent, modifier = Modifier )
        }

        val selectedGoat by viewModel.selectedGoat.collectAsState()


        // When a goat is selected, show the UpdateDialog
        selectedGoat?.let { goat ->
            UpdateDialog(
                goat = goat,
                onDismiss = { viewModel.selectGoat(null) }, // Clear the selection on dismiss
                onUpdate = { updatedGoat ->
                    viewModel.updateGoat(updatedGoat) // Implement this in ViewModel
                }
            )
        }




        LazyColumn(modifier = Modifier
            .padding(padding)
            .background(color = md_theme_dark_outline, RoundedCornerShape(40.dp))
            .fillMaxHeight(),
            verticalArrangement = Arrangement.spacedBy(8.dp)


        ) {


            items(state.goats) {goat ->
                Row(modifier = Modifier
                    .padding(15.dp)
                    .clickable {
                        viewModel.selectGoat(goat)
                    }

                    .fillMaxWidth()
                    .background(
                        color = md_theme_dark_secondaryContainer,
                        shape = RoundedCornerShape(bottomStart = 16.dp, topEnd = 16.dp)
                    )
                    .padding(10.dp)

                )
                {



                    Column {
                        Text(text = goat.goatName, fontSize = 20.sp, fontFamily = monserratbold, color = md_theme_dark_onSecondaryContainer
                        )
                    
                        Text(text = "${goat.goatBreed}  ${goat.goatAge} ${goat.goatGender}", fontSize = 14.sp, fontFamily = monserratbold, color = md_theme_dark_onSecondaryContainer)
                    }

                    Spacer(modifier = Modifier.weight(1f))
                    
                    IconButton(onClick = { 
                        onEvent(GoatEvent.DeleteGoat(goat)) }) {
                        Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete Goat")
                    }

                  //  Button(onClick = { viewModel.selectGoat(goat) }) {
                     //   Text("update")
                    //}


                }




            }
        }



    }
}