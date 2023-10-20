package com.example.dal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dal.data.entities.Proverb
import com.example.dal.ui.theme.DalTheme
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DalTheme {
                val owner = LocalViewModelStoreOwner.current
                val repo = (application as DalApp).repo
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    owner?.let {
                        val viewModel: DalViewModel = viewModel(
                            it,
                            "CookViewModel",
                            DalViewModelFactory(repo)
                        )
                        ScreenMain(viewModel)
                    }
                }
            }
        }
    }
}


@Composable
fun ScreenMain(vm: DalViewModel) {
    var data: List<Proverb> by rememberSaveable {
        mutableStateOf(listOf())
    }

    var textState by remember { mutableStateOf(TextFieldValue()) }

    data = vm.allProverbs.observeAsState(listOf()).value

    Column {
        Text("ABC-----------------")
        BasicTextField(
            value = textState,
            onValueChange = { textState = it },
            modifier = Modifier.fillMaxWidth()
        )
        LazyColumn(
            Modifier
                .background(MaterialTheme.colorScheme.surface)
                .fillMaxWidth()
                .padding(0.dp)
        ) {

            itemsIndexed(
                items = data,
                itemContent = { idx, item ->
                    Text(
                        text = item.text,
                    )

                }
            )
        }
    }}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    DalTheme {
//        ScreenMain(viewModel)
//    }
//}