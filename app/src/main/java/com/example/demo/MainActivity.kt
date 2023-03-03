package com.example.demo

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2


class MainActivity : AppCompatActivity() {


    private var viewPager2: ViewPager2? = null
    private var viewModel: MainViewModel? = null

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        viewPager2 = findViewById(R.id.viewPager2)
        viewPager2?.adapter = ViewPager2FragmentAdapter(supportFragmentManager, lifecycle)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val menuItemView: View = findViewById(R.id.menu_item)
        val popupMenu = PopupMenu(this, menuItemView)
        val menu = popupMenu.menu
        val menu1 = menu.add("历史记录")
        popupMenu.menuInflater.inflate(R.menu.menu_main, popupMenu.menu)
        menu1.setOnMenuItemClickListener {
            DialogFragmentHistory().show(supportFragmentManager, "history_dialog")
            true
        }
        popupMenu.setOnMenuItemClickListener { // 处理菜单项点击事件
            DialogFragmentKey().show(supportFragmentManager, "key_dialog")
            true
        }
        // 显示 PopupMenu
        popupMenu.show()
        return super.onOptionsItemSelected(item)
    }


    companion object {
        const val TAG = "zhaohongshuai"
    }

}
