package com.suitmedia.suitmediatest.ui.guest

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import com.suitmedia.core.data.Resource
import com.suitmedia.suitmediatest.R
import com.suitmedia.suitmediatest.databinding.ActivityGuestBinding
import com.suitmedia.suitmediatest.ui.home.choice.ChoiceFragment
import com.suitmedia.suitmediatest.utils.toPresentation
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

        viewModel.requestGuest()
        viewModel.getGuest.observe(this, { resource ->
            when (resource) {
                is Resource.Loading -> {
                    setLoadingIndicator(true)
                    guestAdapter.setList(listOf())
                    binding.textError.visibility = View.GONE
                }
                is Resource.Success -> {
                    setLoadingIndicator(false)
                    val data = resource.data
                    if (data != null) {
                        binding.textError.visibility = View.GONE
                        guestAdapter.setList(data.map { it.toPresentation() })
                    }
                }
                is Resource.Error -> {
                    setLoadingIndicator(false)
                    guestAdapter.setList(listOf())
                    binding.textError.apply {
                        visibility = View.VISIBLE
                        text = resource.message
                    }
                }
            }
        })
        binding.rvGuest.apply {
            layoutManager = GridLayoutManager(this@GuestActivity, 2)
            hasFixedSize()
            adapter = guestAdapter
        }

        guestAdapter.onClick = { guest ->
            val split = guest.birthdate.split("-")
            val date = split[2]
            val month = split[1]
            val intent = Intent()
            intent.putExtra(ChoiceFragment.GUEST_NAME, guest.name)
            intent.putExtra(ChoiceFragment.GUEST_DATE, date.toInt())
            intent.putExtra(ChoiceFragment.GUEST_MONTH, month.toInt())
            setResult(ChoiceFragment.RESULT_FROM_GUEST, intent)
            finish()
        }

        binding.refresh.setOnRefreshListener {
            viewModel.requestGuest()
            binding.refresh.isRefreshing = false
        }
    }

    private fun setLoadingIndicator(state: Boolean) {
        binding.guestProgressbar.isVisible = state
    }
}