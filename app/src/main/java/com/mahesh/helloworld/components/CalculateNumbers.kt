package com.mahesh.helloworld.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp


@Composable
fun CalculateNumbers(modifier: Modifier){
    Column(modifier = Modifier.padding(16.dp)) {

        Text(text = "Calculation of numbers")
        Spacer(modifier = Modifier.padding(16.dp))
        Text(text = "Enter first number")
        TextField(value = "", onValueChange ={} )
        Spacer(modifier = Modifier.padding(16.dp))
        Text(text = "Enter first number")
        TextField(value = "", onValueChange ={} )

        Button(onClick = { /*TODO*/ }) {
            Text(text ="Calculate" )

        }

        Text(text = "sum of numbers")
        Text(text = "multiplication of numbers")
        Text(text = "division of numbers")
        Text(text = "subtraction of numbers")
    }

}
