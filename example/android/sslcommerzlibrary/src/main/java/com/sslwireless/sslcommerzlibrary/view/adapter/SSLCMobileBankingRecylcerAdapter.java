package com.sslwireless.sslcommerzlibrary.view.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.sslwireless.sslcommerzlibrary.R;
import com.sslwireless.sslcommerzlibrary.model.response.SSLCSdkMainResponseModel;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCDownloadImageTask;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCShareInfo;
import com.sslwireless.sslcommerzlibrary.viewmodel.listener.SSLCDowloadImageListener;

import java.util.List;

import static android.content.ContentValues.TAG;

public class SSLCMobileBankingRecylcerAdapter extends RecyclerView.Adapter<SSLCMobileBankingRecylcerAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private List<SSLCSdkMainResponseModel.Desc> mTaskInfo;
    private Context mContext;
    private ClickListener clickListener;
    private int currentPosition = -1, previousPosition;
    private Boolean defaultChecker = false;

    public SSLCMobileBankingRecylcerAdapter(Context context, List<SSLCSdkMainResponseModel.Desc> data) {
        inflater = LayoutInflater.from(context);
        this.mTaskInfo = data;
        this.mContext = context;

        //Log.d("TAG", "Data Size: " + mTaskInfo.size());
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_mobile_banking_recycler_sslc, parent, false);
        MyViewHolder holder = new MyViewHolder(view, viewType);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final SSLCSdkMainResponseModel.Desc datum = mTaskInfo.get(position);

        SSLCDownloadImageTask SSLCDownloadImageTask = new SSLCDownloadImageTask(inflater.getContext(),
                holder.merchantImage, datum.getLogo(), new SSLCDowloadImageListener() {
            @Override
            public void downloadSuccess(Bitmap bitmap) {
                holder.merchantImage.setImageBitmap(bitmap);
            }

            @Override
            public void downloadFailed(String message) {
                SSLCShareInfo.getInstance().showToast(inflater.getContext(), message);
            }
        });

        if (currentPosition != position) {
            holder.selectedContentArea.setVisibility(View.GONE);
        }

        if (!defaultChecker && mTaskInfo.get(position).getAutoselect().equals("1")) {
           // Log.e(TAG, "onBindViewHolder: " + mTaskInfo.get(position).getName());
            defaultChecker = true;
            datum.setSetStatus(true);
            previousPosition = currentPosition;
            currentPosition = holder.getAdapterPosition();

            //Log.e(TAG, "onClick: " + datum.getRedirectGatewayURL());

            holder.selectedContentArea.setVisibility(View.VISIBLE);

            clickListener.itemClicked(holder.selectedContentArea, holder.getAdapterPosition(), mTaskInfo, true);
            //notifyDataSetChanged();
        }

        holder.merchantImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickListener != null) {
                    defaultChecker = true;
                    if (SSLCShareInfo.getInstance().isNetworkAvailable(mContext)) {
                        if (currentPosition == holder.getAdapterPosition() && datum.isSetStatus()) {
                            datum.setSetStatus(false);
                            holder.selectedContentArea.setVisibility(View.GONE);

                            clickListener.itemClicked(holder.selectedContentArea, holder.getAdapterPosition(), mTaskInfo, false);
                            notifyDataSetChanged();
                        } else {
                            datum.setSetStatus(true);
                            previousPosition = currentPosition;
                            currentPosition = holder.getAdapterPosition();

                            //Log.e(TAG, "onClick: " + datum.getRedirectGatewayURL());

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

    public void updateView(ImageView imageView, int pos) {
        imageView.setVisibility(View.GONE);
        //notifyItemChanged(pos);
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

        ImageView merchantImage, selectedContentArea;
        LinearLayout mainMobileBankingLayout;
        View view;

        public MyViewHolder(View itemView, int viewType) {
            super(itemView);
            merchantImage = itemView.findViewById(R.id.merchantImage);
            selectedContentArea = itemView.findViewById(R.id.selectedContentArea);
            mainMobileBankingLayout = itemView.findViewById(R.id.mainMobileBankingLayout);
            view = itemView.findViewById(R.id.view);
        }
    }
}