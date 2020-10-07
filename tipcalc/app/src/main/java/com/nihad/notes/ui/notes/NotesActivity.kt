package com.nihad.notes.ui.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.nihad.notes.R
import com.nihad.notes.ui.notes.main.NotesFragment

class NotesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.notes_activity)

        val windows=this.window
        windows.statusBarColor= android.graphics.Color.parseColor("#FF57314B")
        if (savedInstanceState == null) {
//            supportFragmentManager.beginTransaction()
//                    .replace(R.id.container, NotesFragment.newInstance())
//                    .commitNow()
        }
    }
}