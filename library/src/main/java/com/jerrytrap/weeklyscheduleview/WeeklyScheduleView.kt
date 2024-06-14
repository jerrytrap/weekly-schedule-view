package com.jerrytrap.weeklyscheduleview

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

class WeeklyScheduleView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : ConstraintLayout(context, attrs) {
    private val previousButton: ImageView
    private val nextButton: ImageView
    private val yearAndMonth: TextView
    val timeTable: WeeklyTimeTable

    init {
        inflate(context, R.layout.weekly_schedule, this)
        previousButton = findViewById(R.id.imageView_previous)
        nextButton = findViewById(R.id.imageView_next)
        yearAndMonth = findViewById(R.id.textView_yearAndMonth)
        timeTable = findViewById(R.id.weeklyTimeTable)

        changeYear()

        previousButton.setOnClickListener {
            timeTable.startDay = timeTable.startDay.minusDays(7)
            changeYear()
        }

        nextButton.setOnClickListener {
            timeTable.startDay = timeTable.startDay.plusDays(7)
            changeYear()
        }
    }

    private fun changeYear() {
        yearAndMonth.text = resources.getString(R.string.year, timeTable.startDay.year.toString())
    }
}