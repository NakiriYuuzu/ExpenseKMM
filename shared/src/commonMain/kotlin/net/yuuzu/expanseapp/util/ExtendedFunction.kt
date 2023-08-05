package net.yuuzu.expanseapp.util

import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.number
import kotlinx.datetime.plus
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime

object DateTimeCalculation {
    private val timeZone = TimeZone.currentSystemDefault()
    private val todayDateTime = Clock.System.now().toLocalDateTime(timeZone)
    private val today = todayDateTime.date
    private val time = todayDateTime.time

    // week Calculation
    private val firstDayOfWeekDate = today.minus(today.dayOfWeek.ordinal.toLong(), DateTimeUnit.DAY)
    private val lastDayOfWeekDate = firstDayOfWeekDate.plus(6, DateTimeUnit.DAY)

    // month Calculation
    private val firstDayOfMonthDate = LocalDate(today.year, today.month, 1)
    private val lastDayOfMonthDate = if (today.month.number < 12) {
        LocalDate(today.year, today.month.number + 1, 1).minus(1, DateTimeUnit.DAY)
    } else {
        LocalDate(today.year + 1, 1, 1).minus(1, DateTimeUnit.DAY)
    }

    val firstDayOfWeek = LocalDateTime(firstDayOfWeekDate, time)
    val lastDayOfWeek = LocalDateTime(lastDayOfWeekDate, time)
    val firstDayOfMonth = LocalDateTime(firstDayOfMonthDate, time)
    val lastDayOfMonth = LocalDateTime(lastDayOfMonthDate, time)

    val firstDayOfWeekEpoch = firstDayOfWeek.toInstant(timeZone).toEpochMilliseconds()
    val lastDayOfWeekEpoch = lastDayOfWeek.toInstant(timeZone).toEpochMilliseconds()
    val firstDayOfMonthEpoch = firstDayOfMonth.toInstant(timeZone).toEpochMilliseconds()
    val lastDayOfMonthEpoch = lastDayOfMonth.toInstant(timeZone).toEpochMilliseconds()
}