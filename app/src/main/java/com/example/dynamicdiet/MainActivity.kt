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
                        .fillMaxSize()
                ) {
                    Column {
                        // A surface container using the 'background' color from the theme
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = MaterialTheme.colors.background
                        ) {
                            DefaultPreview()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(
    name: String = "Justin"
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Welcome, $name",
                style = MaterialTheme.typography.h2
            )

        }
    }
}

@Composable
fun SimpleFilledTextFieldSample() {
    var text by remember { mutableStateOf("Enter your weight") }

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.background)
                .padding(15.dp),
            verticalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                label = { Text("Weight") }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview(){
    DynamicDietTheme{
        Surface(
            color = MaterialTheme.colors.background,
            modifier = Modifier.fillMaxWidth()
        ) {
            Greeting("Justin")
            SimpleFilledTextFieldSample()
        }
    }
}







