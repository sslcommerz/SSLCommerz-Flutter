package com.sslwireless.sslcommerzlibrary.view.adapter;

import android.content.Context;
import android.graphics.Color;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sslwireless.sslcommerzlibrary.R;
import com.sslwireless.sslcommerzlibrary.model.response.SSLCOfferModel;
import com.sslwireless.sslcommerzlibrary.model.response.SSLCSdkMainResponseModel;
import com.sslwireless.sslcommerzlibrary.model.response.SSLCVerifyOtpAndLoginModel;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCShareInfo;

import java.util.ArrayList;
import java.util.List;

public class SSLCSaveCardsAdapter extends RecyclerView.Adapter<SSLCSaveCardsAdapter.MyViewHolder> {

    public int previousSelectedPosition = -1;
    public int currentlySelectedPosition = -1;
    public boolean isSelected = false;
    ArrayList<String> offerBins;
    SSLCOfferModel SSLCOfferModel;
    String offerId;
    private LayoutInflater inflater;
    private List<SSLCVerifyOtpAndLoginModel.CardNo> mTaskInfo;
    private Context mContext;
    private ClickListener clickListener;
    private EmiSelectListener emiSelectListener;
    private DeleteListener deleteListener;
    private SSLCSdkMainResponseModel SSLCSdkMainResponseModel;

