package com.example.myapplication

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.recyclerview.RecyclerListAdapter
import kotlin.reflect.KFunction1

class Presenter(private val answerView: TextView) {
    val adapter = RecyclerListAdapter(this)

    fun viewIsReady() {
        addTime()
        setAnswer()
    }

    fun addNumb(numb: Int, scrollToCurrentItem: KFunction1<Int, Unit>) {
        adapter.list[adapter.chosenTime]?.addNumb(numb)
        adapter.addNumb()
        scrollToCurrentItem(adapter.chosenTime)
    }

    fun clearNumb (scrollToCurrentItem: KFunction1<Int, Unit>) {
        adapter.list[adapter.chosenTime]?.clearNumb()
        adapter.clearNumb()
        scrollToCurrentItem(adapter.chosenTime)
    }

    internal fun addTime() {
        adapter.addItem(TimeData())
    }

    fun setAnswer() {
        var totalSeconds = 0
        var totalMinutes = 0
        var totalHours = 0
        for (i in adapter.list) {
            totalSeconds += i?.seconds!!
            totalMinutes += i.minutes
            totalHours += i.hours
        }
        totalMinutes += totalSeconds / 60
        totalSeconds %= 60
        totalHours += totalMinutes / 60
        totalMinutes %= 60
        answerView.text = TimeData.toString(totalHours, totalMinutes, totalSeconds)
    }
}