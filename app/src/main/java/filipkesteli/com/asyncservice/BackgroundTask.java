package filipkesteli.com.asyncservice;

import android.app.Service;
import android.os.AsyncTask;
import android.widget.Toast;

/**
 * Created by programer on 19.5.2016..
 */
public class BackgroundTask extends AsyncTask<Integer, Integer, String> {

    private Service service;

    //u ovom momentu ja imam service
    public BackgroundTask(Service service) {
        this.service = service;
    }

    //metoda u background threadu -> promijenjiv broj parametara
    @Override
    protected String doInBackground(Integer... params) {
        int taskCount = params[0]; //prvi parametar
        for (int i = 0; i < taskCount; i++) {
            performLongTask(); //ta ce metoda spavati

            //idemo javiti frajeru kolki je progres:
            int progress = ((int)((i+1) / ((float)taskCount) * 100));
            publishProgress(progress); //ova metoda to triggerira
        }
        return "Servis je gotov!";
    }

    private void performLongTask() {
        //svaki put spavaj 2 sekunde -> recimo kad uzima podatke iz interneta
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        int progress = values[0];
        Toast.makeText(service, progress + "%", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPostExecute(String s) {
        Toast.makeText(service, s, Toast.LENGTH_SHORT).show();
        service.stopSelf(); //kriticna liniija koda -> moramo ubit servis
        //smisao servisa -> pokreni se, vrti mi i onda se ubij!
    }
}
