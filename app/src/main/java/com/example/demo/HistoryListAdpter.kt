package com.example.demo

import android.content.Context
import android.text.Spanned
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


/**
 * Created by zhaohongshuai on 2023/3/2
 */
class MyAdapter(context: Context?, data: MutableList<Spanned>) :
    RecyclerView.Adapter<MyAdapter.ViewHolder?>() {
    private val mData: MutableList<Spanned>
    private val mInflater: LayoutInflater
    private var mListener: OnItemClickListener? = null
    private fun setOnItemClickListener(listener: OnItemClickListener?) {
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = mInflater.inflate(R.layout.item_layout, parent, false)

        return ViewHolder(view).apply {
            setOnItemClickListener(object : OnItemClickListener {
                override fun onItemClick(position: Int) {
                    Log.d(TAG, "onItemClick: $position")
                }
            })
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mData[position]
        holder.mTextView.text = item
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnLongClickListener {
        val mTextView: TextView

        init {
            mTextView = itemView.findViewById(R.id.text_view)
            itemView.setOnLongClickListener(this)
        }

        override fun onLongClick(v: View?): Boolean {
            v?.context?.let { CommonUtils.copyWithVibrate(it, mTextView.text.toString()) }
            return true

        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    init {
        mData = data
        mInflater = LayoutInflater.from(context)
    }

    companion object {
        const val TAG = "zhaohongshuai"
    }
}
