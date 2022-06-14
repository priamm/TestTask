package com.example.test.repository

import com.example.test.data.db.CrossRefDao
import com.example.test.data.db.EmployeeDao
import com.example.test.data.db.SpecialtyDao
import com.example.test.data.network.GitlabApi
import com.example.test.data.network.model.NetworkResponse
import com.example.test.extensions.toEmployee
import com.example.test.extensions.toEmployeeSpecialtyCrossRef
import com.example.test.extensions.toSpecialty
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class Repository
@Inject constructor(
    private val api: GitlabApi,
    private val employeeDao: EmployeeDao,
    private val specialtyDao: SpecialtyDao,
    private val crossRefDao: CrossRefDao
) {
    fun getListOfSpecialties() = specialtyDao.getListOfSpecialty()
    fun getListOfEmployees(specialtyId: Int) = employeeDao.getListOfEmployee(specialtyId)
    fun getEmployee(employeeId: Int) = employeeDao.getEmployee(employeeId)
    fun getEmployeeSpecialty(employeeId: Int) = specialtyDao.getEmployeeSpecialty(employeeId)

    fun refresh(): Observable<Unit> =
        api.getEmployees()
            .flatMap { fillDatabase(it) }.subscribeOn(Schedulers.io())

    private fun fillDatabase(specialityDataDto: NetworkResponse) = Observable.fromCallable {
        employeeDao.insert(specialityDataDto.response.map { it.toEmployee() })
        specialtyDao.insert(specialityDataDto.response.map { response -> response.specialty.map { it.toSpecialty() } }
            .flatten())
        crossRefDao.insert(specialityDataDto.toEmployeeSpecialtyCrossRef())
    }
}