package com.example.myapplication

import android.os.Build
import android.os.Bundle
import android.view.HapticFeedbackConstants
import android.view.View
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.recyclerview.SimpleItemTouchHelperCallback


class MainActivity : AppCompatActivity() {
    private lateinit var presenter: Presenter

    //val asd : Time;
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        presenter = Presenter(findViewById(R.id.answer_view))

        initRecyclerView()

        setButtonsListeners()

        presenter.viewIsReady()
    }

    private fun initRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)

        val layoutManager = LinearLayoutManager(this)
        layoutManager.reverseLayout = true
        layoutManager.stackFromEnd = false
        layoutManager.isAutoMeasureEnabled = false
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = presenter.adapter

        //window.decorView.systemUiVisibility  = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        //window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LOW_PROFILE;

        val callback: ItemTouchHelper.Callback = SimpleItemTouchHelperCallback(presenter.adapter)
        val touchHelper = ItemTouchHelper(callback)
        touchHelper.attachToRecyclerView(recyclerView)
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun setButtonsListeners() {
        findViewById<Button>(R.id.button_plus).setOnClickListener {
            presenter.addTime()
            scrollToCurrentItem(0)
            it.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP)
        }
        findViewById<Button>(R.id.button0).setOnClickListener {
            addNumb(0)
            it.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP)
        }
        findViewById<Button>(R.id.button1).setOnClickListener {
            addNumb(1)
            it.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP)
        }
        findViewById<Button>(R.id.button2).setOnClickListener {
            addNumb(2)
            it.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP)
        }
        findViewById<Button>(R.id.button3).setOnClickListener {
            addNumb(3)
            it.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP)
        }
        findViewById<Button>(R.id.button4).setOnClickListener {
            addNumb(4)
            it.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP)
        }
        findViewById<Button>(R.id.button5).setOnClickListener {
            addNumb(5)
            it.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP)
        }
        findViewById<Button>(R.id.button6).setOnClickListener {
            addNumb(6)
            it.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP)
        }
        findViewById<Button>(R.id.button7).setOnClickListener {
            addNumb(7)
            it.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP)
        }
        findViewById<Button>(R.id.button8).setOnClickListener {
            addNumb(8)
            it.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP)
        }
        findViewById<Button>(R.id.button9).setOnClickListener {
            addNumb(9)
            it.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP)
        }
        findViewById<View>(R.id.button_backspace).setOnClickListener {
            clearNumb()
            it.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP)
        }
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun addNumb(numb: Int) {
        presenter.addNumb(numb, ::scrollToCurrentItem)
        presenter.setAnswer()
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun clearNumb() {
        presenter.clearNumb(::scrollToCurrentItem)
        presenter.setAnswer()
    }


    private fun scrollToCurrentItem(index: Int) {
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.scrollToPosition(index)
    }

    /*override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        val scrollview = findViewById<NestedScrollView>(R.id.scrollView)
        scrollview.post { scrollview.fullScroll(ScrollView.FOCUS_DOWN) }
    }*/

}