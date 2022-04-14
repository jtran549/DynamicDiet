package com.example.dynamicdiet

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Phone
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
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import com.example.dynamicdiet.dto.Weight
import com.example.dynamicdiet.ui.theme.DynamicDietTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : ComponentActivity() {

    var viewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            val weightEntries = ArrayList<Weight>()
            viewModel.weightEntries.add(Weight(weight = 165.0, date = "03-25-2022"))
            DynamicDietTheme {
                val scaffoldState = rememberScaffoldState()
                val scope = rememberCoroutineScope()
                    Row (horizontalArrangement  =  Arrangement.SpaceEvenly){
                        Column {
                            Surface(
                                color = MaterialTheme.colors.background
                            ) {
                                MainMessage(viewModel = viewModel)
                            }
                            Surface() {
                                MainMenuOptions()

                            }
                            Surface(){
                                DisplayWeightEntries(viewModel = viewModel)
                            }
                        }
                    }

            }
        }
    }
}
@Composable
fun MainMessage(viewModel: MainViewModel) {
    Row {
        Column (
            Modifier
                .fillMaxWidth()
                .padding(30.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Bottom) {
            Text("You need to eat ${viewModel.calories} calories today", fontSize = 30.sp)
        }
    }
}

@Composable
fun MainMenuOptions(){
    Row {
        Column (
            Modifier
                .fillMaxWidth()
                .padding(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                Button (
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {},
                    content = {Text("Log entry")}
                )
                Button (
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {},
                    content = {Text("View goals")}
                )
        }
    }
}

@Composable
fun InputWeight(viewModel: MainViewModel, scope:CoroutineScope, scaffoldState: ScaffoldState) {
    var context = LocalContext.current
    val simpleDateFormat = SimpleDateFormat("MM-dd-yyy");
    val dateTime = simpleDateFormat.format(Date())
        Row{
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(30.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = viewModel.weightInput,
                    onValueChange = {viewModel.onValueChange(it)},
                    label = {Text(stringResource(R.string.weight))},
                )
                Spacer(modifier = Modifier.height(15.dp))
                Button (
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        var weight = Weight(weight = viewModel.weightInput.toDouble(), date = dateTime).apply {
                        }
                        viewModel.save(weight = weight)
                        scope.launch { scaffoldState.snackbarHostState.showSnackbar("Weight: ${viewModel.weightInput} Date: $dateTime") }
                    },
                    content = {Text(text = stringResource(R.string.submit))}
                )
            }
        }
}

@Composable
fun DisplayWeightEntries(viewModel: MainViewModel) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(30.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Bottom){
        Text("Progress", fontSize = 20.sp)
        for (weightEntry in viewModel.weightEntries) {
            Row (Modifier.fillMaxWidth()){
                    Text("${weightEntry.date}: ")
                    Spacer(modifier = Modifier.height(10.dp))
                    Text("${weightEntry.weight}lbs")
                Column(
                    Modifier.fillMaxWidth().fillMaxHeight(),
                    horizontalAlignment = Alignment.End
                ) {

                }
            }
        }
    }
}







