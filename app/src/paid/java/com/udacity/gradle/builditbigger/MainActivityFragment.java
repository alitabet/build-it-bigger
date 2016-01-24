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

/**
 * This Fragment is for the Paid flavor of the app.
 * This flavor contains no ads!
 *
 * @author Ali K Thabet
 */
public class MainActivityFragment extends Fragment {

    ProgressBar mBar;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        // Inflate the loading indicator
        mBar = (ProgressBar) root.findViewById(R.id.progress_bar);
        mBar.setVisibility(View.GONE);

        // Load tell joke button and add click listener
        Button button = (Button) root.findViewById(R.id.button_get_joke);
        // When the user clicks the joke button, we first load
        // the interstitial ad only if it is ready, otherwise
        // we go directly to joke.
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBar.setVisibility(View.VISIBLE);
                new EndpointsAsyncTask().execute(new Pair<Context, String>(getActivity(), "Ali"));
            }
        });

        return root;
    }

    // We make sure the progress bar is not visible
    // when we return from the Joke Activity
    @Override
    public void onResume() {
        super.onResume();
        mBar.setVisibility(View.GONE);
    }
}
