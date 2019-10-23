package com.gmail2548sov.beerbreakolehsuprun;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Info extends AppCompatActivity implements View.OnClickListener {


    final int infopriceone = 5;
    int itotal = 0;
    TextView infototal;
    TextView itotalprise;
    int infoprise = 0;
    Button ibtncns;
    Button ibtncheck;
    EditText iname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order);
        infototal = (TextView)findViewById(R.id.infototal);
        itotalprise = (TextView)findViewById(R.id.iprice);
        ibtncns = (Button)findViewById(R.id.icns);
        ibtncheck = (Button)findViewById(R.id.iok);
        ibtncns.setOnClickListener(this);
        ibtncheck.setOnClickListener(this);
        iname = (EditText)findViewById(R.id.iname);

//получаем данные из родителя
        Intent intent = getIntent();
        itotal = intent.getIntExtra("totalm",0);
        infoprise = itotal*infopriceone;
        infototal.setText(itotal+"");
        itotalprise.setText(infoprise+"");
    }



    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.icns:


                Intent intentcns = new Intent();
                intentcns.putExtra("name", "");
                setResult(2, intentcns);
                finish();
                break;

            case R.id.iok:

                if (TextUtils.isEmpty(iname.getText().toString()))Toast.makeText(this, "Input you name", Toast.LENGTH_SHORT).show(); else
                {
                    Intent intentok = new Intent();
                    String name = iname.getText().toString();
                    intentok.putExtra("name", name);
                    intentok.putExtra("glass", String.valueOf(itotal));
                    intentok.putExtra("sum", String.valueOf(infoprise));
                    intentok.putExtra("end2",0);
                    setResult(RESULT_OK, intentok);
                    finish();
                }
                    break;



            default:
                throw new IllegalStateException("Unexpected value: " + view.getId());
        }

    }
}
