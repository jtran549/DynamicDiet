package com.example.dynamicdiet

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dynamicdiet.dto.Weight
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

var viewModel = MainViewModel()


@Composable
fun HomeScreen() {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    Row (horizontalArrangement  =  Arrangement.SpaceEvenly){
        Column {
            // A surface container using the 'background' color from the theme
            Surface(
                //modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background
            ) {
                Scaffold(scaffoldState = scaffoldState) {
                    InputWeight(viewModel = viewModel, scope, scaffoldState)
                }
                DisplayWeightEntries(viewModel = viewModel)
            }
        }
    }
}

@Composable
fun InputWeight(viewModel: MainViewModel, scope: CoroutineScope, scaffoldState: ScaffoldState) {
    var context = LocalContext.current
    val simpleDateFormat = SimpleDateFormat("MM-dd-yyy");
    val datetime = simpleDateFormat.format(Date())
    Row{
        Column(
            Modifier
                .fillMaxSize()
                .padding(30.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = viewModel.weightInput,
                onValueChange = {viewModel.onValueChange(it)},
                label = { Text(stringResource(R.string.weight)) },
            )
            Spacer(modifier = Modifier.height(15.dp))
            Button (
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    var weight = Weight(weight = viewModel.weightInput.toDouble(), date = datetime).apply {
                    }
                    viewModel.save(weight = weight)
                    scope.launch { scaffoldState.snackbarHostState.showSnackbar("Weight: ${viewModel.weightInput} Date: $datetime") }
                },
                content = { Text(text = stringResource(R.string.submit)) }
            )
        }
    }
}

@Composable
fun DisplayWeightEntries(viewModel: MainViewModel) {
    for (weightEntry in viewModel.weightEntries) {
        Row {
            Column (
                Modifier
                    .fillMaxWidth()
                    .padding(30.dp)) {
                Text("${weightEntry.date}: ")
                Spacer(modifier = Modifier.height(10.dp))
                Text("${weightEntry.weight}lbs")
            }
        }
    }
}

