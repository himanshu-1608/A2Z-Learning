package com.himanshu.a2zlearning.handler;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.himanshu.a2zlearning.MainActivity;
import com.himanshu.a2zlearning.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;


public class CourseDataHandler extends Fragment {

    public CourseDataHandler(int myMessage) {
        this.myMessage = myMessage;
    }
    private int myMessage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_course_data_holder, container, false);

        ImageView img1 = view.findViewById(R.id.img1);
        ImageView img2 = view.findViewById(R.id.img2);
        ImageView img3 = view.findViewById(R.id.img3);
        LinearLayout l1 = view.findViewById(R.id.l1);
        LinearLayout l2 = view.findViewById(R.id.l2);
        LinearLayout l3 = view.findViewById(R.id.l3);
        TextView text1 = view.findViewById(R.id.text1);
        TextView text11 = view.findViewById(R.id.text11);
        TextView text2 = view.findViewById(R.id.text2);
        TextView text21 = view.findViewById(R.id.text21);
        TextView text3 = view.findViewById(R.id.text3);
        TextView text31 = view.findViewById(R.id.text31);

        assert this.getArguments() != null;
        assert getActivity()!=null;
        switch(myMessage) {
            case 1 :
                Objects.requireNonNull(((MainActivity) Objects.requireNonNull(getActivity())).getSupportActionBar()).setTitle("JEE Course Structure");
                img1.setImageDrawable(getActivity().getDrawable(R.drawable.ic_jee1));
                text1.setText(getActivity().getResources().getString(R.string.jeemt1));
                text11.setText(getActivity().getResources().getString(R.string.jeemt2));
                img2.setImageDrawable(getActivity().getDrawable(R.drawable.ic_youtube));
                text2.setText(getActivity().getResources().getString(R.string.cppt3));
                text21.setText(getActivity().getResources().getString(R.string.jeemt3));
                img3.setImageDrawable(getActivity().getDrawable(R.drawable.ic_mcqtest));
                text3.setText(getActivity().getResources().getString(R.string.cppt5));
                text31.setText(getActivity().getResources().getString(R.string.cppt6));
                break;
            case 2 :
                Objects.requireNonNull(((MainActivity) Objects.requireNonNull(getActivity())).getSupportActionBar()).setTitle("Cpp Course Structure");
                img1.setImageDrawable(getActivity().getDrawable(R.drawable.ic_cpp1));
                text1.setText(getActivity().getResources().getString(R.string.cppt1));
                text11.setText(getActivity().getResources().getString(R.string.cppt2));
                img2.setImageDrawable(getActivity().getDrawable(R.drawable.ic_youtube));
                text2.setText(getActivity().getResources().getString(R.string.cppt3));
                text21.setText(getActivity().getResources().getString(R.string.cppt4));
                img3.setImageDrawable(getActivity().getDrawable(R.drawable.ic_mcqtest));
                text3.setText(getActivity().getResources().getString(R.string.cppt5));
                text31.setText(getActivity().getResources().getString(R.string.cppt6));
                break;
            case 3 :
                Objects.requireNonNull(((MainActivity) Objects.requireNonNull(getActivity())).getSupportActionBar()).setTitle("UPSC GS Course Structure");
                img1.setImageDrawable(getActivity().getDrawable(R.drawable.ic_upsc1));
                text1.setText(getActivity().getResources().getString(R.string.ugt1));
                text11.setText(getActivity().getResources().getString(R.string.ugt2));
                img2.setImageDrawable(getActivity().getDrawable(R.drawable.ic_youtube));
                text2.setText(getActivity().getResources().getString(R.string.cppt3));
                text21.setText(getActivity().getResources().getString(R.string.ugt3));
                img3.setImageDrawable(getActivity().getDrawable(R.drawable.ic_mcqtest));
                text3.setText(getActivity().getResources().getString(R.string.cppt5));
                text31.setText(getActivity().getResources().getString(R.string.cppt6));
                break;
        }

        final Bundle bundle = new Bundle();
        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CourseNotesHandler frag = new CourseNotesHandler();
                ArrayList<String> topics = new ArrayList<>();
                ArrayList<String> files = new ArrayList<>();
                switch(myMessage) {
                    case 1 :
                        topics.add("Photoelectric Effect");
                        topics.add("Diffraction of Light");
                        files.add("DualNature.txt");
                        files.add("Diffraction.txt");
                        break;

                    case 2 :
                        topics.add("Longest Common Subsequence");
                        topics.add("Fractional KnapSack Problem");
                        files.add("LcsCode.txt");
                        files.add("KnapSack01.txt");
                        break;

                    case 3 :
                        topics.add("Types of Bank Accounts");
                        topics.add("Functions of Money");
                        files.add("BankACType.txt");
                        files.add("MoneyFunction.txt");
                        break;
                }
                bundle.putStringArrayList("TopicList",topics);
                bundle.putStringArrayList("FileList",files);
                frag.setArguments(bundle);
                assert getFragmentManager() != null;
                getFragmentManager().beginTransaction().replace(R.id.frame,frag).commit();
            }
        });

        l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CourseVideosHandler frag = new CourseVideosHandler();
                ArrayList<String> texts = new ArrayList<>();
                ArrayList<String> links = new ArrayList<>();
                switch(myMessage) {
                    case 1 :
                        texts.add("Source : ATP STAR-JEE");
                        texts.add("IUPAC Nomenclature");
                        texts.add("Binomial Theorem");
                        texts.add("Center of mass and collisions");
                        texts.add("Organic Tricks (Recommended)");
                        links.add("BejGtvWV3m0");
                        links.add("NjBPfRc6y-c");
                        links.add("OXfFa_J3lYI");
                        links.add("hvn1i68rC-E");
                        break;

                    case 2 :
                        texts.add("Source : geekforgeeks.org");
                        texts.add("Longest Common Subsequence");
                        texts.add("Fractional KnapSack Problem");
                        texts.add("Object Oriented Design & Design Patterns");
                        texts.add("A* Algorithm");
                        links.add("HgUOWB0StNE");
                        links.add("m1p-eWxrt6g");
                        links.add("AopCPq2cZlA");
                        links.add("vP5TkF0xJgI");
                        break;

                    case 3 :
                        texts.add("Source : Mrunal Patel");
                        texts.add("British Imperialism Exigencies");
                        texts.add("GDP and related terms");
                        texts.add("Fiscal Framework for India");
                        texts.add("Continental Drift Theory");
                        links.add("qhz6_nGD4Rw");
                        links.add("7jZ4RtMaU9s");
                        links.add("yzeJ7tVcHQA");
                        links.add("IxpPoVc7y3U");
                        break;
                }
                bundle.putStringArrayList("TextList",texts);
                bundle.putStringArrayList("LinkList",links);
                frag.setArguments(bundle);
                assert getFragmentManager() != null;
                getFragmentManager().beginTransaction().replace(R.id.frame,frag).commit();
            }
        });

        l3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CourseTestsHandler frag = new CourseTestsHandler();
                ArrayList<String> topics = new ArrayList<>();
                ArrayList<String> tests = new ArrayList<>(Arrays.asList("Test 1", "Test 2", "Test 3"));
                switch (myMessage) {
                    case 1 :
                        topics.add("Full Length Test(ALL TOPICS)");
                        topics.add("Organic Chemistry");
                        topics.add("Units & Dimensions");
                        break;
                    case 2 :
                        topics.add("Full Length Test(ALL TOPICS)");
                        topics.add("Dynamic Programming");
                        topics.add("OOPS (Object-Oriented)");
                        break;
                    case 3 :
                        topics.add("Full Length Test(ALL TOPICS)");
                        topics.add("Polity");
                        topics.add("Economy");
                        break;
                }
                bundle.putStringArrayList("TopicList",topics);
                bundle.putInt("CourseID",myMessage);
                bundle.putStringArrayList("TestList",tests);
                frag.setArguments(bundle);
                assert getFragmentManager() != null;
                getFragmentManager().beginTransaction().replace(R.id.frame,frag).commit();
            }
        });
        return view;
    }

}
