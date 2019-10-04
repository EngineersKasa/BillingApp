package com.engineerskasa.billingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.text.DecimalFormat;

public class Working extends AppCompatActivity {

    double unit,lifeLine,EFT,EST,ETT,serviceCharge,subsidy,NEL,streetLight,totalBill,e4t;
    int days;
    DecimalFormat twoDForm = new DecimalFormat("#.##");


    TextView unitTxt,daysTxt,serviceChargeRes,subsidyRes,nelRes,streeLightRes,totalBillRes,lifeLineRes,eftRes,estRes,ettRes,ud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_working);

        lifeLineRes = findViewById(R.id.lifeLineWTxt);
        eftRes = findViewById(R.id.eftWTxt);
        estRes = findViewById(R.id.estWTxt);
        ettRes = findViewById(R.id.ettWTxt);
        serviceChargeRes =findViewById(R.id.seviceChargeWTxt);
        subsidyRes = findViewById(R.id.subsidyWTxt);
        nelRes = findViewById(R.id.nelWTxt);
        streeLightRes = findViewById(R.id.streetLightWRes);
        totalBillRes = findViewById(R.id.totalBillWTxt);
        unitTxt = findViewById(R.id.resWUnit);
        daysTxt = findViewById(R.id.resWDays);
        ud = findViewById(R.id.unitDesc);

        if(getIntent() != null){

            unit = getIntent().getDoubleExtra("Unit",0);
            days = getIntent().getIntExtra("Days",0);
            lifeLine = getIntent().getDoubleExtra("Life Line",0);
            EFT = getIntent().getDoubleExtra("Energy First Threshold",0);
            EST = getIntent().getDoubleExtra("Energy Second Threshold",0);
            ETT = getIntent().getDoubleExtra("Energy Third Threshold",0);
            serviceCharge = getIntent().getDoubleExtra("Service Charge",0);
            subsidy = getIntent().getDoubleExtra("Subsidy",0);
            NEL = getIntent().getDoubleExtra("NEL",0);
            streetLight = getIntent().getDoubleExtra("Street Light",0);
            totalBill = getIntent().getDoubleExtra("Total Bill",0);
        }
        unitTxt.setText(String.valueOf(unit));
        daysTxt.setText(String.valueOf(days));

        if(unit <= 50){
            ud.setText("Unit is less than or equal to 50");
            lifeLineRes.setText("0");
            eftRes.setText("0");
            estRes.setText("0");
            ettRes.setText("0");
            serviceChargeRes.setText(" = 0.0701 x days\n = 0.0701 x "+String.valueOf(days)+"\n"+"= "+serviceCharge);
            if(subsidy == 0)
                subsidyRes.setText("Unit/Days = "+String.valueOf(Double.valueOf(twoDForm.format(unit/days)))+" is greater than 1.6438"+"\n ="+subsidy);
            else
                subsidyRes.setText("Unit/Days = "+String.valueOf(unit/days)+" is less than or equal to 1.6438"+"\n ="+subsidy);
            nelRes.setText("EFT = Unit = "+unit+", EST = 0, ETT = 0, E4T = 0\n"+"=(EFT x 0.3078) + (EST x 0.6175) + (ETT x 0.8014) + (E4T x 0.8904)\n"+"= ("+unit+" x 0.3078) + (0 x 0.6175) + (0 x 0.8014) + (0 x 0.8904)\n= "+String.valueOf(Double.valueOf(twoDForm.format(unit * 0.3078)) +" + 0 + 0 + 0\n= 0.02 x "+String.valueOf(Double.valueOf(twoDForm.format(unit * 0.3078)))+"\n="+NEL));
            streeLightRes.setText("EFT = Unit = "+unit+", EST = 0, ETT = 0, E4T = 0\n"+"=(EFT x 0.3078) + (EST x 0.6175) + (ETT x 0.8014) + (E4T x 0.8904)\n"+"= ("+unit+" x 0.3078) + (0 x 0.6175) + (0 x 0.8014) + (0 x 0.8904)\n= "+String.valueOf(Double.valueOf(twoDForm.format(unit * 0.3078)) +" + 0 + 0 + 0\n= 0.03 x "+String.valueOf(Double.valueOf(twoDForm.format(unit * 0.3078))+"\n="+streetLight)));
            totalBillRes.setText(" Total Energy = (EFT x 0.3078) + (EST x 0.6175) + (ETT x 0.8014) + (E4T x 0.8904)\n"+"= ("+unit+" x 0.3078) + (0 x 0.6175) + (0 x 0.8014) + (0 x 0.8904)\n"+"= Total Energy + Service Charge + NEL + Street Light - Subsidy\n"+String.valueOf(Double.valueOf(twoDForm.format(unit * 0.3078))+" + "+String.valueOf(serviceCharge)+" + "+String.valueOf(NEL)+" + "+String.valueOf(streetLight)+" + "+String.valueOf(subsidy)+"\n"+"="+totalBill));

        }
        if(unit > 50){
            ud.setText("Unit is greater than 50");
            lifeLineRes.setText("= Days x 1.6438\n"+"= "+days+" x 1.6438"+"= "+Math.round(days * 1.6438));
            if(lifeLine < unit){
                if(Math.round(8.2191 * days)+lifeLine > unit)
                    eftRes.setText("If Life Line is less than Unit\nEnergy Second Threshold = 8.2191 X Days\n"+"= 8.2191 + "+days+"\n= "+String.valueOf(Math.round(8.2191 * days))+"\nAddon = Life Line + Energy Second Threshold\n"+"= "+lifeLine+" + "+String.valueOf(Math.round(8.2191 * days))+"= "+Double.valueOf(twoDForm.format(lifeLine+Math.round(8.2191 * days)))+"\nAddon is greater than Unit\n"+"EFT = Unit - Life Line"+"\n= "+unit+" - "+lifeLine+"\n="+EFT);
                else if(Math.round(8.2191 * days)+lifeLine < unit)
                    eftRes.setText("If Life Line is less than Unit\nEnergy Second Threshold = 8.2191 X Days\n"+"= 8.2191 + "+days+"\n= "+String.valueOf(Math.round(8.2191 * days))+"\nAddon = Life Line + Energy Second Threshold\n"+"= "+lifeLine+" + "+String.valueOf(Math.round(8.2191 * days))+"= "+Double.valueOf(twoDForm.format(lifeLine+Math.round(8.2191 * days)))+"\nAddon is less than Unit\n"+"EFT = Energy Second Threshold"+"\n= "+String.valueOf(Math.round(8.2191 * days)));
                if(Math.round(8.2191 * days)+lifeLine < unit) {
                    if (lifeLine + Math.round(8.2191 * days) + Math.round(9.2191 * days) > unit)
                        estRes.setText("Energy Third Threshold = 9.2191 x Days\n" + "= 9.2191 x " + days +"\n="+Math.round(9.2191 * days)+ "\nAddon = Addon + Energy Third Threshold\n" + "= " + Double.valueOf(twoDForm.format(lifeLine + Math.round(8.2191 * days))) + " + " + Math.round(9.2191 * days) + "\n= " + Double.valueOf(twoDForm.format(lifeLine + Math.round(8.2191 * days) + Math.round(9.2191 * days))) + "\nAddon is greater than Unit\n" + "EST =  Unit - (Life Line + EFT)\n" + "= " + unit + " - " + "(" + lifeLine + " + " + EFT + ")\n" + "= " + EST);
                    else if (lifeLine + Math.round(8.2191 * days) + Math.round(9.2191 * days) < unit)
                        estRes.setText("Energy Third Threshold = 9.2191 x Days\n" + "= 9.2191 x " + days + "\n="+Math.round(9.2191 * days)+"\nAddon = Addon + Energy Third Threshold\n" + "= " + Double.valueOf(twoDForm.format(lifeLine + Math.round(8.2191 * days))) + " + " + Math.round(9.2191 * days) + "\n= " + Double.valueOf(twoDForm.format(lifeLine + Math.round(8.2191 * days) + Math.round(9.2191 * days))) + "\nAddon is less than Unit\n" + "EST = Energy Third Threshold" + "= " + EST);
                }
                if(lifeLine+Math.round(8.2191 * days)+Math.round(9.2191 * days) < unit){
                    e4t = lifeLine+Math.round(8.2191 * days)+Math.round(9.2191 * days);
                    if((lifeLine+Math.round(8.2191 * days)+Math.round(9.2191 * days)+Math.round(19.7260 * days) > unit) || (lifeLine+Math.round(8.2191 * days)+Math.round(9.2191 * days)+Math.round(19.7260 * days) < unit)){
                        e4t = lifeLine+Math.round(8.2191 * days)+Math.round(9.2191 * days)+Math.round(19.7260 * days);
                        ettRes.setText("Energy Fourth Threshold = 19.7260 x Days\n"+"= 19.7260 x "+days+ "\n="+Math.round(19.7260 * days)+"\nAddon = Addon + Energy Fourth Threshold\n"+"= EST"+" + Energy Fourth Threshold"+"\n= "+EST+" + "+Math.round(19.7260 * days)+"\n= "+Double.valueOf(twoDForm.format(EST+Math.round(19.7260 * days)))+"\nETT = Unit - (Life Line + EFT + EST)\n"+"= "+unit+" - "+"("+lifeLine+" + "+EFT+" + "+EST+")\n"+"= "+ETT);
                    }
                }
                serviceChargeRes.setText(" = 0.2314 x days\n = 0.2314 x "+String.valueOf(days)+"\n"+"= "+serviceCharge);
                subsidyRes.setText("Unit is greater than 50\n"+" ="+subsidy);
                nelRes.setText("Energy First Threshold = "+lifeLine+", Energy Second Threshold = "+EFT+", Energy Third Threshold = "+EST+", Energy Fourth Threshold = "+ETT+"\n"+"=(Energy First Threshold x 0.3078) + (Energy Second Threshold x 0.6175) + (Energy Third Threshold x 0.8014) + (Energy Fourth Threshold x 0.8904)\n"+"= ("+lifeLine+" x 0.3078) + ("+EFT+" x 0.6175) + ("+EST+" x 0.8014) + ("+ETT+" x 0.8904)\n= "+Double.valueOf(twoDForm.format(lifeLine*0.3078)) +" + "+Double.valueOf(twoDForm.format(EFT*0.6175))+" + "+Double.valueOf(twoDForm.format(EST*0.8014))+" + "+Double.valueOf(twoDForm.format(ETT*0.8904))+"\n= 0.02 x "+String.valueOf(Double.valueOf(twoDForm.format((lifeLine*0.3078)+(EFT*0.6175)+(EST*0.8014)+(ETT*0.8904))))+"\n="+NEL);
                streeLightRes.setText("Energy First Threshold = "+lifeLine+", Energy Second Threshold = "+EFT+", Energy Third Threshold = "+EST+", Energy Fourth Threshold = "+ETT+"\n"+"=(Energy First Threshold x 0.3078) + (Energy Second Threshold x 0.6175) + (Energy Third Threshold x 0.8014) + (Energy Fourth Threshold x 0.8904)\n"+"= ("+lifeLine+" x 0.3078) + ("+EFT+" x 0.6175) + ("+EST+" x 0.8014) + ("+ETT+" x 0.8904)\n= "+Double.valueOf(twoDForm.format(lifeLine*0.3078)) +" + "+Double.valueOf(twoDForm.format(EFT*0.6175))+" + "+Double.valueOf(twoDForm.format(EST*0.8014))+" + "+Double.valueOf(twoDForm.format(ETT*0.8904))+"\n= 0.03 x "+String.valueOf(Double.valueOf(twoDForm.format((lifeLine*0.3078)+(EFT*0.6175)+(EST*0.8014)+(ETT*0.8904))))+"\n="+streetLight);
                totalBillRes.setText(" Total Energy = (Energy First Threshold x 0.3078) + (Energy Second Threshold x 0.6175) + (Energy Third Threshold x 0.8014) + (Energy Fourth Threshold x 0.8904)\n"+"= ("+lifeLine+" x 0.3078) + ("+EFT+" x 0.6175) + ("+EST+" x 0.8014) + ("+ETT+" x 0.8904)\n"+"= Total Energy + Service Charge + NEL + Street Light - Subsidy\n"+String.valueOf(Double.valueOf(twoDForm.format((lifeLine*0.3078)+(EFT*0.6175)+(EST*0.8014)+(ETT*0.8904))))+" + "+String.valueOf(serviceCharge)+" + "+String.valueOf(NEL)+" + "+String.valueOf(streetLight)+" + "+String.valueOf(subsidy)+"\n"+"="+totalBill);
            }


        }
    }
}
