package com.example.myapplication

import android.widget.TextView
import com.example.myapplication.recyclerview.RecyclerListAdapter
import kotlin.reflect.KFunction1

class Presenter {
    val adapter = RecyclerListAdapter()

    fun viewIsReady(kFunction3: (totalHours: Int, totalMinutes: Int, totalSeconds: Int) -> Unit) {
        addTime()
        setAnswer(kFunction3)
    }

    fun addNumb(numb: Int, scrollToCurrentItem: KFunction1<Int, Unit>,
        kFunction3: (totalHours: Int, totalMinutes: Int, totalSeconds: Int) -> Unit) {
        adapter.addNumb(numb)
        setAnswer(kFunction3)
        scrollToCurrentItem(adapter.chosenTime)
    }

    fun clearNumb (scrollToCurrentItem: KFunction1<Int, Unit>,
        kFunction3: (totalHours: Int, totalMinutes: Int, totalSeconds: Int) -> Unit) {
        adapter.clearNumb()
        setAnswer(kFunction3)
        scrollToCurrentItem(adapter.chosenTime)
    }

    internal fun addTime() {
        adapter.addItem(TimeData())
    }

    fun setAnswer(kFunction3: (totalHours: Int, totalMinutes: Int, totalSeconds: Int) -> Unit) {
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
        kFunction3(totalHours, totalMinutes, totalSeconds)
    }
}