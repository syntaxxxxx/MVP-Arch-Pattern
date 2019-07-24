package com.example.chatrealtimetracking.fragment.report;


import com.example.chatrealtimetracking.base.BaseView;

public interface PengaduanContract {

    interface Presenter {
        void addPengaduan(String isi, String file, String pengunaan_id, String admin_id);
    }

    interface View extends BaseView {

        void onShowSuccess(String message);
        void onShowError(String message);

    }
}
