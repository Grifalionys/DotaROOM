package grifalion.ru.dotaroom.ui.fragments.wallpaper.detail

import android.app.DownloadManager
import android.app.WallpaperManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import grifalion.ru.dotaroom.databinding.FragmentDetailWallpaperBinding
import grifalion.ru.dotaroom.databinding.FragmentWallpaperBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File

class WallpaperDetailFragment: Fragment() {
    private lateinit var binding: FragmentDetailWallpaperBinding
    private lateinit var wallpaper: String
    private var permission = 0
    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()){
        permission = if(it){
            1
        } else{
            0
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        wallpaper = arguments?.getString("wall")!!
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentDetailWallpaperBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initImagesFun()
    }

    private fun initImagesFun(){
        imageShare()
        showImages()
        downloadImage()
        setBackground()
    }

    private fun showImages(){
        Glide.with(binding.imFullWallpaper)
            .load(wallpaper)
            .into(binding.imFullWallpaper)
    }
    private fun setBackground() {
        binding.btnSetWall.setOnClickListener {
            Toast.makeText(requireContext(),"Обои успешно установлены", Toast.LENGTH_SHORT).show()
            lifecycleScope.launch(Dispatchers.IO) {
                val drawable = Glide.with(requireContext())
                    .asBitmap()
                    .load(wallpaper)
                    .submit()
                    .get()

                val wallpaperManager = WallpaperManager.getInstance(context)
                try {
                    wallpaperManager.setBitmap(drawable)
                } catch (e: java.lang.Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
    private fun downloadImage(){
        requestPermissionLauncher.launch(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
        binding.btnDownloadImage.setOnClickListener{
            if(permission==1){
                try {
                    val downloadManager = activity?.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
                    val imageLink = Uri.parse(wallpaper)
                    val nameImage = wallpaper.substring(60)
                    val request = DownloadManager.Request(imageLink)
                    request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE or DownloadManager.Request.NETWORK_WIFI)
                        .setMimeType("images/jpeg")
                        .setAllowedOverRoaming(false)
                        .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                        .setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES, File.separator+nameImage+".jpg")
                    downloadManager.enqueue(request)
                    Toast.makeText(requireContext(),"Скачивается...", Toast.LENGTH_SHORT).show()
                }catch (e:Exception){
                }
            } else{
                Toast.makeText(requireContext(),"Ошибка", Toast.LENGTH_SHORT).show()
            }
        }

    }
    private fun imageShare(){
        binding.btnShare.setOnClickListener{
            var shareIntent = Intent().apply {
                this.action = Intent.ACTION_SEND
                this.putExtra(Intent.EXTRA_TEXT,wallpaper)
                this.type = "text/plain"
            }
            startActivity(shareIntent)
        }
    }

}