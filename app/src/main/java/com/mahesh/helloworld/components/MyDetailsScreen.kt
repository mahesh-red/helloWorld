package com.mahesh.helloworld.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahesh.helloworld.database.mEntity
import com.mahesh.helloworld.database.mRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

//@Preview
@Composable
fun MyDetailsScreen(detailsViewModel: DetailsViewModel,modifier: Modifier){

    val listofDetails by detailsViewModel.detailsList.collectAsState()

    Column(modifier = Modifier.padding(16.dp),
verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {


        TextField(value = detailsViewModel.name.value, onValueChange ={detailsViewModel.name.value=it} )
        Spacer(modifier = Modifier.height(16.dp))

        TextField(value =detailsViewModel.marks.value, onValueChange ={detailsViewModel.marks.value=it} )
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            detailsViewModel.insertDetail()
        }) {
            Text("save")
        }

        Spacer(modifier = Modifier.height(16.dp))

        listofDetails.forEach{oneDetail->
            Row {
                Text(text = oneDetail.personName)
                Text(text = oneDetail.marks)
            }

        }


    }

}

@HiltViewModel
class DetailsViewModel @Inject constructor(val mRepo: mRepo) :ViewModel (){

    var name = mutableStateOf("")
    var marks= mutableStateOf("")
    var _detailsList = MutableStateFlow<List<mEntity>>(emptyList())
    var detailsList = _detailsList.asStateFlow()

    var singleDetail = mutableStateOf(mEntity( personName ="",marks="" ))
    init {
        viewModelScope.launch(Dispatchers.IO) {
           mRepo.getDetails().collect{allDetailsList->
               _detailsList.value=allDetailsList
           }
        }
    }

 fun insertDetail()=viewModelScope.launch {  mRepo.insertDetail(mEntity(personName = name.value, marks = marks.value)) }


    fun deleteDetail(mEntity: mEntity)=mRepo.deleteDetail(mEntity)

}
