package com.example.chatrealtimetracking.fragment.room;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.chatrealtimetracking.BuildConfig;
import com.example.chatrealtimetracking.R;
import com.example.chatrealtimetracking.fragment.room.model.DataItem;
import com.example.chatrealtimetracking.utils.MyFunction;
import de.hdodenhof.circleimageview.CircleImageView;

import java.util.ArrayList;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {

    Context context;
    List<DataItem> dataItems = new ArrayList<>();

    public ChatAdapter(Context context, List<DataItem> dataItems) {
        this.context = context;
        this.dataItems = dataItems;
    }

    public void setData(List<DataItem> data) {
        dataItems.clear();
        dataItems.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup vg, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_chat_room, vg, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(dataItems.get(position));
    }

    @Override
    public int getItemCount() {
        if (dataItems == null) return 0;
        else return dataItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_item_user)
        CircleImageView ivItemUser;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_message)
        TextView tvMessage;
        @BindView(R.id.tv_timestamp)
        TextView tvTimestamp;
        @BindView(R.id.tv_count)
        TextView tvCount;
        @BindView(R.id.tv_level)
        TextView tvLevel;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        private void bind(DataItem data) {
            MyFunction.displayImagesOriginal(ivItemUser, BuildConfig.IMAGES + data.getFoto());
            tvName.setText(data.getNama());
            tvLevel.setText(data.getLevel());
            tvTimestamp.setText(data.getTgl());
        }
    }
}
