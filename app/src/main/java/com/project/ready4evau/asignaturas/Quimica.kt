package com.project.ready4evau.asignaturas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import com.project.ready4evau.R

class Quimica : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quimica)
        val actionBar: ActionBar? = supportActionBar
        actionBar?.hide()
    }
}