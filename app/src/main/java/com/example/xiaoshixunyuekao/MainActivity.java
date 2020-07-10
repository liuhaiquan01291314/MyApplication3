package com.example.xiaoshixunyuekao;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;



import com.example.adapter.ProjectRvAdapter;
import com.example.base.BaseActivity;
import com.example.bean.ProjectBean;
import com.example.presenter.MainPresenter;
import com.example.view.MyView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity<MainPresenter> implements MyView {

    @BindView(R.id.tv_toolbar)
    TextView tvToolbar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv)
    RecyclerView rv;
    private ProjectRvAdapter adapter;


    @Override
    protected int addLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initPresenter() {
        mPresenter=new MainPresenter();
    }

    @Override
    protected void initView() {
        //设置布局管理器
        rv.setLayoutManager(new LinearLayoutManager(this));
        //添加下划线
        rv.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        //创建适配器
        adapter = new ProjectRvAdapter(this);
        //设置适配器
        rv.setAdapter(adapter);
    }
//去P层获取数据
    @Override
    protected void initData() {
        mPresenter.toGetProjectData();
    }

    @Override
    protected void initListener() {

    }
//获取数据成功方法
    @Override
    public void setView(ProjectBean projectBean) {
        if (projectBean.getDatas()!=null&&projectBean.getDatas().size()>0){
            List<ProjectBean.DatasBean> datas = projectBean.getDatas();
            adapter.initData(datas);
        }
    }
//获取数据失败方法
    @Override
    public void showToast(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }
}
