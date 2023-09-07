package com.example.stardewvalleysnpcwiki

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)

        val dataNpc = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Npc>("key_npc", Npc::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Npc>("key_npc")
        }

        val tvDetailName : TextView = findViewById(R.id.tv_detail_name)
        val tvDetailDescription : TextView = findViewById(R.id.tv_detail_description)
        val ivDetailPhoto : ImageView = findViewById(R.id.iv_detail_photo)
        val tvBirthday : TextView = findViewById(R.id.tv_birthday)
        val tvAge : TextView = findViewById(R.id.tv_age)

        if (dataNpc != null) {
            tvDetailName.text = dataNpc.name
            tvDetailDescription.text = dataNpc.description
            ivDetailPhoto.setImageResource(dataNpc.photo)
            tvBirthday.text = dataNpc.birthday
            tvAge.text = dataNpc.age.toString()
        }

        var actionBar = getSupportActionBar()

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val myIntent = Intent(applicationContext, MainActivity::class.java)
        startActivity(myIntent)
        return true
    }
}