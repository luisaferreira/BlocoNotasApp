package com.example.bloconotas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.example.bloconotas.database.AppDatabase
import com.example.bloconotas.database.dao.NotaDao
import com.example.bloconotas.databinding.FragmentAdicionarNotaBinding
import com.example.bloconotas.entities.Nota

class AdicionarNotaFragment : Fragment(R.layout.fragment_adicionar_nota) {
    private lateinit var binding: FragmentAdicionarNotaBinding
    private lateinit var navigation: NavController
    private lateinit var db: AppDatabase
    private lateinit var notaDao: NotaDao
    private var notaId: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.db = Room.databaseBuilder(
            requireContext().applicationContext,
            AppDatabase::class.java, "item_database"
        ).allowMainThreadQueries().build()

        notaDao = this.db.notaDao()
        navigation = findNavController()

        binding = FragmentAdicionarNotaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            notaId = it.getInt("NOTA_ID", -1)
        }

        binding.btnSalvarNota.setOnClickListener {
            notaId ?: salvarNota()
        }

        notaId?.let {
            var nota = notaDao.GetById(it)
            binding.nomeNota.setText(nota?.NomeNota)
            binding.nota.setText(nota?.Nota)
        }
    }

    fun salvarNota()
    {
        val nomeNota = binding.nomeNota.text.toString()
        val notaConteudo = binding.nota.text.toString()

        var nota = Nota(null, nomeNota, notaConteudo)

        notaDao.Insert(nota)

        navigation.navigate(R.id.action_adicionarNotaFragment_to_listaNotasFragment)
    }
}