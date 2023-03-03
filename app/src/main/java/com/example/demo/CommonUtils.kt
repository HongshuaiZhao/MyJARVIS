package com.example.demo

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.os.Vibrator
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

/**
 * Created by zhaohongshuai on 2023/3/2
 */
object CommonUtils {

    private val handler = Handler(Looper.getMainLooper())

    fun copyWithVibrate(context: Context?, text: String) {
        context ?: return
        val vibrator = context.getSystemService(AppCompatActivity.VIBRATOR_SERVICE) as Vibrator
        val clipboardManager =
            context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clipData = ClipData.newPlainText("text", text)
        clipboardManager.setPrimaryClip(clipData)
        Toast.makeText(context, "复制成功", Toast.LENGTH_SHORT).show()
        vibrator.vibrate(30)
    }

    fun runOnUiThread(action: Runnable) {
        if (Looper.myLooper() !== Looper.getMainLooper()) {
            handler.post(action)
        } else {
            action.run()
        }
    }
}