    public SSLCSaveCardsAdapter(Context context, List<SSLCVerifyOtpAndLoginModel.CardNo> data,
                                SSLCSdkMainResponseModel SSLCSdkMainResponseModel, String offerId, SSLCOfferModel SSLCOfferModel) {
        inflater = LayoutInflater.from(context);
        this.mTaskInfo = data;
        this.mContext = context;
        this.SSLCSdkMainResponseModel = SSLCSdkMainResponseModel;
        this.SSLCOfferModel = SSLCOfferModel;
        this.offerId = offerId;
        //this.offerTitle = offerTitle;
        // this.offerBins = offerBins;

        //Log.d("TAG", "Data Size: " + mTaskInfo);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item_save_cards_sslc, parent, false);
        MyViewHolder holder = new MyViewHolder(view, viewType);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final SSLCVerifyOtpAndLoginModel.CardNo cardNo = mTaskInfo.get(position);

        holder.tvName.setText(cardNo.getCardNo());
        holder.tvBankName.setText(cardNo.getBankName());
        holder.layoutCvc.setVisibility(View.GONE);
        holder.etCardCvc.setText("");
        holder.tvAvailEmi.setText(mContext.getResources().getString(R.string.avail_emi));
        holder.layoutEmiTitle.setVisibility(View.GONE);
        holder.layoutOffer.setVisibility(View.GONE);

       // ChangeCursorColor changeCursorColor = new ChangeCursorColor(mContext);
       // changeCursorColor.setCursorColor(holder.etCardCvc, Color.parseColor("#" + sdkMainResponseModel.getPrimaryColor()));
        //holder.layoutCvc.setBackground(ShareInfo.getInstance().setBackgroundColor(Color.parseColor("#" + sdkMainResponseModel.getActiveColor()), Color.parseColor("#" + sdkMainResponseModel.getPrimaryColor()), mContext.getResources().getColor(R.color.very_light_grey)));

        holder.layoutEMI.setBackground(SSLCShareInfo.getInstance().setBackgroundColor(Color.parseColor("#" + SSLCSdkMainResponseModel.getActiveColor()), Color.parseColor("#" + SSLCSdkMainResponseModel.getPrimaryColor()), mContext.getResources().getColor(R.color.very_light_grey)));
        if (SSLCSdkMainResponseModel.getOfferStatus() == 1) {
            String ccNum = cardNo.getCardNo().substring(0, 6);
            if (SSLCOfferModel != null) {
                for (SSLCOfferModel.DiscountList discount : SSLCOfferModel.getData().getData().getDiscountList()) {
                    for (String str : discount.getAllowedBIN()) {
                        if (str.equalsIgnoreCase(ccNum)) {
                            holder.layoutOffer.setVisibility(View.VISIBLE);
                            offerId = discount.getAvailDiscountId();
                            holder.tvOfferTitle.setText(discount.getDiscountTitle());
                        }
                    }
                }
            } else {
                //Log.e(TAG, "onBindViewHolder AAA: ");
            }
        }

        holder.layoutMother.setBackgroundResource(0);
        if (cardNo.getType().contains("another")) {
            holder.tvName.setText(mContext.getResources().getString(R.string.pay_using_another_card));
            holder.tvBankName.setVisibility(View.GONE);
            holder.layoutCardDelete.setVisibility(View.GONE);
        } else {
            holder.tvBankName.setVisibility(View.VISIBLE);
            holder.layoutCardDelete.setVisibility(View.VISIBLE);
        }
        if (cardNo.getType().toLowerCase().contains("visa")) {
            SSLCShareInfo.getInstance().setEditTextMaxLength(holder.etCardCvc, 3);
            holder.ivCard.setImageResource(R.drawable.visa_card);
        } else if (cardNo.getType().toLowerCase().contains("master")) {
            SSLCShareInfo.getInstance().setEditTextMaxLength(holder.etCardCvc, 3);
            holder.ivCard.setImageResource(R.drawable.master_card);
        } else if (cardNo.getType().toLowerCase().contains("amex")) {
            SSLCShareInfo.getInstance().setEditTextMaxLength(holder.etCardCvc, 4);
            holder.ivCard.setImageResource(R.drawable.amex_card_sslc);
        } else {
            SSLCShareInfo.getInstance().setEditTextMaxLength(holder.etCardCvc, 4);
            holder.ivCard.setImageResource(R.drawable.default_card);
        }
        holder.layoutEMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emiSelectListener.onEMISelect(holder.layoutEMI, holder.layoutOffer, holder.tvAvailEmi, cardNo.getCardNo());
            }
        });
        holder.layoutCardDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteListener.onDelete(holder.getAdapterPosition());
            }
        });

        isSelected = false;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickListener != null){
                    if (previousSelectedPosition == holder.getAdapterPosition()) {
                        isSelected = true;
                    }
                    currentlySelectedPosition = holder.getAdapterPosition();
                    clickListener.itemClicked(v, holder.getAdapterPosition(), previousSelectedPosition);
                    if (previousSelectedPosition == holder.getAdapterPosition()) {
                        notifyItemChanged(holder.getAdapterPosition());
                        //ShareInfo.getInstance().hideKeyboardFrom(holder.etCardCvc);
                        previousSelectedPosition = -1;
                        isSelected = true;
                    } else {
                        previousSelectedPosition = holder.getAdapterPosition();
                        isSelected = false;
                        //notifyItemChanged(previousSelectedPosition);
                    }
                }
            }
        });
    }

    public void reloadData(){
        notifyDataSetChanged();
    }
    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public void setEMIClickListener(EmiSelectListener clickListener) {
        this.emiSelectListener = clickListener;
    }

    public void setDeleteClickListener(DeleteListener clickListener) {
        this.deleteListener = clickListener;
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
        void itemClicked(View v, int position, int size);
    }

    public interface EmiSelectListener {
        void onEMISelect(LinearLayout layoutEMI, LinearLayout layoutOffer, TextView textView, String num);
    }

    public interface DeleteListener {
        void onDelete(int position);
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView ivCard;//, selectedContentArea;
        EditText etCardCvc;
        TextView tvName, tvBankName, tvAvailEmi, tvOfferTitle;
        LinearLayout layoutCvc, layoutEMI, layoutCardDelete, layoutOffer, layoutMother;
        RelativeLayout layoutEmiTitle;

        public MyViewHolder(View itemView, int viewType) {
            super(itemView);
            ivCard = itemView.findViewById(R.id.iv_card);
            tvName = itemView.findViewById(R.id.tv_name);
            tvBankName = itemView.findViewById(R.id.tv_bank_name);
            etCardCvc = itemView.findViewById(R.id.et_card_cvc);
            layoutEmiTitle = itemView.findViewById(R.id.layout_emi_title);
            layoutCvc = itemView.findViewById(R.id.layout_cvc);
            layoutEMI = itemView.findViewById(R.id.layout_emi);
            tvAvailEmi = itemView.findViewById(R.id.tv_avail_emi);
            layoutCardDelete = itemView.findViewById(R.id.layout_card_delete);
            layoutMother = itemView.findViewById(R.id.layoutMother);
            tvOfferTitle = itemView.findViewById(R.id.tv_offer_title);
            layoutOffer = itemView.findViewById(R.id.layout_offer);
        }
    }
}