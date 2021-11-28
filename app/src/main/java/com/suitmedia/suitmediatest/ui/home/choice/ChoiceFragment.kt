package com.suitmedia.suitmediatest.ui.home.choice

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.suitmedia.suitmediatest.R
import com.suitmedia.suitmediatest.databinding.FragmentChoiceBinding
import com.suitmedia.suitmediatest.ui.event.EventActivity
import com.suitmedia.suitmediatest.ui.guest.GuestActivity
import com.suitmedia.suitmediatest.ui.home.HomeViewModel
import com.suitmedia.suitmediatest.utils.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ChoiceFragment : Fragment() {

    private var _binding: FragmentChoiceBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by sharedViewModel()
    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            val intent = result.data
            when (result.resultCode) {
                RESULT_FROM_GUEST -> {
                    val name = intent?.getStringExtra(GUEST_NAME)
                    val date = intent?.getIntExtra(GUEST_DATE, 0)
                    val month = intent?.getIntExtra(GUEST_MONTH, 0)
                    viewModel.setBtnGuestText(name ?: "Pilih guest")
                    viewModel.setDateResult(date ?: 0, month ?: 0)
                }
                RESULT_FROM_EVENT -> {
                    val name = intent?.getStringExtra(EVENT_NAME)
                    viewModel.setBtnEventText(name ?: "Pilih event")
                }
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentChoiceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observer()

        binding.btnGuest.setOnClickListener {
            val intent = Intent(activity, GuestActivity::class.java)
            resultLauncher.launch(intent)
        }
        binding.btnEvent.setOnClickListener {
            val intent = Intent(activity, EventActivity::class.java)
            resultLauncher.launch(intent)
        }
    }

    private fun showAlert(message: String) {
        AlertDialog.Builder(requireContext())
            .setPositiveButton("Ok") { dialogInterface, _ ->
                dialogInterface.dismiss()
            }
            .setMessage(message)
            .show()
    }

    private fun observer() {

        lifecycleScope.launch {
            viewModel.isPalindrome.collectLatest {
                showAlert(it)
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.name.collectLatest {
                binding.textName.text = String.format(resources.getString(R.string.name), it)
            }
        }

        lifecycleScope.launch {
            viewModel.dateResult.collectLatest {
                showAlert(it)
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.btnEvent.collectLatest {
                binding.btnEvent.text = it
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.btnGuest.collectLatest {
                binding.btnGuest.text = it
            }
        }
    }
}