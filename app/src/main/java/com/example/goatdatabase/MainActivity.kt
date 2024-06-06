package com.example.goatdatabase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.goatdatabase.ui.theme.GoatDatabaseTheme
import com.example.goatdatabase.ui.theme.GoatScreen

class MainActivity : ComponentActivity() {
    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            GoatDatabase::class.java,
            "Goats.db"
        ).build()

    }
    private val viewModel by viewModels<GoatViewModel>(
        factoryProducer = {
        object : ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return GoatViewModel(db.dao) as T
            }
        }
    })
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GoatDatabaseTheme {
                val state by viewModel.state.collectAsState()


                GoatScreen(state = state, onEvent = viewModel::onEvent, viewModel = viewModel)
            }
        }
    }
}


