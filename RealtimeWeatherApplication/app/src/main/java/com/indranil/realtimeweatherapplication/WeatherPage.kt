package com.indranil.realtimeweatherapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun WeatherPage(viewModel: WeatherViewModel) {
    val cityState = remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxSize().padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxSize().padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            OutlinedTextField(
                modifier = Modifier.weight(1f),
                value = cityState.value, // Access the value using .value
                onValueChange = { newValue ->
                    cityState.value = newValue // Update the state using .value
                },
                label = {
                    Text(text = "Search for any Location")
                })
            IconButton(onClick = {
                viewModel.getData(cityState.value)
            }) {
                Image(imageVector = Icons.Default.Search,
                    contentDescription = "Search for any Location"
                )
            }
        }
    }
}