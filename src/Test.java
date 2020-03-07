import BookingDotCom.Booking;
import BookingDotCom.BookingCom;
import CSVWriter.Exceptions.FieldMismatchException;
import Documents.Information;
import CSVWriter.Writer;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by mohma on 5/14/2017.
 */
public class Test {

    public static ArrayList<Information> createInformationFetchList(){
        ArrayList<Information> informations=new ArrayList<>();
        //name
        informations.add(new Information("sr-hotel__name",Information.TYPE_CLASS,Information.FIRST));
        //address
        informations.add(new Information("address",Information.TYPE_CLASS,Information.FIRST));
        //stars
        informations.add(new Information("invisible_spoken",Information.TYPE_CLASS,Information.FIRST));
        //rating
        informations.add(new Information("average",Information.TYPE_CLASS,Information.FIRST));
        //price
        informations.add(new Information("totalPrice",Information.TYPE_CLASS,Information.FIRST));
        //features
        informations.add(new Information("facility-badge__title",Information.TYPE_CLASS,Information.ALL));
        //description
        informations.add(new Information("hotel_desc",Information.TYPE_CLASS,Information.FIRST));
        //url
        informations.add(new Information("hotel_name_link url",Information.TYPE_CLASS,Information.FIRST,Information.TYPE_ABSOLUTE_URL));


        //price availprice no_rack_rate

        return informations;
    }

    public static void main(String[] args){
        String csvFile = "result.csv";
        String[] fields={"Name","Address","Stars","Rating", "Price","Features","Description","Link"};
        Writer writer=null;
        try {
            writer = new Writer(new File(csvFile),Writer.DEFAULT_ENCODING).setDelimiter("|").setFields(fields);
        } catch (IOException e) {
            e.printStackTrace();
        }

        BookingCom b= new BookingCom().setSearchString("Athens").setRoom(2).setRatings(new int[]{1}).setCheckIn("2020-03-07").setCheckOut("2020-03-07");
        System.out.println(b.getURL());

        ArrayList<Object> arrString=b.fetchData(createInformationFetchList(),10,Booking.class);

        for(Object datas:arrString){
            Booking hotel=(Booking)datas;
            try {
                writer.addRow(new String[]{hotel.getName(),hotel.getAddress(),hotel.getStars()+"",hotel.getRating()+"",hotel.getPrice()+"",hotel.getFeatures(),hotel.getDescription(),hotel.getLink()});
            } catch (FieldMismatchException e) {
                e.printStackTrace();
            }
        }
        writer.write();
        writer.close();
    }
}
