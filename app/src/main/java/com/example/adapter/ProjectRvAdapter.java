package com.example.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.bean.BaseApp;
import com.example.bean.Bean;
import com.example.bean.ProjectBean;
import com.example.db.BeanDao;
import com.example.xiaoshixunyuekao.R;


import java.util.ArrayList;
import java.util.List;

public class ProjectRvAdapter extends RecyclerView.Adapter<ProjectRvAdapter.ViewHolder> {
    private Context context;
    private List<ProjectBean.DatasBean> datas=new ArrayList<>();
    private BeanDao beanDao;
    private Bean bean;

    public List<ProjectBean.DatasBean> getDatas() {
        return datas;
    }

    public void initData(List<ProjectBean.DatasBean> datas) {
        this.datas.addAll(datas);
        notifyDataSetChanged();
    }

    public ProjectRvAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.project_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        beanDao = BaseApp.getInstance().getDaoSession().getBeanDao();
        final ProjectBean.DatasBean datasBean = datas.get(position);
        holder.tv_title.setText(datasBean.getTitle());
        holder.tv_name.setText(datasBean.getModel()+"");
        Glide.with(context).load(datasBean.getThumbnail()).apply(RequestOptions.circleCropTransform()).into(holder.img_url);


        if (datasBean.isCollect()){
            holder.bt_collect.setText("取消");
        }else {
            holder.bt_collect.setText("关注");
        }

        holder.bt_collect.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //将数据保存到数据库
                if (holder.bt_collect.getText().equals("关注")){
                    holder.bt_collect.setText("取消");
                    bean = new Bean((long)position, datasBean.getThumbnail(), datasBean.getTitle(), datasBean.getModel() + "", true);
                    beanDao.insertOrReplace(bean);
                    datasBean.setCollect(true);
                }else {//将保存的数据从数据库删除
                    holder.bt_collect.setText("关注");
                    bean.setIsCollect(false);
                    beanDao.delete(bean);
                    datasBean.setCollect(false);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final Button bt_collect;
        private final TextView tv_name;
        private final TextView tv_title;
        private final ImageView img_url;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_url = itemView.findViewById(R.id.img_url);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_name = itemView.findViewById(R.id.tv_name);
            bt_collect = itemView.findViewById(R.id.bt_collect);
        }
    }
}
