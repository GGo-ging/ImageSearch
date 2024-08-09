package com.example.searchapplication

import android.app.DownloadManager.Query
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.telecom.Call
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowInsetsAnimation
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.searchapplication.databinding.ActivityMainBinding
import com.example.searchapplication.databinding.FragmentImageSearchBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ImageSearchFragment : Fragment() {

    private lateinit var editTextSearch : EditText
    private lateinit var buttonSearch : Button
    private lateinit var RecyclerView : RecyclerView

    private lateinit var viewModel : ImageSearchFragment

    private lateinit var imageAdapter: ImageAdapter
    private var selectedImages = mutableListOf<Image>()
    private var lastSearchQuery : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_image_search, container, false)

        RecyclerView = view.findViewById(R.id.fragment_IS_RecyclerView_View_Images)
        editTextSearch = view.findViewById(R.id.fragment_IS_et_search)
        buttonSearch = view.findViewById(R.id.fragment_IS_btn_Search)

        imageAdapter = ImageAdapter { image -> onClickSelectedImage(image) }
        RecyclerView.adapter = imageAdapter
        RecyclerView.layoutManager = LinearLayoutManager(context)

        lastSearchQuery?.let { editTextSearch.setText(it) }

        buttonSearch.setOnClickListener {
            val query = editTextSearch.text.toString()
            saveData()
            if (query.isNotEmpty()) {
                lastSearchQuery = query
//                searchImages(query)
                searchImage(query)
            }
        }

        loadData()

        return view
    }

    private fun saveData() {
        val pref = requireActivity().getSharedPreferences("pref", MODE_PRIVATE)
        val edit = pref.edit()
        edit.putString("name", editTextSearch.text.toString())
        edit.apply()
    }

    private fun loadData() {
        val pref = requireActivity().getSharedPreferences("pref", MODE_PRIVATE)
        editTextSearch.setText(pref.getString("name", ""))
    }
//    private fun searchImages(query: String) {
//        CoroutineScope(Dispatchers.IO).launch {
//            try {
//                val response = RetrofitInstance.api.searchImages(query)
//                if (response.isSuccessful) {
//                    val images = response.body()?.documents ?: emptyList()
//                    withContext(Dispatchers.Main) {
//                        imageAdapter.submitList(images.map { Image(it.thumbnailUrl, it.displaySitename, it.datetime) })
//                    }
//                } else {
//                    Log.e("error", "err")
//                }
//            } catch (e: Exception) {
//                Log.e("error", "err : $e")
//            }
//        }
//    }

    private fun searchImage(query: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://dapi.kakao.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        Log.e("baseUrl", "${Retrofit.Builder().baseUrl("")}")
        val apiService = retrofit.create(KakaoApi::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val imageResponse = apiService.searchImages(query)
                withContext(Dispatchers.Main) {
                    val images = imageResponse
                    Log.d("ImageSearch", "Loaded images: ${images}")


                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Log.e("ImageSearch", "onFailure: ${e.message}")
                    Toast.makeText(requireContext(), "요청 실패!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun onClickSelectedImage(imageData: Image) {
        if (selectedImages.contains(imageData)) {
            selectedImages.remove(imageData)
        } else {
            selectedImages.add(imageData)
        }
    }

    override fun onPause() {
        super.onPause()
        saveData() // Fragment가 중지될 때 데이터 저장
    }


    companion object {

    }
}