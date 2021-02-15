package com.sslwireless.sslcommerzlibrary.view.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import android.view.Gravity;
import android.view.ViewGroup.LayoutParams;

import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.sslwireless.sslcommerzlibrary.R;
import com.sslwireless.sslcommerzlibrary.model.initializer.SSLCommerzInitialization;
import com.sslwireless.sslcommerzlibrary.model.response.SSLCOfferModel;
import com.sslwireless.sslcommerzlibrary.model.response.SSLCSdkMainResponseModel;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCShareInfo;
import com.sslwireless.sslcommerzlibrary.view.adapter.SSLCOffersRecyclerAdapter;

public class SSLOffersActivitySSLC extends SSLCBaseActivity {

    TextView tvDataNotFound;
    RecyclerView recyclerView;
   // SwipeRefreshLayout mSwipeRefreshLayout;
    SSLCSdkMainResponseModel SSLCSdkMainResponseModel;
    private Context context;
    private SSLCommerzInitialization sslCommerzInitialization;
    SSLCOfferModel SSLCOfferModel;
    //PullToRefreshView mPullToRefreshView;
   // private PullRefreshContainerView mContainerView;
    //private TextView mRefreshHeader;

    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent
            finish();
        }
    };

    @Override
    protected void onDestroy() {
        // Unregister since the activity is about to be closed.
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver);
        super.onDestroy();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ssl_offers);
        context = this;
        tvDataNotFound = findViewById(R.id.tv_data_not_found);
       // mSwipeRefreshLayout = findViewById(R.id.swipe_container);
        tvDataNotFound.setVisibility(View.GONE);
        sslCommerzInitialization = (SSLCommerzInitialization) getIntent().
                getSerializableExtra("sslCommerzInitialerData");

        Bundle bun = getIntent().getExtras();
        if (getIntent().hasExtra("main_response")) {
            SSLCSdkMainResponseModel = new SSLCSdkMainResponseModel();
            SSLCSdkMainResponseModel = SSLCSdkMainResponseModel.fromJSON(bun.getString(SSLCShareInfo.main_response));
        }
        SSLCOfferModel = new Gson().fromJson(getIntent().getExtras().getString("offer_model"), SSLCOfferModel.class);

        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                new IntentFilter("custom-event-name"));
    }

    @Override
    public void viewRelatedTask() {
        recyclerView = findViewById(R.id.offersRecycler);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setTitle("Offers");
        }

        //getData();

      /*  mSwipeRefreshLayout.setRefreshing(false);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
        mSwipeRefreshLayout.setProgressViewOffset(false, 0, 400);

        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);

        mSwipeRefreshLayout.post(new Runnable() {

            @Override
            public void run() {
                getData();
            }
        });*/

        getData();

       /* mRefreshHeader = new TextView(this);
        mRefreshHeader.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        mRefreshHeader.setGravity(Gravity.CENTER);
        mRefreshHeader.setText("Pull to refresh...");

        mContainerView = (PullRefreshContainerView) findViewById(R.id.swipe_container);
        mContainerView.setRefreshHeader(mRefreshHeader);

        mContainerView.setOnChangeStateListener(new PullRefreshContainerView.OnChangeStateListener() {
            @Override
            public void onChangeState(PullRefreshContainerView container, int state) {
                switch (state) {
                    case PullRefreshContainerView.STATE_IDLE:
                    case PullRefreshContainerView.STATE_PULL:
                        mRefreshHeader.setText("Pull to refresh...");
                        break;
                    case PullRefreshContainerView.STATE_RELEASE:
                        mRefreshHeader.setText(R.string.release_to_refresh);
                        break;
                    case PullRefreshContainerView.STATE_LOADING:
                        mRefreshHeader.setText("Loading...");
                        getData();
                        mContainerView.completeRefresh();

                        break;
                }
            }
        });
*/
    }

    private void getData() {
        if (SSLCOfferModel == null) {
            //ShareInfo.getInstance().showToast(this, getResources().getString(R.string.no_offer_message));
            tvDataNotFound.setVisibility(View.VISIBLE);
            return ;
        }

        recyclerView.setHasFixedSize(true);
        final SSLCOffersRecyclerAdapter adapter = new SSLCOffersRecyclerAdapter(SSLOffersActivitySSLC.this, SSLCOfferModel.getData().getData().getDiscountList());
        recyclerView.setLayoutManager(new LinearLayoutManager(SSLOffersActivitySSLC.this));
        recyclerView.setAdapter(adapter);

        adapter.setClickListener(new SSLCOffersRecyclerAdapter.ClickListener() {
            @Override
            public void itemClicked(View v, int position) {
                SSLCOfferModel.DiscountList discount = SSLCOfferModel.getData().getData().getDiscountList().get(position);
                if (!discount.getRedirectGWPath().isEmpty()) {
                    Intent intent = new Intent(context, WebViewActivitySSLC.class);
                    intent.putExtra("redirectUrl", discount.getRedirectGWPath());
                    intent.putExtra("session_key", SSLCSdkMainResponseModel.getSessionkey());
                    intent.putExtra("sdkMainResponse", sslCommerzInitialization);
                    startActivity(intent);
                } else {
                    Intent intent = getIntent();
                    intent.putExtra("id", discount.getAvailDiscountId());
                    intent.putExtra("image", discount.getDisIMGPath());
                    intent.putExtra("title", discount.getDiscountTitle());
                    intent.putExtra("max_discount", discount.getMaxDisAmt());
                    intent.putStringArrayListExtra("offer_bins", SSLCShareInfo.getInstance().convertListToArrayList(discount.getAllowedBIN()));
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
