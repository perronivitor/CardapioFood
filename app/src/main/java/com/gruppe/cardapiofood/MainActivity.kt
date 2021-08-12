package com.gruppe.cardapiofood

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var toolbar: androidx.appcompat.widget.Toolbar
    private lateinit var menuItemSearch: MenuItem
    private lateinit var menuItemFavorite: MenuItem
    private lateinit var menuItemSettings: MenuItem
    private lateinit var menuItemLight: MenuItem
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        navController = findNavController(R.id.nav_host_fragment)

        //Prepara o actionbar
        appBarConfiguration = AppBarConfiguration
            .Builder(R.id.MenuCategoryFragment).build()

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
            menuItemSearch.isVisible = destination.id == R.id.mealsFragment
            menuItemFavorite.isVisible = destination.id == R.id.MenuCategoryFragment
            menuItemSettings.isVisible = destination.id == R.id.MenuCategoryFragment
            menuItemLight.isVisible = destination.id == R.id.MenuCategoryFragment
        }
    }

}