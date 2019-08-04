package com.example.tabletapp;

public class CTime_EndTime {
    public String getrefreshtime(String str)
    {
        if(str.compareTo("09:00:00")>0 && str.compareTo("09:30:00")<0)
        {
            return "09:30:00";
        }
        else if(str.compareTo("09:30:00")>0 && str.compareTo("10:00:00")<0)
        {
            return "10:00:00";
        }
        else if(str.compareTo("10:00:00")>0 && str.compareTo("10:30:00")<0) {
            return "10:30:00";
        }
        else if(str.compareTo("10:30:00")>0 && str.compareTo("11:00:00")<0)
        {
            return "11:00:00";
        }
        else if(str.compareTo("11:00:00")>0 && str.compareTo("11:30:00")<0)
        {
            return "11:30:00";
        }
        else if(str.compareTo("11:30:00")>0 && str.compareTo("12:00:00")<0)
        {
            return "12:00:00";
        }
        else if(str.compareTo("12:00:00")>0 && str.compareTo("12:30:00")<0)
        {
            return "12:30:00";
        }
        else if(str.compareTo("12:30:00")>0 && str.compareTo("13:00:00")<0)
        {
            return "13:00:00";
        }
        else if(str.compareTo("13:00:00")>0 && str.compareTo("13:30:00")<0)
        {
            return "13:30:00";
        }
        else if(str.compareTo("13:30:00")>0 && str.compareTo("14:00:00")<0)
        {
            return "14:00:00";
        }
        else if(str.compareTo("14:00:00")>0 && str.compareTo("14:30:00")<0)
        {
            return "14:30:00";
        }
        else if(str.compareTo("14:30:00")>0 && str.compareTo("15:00:00")<0)
        {
            return "15:00:00";
        }
        else if(str.compareTo("15:00:00")>0 && str.compareTo("15:30:00")<0)
        {
            return "15:30:00";
        }
        else if(str.compareTo("15:30:00")>0 && str.compareTo("16:00:00")<0)
        {
            return "16:00:00";
        }
        else if(str.compareTo("16:00:00")>0 && str.compareTo("16:30:00")<0)
        {
            return "16:30:00";
        }
        else if(str.compareTo("16:30:00")>0 && str.compareTo("17:00:00")<0)
        {
            return "17:00:00";
        }
        else if(str.compareTo("17:00:00")>0 && str.compareTo("17:30:00")<0)
        {
            return "17:30:00";
        }
        else if(str.compareTo("17:30:00")>0 && str.compareTo("18:00:00")<0)
        {
            return "18:00:00";
        }
        else {
            return "00:00:00";
        }
    }
}
