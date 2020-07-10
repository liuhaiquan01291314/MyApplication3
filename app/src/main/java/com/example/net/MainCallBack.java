package com.example.net;

import com.example.bean.ProjectBean;

public interface MainCallBack {
    void getProjectDataError(String str);
    void getProjectDataCorrect(ProjectBean projectBean);
}
