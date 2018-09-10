//package com.labs2160.rxfiredroid.samples
//
//import android.os.Bundle
//import android.support.v7.app.AppCompatActivity
//import android.util.Log
//import com.google.firebase.FirebaseApp
//import com.labs2160.rxfirebase.firestore.FirebaseFirestore
//import com.labs2160.rxfirebase.firestore.FirebaseFirestoreImpl
//
//class FirestoreDemoActivity : AppCompatActivity() {
//  override fun onCreate(savedInstanceState: Bundle?) {
//    super.onCreate(savedInstanceState)
//
//    FirebaseApp.initializeApp(this)
//
//    val firestore  = FirebaseFirestore.getInstance()
//
//    val startTime = System.currentTimeMillis()
//
////    db.collection("users")
////        .add(hashMapOf(
////            "first"  to "Edwin",
////            "last"   to "Starr",
////            "born"   to 1812
////        ))
////        .addOnCompleteListener { task ->
////          Log.d(javaClass.name, "## Done in ${System.currentTimeMillis() - startTime}ms")
////
////          if(task.isSuccessful) {
////            Log.d(javaClass.name, "## DocumentSnapshot added with ID: ${task.result.id}.")
////          } else {
////            Log.e(javaClass.name, "## Error adding document.", task.exception)
////          }
////        }
//
////    db.rxCollection("users")
////        .rxAdd(hashMapOf(
////            "first"  to "Edwin",
////            "last"   to "Starr",
////            "born"   to 1812
////        ))
////        .doOnSuccess { Log.d(javaClass.name, "## Done in ${System.currentTimeMillis() - startTime}ms") }
////        .subscribeBy(
////            onSuccess = {
////              Log.d(javaClass.name, "## DocumentSnapshot added with ID: ${it.getId()}.")
////            },
////            onError = { error ->
////              Log.e(javaClass.name, "## Error adding document.", error)
////            }
////        )
//
////    db.collection("users")
////        .get()
////        .addOnCompleteListener { task ->
////          Log.d(javaClass.name, "## Done in ${System.currentTimeMillis() - startTime}ms")
////
////          if(task.isSuccessful) {
////            task.result.forEach { document ->
////              Log.d(javaClass.name, "## ${document.id} => ${document.data}")
////            }
////
////          } else {
////            Log.e(javaClass.name, "## Error getting documents.", task.exception)
////          }
////        }
//
//    firestore.rxCollection("users")
//        .rxWhereEqualTo("last", "Starr")
//        .rxGet()
//        .doOnSuccess { Log.d(javaClass.name, "## Done in ${System.currentTimeMillis() - startTime}ms") }
//        .subscribeBy(
//            onSuccess = {
//              Log.d(javaClass.name, "## QuerySnapshot.isEmpty ? ${it.isEmpty()}")
//
//              it
//              it.getDocumentChanges().forEach { document ->
//                Log.d(javaClass.name, "## ${document.id} => ${document.data}")
//              }
//            },
//            onError = { error ->
//              Log.e(javaClass.name, "## Error getting documents.", error)
//            }
//        )
//  }
//}