
package com.example.stardewvalleysnpcwiki

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvNpcs: RecyclerView
    private val list = ArrayList<Npc>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvNpcs = findViewById(R.id.rv_npcs)
        rvNpcs.setHasFixedSize(true)
        list.addAll(getListHeroes())
        showRecyclerList()
    }

    @SuppressLint("Recycle")
    private fun getListHeroes(): ArrayList<Npc> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val dataBirthday = resources.getStringArray(R.array.data_birthday)
        val dataAge = resources.getIntArray(R.array.age)
        val listNpc = ArrayList<Npc>()
        for (i in dataName.indices) {
            val npc = Npc(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1), dataBirthday[i], dataAge[i])
            listNpc.add(npc)
        }
        return listNpc
    }

    private fun showRecyclerList() {
        rvNpcs.layoutManager = LinearLayoutManager(this)
        val listNpcAdapter = ListNpcAdapter(list)
        rvNpcs.adapter = listNpcAdapter

        listNpcAdapter.setOnItemClickCallback(object : ListNpcAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Npc) {
                showSelectedNpc(data)
            }
        })
    }

    private fun showSelectedNpc(npc: Npc) {
        Toast.makeText(this, "Kamu memilih " + npc.name, Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
            menuInflater.inflate(R.menu.profile, menu)
            return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.about_page -> {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
            true
        }

        else -> {
            super.onOptionsItemSelected(item)
        }
    }
}