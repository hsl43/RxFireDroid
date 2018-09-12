package com.labs2160.rxfiredroid.samples.java;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.labs2160.rxfiredroid.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;

public class FirestoreSampleActivity extends AppCompatActivity {

  @SuppressLint("CheckResult")
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    final FirebaseFirestore firestore = FirebaseFirestore.Companion.getInstance();

    final long startTime = System.currentTimeMillis();

    final Map<String,Object> person = new HashMap<>();
    person.put("first", "Bomani");
    person.put("last",  "Jones");
    person.put("born",  1980);

    firestore
        .rxCollection("users")
        .rxAdd(person)
        .doOnSuccess(blah -> System.out.println("## Added in " + (System.currentTimeMillis() - startTime) + "ms"))
        .flatMap(docRef -> firestore.rxCollection("users")
            .rxWhereEqualTo("last", "Starr")
            .rxGet()
        )
        .doOnSuccess(blah -> System.out.println("## Fetched in " + (System.currentTimeMillis() - startTime) + "ms"))
        .subscribe(
            querySnapshot -> querySnapshot.forEach(snapshot -> {
              System.out.println("## " + snapshot.getId() + " => " + snapshot.getData());
            }),
            error -> System.out.println("## Unexpected error")
        );
  }

}
