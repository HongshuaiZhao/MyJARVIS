package com.example.demo.openai


import com.google.gson.annotations.SerializedName

/**
 * Created by zhaohongshuai on 2023/3/2
 */

//{
//    "id": "chatcmpl-6pXu1tj2VvJJFjVWRYVnf7xxd8wvp",
//    "object": "chat.completion",
//    "created": 1677743069,
//    "model": "gpt-3.5-turbo-0301",
//    "usage": {
//        "prompt_tokens": 14,
//        "completion_tokens": 105,
//        "total_tokens": 119
//    },
//    "choices": [
//        {
//            "message": {
//                "role": "assistant",
//                "content": "\n\nAs an AI language model, I don't have my own mission. However, the mission of OpenAI is to create and promote friendly artificial intelligence that benefits humanity as a whole. Their main goal is to ensure that the development and deployment of advanced AI systems are safe, beneficial, and aligned with human values. They also want to advance the field of artificial intelligence through research and dissemination of knowledge. OpenAI aims to foster collaboration among experts in AI and related fields to solve some of the most pressing problems facing humanity."
//            },
//            "finish_reason": "stop",
//            "index": 0
//        }
//    ]
//}
//生成对应data class
data class OpenAiResponse(
    @SerializedName("id")
    val id: String,

    @SerializedName("object")
    val _object: String,

    @SerializedName("created")
    val created: Long,

    @SerializedName("choices")
    val choices: List<OpenAiChoice>
)

//{
//            "message": {
//                "role": "assistant",
//                "content": "\n\nAs an AI language model, I don't have my own mission. However, the mission of OpenAI is to create and promote friendly artificial intelligence that benefits humanity as a whole. Their main goal is to ensure that the development and deployment of advanced AI systems are safe, beneficial, and aligned with human values. They also want to advance the field of artificial intelligence through research and dissemination of knowledge. OpenAI aims to foster collaboration among experts in AI and related fields to solve some of the most pressing problems facing humanity."
//            },
//            "finish_reason": "stop",
//            "index": 0
//        }
data class OpenAiMessage(
    @SerializedName("role")
    val role: String,
    @SerializedName("content")
    val content: String
)

//    "choices": [
//        {
//            "message": {
//                "role": "assistant",
//                "content": "\n\nAs an AI language model, I don't have my own mission. However, the mission of OpenAI is to create and promote friendly artificial intelligence that benefits humanity as a whole. Their main goal is to ensure that the development and deployment of advanced AI systems are safe, beneficial, and aligned with human values. They also want to advance the field of artificial intelligence through research and dissemination of knowledge. OpenAI aims to foster collaboration among experts in AI and related fields to solve some of the most pressing problems facing humanity."
//            },
//            "finish_reason": "stop",
//            "index": 0
//        }
//    ]
data class OpenAiChoice(
    @SerializedName("message")
    val message: OpenAiMessage,
    @SerializedName("finish_reason")
    val finishReason: String,
    @SerializedName("index")
    val index: Int
)

data class OpenAiRequest(
    @SerializedName("model")
    val model: String,
    @SerializedName("messages")
    val messages: List<OpenAiMessage>
)


//data class OpenAiChoice(
//    @SerializedName("text")
//    val text: String,
//    @SerializedName("index")
//    val index: Int,
//    @SerializedName("logprobs")
//    val logprobs: Any?,
//    @SerializedName("finish_reason")
//    val finishReason: String?,
//    @SerializedName("selected_text")
//    val selectedText: String?
//)
