package com.example.myapplication

import android.widget.TextView
import com.example.myapplication.recyclerview.RecyclerListAdapter

class Presenter(private val textView: TextView) {
    val adapter = RecyclerListAdapter(this)

    fun viewIsReady() {
        addTime()
    }

    fun addNumb (numb : Int) {
        adapter.list[adapter.chosenTime]?.addNumb(numb)
        adapter.addNumb()
    }

    fun clearNumb () {
        adapter.list[adapter.chosenTime]?.clearNumb()
        adapter.clearNumb()
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
        textView.text = TimeData.toString(totalHours, totalMinutes, totalSeconds)
    }
}