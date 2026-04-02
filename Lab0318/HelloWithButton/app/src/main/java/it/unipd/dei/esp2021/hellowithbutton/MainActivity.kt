package it.unipd.dei.esp2021.hellowithbutton

import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsetsController.APPEARANCE_LIGHT_NAVIGATION_BARS
import android.view.WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.WindowCompat

class MainActivity : AppCompatActivity()
{
    // Called when the activity is first created
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        // Create the TextView
        val tv = TextView(this)
        tv.text = "Press the button, please"

        // Create the Button
        val bu = Button(this)
        bu.text = "Press me"

        // Set the action to be performed when the button is pressed
        bu.setOnClickListener { // Perform action on click
            tv.text = "Good job!"
        }

        // Secondo pulsante
        val but2 = Button(this)
        but2.text = "+1"

        // Seconda text view
        val tv2 = TextView(this)
        tv2.text = "0"

        var count : Int = 0
        but2.setOnClickListener {
            count = count.inc()
            tv2.text = count.toString()
        }

        // All UI elements must have IDs to use ConstraintSet
        bu.id = View.generateViewId()
        tv.id = View.generateViewId()
        but2.id = View.generateViewId()
        tv2.id = View.generateViewId()

        // Create the layout
        val myLayout = ConstraintLayout(this)

        // Add the UI elements to the layout
        myLayout.addView(bu)
        myLayout.addView(tv)
        myLayout.addView(but2)
        myLayout.addView(tv2)

        // Add constraints to the layout so that UI elements are positioned correctly
        val mySet = ConstraintSet()
        mySet.clone(myLayout)
        mySet.connect(bu.id, ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT)
        mySet.connect(bu.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP)
        mySet.connect(tv.id, ConstraintSet.LEFT, bu.id, ConstraintSet.RIGHT)
        mySet.connect(tv.id, ConstraintSet.BASELINE, bu.id, ConstraintSet.BASELINE)

        mySet.connect(but2.id, ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT)
        mySet.connect(but2.id, ConstraintSet.TOP, bu.id, ConstraintSet.BOTTOM)
        mySet.connect(tv2.id, ConstraintSet.LEFT, but2.id, ConstraintSet.RIGHT)
        mySet.connect(tv2.id, ConstraintSet.BASELINE, but2.id, ConstraintSet.BASELINE)

        mySet.applyTo(myLayout)

        // Account for system bars insets
        myLayout.fitsSystemWindows = true

        // Enable edge-to-edge display on API level < 35
        WindowCompat.enableEdgeToEdge(window)

        // Ensure that system bars remain visible regardless of the background color
        manageSystemBarsAppearance(myLayout)

        // Display the layout
        setContentView(myLayout)
    }

    fun manageSystemBarsAppearance(rootView: View) {
        val nightModeMask = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        if (nightModeMask == Configuration.UI_MODE_NIGHT_NO) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                val appearanceMask =
                    APPEARANCE_LIGHT_NAVIGATION_BARS or
                            APPEARANCE_LIGHT_STATUS_BARS
                window.insetsController?.setSystemBarsAppearance(appearanceMask, appearanceMask)
            } else {
                val newVis = rootView.systemUiVisibility or
                        View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR or
                        View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                rootView.systemUiVisibility = newVis
            }
        }
    }
}
