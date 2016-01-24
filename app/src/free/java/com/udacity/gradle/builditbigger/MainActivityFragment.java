package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

/**
 * This Fragment is for the Free flavor of the app.
 * The Fragment loads a basic interface where the
 * user can request a joke. When the joke is requested,
 * an interstitial ad is loaded.
 *
 * @author Ali K Thabet
 */
public class MainActivityFragment extends Fragment {

    private static final String LOG_TAG = MainActivityFragment.class.getSimpleName();

    private InterstitialAd mInterstitialAd; // Interstitial Add
    private ProgressBar mBar; // Progress Bar

    private boolean isBarVisible; // Boolean to set bar visibility

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        // Inflate the loading indicator.
        // Bar is initially not visible
        mBar = (ProgressBar) root.findViewById(R.id.progress_bar);
        mBar.setVisibility(View.GONE);
        isBarVisible = false;

        // Create an Interstitial add:
        mInterstitialAd = new InterstitialAd(getActivity());
        mInterstitialAd.setAdUnitId(getString(R.string.banner_ad_unit_id));

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                tellJoke();
                // We set the visibility of the bar to true here.
                // This is done to differentiate between closing
                // the ad or returning from the Jokes Activity
                isBarVisible = true;
            }
        });

        requestNewInterstitial();

        // Load tell joke button and add click listener
        Button button = (Button) root.findViewById(R.id.button_get_joke);

        // When the user clicks the joke button, we first load
        // the interstitial ad only if it is ready, otherwise
        // we go directly to joke.
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBar.setVisibility(View.VISIBLE);
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    tellJoke();
                }
            }
        });

        return root;
    }

    private void requestNewInterstitial() {
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("82D16487617CC9DC156E606E42A0B36B") // my Nexus 5X
                .build();
        mInterstitialAd.loadAd(adRequest);
    }

    // Tell Joke! Execute AsyncTask to get joke and start Joke Activity
    private void tellJoke(){
        new EndpointsAsyncTask().execute(new Pair<Context, String>(getActivity(), "Ali"));
    }

    // Since in the free version we have an interstitial ad,
    // when the activity resumes, we check whether it is
    // returning from the ad or the Joke Activity. If it
    // returns from the interstitial ad, then we need to keep
    // the bar visible
    @Override
    public void onResume() {
        super.onResume();
        if (isBarVisible) {
            // We're back from the interstitial ad
            // so we don't disappear the bar, but
            // we set its visibility boolean to false
            // in order to remove it from the screen
            // once we return from the Joke Activity
            isBarVisible = false;
        } else {
            mBar.setVisibility(View.GONE);
        }
    }
}
