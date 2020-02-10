package com.nvlad.gitusers.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nvlad.gitusers.R


abstract class SingleFragmentActivity : AppCompatActivity() {

    protected abstract fun createFragment(): androidx.fragment.app.Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, createFragment())
                    .commitNow()
        }
    }


}