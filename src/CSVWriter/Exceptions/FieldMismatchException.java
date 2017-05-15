package CSVWriter.Exceptions;

/**
 * Created by mohma on 5/14/2017.
 */
public class FieldMismatchException extends Exception {
    public FieldMismatchException(){

        super("Number of fields don't match");
    }
}
