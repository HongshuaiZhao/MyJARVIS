package com.example.demo

import android.text.Spanned
import androidx.lifecycle.ViewModel

/**
 * Created by zhaohongshuai on 2023/3/2
 */
class MainViewModel : ViewModel() {
    var requestApiKey = "Bearer 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"
    var resList: MutableList<Spanned> = mutableListOf()
}