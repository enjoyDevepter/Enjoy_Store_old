package me.jessyan.mvparms.demo.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import butterknife.BindView;
import me.jessyan.mvparms.demo.R;
import me.jessyan.mvparms.demo.di.component.DaggerPlaceOrderComponent;
import me.jessyan.mvparms.demo.di.module.PlaceOrderModule;
import me.jessyan.mvparms.demo.mvp.contract.PlaceOrderContract;
import me.jessyan.mvparms.demo.mvp.presenter.PlaceOrderPresenter;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class PlaceOrderActivity extends BaseActivity<PlaceOrderPresenter> implements PlaceOrderContract.View, View.OnClickListener {

    @BindView(R.id.back)
    View backV;
    @BindView(R.id.title)
    TextView titleTV;

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerPlaceOrderComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .placeOrderModule(new PlaceOrderModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.activity_place_order; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        backV.setOnClickListener(this);
        titleTV.setText("下单中心");
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ArmsUtils.snackbarText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {
        finish();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                killMyself();
                break;
        }
    }
}
