package com.intents.recyclerview

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.widget.Toolbar
import com.example.recycleview.CardViewHeroAdapter
import com.example.recycleview.GridHeroAdapter
import com.example.recycleview.Hero
import com.example.recycleview.HeroesData
import com.example.recycleview.R

class MainActivity : AppCompatActivity() {
	private lateinit var rvHeroes: RecyclerView
	private lateinit var toolbar: Toolbar
	private var list: ArrayList<Hero> = arrayListOf()
	private var title: String = "Mode List"

	private fun setActionBarTitle(title: String) {
		supportActionBar?.title = title
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		// Inisialisasi Toolbar
		toolbar = findViewById(R.id.toolbar) // Inisialisasi toolbar
		setSupportActionBar(toolbar) // Set toolbar sebagai action bar

		// Aktifkan ActionBar dan set judul awal
		supportActionBar?.setDisplayHomeAsUpEnabled(true)
		setActionBarTitle(title)

		rvHeroes = findViewById(R.id.rv_hero)
		rvHeroes.setHasFixedSize(true)

		list.addAll(HeroesData.listData)
		showRecyclerList()
	}


	private fun showRecyclerList() {
		rvHeroes.layoutManager = LinearLayoutManager(this)
		val listHeroAdapter = ListHeroAdapter(list)
		rvHeroes.adapter = listHeroAdapter

		listHeroAdapter.setOnItemClickCallback(object : ListHeroAdapter.OnItemClickCallback {
			override fun onItemClicked(data: Hero) {
				showSelectedHero(data)
			}
		})
	}

	private fun showRecyclerGrid() {
		rvHeroes.layoutManager = GridLayoutManager(this, 2)
		val gridHeroAdapter = GridHeroAdapter(list)
		rvHeroes.adapter = gridHeroAdapter

		gridHeroAdapter.setOnItemClickCallback(object : GridHeroAdapter.OnItemClickCallback {
			override fun onItemClicked(data: Hero) {
				showSelectedHero(data)
			}
		})
	}

	private fun showRecyclerCardView() {
		rvHeroes.layoutManager = LinearLayoutManager(this)
		val cardViewHeroAdapter = CardViewHeroAdapter(list)
		rvHeroes.adapter = cardViewHeroAdapter
	}

	override fun onCreateOptionsMenu(menu: Menu): Boolean {
		menuInflater.inflate(R.menu.menu_main, menu)
		return true
	}

	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		when (item.itemId) {
			R.id.action_list -> {
				title = "Mode List"
				showRecyclerList()
			}

			R.id.action_grid -> {
				title = "Mode Grid"
				showRecyclerGrid()
			}

			R.id.action_cardview -> {
				title = "Mode Cardview"
				showRecyclerCardView()
			}
		}

		// Set judul ActionBar setelah beralih mode
		setActionBarTitle(title)

		return super.onOptionsItemSelected(item)
	}
	private fun showSelectedHero(hero: Hero) {
		Toast.makeText(this, "Kamu Memilih ${hero.name}", Toast.LENGTH_SHORT).show()
	}
}