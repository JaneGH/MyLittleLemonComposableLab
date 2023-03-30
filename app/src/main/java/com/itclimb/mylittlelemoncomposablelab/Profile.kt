package com.itclimb.mylittlelemoncomposablelab

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
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

            Text(text = "Profile information:")
            Text(text = "First name:")
            TextField(value = firstName,  onValueChange = { } )
            Text(text = "Last name:")
            TextField(value = lastName,  onValueChange = { } )
            Text(text = "email:")
            TextField(value = email,  onValueChange = { } )

            Button(onClick = {
                sharedPref.edit().clear().commit();
                navController.navigate(Onboarding.route)
            },
               colors = ButtonDefaults.buttonColors(backgroundColor = Color.Yellow)

            ) {
                Text(text = "Log out")

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

    Image(
        painter = painterResource(R.drawable.logo),
        contentDescription = "My Image",
        modifier = Modifier.fillMaxWidth()
    )

}

