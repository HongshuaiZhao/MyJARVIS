package com.example.demo

import android.app.Dialog
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


/**
 * Created by zhaohongshuai on 2023/3/2
 */
class DialogFragmentKey : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireActivity())

        val inflater = requireActivity().layoutInflater
        val view = inflater.inflate(R.layout.dialog_key_layout, null)
        val viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        val editText: EditText = view.findViewById(R.id.editText)

        builder.setView(view)
            .setTitle("更新Key")
            .setPositiveButton("OK") { _, _ ->
                viewModel.requestApiKey = editText.text.toString()
                Toast.makeText(context, "更新成功", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Cancel", null)

        return builder.create()
    }
}