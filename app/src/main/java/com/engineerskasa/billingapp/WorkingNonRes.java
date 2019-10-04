package com.engineerskasa.billingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.text.DecimalFormat;

public class WorkingNonRes extends AppCompatActivity {

    TextView ud,eftNonRes,estNonRes,ettNonRes,serviceChargeNonRes,nelNonRes,streetLightNonRes,vatNonRes,getFundNonRes,billNonRes,unitTxt,daysTxt;

    double unit,EFT,EST,ETT,serviceCharge,NEL,streetLight,vat,gfnhl,totalBill;
    int days;
    DecimalFormat twoDForm = new DecimalFormat("#.##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_working_non_res);


        eftNonRes = findViewById(R.id.eftNonResWTxt);
        estNonRes = findViewById(R.id.estNonResWTxt);
        ettNonRes = findViewById(R.id.ettNonResWTxt);
        serviceChargeNonRes = findViewById(R.id.seviceChargeNonResWTxt);
        nelNonRes = findViewById(R.id.nelNonResWTxt);
        streetLightNonRes = findViewById(R.id.streetLightnonResWTxt);
        vatNonRes = findViewById(R.id.vatnonResWTxt);
        getFundNonRes = findViewById(R.id.getFundnonResWTxt);
        billNonRes = findViewById(R.id.BillnonResWTxt);
        unitTxt = findViewById(R.id.nonResWUnit);
        daysTxt = findViewById(R.id.nonResWDays);
        ud = findViewById(R.id.unitDesc1);

        if(getIntent() != null){
            unit = getIntent().getDoubleExtra("Unit",0);
            days = getIntent().getIntExtra("Days",0);
            EFT = getIntent().getDoubleExtra("Energy First Threshold",0);
            EST = getIntent().getDoubleExtra("Energy Second Threshold",0);
            ETT = getIntent().getDoubleExtra("Energy Third Threshold",0);
            serviceCharge = getIntent().getDoubleExtra("Service Charge",0);
            NEL = getIntent().getDoubleExtra("NEL",0);
            streetLight = getIntent().getDoubleExtra("Street Light",0);
            vat = getIntent().getDoubleExtra("VAT",0);
            gfnhl = getIntent().getDoubleExtra("GETFUND",0);
            totalBill = getIntent().getDoubleExtra("Total Bill",0);
        }

        unitTxt.setText(String.valueOf(unit));
        daysTxt.setText(String.valueOf(days));

        if(unit <= 300){
            ud.setText("Unit is less than or equal to 300");
            eftNonRes.setText("0");
            estNonRes.setText("0");
            ettNonRes.setText("0");
            serviceChargeNonRes.setText(" = 0.3857 x days\n = 0.3857 x "+String.valueOf(days)+"\n"+"= "+serviceCharge);
            nelNonRes.setText("Energy First Threshold = Unit = "+unit+", Energy Second Threshold = 0, Energy Third Threshold = 0\n"+"=(Energy First Threshold x 0.7532) + (Energy Second Threshold x 0.8015) + (Energy Third Threshold x 1.2647)\n"+"= ("+unit+" x 0.7532) + (0 x 0.8015) + (0 x 1.2647)\n= "+String.valueOf(Double.valueOf(twoDForm.format(unit * 0.7532)) +" + 0 + 0 \n= 0.02 x "+String.valueOf(Double.valueOf(twoDForm.format(unit * 0.7532)))+"\n="+NEL));
            streetLightNonRes.setText("Energy First Threshold = Unit = "+unit+", Energy Second Threshold = 0, Energy Third Threshold = 0\n"+"=(Energy First Threshold x 0.7532) + (Energy Second Threshold x 0.8015) + (Energy Third Threshold x 1.2647)\n"+"= ("+unit+" x 0.7532) + (0 x 0.8015) + (0 x 1.2647)\n= "+String.valueOf(Double.valueOf(twoDForm.format(unit * 0.7532)) +" + 0 + 0\n= 0.03 x "+String.valueOf(Double.valueOf(twoDForm.format(unit * 0.7532)))+"\n="+streetLight));
            vatNonRes.setText(" = (Total Energy + Service Charge) x 0.125\n"+"Total Energy =(Energy First Threshold x 0.7532) + (Energy Second Threshold x 0.8015) + (Energy Third Threshold x 1.2647)\n"+"= ("+unit+" x 0.7532) + (0 x 0.8015) + (0 x 1.2647)\n= "+String.valueOf(Double.valueOf(twoDForm.format(unit * 0.7532)) +" + 0 + 0 \n= "+(Double.valueOf(twoDForm.format(unit * 0.7532))+serviceCharge+"x 0.125\n"+"= "+vat)));
            getFundNonRes.setText(" = (Total Energy + Service Charge) x 0.05\n"+"Total Energy =(Energy First Threshold x 0.7532) + (Energy Second Threshold x 0.8015) + (Energy Third Threshold x 1.2647)\n"+"= ("+unit+" x 0.7532) + (0 x 0.8015) + (0 x 1.2647)\n= "+String.valueOf(Double.valueOf(twoDForm.format(unit * 0.7532)) +" + 0 + 0 \n= "+(Double.valueOf(twoDForm.format(unit * 0.7532))+serviceCharge+"x 0.05\n"+"= "+gfnhl)));
            billNonRes.setText(" = (Total Energy + Service Charge + NEL + Street Light + VAT + GET FUND)\n= "+Double.valueOf(twoDForm.format(unit * 0.7532))+serviceCharge+NEL+streetLight+vat+gfnhl+"\n="+totalBill);

        }
        if(unit > 300){
            ud.setText("Unit is greater than 300");
            eftNonRes.setText(" = Days x 9.8630\n"+"9.8630 x"+days+"\n="+EFT);
            if(EFT < unit) {
                if (Math.round(9.8630 * days) + EFT > unit)
                    estNonRes.setText("Energy Second Threshold = 9.8630 x " + days + "\n=" + Math.round(9.8630 * days) + "\nAddon = EFT + Energy Second Threshold\n" + "= " + EFT + " + " + Math.round(9.8630 * days) + "\n=" + Double.valueOf(twoDForm.format(EFT + Math.round(9.8630 * days))) + "\nEST = Unit - EFT\n" + "= " + unit + " - " + EFT + "\n=" + EST);
                else if (Math.round(9.8630 * days) + EFT < unit)
                    estNonRes.setText("Energy Second Threshold = 9.8630 x " + days + "\n= EST = Energy Second Threshold\n" + "=" + EST);
            }
                if(Math.round(9.8630 * days)+EFT < unit) {
                    if(Math.round(19.7260 * days)+Math.round(9.8630 * days)+EFT < unit || Math.round(19.7260 * days)+Math.round(9.8630 * days)+EFT > unit)
                    ettNonRes.setText(" Energy Third Threshold = 19.7260 x "+days+"\nAddon = Addon + Energy Third Threshold\n"+"= "+Double.valueOf(twoDForm.format(EFT+Math.round(9.8630 * days)))+" + "+Math.round(19.7260 * days)+"\n= "+Double.valueOf(twoDForm.format(EFT+Math.round(9.8630 * days)+ Math.round(19.7260 * days)))+"\n ETT = Unit - (EFT + EST)\n="+unit+" - ("+EFT+" + "+EST+")\n"+"="+ETT);

                }
            serviceChargeNonRes.setText(" = 0.3857 x days\n = 0.3857 x "+String.valueOf(days)+"\n"+"= "+serviceCharge);
            nelNonRes.setText("Energy First Threshold = Unit = "+EFT+", Energy Second Threshold = "+EST+", Energy Third Threshold = "+ETT+"\n"+"=(Energy First Threshold x 0.7532) + (Energy Second Threshold x 0.8015) + (Energy Third Threshold x 1.2647)\n"+"= ("+EFT+" x 0.7532) + ("+EST+" x 0.8015) + ("+ETT+" x 1.2647)\n= "+String.valueOf(Double.valueOf(twoDForm.format(EFT * 0.7532)) +" + "+Double.valueOf(twoDForm.format(EST * 0.8015))+" + "+Double.valueOf(twoDForm.format(ETT * 1.2647))+" \n= 0.02 x "+String.valueOf(Double.valueOf(twoDForm.format((EFT * 0.7532) +(EST * 0.8015)+(ETT * 1.2647)))+"\n="+NEL)));
            streetLightNonRes.setText("Energy First Threshold = Unit = "+EFT+", Energy Second Threshold = "+EST+", Energy Third Threshold = "+ETT+"\n"+"=(Energy First Threshold x 0.7532) + (Energy Second Threshold x 0.8015) + (Energy Third Threshold x 1.2647)\n"+"= ("+EFT+" x 0.7532) + ("+EST+" x 0.8015) + ("+ETT+" x 1.2647)\n= "+String.valueOf(Double.valueOf(twoDForm.format(EFT * 0.7532)) +" + "+Double.valueOf(twoDForm.format(EST * 0.8015))+" + "+Double.valueOf(twoDForm.format(ETT * 1.2647))+" \n= 0.03 x "+String.valueOf(Double.valueOf(twoDForm.format((EFT * 0.7532) +(EST * 0.8015)+(ETT * 1.2647)))+"\n="+streetLight)));
            vatNonRes.setText(" = (Total Energy + Service Charge) x 0.125\n"+"Total Energy =(Energy First Threshold x 0.7532) + (Energy Second Threshold x 0.8015) + (Energy Third Threshold x 1.2647)\n"+"= ("+EFT+" x 0.7532) + ("+EST+" x 0.8015) + ("+ETT+" x 1.2647)\n= "+String.valueOf(Double.valueOf(twoDForm.format(EFT * 0.7532)) +" + "+String.valueOf(Double.valueOf(twoDForm.format(EST * 0.8015)))+" + "+String.valueOf(Double.valueOf(twoDForm.format(ETT * 1.2647)))+" \n= "+String.valueOf(Double.valueOf(twoDForm.format((EFT * 0.7532)) +Double.valueOf(twoDForm.format(EST * 0.8015))+Double.valueOf(twoDForm.format(ETT * 1.2647)))+serviceCharge+"x 0.125\n"+"= "+vat)));
            getFundNonRes.setText(" = (Total Energy + Service Charge) x 0.05\n"+"Total Energy =(Energy First Threshold x 0.7532) + (Energy Second Threshold x 0.8015) + (Energy Third Threshold x 1.2647)\n"+"= ("+EFT+" x 0.7532) + ("+EST+" x 0.8015) + ("+ETT+" x 1.2647)\n= "+String.valueOf(Double.valueOf(twoDForm.format(EFT * 0.7532)) +" + "+String.valueOf(Double.valueOf(twoDForm.format(EST * 0.8015)))+" + "+String.valueOf(Double.valueOf(twoDForm.format(ETT * 1.2647)))+" \n= "+String.valueOf(Double.valueOf(twoDForm.format((EFT * 0.7532)) +Double.valueOf(twoDForm.format(EST * 0.8015))+Double.valueOf(twoDForm.format(ETT * 1.2647)))+serviceCharge+"x 0.05\n"+"= "+gfnhl)));
            billNonRes.setText(" = (Total Energy + Service Charge + NEL + Street Light + VAT + GET FUND)\n= "+String.valueOf(Double.valueOf(twoDForm.format((EFT * 0.7532) +(EST * 0.8015)+(ETT * 1.2647)))+serviceCharge+NEL+streetLight+vat+gfnhl+"\n="+totalBill));

        }

    }
}
