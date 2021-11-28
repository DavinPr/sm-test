package com.suitmedia.suitmediatest.ui.home.inputname

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import com.suitmedia.suitmediatest.R
import com.suitmedia.suitmediatest.databinding.FragmentInputNameBinding
import com.suitmedia.suitmediatest.ui.home.HomeViewModel
import com.suitmedia.suitmediatest.ui.home.choice.ChoiceFragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class InputNameFragment : Fragment() {

    private var _binding: FragmentInputNameBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentInputNameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tidtName.doOnTextChanged { text, _, _, _ ->
            binding.btnNext.isEnabled = !text.isNullOrEmpty()
        }

        binding.btnNext.setOnClickListener {
            viewModel.setFragment(ChoiceFragment())
            viewModel.setName(String.format(resources.getString(R.string.name), binding.tidtName.text.toString()))
        }

    }
}