package com.example.searchapplication

import android.media.Image
import android.os.Bundle
import android.text.TextUtils.replace
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.searchapplication.databinding.ActivityMainBinding
import retrofit2.http.Url
import com.kakao.sdk.common.util.Utility

class MainActivity : AppCompatActivity() {

    private val selectedImages = mutableListOf<Image>()
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainBtnSearch : Button
    private lateinit var mainBtnFavorites : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var keyHash = Utility.getKeyHash(this)
        Log.e("keyHash", "$keyHash")

        mainBtnSearch = findViewById(R.id.main_btn_search)
        mainBtnFavorites = findViewById(R.id.main_btn_favorites)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout_container, ImageSearchFragment())
                .commit()
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayout_container, ImageSearchFragment())
            .commit()


        mainBtnSearch.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout_container, ImageSearchFragment())
                .commit()
        }

        mainBtnFavorites.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout_container, FavoritesFragment())
                .commit()
        }
    }

    fun onSelectedImage(image: Image) {
        if (selectedImages.contains(image)) {
            selectedImages.remove(image)
        } else {
            selectedImages.add(image)
        }
    }

    fun changedFarvoriteFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayout_container, FavoritesFragment())
            .addToBackStack(null)
            .commit()
    }

}