/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.hajaulee.jsubanime;

import android.os.Bundle;
import android.support.v17.leanback.app.VideoSupportFragment;
import android.support.v17.leanback.app.VideoSupportFragmentGlueHost;
import android.support.v17.leanback.media.MediaPlayerGlue;
import android.support.v17.leanback.media.PlaybackGlue;

/**
 * Handles video playback with media controls.
 */
public class PlaybackVideoFragment extends VideoSupportFragment {
    private static final String TAG = "PlaybackVideoFragment";

    private MediaPlayerGlue mTransportControlGlue;
    public Movie movie;
    public VideoSupportFragmentGlueHost glueHost;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        movie = (Movie) getActivity()
                .getIntent().getSerializableExtra(DetailsActivity.MOVIE);

        glueHost = new VideoSupportFragmentGlueHost(PlaybackVideoFragment.this);
        glueHost.setControlsOverlayAutoHideEnabled(true);
        glueHost.setOnActionClickedListener(new MediaPlayerGlue(getContext()));
        glueHost.showControlsOverlay(true);

        mTransportControlGlue = new MediaPlayerGlue(getActivity());
        mTransportControlGlue.setMode(MediaPlayerGlue.NO_REPEAT);
        mTransportControlGlue.setHost(glueHost);
        mTransportControlGlue.setTitle(movie.getTitle());
        mTransportControlGlue.setArtist(movie.getDescription());
//        mTransportControlGlue.enableProgressUpdating(true);
//        mTransportControlGlue.addPlayerCallback(
//                new PlaybackGlue.PlayerCallback() {
//                    @Override
//                    public void onPreparedStateChanged(final PlaybackGlue glue) {
//                        if (glue.isPrepared()) {
//                            glue.play();
//                        }
//                    }
//                });
//        mTransportControlGlue.setVideoUrl(movie.getVideoUrl());


    }


    @Override
    public void onPause() {
        super.onPause();
        if (mTransportControlGlue != null) {
            mTransportControlGlue.pause();
        }
    }
}