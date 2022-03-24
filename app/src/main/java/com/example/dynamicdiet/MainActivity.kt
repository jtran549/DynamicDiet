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
import com.example.dynamicdiet.ui.theme.DynamicDietTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DynamicDietTheme {
                Box(
                    modifier = Modifier
                        .background(Color.Blue)
                       // .fillMaxSize()
                ) {
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
            }
        }
    }
}

@Composable
fun InputWeight() {
    var weightInput by remember { mutableStateOf("")}
    var context = LocalContext.current
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
                        Toast.makeText(context, "$weightInput", Toast.LENGTH_LONG).show()
                    },
                    content = {Text(text = stringResource(R.string.submit))}
                )
            }
        }
    }
}







