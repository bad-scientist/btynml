package com.example.beautymnl.ui.fragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.beautymnl.R
import com.example.beautymnl.data.model.Status
import com.example.beautymnl.databinding.FragmentViewBinding
import com.example.beautymnl.ui.activity.MainActivity
import com.example.beautymnl.ui.viewmodel.DeveloperViewModel


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class ViewFragment : Fragment() {

    private var _binding: FragmentViewBinding? = null
    lateinit var viewModel: DeveloperViewModel

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentViewBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true /* enabled by default */) {
                override fun handleOnBackPressed() {
                    findNavController().navigate(R.id.action_ViewFragment_to_IndexFragment)
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)

        viewModel = (activity as MainActivity).viewModel
        viewModel.init()

        viewModel.message.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    Toast.makeText(context, "Success", Toast.LENGTH_LONG).show()
                    findNavController().navigate(R.id.action_ViewFragment_to_IndexFragment)
                }
                Status.ERROR -> {
                    Toast.makeText(context, "Error Occur! Please try again.", Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.developer = requireArguments().getParcelable("developer")
        Glide.with(this).load(binding.developer?.photo).into(binding.imageView)

        binding.btnEdit.setOnClickListener {
            val bundle = bundleOf("developer" to binding.developer)
            findNavController().navigate(R.id.action_ViewFragment_to_EditFragment, bundle)
        }

        binding.btnDelete.setOnClickListener {
            val builder: AlertDialog.Builder = AlertDialog.Builder(context)
            builder.setMessage("Are you sure? ${binding.developer!!.id}").setPositiveButton("Yes") { dialog, which ->
                viewModel.deleteDeveloper(binding.developer!!.id)
            }
            .setNegativeButton("No") { dialog, which ->
                dialog.dismiss();
            }.show()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}