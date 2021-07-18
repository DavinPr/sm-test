package com.suitmedia.suitmediatest.ui.home.choice

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.suitmedia.suitmediatest.R
import com.suitmedia.suitmediatest.databinding.FragmentChoiceBinding
import com.suitmedia.suitmediatest.ui.event.EventActivity
import com.suitmedia.suitmediatest.ui.guest.GuestActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChoiceFragment : Fragment() {

    private var _binding: FragmentChoiceBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModel<ChoiceViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        activity?.title = getString(R.string.choice_actionbar)

        _binding = FragmentChoiceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val name = arguments?.getString(NAME)

        if (name != null) {
            viewModel.palindromeCheck(name)
        }

        viewModel.isPalindrome.observe(viewLifecycleOwner, { result ->
            showAlert(result)
        })

        val textName = "Nama\t\t: $name"
        binding.textName.text = textName

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
            .setPositiveButton("Ok") { dialog, _ ->
                dialog.dismiss()
            }
            .setMessage(message)
            .show()
    }

    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            val intent = result.data
            when (result.resultCode) {
                RESULT_FROM_GUEST -> {
                    val name = intent?.getStringExtra(GUEST_NAME)
                    val date = intent?.getIntExtra(GUEST_DATE, 0)
                    val month = intent?.getIntExtra(GUEST_MONTH, 0)
                    binding.btnGuest.text = name

                    val toastByDate = viewModel.toastByDate(date ?: 0)
                    val isPrime = viewModel.isPrime(month ?: 0)
                    showAlert(
                        """$toastByDate
                        |$isPrime
                    """.trimMargin()
                    )
                }
                RESULT_FROM_EVENT -> {
                    val name = intent?.getStringExtra(EVENT_NAME)
                    binding.btnEvent.text = name
                }
            }
        }

    companion object {
        const val NAME = "name_key"
        const val GUEST_NAME = "guest_name_key"
        const val GUEST_DATE = "guest_date_key"
        const val GUEST_MONTH = "guest_month_key"
        const val EVENT_NAME = "event_name_key"
        const val RESULT_FROM_GUEST = 100
        const val RESULT_FROM_EVENT = 200
    }
}