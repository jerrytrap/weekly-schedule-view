package com.jerrytrap.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jerrytrap.weeklyscheduleview.WeeklyScheduleView
import java.time.LocalDateTime

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val weeklyScheduleView: WeeklyScheduleView = findViewById(R.id.weeklyScheduleView)

        weeklyScheduleView.eventList = listOf(
            LocalDateTime.of(2024, 6, 3, 13, 0, 0),
            LocalDateTime.of(2024, 6, 3, 14, 0, 0),
            LocalDateTime.of(2024, 6, 4, 16, 0, 0),
            LocalDateTime.of(2024, 6, 4, 17, 0, 0),
            LocalDateTime.of(2024, 6, 4, 18, 0, 0),
            LocalDateTime.of(2024, 6, 5, 14, 0, 0),
            LocalDateTime.of(2024, 6, 5, 20, 0, 0),
            LocalDateTime.of(2024, 6, 5, 21, 0, 0),
            LocalDateTime.of(2024, 6, 7, 10, 0, 0),
            LocalDateTime.of(2024, 6, 7, 11, 0, 0),
            LocalDateTime.of(2024, 6, 7, 14, 0, 0),
            LocalDateTime.of(2024, 6, 8, 15, 0, 0),
            LocalDateTime.of(2024, 6, 8, 16, 0, 0),
        )
    }
}