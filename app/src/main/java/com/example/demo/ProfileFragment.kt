package com.example.demo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demo.databinding.FragmentProfileBinding

/**
 * Created by zhaohongshuai on 2023/3/3
 * todo 先上班
 */
class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: MyAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

        // 设置 RecyclerView 的布局和数据源
        binding.optionsRecyclerView.layoutManager = LinearLayoutManager(activity)
        adapter = MyAdapter(context, viewModel.resList)
        binding.optionsRecyclerView.adapter = adapter

        // 上传头像的点击事件
        binding.avatar.setOnClickListener {
            // 调用上传头像的方法
            uploadAvatar()
        }

        // 退出登录的点击事件
        binding.logoutButton.setOnClickListener {
            // 调用退出登录的方法
            logout()
        }
    }

    private fun uploadAvatar() {
        // 实现上传头像的逻辑
        // ...
    }

    private fun logout() {
        // 实现退出登录的逻辑
        // ...
    }
}
