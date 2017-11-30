package qc.browser.light;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;

import java.io.File;


public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+"QC_Browser"+File.separator+"Pages");

        if (!file.exists()) {
            Log.e("gdfg" , "file make directory call"+file.getPath());
            try {
                file.mkdirs();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent i = new Intent(SplashActivity.this, CustomMainActivity.class);
                startActivity(i);
            }
        }, 2000);
    }
}
