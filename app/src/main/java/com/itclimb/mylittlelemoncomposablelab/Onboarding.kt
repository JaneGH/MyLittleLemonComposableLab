package com.itclimb.mylittlelemoncomposablelab


import android.content.Context.MODE_PRIVATE
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController


@Composable
fun onBoarding(navController: NavHostController) {
    val sharedPref = navController.context.getSharedPreferences("sharedPref", MODE_PRIVATE)


        var firstName by remember {
            mutableStateOf("")
        }

        var lastName by remember {
            mutableStateOf("")
        }

        var email by remember {
            mutableStateOf("")
        }



        Box {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Image(
                    painter = painterResource(R.drawable.logo),
                    contentDescription = "My Image",
                    modifier = Modifier.fillMaxWidth()
                )

                Text(
                    text = "Let's get to know you",
                    fontSize = 30.sp

                )
                TextField(value = firstName,
                    onValueChange = { firstNameText ->
                        firstName = firstNameText
                    })

                TextField(value = lastName,
                    onValueChange = { lastNameText ->
                        lastName = lastNameText
                    })

                TextField(value = email,
                    onValueChange = { emailText ->
                        email = emailText
                    })

                Button(
                    onClick = {
                        if (firstName.isBlank() || lastName.isBlank() || email.isBlank()) {
                            val toast = Toast.makeText(
                                navController.context,
                                "Registration unsuccessful. Please enter all data.",
                                Toast.LENGTH_SHORT
                            )
                            toast.show()
                        } else {
                            var editor = sharedPref.edit()
                            editor.putString("firstName", firstName)
                            editor.putString("lastName", lastName)
                            editor.putString("email", email)
                            editor.commit()
                            navController.navigate(Home.route)
                        }

                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Yellow)
                ) {
                    Text(text = "Register")
                }

            }
            // Add any other content you want to show on top of the image here
        }



}



