import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.net.URI
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import kotlin.io.path.name

@Composable
fun selectFolder(
    currentDir: String,
    modifier: Modifier = Modifier
){
    Column (modifier.background(MaterialTheme.colors.secondaryVariant).padding(8.dp).fillMaxWidth()){
        Row {
            Text(currentDir, color = MaterialTheme.colors.onPrimary)

        }


    }
}


