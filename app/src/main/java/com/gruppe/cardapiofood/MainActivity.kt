package com.gruppe.cardapiofood

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var toolbar: androidx.appcompat.widget.Toolbar
    private lateinit var menuItemSearch: MenuItem
    private lateinit var menuItemFavorite: MenuItem
    private lateinit var menuItemSettings: MenuItem
    private lateinit var menuItemLight: MenuItem
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        navController = findNavController(R.id.nav_host_fragment)

        setupActionBarWithNavController(navController)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        menuItemFavorite = menu.findItem(R.id.menuItemFavorite)
        menuItemLight = menu.findItem(R.id.menuItemLight)
        menuItemSearch = menu.findItem(R.id.menuItemSearch)
        menuItemSettings = menu.findItem(R.id.menuItemSettings)
        prepareMenuItens()
        return true
    }

    /**
     * Função responsável por controlar a visibilidade dos menu itens
     */
    private fun prepareMenuItens() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            //Controla Visibilidade dos Menu Itens
            menuItemSearch.isVisible = destination.id == R.id.SecondFragment
            menuItemFavorite.isVisible = destination.id == R.id.FirstFragment
            menuItemSettings.isVisible = destination.id == R.id.FirstFragment
            menuItemLight.isVisible = destination.id == R.id.FirstFragment
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            else -> super.onOptionsItemSelected(item)
        }
    }
}