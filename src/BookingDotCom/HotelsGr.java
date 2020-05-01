package BookingDotCom;

import Documents.Information;
import Documents.ItemsFetcher;

import java.util.ArrayList;

/**
 * Created by mohma on 5/15/2017.
 */
public class HotelsGr extends ItemsFetcher {
    public static final String baseURL="https://el.hotels.com/search.do?resolved-location=CITY&as-shown=false";

    public HotelsGr(){
        super(HotelsGr.baseURL,".sr_item_content");
    }


    public HotelsGr setSearchString(String term){
        appendToURL("q-destination",term);
        return this;
    }

    public HotelsGr setRoomAndAdults(int noOfRooms, int noOfAdults){
        appendToURL("q-rooms" + noOfRooms + "&q-room-0-adults",noOfAdults + "");
        return this;
    }

    public HotelsGr setCheckIn(String checkIn){
        appendToURL("q-check-in",checkIn);
        appendToURL("q-localised-check-in",checkIn);
        return this;
    }

    public HotelsGr setCheckOut(String checkOut){
        appendToURL("q-check-out",checkOut);
        appendToURL("q-localised-check-out",checkOut);
        return this;
    }

//    public HotelsGr setRoomType(String roomType) {
//        if(roomType.equals("1"))
//            appendToURL("iRoomType", "1");
//        else
//            appendToURL("iRoomType", "7");
//        return this;
//    }


    public ArrayList<Object> fetchData(ArrayList<Information> info,int maxRecords, Class classToUser){
        ArrayList<Object> result= new ArrayList<Object>();
        ArrayList<Object> temp=fetch(info,getURL(),classToUser,Booking.DELIMITER);
//        for(int i=0;i<maxRecords;){
//            System.out.println(getURL()+"&rows=15&offset="+i);
//            //ArrayList<Object> temp=fetch(info,getURL()+"&rows=15&offset="+i,classToUser,Booking.DELIMITER);
//            ArrayList<Object> temp=fetch(info,getURL()+"&rows=15&offset="+i,classToUser,Booking.DELIMITER);
//            result.addAll(temp);
//            i+=15;
//        }
        return result;
    }


}
