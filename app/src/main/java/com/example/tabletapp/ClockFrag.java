package com.example.tabletapp;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ClockFrag extends Fragment {

//    List<TimeSlot_Data> a;
//    String s;
//    Bundle b;
//    Button bstart;
    public ClockFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_clock, container, false);
//
//        bstart=view.findViewById(R.id.bstart);
//        bstart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                a = new ArrayList<>();
////                b = getArguments();
////                s= new String();
////                s = b.getString("list");
////            ListFrag list = new ListFrag();
////            a = list.dummytslot2;
//                String str = getArguments().getString("list");
////                if (args == null) {
//////                    showReceivedData.setText(args.getString(DATA_RECEIVE));
////                    Toast.makeText(getContext(), "hello", Toast.LENGTH_SHORT).show();
////                }
////                else{
//                    Toast.makeText(getContext(), str, Toast.LENGTH_SHORT).show();
////                }
//            }
//        });
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

//    public void onClick(View view) {
//        if (view.getId() == R.id.bstart) {
////            Intent intent = new Intent(getActivity(), Activity.class);
//            a = new ArrayList<>();
//            b = this.getArguments();
//            a = (List<TimeSlot_Data>) b.getSerializable("list");
////            ListFrag list = new ListFrag();
////            a = list.dummytslot2;
//            Toast.makeText(getContext(), a.get(0).getName(), Toast.LENGTH_SHORT).show();
////            if (list.strtime.compareTo(list.stt.ConvertToTime(a.get(0).getSlot())[0]) > 0 && list.strtime.compareTo(list.stt.ConvertToTime(a.get(0).getSlot())[1]) < 0) {
////                tvtimecount.setVisibility(View.VISIBLE);
////                bstart.setVisibility(View.GONE);
////            }
////            else
////            {
////                Toast.makeText(this, "Meeting Not booked at this time", Toast.LENGTH_SHORT).show();
////            }
////        if(a.get(0)!=null)
////        {
////            time0=a.get(0)
////        }
//        }
//    }
}