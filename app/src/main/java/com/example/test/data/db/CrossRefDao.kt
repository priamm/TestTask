package com.example.test.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface CrossRefDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(crossRef: List<EmployeeSpecialtyCrossRef>)
}