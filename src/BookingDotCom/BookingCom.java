package BookingDotCom;

import Documents.Information;
import Documents.ItemsFetcher;

import java.util.ArrayList;

/**
 * Created by mohma on 5/15/2017.
 */
public class BookingCom extends ItemsFetcher {
    public static final String baseURL="https://www.booking.com/searchresults.html?";

    public BookingCom(){
        super(BookingCom.baseURL,".sr_item_content");
    }


    public BookingCom setSearchString(String term){
        appendToURL("ss",term);
        return this;
    }

    public BookingCom setRoom(int noOfRooms){
        appendToURL("no_rooms",noOfRooms+"");
        return this;
    }

    public BookingCom setNoOfAdults(int noOfAdults){
        appendToURL("group_adults",noOfAdults+"");
        return this;
    }

    public BookingCom setNoOfChildren(int noOfChildren){
        appendToURL("group_children",noOfChildren+"");
        return this;
    }

    public BookingCom setCheckIn(String checkIn){
        appendToURL("checkin",checkIn);
        return this;
    }

    public BookingCom setCheckOut(String checkOut){
        appendToURL("checkout",checkOut);
        return this;
    }

    public BookingCom setRatings(int[] ratings){
        String content="";
        for(int rating:ratings){
            content+="class%3D"+rating;
        }
        content+="%3B&unchecked_filter=class";
        appendToURL("nflt",content);
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
