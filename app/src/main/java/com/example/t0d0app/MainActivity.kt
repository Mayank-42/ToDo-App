package com.example.t0d0app


import android.R
import android.R.attr.checked
import android.R.attr.name
import android.R.attr.text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.t0d0app.TaskViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import android.os.Bundle
import android.util.Log.i
import android.widget.CheckBox
import androidx.compose.ui.text.font.FontStyle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeCompilerApi
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.SemanticsActions.OnClick
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.room.util.copy
import com.example.t0d0app.data.local.Task
import com.example.t0d0app.data.local.todoReposatory
import com.example.t0d0app.ui.theme.T0D0APPTheme
import dagger.hilt.android.AndroidEntryPoint
import java.nio.file.Files.size
import kotlin.collections.emptyList
import androidx.hilt.navigation.compose.hiltViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {// it can reive a bundel,, y it is nulable beacuse when we create this for the first time so does android have any bundel(NO) so at that time we will pass null inside it
        super.onCreate(savedInstanceState)  //and now when we use onDestroy and call to onCreate it will first call its parent then we can if that has some bunle we execute that
        enableEdgeToEdge()
        setContent {
//            val application = application as todoApplication

//            val repository = todoReposatory(
//                application.database.Daoo()
//            )
//            val repository = application.container.repository

//            val viewModel: TaskViewModel = viewModel(
//                factory = TaskViewModelFactory(repository)
//            )
//            val viewModel: TaskViewModel = viewModel(
//                factory = application.container.viewModelFactory
//            )
            val viewModel: TaskViewModel = hiltViewModel()
//            val viewMolel: TaskViewModel= viewModel()
//            viewModel.insert( Task(0,"SWIPE TO DELETE"))
            T0D0APPTheme {
                shoScreen(viewModel)
            }
        }
    }
}
//data class Task(var task:String,var isMarked: MutableState<Boolean> = mutableStateOf(false)){

//}


//    var task=mutableStateListOf<Task>();
@Composable
fun shoScreen(viewMode:TaskViewModel){
    var ShowScreen by remember{mutableStateOf(false)}
    if(ShowScreen){
        TakingInput(onClicked ={ShowScreen=false},viewModel= viewMode)
    }
    else{
        homePage(onClicked ={ShowScreen=true},viewModel= viewMode)
//        onClicked ={ShowScreen=true},
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun homePage(onClicked:()->Unit,viewModel: TaskViewModel) {

//    var markedCount=(Task.isMarked==true).count

    val task by viewModel.allTasks.collectAsState(initial = emptyList())

        val markedCount = task.count { it.isMarked }
    Scaffold(
        topBar={
            Surface(
                shape = RoundedCornerShape(bottomStart = 15.dp, bottomEnd = 15.dp),
                shadowElevation = 8.dp
            ) {
                TopAppBar(

                        title = {
                            text("YOKOSO",40)
                        }
                    ,
                    actions = {
                        if (markedCount > 0) {
                            IconButton(onClick = { viewModel.getallMarked() },modifier=Modifier.size(80.dp)) {
                                Icon(imageVector = Icons.Default.Delete, contentDescription = null,tint=Color.Red)
                            }
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFFFB300)),
//                shape=RoundedCornerShape(16.dp)
                )

              }
        }
        ,

    ) { paddingValues ->

//    var checked by remember{mutableStateOf(true)}
        Box(
            modifier = Modifier.fillMaxSize().background(color = Color(0xFFFFF9C4)).padding(paddingValues)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            ) {
                items(task,key = { it.id }) { ele ->
            var offsetX by remember { mutableFloatStateOf(0f) }

                    Box(
                        modifier = Modifier
                            .padding(start = 20.dp, top = 20.dp)
                            .height(80.dp)
                            .width(350.dp)
                    ) {

                        // Background
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    Color.Red,
                                    RoundedCornerShape(16.dp)
                                ),
                            contentAlignment = Alignment.CenterEnd
                        ) {
//                        Button(onClick={ task.remove(ele)},colors=ButtonDefaults.buttonColors(containerColor = Color.Red)){
                            Button(
                                onClick = { viewModel.delete(ele) },
                                colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                            ) {

                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "Delete",
                                    tint = Color.White,
                                    modifier = Modifier.padding(end = 15.dp)
                                )
                            }

                        }

                        // Your Surface
                        Surface(
                            modifier = Modifier
                                .fillMaxSize()
                                .offset {
                                    IntOffset(offsetX.toInt(), 0)
                                }
                                .draggable(
                                    orientation = Orientation.Horizontal,
                                    state = rememberDraggableState { delta ->

                                        offsetX += delta

                                        // Don't allow dragging to the right
                                        if (offsetX > 0f)
                                            offsetX = 0f

                                        // Maximum slide distance
                                        if (offsetX < -200f)
                                            offsetX = -200f
                                    }
                                ),


                            color = Color.Yellow,
                            shadowElevation = 4.dp,
                            shape = RoundedCornerShape(16.dp)

                        ) {

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.fillMaxSize()
                            ) {
                                println("${ele.task} -> ${ele.isMarked}")
                                Checkbox(
                                    checked = ele.isMarked,
//                                onCheckedChange = { ele.isMarked = it; viewModel.update(ele);println("Clicked: $it") }
                                colors = CheckboxDefaults.colors( checkedColor = Color.DarkGray,
                                    uncheckedColor = Color.Gray,
                                    checkmarkColor = Color.White),
                                    onCheckedChange = {
                                        val updatedTask = ele.copy(isMarked = it)
                                        viewModel.update(updatedTask)
                                    }
                                )

                                text(ele.task)
                            }

                        }

                    }

                }

            }
            val LightYellow = Color(0xFFFFFF99)
            Row(
                modifier = Modifier.fillMaxSize()
                    .padding(bottom = 70.dp, end = 20.dp),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.End
            ) {
                ElevatedButton(
                    onClick = onClicked,
                    shape = RoundedCornerShape(40.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = LightYellow)

                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null,
                        tint = Color.Black
                    )
                }
            }
        }
    }
}


