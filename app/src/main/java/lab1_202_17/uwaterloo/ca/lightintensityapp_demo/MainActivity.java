package lab1_202_17.uwaterloo.ca.lightintensityapp_demo;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView messageText = (TextView) findViewById(R.id.textField);

        messageText.setText("Initialization...");

        SensorManager senManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        Sensor lightSensor = senManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        LightSensorHandler lightHandler = new LightSensorHandler(messageText);

        senManager.registerListener(lightHandler, lightSensor, senManager.SENSOR_DELAY_NORMAL);
    }
}

class LightSensorHandler implements SensorEventListener {

    private final float LIGHT_INTENSITY_THRESHOLD = 50.0f;

    TextView tv1;

    public LightSensorHandler(TextView targetTv) {
        this.tv1 = targetTv;
        tv1.setText("Completed");
    }

    public void onAccuracyChanged(Sensor s, int i) {

    }

    public void onSensorChanged(SensorEvent se) {
        if (se.sensor.getType() == Sensor.TYPE_LIGHT) {
             if (se.values[0] <= LIGHT_INTENSITY_THRESHOLD) {
                 tv1.setText("Ambient Light Too Low!!!");
             }

            else {
                 tv1.setText("Ambient Light Normal...");
             }
        }

        // se.values(0); // Light Intensity Reading (Float)
    }

}
