package com.example.base;

import java.util.ArrayList;

public abstract class BasePresenter<V extends BaseView> {
    public V mView;
    public ArrayList<BaseModel>mModels=new ArrayList<>();

    public BasePresenter() {
        initModel();
    }
    public void bindView(V view){
        mView=view;
    }

    protected abstract void initModel();

}
