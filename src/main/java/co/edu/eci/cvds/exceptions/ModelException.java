package co.edu.eci.cvds.exceptions;

public class ModelException extends Exception{

    public final static String ITEM_INVALID_VALUE = "The value given to the item is invalid";
    public final static String ITEM_INVALID_CURRENCY = "The value given to the currency is invalid";
    public final static String ITEM_INVALID_DISCOUNT = "The value given to the discount is invalid";
    public final static String ITEM_INVALID_TAX = "The value given to the tax is invalid";

    public ModelException(String message){
        super(message);
    }
}
