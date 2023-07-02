package com.example.buylist2.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.buylist2.R
import com.example.buylist2.ui.activities.LoginActivity
import com.example.buylist2.databinding.FragmentUserRegisterBinding
import com.example.buylist2.viewModel.UserRegisterViewModel

class UserRegisterFragment : Fragment() {


    private lateinit var viewModel: UserRegisterViewModel

    private var _binding: FragmentUserRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel = ViewModelProvider(this.requireActivity()).get(UserRegisterViewModel::class.java)

        _binding = FragmentUserRegisterBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //Save data to Login

        handleRegister()

        observe()


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observe() {
        viewModel.register.observe(this) {
            if (it.status()) {
                startActivity(Intent(context, LoginActivity::class.java))
                Toast.makeText(
                    context, R.string.successful_registration,
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(context, it.message(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun handleRegister() {

        binding.buttonSave.setOnClickListener{
            val email = binding.editEmail.text.toString()
            val password = binding.editPassword.text.toString()

            viewModel.create(email, password)
        }

    }
}
