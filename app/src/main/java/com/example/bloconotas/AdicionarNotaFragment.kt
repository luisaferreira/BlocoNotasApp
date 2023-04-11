package com.example.bloconotas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bloconotas.databinding.FragmentAdicionarNotaBinding

class AdicionarNotaFragment : Fragment(R.layout.fragment_adicionar_nota) {
    private lateinit var binding: FragmentAdicionarNotaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAdicionarNotaBinding.inflate(inflater, container, false)
        return binding.root
    }
}