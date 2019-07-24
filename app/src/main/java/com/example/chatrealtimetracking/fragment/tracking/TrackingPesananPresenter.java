package com.example.chatrealtimetracking.fragment.tracking;


import com.example.chatrealtimetracking.base.BasePresenter;

public class TrackingPesananPresenter implements BasePresenter<TrackingPesananContract.View> {

    TrackingPesananContract.View trackingPesananView;

    public TrackingPesananPresenter(TrackingPesananContract.View trackingPesananView) {
        this.trackingPesananView = trackingPesananView;
    }

    @Override
    public void onAttach(TrackingPesananContract.View view) {
        trackingPesananView = view;
    }

    @Override
    public void onDettach() {
        trackingPesananView = null;
    }
}
