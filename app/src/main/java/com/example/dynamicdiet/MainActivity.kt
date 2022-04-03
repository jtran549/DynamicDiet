package com.example.dynamicdiet

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.dynamicdiet.dto.Weight
import com.example.dynamicdiet.ui.theme.DynamicDietTheme
import java.text.SimpleDateFormat
import java.util.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val weights by viewModel.weights.observeAsState(initial = emptyList())

            val weightEntries = ArrayList<Weight>()

            DynamicDietTheme {
                Box(
                    modifier = Modifier
                        .background(Color.Blue)
                       // .fillMaxSize()
                ) {
                    Row (horizontalArrangement  =  Arrangement.SpaceEvenly){
                        Column {
                            // A surface container using the 'background' color from the theme
                            Surface(
                                //modifier = Modifier.fillMaxSize(),
                                color = MaterialTheme.colors.background
                            ) {
                                inputWeight()
                            }
                        }
                    }
                    Row (horizontalArrangement = Arrangement.SpaceEvenly){
                        displayWeightEntries(weightEntries)
                    }
                }
            }
        }
    }
}

@Composable
fun inputWeight() {
    var weightInput by remember { mutableStateOf("")}
    var context = LocalContext.current
    val simpleDateFormat = SimpleDateFormat("MM-dd-yyy");
    val datetime = simpleDateFormat.format(Date())
    val viewModel = MainViewModel()
    Column {
        Row{
            Column{
                OutlinedTextField(
                    value = weightInput,
                    onValueChange = {weightInput = it},
                    label = {Text(stringResource(R.string.weight))}
                )
            }
            Column{
                Button (
                    onClick = {
                        var weight = Weight(weight = weightInput.toDouble(), date = datetime).apply {
                        }
                        viewModel.save(weight = weight)
                    },
                    content = {Text(text = stringResource(R.string.submit))}
                )
            }
        }
    }
}

@Composable
fun displayWeightEntries(weightEntries : ArrayList<Weight>) {
    for (weightEntry in weightEntries) {
        Row {
            Column{
                Text("${weightEntry.date}: ")
            }
            Column{
                Text("${weightEntry.weight}lbs")
            }
        }
    }
}







