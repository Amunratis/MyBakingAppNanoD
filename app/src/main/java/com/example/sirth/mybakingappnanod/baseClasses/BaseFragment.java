package com.example.sirth.mybakingappnanod.baseClasses;

import android.support.v4.app.Fragment;

import com.google.android.exoplayer2.Timeline;

public abstract class BaseFragment extends Fragment {
    public abstract void onTimelineChanged(Timeline timeline, Object manifest);

    public abstract void onPositionDiscontinuity();
}