@Composable
fun text(name:String,size:Int=25,pad:Int=12){
    Text(
        text="$name",


        color=Color.Black,
        modifier=Modifier.padding(pad.dp)
//            .background(color=Color.Yellow)
            ,
        fontSize = size.sp,
        fontWeight  = FontWeight.Medium,
//        fontStyle =FontStyle.Italic,
        fontFamily=FontFamily.SansSerif


    )
}



@Composable
fun TakingInput(onClicked:()->Unit,viewModel:TaskViewModel) {

    var Words by remember { mutableStateOf("") }


    Box(modifier = Modifier.fillMaxSize().background(color=Color(0xFFFFFF99)), contentAlignment = Alignment.Center) {

        Box(
            modifier = Modifier.height(250.dp).width(280.dp)
            .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFFFFA726)),
        ) {
            text("Add Task",24,pad=70)
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                TextField(value = Words, onValueChange = { Words = it },
                    shape=RoundedCornerShape(16.dp),
                    colors= TextFieldDefaults.colors(unfocusedContainerColor = Color(0xFFFFF3E0), focusedContainerColor = Color(0xFFFFE0B2), focusedTextColor = Color.Black,unfocusedTextColor = Color.Black),
                    placeholder = {Text ("Enter you text",color=Color.Black)},

                )
            }

            Row(
                modifier = Modifier.fillMaxSize().padding(8.dp),

                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.End
            ) {
                Button(
//                    onClick ={ task.add(Task(0,Words,false)); onClicked() },
                    onClick ={ viewModel.insert(Task(0,Words,false)); onClicked() },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFCC80))
                ) {
                    Text(text = "SUBMIT")
                }
                Spacer(modifier=Modifier.width(20.dp))
                Button(
                    onClick =onClicked,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE0E0E0))
//                    size=10.dp
                ) { Text(text = "Cancel") }
            }
        }
    }
}




//    @Preview(showBackground = true, showSystemUi = true)
//    @Composable
//    fun homePreview() {
//        T0D0APPTheme {
//            homePage({},);
//        }
//    }
//
//    @Preview(showBackground = true, showSystemUi = true)
//    @Composable
//    fun GreetingPreview() {
//        T0D0APPTheme {
//            TakingInput({} );
//        }
//    }
