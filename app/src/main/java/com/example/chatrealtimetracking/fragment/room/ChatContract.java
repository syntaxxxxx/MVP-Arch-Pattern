package com.example.chatrealtimetracking.fragment.room;


import com.example.chatrealtimetracking.base.BaseView;
import com.example.chatrealtimetracking.fragment.room.model.DataItem;

import java.util.List;


public interface ChatContract {

    interface Presenter {

        void onFetchListUser();

    }

    interface View extends BaseView {
        void onShowListUser(List<DataItem> data);
        void onShowListEmpty();
        void isError(String message);
    }
}
