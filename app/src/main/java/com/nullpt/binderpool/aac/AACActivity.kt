package com.nullpt.binderpool.aac

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.HasDefaultViewModelProviderFactory
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.RecyclerView
import com.nullpt.binderpool.R

class AACActivity : AppCompatActivity(), HasDefaultViewModelProviderFactory {

    override fun getDefaultViewModelProviderFactory(): ViewModelProvider.Factory {
        return ViewModelProvider.AndroidViewModelFactory(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aac)

        val viewModel = ViewModelProvider(this).get(TestViewModel::class.java)

    }
}
