package com.itclimb.mylittlelemoncomposablelab


import android.content.Context.MODE_PRIVATE
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
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
                HeaderProfile();

                Text(
                    text = "Let's get to know you",
                    fontFamily = karlaFontFamily,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .padding(vertical = 10.dp)

                )

                Text(text = "First name:",
                    fontFamily = karlaFontFamily,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier.
                    padding(horizontal = 10.dp).
                    padding(top = 10.dp)
                )
                TextField(value = firstName,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(colorResource(id = R.color.lightGrey)).
                        padding(horizontal = 10.dp),
                    textStyle = TextStyle(
                        fontFamily = karlaFontFamily,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black
                    ),
                    onValueChange = { firstNameText ->
                        firstName = firstNameText
                    })


                Text(text = "Last name:",
                    fontFamily = karlaFontFamily,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier.
                    padding(horizontal = 10.dp).
                    padding(top = 10.dp)
                )

                TextField(value = lastName,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(colorResource(id = R.color.lightGrey)).
                        padding(horizontal = 10.dp),
                    textStyle = TextStyle(
                        fontFamily = karlaFontFamily,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black
                    ),
                    onValueChange = { lastNameText ->
                        lastName = lastNameText
                    })


                Text(text = "email:",
                    fontFamily = karlaFontFamily,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier.
                    padding(horizontal = 10.dp).
                    padding(top = 10.dp))

                TextField(value = email,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(colorResource(id = R.color.lightGrey)).
                        padding(horizontal = 10.dp),
                    textStyle = TextStyle(
                        fontFamily = karlaFontFamily,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black
                    ),
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
                    colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.yellow)),
                    modifier = Modifier.padding(horizontal = 10.dp).
                    padding(top=20.dp).clip (RoundedCornerShape(30.dp))

                ) {
                    Text(text = "Register")
                }

            }
            // Add any other content you want to show on top of the image here
        }



}



