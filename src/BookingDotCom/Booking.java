package BookingDotCom;

/**
 * Created by mohma on 5/15/2017.
 */
public class Booking {
    String name,address,features, description, link;
    int stars;
    float rating,price;

    public static String DELIMITER="~~~";

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getFeatures() {
        return features;
    }

    public String getDescription() {
        return description;
    }

    public String getLink() {
        return link;
    }

    public int getStars() {
        return stars;
    }

    public float getRating() {
        return rating;
    }

    public float getPrice() {
        return price;
    }



    public Booking(String data){
        String[] datas=data.split("~~~");

        name=datas[0];
        address=datas[1];
        String star=datas[2].split("-")[0];
        try {
            stars = Integer.parseInt(star);
        }
        catch (Exception e){
            stars=0;
        }
        try {
            rating=Float.parseFloat(datas[3]);
        }
        catch (Exception e){
            rating=0;
        }
        String prices[]=datas[4].split("EUR");
        if(prices.length>1){
            try {
                prices[1]=prices[1].replace(",","").trim();

                price = Float.parseFloat(prices[1].substring(1));
            }
            catch (Exception e){
                price=0;
                e.printStackTrace();
            }
        }
        features=datas[5];
        description=datas[6];
        link=datas[7];
    }

    @Override
    public String toString() {
        return name+"-"+address+"-"+features+"-"+description+"-"+link+"-"+stars+"-"+rating+"-"+price;
    }
}
