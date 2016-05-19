package filipkesteli.com.asyncservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class AsyncService extends Service {
    public AsyncService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //Kad se starta servis, dajem mu 3 zadatka da obavi
        BackgroundTask bt = new BackgroundTask(this); //sebe saljem kao servis
        bt.execute(5);



        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        //servis i background task su medusobno ovisni!!
        Toast.makeText(AsyncService.this, "onDestroy je pozvan!", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }
}
