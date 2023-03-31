package com.itclimb.mylittlelemoncomposablelab

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

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

            Text(text = "Profile information:",
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
                onValueChange = { } )


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
                onValueChange = { })


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
                onValueChange = { })


            Button(onClick = {
                sharedPref.edit().clear().commit();
                navController.navigate(Onboarding.route)
            },
               colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.yellow)),
                modifier = Modifier.padding(horizontal = 10.dp).
                        padding(top=20.dp).clip (RoundedCornerShape(30.dp))

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

@Preview(showBackground = true)
@Composable
fun HeaderProfilePreview() {
    Column {
        HeaderProfile()

    }
}

@Composable
fun HeaderProfile() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        horizontalArrangement = Arrangement.End

    ) {

        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = "My Image",
                modifier = Modifier
                    .size(180.dp)
            )
        }
    }


}

