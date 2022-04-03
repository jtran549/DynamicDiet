package com.example.dynamicdiet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.example.dynamicdiet.dto.Entries
import com.example.dynamicdiet.ui.theme.DynamicDietTheme
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : ComponentActivity() {

    val viewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val weightEntries = ArrayList<Entries>()
            weightEntries.add(Entries(weightLbs = 165.0, date = "03-25-2022"))
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
                                InputWeight()
                            }
                        }
                    }
                    Row (horizontalArrangement = Arrangement.SpaceEvenly){
                        DisplayWeightEntries(weightEntries)
                    }
                }
            }
        }
    }
}

@Composable
fun InputWeight() {
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
                        var weight = Entries(weightLbs = weightInput.toDouble(), date = datetime).apply {
                        }
                        viewModel.save(entries = weight)
                    },
                    content = {Text(text = stringResource(R.string.submit))}
                )
            }
        }
    }
}

@Composable
fun DisplayWeightEntries(entriesEntries : ArrayList<Entries>) {
    for (weightEntry in entriesEntries) {
        Row {
            Column{
                Text("${weightEntry.date}: ")
            }
            Column{
                Text("${weightEntry.weightLbs}lbs")
            }
        }
    }
}







