package com.example.demo

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter


/**
 * Created by zhaohongshuai on 2023/3/2
 */

internal class ViewPager2FragmentAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            //Chatgpt
            0 -> ViewPagerFragmentGpt()
            else -> ProfileFragment()
            //stable diffusion
            //profile

        }
    }

    override fun getItemCount(): Int {
        return 2
    }
}