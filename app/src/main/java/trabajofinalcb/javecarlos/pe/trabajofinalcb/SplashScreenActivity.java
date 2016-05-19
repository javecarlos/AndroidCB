package trabajofinalcb.javecarlos.pe.trabajofinalcb;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import utils.Constantes;

/**
 * Created by Administrator on 18/05/2016.
 */
public class SplashScreenActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private static final String TAG = "SplashActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setMax(100);

        Thread timerThread = new Thread() {
            public void run() {
                try{
                    for (int i=1; i<=100;i++){
                        sleep(50);
                        progressBar.setProgress(i);
                    }
                }catch(InterruptedException e){
                    Log.d(TAG, e.getMessage());
                    Toast toastMessage = Toast.makeText(getApplicationContext(), Constantes.ERROR_NO_CONTROLADO, Toast.LENGTH_SHORT);
                    toastMessage.show();
                }finally{
                    Intent intentLogin = new Intent(SplashScreenActivity.this, LoginActivity.class);
                    startActivity(intentLogin);
                    finish();
                }
            }
        };
        timerThread.start();
    }
}
