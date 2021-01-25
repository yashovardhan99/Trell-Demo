package com.yashovardhan99.trelldemo.profile

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import androidx.core.app.ShareCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.databinding.BindingBuildInfo
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.yashovardhan99.trelldemo.MainActivity
import com.yashovardhan99.trelldemo.databinding.FragmentProfileBinding
import com.yashovardhan99.trelldemo.feed.FeedRepository
import com.yashovardhan99.trelldemo.feed.ImageServiceProvider
import kotlinx.coroutines.flow.flow
import okhttp3.internal.wait
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import java.util.*
import kotlin.concurrent.schedule

class ProfileViewModel @ViewModelInject constructor(repository: ProfileRepository) : ViewModel() {

    var allImages : List<ImageInfo> = emptyList()
    var savedImages : List<ImageInfo> = emptyList()
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
    fun invite(activity: Activity){
        ShareCompat.IntentBuilder.from(activity).setType("text/plain")
            .setChooserTitle("Trell")
            .setText("Hey there! Join me on the Trell app to watch and create vlogs on travel, food, fashion & moview reviews."+"\nhttps://play.google.com/store/apps/details?id=app.trell")
            .startChooser();
    }

    fun goToWebView(context: Context?){
        val intent = Intent(context,WebViewClass::class.java)
        if (context != null) {
            context.startActivity(intent)
        }
    }
}
