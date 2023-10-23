package com.example.dal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import com.example.dal.data.entities.Proverb
import com.example.dal.ui.theme.DalTheme
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.sp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val vm: DalViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DalTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                        ScreenMain(vm)
                }
            }
        }
    }
}


@Composable
fun ScreenMain(vm: DalViewModel) {
    //val isLoading by vm.isLoading.collectAsState(initial = true)

    var data: List<Proverb> by rememberSaveable {
        mutableStateOf(listOf())
    }

    var filterText by remember { mutableStateOf(TextFieldValue()) }

    data = vm.allProverbs.observeAsState(listOf()).value

//    data = vm.loadData().observeAsState(listOf()).value

    if (data.isEmpty()) {
//      if(true) {
          Column {
              CircularProgressIndicator(
                  modifier = Modifier.width(64.dp),
                  color = MaterialTheme.colorScheme.surfaceVariant,
                  trackColor = MaterialTheme.colorScheme.secondary,
              )
//              LinearProgressIndicator(
//                  color = MaterialTheme.colorScheme.surfaceVariant,
//                  trackColor = MaterialTheme.colorScheme.secondary,
//              )
          }
    } else {
        Column {
            OutlinedTextField(
                value = filterText,
                onValueChange = { filterText = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("filter") },
                singleLine = true,
                textStyle = TextStyle(
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                ),
            )
            LazyColumn(
                Modifier
                    .background(MaterialTheme.colorScheme.surface)
                    .fillMaxWidth()
                    .padding(0.dp)
            ) {

                items(filterData(data, filterText.text)) { item ->
                    Text(text = item.text)
                }
            }
        }
    }
}

fun filterData(data: List<Proverb>, filter: String): List<Proverb> {
    return data.filter { item -> item.text.contains(filter, ignoreCase = true) }
}


//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    DalTheme {
//        ScreenMain(viewModel)
//    }
//}