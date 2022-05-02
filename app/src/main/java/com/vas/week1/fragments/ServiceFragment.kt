package com.vas.week1.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vas.week1.services.AudioService
import com.vas.week1.R
import com.vas.week1.databinding.FragmentServiceBinding

/**
 * Код ServiceFragment.
 * Данный фрагмент ознакамливает нас с функционалом Service.
 * Сервисы используются для выполнения фоновых задач (прослушивание музыки, скачивание файлов и тд.).
 * Используются во многих приложениях (ЯндексМузыка, ВК, PlayMarket).
 */

class ServiceFragment : Fragment() {

    private var binding: FragmentServiceBinding? = null
    private var playerFlag = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentServiceBinding.inflate(inflater, container, false)

        binding?.imageButtonAudio?.setOnClickListener {

            playerFlag = if (playerFlag){
                // запускаем наш сервис
                context?.startService(Intent(context, AudioService::class.java))

                binding?.imageButtonAudio?.setImageResource(R.drawable.ic_stop)
                binding?.imageViewAudio?.setImageResource(R.drawable.brawl)
                false
            } else {
                // закрываем наш сервис
                context?.stopService(Intent(context, AudioService::class.java))

                binding?.imageButtonAudio?.setImageResource(R.drawable.ic_play)
                binding?.imageViewAudio?.setImageResource(R.drawable.brawl_bw)
                true
            }

        }

        return binding?.root
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}