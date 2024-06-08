package com.jerrytrap.weeklyscheduleview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.annotation.StringRes
import java.time.LocalDate
import java.time.LocalDateTime

internal class WeeklyTimeTable @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : View(context, attrs) {
    private val paint = Paint()
    private val columns = 8
    private var rows = 15

    private val cellWidth
        get() = (width / columns).toFloat()
    private val cellHeight
        get() = (height / rows).toFloat()

    private var startDay = LocalDate.now()
    private var endDay = startDay.plusDays(7)
    private var startTime = 9

    var eventList: List<LocalDateTime> = emptyList()
        set(value) {
            field = value
            invalidate()
        }

    fun setStartDay(day: LocalDate) {
        startDay = day
        endDay = startDay.plusDays(7)
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        drawBorder(canvas)
        drawLines(canvas)
        drawText(canvas)
        paintEvent(canvas)
    }

    private fun drawBorder(canvas: Canvas) {
        paint.apply {
            color = Color.BLACK
            style = Paint.Style.STROKE
            strokeWidth = 5f
        }

        canvas.drawRect(0.0f, 0.0f, width.toFloat(), height.toFloat(), paint)
    }

    private fun drawLines(canvas: Canvas) {
        paint.apply {
            color = Color.BLACK
            strokeWidth = 1f
        }

        for (row in 1 until rows) {
            canvas.drawLine(
                0f, row * cellHeight,
                width.toFloat(), row * cellHeight,
                paint
            )
        }

        for (col in 1 until columns) {
            canvas.drawLine(
                col * cellWidth, 0f,
                col * cellWidth, height.toFloat(),
                paint
            )
        }
    }

    private fun drawText(canvas: Canvas) {
        paint.apply {
            color = Color.BLACK
            style = Paint.Style.FILL
            isAntiAlias = true
            textSize = 40f
        }

        markDayOfWeek(canvas)
        markDate(canvas)
        markTime(canvas)
    }

    private fun markDayOfWeek(canvas: Canvas) {
        DayOfWeek.values().forEachIndexed { idx, it ->
            val text = resources.getString(it.dayName)
            val x = (idx + 1) * cellWidth + cellWidth / 2f - paint.measureText(text) / 2f
            val y = cellHeight / 2f + (paint.ascent() + paint.descent()) / 2f

            canvas.drawText(text, x, y, paint)
        }
    }

    private fun markDate(canvas: Canvas) {
        for (i in 0 until 7) {
            val day = startDay.plusDays(i.toLong())
            val text = if (i == 0 || day.dayOfMonth == 1) {
                "${day.month.value}/${day.dayOfMonth}"
            } else {
                day.dayOfMonth.toString()
            }

            val x = ((i + 1) * cellWidth) + (cellWidth / 2f) - (paint.measureText(text) / 2f)
            val y = (cellHeight / 2f) - ((paint.ascent() + paint.descent()) * 1.5f)

            canvas.drawText(text, x, y, paint)

            if (day.isEqual(LocalDate.now())) {
                markToday(canvas, i)
            }
        }
    }

    private fun markTime(canvas: Canvas) {
        for (row in 0 until rows) {
            val text = (startTime + row).toString()
            val x = (cellWidth / 2f) - (paint.measureText(text) / 2f)
            val y = ((row + 1) * cellHeight) + (cellHeight / 2f) - ((paint.ascent() + paint.descent()) / 2f)

            canvas.drawText(text, x, y, paint)
        }
    }

    private fun markToday(canvas: Canvas, idx: Int) {
        paint.apply {
            color = Color.GREEN
            alpha = 50
        }
        val x = (idx + 1) * cellWidth + cellWidth / 2f
        val y = cellHeight / 2f
        canvas.drawCircle(x, y, cellWidth * 0.4f, paint)

        paint.color = Color.BLACK
    }

    private fun paintEvent(canvas: Canvas) {
        val cellWidth = width / columns
        val cellHeight = height / rows
        paint.color = Color.LTGRAY

        eventList.forEach {
            val date = it.toLocalDate()

            if (date.isAfter(startDay) && date.isBefore(endDay)) {
                val row = it.hour - startTime + 1
                val col = date.dayOfWeek.value

                val x = col * cellWidth.toFloat()
                val y = row * cellHeight.toFloat()

                canvas.drawRect(x + 1, y + 1, x + cellWidth, y + cellHeight, paint)
            }
        }
    }

    enum class DayOfWeek(@StringRes val dayName: Int) {
        MON(R.string.monday),
        TUE(R.string.tuesday),
        WED(R.string.wednesday),
        THU(R.string.thursday),
        FRI(R.string.friday),
        SAT(R.string.saturday),
        SUN(R.string.sunday)
    }
}