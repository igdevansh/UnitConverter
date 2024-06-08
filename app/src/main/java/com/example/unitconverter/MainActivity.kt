package com.example.unitconverter

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import com.example.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            UnitConverterTheme {
                UnitConverter()
                }
            }
        }
    }

@Composable
fun UnitConverter(){
    var userinput by remember { mutableStateOf("") }
    var useroutput by remember { mutableStateOf("") }
    var inputunit by remember { mutableStateOf("Meters") }
    var outputunit by remember { mutableStateOf("Meters") }
    var iexpanded by remember { mutableStateOf(false) }
    var oexpanded by remember { mutableStateOf(false) }
    val conversionfactor = remember { mutableStateOf(1.00) }
    val oconversionfactor = remember { mutableStateOf(1.00) }

    fun convert(){
        val inputvalue = userinput.toDoubleOrNull() ?: 0.0
        val result = (inputvalue * conversionfactor.value * 100.0/oconversionfactor.value).roundToInt() / 100.0
        useroutput = result.toString()
    }

    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        /*there is number of text composes 1. Textfields 2. Basictextfields 3. Outlinedtextfields */
        Text(text = "Unit Convertor")

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(value = userinput, onValueChange = {userinput= it
            convert()}, label = { Text(text = "enter value")})

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            Box{//INPUT BOX
                Button(onClick = {iexpanded = true}) {//INPUT BUTTON
                    Text(text = inputunit)
                    Icon(Icons.Default.ArrowDropDown,contentDescription="Arrow Down")
                }
                DropdownMenu(expanded = iexpanded, onDismissRequest = {iexpanded=false}) {
                    DropdownMenuItem(text = { Text(text = "Centimeters") },
                        onClick = {iexpanded=false
                        inputunit = "Centimeters"
                        conversionfactor.value = 0.01
                        convert()})
                    DropdownMenuItem(text = { Text(text = "Meters") }, onClick = {iexpanded=false
                        inputunit = "Meters"
                        conversionfactor.value = 1.00
                        convert()})
                    DropdownMenuItem(text = { Text(text = "Kilometers") }, onClick = {iexpanded=false
                        inputunit = "Kilometers"
                        conversionfactor.value = 1000.0
                        convert()})
                    DropdownMenuItem(text = { Text(text = "Millimeters") }, onClick = {iexpanded=false
                        inputunit = "Millimeters"
                        conversionfactor.value = 0.001
                        convert()})
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            Box{
                Button(onClick = {oexpanded=true}) {
                    Text(text = outputunit)
                    Icon(Icons.Default.ArrowDropDown,contentDescription="Arrow Down")
                }
                DropdownMenu(expanded = oexpanded, onDismissRequest = {oexpanded=false}) {
                    DropdownMenuItem(text = { Text(text = "Centimeters") }, onClick = {oexpanded=false
                        outputunit = "Centimeters"
                        oconversionfactor.value = 0.01
                        convert()})
                    DropdownMenuItem(text = { Text(text = "Meters") }, onClick = {oexpanded=false
                        outputunit = "Meters"
                        oconversionfactor.value = 1.00
                        convert()})
                    DropdownMenuItem(text = { Text(text = "Kilometers") }, onClick = {oexpanded=false
                        outputunit = "Kilometers"
                        oconversionfactor.value = 1000.0
                        convert()})
                    DropdownMenuItem(text = { Text(text = "Millimeters") }, onClick = {oexpanded=false
                        outputunit = "Millimeters"
                        oconversionfactor.value = 0.001
                        convert()})
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Result: $useroutput $outputunit")
    }
}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview(){
    UnitConverter()
}
