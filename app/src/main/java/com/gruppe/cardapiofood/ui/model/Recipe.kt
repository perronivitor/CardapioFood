package com.gruppe.cardapiofood.ui.model

class Recipe (val title :String,
              val imgUrl :String,
              val prepareMode :String,
              var isFavorite :Boolean = false,
              val ingredients : List<String>
)