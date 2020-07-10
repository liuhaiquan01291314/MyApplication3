package com.example.presenter;

import com.example.base.BasePresenter;
import com.example.bean.ProjectBean;
import com.example.model.MainModel;
import com.example.net.MainCallBack;
import com.example.view.MyView;

public class MainPresenter extends BasePresenter<MyView>implements MainCallBack {

    private MainModel mainModel;

    @Override
    protected void initModel() {
        mainModel = new MainModel();
        mModels.add(mainModel);
    }
//获取数据失败方法
    @Override
    public void getProjectDataError(String str) {
        mView.showToast(str);
    }
//获取数据成功方法
    @Override
    public void getProjectDataCorrect(ProjectBean projectBean) {
        mView.setView(projectBean);
    }
//去M层获取网络数据
    public void toGetProjectData() {
        mainModel.getProjectData(this);
    }
}
