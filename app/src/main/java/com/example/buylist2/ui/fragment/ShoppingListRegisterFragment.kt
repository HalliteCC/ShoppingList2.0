package com.example.buylist2.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.buylist2.adapter.ShoppingListAdapter
import com.example.buylist2.constant.ShoppingConstant
import com.example.buylist2.databinding.FragmentShoppingListRegisterBinding
import com.example.buylist2.model.ShoppingListModel
import com.example.buylist2.viewModel.ShoppingListRegisterViewModel

class ShoppingListRegisterFragment : Fragment() {


    private lateinit var viewModel: ShoppingListRegisterViewModel


    private lateinit var binding: FragmentShoppingListRegisterBinding
    private val adapter = ShoppingListAdapter()

    private var listId = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        viewModel = ViewModelProvider(this).get(ShoppingListRegisterViewModel::class.java)


        binding = FragmentShoppingListRegisterBinding.inflate(inflater, container, false)
        val root: View = binding.root


        newShopping()
        observer()
        loadData()

        return root
    }

    fun newShopping() {
        binding.buttonCreateList.setOnClickListener{
            val listName = binding.editListName.text.toString()

            val model = ShoppingListModel().apply {
                this.id = listId
                this.listName = listName
            }
            toast(model)
        }
    }

    private fun loadData() {
        val bundle = arguments
        if (bundle != null) {
            listId = bundle.getInt("listID")
            viewModel.get(listId)
        }
    }

    private fun observer() {
        viewModel.shoppingList.observe(viewLifecycleOwner) { shoppingList ->
            shoppingList?.let {
                binding.editListName.setText(it.listName)
                listId = it.id
            }
        }
    }

    private fun toast(shoppingList: ShoppingListModel) {

        val name = binding.editListName.text.toString()

        //Verificação se já existe a Lista
        if (listId == 0 && name != "") {
            viewModel.insert(shoppingList)
            Toast.makeText(context, "Lista Criada", Toast.LENGTH_SHORT).show()
            backImmediate()
        } else if (listId != 0 && name != "") {
            viewModel.update(shoppingList)
            Toast.makeText(context, "Lista Modificada", Toast.LENGTH_SHORT).show()
            backImmediate()
        } else {
            Toast.makeText(context, "FALHA", Toast.LENGTH_SHORT).show()
        }
    }

    fun backImmediate(){
        val fragmentManager = requireActivity().supportFragmentManager
        fragmentManager.popBackStackImmediate()
    }
}