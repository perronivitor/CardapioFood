package com.gruppe.cardapiofood.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gruppe.cardapiofood.R

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class CategoriaFragment : Fragment() {

    private lateinit var viewFragment : View

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        viewFragment = inflater.inflate(R.layout.fragment_second, container, false)
        return viewFragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}