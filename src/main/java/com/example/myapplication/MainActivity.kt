package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.recyclerview.SimpleItemTouchHelperCallback


class MainActivity : AppCompatActivity() {
    private lateinit var presenter: Presenter

    //val asd : Time;
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
        layoutManager.reverseLayout = true;
        layoutManager.stackFromEnd = false;
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = presenter.adapter

        val callback: ItemTouchHelper.Callback = SimpleItemTouchHelperCallback(presenter.adapter)
        val touchHelper = ItemTouchHelper(callback)
        touchHelper.attachToRecyclerView(recyclerView)
    }

    private fun setButtonsListeners() {
        findViewById<Button>(R.id.button_plus).setOnClickListener {
            presenter.addTime()
            val scrollview = findViewById<NestedScrollView>(R.id.scrollView)
            scrollview.post { scrollview.fullScroll(ScrollView.FOCUS_DOWN) }
        }
        findViewById<Button>(R.id.button0).setOnClickListener {
            presenter.addNumb(0)
            presenter.setAnswer()
        }
        findViewById<Button>(R.id.button1).setOnClickListener {
            presenter.addNumb(1)
            presenter.setAnswer()
        }
        findViewById<Button>(R.id.button2).setOnClickListener {
            presenter.addNumb(2)
            presenter.setAnswer()
        }
        findViewById<Button>(R.id.button3).setOnClickListener {
            presenter.addNumb(3)
            presenter.setAnswer()
        }
        findViewById<Button>(R.id.button4).setOnClickListener {
            presenter.addNumb(4)
            presenter.setAnswer()
        }
        findViewById<Button>(R.id.button5).setOnClickListener {
            presenter.addNumb(5)
            presenter.setAnswer()
        }
        findViewById<Button>(R.id.button6).setOnClickListener {
            presenter.addNumb(6)
            presenter.setAnswer()
        }
        findViewById<Button>(R.id.button7).setOnClickListener {
            presenter.addNumb(7)
            presenter.setAnswer()
        }
        findViewById<Button>(R.id.button8).setOnClickListener {
            presenter.addNumb(8)
            presenter.setAnswer()
        }
        findViewById<Button>(R.id.button9).setOnClickListener {
            presenter.addNumb(9)
            presenter.setAnswer()
        }
        findViewById<Button>(R.id.button_backspace).setOnClickListener {
            presenter.clearNumb()
            presenter.setAnswer()
        }
    }

    /*override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        val scrollview = findViewById<NestedScrollView>(R.id.scrollView)
        scrollview.post { scrollview.fullScroll(ScrollView.FOCUS_DOWN) }
    }*/

}