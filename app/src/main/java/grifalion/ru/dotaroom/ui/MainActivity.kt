package grifalion.ru.dotaroom.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import grifalion.ru.dotaroom.R
import grifalion.ru.dotaroom.databinding.MainLayoutBinding
import grifalion.ru.dotaroom.network.NetworkConnection

class MainActivity: AppCompatActivity() {
    private lateinit var binding: MainLayoutBinding
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        networkConnection()
        navController = Navigation.findNavController(this,R.id.place_holder)
        binding.bottomNavigationView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.wallpaperDetailFragment -> hideBottomNavigation()
                R.id.detailHeroFragment -> hideBottomNavigation()
                R.id.detailAbilitiesFragment -> hideBottomNavigation()
                else -> showBottomNavigation()
            }
        }
    }

    private fun networkConnection(){
        val inflateLayout = findViewById<View>(R.id.networkError)
        val networkConnection = NetworkConnection(applicationContext)
        networkConnection.observe(this){ isConnected ->
            if(isConnected){
                Toast.makeText(this,"Подключено", Toast.LENGTH_SHORT).show()
                inflateLayout.visibility = View.GONE
                binding.mainLayout.visibility = View.VISIBLE
            } else{
                inflateLayout.visibility = View.VISIBLE
                binding.mainLayout.visibility = View.GONE

            }
        }
    }
    private fun hideBottomNavigation(){
        binding.bottomNavigationView.visibility = View.GONE
    }
    private fun showBottomNavigation(){
        binding.bottomNavigationView.visibility = View.VISIBLE
    }
}