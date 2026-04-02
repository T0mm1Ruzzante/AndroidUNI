package it.unipd.dei.esp2223.hellowithbuttond

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import it.unipd.dei.esp2223.hellowithbuttond.ui.theme.ComposeHelloWithButtonTheme
import java.lang.NumberFormatException

class MainActivity: ComponentActivity()
{
    // Called when the activity is first created
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        // Enable edge-to-edge display on API level < 35
        enableEdgeToEdge()

        // Set and display the UI content
        setContent {
            ComposeHelloWithButtonTheme {
                // Reference: https://developer.android.com/develop/ui/compose/components/scaffold
                // The scaffold fills the whole display area
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // MainScreen consumes the insets
                    // to keep the app UI away from the system UI and display cutouts
                    MainScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier)
{
    val pleasePress = stringResource(R.string.please_press)
    val goodJob = stringResource(R.string.good_job)
    val counter = stringResource(R.string.counter)

    // Reference: https://developer.android.com/jetpack/compose/state
    var t by remember { mutableStateOf(pleasePress) }
    var t2 by remember { mutableStateOf(counter) }

    // Reference: https://developer.android.com/develop/ui/compose/layouts/constraintlayout
    ConstraintLayout(modifier = modifier) {
        val (bu, tv) = createRefs()

        Button(
            modifier = Modifier.constrainAs(bu) {
                top.linkTo(parent.top)
                start.linkTo(parent.start, margin = 4.dp)
            },
            // Set the action to be performed when the button is pressed
            onClick = { t = goodJob }
        ) {
            Text(text = stringResource(R.string.press_me))
        }

        Text(
            modifier = Modifier.constrainAs(tv) {
                start.linkTo(bu.end, margin = 4.dp)
                baseline.linkTo(bu.baseline)
            },
            text = t
        )

        val (but2, tv2) = createRefs()

        Button(
            modifier = Modifier.constrainAs(but2) {
                top.linkTo(bu.bottom)
                start.linkTo(parent.start, margin = 4.dp)
            },
            onClick = {
                val i : Int = try {
                    t2.toInt()
                } catch (e: NumberFormatException) {
                    -1
                }
                t2 = "${i+1}"
            }
        ) {
            Text(text = stringResource(R.string.sbutton))
        }

        Text(
            modifier = Modifier.constrainAs(tv2) {
                start.linkTo(but2.end, margin = 4.dp)
                baseline.linkTo(but2.baseline)
            },
            text = t2
        )
    }
}

// Reference: https://developer.android.com/develop/ui/compose/tooling/previews
@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
}
