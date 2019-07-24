package com.example.chatrealtimetracking.fragment.room;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.example.chatrealtimetracking.R;
import com.example.chatrealtimetracking.fragment.room.model.DataItem;
import com.example.chatrealtimetracking.utils.MyFunction;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChatFragment extends Fragment implements ChatContract.View {


    @BindView(R.id.recyclerview_chat)
    RecyclerView recyclerviewChat;
    Unbinder unbinder;

ChatPresenter presenter;

    public ChatFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chat_fragmeny, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        initPresenter();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter.onFetchListUser();
    }

    private void initPresenter() {
        presenter = new ChatPresenter(this);
    }

    @Override
    public void onAttachView() {
        presenter.onAttach(this);
    }

    @Override
    public void onDettachView() {
        presenter.onDettach();
    }

    @Override
    public void onShowListUser(List<DataItem> data) {
        recyclerviewChat.setHasFixedSize(true);
        ChatAdapter adapter = new ChatAdapter(getContext(), data);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerviewChat.setLayoutManager(layoutManager);
        recyclerviewChat.setAdapter(adapter);
    }

    @Override
    public void onShowListEmpty() {
        MyFunction.toast(getContext(), getString(R.string.isEmptyListUser));
    }

    @Override
    public void isError(String message) {
        MyFunction.toast(getContext(), message);
    }

    @Override
    public void onDestroy() {
        onDettachView();
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
