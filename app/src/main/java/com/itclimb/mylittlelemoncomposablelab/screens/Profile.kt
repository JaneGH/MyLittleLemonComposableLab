package com.itclimb.mylittlelemoncomposablelab.screens

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
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
import com.itclimb.mylittlelemoncomposablelab.navigation.Onboarding

@Composable
fun onProfile(navController: NavHostController) {

    val sharedPref = navController.context.getSharedPreferences("sharedPref", Context.MODE_PRIVATE)


    Box {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            HeaderProfile()

            var firstName = sharedPref.getString("firstName","")
            if (firstName==null){
               firstName="";
            };
            var lastName = sharedPref.getString("lastName","");
            if (lastName==null){
                lastName="";
            };
            var email = sharedPref.getString("email","");
            if (email==null){
                email="";
            };

            Text(text = "Personal information",
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
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .padding(top = 10.dp)
            )

            OutlinedTextField(value = firstName,
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
                onValueChange = { } )


            Text(text = "Last name:",
                fontFamily = karlaFontFamily,
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .padding(top = 10.dp)
            )

            OutlinedTextField(value = lastName,
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
                onValueChange = { })


            Text(text = "email:",
                fontFamily = karlaFontFamily,
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .padding(top = 10.dp))


            OutlinedTextField(value = email,
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
                onValueChange = { })

                Spacer(modifier = Modifier.weight(1f))

                Button(onClick = {
                    sharedPref.edit().clear().commit();
                    navController.navigate(Onboarding.route)
                },
                   colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.yellow)),
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .padding(vertical = 20.dp)
                        .fillMaxWidth()
                        .height(45.dp)
                        .clip(RoundedCornerShape(15.dp))



                ) {
                    Text(text = "Log out",
                        fontFamily = karlaFontFamily,
                        fontWeight = FontWeight.ExtraBold,
                        color = colorResource(id = R.color.darkGreen)
                    )

                }



        }
    }
}




