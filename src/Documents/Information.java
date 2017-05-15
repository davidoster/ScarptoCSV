package Documents;

/**
 * Created by mohma on 5/15/2017.
 */
public class Information {
    public static final int TYPE_CLASS=0;
    public static final int TYPE_ID=1;
    //How many to fetch
    public static final int ALL=0;
    public static final int FIRST=1;

    public static final String TYPE_ABSOLUTE_URL="abs:href";

    private String term;
    private int type;
    private int amount;
    private String attribute;

    /**
     * Use this constructor if you are looking for value inside the element
     * @param term: the field to be searched for
     * @param type: is this search by class or ID
     * @param amount: do you want to get the only the first value or all values
     */
    public Information(String term,int type,int amount){
        this.term=term;
        this.type=type;
        this.amount=amount;
    }

    /**
     * Use this constructor if your are looking for an attribute value
     * @param term: the field to be searched for
     * @param type: is this search by class or ID
     * @param amount: do you want to get the only the first value or all values
     * @param attr: Attribute to search for
     */
    public Information(String term,int type,int amount,String attr){
        this.term=term;
        this.type=type;
        this.amount=amount;
        this.attribute=attr;
    }


    /**
     *
     * @return the query term
     */
    public String getTerm(){
        return this.term;
    }

    /**
     *
     * @return the type of query
     */
    public int getType(){
        return this.type;
    }

    /**
     *
     * @return how many records to queried
     */
    public int getAmount(){
        return this.amount;
    }

    /**
     *
     * @return the attribute to be searched for
     */
    public String getAttribute(){
        return this.attribute;
    }
}
