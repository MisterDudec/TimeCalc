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
            scrollToCurrentItem(0)
        }
        findViewById<Button>(R.id.button0).setOnClickListener {
            addNumb(0)
        }
        findViewById<Button>(R.id.button1).setOnClickListener {
            addNumb(1)
        }
        findViewById<Button>(R.id.button2).setOnClickListener {
            addNumb(2)
        }
        findViewById<Button>(R.id.button3).setOnClickListener {
            addNumb(3)
        }
        findViewById<Button>(R.id.button4).setOnClickListener {
            addNumb(4)
        }
        findViewById<Button>(R.id.button5).setOnClickListener {
            addNumb(5)
        }
        findViewById<Button>(R.id.button6).setOnClickListener {
            addNumb(6)
        }
        findViewById<Button>(R.id.button7).setOnClickListener {
            addNumb(7)
        }
        findViewById<Button>(R.id.button8).setOnClickListener {
            addNumb(8)
        }
        findViewById<Button>(R.id.button9).setOnClickListener {
            addNumb(9)
        }
        findViewById<Button>(R.id.button_backspace).setOnClickListener {
            clearNumb()
        }
    }

    private fun addNumb(numb: Int) {
        presenter.addNumb(numb, ::scrollToCurrentItem)
        presenter.setAnswer()
    }

    private fun clearNumb() {
        presenter.clearNumb(::scrollToCurrentItem)
        presenter.setAnswer()
    }


    private fun scrollToCurrentItem(index: Int) {
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.scrollToPosition(index);
    }

    /*override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        val scrollview = findViewById<NestedScrollView>(R.id.scrollView)
        scrollview.post { scrollview.fullScroll(ScrollView.FOCUS_DOWN) }
    }*/

}