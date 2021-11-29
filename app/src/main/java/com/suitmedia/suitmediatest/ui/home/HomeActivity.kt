package com.suitmedia.suitmediatest.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.suitmedia.suitmediatest.databinding.ActivityHomeBinding
import com.suitmedia.suitmediatest.ui.home.inputname.InputNameFragment
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val viewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launchWhenStarted {
            viewModel.fragment.collect {
                if (it == null) {
                    viewModel.setFragment(InputNameFragment())
                } else {
                    val tag = it::class.java.simpleName
                    if (tag != viewModel.fragmentTag.value) {
                        viewModel.setFragmentTag(tag)
                        supportFragmentManager.beginTransaction().apply {
                            replace(
                                binding.homeContainer.id,
                                it,
                                tag
                            )
                            commit()
                        }
                    }
                }
            }
        }
    }
}