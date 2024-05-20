package com.victor.budgetpilot.ui.theme.screens.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.victor.budgetpilot.data.AuthViewModel
import com.victor.budgetpilot.navigation.ROUTE_LOGIN
import com.victor.budgetpilot.viewmodel.RegisterViewModel

@Composable
fun RegisterScreen(navController:NavHostController) {
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var confirmpassword by remember { mutableStateOf(TextFieldValue("")) }
    var context= LocalContext.current
    val registerViewModel = RegisterViewModel(context)
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally) {

        Text(text = "Register here",
            color = Color.Red,
            fontFamily = FontFamily.Serif,
            fontSize = 30.sp)
        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = email, onValueChange = { email = it },
            label = { Text(text = "Enter Email", color=Color.Red) },

            keyboardOptions = KeyboardOptions . Default . copy (imeAction = ImeAction.Next),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),

            )
        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(value =password , onValueChange = {password=it},
            label = { Text(text = "Enter password", color = Color.Red) },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(value =confirmpassword , onValueChange = {
            confirmpassword=it},
            label = { Text(text = "Enter Confirm Password", color= Color.Red) },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))


        Button(onClick = { val myregister= AuthViewModel(navController,context)
            myregister.signup(email.text.trim(),password.text.trim(),confirmpassword.text.trim()) }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Register ")
            registerViewModel.registerNewUser()
        }
        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            navController.navigate(ROUTE_LOGIN)
        }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Have an Account? Click to Login")
        }

    }



}


@Preview
@Composable
private fun registerprev() {
    RegisterScreen(rememberNavController())

}

