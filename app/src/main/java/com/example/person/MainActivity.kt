package com.example.person

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.person.fragment.data.DataFragment


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragment = supportFragmentManager.beginTransaction()
        fragment.add(R.id.rootFragment, DataFragment())
        fragment.commit()
    }
}