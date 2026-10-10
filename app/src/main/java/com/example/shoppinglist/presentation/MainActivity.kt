package com.example.shoppinglist.presentation


import android.util.Log
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.shoppinglist.R
import com.example.shoppinglist.domain.ShopItem

class MainActivity : androidx.appcompat.app.AppCompatActivity() {

    private lateinit var linearLayoutMain: LinearLayout
    private lateinit var viewModel: MainViewModel;


    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(_root_ide_package_.com.example.shoppinglist.R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        linearLayoutMain = findViewById(R.id.linearLayoutMain)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.shopList.observe(this){
            showList(it)
        }
    }

    private fun showList(list: List<ShopItem>){
        linearLayoutMain.removeAllViews()
        for(shopItem in list){
            val layoutId = if (shopItem.enabled) {
                R.layout.enabled_item
            }
            else {
                R.layout.disabled_item
            }
            val view = LayoutInflater.from(this).inflate(
                layoutId, linearLayoutMain, false
            )
            val tvItemName = view.findViewById<TextView>(R.id.tvItemName)
            val tvItemCount = view.findViewById<TextView>(R.id.tvItemCount)
            tvItemName.text = shopItem.name
            tvItemCount.text = shopItem.count.toString()
            view.setOnLongClickListener {
                viewModel.changeEnableState(shopItem)
                true
            }
            linearLayoutMain.addView(view)
        }
    }
}