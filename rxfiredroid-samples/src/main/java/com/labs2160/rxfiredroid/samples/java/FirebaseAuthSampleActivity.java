package com.labs2160.rxfiredroid.samples.java;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.google.firebase.FirebaseApp;
import com.labs2160.rxfiredroid.auth.FirebaseAuthAppCompatActivity;
import com.labs2160.rxfiredroid.auth.GitHubAuthConfiguration;
import com.labs2160.rxfiredroid.auth.GoogleAuthConfiguration;
import com.labs2160.rxfiredroid.auth.TwitterAuthConfiguration;
import com.labs2160.rxfiredroid.samples.R;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
import io.reactivex.disposables.CompositeDisposable;
import org.jetbrains.annotations.Nullable;

public class FirebaseAuthSampleActivity extends FirebaseAuthAppCompatActivity {

  private final CompositeDisposable disposables = new CompositeDisposable();

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.firebase_auth_demo_activity);

    findViewById(R.id.email_password_sign_in_button).setOnClickListener(view -> {
      disposables.add(
          firebaseAuth.rxSignInWithEmailAndPassword("...", "...").subscribe(
              authResult -> Log.d(getClass().getName(), "## Sign-in to Firebase with email and password succeeded"),
                   error -> Log.e(getClass().getName(), "## Sign-in to Firebase with email and password failed", error)
          )
      );
    });

    disposables.addAll(
        bindGitHubAuth(false).subscribe(
            authResult -> Log.d(getClass().getName(), "## Sign-in to Firebase with GitHub credentials succeeded"),
                 error -> Log.e(getClass().getName(), "## Sign-in to Firebase with GitHub credentials failed", error)
        ),
        bindGoogleAuth(false).subscribe(
            authResult -> Log.d(getClass().getName(), "## Sign-in to Firebase with Google credentials succeeded"),
                 error -> Log.e(getClass().getName(), "## Sign-in to Firebase with Google credentials failed", error)
        ),
        bindTwitterAuth(false).subscribe(
            authResult -> Log.d(getClass().getName(), "## Sign-in to Firebase with Twitter credentials succeeded"),
                 error -> Log.e(getClass().getName(), "## Sign-in to Firebase with Twitter credentials failed", error)
        )
    );

    findViewById(R.id.sign_out_button).setOnClickListener(view -> firebaseAuth.signOut());

    FirebaseApp.initializeApp(this);
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();

    disposables.clear();
  }

  @Override @Nullable
  protected GitHubAuthConfiguration gitHubAuthConfiguration() {
    final String clientId     = "...";
    final String clientSecret = "...";
    final String callbackUrl  = "...";
    final String scope        = null;

    return new GitHubAuthConfiguration(clientId, clientSecret, callbackUrl, scope);
  }

  @Override @Nullable
  protected View gitHubSignInButton() {
    return findViewById(R.id.git_hub_sign_in_button);
  }

  @Override @Nullable
  protected GoogleAuthConfiguration googleAuthConfiguration() {
    final String requestIdToken  = "...";
    final boolean requestId      = false;
    final boolean requestEmail   = false;
    final boolean requestProfile = false;

    return new GoogleAuthConfiguration(requestIdToken, requestId, requestEmail, requestProfile);
  }

  @Override @Nullable
  protected View googleSignInButton() {
    return findViewById(R.id.google_sign_in_button);
  }

  @Override @Nullable
  protected TwitterAuthConfiguration twitterAuthConfiguration() {
    final String consumerKey    = "...";
    final String consumerSecret = "...";
    final boolean debug         = true;
    final int logLevel          = Log.DEBUG;

    return new TwitterAuthConfiguration(consumerKey, consumerSecret, debug, logLevel);
  }

  @Override @Nullable
  protected TwitterLoginButton twitterLoginButton() {
    return findViewById(R.id.twitter_sign_in_button);
  }

}