package com.itclimb.mylittlelemoncomposablelab.screens


import android.content.Context.MODE_PRIVATE
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.itclimb.mylittlelemoncomposablelab.ui.HeaderProfile
import com.itclimb.mylittlelemoncomposablelab.R
import com.itclimb.mylittlelemoncomposablelab.ui.karlaFontFamily
import com.itclimb.mylittlelemoncomposablelab.ui.markaziFontFamily
import com.itclimb.mylittlelemoncomposablelab.navigation.Home


@Composable
fun onBoarding(navController: NavHostController) {
    val sharedPref = navController.context.getSharedPreferences("sharedPref", MODE_PRIVATE)


        var firstName by remember {
            mutableStateOf("")
        }
        var isValidFirstName by remember {
            mutableStateOf(firstName.isNotBlank())
        }


        var lastName by remember {
            mutableStateOf("")
        }
        var isValidLastName by remember {
            mutableStateOf(lastName.isNotBlank())
        }

        var email by remember {
            mutableStateOf("")
        }
        var isValidEmail by remember {
            mutableStateOf(email.isNotBlank())
        }




        Box {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                HeaderProfile();

                Box(
                    modifier = Modifier
                        .background(colorResource(id = R.color.darkGreen))
                        .padding (vertical =30.dp).fillMaxWidth(),
                    contentAlignment = Alignment.Center

                ) {

                    Text(
                        text = "Let's get to know you",
                        color = Color.White,
                        modifier = Modifier.padding(vertical = 20.dp),
                        fontFamily = markaziFontFamily,
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Normal
                    )
                }

                Text(
                    text = "Personal information",
                    fontFamily = karlaFontFamily,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .padding(vertical = 40.dp)

                )

                Text(text = "First name:",
                    fontFamily = karlaFontFamily,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.
                    padding(horizontal = 10.dp).
                    padding(top = 10.dp)
                )
                OutlinedTextField(value = firstName,
                    isError = !isValidFirstName,
                    shape = RoundedCornerShape(15.dp),
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(horizontal = 10.dp),
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
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.
                    padding(horizontal = 10.dp).
                    padding(top = 10.dp)
                )

                OutlinedTextField(value = lastName,
                    shape = RoundedCornerShape(15.dp),
                    isError = !isValidLastName,
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(horizontal = 10.dp),
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
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.
                    padding(horizontal = 10.dp).
                    padding(top = 10.dp))

                OutlinedTextField(value = email,
                    shape = RoundedCornerShape(15.dp),
                    isError = !isValidEmail,
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(horizontal = 10.dp),
                    textStyle = TextStyle(
                        fontFamily = karlaFontFamily,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black
                    ),
                    onValueChange = { emailText ->
                        email = emailText
                    })

                Spacer(modifier = Modifier.weight(1f))

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
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .padding(vertical = 20.dp)
                        .fillMaxWidth()
                        .height(45.dp)
                        .clip(RoundedCornerShape(15.dp))

                ) {
                    Text(text = "Register")
                }

            }
            // Add any other content you want to show on top of the image here
        }



}



