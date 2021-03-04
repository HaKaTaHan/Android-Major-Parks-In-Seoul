package com.teamnoyes.majorparksinseoul.main.bookmark

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.teamnoyes.majorparksinseoul.R
import com.teamnoyes.majorparksinseoul.databinding.BookmarkFragmentBinding

class BookmarkFragment : Fragment() {
    private lateinit var bookmarkFragmentBinding: BookmarkFragmentBinding
    private lateinit var bookmarkViewModel: BookmarkViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bookmarkFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.bookmark_fragment, container, false)
        return bookmarkFragmentBinding.root
    }


}