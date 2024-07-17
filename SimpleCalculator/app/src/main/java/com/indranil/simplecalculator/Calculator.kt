package com.indranil.simplecalculator

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


fun buttonList() = listOf(
    "C", "(", ")", "/",
    "7", "8", "9", "*",
    "4", "5", "6", "-",
    "1", "2", "3", "+",
    "AC", "0", ".", "="
)
@Composable
fun Calculator(modifier: Modifier = Modifier, viewModel: CalculatorViewModel) {

    var equationText = viewModel.equationText.observeAsState()
    var resultText = viewModel.resultText.observeAsState()
    Box(modifier = modifier) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = equationText.value?:"",
                style = TextStyle(
                    fontSize = 30.sp,
                    textAlign = TextAlign.End
                ),
                maxLines = 5,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = resultText.value?:"",
                style = TextStyle(
                    fontSize = 60.sp,
                    textAlign = TextAlign.End
                ),
                maxLines = 2,
            )

            Spacer(modifier = Modifier.height(10.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(4)
            ) {
                items(buttonList()) {
                    CalculatorButton(btn = it, onClick = {
                        viewModel.onButtonClick(it)
                    }
                    )
                }
            }
        }
    }
}


@Composable
fun CalculatorButton(btn : String, onClick: () -> Unit) {
    Box(modifier = Modifier
        .padding(6.dp)
        .padding(
            bottom = 10.dp
        )) {
        FloatingActionButton(
            onClick = onClick,
            modifier = Modifier.size(75 .dp),
            shape = CircleShape,
            containerColor = getColor(btn),
            contentColor = Color.White
        ) {
            Text(text = btn, fontSize = 20.sp)
        }
    }
}

fun getColor(btn : String) : Color {
    if (btn == "C" || btn == "AC")
        return Color(0xFFf44336)
    if (btn == "(" || btn == ")")
        return Color.Gray
    if (btn == "/" || btn == "*" || btn == "-" || btn == "+")
        return Color(0xFFFF9800)
    return Color(0xFF3493FF)
}