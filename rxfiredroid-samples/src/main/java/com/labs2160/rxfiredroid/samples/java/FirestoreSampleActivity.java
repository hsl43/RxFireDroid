package com.labs2160.rxfiredroid.samples.java;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import com.labs2160.rxfiredroid.firestore.CollectionReference;
import com.labs2160.rxfiredroid.firestore.FirebaseFirestore;
import com.labs2160.rxfiredroid.firestore.QueryDocumentSnapshot;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class FirestoreSampleActivity extends AppCompatActivity {

  @SuppressLint("CheckResult")
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    final FirebaseFirestore firestore = FirebaseFirestore.Companion.getInstance();

    final String tag = getClass().getName();

    final Map<String,Object> sf = new HashMap<>();
    sf.put("name", "San Francisco");
    sf.put("state", "CA");
    sf.put("country", "USA");
    sf.put("capital", false);
    sf.put("population", 860000);
    sf.put("regions", Arrays.asList("west_coast", "norcal"));

    final Map<String,Object> la = new HashMap<>();
    la.put("name", "Los Angeles");
    la.put("state", "CA");
    la.put("country", "USA");
    la.put("capital", false);
    la.put("population", 3900000);
    la.put("regions", Arrays.asList("west_coast", "socal"));

    final Map<String,Object> dc = new HashMap<>();
    dc.put("name", "Washington D.C.");
    dc.put("state", null);
    dc.put("country", "USA");
    dc.put("capital", true);
    dc.put("population", 680000);
    dc.put("regions", Collections.singletonList("east_coast"));

    final Map<String,Object> tokyo = new HashMap<>();
    tokyo.put("name", "Tokyo");
    tokyo.put("state", null);
    tokyo.put("country", "Japan");
    tokyo.put("capital", true);
    tokyo.put("population", 9000000);
    tokyo.put("regions", Arrays.asList("kanto", "honshu"));

    final Map<String,Object> beijing = new HashMap<>();
    beijing.put("name", "Beijing");
    beijing.put("state", null);
    beijing.put("country", "China");
    beijing.put("capital", true);
    beijing.put("population", 21500000);
    beijing.put("regions", Arrays.asList("jingjinji", "hebei"));

    final CollectionReference cities = firestore.rxCollection("cities");

    cities
        .rxAdd(sf)
        .doOnSuccess(docRef ->
            Log.d(tag, "## Added SF"))
        .flatMap(docRef -> cities.rxAdd(la))
        .doOnSuccess(docRef ->
            Log.d(tag, "## Added LA"))
        .flatMap(docRef -> cities.rxAdd(dc))
        .doOnSuccess(docRef ->
            Log.d(tag, "## Added DC"))
        .flatMap(docRef -> cities.rxAdd(tokyo))
        .doOnSuccess(docRef ->
            Log.d(tag, "## Added Tokyo"))
        .flatMap(docRef -> cities.rxAdd(beijing))
        .doOnSuccess(docRef ->
            Log.d(tag, "## Added Beijing"))
        .flatMap(docRef ->
            cities.rxWhereEqualTo("country", "USA")
                .rxGet())
        .subscribe(
            querySnapshot -> {
              for(QueryDocumentSnapshot qds : querySnapshot) {
                Log.d(tag, "## " + qds.getId() + " => " + qds.getData());
              }
            },
            error -> {
              Log.e(tag, "## Unexpected error!", error);
            }
        );
  }

}
