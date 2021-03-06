package com.suitmedia.suitmediatest.ui.event

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.suitmedia.suitmediatest.R
import com.suitmedia.suitmediatest.databinding.ActivityEventBinding
import com.suitmedia.suitmediatest.ui.home.choice.ChoiceFragment
import com.suitmedia.suitmediatest.utils.toPresentation
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel

class EventActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEventBinding
    private val viewModel by viewModel<EventViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEventBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = getString(R.string.event_actionbar)

        val eventAdapter = EventListAdapter()

        lifecycleScope.launchWhenCreated {
            viewModel.getEvents.collect { list ->
                eventAdapter.setList(list.map { it.toPresentation() })
            }
        }

        binding.rvEvent.apply {
            layoutManager = LinearLayoutManager(this@EventActivity)
            hasFixedSize()
            adapter = eventAdapter
            addItemDecoration(
                DividerItemDecoration(
                    this@EventActivity,
                    DividerItemDecoration.VERTICAL
                )
            )
        }

        eventAdapter.onClick = { name ->
            val intent = Intent()
            intent.putExtra(ChoiceFragment.EVENT_NAME, name)
            setResult(ChoiceFragment.RESULT_FROM_EVENT, intent)
            finish()
        }
    }
}