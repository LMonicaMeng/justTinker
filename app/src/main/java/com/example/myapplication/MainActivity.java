package com.example.myapplication;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.jnitest.JniUtil;
import com.tencent.tinker.lib.tinker.Tinker;
import com.tencent.tinker.lib.tinker.TinkerInstaller;
import com.tencent.tinker.loader.shareutil.ShareTinkerInternals;

/**
 * 使用tinker实现热修复
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void say(View view) {
//        Toast.makeText(getApplicationContext(), "Hello", Toast.LENGTH_SHORT).show();
//        Toast.makeText(getApplicationContext(), "Hello World", Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(), "Hello World 123", Toast.LENGTH_SHORT).show();
    }

    public void install_patch(View view) {
        TinkerInstaller.onReceiveUpgradePatch(getApplicationContext(), Environment.getExternalStorageDirectory().getAbsolutePath() + "/patch_signed_7zip.apk");
//        TinkerInstaller.onReceiveUpgradePatch(getApplicationContext(), getCacheDir().getAbsolutePath() + "/patch_signed_7zip.apk");
    }

    public void uninstall_patch(View view) {
        ShareTinkerInternals.killAllOtherProcess(getApplicationContext());
        Tinker.with(getApplicationContext()).cleanPatch();
    }

    public void kill_myself(View view) {
        ShareTinkerInternals.killAllOtherProcess(getApplicationContext());
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    public void string_from_so(View view) {
        String string = JniUtil.hello();
        Toast.makeText(getApplicationContext(), string, Toast.LENGTH_SHORT).show();
    }
}
