package com.jerrytrap.weeklyscheduleview

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import java.time.LocalDate
import java.time.LocalDateTime

class WeeklyScheduleView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : ConstraintLayout(context, attrs) {
    private val today = LocalDate.now()
    private var startDay = today.minusDays(today.dayOfWeek.ordinal.toLong())
    var eventList: List<LocalDateTime> = emptyList()
        set(value) {
            field = value
            weeklyTimeTable.eventList = value
        }
    private val previousButton: ImageView
    private val nextButton: ImageView
    private val yearAndMonth: TextView
    private val weeklyTimeTable: WeeklyTimeTable

    init {
        inflate(context, R.layout.weekly_schedule, this)
        previousButton = findViewById(R.id.imageView_previous)
        nextButton = findViewById(R.id.imageView_next)
        yearAndMonth = findViewById(R.id.textView_yearAndMonth)
        weeklyTimeTable = findViewById(R.id.weeklyTimeTable)

        changeYear()
        setWeek()

        previousButton.setOnClickListener {
            startDay = startDay.minusDays(7)
            setWeek()
            changeYear()
        }

        nextButton.setOnClickListener {
            startDay = startDay.plusDays(7)
            setWeek()
            changeYear()
        }
    }

    private fun changeYear() {
        yearAndMonth.text = resources.getString(R.string.year, startDay.year.toString())
        println(resources.getString(R.string.year, startDay.year.toString()))
    }

    private fun setWeek() {
        weeklyTimeTable.setStartDay(startDay)
    }
}