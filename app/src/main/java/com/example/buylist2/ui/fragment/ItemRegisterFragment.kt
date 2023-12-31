package com.example.buylist2.ui.fragment

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.buylist2.constant.ShoppingConstant
import com.example.buylist2.databinding.FragmentItemRegisterBinding
import com.example.buylist2.model.ItemsModel
import com.example.buylist2.viewModel.ItemRegisterViewModel
import com.example.buylist2.viewModel.ShoppingListViewModel

class ItemRegisterFragment : Fragment() {

    private lateinit var binding: FragmentItemRegisterBinding
    private lateinit var viewModel: ItemRegisterViewModel
    private lateinit var shoppingListViewModel: ShoppingListViewModel

    private var itemId = 0
    private var selectedListId = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentItemRegisterBinding.inflate(inflater, container, false)
        val root: View = binding.root

        viewModel = ViewModelProvider(this).get(ItemRegisterViewModel::class.java)


        newItem()
        observer()
        loadData()
        loadSpinner()

        return root
    }


    fun newItem() {
        binding.buttonSave.setOnClickListener {
            val item = binding.editDescription.text.toString()
            var strQ = binding.editQuantity.text.toString()
            val str = binding.editPrice.text.toString()
            if (item == "") {
                Toast.makeText(context, "FAIL", Toast.LENGTH_SHORT).show()
            } else if (str == "" && strQ == "") {

                var quantity = 0.0
                var price = 0.0

                val model = ItemsModel().apply {
                    this.id = itemId
                    this.item = item
                    this.price = price
                    this.quantity = quantity
                    this.totalPrice = price * quantity
                    this.listId = selectedListId
                }
                toast(model)
            }else {
                var quantity = strQ.toDouble()
                var price = str.toDouble()
                val model = ItemsModel().apply {
                    this.id = itemId
                    this.item = item
                    this.price = price
                    this.quantity = quantity
                    this.totalPrice = price * quantity
                    this.listId = selectedListId
                }
                toast(model)
            }
        }
    }

    private fun loadData() {
        val bundle = arguments
        if (bundle != null) {
            itemId = bundle.getInt(ShoppingConstant.LIST.ITEM_ID)
            viewModel.get(itemId)
        }
    }

    private fun loadSpinner() {
        shoppingListViewModel = ViewModelProvider(this).get(ShoppingListViewModel::class.java)
        val shoppingLists = shoppingListViewModel.spinner()
       val nameList =
            mutableListOf("Select a place to shop") // Adiciona a mensagem no início da lista
        nameList.addAll(shoppingLists.map { it.listName })

        val adapter = context?.let { ArrayAdapter(it, R.layout.simple_spinner_item, nameList) }

        adapter?.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        binding.spinnerShopping.adapter = adapter

        binding.spinnerShopping.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    if (position > 0) { // Ignora a primeira opção "Selecione uma opção"
                        val selectedList =
                            shoppingLists[position - 1] // Obtém a lista selecionada do ViewModel
                        selectedListId = selectedList.id // Obtém o ID da lista selecionada
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }
            }
    }


    private fun observer() {
        viewModel.itemSave.observe(viewLifecycleOwner) { shoppingList ->
            shoppingList?.let {
                binding.editDescription.setText(it.item)
                itemId = it.id
            }
        }
    }


    private fun toast(productList: ItemsModel) {

        val name = binding.editDescription.text.toString()
        val strQ = binding.editQuantity.text.toString()
        val str = binding.editPrice.text.toString()

        //Verificação se já existe a Lista
        if (itemId == 0 && name != "" && selectedListId != 0) {
            viewModel.insertItem(productList)
            Toast.makeText(context, "Item Created", Toast.LENGTH_SHORT).show()
            backImmediate()
        } else if (itemId != 0 && name != "" && strQ != "" && str != "" && selectedListId != 0) {
            viewModel.updateItem(productList)
            Toast.makeText(context, "Item Modified", Toast.LENGTH_SHORT).show()
            backImmediate()
        } else if (selectedListId == 0){
            Toast.makeText(context, "Please select a place to shop", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Please fill all the fields", Toast.LENGTH_SHORT).show()
        }
    }

    private fun backImmediate() {
        val fragmentManager = requireActivity().supportFragmentManager
        fragmentManager.popBackStackImmediate()
    }

}