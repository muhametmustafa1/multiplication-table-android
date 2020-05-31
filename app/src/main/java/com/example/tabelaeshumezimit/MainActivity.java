package com.example.tabelaeshumezimit;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    int piket = 0;
    int SEKONDAT_FILLIM = 30+1;
    Random random = new Random();
    int n1 = random.nextInt(11);
    int n2 = random.nextInt(11);
    TextView piketTextView;
    TextView kohaTextView;
    CountDownTimer timer;
    TextView pyetja;
    TextView mesazhiGabim;
    Button butoniA;
    Button butoniB;
    Button butoniC;
    Button butoniD;
    Button butoniLuajPerseri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/crayoncrumble.ttf");

        piketTextView = (TextView)findViewById(R.id.piketTextView);
        piketTextView.setTypeface(typeface);

        kohaTextView = (TextView)findViewById(R.id.kohaTextView);
        kohaTextView.setTypeface(typeface);

        pyetja = (TextView)findViewById(R.id.pyetjaTextView);
        pyetja.setTypeface(typeface);

        mesazhiGabim = (TextView)findViewById(R.id.mesazhiGabim);
        mesazhiGabim.setTypeface(typeface);

        butoniLuajPerseri = (Button)findViewById(R.id.butoniLuajPerseri);
        butoniLuajPerseri.setTypeface(typeface);
        butoniLuajPerseri.setOnClickListener(this);

        butoniA = (Button)findViewById(R.id.butoniA);
        butoniA.setTypeface(typeface);
        butoniA.setOnClickListener(this);

        butoniB = (Button)findViewById(R.id.butoniB);
        butoniB.setTypeface(typeface);
        butoniB.setOnClickListener(this);

        butoniC = (Button)findViewById(R.id.butoniC);
        butoniC.setTypeface(typeface);
        butoniC.setOnClickListener(this);

        butoniD = (Button)findViewById(R.id.butoniD);
        butoniD.setTypeface(typeface);
        butoniD.setOnClickListener(this);

        pyetja.setText(n1 +" × "+n2 +"?");

        Integer[] opsionetRandome = randomOptions(n1*n2);
        butoniA.setText(opsionetRandome[0].toString());
        butoniB.setText(opsionetRandome[1].toString());
        butoniC.setText(opsionetRandome[2].toString());
        butoniD.setText(opsionetRandome[3].toString());

        timer = new CountDownTimer(SEKONDAT_FILLIM*1000,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                kohaTextView.setText("Koha e mbetur: "+millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                kohaTextView.setText("Koha ka mbaruar!");
                setButtonsEnabled(false);
            }
        };
        timer.start();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.butoniA:
                onButtonClick(butoniA);
                break;
            case R.id.butoniB:
                onButtonClick(butoniB);
                break;
            case R.id.butoniC:
                onButtonClick(butoniC);
                break;
            case R.id.butoniD:
                onButtonClick(butoniD);
                break;
            case R.id.butoniLuajPerseri:{
                piket = 0;
                piketTextView.setText("Pikët: "+piket);
                changeStuff();
                mesazhiGabim.setVisibility(View.INVISIBLE);
                setButtonsEnabled(true);
                setButtonsVisible(View.VISIBLE);
                kohaTextView.setText("Koha e mbetur: "+SEKONDAT_FILLIM);
                timer.cancel();
                timer = new CountDownTimer(SEKONDAT_FILLIM*1000,1000){

                    @Override
                    public void onTick(long millisUntilFinished) {
                        kohaTextView.setText("Koha e mbetur: "+millisUntilFinished/1000);
                    }

                    @Override
                    public void onFinish() {
                        kohaTextView.setText("Koha ka mbaruar!");
                        setButtonsEnabled(false);
                    }
                };
                timer.start();

            }

        }

    }
    private Integer[] randomOptions(int pergjigjjaSakte){
        Integer[] rez = new Integer[4];
        Set<Integer> bashkesia = new HashSet<>();
        bashkesia.add(pergjigjjaSakte);
        while(bashkesia.size() < 4){
            bashkesia.add(random.nextInt(101));
        }
        bashkesia.toArray(rez);
        return rez;
    }
    private void changeStuff(){
        n1 = random.nextInt(11);
        n2 = random.nextInt(11);
        pyetja.setText(n1 + " × " + n2 + "?");
        Integer[] opsionetRandome = randomOptions(n1*n2);
        butoniA.setText(opsionetRandome[0].toString());
        butoniB.setText(opsionetRandome[1].toString());
        butoniC.setText(opsionetRandome[2].toString());
        butoniD.setText(opsionetRandome[3].toString());
    }
    private void setButtonsEnabled(boolean enabled){
        butoniA.setEnabled(enabled);
        butoniB.setEnabled(enabled);
        butoniC.setEnabled(enabled);
        butoniD.setEnabled(enabled);
    }
    private void setButtonsVisible(int visible){
        butoniA.setVisibility(visible);
        butoniB.setVisibility(visible);
        butoniC.setVisibility(visible);
        butoniD.setVisibility(visible);
    }
    private void onButtonClick(Button butoni){
        int pergjigja = Integer.parseInt(butoni.getText().toString());
        if(pergjigja == (n1 * n2)){
            piket++;
            piketTextView.setText("Pikët: "+piket);
            changeStuff();
        }
        else{
            mesazhiGabim.setText("Pergjigjja është e pasaktë!\n" +
                                 n1+" × "+n2+ " = "+(n1*n2)+
                                 "\nPikët totale: "+piket);
            mesazhiGabim.setVisibility(View.VISIBLE);
            setButtonsEnabled(false);
            setButtonsVisible(View.INVISIBLE);
            timer.cancel();
        }
    }
}
