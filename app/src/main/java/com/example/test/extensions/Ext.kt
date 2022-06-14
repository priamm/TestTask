package com.example.test.extensions

import com.example.test.data.db.Employee
import com.example.test.data.db.EmployeeSpecialtyCrossRef
import com.example.test.data.db.Specialty
import com.example.test.data.network.model.NetworkResponse
import com.example.test.data.network.model.Response
import com.example.test.data.network.model.SpecialtyResponse
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.util.*

fun String.lowerCase() = lowercase(Locale.ROOT).replaceFirstChar {
    if (it.isLowerCase()) it.titlecase(
        Locale.ROOT
    ) else it.toString()
}

fun Response.toEmployee() = Employee(
    employeeId = this.hashCode(),
    avatar = this.avatar,
    birthday = this.birthday,
    firstName = this.firstName,
    lastName = this.lastName,
)

fun SpecialtyResponse.toSpecialty() = Specialty(
    name = this.name,
    specialtyId = this.specialtyId,
)

fun Disposable.addTo(compositeDisposable: CompositeDisposable) {
    compositeDisposable.add(this)
}

fun NetworkResponse.toEmployeeSpecialtyCrossRef() =
    this.response.map { list -> Pair(list.toEmployee(), list.specialty.map { it.toSpecialty() }) }
        .map { pair ->
            pair.second.map { specialty ->
                EmployeeSpecialtyCrossRef(
                    pair.first.employeeId,
                    specialty.specialtyId
                )
            }
        }.flatten()