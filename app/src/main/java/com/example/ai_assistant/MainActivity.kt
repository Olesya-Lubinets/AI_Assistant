package com.example.ai_assistant

import android.os.Bundle
import android.view.Menu
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ai_assistant.databinding.ActivityMainBinding
import com.example.ai_assistant.ui.gallery.ChatAdapter
import dagger.hilt.android.AndroidEntryPoint
import androidx.activity.viewModels
import androidx.core.os.bundleOf
import androidx.core.view.GravityCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private val chatViewModel: ChatViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        val chatListRecyclerView: RecyclerView = findViewById(R.id.chatListRecyclerView)

        chatListRecyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = ChatAdapter { chat ->
            navController.navigate(R.id.nav_chat, bundleOf("chatID" to chat.id))
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        }
        chatListRecyclerView.adapter = adapter

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_chat, R.id.nav_slideshow
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)


        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                chatViewModel.listOfChats.collect { chatList ->
                    adapter.submitList(chatList)
                }
            }
        }

        binding.drawerContent.btnNewChat.setOnClickListener {
            navController.navigate(R.id.nav_chat)
        }

        binding.drawerContent.btnNewChat.setOnClickListener {
            chatViewModel.createChat()
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                chatViewModel.chatID.collect { id ->
                    navController.navigate(R.id.nav_chat, bundleOf("chatID" to id))
                    binding.drawerLayout.closeDrawer(GravityCompat.START)
                }
            }
        }


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}