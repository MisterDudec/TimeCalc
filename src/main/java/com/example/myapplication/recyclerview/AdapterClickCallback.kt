package com.example.myapplication.recyclerview

import android.view.View

abstract class AdapterClickCallback<T> {
    fun onItemClick(model: T, view: View) {}
    open fun onItemClick(model: T, view: View, position: Int) {}
}