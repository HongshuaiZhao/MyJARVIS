package com.example.demo

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


/**
 * Created by zhaohongshuai on 2023/3/2
 */
class DialogFragmentHistory : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireActivity())
        val inflater = requireActivity().layoutInflater
        val view = inflater.inflate(R.layout.dialog_history_layout, null)
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        val viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = MyAdapter(context, viewModel.resList)
        builder.setView(view)
            .setTitle("历史列表")
            .setPositiveButton("OK", null)
            .setNegativeButton("Cancel", null)
        return builder.create()
    }
}