package com.example.beautymnl.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.beautymnl.R
import com.example.beautymnl.data.model.Developer
import com.example.beautymnl.data.model.Status
import com.example.beautymnl.databinding.FragmentAddBinding
import com.example.beautymnl.ui.activity.MainActivity
import com.example.beautymnl.ui.viewmodel.DeveloperViewModel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class AddFragment : Fragment() {

    private var _binding: FragmentAddBinding? = null
    lateinit var viewModel: DeveloperViewModel

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        viewModel.init()
        viewModel.developer.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    val bundle = bundleOf("developer" to it.data)
                    findNavController().navigate(R.id.action_AddFragment_to_ViewFragment, bundle)
                }
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSave.setOnClickListener {
                viewModel.addDeveloper(Developer(
                    id = (Math.random() * 100).toInt(),
                    name = binding.etName.text.toString(),
                    phone = binding.etPhone.text.toString(),
                    email = binding.etEmail.text.toString(),
                    company = binding.etEmail.text.toString()))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}