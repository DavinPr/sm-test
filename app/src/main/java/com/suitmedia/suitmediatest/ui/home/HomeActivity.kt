package com.suitmedia.suitmediatest.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.suitmedia.suitmediatest.databinding.ActivityHomeBinding
import com.suitmedia.suitmediatest.ui.home.inputname.InputNameFragment

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().apply {
            add(
                binding.homeContainer.id,
                InputNameFragment(),
                InputNameFragment::class.java.simpleName
            )
            commit()
        }

    }
}