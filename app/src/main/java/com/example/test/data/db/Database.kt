package com.example.test.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        Employee::class, Specialty::class, EmployeeSpecialtyCrossRef::class],
    version = 1,
    exportSchema = true
)
abstract class Database : RoomDatabase() {

    abstract fun employeeDao(): EmployeeDao

    abstract fun specialtyDao(): SpecialtyDao

    abstract fun crossRefDao(): CrossRefDao
}
