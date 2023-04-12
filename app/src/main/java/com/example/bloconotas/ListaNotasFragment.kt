package com.example.bloconotas

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.example.bloconotas.database.AppDatabase
import com.example.bloconotas.database.dao.NotaDao
import com.example.bloconotas.databinding.FragmentListaNotasBinding

class ListaNotasFragment : Fragment(R.layout.fragment_lista_notas) {
    private lateinit var binding: FragmentListaNotasBinding
    private lateinit var navigation: NavController
    private lateinit var db: AppDatabase
    private lateinit var notaDao: NotaDao
    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

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

        binding = FragmentListaNotasBinding.inflate(inflater, container, false)
        listView = binding.listaNotas

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.adicionarNota.setOnClickListener{
            navigation.navigate(R.id.action_listaNotasFragment_to_adicionarNotaFragment)
        }

        listarNotas()
    }

    fun listarNotas()
    {
        var notas = notaDao.GetAll()
        var listaNotas = mutableListOf<String>()
        val listaNotasId = mutableListOf<Int>()

        for(nota in notas)
        {
            listaNotas.add(nota.NomeNota)
            listaNotasId.add(nota.Id as Int)
        }

        var resource = android.R.layout.simple_list_item_1
        var context = activity?.baseContext as Context

        val adapter = ArrayAdapter<String>(context, resource, listaNotas)
        listView.adapter = adapter

        listView.onItemClickListener = AdapterView.OnItemClickListener {
            _, _, position, _ ->
                var notaId = listaNotasId[position]

            var bundle = Bundle()
            bundle.putInt("NOTA_ID", notaId)
            navigation.navigate(R.id.action_listaNotasFragment_to_adicionarNotaFragment, bundle)
        }

        listView.onItemLongClickListener = AdapterView.OnItemLongClickListener() {
            _, _, position, _ ->
                var notaId = listaNotasId[position]

            abrirCaixaConfirmação(notaId)
                true
        }
    }

    fun abrirCaixaConfirmação(idNota: Int)
    {
        var alertDialog = AlertDialog.Builder(requireContext())
        alertDialog.setTitle("Confirmação de exclusão")
            .setMessage("Você tem certeza que deseja excluir esta nota?")

        alertDialog.setPositiveButton("Sim") { _, _ ->
            // código para executar a ação
            deletarNota(idNota)
        }

        alertDialog.setNegativeButton("Cancelar") { dialog, _ ->
            // código para cancelar a ação
            dialog.dismiss()
        }

        alertDialog.show()
    }

    fun deletarNota(idNota: Int)
    {
        var nota = notaDao.GetById(idNota)
        notaDao.Delete(nota)

        listarNotas()
    }
}