package com.example.campuschat.ui.home

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.campuschat.CCApplication
import com.example.campuschat.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_home.*
import kotlin.concurrent.thread

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters

    // Chat Example
    private val chat = mutableListOf<Chat>(Chat(
        "ex01",
        R.drawable.nav_icon,
        "喵小白",
        "pizyj",
        "2时",
        "猫猫！",
        listOf("belzhebub"),
        listOf("萌宠"),
        R.drawable.chat_picture_example,
        5,
        10,
        16
    ))
    private val chat_list = ArrayList<Chat>()
    private lateinit var swipeRefresh: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // FAB
        val fab = view.findViewById<FloatingActionButton>(R.id.home_fab)
        fab.setOnClickListener {
            Snackbar.make(it, "Data deleted", Snackbar.LENGTH_SHORT)
                .setAction("Undo") {
                    Toast.makeText(activity, "FAB CLICKED", Toast.LENGTH_SHORT).show()
                }.show()
        }

        // recycle view
        initChat()
        val layoutManager = GridLayoutManager(CCApplication.context, 1)
        val recyclerView = view.findViewById<RecyclerView>(R.id.home_recycle_view)
        recyclerView.layoutManager = layoutManager
        val adapter = ChatAdapter(CCApplication.context, chat_list)
        recyclerView.adapter = adapter

        swipeRefresh = view.findViewById(R.id.home_swipe_refresh)
        swipeRefresh.setColorSchemeResources(R.color.theme_blue)
        swipeRefresh.setOnRefreshListener {
            refreshChat(adapter)
        }

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun initChat(){
        chat_list.clear()

        // this is an example
        repeat(40) {
            chat_list.add(chat[0])
        }

        // refresh chat_list here

    }

    private fun refreshChat(adapter: ChatAdapter) {
        thread {
            Thread.sleep(1000)
            activity?.runOnUiThread {
                initChat()
                adapter.notifyDataSetChanged()
                swipeRefresh.isRefreshing = false
            }
        }
    }
}