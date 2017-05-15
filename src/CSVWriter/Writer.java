package CSVWriter;

import CSVWriter.Exceptions.FieldMismatchException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * Created by mohma on 5/14/2017.
 */
public class Writer extends PrintWriter {
    private StringBuilder sb;
    private int noOfFields;
    /*
    Default Delimiter is comma
     */
    private String delimiter=",";
    /*
    Default file encoding format is UTF-8
     */
    public static final String DEFAULT_ENCODING="UTF8";

    /**
     *
     * @param file : The output file
     * @param Encoding : Encoding to be used
     * @throws FileNotFoundException
     * @throws UnsupportedEncodingException
     */
    public Writer(File file,String Encoding) throws FileNotFoundException, UnsupportedEncodingException {
        super(file,Encoding);
    }

    /**
     * This function must be called before setting fields if the delimiter type is different
     * @param delimiter : The type of delimiter to be used in the output file
     * @return : instance of this class to enable chaining
     */
    public Writer setDelimiter(String delimiter){
        this.delimiter=delimiter;
        return this;
    }

    /**
     *
     * @param fields : The fields of the CSV file, a.k.a the first row
     * @return : instance of this class to enable chaining
     */
    public Writer setFields(String[] fields){
        sb = new StringBuilder();
        noOfFields=fields.length;
        sb.append("sep="+delimiter+"\n");
        for(int i=0;i<noOfFields;i++){
            sb.append(fields[i]);
            if(i!=noOfFields-1)
                sb.append(delimiter);
        }
        sb.append('\n');
        return this;
    }

    /**
     *
     * @param data : Set of string data to be written in the row
     * @throws FieldMismatchException
     */
    public void addRow(String[] data) throws FieldMismatchException{
        if(data.length!=noOfFields)
            throw new FieldMismatchException();
        for(int i=0;i<noOfFields;i++){
            sb.append(data[i]);
            if(i!=noOfFields-1)
                sb.append(delimiter);
        }
        sb.append('\n');
    }

    /**
     * Method used to write contents to the file
     */
    public void write(){
        this.write(sb.toString());
    }
}
