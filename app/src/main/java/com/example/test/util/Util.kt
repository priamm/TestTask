package com.example.test.util

import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter

fun formatDate(date: String): String {
    val birthday = getLocalDate(date)
    val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy г.")
    return birthday.format(formatter)
}

fun getLocalDate(date: String): LocalDate {
    return if (date.substringBefore("-").length > 2)
        LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
    else LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"))
}

fun getAge(date: String): String {
    return Period.between(getLocalDate(date), LocalDate.now()).years.toString()
}