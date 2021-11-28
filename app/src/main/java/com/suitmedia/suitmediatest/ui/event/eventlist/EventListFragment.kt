package com.suitmedia.suitmediatest.ui.event.eventlist

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.suitmedia.suitmediatest.databinding.FragmentEventListBinding
import com.suitmedia.suitmediatest.ui.event.EventViewModel
import com.suitmedia.suitmediatest.utils.EVENT_NAME
import com.suitmedia.suitmediatest.utils.RESULT_FROM_EVENT
import com.suitmedia.suitmediatest.utils.toPresentation
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel


class EventListFragment : Fragment() {

    private var _binding: FragmentEventListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: EventViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentEventListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val eventAdapter = EventListAdapter()

        lifecycleScope.launchWhenCreated {
            viewModel.getEvents.collect { list ->
                eventAdapter.setList(list.map { it.toPresentation() })
            }
        }

        binding.rvEvent.apply {
            layoutManager = LinearLayoutManager(requireContext())
            hasFixedSize()
            adapter = eventAdapter
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
        }

        eventAdapter.onClick = { name ->
            val intent = Intent()
            intent.putExtra(EVENT_NAME, name)
            activity?.setResult(RESULT_FROM_EVENT, intent)
            activity?.finish()
        }
    }
}