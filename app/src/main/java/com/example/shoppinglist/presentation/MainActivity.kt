package com.example.shoppinglist.presentation

import androidx.activity.enableEdgeToEdge

class MainActivity : androidx.appcompat.app.AppCompatActivity() {
    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(_root_ide_package_.com.example.shoppinglist.R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}