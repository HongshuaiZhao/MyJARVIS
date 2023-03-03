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