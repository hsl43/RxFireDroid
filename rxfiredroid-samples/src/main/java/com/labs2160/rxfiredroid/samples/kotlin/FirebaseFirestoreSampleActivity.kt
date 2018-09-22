package com.labs2160.rxfiredroid.samples.kotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.google.firebase.FirebaseApp
import com.labs2160.rxfiredroid.firestore.FirebaseFirestore
import io.reactivex.rxkotlin.subscribeBy

class FirebaseFirestoreSampleActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    FirebaseApp.initializeApp(this)

    val firestore  = FirebaseFirestore.getInstance()

    val cities = firestore.rxCollection("cities")

    val tag = javaClass.name

    cities
        .rxAdd(hashMapOf(
            "name"       to "San Fransisco",
            "state"      to "CA",
            "country"    to "USA",
            "capital"    to false,
            "population" to 860000,
            "regions"    to listOf("west_coast", "norcal")
        ))
        .doOnSuccess { Log.d(tag, "## Added SF") }
        .flatMap { cities.rxAdd(hashMapOf(
            "name"       to "Los Angeles",
            "state"      to "CA",
            "country"    to "USA",
            "capital"    to false,
            "population" to 3900000,
            "regions"    to listOf("west_coast", "socal")
        ))}
        .doOnSuccess { Log.d(tag, "## Added LA") }
        .flatMap { cities.rxAdd(hashMapOf(
            "name"       to "Washington D.C.",
            "state"      to null,
            "country"    to "USA",
            "capital"    to true,
            "population" to 680000,
            "regions"    to listOf("east_coast")
        ))}
        .doOnSuccess { Log.d(tag, "## Added DC") }
        .flatMap { cities.rxAdd(hashMapOf(
            "name"       to "Tokyo",
            "state"      to null,
            "country"    to "Japan",
            "capital"    to true,
            "population" to 9000000,
            "regions"    to listOf("kanto", "honshu")
        ))}
        .doOnSuccess { Log.d(tag, "## Added Tokyo") }
        .flatMap { cities.rxAdd(hashMapOf(
            "name"       to "Beijing",
            "state"      to null,
            "country"    to "China",
            "capital"    to true,
            "population" to 21500000,
            "regions"    to listOf("jingjinji", "hebei")
        ))}
        .doOnSuccess { Log.d(tag, "## Added Beijing") }
        .flatMap {
          cities.rxWhereEqualTo("country", "USA")
              .rxGet()
        }
        .subscribeBy(
            onSuccess = { querySnapshot ->
              querySnapshot.forEach { Log.d(tag, "## ${it.getId()} => ${it.getData()}") }
            },
            onError = { error ->
              Log.e(tag, "## Unexpected error!", error)
            }
        )
  }
}