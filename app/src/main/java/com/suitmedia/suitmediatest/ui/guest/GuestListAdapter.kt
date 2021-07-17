package com.suitmedia.suitmediatest.ui.guest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.suitmedia.suitmediatest.databinding.ItemGuestBinding
import com.suitmedia.suitmediatest.model.Guest

class GuestListAdapter : RecyclerView.Adapter<GuestListAdapter.GuestListViewHolder>() {

    private val list = ArrayList<Guest>()

    fun setList(list: List<Guest>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    var onClick : ((Guest) -> Unit)? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GuestListAdapter.GuestListViewHolder =
        GuestListViewHolder(
            ItemGuestBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: GuestListAdapter.GuestListViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = list.size

    inner class GuestListViewHolder(private val binding: ItemGuestBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Guest) {
            binding.guestName.text = item.name
            Glide.with(itemView.context)
                .load(item.image)
                .into(binding.guestImage)
        }
        init {
            itemView.setOnClickListener {
                onClick?.invoke(list[adapterPosition])
            }
        }
    }
}