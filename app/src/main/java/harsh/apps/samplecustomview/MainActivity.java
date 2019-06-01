package harsh.apps.samplecustomview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import harsh.apps.bikecomputerwidget.BikeComputer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BikeComputer bikeComputer = findViewById(R.id.bike_computer);
        bikeComputer.animateOnInitialization(true);
    }
}
