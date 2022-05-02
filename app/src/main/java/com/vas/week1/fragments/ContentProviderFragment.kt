package com.vas.week1.fragments

import android.annotation.SuppressLint
import android.content.ContentValues
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vas.week1.MyContentProvider
import com.vas.week1.R
import com.vas.week1.databinding.FragmentContentProviderBinding
import com.vas.week1.databinding.FragmentServiceBinding

/**
 * Код ContentProviderFragment.
 * Данный фрагмент ознакамливает нас с функционалом ContentProvider.
 * ContentProvider - механизм, позволяющий делиться своими данными другому приложению
 * (к примеру список контактов из телефонной книги).
 */

class ContentProviderFragment : Fragment() {

    private var binding: FragmentContentProviderBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentContentProviderBinding.inflate(inflater, container, false)

        binding?.insertButton?.setOnClickListener {
            addData()
        }

        binding?.loadButton?.setOnClickListener {
            getData()
        }

        return binding?.root
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    //метод для вставки данных
    fun addData(){
        //объект класса для добавления значений в БД
        val values = ContentValues()

        //извлечение текста
        values.put(MyContentProvider.nick, binding?.editTextNick?.text.toString())

        //вставка в БД
        activity?.contentResolver?.insert(MyContentProvider.CONTENT_URI, values)
    }

    //метод для получения данных
    @SuppressLint("Range")
    fun getData(){
        // получение курсора с данными из БД
        val cursor = activity?.contentResolver?.query(
            Uri.parse("content://com.vas.week1/nicknames"),
            null, null, null, null)

        val strBuild = StringBuilder()
        while (cursor?.moveToNext() == true) {
            strBuild.append(cursor.getString(cursor.getColumnIndex("nick")) + "\n")
        }
        cursor?.close()
        binding?.textViewData?.text = strBuild
    }

}