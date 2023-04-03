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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
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
        MainPanelHome(context)

    }


}



@Composable
fun setDatabaseList( ){


    MyLittleLemonComposableLabTheme {


        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {

            // add Button code here
            Column(
                modifier = Modifier. fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

            }

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
                        Text(menuItem.title,
                            fontFamily = karlaFontFamily,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.ExtraBold,
                            modifier = Modifier.
                            padding(horizontal = 10.dp)
                        )
                        Row() {


                            Column(modifier = Modifier.fillMaxWidth(0.7f)) {
                                Text(menuItem.description,
                                    fontFamily = karlaFontFamily,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Normal,
                                    modifier = Modifier.padding(vertical = 10.dp).
                                            padding(horizontal = 10.dp)
                                    )
                                Text(
                                    fontFamily = karlaFontFamily,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Medium,
                                    text = "$%.2f".format(menuItem.price),
                                    modifier = Modifier.
                                    padding(horizontal = 10.dp)
                                )
                            }

                               GlideImage(
                                    model = menuItem.image,
                                    contentScale = ContentScale.Crop,
                                    contentDescription = "dish image for " + menuItem.title,
                                    modifier = Modifier
                                        .size(100.dp, 100.dp)
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
                    .height(60.dp),
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
                            .size(180.dp)
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


@Composable
fun MainPanelHome(context: Context) {


    val viewModel: HomeViewModel = HomeViewModel(context)

    val databaseMenuItems by viewModel.getMenuItems ().observeAsState(emptyList())


    var orderMenuItems by remember { mutableStateOf(false) }
    var searchPhrase by remember { mutableStateOf("") }
    var category by remember {mutableStateOf("")}

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

    if (category.isNotEmpty()){
        menuItems = menuItems.filter {it.category==category}

    }

    Box ( modifier = Modifier
        .fillMaxWidth()
        .background(colorResource(R.color.darkGreen)),
     ){
        Column() {

                Text(
                    text = "Little Lemon",
                    fontFamily = markaziFontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 60.sp,
                    color = colorResource(id = R.color.yellow),
                    modifier = Modifier
                        .padding(horizontal = 10.dp)

                )

                Text(
                    text = "Chicago",
                    fontFamily = markaziFontFamily,
                    fontSize = 40.sp,
                    color = Color.White,
                    modifier = Modifier.padding(horizontal = 10.dp)
                        .offset(0.dp,(-20).dp)
                )


            Row() {

                Text(
                    text = "We are a family owned Mediterranean restaurant, focused on traditional recipes served with a modern twist.",
                    fontSize = 18.sp,
                    color = Color.White,
                    fontFamily = karlaFontFamily,
                    modifier = Modifier
                        .fillMaxWidth(0.6f)
                        .padding(horizontal = 10.dp)

                )


                Image( painter = painterResource(id = R.drawable.hero_image),
                    contentDescription = "Herro Image",
                    contentScale = ContentScale.Crop,

                    modifier = Modifier
                        .size(150.dp, 150.dp)
                        .padding(horizontal = 10.dp)
                        .padding(bottom = 10.dp)
                        .clip(RoundedCornerShape(16.dp))

                )

            }

            TextField(
                value = searchPhrase,
                singleLine = true,
                placeholder = { Text("Enter Search Phrase") },
                onValueChange = {
                    searchPhrase = it
                },
                shape = RoundedCornerShape(16.dp),
                label = { Text("Search") },
                leadingIcon = {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = "Search"
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = colorResource(id = R.color.lightGrey) // Set the background color to white
                ),
                modifier = Modifier
                    .padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
                    .fillMaxWidth())
        }
    }

    Text(text = "ORDER FOR DELIVERY!",
        fontFamily = karlaFontFamily,
        fontSize = 20.sp,
        fontWeight = FontWeight.ExtraBold,
        modifier = Modifier
            .padding(top = 20.dp)
            .padding(horizontal = 10.dp)
    )

    val categoryList = databaseMenuItems.map{ it.category }.distinct().toList();
    Row(){
        categoryList.forEach {
            Button(onClick = {
                 category = it
            },
                colors = ButtonDefaults.buttonColors(colorResource(R.color.lightGrey)),
                modifier = Modifier
                    .padding(5.dp)
                    .clip(RoundedCornerShape(30.dp))

            ) {
                Text(text = it,
                    fontFamily = karlaFontFamily,
                    fontWeight = FontWeight.ExtraBold,
                    color = colorResource(id = R.color.darkGreen))

            }
        }
    }

    Divider(modifier = Modifier.padding(vertical = 10.dp))
    MenuItemsList(menuItems)

}

@Composable
fun setCategories(menuItems: List<MenuItemRoom>) {


}
