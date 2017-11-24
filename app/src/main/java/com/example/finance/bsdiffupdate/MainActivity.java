package com.example.finance.bsdiffupdate;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private String mOldPath;
    private String mPath;
    private String mPatchPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/";
        mOldPath = path + "app_bs.apk";
        mPath = path + "app_new.apk";
        mPatchPath = path + "diff.patch";


        findViewById(R.id.createDiff).setOnClickListener(this);
        findViewById(R.id.createNewApk).setOnClickListener(this);
    }

    static {
        System.loadLibrary("native-lib");
    }
    //生成差分包
    public native int diff(String oldpath,String newpath,String patch);
    //旧apk和差分包合并
    public native int patch(String oldpath,String newpath,String patch);


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.createDiff:
                diff(mOldPath, getApkPath(), mPatchPath);
                Toast.makeText(this, "差别包好了。", Toast.LENGTH_SHORT).show();
                break;
            case R.id.createNewApk:
                patch(getApkPath(), mPath, mPatchPath);
                Toast.makeText(this, "新包合并好了。", Toast.LENGTH_SHORT).show();
                break;

        }
    }

    private String getApkPath() {
        String name = getPackageName();
        List<ApplicationInfo> infos = getPackageManager().getInstalledApplications(PackageManager.MATCH_UNINSTALLED_PACKAGES);

        for (ApplicationInfo info : infos) {
            if (info.packageName.equals(name)) {
                return info.sourceDir;
            }
        }

        return null;
    }


}
