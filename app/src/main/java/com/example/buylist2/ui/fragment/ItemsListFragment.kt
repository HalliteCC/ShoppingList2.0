package com.example.buylist2.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.buylist2.R
import com.example.buylist2.adapter.ItemListAdapter
import com.example.buylist2.constant.ShoppingConstant
import com.example.buylist2.databinding.FragmentItemsBinding
import com.example.buylist2.listener.ItemsListener
import com.example.buylist2.viewModel.ItemsViewModel

class ItemsListFragment : Fragment() {

    private lateinit var viewModel: ItemsViewModel
    private var _binding: FragmentItemsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val adapter = ItemListAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, b: Bundle?): View {
        viewModel = ViewModelProvider(this).get(ItemsViewModel::class.java)

        _binding = FragmentItemsBinding.inflate(inflater, container, false)

        binding.recyclerItems.layoutManager = LinearLayoutManager(context)
        binding.recyclerItems.adapter = adapter

        val listener = object : ItemsListener {

            override fun onEditClick(id: Int) {
                val bundle = bundleOf(ShoppingConstant.LIST.ITEM_ID to id)
                Navigation.findNavController(binding.root)
                    .navigate(R.id.itemRegisterFragment, bundle)

            }

            override fun onDelete(id: Int) {
                viewModel.delete(id)
                viewModel.getAll()
                Toast.makeText(context, "Item removed", Toast.LENGTH_SHORT).show()
            }

            override fun onCompleteClick(id: Int) {
                viewModel.status(id, true)
            }

            override fun onUndoClick(id: Int) {
                viewModel.status(id, false)
            }
        }
        adapter.attachListener(listener)

        observe()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAll()
        /*arguments?.getInt("ListID")?.let {
            viewModel.getItemById(it)
        }*/
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observe() {
        viewModel.product.observe(viewLifecycleOwner) {
            adapter.updateProduct(it)
        }
    }
}

