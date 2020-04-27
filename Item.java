/**
 * Write a description of class Item here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Item
{
    // instance variables - replace the example below with your own
    private int itemWeight;
    private String itemDescription;

    /**
     * Constructor for objects of class Item
     */
    public Item(String descripcion, int peso)
    {
        this.itemWeight = peso;
        this.itemDescription = descripcion;
    }

    public String getDescripcion(){
        String aDevolver = "";
        if(itemDescription != null){
            aDevolver = "Objeto: " + itemDescription + "\n"+ "Peso objeto: " + itemWeight;
        }
        return aDevolver;
    }
}