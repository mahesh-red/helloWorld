package com.mahesh.helloworld

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mahesh.helloworld.components.CalculateNumbers
import com.mahesh.helloworld.components.CalculationScren
import com.mahesh.helloworld.components.DetailsViewModel
import com.mahesh.helloworld.components.MyDetailsScreen
import com.mahesh.helloworld.components.myViewModel
import com.mahesh.helloworld.navigation.Navigation
import com.mahesh.helloworld.ui.theme.HelloWorldTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HelloWorldTheme {

                val viewmodel by viewModels<myViewModel> ()
                val detailsViewModel by viewModels<DetailsViewModel>()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//MyDetailsScreen()
                    MyDetailsScreen(detailsViewModel = detailsViewModel,modifier=Modifier.padding(16.dp))

//                    CalculateNumbers(modifier=Modifier.padding(innerPadding))
//                    CalculationScren(modifier = Modifier.padding(innerPadding), viewModel =viewmodel )


                }
            }
        }
    }
}




