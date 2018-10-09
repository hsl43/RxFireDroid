package com.labs2160.rxfiredroid.samples

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.labs2160.rxfiredroid.samples.kotlin.FirebaseAuthSampleActivity
import com.labs2160.rxfiredroid.samples.kotlin.FirebaseCloudMessagingSampleActivity
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {
  private lateinit var recyclerViewAdapter: RecyclerView.Adapter<*>
  private lateinit var recyclerViewLayoutManager: RecyclerView.LayoutManager

  private inner class MainMenuRecyclerViewAdapter(
      private val menuItems: List<Pair<Int,Class<out Activity>>>

  ) : RecyclerView.Adapter<MainMenuRecyclerViewAdapter.ViewHolder>() {

    private inner class ViewHolder(itemView: ViewGroup) : RecyclerView.ViewHolder(itemView) {
      val menuItemTextTextView: TextView = itemView.findViewById(R.id.main_menu_item_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
      val layout = LayoutInflater
          .from(parent.context)
          .inflate(R.layout.main_menu_item, parent, false) as ViewGroup

      return ViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
      val pair = menuItems[position]

      holder.itemView.setOnClickListener {
        startActivity(Intent(this@MainActivity, pair.second))
      }

      holder.menuItemTextTextView.text = resources.getString(pair.first)
    }

    override fun getItemCount() = menuItems.size
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContentView(R.layout.main_activity)

    main_fab.setOnClickListener { Log.d(javaClass.name, "## TODO") }

    recyclerViewAdapter = MainMenuRecyclerViewAdapter(listOf(
        R.string.main_menu_auth_samples to FirebaseAuthSampleActivity::class.java,
//        R.string.main_menu_firestore_sample to FirebaseFirestoreSampleActivity::class.java,
        R.string.main_menu_messaging_sample to FirebaseCloudMessagingSampleActivity::class.java
    ))

    recyclerViewLayoutManager = LinearLayoutManager(this)

    with(main_menu_recycler_view) {
      adapter = recyclerViewAdapter

      layoutManager = recyclerViewLayoutManager

      setHasFixedSize(true)
    }
  }
}