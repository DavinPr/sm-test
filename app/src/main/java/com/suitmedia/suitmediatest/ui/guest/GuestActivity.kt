package com.suitmedia.suitmediatest.ui.guest

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.suitmedia.core.data.Resource
import com.suitmedia.suitmediatest.R
import com.suitmedia.suitmediatest.databinding.ActivityGuestBinding
import com.suitmedia.suitmediatest.ui.home.choice.ChoiceFragment
import com.suitmedia.suitmediatest.utils.toPresentation
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel

class GuestActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGuestBinding
    private val viewModel: GuestViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGuestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = getString(R.string.guest_actionbar)

        val guestAdapter = GuestListAdapter()

        lifecycleScope.launchWhenCreated {
            viewModel.getGuest.collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        setLoadingIndicator(true)
                    }
                    is Resource.Success -> {
                        setLoadingIndicator(false)
                        val data = resource.data
                        if (data != null) {
                            guestAdapter.setList(data.map { it.toPresentation() })
                        }
                    }
                    is Resource.Error -> {
                        setLoadingIndicator(false)
                        binding.textError.apply {
                            visibility = View.VISIBLE
                            text = resource.message
                        }
                    }
                }
            }
        }

        binding.rvGuest.apply {
            layoutManager = GridLayoutManager(this@GuestActivity, 2)
            hasFixedSize()
            adapter = guestAdapter
        }

        guestAdapter.onClick = { guest ->
            val date = guest.birthdate.split("-")[2]
            val intent = Intent()
            intent.putExtra(ChoiceFragment.GUEST_NAME, guest.name)
            intent.putExtra(ChoiceFragment.GUEST_DATE, date.toInt())
            setResult(ChoiceFragment.RESULT_FROM_GUEST, intent)
            finish()
        }
    }

    private fun setLoadingIndicator(state: Boolean) {
        binding.guestProgressbar.isVisible = state
    }
}