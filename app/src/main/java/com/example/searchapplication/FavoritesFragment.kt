package com.example.searchapplication

import android.os.Bundle
import android.provider.ContactsContract.RawContacts.Data
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.searchapplication.databinding.FragmentFavoritesBinding
import com.example.searchapplication.databinding.FragmentImageSearchBinding


class FavoritesFragment : Fragment() {


    private lateinit var recyclerViewFavorites : RecyclerView
    private lateinit var favoritesAdapter: ImageAdapter
    private var selectedImage : MutableList<Image> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_favorites, container, false)

        recyclerViewFavorites = view.findViewById(R.id.recyclerViewFavorites)
        favoritesAdapter = ImageAdapter{ image -> onSelcetedImage(image) }
        recyclerViewFavorites.adapter = favoritesAdapter
        recyclerViewFavorites.layoutManager = LinearLayoutManager(context)

        return view
    }


    private fun onSelcetedImage(image: Image) {
        selectedImage.remove(image)
        favoritesAdapter.submitList(selectedImage)
        favoritesAdapter.notifyDataSetChanged()
    }


    companion object {

    }
}