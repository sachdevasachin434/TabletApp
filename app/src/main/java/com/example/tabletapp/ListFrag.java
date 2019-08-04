package com.example.tabletapp;


import android.R.layout;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.provider.DocumentsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import static android.os.Looper.getMainLooper;
import static com.example.tabletapp.R.layout.fragment_list;
import static com.example.tabletapp.R.layout.list_layout;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListFrag extends Fragment {
    Activity context;
    FirebaseFirestore db;
    String text1,text2,text3;
    TextView tvtemp1,tvtemp2,tvtemp3;
    ListView lv;
    SlotToTime stt;
    String[] str;
    List<TimeSlot_Data> dummytslot;
    String strtime;
    TextView tdate;
    List<HashMap<String,String>> aList;
    SimpleDateFormat tformat;
    CollectionReference bookedDate2;
    View newView;
List<TimeSlot_Data> timeSlots,timeSlots2;

    public ListFrag() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        newView = getView();
        lv=newView.findViewById(R.id.lv);

        text2=new String();
        tvtemp1=newView.findViewById(R.id.tvtemp1);
        tvtemp2=newView.findViewById(R.id.tvtemp2);
        tvtemp3=newView.findViewById(R.id.tvtemp3);
        tdate= newView.findViewById(R.id.date);
        dummytslot=new ArrayList<>();


        db = FirebaseFirestore.getInstance();
        tdate.setVisibility(View.GONE);
        String[] str=new String[2];

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dformat = new SimpleDateFormat("dd_MM_yyyy");
        tformat = new SimpleDateFormat("HH:mm");
        strtime=tformat.format(calendar.getTime());
        refresh();

    }



    public void refresh()
    {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE,0);
        SimpleDateFormat dformat = new SimpleDateFormat("dd_MM_yyyy");
        String currentDate = dformat.format(cal.getTime());
        bookedDate2 = db.collection("Bookings").document("room2").collection(currentDate);

        bookedDate2.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@javax.annotation.Nullable QuerySnapshot queryDocumentSnapshots, @javax.annotation.Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Toast.makeText(context, "Listen Failed", Toast.LENGTH_SHORT).show();
                    return;
                }

                timeSlots = new ArrayList<>();
                for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                    timeSlots.add(doc.toObject(TimeSlot_Data.class));
                }
                getData(timeSlots);

                for (DocumentChange document : queryDocumentSnapshots.getDocumentChanges()) {
                    timeSlots2=new ArrayList<>();
                    switch (document.getType()) {
                        case ADDED:
                            for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                                timeSlots2.add(doc.toObject(TimeSlot_Data.class));
                            }
                            getData(timeSlots2);
                            break;
                    }
                }
            }
        });
    }

    public void getData(List<TimeSlot_Data> tslots) {

        stt=new SlotToTime();
        str=new String[2];
        final List<TimeSlot_Data> dummytslot2=new ArrayList<>();
        qsort(tslots);
        int j=0;
        for (int i= 0; i< dummytslot.size(); i++)
        {
            str = stt.ConvertToTime(dummytslot.get(i).getSlot());
            if (strtime.compareTo(str[1])<0)
            {
                dummytslot2.add(j,dummytslot.get(i));
                j++;
            }
        }

        preparelist(dummytslot2);


        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                long date = System.currentTimeMillis();
                                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                                String dateString = sdf.format(date);
                                tdate.setText(dateString);
                                if (dummytslot2.size()!=0) {
                                    if (tdate.getText().toString().substring(6, 8).compareTo("00") == 0) {
                                        if (tdate.getText().toString().substring(0, 5).compareTo(stt.ConvertToTime(dummytslot2.get(0).getSlot())[1]) == 0) {
                                            dummytslot2.remove(0);
                                            preparelist(dummytslot2);

                                        }
                                    }
                                }
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };
        t.start();
    }


        public void preparelist(List<TimeSlot_Data> a)
                {
                    aList = new ArrayList<>();
                    if(a.size()!=0) {
                        text1 = stt.ConvertToTime(a.get(0).getSlot())[0];
                        text2 = stt.ConvertToTime(a.get(0).getSlot())[1];
                        text3=a.get(0).getName();
                        tvtemp1.setText(text1);
                        tvtemp2.setText(text2);
                        tvtemp3.setText(text3);
                    }
                    else
                    {
                        Toast.makeText(getContext(), "No bookings yet on this date.", Toast.LENGTH_SHORT).show();
                        tvtemp2.setText("30");
                        tvtemp1.setText("30");
                    }

                    for (int i= 0; i< a.size(); i++) {

                        HashMap<String, String> hm = new HashMap<>();
                        str = stt.ConvertToTime(a.get(i).getSlot());
                        hm.put("time1", str[0]);
                        hm.put("time2", str[1]);
                        hm.put("company", a.get(i).getName());
                        hm.put("purpose", a.get(i).getPurpose());
                        aList.add(hm);
                    }
                    String[] from = { "time1","time2","company","purpose" };
                    int[] to = { R.id.time1,R.id.time2,R.id.company,R.id.purpose};



                    SimpleAdapter adapter = new SimpleAdapter(getContext(), aList, R.layout.list_layout, from, to);
                    lv.setAdapter(adapter);

    }


    public void qsort(List<TimeSlot_Data> tslot) {

        if (tslot == null || tslot.size() == 0) {
            return;
        }

        dummytslot = tslot;
        quickSort(0, (dummytslot.size()-1));
    }

    private void quickSort(int left, int right) {

        int i = left;
        int j = right;

        int pivot = dummytslot.get(left+(right-left)/2).getSlot();

        while (i <= j) {

            while (dummytslot.get(i).getSlot() < pivot) { i++; } while (dummytslot.get(j).getSlot() > pivot) {
                j--;
            }
            if (i <= j) {
                exchange(i, j);
                i++;
                j--;
            }
        }
        if (left < j)
            quickSort(left, j);
        if (i < right)
            quickSort(i, right);
    }

    private void exchange(int i, int j) {
        TimeSlot_Data temp = new TimeSlot_Data();

        temp.setSlot(dummytslot.get(i).getSlot());
        temp.setName(dummytslot.get(i).getName());
        temp.setPurpose(dummytslot.get(i).getPurpose());

        dummytslot.get(i).setSlot(dummytslot.get(j).getSlot());
        dummytslot.get(i).setName(dummytslot.get(j).getName());
        dummytslot.get(i).setPurpose(dummytslot.get(j).getPurpose());

        dummytslot.get(j).setSlot(temp.getSlot());
        dummytslot.get(j).setName(temp.getName());
        dummytslot.get(j).setPurpose(temp.getPurpose());
    }
}
