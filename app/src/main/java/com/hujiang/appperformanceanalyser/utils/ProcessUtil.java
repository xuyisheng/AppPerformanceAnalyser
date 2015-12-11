package com.hujiang.appperformanceanalyser.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import com.hujiang.appperformanceanalyser.entity.AppInfo;

import java.util.ArrayList;
import java.util.List;

public class ProcessUtil {

    public List<AppInfo> getRunningProcess(Context context) {
        ActivityManager am =
                (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningAppProcessInfoList =
                am.getRunningAppProcesses();
        PackageManager pm = context.getPackageManager();
        List<AppInfo> progressList = new ArrayList<>();

        for (ApplicationInfo appinfo : getPackagesInfo(context)) {
            AppInfo appInfo = new AppInfo();
            if (((appinfo.flags & ApplicationInfo.FLAG_SYSTEM) > 0) ||
                    ((appinfo.processName != null) &&
                            (appinfo.processName.equals(context.getPackageName())))) {
                continue;
            }
            for (ActivityManager.RunningAppProcessInfo runningProcess : runningAppProcessInfoList) {
                if ((runningProcess.processName != null) &&
                        runningProcess.processName.equals(appinfo.processName)) {
                    break;
                }
            }
            appInfo.appName = appinfo.loadLabel(pm).toString();
            appInfo.packageName = appinfo.processName;
            appInfo.appIcon = appinfo.loadIcon(pm);
            progressList.add(appInfo);
        }
        return progressList;
    }

    private List<ApplicationInfo> getPackagesInfo(Context context) {
        PackageManager pm = context.getApplicationContext().getPackageManager();
        return pm.getInstalledApplications(PackageManager.GET_UNINSTALLED_PACKAGES);
    }
}
