package com.example.ifood.The_Moive_db

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun moive_list_ui(vm: MovieViewModel){
    val nc = rememberNavController()
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(

                title = {

                    Surface(
                        modifier = Modifier
                            .height(50.dp)
                            .width(50.dp),

                        )   {
                        AsyncImage(
                            model ="https://d3t3ozftmdmh3i.cloudfront.net/staging/podcast_uploaded_nologo/360773/360773-1520262091652-fa603e15cdcd2.jpg",
                            contentDescription = null,
                        )

                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(

                    containerColor = androidx.compose.ui.graphics.Color.Black,
                    titleContentColor = androidx.compose.ui.graphics.Color.White,

                    ),
                // refresh button

//                actions = {
//                    IconButton(onClick = {
//                        vm.getResultList()
//                    }) {
//                        Icon(
//                            Icons.Default.Refresh,
//                            contentDescription = "Refresh",
//                            tint = androidx.compose.ui.graphics.Color.White,
//                        )
//                    }
//                }
            )
        }
    ) {
        Surface(
            modifier = Modifier.padding(it)
        ) {
            composeGride(vm)
        }
    }
}




@Composable
fun composeGride(vm: MovieViewModel){

    LaunchedEffect(Unit) {
        vm.getResultList()
    }

    Box(
        modifier = Modifier.fillMaxSize()
            .background(Color.Black),
    )

    if (vm.isLoading) {
        CircularProgressIndicator()
    } else if (vm.errorMessage.isNotEmpty()) {
        Text("Error: ${vm.errorMessage}")
    } else {
        LazyVerticalGrid(columns = GridCells.Fixed(2))

        {
            items(vm.results.size){

                val element = vm.results[it]

                Column(
                    modifier = Modifier
                        .padding(5.dp),
                ){

                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                        ,
                    ) {
                        AsyncImage(
                            model = element.fullPosterPath(),
                            contentDescription = element.fullPosterPath(),
                        )
                    }

                    Box(modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                        .padding(20.dp),
                        Alignment.Center,
                    ){
                        Text(
                            "${element.title}",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            textAlign = TextAlign.Center,
                        )
                    }


                }
            }

        }
    }


}
