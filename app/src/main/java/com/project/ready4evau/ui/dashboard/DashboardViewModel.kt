package com.project.ready4evau.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ready4evau.database.ExamenAdapterModel
import com.example.ready4evau.database.PreguntasDBHelper
import com.project.ready4evau.R

class DashboardViewModel : ViewModel() {


    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text

}