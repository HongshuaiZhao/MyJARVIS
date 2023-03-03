package com.example.demo

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

/**
 * Created by zhaohongshuai on 2023/3/3
 */
class LoginActivity : AppCompatActivity() {
    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // 初始化控件
        usernameEditText = findViewById(R.id.et_username)
        passwordEditText = findViewById(R.id.et_password)
        loginButton = findViewById(R.id.btn_login)

        // 设置登录按钮的点击事件
        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            // 检查用户名和密码是否为空
            if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                Toast.makeText(this, "用户名和密码不能为空", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // TODO: 向服务器验证用户名和密码

            // 登录成功，保存用户信息到SharedPreferences中
            val sharedPreferences = getSharedPreferences("user_info", MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString("username", username)
            editor.putString("password", password)
            editor.apply()

            // 跳转到MainActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    // 在Activity的onResume方法中检查用户是否已登录，如果已登录，则直接跳转到MainActivity
    override fun onResume() {
        super.onResume()
        val sharedPreferences = getSharedPreferences("user_info", MODE_PRIVATE)
        val username = sharedPreferences.getString("username", "")
        val password = sharedPreferences.getString("password", "")
        if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
