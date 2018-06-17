package bakingapp.udacity.com.bakingapp;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;


/**
 * A {@link Fragment} that turns recipe data into the main view
 */
public class RecipeFragment extends Fragment {


    Boolean playState = true;
    Recipe currentRecipe;
    Integer stepPosition;
    SimpleExoPlayer player;
    Player.EventListener listener = new Player.EventListener() {
        @Override
        public void onTimelineChanged(Timeline timeline, Object manifest, int reason) {

        }

        @Override
        public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

        }

        @Override
        public void onLoadingChanged(boolean isLoading) {

        }

        @Override
        public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
            playState = playWhenReady;
        }

        @Override
        public void onRepeatModeChanged(int repeatMode) {

        }

        @Override
        public void onShuffleModeEnabledChanged(boolean shuffleModeEnabled) {

        }

        @Override
        public void onPlayerError(ExoPlaybackException error) {

        }

        @Override
        public void onPositionDiscontinuity(int reason) {

        }

        @Override
        public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {

        }

        @Override
        public void onSeekProcessed() {

        }
    };

    public RecipeFragment() {
    }

    @Override
    public void setArguments(@Nullable Bundle args) {
        currentRecipe = (Recipe) args.getSerializable("data");
        stepPosition = args.getInt("start");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        player.stop();
        player.release();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Long position = player.getCurrentPosition();
        outState.putLong("player", position);
        outState.putBoolean("state", playState);
        outState.putSerializable("data", currentRecipe);
        outState.putInt("start", stepPosition);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {

        Long playbackPosition = null;
        if (savedInstanceState != null) {
            currentRecipe = (Recipe) savedInstanceState.getSerializable("data");
            stepPosition = savedInstanceState.getInt("start");
            playState = savedInstanceState.getBoolean("state");
            playbackPosition = savedInstanceState.getLong("player");
        }
        final Step currentStep = currentRecipe.getSteps().get(stepPosition);

        View convertView = inflater.inflate(R.layout.fragment_recipe, container, false);

        final PlayerView playerView = convertView.findViewById(R.id.ex_player);

        Handler mainHandler = new Handler();
        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelection.Factory videoTrackSelectionFactory =
                new AdaptiveTrackSelection.Factory(bandwidthMeter);
        TrackSelector trackSelector =
                new DefaultTrackSelector(videoTrackSelectionFactory);

        player = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector);


        playerView.setPlayer(player);

        if (!currentStep.getVideoURL().isEmpty()) {
            //If their is a video

            DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(getContext(),
                    Util.getUserAgent(getContext(), "bakingapp"));

            Uri uri = Uri.parse(currentStep.getVideoURL());
            MediaSource videoSource = new ExtractorMediaSource.Factory(dataSourceFactory).createMediaSource(uri);

            player.prepare(videoSource);

            if (savedInstanceState != null) {
                player.seekTo(playbackPosition);
                player.setPlayWhenReady(playState);
            }

            player.addListener(listener);
            playerView.setVisibility(PlayerView.VISIBLE);

        } else {
            playerView.setVisibility(PlayerView.GONE);
        }

        final TextView descriptionTextView = convertView.findViewById(R.id.tv_recipe_description);
        descriptionTextView.setText(currentStep.getDescription());

        //Listener navigation
        ImageView left = convertView.findViewById(R.id.navigation_left);
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (stepPosition != 0) {
                    stepPosition--;
                    Step leftStep = currentRecipe.getSteps().get(stepPosition);
                    if (!leftStep.getVideoURL().isEmpty()) {
                        //If their is a video

                        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(getContext(),
                                Util.getUserAgent(getContext(), "bakingapp"));

                        Uri uri = Uri.parse(leftStep.getVideoURL());
                        MediaSource videoSource = new ExtractorMediaSource.Factory(dataSourceFactory).createMediaSource(uri);

                        player.prepare(videoSource);

                        player.addListener(listener);
                        playerView.setVisibility(PlayerView.VISIBLE);

                    } else {
                        playerView.setVisibility(PlayerView.GONE);
                    }

                    descriptionTextView.setText(leftStep.getDescription());
                }

            }
        });

        ImageView right = convertView.findViewById(R.id.navigation_right);
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (stepPosition != currentRecipe.getSteps().size() - 1) {
                    stepPosition++;
                    Step rightStep = currentRecipe.getSteps().get(stepPosition);
                    if (!rightStep.getVideoURL().isEmpty()) {
                        //If their is a video

                        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(getContext(),
                                Util.getUserAgent(getContext(), "bakingapp"));

                        Uri uri = Uri.parse(rightStep.getVideoURL());
                        MediaSource videoSource = new ExtractorMediaSource.Factory(dataSourceFactory).createMediaSource(uri);

                        player.prepare(videoSource);

                        player.addListener(listener);
                        playerView.setVisibility(PlayerView.VISIBLE);

                    } else {
                        playerView.setVisibility(PlayerView.GONE);
                    }

                    descriptionTextView.setText(rightStep.getDescription());
                }

            }
        });

        return convertView;
    }
}

