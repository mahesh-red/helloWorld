package com.mahesh.helloworld.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController

class myViewModel:ViewModel() {
    var firstN = mutableStateOf("")
    var secondN = mutableStateOf("")


    var sum = mutableStateOf(0)
    var difference= mutableStateOf(0)
    fun logic(){

        val first= firstN.value.toIntOrNull()?:0
        val second=secondN.value.toIntOrNull()?:0

        sum.value = first+second
        difference.value = first - second
    }




}



@Composable
fun SimpleCalcu(text:String,
                firstvalue: String,
                mahesh: (String)->Unit){
    Text(text)
    TextField(value =firstvalue , onValueChange =mahesh )

}

@Composable
fun CalculationScren(modifier: Modifier, viewModel: myViewModel,navController: NavController) {
//    var firstNumber by remember { mutableStateOf("") }
//    var secondNumber by remember { mutableStateOf("") }
    val firstNumber by viewModel.firstN
    val secondNumber by viewModel.secondN
//    var sum by remember { mutableStateOf(0) }
//    var difference by remember { mutableStateOf(0) }
    var product by remember { mutableStateOf(0) }
    var quotient by remember { mutableStateOf(0f) }

//    val logic = viewModel.logic()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        SimpleCalcu(text = "Enter First number", firstvalue = firstNumber) {
            viewModel.firstN.value=it
        }
        Spacer(modifier = Modifier.height(8.dp))
        // Second Number Input
        SimpleCalcu(text = "Enter Second Number", firstvalue =secondNumber ) {
            viewModel.secondN.value=it
        }
        Spacer(modifier = Modifier.height(16.dp))
        // Calculate Button
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = {
                viewModel.logic()

                val first = firstNumber.toIntOrNull() ?: 0
                val second = secondNumber.toIntOrNull() ?: 0

//                sum = first + second
//                difference = first - second
                product = first * second
                quotient = if (second != 0) first.toFloat() / second else 0f
            }) {
                Text("Calculate")
            }
        }
        val name="mahesh"
        Button(onClick = { navController.navigate("details/$name") }) {
            Text(text = "route")
        }
        Spacer(modifier = Modifier.height(16.dp))
        // Results
        Text(text = "Sum of Numbers: ${viewModel.sum.value}")
        Text(text = "Subtraction of Numbers: ${viewModel.difference.value}")
        Text(text = "Multiplication of Numbers: $product")
        Text(text = "Division of Numbers: $quotient")
    }
}


@Composable
fun NameScreen(name:String){
    Text(name)
}
