package com.example.test.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.test.R
import com.example.test.data.db.Employee
import com.example.test.databinding.ItemEmployeeBinding
import com.example.test.extensions.lowerCase
import com.example.test.util.getAge

class EmployeeAdapter(private val onClick: (Employee) -> Unit) :
    ListAdapter<Employee, EmployeeAdapter.ViewHolder>(EmployeeDiffCallback) {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemEmployeeBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding, onClick)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(getItem(position))
    }

    class ViewHolder(private val binding : ItemEmployeeBinding, val onClick: (Employee) -> Unit) : RecyclerView.ViewHolder(binding.root) {
        private var currentEmployee: Employee? = null

        init {
            itemView.setOnClickListener { currentEmployee?.let { onClick(it) } }
        }

        fun bind(employee: Employee) {
            currentEmployee = employee
            with(binding) {
                firstName.text =
                    itemView.resources.getString(
                        R.string.first_name,
                        employee.firstName?.lowerCase()
                    )
                lastName.text =
                    itemView.resources.getString(
                        R.string.last_name,
                        employee.lastName?.lowerCase()
                    )
                age.text =
                    if (!employee.birthday.isNullOrBlank()) itemView.resources.getString(
                        R.string.age,
                        getAge(employee.birthday)
                    ) else "--"
            }
        }
    }
}

object EmployeeDiffCallback : DiffUtil.ItemCallback<Employee>() {
    override fun areItemsTheSame(oldItem: Employee, newItem: Employee): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Employee, newItem: Employee): Boolean {
        return oldItem.employeeId == newItem.employeeId
    }
}