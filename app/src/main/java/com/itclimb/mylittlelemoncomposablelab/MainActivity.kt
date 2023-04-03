package com.itclimb.mylittlelemoncomposablelab


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.itclimb.mylittlelemoncomposablelab.database.HomeViewModel
import com.itclimb.mylittlelemoncomposablelab.navigation.MyNavigation
import com.itclimb.mylittlelemoncomposablelab.ui.theme.MyLittleLemonComposableLabTheme
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {


    private val httpClientInstance = HttpClient(Android) {
        install(ContentNegotiation) {
            json(contentType = ContentType("text", "plain"))
        }

     }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setContent {
            val navController = rememberNavController()
            MyNavigation(navController, this)
       }

                //find
        lifecycleScope.launch(Dispatchers.IO) {
            if ( !HomeViewModel(this@MainActivity).isEmptyDatabase()) {
                HomeViewModel(this@MainActivity).deleteAllItemsFromDatabase()
            }
                val  menuItems = getMenu();
                   HomeViewModel(this@MainActivity).saveMenuToDatabase(menuItems)

        }



    }



    private suspend fun getMenu(): List<MenuItemNetwork> {

        val response: MenuNetworkData = httpClientInstance
            .get("https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json").body()

        return response.menu

    }


}



@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyLittleLemonComposableLabTheme {
        Greeting("Android")
    }
}

