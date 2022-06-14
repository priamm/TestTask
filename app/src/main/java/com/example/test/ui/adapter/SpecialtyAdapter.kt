package com.example.test.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.test.data.db.Specialty
import com.example.test.databinding.ItemSpecialtyBinding

class SpecialtyAdapter(private val onClick: (Specialty) -> Unit) :
    ListAdapter<Specialty, SpecialtyAdapter.ViewHolder>(SpecialtyDiffCallback) {


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSpecialtyBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding, onClick)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(getItem(position))
    }

    class ViewHolder(private val binding: ItemSpecialtyBinding, val onClick: (Specialty) -> Unit) : RecyclerView.ViewHolder(binding.root) {
        private var currentSpecialty: Specialty? = null

        init {
            itemView.setOnClickListener { currentSpecialty?.let { onClick(it) } }
        }

        fun bind(specialty: Specialty) {
            currentSpecialty = specialty
            with(binding) {
                speciality.text = specialty.name
            }
        }
    }

}

object SpecialtyDiffCallback : DiffUtil.ItemCallback<Specialty>() {
    override fun areItemsTheSame(oldItem: Specialty, newItem: Specialty): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Specialty, newItem: Specialty): Boolean {
        return oldItem.specialtyId == newItem.specialtyId
    }
}