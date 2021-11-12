package com.example.profilecardlayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Top
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.transform.CircleCropTransformation
import com.example.profilecardlayout.ui.theme.ProfileCardLayoutTheme
import com.google.accompanist.coil.rememberCoilPainter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProfileCardLayoutTheme {
                UsersApplication()
            }
        }
    }
}

//NAVCONTROLLER
@Composable
fun UsersApplication(userProfiles: List<UserProfile> = userProfileList) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "users_list") {
        composable(route ="users_list") {
            UserListScreen(userProfiles, navController)
        }
        composable(
            route = "user_details/{userId}",
            arguments = listOf(navArgument("userId") {
                type = NavType.IntType
            } )
        ) {
            navBackStackEntry ->
            UserProfileDetailsScreen(navBackStackEntry.arguments!!.getInt("userId"))
            println("HEREEEEEEE")
        }
    }
}

//SCREEN
@Composable
fun UserListScreen(userProfiles: List<UserProfile>, navController: NavHostController?) {

    Scaffold(topBar = { AppBar() }) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.LightGray
        ) {
            LazyColumn {
                items(userProfiles) { userProfile ->
                    ProfileCard(userProfile = userProfile) {
                        navController?.navigate("user_details/${userProfile.id}")
                    }
                }
            }
        }
    }
}

//SCREEN
@Composable
fun UserProfileDetailsScreen(userId: Int) {

    val userProfile = userProfileList.first { userProfile -> userId == userProfile.id }

    Scaffold(topBar = { AppBar() }) {

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.LightGray
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                ProfilePicture(userProfile.pictureUrl, userProfile.status, 240.dp)
                ProfileContent(userProfile.name, userProfile.status, Alignment.CenterHorizontally)
            }
        }
    }
}

@Composable
fun AppBar() {
    TopAppBar(
        title = {
            Text("Messaging application users")},
        navigationIcon = {
            Icon(
                Icons.Default.Home,
                "Home",
                modifier = Modifier.padding(12.dp)
            )}
    )
}

@Composable
fun ProfileCard(userProfile: UserProfile, clickAction: () -> Unit) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(align = Top)
            .padding(start = 16.dp, top = 14.dp, bottom = 4.dp, end = 16.dp)
            .clickable(onClick = { clickAction.invoke() }),
        elevation = 8.dp,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            ProfilePicture(userProfile.pictureUrl, userProfile.status, 72.dp)
            ProfileContent(userProfile.name, userProfile.status, Alignment.Start)
        }
    }
}

@Composable
fun ProfilePicture(pictureUrl: String, onlineStatus: Boolean, imageSize: Dp) {

    Card(
        shape = CircleShape,
        border = BorderStroke(
            width = 1.5.dp,
            color =
            if (onlineStatus)
                Color.Green
            else
                Color.Red),
        modifier = Modifier
            .padding(16.dp),
        elevation = 4.dp,
    ) {
        //async loading image library
        Image(
            painter = rememberCoilPainter(
            request = pictureUrl,
            requestBuilder = {
                transformations(CircleCropTransformation())
            }),
            modifier = Modifier.size(imageSize),
            contentDescription = "Profile picture description",
        )
    }
}

@Composable
fun ProfileContent(userName: String, onlineStatus: Boolean, alignment: Alignment.Horizontal) {

    Column(
        modifier = Modifier
            .padding(8.dp),
        horizontalAlignment = alignment
    ) {
        CompositionLocalProvider(
            LocalContentAlpha provides (
                    if (onlineStatus)
                        ContentAlpha.high
                    else ContentAlpha.medium)
        ) {
            Text(
                text = userName,
                style = MaterialTheme.typography.h5
            )
        }
        CompositionLocalProvider(
            LocalContentAlpha provides ContentAlpha.medium
        ) {
            Text(
                text =
                if(onlineStatus)
                    "Active now"
                else
                    "Offline",
                style = MaterialTheme.typography.body2,
            )
        }
    }
}



//PREVIEWS
@Preview(showBackground = true)
@Composable
fun WholeApp() {
    ProfileCardLayoutTheme {
        UsersApplication()
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileListPreview() {
    ProfileCardLayoutTheme {
        UserListScreen(userProfiles = userProfileList, null)
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileDetailsPreview() {
    ProfileCardLayoutTheme {
        UserProfileDetailsScreen(userId = 0)
    }
}