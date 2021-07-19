package com.suitmedia.suitmediatest.ui.home.inputname

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.suitmedia.suitmediatest.R
import com.suitmedia.suitmediatest.databinding.FragmentInputNameBinding
import com.suitmedia.suitmediatest.ui.home.choice.ChoiceFragment


class InputNameFragment : Fragment() {

    private var _binding : FragmentInputNameBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        activity?.title = getString(R.string.input_name_actionbar)

        _binding = FragmentInputNameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnNext.setOnClickListener {
            val targetFragment = ChoiceFragment().apply {
                val bundle = Bundle().apply {
                    putString(ChoiceFragment.NAME, binding.tidtName.text.toString())
                }
                arguments = bundle
            }
            val mFragmentManager = activity?.supportFragmentManager

            mFragmentManager?.beginTransaction()?.apply {
                replace(R.id.home_container, targetFragment, ChoiceFragment::class.java.simpleName)
                addToBackStack(null)
                commit()
            }
        }

    }
}