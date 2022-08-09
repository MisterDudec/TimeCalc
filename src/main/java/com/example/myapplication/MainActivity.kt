package com.example.myapplication

import android.os.Build
import android.os.Bundle
import android.view.HapticFeedbackConstants
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.recyclerview.AdapterClickCallback
import com.example.myapplication.recyclerview.AdapterDismissCallback
import com.example.myapplication.recyclerview.SimpleItemTouchHelperCallback


class MainActivity : AppCompatActivity() {
    private lateinit var presenter: Presenter

    //val asd : Time;
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = Presenter()

        initRecyclerView()

        setButtonsListeners()

        presenter.viewIsReady(::setAnswer)
    }

    private fun initRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)

        val layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, true)
        layoutManager.stackFromEnd = false
        layoutManager.isAutoMeasureEnabled = false
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)

        attachClickCallback()

        attachDismissCallback()

        recyclerView.adapter = presenter.adapter

        val callback: ItemTouchHelper.Callback = SimpleItemTouchHelperCallback(presenter.adapter)
        val touchHelper = ItemTouchHelper(callback)
        touchHelper.attachToRecyclerView(recyclerView)
    }

    private fun attachDismissCallback() {
        presenter.adapter.attachDismissCallback(object : AdapterDismissCallback {
            override fun dismissItem() {
                presenter.setAnswer(::setAnswer)
            }
        })
    }

    private fun attachClickCallback() {
        presenter.adapter.attachClickCallback(object : AdapterClickCallback<TimeData>() {
            override fun onItemClick(model: TimeData, view: View, position: Int) {
                view.performHapticFeedback(HapticFeedbackConstants.CONTEXT_CLICK)
                view.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP)
                view.startAnimation(AnimationUtils.loadAnimation(view.context, R.anim.button_press))
                val prev = presenter.adapter.chosenTime
                presenter.adapter.chosenTime = position
                presenter.adapter.notifyItemChanged(prev)
                presenter.adapter.notifyItemChanged(presenter.adapter.chosenTime)
            }
        })
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun setButtonsListeners() {
        findViewById<Button>(R.id.button_plus).setOnClickListener {
            presenter.addTime()
            scrollToCurrentItem(0)
            it.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP)
            it.startAnimation(AnimationUtils.loadAnimation(this, R.anim.button_press))
        }
        findViewById<Button>(R.id.button0).setOnClickListener {
            addNumb(0, it)
        }
        findViewById<Button>(R.id.button1).setOnClickListener {
            addNumb(1, it)
        }
        findViewById<Button>(R.id.button2).setOnClickListener {
            addNumb(2, it)
        }
        findViewById<Button>(R.id.button3).setOnClickListener {
            addNumb(3, it)
        }
        findViewById<Button>(R.id.button4).setOnClickListener {
            addNumb(4, it)
        }
        findViewById<Button>(R.id.button5).setOnClickListener {
            addNumb(5, it)
        }
        findViewById<Button>(R.id.button6).setOnClickListener {
            addNumb(6, it)
        }
        findViewById<Button>(R.id.button7).setOnClickListener {
            addNumb(7, it)
        }
        findViewById<Button>(R.id.button8).setOnClickListener {
            addNumb(8, it)
        }
        findViewById<Button>(R.id.button9).setOnClickListener {
            addNumb(9, it)
        }
        findViewById<View>(R.id.button_backspace).setOnClickListener {
            clearNumb(it)
        }
    }

    private fun addNumb(numb: Int, view: View) {
        view.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP)
        view.startAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_in))
        presenter.addNumb(numb, ::scrollToCurrentItem, ::setAnswer)
    }

    private fun clearNumb(view: View) {
        view.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP)
        view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.button_press))
        presenter.clearNumb(::scrollToCurrentItem, ::setAnswer)
    }

    private fun scrollToCurrentItem(index: Int) {
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.scrollToPosition(index)
    }

    private fun setAnswer(totalHours: Int, totalMinutes: Int, totalSeconds: Int) {
        findViewById<TextView>(R.id.answer_view).text = TimeData.toString(totalHours, totalMinutes, totalSeconds)
    }

}