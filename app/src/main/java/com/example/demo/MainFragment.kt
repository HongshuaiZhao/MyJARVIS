package com.example.demo

import android.os.Bundle
import android.os.Vibrator
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.demo.CommonUtils.runOnUiThread
import com.example.demo.openai.OpenAiApiClient
import com.example.demo.openai.OpenAiMessage
import com.example.demo.openai.OpenAiRequest
import io.noties.markwon.Markwon


/**
 * Created by zhaohongshuai on 2023/3/2
 */
class ViewPagerFragmentGpt : Fragment() {

    private val openAiApiClient by lazy {
        OpenAiApiClient()
    }
    private var text: TextView? = null
    private var editText: EditText? = null
    private var submit: TextView? = null
    private var stateButton: TextView? = null
    private var markwon: Markwon? = null
    private var viewModel: MainViewModel? = null

    // 获取 Vibrator 实例
    var vibrator: Vibrator? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vibrator = this.context?.getSystemService(AppCompatActivity.VIBRATOR_SERVICE) as Vibrator

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
        initListener()
        initViewModel()
    }

    private fun initView(v: View) {

        text = v.findViewById(R.id.text)
        editText = v.findViewById(R.id.editText)
        submit = v.findViewById(R.id.submit)
//        stateButton = v.findViewById(R.id.expand)
        markwon = this.context?.let { Markwon.create(it) }
        val spannable = markwon?.toMarkdown("*这里是答案*")
        text?.text = spannable
    }

    private fun initListener() {
        submit?.setOnClickListener {
            vibrator?.vibrate(30)
            if (editText?.text.toString().isEmpty()) {
                Toast.makeText(this.context, "请输入问题", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else {
                sendRequest(editText?.text.toString())
            }
        }
        text?.setOnLongClickListener {
            CommonUtils.copyWithVibrate(this.context, text?.text.toString())
            return@setOnLongClickListener true
        }
//        stateButton?.setOnClickListener {
//            if (stateButton?.text == "展开") {
//                stateButton?.text = "收起"
//                text?.maxLines = 100
//            } else {
//                stateButton?.text = "展开"
//                text?.maxLines = 30
//            }
//        }
    }

    private fun initViewModel() {
        viewModel = activity?.let { ViewModelProvider(it).get(MainViewModel::class.java) }
    }

    private fun sendRequest(message: String) {
        val request = OpenAiRequest(
            model = "gpt-3.5-turbo",
            messages = listOf(OpenAiMessage(role = "user", content = message))
        )
        //apiKey为我的密钥
        openAiApiClient.getCompletion(
            //https://platform.openai.com/account/api-keys 生成地址
            apiKey = viewModel?.requestApiKey
                ?: "Bearer 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",
            request = request,
            onResponse = { response ->
                runOnUiThread {
                    markwon?.toMarkdown(response)?.let {
                        text?.text = it
                        viewModel?.resList?.add(it)
                    }
                }
            },
            onError = { error ->
                Log.e(MainActivity.TAG, error.message, error)
            }
        )

    }


}