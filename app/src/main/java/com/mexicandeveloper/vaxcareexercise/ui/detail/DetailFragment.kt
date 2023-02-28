package com.mexicandeveloper.vaxcareexercise.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.mexicandeveloper.vaxcareexercise.R
import com.mexicandeveloper.vaxcareexercise.databinding.FragmentDetailBinding
import com.mexicandeveloper.vaxcareexercise.models.Book
import com.mexicandeveloper.vaxcareexercise.ui.main.MainFragment

class DetailFragment : Fragment() {

    companion object {
        const val BOOK = "book"
        fun newInstance(info: Book, fm: FragmentManager) {
            val theFragment = DetailFragment()
            val bundle = Bundle()
            bundle.putParcelable(BOOK, info)
            theFragment.arguments = bundle
            fm.commit {
                replace(R.id.container, theFragment)
                addToBackStack(null)
                setReorderingAllowed(true)
            }

        }
    }

    lateinit var binding: FragmentDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val book: Book = arguments?.getParcelable<Book>(BOOK) ?: Book(0, "", "", "", 0.0, "")
        binding.book = book
    }
}