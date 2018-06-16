package bakingapp.udacity.com.bakingapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.io.Serializable;


/**
 * A {@link Fragment} that turns recipe data into the main view
 */
public class RecipeFragment extends Fragment {

    Step currentStep;
    SimpleExoPlayer player;
    onNavClicked listener;

    public RecipeFragment() {
    }

    @Override
    public void setArguments(@Nullable Bundle args) {
        currentStep = (Step) args.getSerializable("data");
        listener = (onNavClicked) args.getSerializable("listener");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(player != null) {
            player.release();
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Long position = player.getCurrentPosition();
        outState.putLong("player",position);
        outState.putSerializable("step",currentStep);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(player != null) {
            player.setPlayWhenReady(true);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Context context = getContext();

        if(savedInstanceState != null){
            currentStep = (Step) savedInstanceState.getSerializable("step");
        }
        View inflatedView = inflater.inflate(R.layout.fragment_recipe, container, false);

        if (listener != null) {
            ImageView left = inflatedView.findViewById(R.id.navigation_left);
            left.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onLeftClicked();
                }
            });

            ImageView right = inflatedView.findViewById(R.id.navigation_right);
            right.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onRightClicked();
                }
            });
        }

        TextView descriptionTextView = inflatedView.findViewById(R.id.tv_recipe_description);
        descriptionTextView.setText(currentStep.getDescription());

        PlayerView playerView = inflatedView.findViewById(R.id.ex_player);
        if (!currentStep.getVideoURL().isEmpty()) {

            Handler mainHandler = new Handler();
            BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
            TrackSelection.Factory videoTrackSelectionFactory =
                    new AdaptiveTrackSelection.Factory(bandwidthMeter);
            TrackSelector trackSelector =
                    new DefaultTrackSelector(videoTrackSelectionFactory);

            player =
                    ExoPlayerFactory.newSimpleInstance(context, trackSelector);


            playerView.setPlayer(player);

            DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(context,
                    Util.getUserAgent(context, "bakingapp"));

            Uri uri = Uri.parse(currentStep.getVideoURL());
            Log.v("URI", uri.toString());


            MediaSource videoSource = new ExtractorMediaSource.Factory(dataSourceFactory).createMediaSource(uri);

            player.prepare(videoSource);

        } else {
            playerView.setVisibility(PlayerView.GONE);
        }
        if(savedInstanceState != null){

            player.seekTo(savedInstanceState.getLong("player"));
        }
        return inflatedView;
    }

    public void setData(Step s) {

        currentStep = s;

        Context context = getContext();


        View inflatedView = getView();

        TextView descriptionTextView = inflatedView.findViewById(R.id.tv_recipe_description);
        descriptionTextView.setText(currentStep.getDescription());

        PlayerView playerView = inflatedView.findViewById(R.id.ex_player);
        if (!currentStep.getVideoURL().isEmpty()) {

            playerView.setVisibility(PlayerView.VISIBLE);
            Handler mainHandler = new Handler();
            BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
            TrackSelection.Factory videoTrackSelectionFactory =
                    new AdaptiveTrackSelection.Factory(bandwidthMeter);
            TrackSelector trackSelector =
                    new DefaultTrackSelector(videoTrackSelectionFactory);

            player =
                    ExoPlayerFactory.newSimpleInstance(context, trackSelector);


            playerView.setPlayer(player);

            DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(context,
                    Util.getUserAgent(context, "bakingapp"));

            Uri uri = Uri.parse(currentStep.getVideoURL());
            Log.v("URI", uri.toString());


            MediaSource videoSource = new ExtractorMediaSource.Factory(dataSourceFactory).createMediaSource(uri);

            player.prepare(videoSource);

        } else {
            playerView.setVisibility(PlayerView.GONE);
        }
    }

    public interface onNavClicked extends Serializable {
        public void onLeftClicked();

        public void onRightClicked();
    }
}
