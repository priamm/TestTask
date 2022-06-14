package com.example.test.ui.employee

import androidx.lifecycle.ViewModel
import com.example.test.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EmployeeViewModel
@Inject
constructor(private val repository: Repository): ViewModel() {

    fun getEmployee(employeeId: Int) = repository.getEmployee(employeeId)

    fun getSpecialties(employeeId: Int) = repository.getEmployeeSpecialty(employeeId)

}