package com.example.unit_convertor

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unit_convertor.ui.theme.Unit_convertorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Unit_convertorTheme {
                mainscreen()
            }
        }
    }
}

@Composable
fun mainscreen() {
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

        var expandi by remember { mutableStateOf(false) }
        var expando by remember { mutableStateOf(false) }
        var texti by remember { mutableStateOf("") }
        var texto by remember { mutableStateOf("") }
        var uniti by remember { mutableStateOf("Centimeters") }
        var unito by remember { mutableStateOf("Meters") }
        var factori by remember { mutableStateOf(0.01) }
        var factoro by remember { mutableStateOf(1.00) }


        fun logic(){
            var textin = texti.toDoubleOrNull() ?: 0.0
            var textout = (textin * factori) / factoro
            texto = textout.toString()
        }


        Text("Unit Convertor" , modifier = Modifier
            .padding(16.dp)
            .offset(y = -10.dp) , fontSize = 24.sp)

        var value by remember { mutableStateOf("") }
        OutlinedTextField(value = texti , onValueChange = {texti = it
                                                          logic()} , label = { Text("Enter Value") })

        Spacer(modifier = Modifier.height(8.dp))
        Row(modifier = Modifier) {
            Box {
                Button(onClick = { expandi = true }) {
                    Text("$uniti")
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "ArrowDropDown")
                }
                DropdownMenu(expanded = expandi, onDismissRequest = { expandi = false }) {
                    DropdownMenuItem(text = { Text("Centimeters") },
                        onClick = {expandi = false
                            uniti = "Centimeters"
                            factori = 0.01
                            logic()})
                    DropdownMenuItem(text = { Text("Meters") },
                        onClick = {expandi = false
                            uniti = "Meters"
                            factori = 1.00
                            logic()})
                    DropdownMenuItem(text = { Text("Kilometers") },
                        onClick = {
                            expandi = false
                            uniti = "Kilometers"
                            factori = 1000.00
                            logic()})
                    DropdownMenuItem(text = { Text("Inches") },
                        onClick = {
                            expandi = false
                            uniti = "Inches"
                            factori = 0.0254
                            logic()})
                    DropdownMenuItem(text = { Text("Feet") },
                        onClick = {
                            expandi = false
                            uniti = "Feet"
                            factori = 0.3048
                            logic()})
                }
            }


            Spacer(modifier = Modifier.width(15.dp))


            Box{
                Button(onClick = {expando = true}) {
                    Text("$unito")
                    Icon(Icons.Default.ArrowDropDown , contentDescription = "ArrowDropDown")
                }
                DropdownMenu(expanded = expando, onDismissRequest = {expando = false}) {
                    DropdownMenuItem(text = { Text("Centimeters") },
                        onClick = {expando = false
                            unito = "Centimeters"
                            factoro = 0.01
                            logic()})
                    DropdownMenuItem(text = { Text("Meters") },
                        onClick = {expando = false
                            unito = "Meters"
                            factoro = 1.00
                            logic()})
                    DropdownMenuItem(text = { Text("Kilometers") },
                        onClick = {
                            expando = false
                            unito = "Kilometers"
                            factoro = 1000.00
                            logic()})
                    DropdownMenuItem(text = { Text("Inches") },
                        onClick = {
                            expando = false
                            unito = "Inches"
                            factoro = 0.0254
                            logic()})
                    DropdownMenuItem(text = { Text("Feet") },
                        onClick = {
                            expando = false
                            unito = "Feet"
                            factoro = 0.3048
                            logic()})

                }
            }
        }


        Text("Results: $texto $unito" , modifier = Modifier.padding(16.dp) , fontSize = 20.sp)

    }
}



@Preview(showBackground = true)
@Composable
fun mainscreenPreview(){
    mainscreen()
}
