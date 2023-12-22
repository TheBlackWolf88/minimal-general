import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowPosition
import org.jetbrains.skiko.OS
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import kotlin.io.path.isDirectory
import kotlin.math.roundToInt

@Composable
fun fajlBoksz(
    currentDir: MutableState<String>,
    ctrlState: Boolean,
    shiftState: Boolean,
    id: Int,
    modifier: Modifier = Modifier
) {
    Column {
        val selectedItems = remember { mutableStateListOf<String>() }
        Text(
            "  ..",
            color = MaterialTheme.colors.onPrimary,
            modifier = modifier.height(26.dp).padding(0.dp, 0.dp).fillMaxWidth()
                .background(MaterialTheme.colors.secondary).border(width = 1.dp, color = MaterialTheme.colors.onPrimary)
                .clickable {
                    if (Paths.get(currentDir.value).parent != null) currentDir.value =
                        Paths.get(currentDir.value).parent.toString()
                })
        LazyColumn(
            modifier.fillMaxWidth(1f).background(MaterialTheme.colors.secondary)
        ) {
            itemsIndexed(retrieveFolders(currentDir.value).toList()) { index, item ->
                Item(item.toString(), selectedItems, currentDir, ctrlState, shiftState,index)
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class, ExperimentalFoundationApi::class)
@Composable
fun Item(name: String, selectedItems: MutableList<String>, currentDir: MutableState<String>, ctrlState: Boolean,shiftState: Boolean, index : Int){
    var offsetX = remember { mutableStateOf(0f) }
    var offsetY = remember { mutableStateOf(0f) }
    Box(Modifier.border(width = 1.dp, color = if(selectedItems.contains(name)) MaterialTheme.colors.onSecondary else MaterialTheme.colors.onPrimary )
        .combinedClickable (
            onClick = {
                if(ctrlState){
                    selectedItems.add(name)
                    osOpenCommand()
                }
                else if(!ctrlState && ! shiftState){
                    if(selectedItems.contains(name)){ selectedItems.remove(name) ;return@combinedClickable
                    }
                    selectedItems.clear()
                    selectedItems.add(name)
                }
                else if(shiftState && !ctrlState){
                    var folders : List<String> = retrieveFolders(currentDir.value).map { it.toString() }
                    var from = folders.indexOf(selectedItems.last())

                    folders.forEachIndexed { i, item ->
                        run {
                            if (i in index..from || i in from .. index) selectedItems.add(item)
                        }
                    }
                }
            },
            onDoubleClick = {
                if(Paths.get(name).isDirectory()) currentDir.value = name else Runtime.getRuntime().exec("xdg-open $name")
            }
        )
        .pointerInput(Unit){
            detectDragGestures { change, dragAmount ->
                selectedItems.add(name)
                change.consume()
                offsetX.value += dragAmount.x
                offsetY.value += dragAmount.y
            }
        }
        .offset { IntOffset(offsetX.value.roundToInt(), offsetY.value.roundToInt()) }
    )
    {
        Text(text=name, color = if(selectedItems.contains(name)) MaterialTheme.colors.onSecondary else MaterialTheme.colors.onPrimary,
             modifier = Modifier.fillMaxWidth().padding(16.dp, 4.dp)

        )
    }

}


fun retrieveFolders(currentDir: String): List<Path> {
    val dirs = Files.walk(Paths.get(currentDir), 1).filter { it.toString() != currentDir }.toList()
    return dirs

}

fun osOpenCommand(){
    val os = System.getProperty("os.name")
    println(os)
}