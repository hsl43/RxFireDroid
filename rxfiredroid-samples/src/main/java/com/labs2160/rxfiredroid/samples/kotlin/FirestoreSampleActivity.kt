package com.labs2160.rxfiredroid.samples.kotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.google.firebase.FirebaseApp
import com.labs2160.rxfiredroid.firestore.FirebaseFirestore
import io.reactivex.rxkotlin.subscribeBy

class FirestoreSampleActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    FirebaseApp.initializeApp(this)

    val firestore  = FirebaseFirestore.getInstance()

    val startTime = System.currentTimeMillis()

    firestore.rxCollection("users")
        .rxAdd(hashMapOf(
            "first"  to "Bat",
            "last"   to "Man",
            "born"   to 1951
        ))
        .doOnSuccess { Log.d(javaClass.name, "## Added in ${System.currentTimeMillis() - startTime}ms") }
        .flatMap {
          firestore.rxCollection("users")
              .rxWhereEqualTo("last", "Starr")
              .rxGet()
        }
        .doOnSuccess { Log.d(javaClass.name, "## Fetched in ${System.currentTimeMillis() - startTime}ms") }
        .subscribeBy(
            onSuccess = { querySnapshot ->
              querySnapshot.forEach { println("## ${it.getId()} => ${it.getData()}") }
            },
            onError = { error ->
              Log.e(javaClass.name, "## Error adding document.", error)
            }
        )
  }
}