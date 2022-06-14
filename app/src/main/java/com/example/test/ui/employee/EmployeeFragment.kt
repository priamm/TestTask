package com.example.test.ui.employee

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.test.R
import com.example.test.data.db.Employee
import com.example.test.data.db.Specialty
import com.example.test.databinding.EmployeeFragmentBinding
import com.example.test.extensions.lowerCase
import com.example.test.ui.adapter.SpecialtyAdapter
import com.example.test.util.formatDate
import com.example.test.util.getAge
import com.example.test.util.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EmployeeFragment : Fragment(R.layout.employee_fragment) {

    private val args: EmployeeFragmentArgs by navArgs()

    private val viewModel: EmployeeViewModel by viewModels()

    private val binding by viewBinding(EmployeeFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getEmployee(args.employeeId)
            .observe(
                this.viewLifecycleOwner
            ) { initUi(it) }
        viewModel.getSpecialties(args.employeeId)
            .observe(
                this.viewLifecycleOwner
            ) { list -> if (list.isNotEmpty()) initList(list) }
    }

    private fun initUi(employee: Employee) {
        with(binding){
            firstName.text = resources.getString(
                R.string.first_name, employee.firstName?.lowerCase()
            )
            lastName.text = resources.getString(
                R.string.last_name, employee.lastName?.lowerCase()
            )
            birthday.text = if (!employee.birthday.isNullOrBlank()) resources.getString(
                R.string.birthday,
                formatDate(employee.birthday)
            ) else "--"
            age.text = if (!employee.birthday.isNullOrBlank()) resources.getString(
                R.string.age,
                getAge(employee.birthday)
            ) else "--"
            specialty.text = resources.getString(
                R.string.specialty
            )
        }
    }

    private fun initList(specialties: List<Specialty>) {
        val adapter = SpecialtyAdapter(::onClick)
        adapter.submitList(specialties)
        binding.specialtyList.adapter = adapter
    }

    private fun onClick(specialty: Specialty) {}
}