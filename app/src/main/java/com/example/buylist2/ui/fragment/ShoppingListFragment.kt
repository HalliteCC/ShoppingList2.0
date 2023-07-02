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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.buylist2.R
import com.example.buylist2.adapter.ShoppingListAdapter
import com.example.buylist2.databinding.FragmentShoppingListBinding
import com.example.buylist2.listener.ShoppingListListener
import com.example.buylist2.viewModel.ShoppingListViewModel

class ShoppingListFragment : Fragment() {

    private lateinit var viewModel: ShoppingListViewModel
    private var _binding: FragmentShoppingListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    private val adapter = ShoppingListAdapter()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, b: Bundle?): View {

        viewModel = ViewModelProvider(this).get(ShoppingListViewModel::class.java)

        _binding = FragmentShoppingListBinding.inflate(inflater, container, false)


        //layout
        binding.recyclerShoppingList.layoutManager = LinearLayoutManager(context)

        //adapter
        binding.recyclerShoppingList.adapter = adapter


        //Click Event
        val listener = object : ShoppingListListener {

            //Function to open products fragment when an item of the list is clicked
            override fun onOpenFragment(id: Int) {
                val navController = Navigation.findNavController(binding.root)
                navController.navigate(R.id.items_list, args = bundleOf("listID" to id))
            }

            //Function to open ShoppingList Register
           override fun onEditClick(id: Int) {

                /*val intent = Intent(context, BuyListActivity::class.java)
                val bundle = Bundle()
                bundle.putInt(ShoppingConstant.LIST.LIST_ID, id)
                intent.putExtras(bundle)

                startActivity(intent)*/
            }

            //Function to delete a ShoppingList item in the List
            override fun onDeleteClick(id: Int) {
                viewModel.delete(id)
                viewModel.getAll()
                Toast.makeText(context, "Lista removida", Toast.LENGTH_SHORT).show()
            }

        }

        adapter.attachListener(listener)

        observe()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAll()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observe() {
        viewModel.buy.observe(viewLifecycleOwner) {
            adapter.updateTasks(it)
        }
    }

}