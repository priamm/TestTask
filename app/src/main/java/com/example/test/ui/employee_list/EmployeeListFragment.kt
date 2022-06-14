package com.example.test.ui.employee_list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.test.R
import com.example.test.data.db.Employee
import com.example.test.databinding.EmployeeListFragmentBinding
import com.example.test.ui.adapter.EmployeeAdapter
import com.example.test.util.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EmployeeListFragment : Fragment(R.layout.employee_list_fragment) {

    private val args: EmployeeListFragmentArgs by navArgs()

    private val viewModel: EmployeeListViewModel by viewModels()

    private val binding by viewBinding(EmployeeListFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getEmployees(args.specialtyId)
            .observe(
                this.viewLifecycleOwner
            ) { list -> if (list.isNotEmpty()) initList(list) }
    }

    private fun initList(employees: List<Employee>) {
        val adapter = EmployeeAdapter(::onClick)
        adapter.submitList(employees)
        binding.employeeList.adapter = adapter
    }

    private fun onClick(employee: Employee){
        val action = EmployeeListFragmentDirections.employee(employee.employeeId)
        findNavController().navigate(action)
    }

}