package com.sys.appstudent.appupdate;

/**
 * @ProjectName: AppStudent
 * @Package: com.sys.appstudent.appupdate
 * @ClassName: UpdateDownloadListener
 * @Description: app自动更新
 * @Author: ©sys
 * @CreateDate: 2020/8/14 18:03
 * @Version: v1.0
 */
public interface UpdateDownloadListener {
    public void onStarted();
    public void onProgressChanged(int progress, String downloadUrl);
    public void onFinished(float completeSize, String downloadUrl);
    public void onFailure();
}
