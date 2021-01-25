package com.yashovardhan99.trelldemo.profile

import android.app.Activity
import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import coil.load
import coil.transform.CircleCropTransformation
import com.yashovardhan99.trelldemo.MainActivity
import com.yashovardhan99.trelldemo.R
import com.yashovardhan99.trelldemo.databinding.FragmentProfileBinding
import com.yashovardhan99.trelldemo.feed.*
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.qualifiers.ActivityContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private val viewModel: ProfileViewModel by viewModels()
    var allImages : List<ImageInfo> = emptyList()
    var savedImages : List<ImageInfo> = emptyList()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentProfileBinding.inflate(inflater, container, false)
        binding.cardView.load(R.drawable.avatar) {
            crossfade(true)
            placeholder(R.drawable.circle_image_placeholder)
            transformations(CircleCropTransformation())
        }

        binding.profileRecycler.layoutManager=
            GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
        showAll(binding)

        binding.allTrails.setOnClickListener(View.OnClickListener {
            showAll(binding)
        })

        binding.savedTrails.setOnClickListener(View.OnClickListener {
            showSaved(binding)
        })

        binding.constraintLayout.setOnClickListener(View.OnClickListener {
            val intent = Intent(context,WebViewClass::class.java)
            startActivity(intent)
        })

        binding.v1.setOnClickListener(View.OnClickListener {
            val intent = Intent(context,WebViewClass::class.java)
            startActivity(intent)
        })

        binding.cardView1.setOnClickListener(View.OnClickListener {
            ShareCompat.IntentBuilder.from(activity as MainActivity).setType("text/plain")
                .setChooserTitle("Trell")
                .setText("Hey there! Join me on the Trell app to watch and create vlogs on travel, food, fashion & moview reviews."+"\nhttps://play.google.com/store/apps/details?id=app.trell")
                .startChooser();
        })

        return binding.root
    }

    fun showAll(binding: FragmentProfileBinding){
        binding.u2.visibility = View.GONE;
        binding.u1.visibility = View.VISIBLE;
        if(!allImages.isEmpty()){
            val adapter = ImagesViewAdapter(allImages)

            binding.profileRecycler.adapter=adapter;
        }
        else{
            val request = ImageServiceProvider.provideImageService();
            val callAllImages = request.getImages1(2,10);
            callAllImages.enqueue(object : Callback<List<ImageInfo>> {
                override fun onFailure(call: Call<List<ImageInfo>>, t: Throwable) {
                    TODO("Not yet implemented")
                }

                override fun onResponse(
                    call: Call<List<ImageInfo>>,
                    response: Response<List<ImageInfo>>
                ) {
                    if (response.isSuccessful){
                        allImages = response.body() as List<ImageInfo>
                        val adapter = ImagesViewAdapter(allImages)

                        binding.profileRecycler.adapter=adapter;
                    }
                }

            })
        }
    }

    fun showSaved(binding: FragmentProfileBinding){
        binding.u1.visibility = View.GONE;
        binding.u2.visibility = View.VISIBLE;
        if(!savedImages.isEmpty()){
            val adapter = ImagesViewAdapter(savedImages)

            binding.profileRecycler.adapter=adapter;
        }
        else{
            val request = ImageServiceProvider.provideImageService();
            val callSavedImages = request.getImages1(3,10);
            callSavedImages.enqueue(object : Callback<List<ImageInfo>> {
                override fun onFailure(call: Call<List<ImageInfo>>, t: Throwable) {
                    TODO("Not yet implemented")
                }

                override fun onResponse(
                    call: Call<List<ImageInfo>>,
                    response: Response<List<ImageInfo>>
                ) {
                    if (response.isSuccessful){
                        savedImages = response.body() as List<ImageInfo>
                        val adapter = ImagesViewAdapter(savedImages)

                        binding.profileRecycler.adapter=adapter;
                    }
                }

            })
        }
    }
}