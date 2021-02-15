package com.sslwireless.sslcommerzlibrary.view.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.sslwireless.sslcommerzlibrary.R;
import com.sslwireless.sslcommerzlibrary.model.initializer.SSLCommerzInitialization;
import com.sslwireless.sslcommerzlibrary.model.response.SSLCSdkMainResponseModel;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCEnums;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCShareInfo;
import com.sslwireless.sslcommerzlibrary.view.activity.MainUIActivitySSLC;
import com.sslwireless.sslcommerzlibrary.view.activity.WebViewActivitySSLC;
import com.sslwireless.sslcommerzlibrary.view.adapter.SSLCMobileBankingRecylcerAdapter;
import com.sslwireless.sslcommerzlibrary.viewmodel.listener.SSLCOnBtnPayActiveListener;
import com.sslwireless.sslcommerzlibrary.viewmodel.listener.SSLCOnUserVerifyListener;
import com.sslwireless.sslcommerzlibrary.viewmodel.listener.SSLCPayNowListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SSLCMobileBankingFragment extends Fragment implements SSLCMobileBankingRecylcerAdapter.ClickListener {

    private static SSLCommerzInitialization mobileSslCommerzInitialization;
    private Context context;
    private RecyclerView mobileBankingRecycler;
    private SSLCMobileBankingRecylcerAdapter adapter;
    private SSLCSdkMainResponseModel SSLCSdkMainResponseModel;
    private List<SSLCSdkMainResponseModel.Desc> mobileBankingList = new ArrayList<>();
    private SSLCOnBtnPayActiveListener SSLCOnBtnPayActiveListener;
    private SSLCOnUserVerifyListener SSLCOnUserVerifyListener;
    private ImageView imageView;
    private int pos = -1;
    private List<SSLCSdkMainResponseModel.Desc> itemClickData;

    public SSLCMobileBankingFragment() {
        // Required empty public constructor
    }

    public static SSLCMobileBankingFragment newInstance(String param1, SSLCommerzInitialization sslCommerzInitialization) {
        SSLCMobileBankingFragment fragment = new SSLCMobileBankingFragment();
        Bundle args = new Bundle();
        args.putString(SSLCShareInfo.main_response, param1);
        mobileSslCommerzInitialization = sslCommerzInitialization;
        fragment.setArguments(args);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            SSLCSdkMainResponseModel = new SSLCSdkMainResponseModel();
            SSLCSdkMainResponseModel = SSLCSdkMainResponseModel.fromJSON(getArguments().getString(SSLCShareInfo.main_response));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_mobile_banking_sslc, container, false);
        context = getActivity().getApplicationContext();

        mobileBankingRecycler = v.findViewById(R.id.mobileBankingRecycler);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        for (SSLCSdkMainResponseModel.Desc desc : SSLCSdkMainResponseModel.getDesc()) {
            if (desc.getType().equalsIgnoreCase("mobilebanking")) {
                mobileBankingList.add(desc);
            }
        }

        mobileBankingRecycler.setHasFixedSize(true);
        adapter = new SSLCMobileBankingRecylcerAdapter(context, mobileBankingList);
        mobileBankingRecycler.setLayoutManager(new GridLayoutManager(context, 3));
        mobileBankingRecycler.setAdapter(adapter);
        adapter.setClickListener(this);
    }

    @Override
    public void itemClicked(View v, final int position,
                            final List<SSLCSdkMainResponseModel.Desc> mTaskInfo, boolean status) {
        imageView = v.findViewById(R.id.selectedContentArea);
        pos = position;
        itemClickData = mTaskInfo;

        if (status) {
            // onBtnPayActiveListener.onBtnPayActive(true, sdkMainResponseModel.getDesc().get(position).getTransAmt(), sdkMainResponseModel.getDesc().get(position).getCharge());
            if (mobileBankingList.size() > position) {
                SSLCOnBtnPayActiveListener.onBtnPayActive(true, mobileBankingList.get(position).getName());
            }
        } else
            SSLCOnBtnPayActiveListener.onBtnPayActive(false, "");

        ((MainUIActivitySSLC) getActivity()).setOnPayClickListener(new SSLCPayNowListener() {
            @Override
            public void onPayClick() {
                if (mTaskInfo.get(position).getRFlag().equals("1")) {
//                    Log.e(TAG, "onPayClick: " + mTaskInfo.get(position).getRedirectGatewayURL());

                    Intent intent = new Intent(context, WebViewActivitySSLC.class);
                    intent.putExtra("redirectUrl", mTaskInfo.get(position).getRedirectGatewayURL());
                    intent.putExtra("merchantName", mTaskInfo.get(position).getName());
                    intent.putExtra("session_key", SSLCSdkMainResponseModel.getSessionkey());
                    intent.putExtra("timeOutValue", SSLCSdkMainResponseModel.getTimeoutinMin());
                    intent.putExtra("sdkMainResponse", mobileSslCommerzInitialization);
                    getActivity().startActivityForResult(intent, SSLCEnums.Common.Activity2.ordinal());
                }
            }
        });
    }

    private void hideView(ImageView imageView) {
        imageView.setVisibility(View.GONE);
    }

    @Override
    public void setUserVisibleHint(boolean isFragmentVisible_) {
        super.setUserVisibleHint(true);

        if (this.isVisible()) {
            if (pos != -1) {
                itemClickData.get(pos).setSetStatus(false);
                adapter.updateView(imageView, pos);
            }
            SSLCOnBtnPayActiveListener.onBtnPayActive(false, "");
            SSLCShareInfo.getInstance().hideKeyboardFrom(getView());

            if (isFragmentVisible_) {
                ((MainUIActivitySSLC) getActivity()).setOnPayClickListener(new SSLCPayNowListener() {
                    @Override
                    public void onPayClick() {

                    }
                });
            }
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof SSLCOnUserVerifyListener) {
            SSLCOnUserVerifyListener = (SSLCOnUserVerifyListener) context;
            SSLCOnBtnPayActiveListener = (SSLCOnBtnPayActiveListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        SSLCOnUserVerifyListener = null;
        SSLCOnBtnPayActiveListener = null;
    }
}
