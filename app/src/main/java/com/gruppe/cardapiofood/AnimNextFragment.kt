package com.gruppe.cardapiofood

import androidx.navigation.navOptions

/**
 * Objeto responsável por dar as cordenadas de animação entre a troca de páginas
 */
object AnimNextFragment {

    val animOptions = navOptions {
        anim {
            enter = R.anim.slide_in_right
            exit = R.anim.slide_out_left
            popEnter = R.anim.slide_in_left
            popExit = R.anim.slide_out_right
        }
    }
}