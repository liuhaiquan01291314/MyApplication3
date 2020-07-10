package com.example.model;

import android.util.Log;

import com.example.base.BaseModel;
import com.example.bean.ProjectBean;
import com.example.net.MainCallBack;
import com.example.presenter.MainPresenter;
import com.example.service.ApiService;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainModel extends BaseModel {
    private static final String TAG = "MainModel";
    public void getProjectData(MainCallBack callBack) {
        Retrofit build = new Retrofit.Builder()
                .baseUrl(ApiService.PROJECT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        ApiService apiService = build.create(ApiService.class);
        Observable<ProjectBean> data = apiService.getData();
        data.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ProjectBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ProjectBean projectBean) {
                        if (projectBean!=null&&projectBean.getDatas()!=null&&projectBean.getDatas().size()>0){
                            Log.d(TAG, "onNext: "+projectBean.toString());
                            callBack.getProjectDataCorrect(projectBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.getProjectDataError("获取网络数据失败");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
