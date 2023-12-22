import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.window.WindowDraggableArea
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowPlacement.*
import androidx.compose.ui.window.WindowScope
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.rememberWindowState
import kotlin.system.exitProcess

@Composable
fun WindowScope.titleBarD(
    state: WindowState,
    modifier: Modifier=Modifier
) = WindowDraggableArea{
    Row (verticalAlignment = Alignment.CenterVertically, modifier = Modifier.height(40.dp).fillMaxWidth().background(MaterialTheme.colors.primaryVariant))
    {
        Text("Minimal General - v0.1", modifier.padding(horizontal = 5.dp), color = MaterialTheme.colors.onPrimary )
        Spacer(modifier.weight(1f))
        Button(onClick = { minimize(state) }, modifier.padding(
            horizontal = 5.dp,
            vertical = 5.dp
        ).width(20.dp).height(20.dp), shape = CircleShape, colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.onBackground
        )){}
        Button(onClick = {maximize(state)}, modifier.padding(
            horizontal = 5.dp,
            vertical = 5.dp
        ).width(20.dp).height(20.dp), shape = CircleShape, colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.onSurface)){}
        Button(onClick = {quit()}, modifier.padding(
            horizontal = 5.dp,
            vertical = 5.dp
        ).width(20.dp).height(20.dp), shape = CircleShape, colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.onError)){}
    }
}


fun quit(){
    exitProcess(0)
}
fun minimize(state: WindowState){
    state.isMinimized = true
}
fun maximize(state:WindowState){
    if(state.placement == Maximized){
        state.placement = Floating
    } else {
        state.placement = Maximized
    }
}