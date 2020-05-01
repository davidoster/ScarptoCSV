package BookingDotCom;

import Documents.Information;
import Documents.ItemsFetcher;

import java.util.ArrayList;

/**
 * Created by mohma on 5/15/2017.
 */
public class TrivagoGr extends ItemsFetcher {
    public static final String baseURL="https://trivago.com?";

    public TrivagoGr(){
        super(TrivagoGr.baseURL,".sr_item_content");
    }


    public TrivagoGr setSearchString(String term){
        appendToURL("address",term);
        return this;
    }

    public TrivagoGr setRoomAndAdults(int noOfRooms, int noOfAdults){
        appendToURL("aRooms%5B" + noOfRooms + "%5D%5Badults%5D",noOfAdults + "");
        return this;
    }

    public TrivagoGr setCheckIn(String checkIn){
        appendToURL("aDateRange%5Barr%5D",checkIn);
        return this;
    }

    public TrivagoGr setCheckOut(String checkOut){
        appendToURL("aDateRange%5Bdep%5D",checkOut);
        return this;
    }

    public TrivagoGr setRoomType(String roomType) {
        if(roomType.equals("1"))
            appendToURL("iRoomType", "1");
        else
            appendToURL("iRoomType", "7");
        return this;
    }


    public ArrayList<Object> fetchData(ArrayList<Information> info,int maxRecords, Class classToUser){
        ArrayList<Object> result= new ArrayList<Object>();
        for(int i=0;i<maxRecords;){
            System.out.println(getURL()+"&rows=15&offset="+i);
            ArrayList<Object> temp=fetch(info,getURL()+"&rows=15&offset="+i,classToUser,Booking.DELIMITER);
            result.addAll(temp);
            i+=15;
        }
        return result;
    }


}
