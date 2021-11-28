package com.suitmedia.suitmediatest.ui.event

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.suitmedia.suitmediatest.R
import com.suitmedia.suitmediatest.databinding.ActivityEventBinding
import com.suitmedia.suitmediatest.ui.event.eventlist.EventListFragment
import com.suitmedia.suitmediatest.ui.event.eventmap.MapsFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class EventActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEventBinding
    private val viewModel by viewModel<EventViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEventBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        supportFragmentManager.beginTransaction().apply {
            add(
                binding.eventContainer.id,
                EventListFragment(),
                EventListFragment::class.java.simpleName
            )
            commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.event_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_maps -> {
                supportFragmentManager.beginTransaction().apply {
                    replace(
                        binding.eventContainer.id,
                        MapsFragment(),
                        MapsFragment::class.java.simpleName
                    )
                    addToBackStack(null)
                    commit()
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}