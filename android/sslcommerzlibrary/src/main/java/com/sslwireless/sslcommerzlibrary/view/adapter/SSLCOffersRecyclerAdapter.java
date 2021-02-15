package com.sslwireless.sslcommerzlibrary.view.adapter;

import android.content.Context;
import android.graphics.Paint;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.sslwireless.sslcommerzlibrary.R;
import com.sslwireless.sslcommerzlibrary.model.response.SSLCOfferModel;
import com.sslwireless.sslcommerzlibrary.view.custom.SSLCCustomTextView;

import java.util.List;

public class SSLCOffersRecyclerAdapter extends RecyclerView.Adapter<SSLCOffersRecyclerAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private List<SSLCOfferModel.DiscountList> mTaskInfo;
    private Context mContext;
    private ClickListener clickListener;
    private int currentPosition = -1, previousPosition;

    public SSLCOffersRecyclerAdapter(Context context, List<SSLCOfferModel.DiscountList> data) {
        inflater = LayoutInflater.from(context);
        this.mTaskInfo = data;
        this.mContext = context;

        //Log.d("TAG", "Data Size: " + mTaskInfo);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_offers_recycler_sslc, parent, false);
        MyViewHolder holder = new MyViewHolder(view, viewType);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        SSLCOfferModel.DiscountList discount = mTaskInfo.get(position);

        if (discount.getIsVisa().equals("1")) {
            holder.visaCard.setVisibility(View.VISIBLE);
        }
        if (discount.getIsAmex().equals("1")) {
            holder.amexCard.setVisibility(View.VISIBLE);
        }
        if (discount.getIsMaster().equals("1")) {
            holder.masterCard.setVisibility(View.VISIBLE);
        }

        holder.title.setText(discount.getDiscountTitle());
        holder.desc.setText("Upto " + discount.getMaxDisAmt() + " | Ends on " + discount.getOfferEndOnDate());

        holder.previousAmount.setText("৳" + discount.getRegularPrice());
        holder.previousAmount.setPaintFlags(holder.previousAmount.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        holder.discountedAmount.setText("৳" + discount.getPayableAMT());
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
        void itemClicked(View v, int position);
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        LinearLayout mainLinearLayout;
        ImageView visaCard, masterCard, amexCard;
        SSLCCustomTextView title, desc, previousAmount, discountedAmount;

        public MyViewHolder(View itemView, int viewType) {
            super(itemView);
            mainLinearLayout = itemView.findViewById(R.id.mainLinearLayout);
            visaCard = itemView.findViewById(R.id.visaCard);
            masterCard = itemView.findViewById(R.id.masterCard);
            amexCard = itemView.findViewById(R.id.amexCard);
            title = itemView.findViewById(R.id.title);
            desc = itemView.findViewById(R.id.desc);
            previousAmount = itemView.findViewById(R.id.previousAmount);
            discountedAmount = itemView.findViewById(R.id.discountedAmount);

            mainLinearLayout.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (clickListener != null) {
                clickListener.itemClicked(v, getPosition());
            }
        }
    }
}