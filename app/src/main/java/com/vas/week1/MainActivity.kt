package com.vas.week1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.vas.week1.databinding.ActivityMainBinding
import com.vas.week1.fragments.BroadcastReceiverFragment
import com.vas.week1.fragments.ContentProviderFragment
import com.vas.week1.fragments.ServiceFragment

/**
 * Код Activity.
 * Данная активность используется для навигации между тремя фрагментами с помощью
 * BottomNavigationView.
 * Activity служит точкой входа для взаимодействия приложения с пользователем,
 * а также отвечает за то, как пользователь перемещается внутри приложения или между приложениями.
 */

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val broadcastReceiverFragment = BroadcastReceiverFragment()
        val contentProviderFragment = ContentProviderFragment()
        val serviceFragment = ServiceFragment()

        setCurrentFragment(serviceFragment)

        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.service_menu->setCurrentFragment(serviceFragment)
                R.id.broadcast_receiver_menu->setCurrentFragment(broadcastReceiverFragment)
                R.id.content_provider_menu->setCurrentFragment(contentProviderFragment)
            }
            true
        }
    }

    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment,fragment)
            commit()
        }
}