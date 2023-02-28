package com.mexicandeveloper.vaxcareexercise.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.mexicandeveloper.vaxcareexercise.R
import com.mexicandeveloper.vaxcareexercise.databinding.FragmentMainBinding
import com.mexicandeveloper.vaxcareexercise.models.Book
import com.mexicandeveloper.vaxcareexercise.ui.detail.DetailFragment
import com.mexicandeveloper.vaxcareexercise.utils.Resource
import com.mexicandeveloper.vaxcareexercise.utils.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(), RVMainAdapter.ListenerInteraction {

    companion object {
        fun newInstance() = MainFragment()
    }

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getBooks()
        binding.rvMain.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        binding.rvMain.adapter = RVMainAdapter(emptyList(), this)


        viewModel.res.observe(viewLifecycleOwner) {
            (binding.rvMain.adapter as RVMainAdapter).setList(it.data ?: emptyList())
            when (it.status) {
                Status.LOADING -> {
                    binding.pbMain.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    binding.pbMain.visibility = View.GONE
                }
                Status.SUCCESS -> {
                    binding.pbMain.visibility = View.GONE
                }

            }
        }
    }

    override fun onItemClick(item: Book) {
        DetailFragment.newInstance(item, parentFragmentManager)
    }

}