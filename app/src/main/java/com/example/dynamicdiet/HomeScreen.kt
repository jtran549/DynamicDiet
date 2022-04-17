package com.example.dynamicdiet

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dynamicdiet.dto.Weight
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

var viewModel = MainViewModel()


@ExperimentalComposeUiApi
@Composable
fun HomeScreen(navController: NavController) {
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
                    InputWeight(viewModel = viewModel, scope, scaffoldState, navController = navController)
                }
//                DisplayWeightEntries(viewModel = viewModel)
//                Spacer(modifier = Modifier.height(15.dp))
//                DetailsNavButton(navController)
            }
        }
    }
}

@ExperimentalComposeUiApi
@Composable
fun InputWeight(viewModel: MainViewModel, scope: CoroutineScope, scaffoldState: ScaffoldState, navController: NavController) {
    var context = LocalContext.current
    val keyboard = LocalSoftwareKeyboardController.current
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
                    keyboard?.hide()
                    scope.launch { scaffoldState.snackbarHostState.showSnackbar(message = "Weight: ${viewModel.weightInput} Date: $datetime") }
                },
                content = { Text(text = stringResource(R.string.submit)) },


            )
            Spacer(modifier = Modifier.height(15.dp))
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    navController.navigate(Screen.Details.route)
                },
                content = { Text(text = "View your weight log") }
            )
        }
    }
}

//@Composable
//fun DisplayWeightEntries(viewModel: MainViewModel) {
//    for (weightEntry in viewModel.weightEntries) {
//        Row {
//            Column (
//                Modifier
//                    .fillMaxWidth()
//                    .padding(30.dp)) {
//                Text("${weightEntry.date}: ")
//                Spacer(modifier = Modifier.height(10.dp))
//                Text("${weightEntry.weight}lbs")
//            }
//        }
//    }
//}

@Composable
fun DetailsNavButton(navController: NavController) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(30.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center)
    {
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                navController.navigate(Screen.Details.route)
            },
            content = { Text(text = "View your weight log", style = MaterialTheme.typography.h1) }
        )
    }
}