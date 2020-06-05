package com.studying.storedemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.studying.storedemo.model.Models
import kotlinx.android.synthetic.main.fragment_response_list.*


class ResponseList : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_response_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        list.apply {
            layoutManager = LinearLayoutManager(activity)
            this.adapter = responseAdapter
        }
    }

    fun updateList(generalList: List<Models>) = responseAdapter.update(generalList)


    companion object {
        private val responseAdapter = ResponseAdapter()

        @JvmStatic
        fun newInstance() = ResponseList()
    }
}
