package com.aptivist.composestate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aptivist.composestate.ui.theme.ComposeStateTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeStateTheme {

                val vm : MainViewModel = viewModel()

                Column(modifier = Modifier.fillMaxSize()) {
                    Header(modifier = Modifier.fillMaxWidth(), vm = vm)
                    Body(modifier = Modifier.fillMaxSize(), vm)
                }
            }
        }
    }


}

@Composable
private fun Body(modifier: Modifier= Modifier, vm: MainViewModel) {

    val viewState = remember { vm.containerState }

    Box(modifier = modifier) {
        when(viewState.value){
            MainViewContainerState.ViewOne -> {
                Column(modifier = Modifier.align(Alignment.Center), verticalArrangement = Arrangement.Center) {
                    Text(text = "This is my first view")
                    Image(painter = painterResource(id = android.R.drawable.btn_star_big_on), contentDescription = "")
                }
            }
            MainViewContainerState.ViewTwo -> {
                Text(text = "This is my second view", modifier = Modifier.align(Alignment.Center))
            }
            MainViewContainerState.ViewThree -> {
                Text(text = "This is my third view", modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}

@Composable
private fun Header(modifier: Modifier= Modifier, vm: MainViewModel) {

    val viewState = remember { vm.containerState }

    Row(modifier = modifier) {
        Tabs(
            title = "First View", modifier = Modifier
                .weight(1f)
                .background(Color.DarkGray) , isSelected = viewState.value == MainViewContainerState.ViewOne
        ) {
            vm.switchViews(MainViewContainerState.ViewOne)
        }
        Tabs(
            title = "Second View", modifier = Modifier
                .weight(1f)
                .background(Color.DarkGray) , isSelected = viewState.value == MainViewContainerState.ViewTwo
        ) {
            vm.switchViews(MainViewContainerState.ViewTwo)
        }
        Tabs(
            title = "Third View", modifier = Modifier
                .weight(1f)
                .background(Color.DarkGray) , isSelected = viewState.value == MainViewContainerState.ViewThree
        ) {
            vm.switchViews(MainViewContainerState.ViewThree)
        }
    }
}

@Composable
fun Tabs(modifier: Modifier = Modifier, title: String, isSelected : Boolean, onClick : () -> Unit) {

    Box(modifier = modifier
        .clickable {
            onClick.invoke()
        }
        .background(if (isSelected) Color.Green else  Color.Blue)
        .height(60.dp)) {
        Text(text = title, modifier = Modifier.align(Alignment.Center), color = Color.White)
    }
}
