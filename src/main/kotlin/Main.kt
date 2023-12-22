// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.type
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*

private val defaultColorScheme = lightColors(
    primary = Color(0xff353535),
    primaryVariant = Color(0xff262626),
    secondary = Color(0x26000000),
    secondaryVariant = Color(0x33000000),
    onPrimary = Color(0xffffffff),
    onSecondary = Color(0xff50ded6),
    onError = Color(0xffee4242),
    onSurface = Color(0xff67de1f),
    onBackground = Color(0xffeedd43)

)

val savedFolders = arrayOf( "/home/mamad/Downloads","/home/.secretFoldeer")
//val savedFolders = arrayOf<String>()


@Composable
@Preview
fun WindowScope.App(ctrlState: Boolean, shiftState: Boolean, remWindowState: WindowState) {
    val currentDirLeft = remember { mutableStateOf("/home/dan") }
    val currentDirRight = remember { mutableStateOf("/home/dan/dev") }

    MaterialTheme(colors = defaultColorScheme){
        Column (modifier = Modifier.background(MaterialTheme.colors.primary).fillMaxSize()){
            titleBarD(remWindowState)
            Box(Modifier.fillMaxWidth()){
                Column (Modifier.fillMaxWidth(0.5f)) {
                    Spacer(modifier = Modifier.height(2.dp))
                    selectFolder(currentDirLeft.value, Modifier.padding(8.dp, 0.dp))
                    Spacer(modifier = Modifier.height(8.dp))
                    fajlBoksz(currentDirLeft,ctrlState,shiftState,1, modifier = Modifier.padding(8.dp, 0.dp))
                }
                Column (Modifier.align(Alignment.TopEnd).fillMaxWidth(0.5f)) {
                    Spacer(modifier = Modifier.height(2.dp))
                    selectFolder(currentDirRight.value, Modifier.padding(8.dp, 0.dp))
                    Spacer(modifier = Modifier.height(8.dp))
                    fajlBoksz(currentDirRight,ctrlState,shiftState,2, modifier = Modifier.padding(8.dp, 0.dp))
                }
            }

        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
fun main() = application {
    val remWindowState = rememberWindowState(placement = WindowPlacement.Maximized)
    var ctrlState by remember { mutableStateOf(false) }
    var shiftState by remember { mutableStateOf(false) }
    Window(onCloseRequest = ::exitApplication, state = remWindowState, undecorated = true, onKeyEvent = { when {
        (it.key == Key.CtrlLeft && it.type== KeyEventType.KeyDown) -> {
            ctrlState = true
            true
        }
        (it.key == Key.CtrlLeft && it.type== KeyEventType.KeyUp) -> {
            ctrlState = false
            true
        }
        (it.key == Key.ShiftLeft && it.type== KeyEventType.KeyDown) -> {
            shiftState = true
            true
        }
        (it.key == Key.ShiftLeft && it.type== KeyEventType.KeyUp) -> {
            shiftState = false
            true
        }
        else -> false
    }}) {
        App(ctrlState, shiftState, remWindowState)

    }
}

