package com.example.test.ui.employee_list

import androidx.lifecycle.ViewModel
import com.example.test.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EmployeeListViewModel
@Inject
constructor(private val repository: Repository) : ViewModel() {

    fun getEmployees(specialtyId: Int) = repository.getListOfEmployees(specialtyId)

}