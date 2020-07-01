package com.himanshu.a2zlearning.navfrag;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.himanshu.a2zlearning.ui.activities.ChartActivity;
import com.himanshu.a2zlearning.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;


public class PerformanceFragment extends Fragment {

    ArrayList<Integer> marks;
    private String a;
    private final static String Total = "total.txt";
    private final static String Correct = "correct.txt";
    private final static String TestName = "testname.txt";

    public PerformanceFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_performance, container, false);
        TextView one = view.findViewById(R.id.one);
        TextView two = view.findViewById(R.id.two);
        TextView three = view.findViewById(R.id.three);
        TextView four = view.findViewById(R.id.four);
        TextView counter = view.findViewById(R.id.Counter);
        Button chart = view.findViewById(R.id.chartshow);

        FileInputStream fis1 = null, fis2 = null,fis3 = null;
        if(!exists()) {
            Toast.makeText(getContext(), "No Tests Given till Now", Toast.LENGTH_LONG).show();
            counter.setText(" 0");
        }
        else {
            try {
                assert getActivity()!=null;
                fis1 = getActivity().openFileInput(Total);
                fis2 = getActivity().openFileInput(Correct);
                fis3 = getActivity().openFileInput(TestName);
                InputStreamReader isr1 = new InputStreamReader(fis1);
                InputStreamReader isr2 = new InputStreamReader(fis2);
                InputStreamReader isr3 = new InputStreamReader(fis3);
                BufferedReader br1 = new BufferedReader(isr1);
                BufferedReader br2 = new BufferedReader(isr2);
                BufferedReader br3 = new BufferedReader(isr3);
                StringBuilder sb1 = new StringBuilder();
                StringBuilder sb2 = new StringBuilder();
                StringBuilder sb3 = new StringBuilder();
                String t1, t2, t3;
                while ((t1 = br1.readLine()) != null) {
                    sb1.append(t1);
                    sb1.append("qq");
                }
                while ((t2 = br2.readLine()) != null) {
                    sb2.append(t2);
                    sb2.append("qq");
                }
                while ((t3 = br3.readLine()) != null) {
                    sb3.append(t3);
                    sb3.append("qq");
                }
                int l1, l2, l3,up;
                float ans;
                l1 = sb1.indexOf("qq");
                l2 = sb2.indexOf("qq");
                a = sb1.substring(0, l1);
                String b = sb2.substring(0, l2);
                char[] barr = b.toCharArray();
                String pehli = "", doosri = "", teesri = "",chothi = "";
                counter.setText("  " + a);
                String temp;
                marks = new ArrayList<>();
                int count = Integer.parseInt(a);
                for (int i = 0; i < count; i++) {
                    l3 = sb3.indexOf("qq");
                    temp = sb3.substring(0, l3);
                    sb3.delete(0, l3 + 2);
                    pehli += setter(temp) + "\n\n";
                    doosri += "4\n\n";
                    teesri += barr[i] + "\n\n";
                    up = Integer.parseInt(String.valueOf(barr[i]));
                    marks.add(up);
                    ans = (float)up/(float)4;
                    ans *= 100;
                    chothi += ans+"\n\n";
                }
                one.setText(pehli);
                two.setText(doosri);
                three.setText(teesri);
                four.setText(chothi);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fis1 != null) {
                    try {
                        fis1.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis2 != null) {
                    try {
                        fis2.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis3 != null) {
                    try {
                        fis3.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        chart.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), ChartActivity.class);
            if(!(exists())){
                intent.putExtra("Total",0);
            } else {
                intent.putExtra("Total",Integer.parseInt(a));
            }
            intent.putExtra("CorrectList",marks);
            startActivity(intent);
        });
        return view;
    }

    private String setter(String temp) {
        switch (temp) {
            case "CppDPTest1" : return "DP T1";
            case "CppDPTest2" : return "DP T2";
            case "CppDPTest3" : return "DP T3";
            case "CppFullTest1" :
            case "UpscFullTest1" :
            case "JEEFullTest1" :
                return "Full T1";
            case "CppFullTest2" :
            case "UpscFullTest2" :
            case "JEEFullTest2" :
                return "Full T2";
            case "CppFullTest3" :
            case "UpscFullTest3" :
            case "JEEFullTest3" :
                return "Full T3";
            case "CppOOPSTest1" : return "OOPS T1";
            case "CppOOPSTest2" : return "OOPS T2";
            case "CppOOPSTest3" : return "OOPS T3";
            case "JEEChemistryTest1" : return "Chem T1";
            case "JEEChemistryTest2" : return "Chem T2";
            case "JEEChemistryTest3" : return "Chem T3";
            case "JEEPhysicsTest1" : return "Phy T1";
            case "JEEPhysicsTest2" : return "Phy T2";
            case "JEEPhysicsTest3" : return "Phy T3";
            case "UpscEconomyTest1" : return "Eco T1";
            case "UpscEconomyTest2" : return "Eco T2";
            case "UpscEconomyTest3" : return "Eco T3";
            case "UpscPolityTest1" : return "Polity T1";
            case "UpscPolityTest2" : return "Polity T2";
            case "UpscPolityTest3" : return "Polity T3";
            default : return "";
        }
    }

    private boolean exists() {
        File file = new File(Objects.requireNonNull(getActivity()).getApplicationContext().getFilesDir(),Total);
        return file.exists();
    }
}
