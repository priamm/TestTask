package com.example.test.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SpecialtyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(specialty: List<Specialty>)

    @Query("SELECT * FROM specialty ORDER BY name ASC")
    fun getListOfSpecialty(): LiveData<List<Specialty>>

    @Query("SELECT * FROM specialty WHERE specialty_id IN (SELECT specialty_id FROM employee_specialty_cross_ref WHERE employee_id = :employeeId)")
    fun getEmployeeSpecialty(employeeId: Int): LiveData<List<Specialty>>
}