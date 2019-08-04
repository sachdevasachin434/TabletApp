package com.example.tabletapp;

import java.lang.StringBuilder;
public class SlotToTime {
    String str1= "";
    String str2= "";
    public String[] ConvertToTime(int slot)
    {
        switch (slot)
        {
            case 0:
                str1="09:00";
                str2="09:30";
                break;
            case 1:
                str1="09:30";
                str2="10:00";
                break;
            case 2:
                str1="10:00";
                str2="10:30";
                break;
            case 3:
                str1="10:30";
                str2="11:00";
                break;
            case 4:
                str1="11:00";
                str2="11:30";
                break;
            case 5:
                str1="11:30";
                str2="12:00";
                break;
            case 6:
                str1="12:00";
                str2="12:30";
                break;
            case 7:
                str1="12:30";
                str2="13:00";
                break;
            case 8:
                str1="13:00";
                str2="13:30";
                break;
            case 9:
                str1="13:30";
                str2="14:00";
                break;
            case 10:
                str1="14:00";
                str2="14:30";
                break;
            case 11:
                str1="14:30";
                str2="15:00";
                break;
            case 12:
                str1="15:00";
                str2="15:30";
                break;
            case 13:
                str1="15:30";
                str2="16:00";
                break;
            case 14:
                str1="16:00";
                str2="16:30";
                break;
            case 15:
                str1="16:30";
                str2="17:00";
                break;
            case 16:
                str1="17:00";
                str2="17:30";
                break;
            case 17:
                str1="17:30";
                str2="18:00";
                break;
            default:
                str1="";
                str2="";
                break;
        }
        String ar[] = new String[2];
        ar[0] = str1;
        ar[1] =  str2;
        return ar;
    }
}
