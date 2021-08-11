package com.gruppe.cardapiofood.ui.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gruppe.cardapiofood.R
import com.gruppe.cardapiofood.ui.viewmodel.ReceitaViewModel

class ReceitaFragment : Fragment() {

    companion object {
        fun newInstance() = ReceitaFragment()
    }

    private lateinit var viewModel: ReceitaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.receita_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ReceitaViewModel::class.java)
        // TODO: Use the ViewModel
    }

}