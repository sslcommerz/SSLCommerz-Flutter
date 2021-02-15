package com.sslwireless.sslcommerzlibrary.view.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.sslwireless.sslcommerzlibrary.R;
import com.sslwireless.sslcommerzlibrary.model.response.SSLCSdkMainResponseModel;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCDownloadImageTask;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCShareInfo;
import com.sslwireless.sslcommerzlibrary.viewmodel.listener.SSLCDowloadImageListener;

import java.util.ArrayList;
import java.util.List;

public class SSLCOtherCardsAdapter extends RecyclerView.Adapter<SSLCOtherCardsAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<SSLCSdkMainResponseModel.Desc> mTaskInfo;
    private Context mContext;
    private ClickListener clickListener;
    public int currentPosition = -1;

    public SSLCOtherCardsAdapter(Context context, ArrayList<SSLCSdkMainResponseModel.Desc> data) {
        inflater = LayoutInflater.from(context);
        this.mTaskInfo = data;
        this.mContext = context;

        //Log.d("TAG", "Data Size: " + mTaskInfo);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item_other_cards_sslc, parent, false);
        MyViewHolder holder = new MyViewHolder(view, viewType);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final SSLCSdkMainResponseModel.Desc desc = mTaskInfo.get(position);
        // if (!desc.getRFlag().contains("-1")) {
        holder.ivCard.setVisibility(View.VISIBLE);
        //  holder.ivGoBack.setVisibility(View.GONE);

        SSLCDownloadImageTask SSLCDownloadImageTask = new SSLCDownloadImageTask(inflater.getContext(),
                holder.ivCard, desc.getLogo(), new SSLCDowloadImageListener() {
            @Override
            public void downloadSuccess(Bitmap bitmap) {
                holder.ivCard.setImageBitmap(bitmap);
            }

            @Override
            public void downloadFailed(String message) {
                SSLCShareInfo.getInstance().showToast(inflater.getContext(), message);
            }
        });
//        } else {
//            holder.ivCard.setVisibility(View.GONE);
//            holder.ivGoBack.setVisibility(View.VISIBLE);
//        }

        if (currentPosition != position) {
            holder.selectedContentArea.setVisibility(View.GONE);
        }

        holder.ivCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickListener != null) {
                    if (SSLCShareInfo.getInstance().isNetworkAvailable(mContext)) {
                        if (currentPosition == holder.getAdapterPosition() && desc.isSetStatus()) {
                            desc.setSetStatus(false);
                            holder.selectedContentArea.setVisibility(View.GONE);

                            clickListener.itemClicked(holder.selectedContentArea, holder.getAdapterPosition(), mTaskInfo, false);
                            notifyDataSetChanged();
                        } else {
                            desc.setSetStatus(true);
                            currentPosition = holder.getAdapterPosition();

                            holder.selectedContentArea.setVisibility(View.VISIBLE);

                            clickListener.itemClicked(holder.selectedContentArea, holder.getAdapterPosition(), mTaskInfo, true);
                            notifyDataSetChanged();
                        }
                    } else {
                        SSLCShareInfo.getInstance().showToast(inflater.getContext(), mContext.getResources().getString(R.string.internet_connection));
                    }
                }
            }
        });

        if (mTaskInfo.size() % 3 == 0) {
            if (position == getItemCount() - 1 || position == getItemCount() - 2
                    || position == getItemCount() - 3) {
                holder.view.setVisibility(View.GONE);
            }
        } else if (mTaskInfo.size() % 3 == 2) {
            if (position == getItemCount() - 1 || position == getItemCount() - 2) {
                holder.view.setVisibility(View.GONE);
            }
        } else if (mTaskInfo.size() % 3 == 1) {
            if (position == getItemCount() - 1) {
                holder.view.setVisibility(View.GONE);
            }
        }
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public int getItemCount() {
        return mTaskInfo.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public interface ClickListener {
        void itemClicked(View v, int position, List<SSLCSdkMainResponseModel.Desc> mTaskInfo, boolean status);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView ivCard, selectedContentArea;
        View view;

        public MyViewHolder(View itemView, int viewType) {
            super(itemView);
            ivCard = itemView.findViewById(R.id.iv_card);
            selectedContentArea = itemView.findViewById(R.id.selectedContentArea);
            //ivGoBack = itemView.findViewById(R.id.iv_go_back);
            view = itemView.findViewById(R.id.view);
        }
    }
}