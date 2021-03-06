package com.example.dynamicdiet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.dynamicdiet.dto.Entry
import com.example.dynamicdiet.dto.Goal
import com.example.dynamicdiet.ui.theme.DynamicDietTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : ComponentActivity() {

    var viewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DynamicDietTheme {
                val entries by viewModel.entries.observeAsState(initial = emptyList())

                val scaffoldState = rememberScaffoldState()
                val scope = rememberCoroutineScope()
                    Row (horizontalArrangement  =  Arrangement.SpaceEvenly){
                        Column {
                            Surface(
                                color = MaterialTheme.colors.background
                            ) {
                                MainMessage(viewModel = viewModel)
                            }
                            Surface {
                                MainMenuOptions(viewModel = viewModel)
                            }
                            Surface(){
                                DisplayWeightEntries(viewModel = viewModel, entries)
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
            Text("You need to eat 2000 calories today", fontSize = 30.sp)
        }
    }
}

@Composable
fun MainMenuOptions(viewModel: MainViewModel){
    Row {
        Column (
            Modifier
                .fillMaxWidth()
                .padding(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                NewEntryButton(viewModel = viewModel)
            Spacer(modifier = Modifier.height(10.dp))
                GoalsButton(viewModel = viewModel)
        }
    }
}


@Composable
fun NewEntryButton(viewModel : MainViewModel) {
    val isDialogOpen = remember { mutableStateOf(false)}
        NewEntryDialogue(isDialogOpen, viewModel)
        Button(
            onClick = {
                isDialogOpen.value = true
            },
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            Text(text = "New entry",)
        }
}

@Composable
fun GoalsButton(viewModel: MainViewModel) {
    val isDialogOpen = remember{ mutableStateOf(false)}
        GoalsDialog(isDialogOpen, viewModel)
    Button(
        onClick = {
            isDialogOpen.value = true
        },
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Text(text = "Weight loss goals",)
    }

}

@Composable
fun NewEntryDialogue(isDialogOpen:MutableState<Boolean>, viewModel: MainViewModel){
    val simpleDateFormat = SimpleDateFormat("MM-dd-yyy");
    val dateTime = simpleDateFormat.format(Date())
    if(isDialogOpen.value) {
        Dialog(onDismissRequest = { isDialogOpen.value = false }) {
            Surface(
                modifier = Modifier
                    .width(300.dp)
                    .height(450.dp)
                    .padding(5.dp),
                shape = RoundedCornerShape(5.dp),
                color = Color.White
            ) {
                Column(
                    modifier = Modifier.padding(5.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Spacer(modifier = Modifier.padding(5.dp))

                    Text(
                        text = "New entry",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp
                    )

                    Spacer(modifier = Modifier.padding(10.dp))

                    OutlinedTextField(
                        value = viewModel.weightInput,
                        onValueChange = {viewModel.onWeightValueChange(it) },
                        label = { Text(text = "Weight") },
                        placeholder = { Text(text = "Weight") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(0.8f)
                    )

                    Spacer(modifier = Modifier.padding(10.dp))

                    OutlinedTextField(
                        value = viewModel.caloriesInput,
                        onValueChange = { viewModel.onCalorieValueChange(it)},
                        label = { Text(text = "Calories eaten") },
                        placeholder = { Text(text = "Calories eaten") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(0.8f)
                    )

                    Spacer(modifier = Modifier.padding(15.dp))

                    Button(
                        onClick = {
                            isDialogOpen.value = false
                            var weight = Entry(weight = viewModel.weightInput.toDouble(), calories = viewModel.caloriesInput.toDouble(),date = dateTime).apply {
                            }
                            viewModel.saveEntry(entry = weight)
                        },
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .height(60.dp)
                            .padding(10.dp),
                        shape = RoundedCornerShape(5.dp),
                    ) {
                        Text(
                            text = "Save",
                            color = Color.White,
                            fontSize = 12.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun GoalsDialog(isDialogOpen:MutableState<Boolean>, viewModel: MainViewModel){
    val simpleDateFormat = SimpleDateFormat("MM-dd-yyy");
    val dateTime = simpleDateFormat.format(Date())
    if(isDialogOpen.value) {
        Dialog(onDismissRequest = { isDialogOpen.value = false }) {
            Surface(
                modifier = Modifier
                    .width(300.dp)
                    .height(450.dp)
                    .padding(5.dp),
                shape = RoundedCornerShape(5.dp),
                color = Color.White
            ) {
                Column(
                    modifier = Modifier.padding(5.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Spacer(modifier = Modifier.padding(5.dp))

                    Text(
                        text = "Weight loss goals",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp
                    )

                    Spacer(modifier = Modifier.padding(10.dp))

                    OutlinedTextField(
                        value = viewModel.goalWeightInput,
                        onValueChange = {viewModel.onGoalWeightValueChange(it) },
                        label = { Text(text = "Goal weight") },
                        placeholder = { Text(text = "Goal weight") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(0.8f)
                    )

                    Spacer(modifier = Modifier.padding(10.dp))

                    OutlinedTextField(
                        value = viewModel.ratePerWeekInput,
                        onValueChange = { viewModel.onRatePerWeekValueChange(it)},
                        label = { Text(text = "Rate of weight loss/week") },
                        placeholder = { Text(text = "Rate of weight loss/week") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(0.8f)
                    )

                    Spacer(modifier = Modifier.padding(15.dp))

                    Button(
                        onClick = {
                            isDialogOpen.value = false
                            var goal = Goal(goalWeight = viewModel.goalWeightInput.toDouble(), weightLossRate = viewModel.ratePerWeekInput.toDouble()).apply {
                            }
                            viewModel.saveGoal(goal = goal)
                        },
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .height(60.dp)
                            .padding(10.dp),
                        shape = RoundedCornerShape(5.dp),
                    ) {
                        Text(
                            text = "Save",
                            color = Color.White,
                            fontSize = 12.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun DisplayWeightEntries(viewModel: MainViewModel, entries: List<Entry>) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(30.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Bottom){
        Text("Progress", fontSize = 20.sp)
        for (weightEntry in entries) {
                Text("${weightEntry.date}: ")
                Spacer(modifier = Modifier.height(10.dp))
                Text("${weightEntry.weight}lbs")
            }
    }
}




