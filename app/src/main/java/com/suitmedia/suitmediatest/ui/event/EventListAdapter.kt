package com.suitmedia.suitmediatest.ui.event

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.suitmedia.suitmediatest.databinding.ItemEventBinding
import com.suitmedia.suitmediatest.model.Event
import com.suitmedia.suitmediatest.utils.dateFormat
import com.suitmedia.suitmediatest.utils.loadImage

class EventListAdapter : RecyclerView.Adapter<EventListAdapter.EventListViewHolder>() {

    private val list = ArrayList<Event>()

    fun setList(list: List<Event>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    var onClick: ((String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventListViewHolder =
        EventListViewHolder(
            ItemEventBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: EventListViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = list.size

    inner class EventListViewHolder(private val binding: ItemEventBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Event) {
            binding.eventDate.text = item.date.dateFormat()
            binding.eventName.text = item.name
            Glide.with(itemView.context)
                .load(itemView.context.loadImage(item.image))
                .into(binding.eventImage)
        }

        init {
            itemView.setOnClickListener {
                onClick?.invoke(list[adapterPosition].name)
            }
        }
    }
}