@file:OptIn(ExperimentalGlideComposeApi::class)

package com.itclimb.mylittlelemoncomposablelab

import android.content.Context
import android.view.MenuItem
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

import com.itclimb.mylittlelemoncomposablelab.ui.theme.MyLittleLemonComposableLabTheme


@Composable
fun onHome(navController: NavHostController, context: Context) {
    Column() {
        Header(navController)
        MainPanelHome()
//        Button(onClick = {
//            navController.navigate(Profile.route)
//        },
//            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Yellow)
//
//        ) {
//            Text(text = "Profile")
//
//        }

        setDatabaseList(context)
    }


}



@Composable
fun setDatabaseList( context: Context){

    val viewModel: HomeViewModel = HomeViewModel(context)

    val databaseMenuItems by viewModel.getMenuItems ().observeAsState(emptyList())

    MyLittleLemonComposableLabTheme {


        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {

            var orderMenuItems by remember { mutableStateOf(false) }
            var searchPhrase by remember { mutableStateOf("") }

            var menuItems = if ((orderMenuItems) && (searchPhrase.isNotEmpty())) {
                databaseMenuItems.sortedBy { it.title }.filter {
                    it.title.contains(searchPhrase, ignoreCase = true)
                }
            }  else if (orderMenuItems) {
                databaseMenuItems.sortedBy { it.title }
            }  else if (searchPhrase.isNotEmpty()) {
                databaseMenuItems.sortedBy { it.title }.filter {
                    it.title.contains(searchPhrase, ignoreCase = true)}
            } else {
                databaseMenuItems
            }


            if (searchPhrase.isNotEmpty()) {
                menuItems.filter {
                    it.title.contains(searchPhrase, ignoreCase = true)
                }
            }

            // add Button code here
            Column(
                modifier = Modifier. fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(onClick = {
                    orderMenuItems = true;
                    databaseMenuItems.sortedBy { it.title }
                }) {
                    Text("Tap to Order By Name")
                }


                OutlinedTextField(
                    value = searchPhrase,
                    singleLine = true,
                    onValueChange = {
                        searchPhrase = it
                    },
                    label = { Text("Search") },
                    modifier = Modifier.padding(start = 50.dp, end = 50.dp)
                )


            }


            MenuItemsList(menuItems)
        }
    }
}


@Composable
private fun MenuItemsList(items: List<MenuItemRoom>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .padding(top = 20.dp)
    ) {
        items(
            items = items,
            itemContent = { menuItem ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Column() {
                        Text(menuItem.title)
                        Row() {


                            Column(modifier = Modifier.fillMaxWidth(0.7f)) {
                                Text(menuItem.description)
                                Text(
    //                            modifier = Modifier
    //                                .weight(1f)
    //                                .padding(5.dp),
    //                            textAlign = TextAlign.Right,
                                    text = "$%.2f".format(menuItem.price)
                                )
                            }

                               GlideImage(
                                    model = menuItem.image,
                                    contentScale = ContentScale.Crop,
                                    contentDescription = "dish image for " + menuItem.title,
                                    modifier = Modifier.size(100.dp,100.dp)
                                        .padding(horizontal = 5.dp)
                               )


                        }

                    }
                }
                Divider(modifier = Modifier.padding(vertical = 10.dp))
            }
        )
    }
}

@Composable
fun Header(navController: NavHostController) {

    TopAppBar(

        title = { Text(text = "") },
        backgroundColor = Color.White,
        actions = {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                horizontalArrangement = Arrangement.End

            ) {
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "Little Lemon Logo",
                        modifier = Modifier
                            .size(150.dp)
                    )
                }

                Box(
                    modifier = Modifier.padding(end = 16.dp),
                    contentAlignment = Alignment.Center

                ) {
                    Image(
                        painter = painterResource(id = R.drawable.profile),
                        contentDescription = "Profile",
                        modifier = Modifier
                            .padding(vertical = 5.dp)
                            .size(48.dp)
                            .clip(CircleShape)
                            .clickable(onClick = {
                                navController.navigate(Profile.route)
                            })
                    )
                }
            }

        }
    )
}

@Preview(showBackground = true)
@Composable
fun MainPanelHomePreview() {
    MainPanelHome()
}

@Composable
fun MainPanelHome() {
    Box ( modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(0.3f)

        .background(colorResource(R.color.darkGreen)),
     ){
        Column() {
            Text(
                text = "Little Lemon",
                fontSize = 30.sp,
                color = Color.Yellow,
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .padding(vertical = 5.dp)

            )

            Text(
                text = "Chicago",
                fontSize = 20.sp,
                color = Color.White,
                modifier = Modifier.padding(horizontal = 10.dp)
            )

            Row() {

                Text(
                    text = "We are a family owned Mediterranean restaurant, focused on traditional recipes served with a modern twist.",
                    fontSize = 15.sp,
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxWidth(0.6f)
                        .padding(vertical = 10.dp)
                        .padding(horizontal = 10.dp)



                )

                Image( painter = painterResource(id = R.drawable.hero_image),
                    contentDescription = "Herro Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(140.dp, 140.dp)
                        .padding(vertical = 10.dp)
                        .padding(horizontal = 5.dp)

                )

            }
        }
    }



}
