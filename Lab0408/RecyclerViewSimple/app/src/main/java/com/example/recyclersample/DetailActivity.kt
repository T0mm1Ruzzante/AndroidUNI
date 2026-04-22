package com.example.recyclersample

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.recyclerview.widget.RecyclerView

class DetailActivity : AppCompatActivity() {
    val mTag = this.javaClass.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.v(mTag, "ENTER THE onCreate DetailActivity FUNCTION")

        // Enable edge-to-edge display on API level < 35
        WindowCompat.enableEdgeToEdge(window)

        // Display the layout
        setContentView(R.layout.activity_detail)

        val tv: TextView = findViewById(R.id.good_flower)

        // Get the string
        val flowerName = intent.getStringExtra("tagFlower")

        tv.text = "${flowerName} is a fiore"
    }

    companion object {
        const val FLOWER_NAME = "tagFlower"
    }
}

