package com.suitmedia.suitmediatest.ui.event.eventmap

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.suitmedia.suitmediatest.databinding.ItemEventHorizontalBinding
import com.suitmedia.suitmediatest.model.Event
import com.suitmedia.suitmediatest.utils.FormatHelper

class EventListMapAdapter : RecyclerView.Adapter<EventListMapAdapter.EventListMapViewHolder>() {

    private val list = ArrayList<Event>()

    fun setList(list: List<Event>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    val getList get() = list

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EventListMapAdapter.EventListMapViewHolder =
        EventListMapViewHolder(
            ItemEventHorizontalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(
        holder: EventListMapAdapter.EventListMapViewHolder,
        position: Int
    ) {
        val item = list[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = list.size

    inner class EventListMapViewHolder(private val binding: ItemEventHorizontalBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Event) {
            binding.eventName.text = item.name
            Glide.with(itemView.context)
                .load(FormatHelper.loadImage(itemView.context, item.image))
                .into(binding.eventImage)
        }
    }
}