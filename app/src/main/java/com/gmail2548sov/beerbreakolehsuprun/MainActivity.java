package com.gmail2548sov.beerbreakolehsuprun;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    final int priceone = 5;
    int total = 0;
    int prise = 0;
    private static final String KEY_COUNT = "COUNT";
    int end1 = 1;
    ImageButton btnPlus;
    ImageButton btnMinus;
    TextView qnt;
    TextView txprise;
    Button btncns;
    Button btncheck;
    TextView mname;
    TextView mnamer;
    int OZEMAIL = 0;
    String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnPlus = findViewById(R.id.btnPlus);
        btnMinus = findViewById(R.id.btnMinus);
        qnt = findViewById(R.id.qnt);
        txprise = findViewById(R.id.prise_);
        btncns = findViewById(R.id.btncnsl);
        btncheck = findViewById(R.id.btncheckt);
        btnPlus.setOnClickListener(this);
        btnMinus.setOnClickListener(this);
        btncns.setOnClickListener(this);
        btncheck.setOnClickListener(this);
        mname = findViewById(R.id.mname);
        mnamer = findViewById(R.id.mnamer);


        if (savedInstanceState != null) {
            total = savedInstanceState.getInt(KEY_COUNT, 0);
            qnt.setText(total + "");
            txprise.setText(priceone * total + "");
            if (savedInstanceState.getInt("end", 1) == 0) {


                mname.setText("Name:");
                btncns.setEnabled(false);
                btncns.setBackgroundColor(ContextCompat.getColor(this, R.color.grey2));
                btncheck.setText("r=Order by e-mail");
                btnPlus.setEnabled(false);
                btnMinus.setEnabled(false);
                end1 = 0;


            }
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_COUNT, total);
        if (end1 == 0) outState.putInt("end", 0);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnMinus:
                if (total < 1) total = 0;
                else total -= 1;
                prise = priceone * total;
                qnt.setText(total + "");
                txprise.setText(prise + "");
                break;
            case R.id.btnPlus:
                total = total + 1;
                prise = priceone * total;
                qnt.setText(total + "");
                txprise.setText(prise + "");
                break;
            case R.id.btncnsl:
                total = 0;
                prise = 0;
                qnt.setText(total + "");
                txprise.setText(prise + "");
                break;
            case R.id.btncheckt:
                if (end1 == 0) {
                    Intent email = new Intent(Intent.ACTION_SEND);

                   // if (email != null) {

                        email.putExtra(Intent.EXTRA_EMAIL, new String[]{"2548sov@gmail.com"});

                        email.putExtra(Intent.EXTRA_SUBJECT, "Order_Beer");
                        email.putExtra(Intent.EXTRA_TEXT, "Order for " + total + " glasses of beer, $ " + prise);

                        email.setType("message/rfc822");

                        try {
                            startActivity(email);
                        } catch (android.content.ActivityNotFoundException ex) {
                            Toast.makeText(this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();

                            //выход из приложения
                            /*Intent homeIntent = new Intent(Intent.ACTION_SEND);
                            homeIntent.addCategory(Intent.CATEGORY_HOME);
                            homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(homeIntent);*/
                        }


                    /*

                    try {
                        startActivity(Intent.createChooser(i, "Send mail..."));
                    } catch (android.content.ActivityNotFoundException ex) {
                        Toast.makeText(MyActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                    }

                    */


                    //}

                }
                if (end1 != 0 && total > 0) {
                    Intent intent = new Intent(this, Info.class);
                    intent.putExtra("totalm", total);
                    startActivityForResult(intent, 3);
                } else if (total < 1)
                    Toast.makeText(this, "You have an empty basket", Toast.LENGTH_SHORT).show();

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null || requestCode != 3) {
            return;
        }
        if (resultCode == 0 || resultCode == 2) {
            total = 0;
            prise = 0;
            qnt.setText(total + "");
            txprise.setText(prise + "");
            mname.setText("");
            mnamer.setText("");
            end1 = 1;
        } else {

            mnamer.setText(data.getStringExtra("name"));
            name = data.getStringExtra("name");
            mname.setText("Name:");
            qnt.setText(data.getStringExtra("glass"));
            btncns.setBackgroundColor(ContextCompat.getColor(this, R.color.grey2));
            txprise.setText(data.getStringExtra("sum"));
            end1 = data.getIntExtra("end2", 1);
            btncns.setEnabled(false);
            btncheck.setText("Order by e-mail");

            btnPlus.setEnabled(false);
            btnMinus.setEnabled(false);


        }


    }


}
