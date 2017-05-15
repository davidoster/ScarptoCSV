package Documents;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 * Created by mohma on 5/15/2017.
 */
public class ItemsFetcher {
    private String url;
    private String itemClass;

    public ItemsFetcher(String url, String itemClass){
        this.url=url;
        this.itemClass=itemClass;
    }

    /**
     *
     * @return the latest generated URL
     */
    public String getURL(){
        return url;
    }

    /**
     *
     * @param name : Get request name field
     * @param value : Get request value of the name field
     */
    public void appendToURL(String name,String value){
        url+="&"+name+"="+value;
    }

    /**
     *
     * @param informations : List of elements of a particular items that need to be queried for value
     * @param newURL : URL from where data needs to be fetched from
     * @param className : Class of the item
     * @param delimiter : Delimiter to facilate passing of entire string related to an item and then spliting into individual values
     * @return : Arraylist of object
     * @throws NullPointerException
     */
    public ArrayList<Object> fetch(ArrayList<Information> informations,String newURL,Class className, String delimiter) throws NullPointerException{
        Document doc = null;
        ArrayList<Object> result=new ArrayList<Object>();
            try {
                doc = Jsoup.connect(newURL).get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Elements items = doc.select(itemClass);
            for(Element item:items){
                ArrayList<String> temp=new ArrayList<String>();
                String ndata="";
                for(Information info:informations){
                    String data="";
                    switch (info.getType()){
                        case Information.TYPE_CLASS:
                            if(info.getAmount()==Information.FIRST){
                                try {
                                    Element element = item.getElementsByClass(info.getTerm()).first();
                                    if(element==null)
                                        break;
                                    if(info.getAttribute()==null)
                                        data= element.text();
                                    else
                                        data= element.attr(info.getAttribute());
                                }
                                catch (NullPointerException e){
                                    data="";
                                }
                            }
                            else{
                                Elements features=item.getElementsByClass(info.getTerm());
                                for(int j=0;j<features.size();j++){
                                    data+=features.get(j).text();
                                    if(j!=features.size()-1){
                                        data+=", ";
                                    }
                                }
                            }
                            break;
                        case Information.TYPE_ID:
                            data=item.getElementById(info.getTerm()).text();
                            break;
                    }

                    temp.add(data);

                    ndata+=data+delimiter;
                }
                try {
                    Class[] cArg = new Class[1]; //Our constructor has 3 arguments
                    cArg[0] = String.class; //Second argument is of *object* type String
                    Object datum=className.getDeclaredConstructor(cArg).newInstance(ndata);
                    result.add(datum);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }

        return result;
    }
}
