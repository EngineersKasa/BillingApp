package com.engineerskasa.billingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    //New Tarrif
    final double new_res_0_50 = 0.3078;
    final double new_res_51_300 = 0.6175;
    final double new_res_301_600 = 0.8014;
    final double new_res_600p = 0.8904;

    //Service Charge
    final double new_res_sc_0_50 = 0.0701;
    final double new_res_sc_50p = 0.2314;

    //Subsidy
    final double new_res_subsidy = 0.020;

    //non residential
    final double non_res_new_tariff_0_100 = 0.7532;
    final double non_res_new_tariff_101_300 = 0.7532;
    final double non_res_new_tariff_301_600 = 0.8015;
    final double non_res_new_tariff_600p = 1.2647;

    //Service Charge
    final double non_res_new_sc = 0.3857;

    DecimalFormat twoDForm = new DecimalFormat("#.##");

    LinearLayout residentialLL,nonResidentialLL;
    RadioGroup radio;
    RadioButton radioButton;
    TextView serviceChargeRes,subsidyRes,nelRes,streeLightRes,totalBillRes,serviceChargeNonRes,nelNonRes,streetLightNonRes,vatNonRes,getFundNonRes,billNonRes,lifeLineRes,eftRes,estRes,ettRes,eftNonRes,estNonRes,ettNonRes;
    Button calcRes,calcNonRes,workingResBtn,workingNonResBtn;
    EditText unitRes,daysRes,unitNonRes,daysNonRes;

    int days_g;
    double unit_g,energy_first_threshold,energy_second_threshold,energy_third_threshold,energy_fourth_threshold,addon,checkLifeline,subs_check_g,energy_first_threshold_nr,energy_second_threshold_nr,energy_third_threshold_nr,checkLifeline_nr,addon_nr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        residentialLL = findViewById(R.id.calcRes);
        nonResidentialLL = findViewById(R.id.calcnonRes);
        radio = findViewById(R.id.radio);
        lifeLineRes = findViewById(R.id.lifeLineTxt);
        eftRes = findViewById(R.id.eftTxt);
        estRes = findViewById(R.id.estTxt);
        ettRes = findViewById(R.id.ettTxt);
        eftNonRes = findViewById(R.id.eftNonResTxt);
        estNonRes = findViewById(R.id.estNonResTxt);
        ettNonRes = findViewById(R.id.ettNonResTxt);
        serviceChargeRes =findViewById(R.id.seviceChargeTxt);
        subsidyRes = findViewById(R.id.subsidyTxt);
        nelRes = findViewById(R.id.nelTxt);
        streeLightRes = findViewById(R.id.streetLightRes);
        totalBillRes = findViewById(R.id.totalBillTxt);
        serviceChargeNonRes = findViewById(R.id.seviceChargeNonResTxt);
        nelNonRes = findViewById(R.id.nelNonResTxt);
        streetLightNonRes = findViewById(R.id.streetLightnonResTxt);
        vatNonRes = findViewById(R.id.vatnonResTxt);
        getFundNonRes = findViewById(R.id.getFundnonResTxt);
        billNonRes = findViewById(R.id.BillnonResTxt);
        calcRes = findViewById(R.id.resBtn);
        calcNonRes = findViewById(R.id.nonResbtn);
        unitRes = findViewById(R.id.resUnit);
        daysRes = findViewById(R.id.resDays);
        unitNonRes = findViewById(R.id.nonResUnit);
        daysNonRes = findViewById(R.id.nonResDays);
        workingResBtn = findViewById(R.id.workResBtn);
        workingNonResBtn = findViewById(R.id.workNonResBtn);

        residentialLL.setVisibility(View.VISIBLE);
        nonResidentialLL.setVisibility(View.GONE);


        int selectedId = radio.getCheckedRadioButtonId();

        // find the radiobutton by returned id
        radioButton = findViewById(selectedId);


        workingResBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Working.class);
                intent.putExtra("Unit",Double.valueOf(unitRes.getText().toString()));
                intent.putExtra("Days",Integer.valueOf(daysRes.getText().toString()));
                intent.putExtra("Life Line",Double.valueOf(lifeLineRes.getText().toString()));
                intent.putExtra("Energy First Threshold",Double.valueOf(eftRes.getText().toString()));
                intent.putExtra("Energy Second Threshold",Double.valueOf(estRes.getText().toString()));
                intent.putExtra("Energy Third Threshold",Double.valueOf(ettRes.getText().toString()));
                intent.putExtra("Service Charge",Double.valueOf(serviceChargeRes.getText().toString()));
                intent.putExtra("Subsidy",Double.valueOf(subsidyRes.getText().toString()));
                intent.putExtra("NEL",Double.valueOf(nelRes.getText().toString()));
                intent.putExtra("Street Light",Double.valueOf(streeLightRes.getText().toString()));
                intent.putExtra("Total Bill",Double.valueOf(totalBillRes.getText().toString()));
                startActivity(intent);
            }
        });


        workingNonResBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, WorkingNonRes.class);
                intent.putExtra("Unit",Double.valueOf(unitNonRes.getText().toString()));
                intent.putExtra("Days",Integer.valueOf(daysNonRes.getText().toString()));
                intent.putExtra("Energy First Threshold",Double.valueOf(eftNonRes.getText().toString()));
                intent.putExtra("Energy Second Threshold",Double.valueOf(estNonRes.getText().toString()));
                intent.putExtra("Energy Third Threshold",Double.valueOf(ettNonRes.getText().toString()));
                intent.putExtra("Service Charge",Double.valueOf(serviceChargeNonRes.getText().toString()));
                intent.putExtra("NEL",Double.valueOf(nelNonRes.getText().toString()));
                intent.putExtra("Street Light",Double.valueOf(streetLightNonRes.getText().toString()));
                intent.putExtra("VAT",Double.valueOf(vatNonRes.getText().toString()));
                intent.putExtra("GETFUND",Double.valueOf(getFundNonRes.getText().toString()));
                intent.putExtra("Total Bill",Double.valueOf(billNonRes.getText().toString()));
                startActivity(intent);

            }
        });
    }

    public void onClickResCalc(View v){
        energy_first_threshold = 0;
        energy_second_threshold = 0;
        energy_third_threshold = 0;
        energy_fourth_threshold = 0;
        eftRes.setText("0");
        estRes.setText("0");
        ettRes.setText("0");
        lifeLineRes.setText("0");
        unit_g = Double.valueOf(unitRes.getText().toString());
        days_g = Integer.valueOf(daysRes.getText().toString());

        checkLifeline = days_g * 1.6438;

        subs_check_g = unit_g/days_g;

        if(unit_g <= 50){
            eftRes.setText(String.valueOf(unit_g));
            serviceChargeRes.setText(String.valueOf(calculateResServiceCharge(days_g,unit_g)));
            subsidyRes.setText(String.valueOf(calculateResSubsidy(days_g,unit_g)));
            nelRes.setText(String.valueOf(calculateResNel(unit_g,0,0,0)));
            streeLightRes.setText(String.valueOf(calculateResStreetLight(unit_g,0,0,0)));
            totalBillRes.setText(String.valueOf(calculateResidentialBill(unit_g,0,0,0,days_g,unit_g)));
        }

        if(unit_g > 50){
            energy_first_threshold = Math.round(checkLifeline);

            lifeLineRes.setText(String.valueOf(energy_first_threshold));

            if(energy_first_threshold != unit_g && energy_first_threshold < unit_g){
                energy_second_threshold = Math.round(8.2191 * days_g);

                addon = energy_first_threshold + energy_second_threshold;

                if (addon > unit_g) {
                    energy_second_threshold = Math.round(unit_g - energy_first_threshold);
                }

                eftRes.setText(String.valueOf(energy_second_threshold));

                if (addon != unit_g && addon < unit_g) {
                    energy_third_threshold = Math.round(9.2191 * days_g);
                    addon = energy_first_threshold + energy_second_threshold + energy_third_threshold;
                    if (addon > unit_g) {
                        energy_third_threshold = Math.round(unit_g - (energy_first_threshold + energy_second_threshold));

                    }

                    estRes.setText(String.valueOf(energy_third_threshold));

                }

                if (addon != unit_g && addon < unit_g) {
                    energy_fourth_threshold = Math.round(19.7260 * days_g);
                    addon = energy_first_threshold + energy_second_threshold + energy_third_threshold + energy_fourth_threshold;

                    if (addon < unit_g || addon > unit_g) {
                        energy_fourth_threshold = Math.round(unit_g - (energy_first_threshold + energy_second_threshold + energy_third_threshold));
                        ettRes.setText(String.valueOf(energy_fourth_threshold));
                    }
                }

                addon = energy_first_threshold + energy_second_threshold + energy_third_threshold + energy_fourth_threshold;
                serviceChargeRes.setText(String.valueOf(calculateResServiceCharge(days_g,unit_g)));
                subsidyRes.setText(String.valueOf(calculateResSubsidy(days_g,unit_g)));
                nelRes.setText(String.valueOf(calculateResNel(energy_first_threshold,energy_second_threshold,energy_third_threshold,energy_fourth_threshold)));
                streeLightRes.setText(String.valueOf(calculateResStreetLight(energy_first_threshold,energy_second_threshold,energy_third_threshold,energy_fourth_threshold)));
                totalBillRes.setText(String.valueOf(calculateResidentialBill(energy_first_threshold,energy_second_threshold,energy_third_threshold,energy_fourth_threshold,days_g,unit_g)));

            }

        }
    }

    public void onClickNonResCalc(View v){
        energy_first_threshold_nr = 0;
        energy_second_threshold_nr = 0;
        energy_third_threshold_nr = 0;
        eftNonRes.setText("0");
        estNonRes.setText("0");
        ettNonRes.setText("0");
        unit_g = Double.valueOf(unitNonRes.getText().toString());
        days_g = Integer.valueOf(daysNonRes.getText().toString());

        checkLifeline_nr = days_g * 9.8630;
        if (unit_g <= 300) {
            energy_first_threshold_nr = unit_g;

            serviceChargeNonRes.setText(String.valueOf(calNonResServiceCharge(days_g)));
            nelNonRes.setText(String.valueOf(calcNonResNel(energy_first_threshold_nr,0,0)));
            streetLightNonRes.setText(String.valueOf(calcNonResStreetLight(energy_first_threshold_nr,0,0)));
            vatNonRes.setText(String.valueOf(calcVAT(energy_first_threshold_nr,0,0,days_g)));
            getFundNonRes.setText(String.valueOf(calcGFNHIL(energy_first_threshold_nr,0,0,days_g)));
            billNonRes.setText(String.valueOf(calculateNonResBill(energy_first_threshold_nr,0,0,days_g)));
        }

        if (unit_g > 300) {
            energy_first_threshold_nr = Math.round(checkLifeline_nr);

            eftNonRes.setText(String.valueOf(energy_first_threshold_nr));
            // echo 'EFT '.$energy_first_threshold.'<br>';

            if (energy_first_threshold_nr != unit_g && energy_first_threshold_nr < unit_g) {
                energy_second_threshold_nr = Math.round(9.8630 * days_g);
                addon_nr = energy_first_threshold_nr + energy_second_threshold_nr;
                if (addon_nr > unit_g) {
                    energy_second_threshold_nr = unit_g - energy_first_threshold_nr;
                    energy_second_threshold_nr = Double.valueOf(twoDForm.format(energy_second_threshold_nr));
                }
                estNonRes.setText(String.valueOf(energy_second_threshold_nr));
            }
            if (addon_nr != unit_g && addon_nr < unit_g) {
                energy_third_threshold_nr = Math.round(19.7260 * days_g);
                addon_nr = energy_first_threshold_nr + energy_second_threshold_nr + energy_third_threshold_nr;

                if (addon_nr < unit_g || addon_nr > unit_g) {
                    energy_third_threshold_nr = Math.round(unit_g - (energy_first_threshold_nr + energy_second_threshold_nr));
                    ettNonRes.setText(String.valueOf(energy_third_threshold_nr));
                    //echo 'ETT '.$energy_third_threshold.'<br>';
                }
            }

            serviceChargeNonRes.setText(String.valueOf(calNonResServiceCharge(days_g)));
            nelNonRes.setText(String.valueOf(calcNonResNel(energy_first_threshold_nr,energy_second_threshold_nr,energy_third_threshold_nr)));
            streetLightNonRes.setText(String.valueOf(calcNonResStreetLight(energy_first_threshold_nr,energy_second_threshold_nr,energy_third_threshold_nr)));
            vatNonRes.setText(String.valueOf(calcVAT(energy_first_threshold_nr,energy_second_threshold_nr,energy_third_threshold_nr,days_g)));
            getFundNonRes.setText(String.valueOf(calcGFNHIL(energy_first_threshold_nr,energy_second_threshold_nr,energy_third_threshold_nr,days_g)));
            billNonRes.setText(String.valueOf(calculateNonResBill(energy_first_threshold_nr,energy_second_threshold_nr,energy_third_threshold_nr,days_g)));
        }
    }

    public double calculateResidentialBill(double eft,double est,double ett,double e4t,int days,double unit){
        double c1,c2,c3,c4,subs_check,service_charge,subsidy,total_energy,nel,total_bill,street_light;

        c1 = Double.valueOf(twoDForm.format(eft * new_res_0_50));
        c2 = Double.valueOf(twoDForm.format(est * new_res_51_300));
        c3 = Double.valueOf(twoDForm.format(ett * new_res_301_600));
        c4 = Double.valueOf(twoDForm.format(e4t * new_res_600p));

        subs_check = unit/days;

        service_charge = 0;
        subsidy = 0;

        total_energy = Double.valueOf(twoDForm.format(c1 + c2 + c3 + c4));

        if(unit <= 50){
            service_charge = Double.valueOf(twoDForm.format(new_res_sc_0_50 * days));
        }else{
            service_charge = Double.valueOf(twoDForm.format(new_res_sc_50p * days));
        }

        //echo 'Service Charge: '.$service_charge.'<br>';

        if (unit <= 50 && subs_check <= 1.6438) {
            subsidy = Double.valueOf(twoDForm.format(new_res_subsidy * days));
        } else {
            subsidy = 0;
        }

        nel = Double.valueOf(twoDForm.format(0.02 * total_energy));
        street_light = Double.valueOf(twoDForm.format(0.03 * total_energy));

        total_bill = total_energy + service_charge + nel + street_light - subsidy;

        return Double.valueOf(twoDForm.format(total_bill));
    }
    public double calculateResServiceCharge(int days, double unit){

        double service_charge;

        if(unit <= 50){
            service_charge = Double.valueOf(twoDForm.format(new_res_sc_0_50 * days));
        }else{
            service_charge = Double.valueOf(twoDForm.format(new_res_sc_50p * days));
        }
        return service_charge;
    }
    public double calculateResSubsidy(int days, double unit){
        double subs_check,subsidy;
        subs_check = unit/days;
        if (unit <= 50 && subs_check <= 1.6438) {
            subsidy = Double.valueOf(twoDForm.format(new_res_subsidy * days));
        } else {
            subsidy = 0;
        }

        return subsidy;
    }
    public double calculateResNel(double eft, double est, double ett, double e4t){
        double c1,c2,c3,c4,total_energy,nel;

        c1 = Double.valueOf(twoDForm.format(eft * new_res_0_50));
        c2 = Double.valueOf(twoDForm.format(est * new_res_51_300));
        c3 = Double.valueOf(twoDForm.format(ett * new_res_301_600));
        c4 = Double.valueOf(twoDForm.format(e4t * new_res_600p));

        total_energy = Double.valueOf(twoDForm.format(c1 + c2 + c3 + c4));

        nel = Double.valueOf(twoDForm.format(0.02 * total_energy));

        return nel;
    }
    public double calculateResStreetLight(double eft, double est, double ett, double e4t){
        double c1,c2,c3,c4,total_energy,street_light;

        c1 = Double.valueOf(twoDForm.format(eft * new_res_0_50));
        c2 = Double.valueOf(twoDForm.format(est * new_res_51_300));
        c3 = Double.valueOf(twoDForm.format(ett * new_res_301_600));
        c4 = Double.valueOf(twoDForm.format(e4t * new_res_600p));

        total_energy = Double.valueOf(twoDForm.format(c1 + c2 + c3 + c4));

        street_light = Double.valueOf(twoDForm.format(0.03 * total_energy));

        return street_light;
    }

    public double calculateNonResBill(double eft, double est, double ett, int days){
        double c1,c2,c3,vat,bill,gfNhil,service_charge,service_energy,total_energy,nel,street_light;

        c1 =  Double.valueOf(twoDForm.format(eft * non_res_new_tariff_0_100));
        c2 =  Double.valueOf(twoDForm.format(est * non_res_new_tariff_301_600));
        c3 =  Double.valueOf(twoDForm.format(ett * non_res_new_tariff_600p));

        total_energy = c1 + c2 + c3;

        service_charge = Double.valueOf(twoDForm.format(non_res_new_sc * days));

        service_energy = total_energy + service_charge;


        //echo 'Service Charge: '.$service_charge.'<br>';

        nel = Double.valueOf(twoDForm.format(0.02 * total_energy));
        //echo 'NEL '.$nel.'<br>';

        street_light = Double.valueOf(twoDForm.format(0.03 * total_energy));
        //echo 'Street Light '.$street_light.'<br>';

        vat = Double.valueOf(twoDForm.format(service_energy * 0.125));
        //echo 'VAT '.$vat.'<br>';
        gfNhil = Double.valueOf(twoDForm.format(service_energy * 0.05));
       // echo 'GETFUND & NHIL '.$gfNhil.'<br>';

        bill = service_energy + nel + street_light + vat + gfNhil;

        //echo 'Bill is '.$bill;
        return Double.valueOf(twoDForm.format(bill));

    }

    public double calNonResServiceCharge(int days){
        double service_charge;
        service_charge = Double.valueOf(twoDForm.format(non_res_new_sc * days));
        return service_charge;
    }

    public double calcNonResNel(double eft, double est, double ett){
        double c1,c2,c3,total_energy,nel;

        c1 =  Double.valueOf(twoDForm.format(eft * non_res_new_tariff_0_100));
        c2 =  Double.valueOf(twoDForm.format(est * non_res_new_tariff_301_600));
        c3 =  Double.valueOf(twoDForm.format(ett * non_res_new_tariff_600p));

        total_energy = c1 + c2 + c3;

        nel = Double.valueOf(twoDForm.format(0.02 * total_energy));
        return nel;

    }
    public double calcNonResStreetLight(double eft, double est, double ett){
        double c1,c2,c3,total_energy,street_light;

        c1 =  Double.valueOf(twoDForm.format(eft * non_res_new_tariff_0_100));
        c2 =  Double.valueOf(twoDForm.format(est * non_res_new_tariff_301_600));
        c3 =  Double.valueOf(twoDForm.format(ett * non_res_new_tariff_600p));

        total_energy = c1 + c2 + c3;

        street_light = Double.valueOf(twoDForm.format(0.03 * total_energy));
        return street_light;

    }

    public double calcVAT(double eft, double est, double ett,int days){
        double c1,c2,c3,vat,service_charge,service_energy,total_energy;

        c1 =  Double.valueOf(twoDForm.format(eft * non_res_new_tariff_0_100));
        c2 =  Double.valueOf(twoDForm.format(est * non_res_new_tariff_301_600));
        c3 =  Double.valueOf(twoDForm.format(ett * non_res_new_tariff_600p));

        total_energy = c1 + c2 + c3;

        service_charge = Double.valueOf(twoDForm.format(non_res_new_sc * days));

        service_energy = total_energy + service_charge;

        vat = Double.valueOf(twoDForm.format(service_energy * 0.125));

        return  vat;
    }

    public double calcGFNHIL(double eft, double est, double ett,int days){
        double c1,c2,c3,gfNhil,service_charge,service_energy,total_energy;

        c1 =  Double.valueOf(twoDForm.format(eft * non_res_new_tariff_0_100));
        c2 =  Double.valueOf(twoDForm.format(est * non_res_new_tariff_301_600));
        c3 =  Double.valueOf(twoDForm.format(ett * non_res_new_tariff_600p));

        total_energy = c1 + c2 + c3;

        service_charge = Double.valueOf(twoDForm.format(non_res_new_sc * days));

        service_energy = total_energy + service_charge;

        gfNhil = Double.valueOf(twoDForm.format(service_energy * 0.05));

        return gfNhil;
    }


    public void radioButtonClicked(View v){
        boolean checked = ((RadioButton) v).isChecked();

        switch(v.getId()) {
            case R.id.radioResidential:
                if (checked){
                    residentialLL.setVisibility(View.VISIBLE);
                    nonResidentialLL.setVisibility(View.GONE);
                }
                break;
                case R.id.radioNonRes:
                    if (checked){
                        residentialLL.setVisibility(View.GONE);
                        nonResidentialLL.setVisibility(View.VISIBLE);
                    }
                break;
        }
    }
}
