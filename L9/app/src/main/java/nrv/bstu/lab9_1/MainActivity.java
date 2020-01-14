package nrv.bstu.lab9_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView personName;
    TextView personAge;
    ImageView personPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.cardview_activity);
        personName = (TextView)findViewById(R.id.person_name1);
        personAge = (TextView)findViewById(R.id.person_age1);
        personPhoto = (ImageView)findViewById(R.id.person_photo1);

        personName.setText("Emma Wilson");
        personAge.setText("23 years old");
        personPhoto.setImageResource(R.drawable.emma);
    }
}
