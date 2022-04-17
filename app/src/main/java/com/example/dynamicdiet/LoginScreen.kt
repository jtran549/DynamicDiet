package com.example.dynamicdiet

import android.view.Surface
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.dynamicdiet.ui.theme.DynamicDietTheme
import com.example.dynamicdiet.ui.theme.Purple200
import com.example.dynamicdiet.ui.theme.Purple500

@Composable
fun LoginScreen(navController: NavController) {
    DynamicDietTheme() {
        Row(horizontalArrangement  =  Arrangement.SpaceEvenly) {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(30.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
//                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier.padding(15.dp),
                    text = "Dynamic Diet",
                    style = MaterialTheme.typography.h3,
                    color = Purple500
                )
                LoginInput(navController = navController)
            }
        }
    }
}


@Composable
fun LoginInput(navController: NavController) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val emailValue = remember {mutableStateOf(TextFieldValue())}
        val passwordValue = remember {mutableStateOf(TextFieldValue())}

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = emailValue.value,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            onValueChange = {emailValue.value = it},
            label = { Text("Email") },
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = passwordValue.value,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            onValueChange = {passwordValue.value = it},
            label = { Text("Password") },
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                navController.navigate(Screen.Home.route)
            },
            content = { Text(text = "Login") }
        )
//        Text(
//            modifier = Modifier.clickable { navController.navigate(Screen.Home.route) },
//            text = "Login (tap here)",
//            style = MaterialTheme.typography.h4
//        )
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen(navController = rememberNavController())
}