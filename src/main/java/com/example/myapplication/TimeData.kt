package com.example.myapplication

import android.util.Log

class TimeData {
    private var index: Int = 0
    private var array: Array<Int> = arrayOf(0, 0, 0, 0, 0, 0, 0)
    //private var array: Array<Int> = arrayOf(0, 0, 0, 0, 0, 0, 0)

    val hours: Int
        get() = (array[5].toString() + array[4].toString()).toInt()

    val minutes: Int
        get() = (array[3].toString() + array[2].toString()).toInt()

    val seconds: Int
        get() = (array[1].toString() + array[0].toString()).toInt()

    fun addNumb(numb: Int) {
        if (index == 0) {
            array[index] = numb
            index++
        }
        else if (index < array.size - 1) {
            var i = index
            while (i > 0) {
                array[i] = array[i - 1]
                i--
            }
            array[i] = numb
            index++
        }
    }

    fun clearNumb() {
        if (index != 0) {
            var i = 0
            while (i < index) {
                Log.d("clearNumb","i = $i")
                array[i] = array[i + 1]
                i++
            }
            array[index] = 0
            index--
        }

    }

    override fun toString() : String {
        var hoursStr : String = hours.toString()
        var minutesStr : String = minutes.toString()
        var secondsStr : String = seconds.toString()
        if (hours < 10) hoursStr = "0$hoursStr"
        if (minutes < 10) minutesStr = "0$minutesStr"
        if (seconds < 10) secondsStr = "0$secondsStr"

        return "$hoursStr:$minutesStr:$secondsStr"
    }

    companion object {
        fun toString(hours: Int, minutes: Int, seconds: Int) : String {
            var hoursStr : String = hours.toString()
            var minutesStr : String = minutes.toString()
            var secondsStr : String = seconds.toString()
            if (hours < 10) hoursStr = "0$hoursStr"
            if (minutes < 10) minutesStr = "0$minutesStr"
            if (seconds < 10) secondsStr = "0$secondsStr"

            return "$hoursStr:$minutesStr:$secondsStr"
        }
    }
}