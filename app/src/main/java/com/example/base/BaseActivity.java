package com.example.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


import com.example.xiaoshixunyuekao.R;

import butterknife.ButterKnife;

public abstract class BaseActivity<P extends BasePresenter>extends AppCompatActivity implements BaseView {
    public P mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(addLayout());
        ButterKnife.bind(this);
        initPresenter();
        if (mPresenter!=null)
            mPresenter.bindView(this);
        initView();
        initData();
        initListener();
    }

    protected abstract int addLayout();

    protected abstract void initPresenter();

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void initListener();
}
