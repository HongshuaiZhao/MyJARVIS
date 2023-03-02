package com.example.demo

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.demo.openai.OpenAiApiClient
import com.example.demo.openai.OpenAiMessage
import com.example.demo.openai.OpenAiRequest


class MainActivity : AppCompatActivity() {


    private val openAiApiClient by lazy {
        OpenAiApiClient()
    }
    private var text: TextView? = null
    private var editText: EditText? = null
    private var submit: TextView? = null



    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        text = findViewById(R.id.text)
        editText = findViewById(R.id.editText)
        submit = findViewById(R.id.submit)
        submit?.setOnClickListener {
                sendRequest(editText?.text.toString())
        }
//        sendRequest()
    }


    private fun sendRequest(message:String) {
        val request = OpenAiRequest(
            model = "gpt-3.5-turbo",
            messages = listOf(OpenAiMessage(role = "user", content = message))
        )

        //apiKey为我的密钥
        openAiApiClient.getCompletion(
            //https://platform.openai.com/account/api-keys 生成地址
//            apiKey = "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",
            apiKey = "Bearer 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",
            request = request,
            onResponse = { response ->
                runOnUiThread {
                    text?.text = response
                    Log.d(TAG, "sendRequest: $response ")
                }
                Log.d(TAG, response)
            },
            onError = { error ->
                Log.e(TAG, error.message, error)
            }
        )

    }


    companion object {
        const val TAG = "zhaohongshuai"
    }

}
