package com.example.bloconotas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.bloconotas.databinding.FragmentListaNotasBinding

class ListaNotasFragment : Fragment(R.layout.fragment_lista_notas) {
    private lateinit var binding: FragmentListaNotasBinding
    private lateinit var navigation: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        navigation = findNavController()

        binding = FragmentListaNotasBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.adicionarNota.setOnClickListener{
            navigation.navigate(R.id.action_listaNotasFragment_to_adicionarNotaFragment)
        }



    }
}