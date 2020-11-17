package com.nullpt.binderpool.test

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.nullpt.binderpool.R
import kotlinx.android.synthetic.main.activity_async_task.*
import java.util.concurrent.Executors

class AsyncTaskActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_async_task)

//        val task1 = MyAsyncTask()
//        val task2 = MyAsyncTask()
//        val task3 = MyAsyncTask()
//        val executor = Executors.newFixedThreadPool(3)
//
//        button.setOnClickListener {MessageQueue
//            task1.executeOnExecutor(executor, "A")
//            task2.executeOnExecutor(executor, "B")
//            task3.executeOnExecutor(executor, "C")
//        }

        layout.setOnClickListener {
            Toast.makeText(this, "A", Toast.LENGTH_SHORT).show()
        }

        button.setOnClickListener {
            Toast.makeText(this, "B", Toast.LENGTH_SHORT).show()
        }

    }

    companion object {

        class MyAsyncTask : AsyncTask<String, Void, Void?>() {

            override fun doInBackground(vararg params: String?): Void? {
                if (params[0] == "A") {
                    Thread.sleep(3000)
                }
                println("BGQ->${params[0]}")
                return null
            }

        }

    }

}
