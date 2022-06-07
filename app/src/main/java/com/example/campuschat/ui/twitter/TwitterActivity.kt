package com.example.campuschat.ui.twitter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.example.campuschat.R

class TwitterActivity : AppCompatActivity() {
    private lateinit var toolBar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_twitter)

        // tool bar
        toolBar = findViewById(R.id.toolbar_twitter)
        setSupportActionBar(toolBar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeAsUpIndicator(R.drawable.blue_arrow_left)
        }
        toolBar.setTitle(R.string.chat)


    }
}