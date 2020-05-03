package com.himanshu.a2zlearning;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class ChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        int total = getIntent().getIntExtra("Total",0);
        if(total==0) {
            Toast.makeText(this,"First Give Some Tests!!!",Toast.LENGTH_SHORT).show();
        }
        ArrayList<Integer> correctList = getIntent().getIntegerArrayListExtra("CorrectList");
        BarChart barChart = findViewById(R.id.barChart);

        List<BarEntry> chartEntries = new ArrayList<>();
        if(total!=0) {
            assert correctList!=null;
            for (int i = 0; i < total ; i++) {
                chartEntries.add(new BarEntry(i+1,(float)((correctList.get(i)*100)/4)));
            }
        }
        BarDataSet barDataSet = new BarDataSet(chartEntries,"");
        List<Integer> colors = new ArrayList<>();
        int[] first = ColorTemplate.COLORFUL_COLORS;
        int[] second = ColorTemplate.JOYFUL_COLORS;
        int[] third = ColorTemplate.MATERIAL_COLORS;
        int[] fourth = ColorTemplate.PASTEL_COLORS;
        for (int value : first) { colors.add(value); }
        for (int value : second) { colors.add(value); }
        for (int value : third) { colors.add(value); }
        for (int value : fourth) { colors.add(value); }
        barDataSet.setColors(colors);
        BarData barData = new BarData(barDataSet);
        barData.setBarWidth(0.9f);
        barChart.setPinchZoom(false);
        barChart.setVisibility(View.VISIBLE);
        barChart.animateY(3000);
        barChart.setData(barData);
        barChart.getDescription().setEnabled(false);
        barChart.setVisibleXRangeMaximum(10);
        barChart.setVisibleYRangeMinimum(120f, YAxis.AxisDependency.LEFT);
        barChart.moveViewToX(29);
        barChart.invalidate();
    }
}
