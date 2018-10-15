package com.labs2160.rxfiredroid.samples.kotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.labs2160.rxfiredroid.messaging.FirebaseMessaging
import com.labs2160.rxfiredroid.samples.R
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.firebase_messaging_activity.*

class FirebaseCloudMessagingSampleActivity : AppCompatActivity() {
  private val disposables = CompositeDisposable()

  private val topicStates = arrayListOf<TopicState>()

  private lateinit var firebaseMessaging: FirebaseMessaging
  private lateinit var recyclerViewAdapter: RecyclerView.Adapter<*>
  private lateinit var recyclerViewLayoutManager: RecyclerView.LayoutManager

  private class TopicState(val name: String, val state: String)

  private inner class TopicsListRecyclerViewAdapter : RecyclerView.Adapter<TopicsListRecyclerViewAdapter.ViewHolder>() {
    private inner class ViewHolder(itemView: ViewGroup) : RecyclerView.ViewHolder(itemView) {
      val topicName: TextView = itemView.findViewById(R.id.messaging_topic_list_item_topic_name_text_view)
      val subscriptionState: ImageView = itemView.findViewById(R.id.messaging_topic_list_item_topic_state_image_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
      val layout = LayoutInflater
          .from(parent.context)
          .inflate(R.layout.firebase_messaging_topic_list_item, parent, false) as ViewGroup

      return ViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
      val topicState = topicStates[position]

      holder.topicName.text = topicState.name

      when(topicState.state) {
        SUBSCRIBING -> holder.subscriptionState.setImageResource(R.drawable.circle_gray)
        SUBSCRIBED  -> holder.subscriptionState.setImageResource(R.drawable.circle_green)
        ERROR       -> holder.subscriptionState.setImageResource(R.drawable.circle_red)
      }
    }

    override fun getItemCount() = topicStates.size
  }

  companion object {
    private const val SUBSCRIBING = "SUBSCRIBING"
    private const val SUBSCRIBED  = "SUBSCRIBED"
    private const val ERROR       = "ERROR"
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    firebaseMessaging = FirebaseMessaging.getInstance()

    setContentView(R.layout.firebase_messaging_activity)

    disposables.add(
        firebaseMessaging.rxBindRegistrationToken()
            .subscribeBy(
                onNext = { token ->
                  messaging_registration_token_text_view.text = token
                },
                onError = { error ->
                  Log.e(javaClass.name, "## Error binding registration token", error)
                }
            )
    )

    recyclerViewAdapter = TopicsListRecyclerViewAdapter()

    recyclerViewLayoutManager = LinearLayoutManager(this)

    with(messaging_topics_recycler_view) {
      adapter = recyclerViewAdapter

      layoutManager = recyclerViewLayoutManager
    }

    messaging_add_topic_button.setOnClickListener { _ ->
      messaging_add_topic_edit_text.text?.toString()?.let { topicName ->
        if(topicStates.asSequence().map { it.name }.contains(topicName)) {
          Toast.makeText(this, "A subscription to '$topicName' already exists", Toast.LENGTH_LONG).show()
        } else {
          topicStates.add(TopicState(name = topicName, state = SUBSCRIBING))

          val position = topicStates.size - 1

          recyclerViewAdapter.notifyDataSetChanged()

          disposables.add(
              firebaseMessaging.rxSubscribeToTopic(topicName)
                  .subscribeBy(
                      onComplete = {
                        topicStates[position] = TopicState(name = topicName, state = SUBSCRIBED)

                        recyclerViewAdapter.notifyItemChanged(position)

                        messaging_add_topic_edit_text.setText("")
                      },
                      onError = { _ ->
                        topicStates[position] = TopicState(name = topicName, state = ERROR)

                        recyclerViewAdapter.notifyItemChanged(position)
                      }
                  )
          )
        }
      }
    }
  }

  override fun onDestroy() {
    super.onDestroy()

    disposables.clear()
  }
}