package com.example.beautymnl.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.beautymnl.R
import com.example.beautymnl.data.model.Developer
import com.example.beautymnl.data.model.Status
import com.example.beautymnl.databinding.FragmentIndexBinding
import com.example.beautymnl.ui.activity.MainActivity
import com.example.beautymnl.ui.adapter.MainAdapter
import com.example.beautymnl.ui.viewmodel.DeveloperViewModel
import com.google.gson.Gson

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class IndexFragment : Fragment() {

    var binding: FragmentIndexBinding? = null

    lateinit var viewModel: DeveloperViewModel

    private lateinit var adapter: MainAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentIndexBinding.inflate(inflater, container, false)

        return binding?.root

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = (activity as MainActivity).viewModel

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.fabAdd?.setOnClickListener {
            findNavController().navigate(R.id.action_IndexFragment_to_AddFragment)
        }

        binding?.let {
            it.rvCategoryList.layoutManager = LinearLayoutManager(activity)
            adapter = MainAdapter(arrayListOf(), ::viewDeveloper)
            it.rvCategoryList.addItemDecoration(
                DividerItemDecoration(
                    it.rvCategoryList.context,
                    (it.rvCategoryList.layoutManager as LinearLayoutManager).orientation
                )
            )
            it.rvCategoryList.adapter = adapter
        }

        viewModel.fetchDevelopers()

        viewModel.getDevelopers().observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    adapter.setData(it.data!!)
                    adapter.notifyDataSetChanged()
                }
            }
        })
    }

    fun viewDeveloper (dev : Developer) {
        val bundle = bundleOf("developer" to dev)
        findNavController().navigate(R.id.action_IndexFragment_to_ViewFragment, bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}