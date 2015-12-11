package com.hujiang.appperformanceanalyser.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hujiang.appperformanceanalyser.R;
import com.hujiang.appperformanceanalyser.entity.AppInfo;

import java.util.List;

public class AppListAdapter extends BaseAdapter {

    private List<AppInfo> mAppInfoList;
    private LayoutInflater mLayoutInflater;

    public AppListAdapter(Context context, List<AppInfo> appInfoList) {
        mAppInfoList = appInfoList;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mAppInfoList.size();
    }

    @Override
    public Object getItem(int position) {
        return mAppInfoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = mLayoutInflater.inflate(R.layout.appinfo_list_item, parent, false);
        ViewHolder holder = (ViewHolder) convertView.getTag();
        if (holder == null) {
            holder = new ViewHolder();
            convertView.setTag(holder);
            holder.ivAppIcon = (ImageView) convertView.findViewById(R.id.iv_app_icon);
            holder.tvAppName = (TextView) convertView.findViewById(R.id.tv_app_name);
            holder.tvAppPkgName = (TextView) convertView.findViewById(R.id.tv_app_pkg);
        }
        AppInfo appInfo = mAppInfoList.get(position);
        holder.ivAppIcon.setImageDrawable(appInfo.appIcon);
        holder.tvAppName.setText(appInfo.appName);
        holder.tvAppPkgName.setText(appInfo.packageName);
        return convertView;
    }

    static class ViewHolder {
        TextView tvAppPkgName;
        TextView tvAppName;
        ImageView ivAppIcon;
    }
}
