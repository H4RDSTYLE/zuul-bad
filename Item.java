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
    private Boolean sePuedeCoger;
    /**
     * Constructor for objects of class Item
     */
    public Item(String descripcion, int peso, boolean sePuedeCoger)
    {
        this.itemWeight = peso;
        this.itemDescription = descripcion;
        this.sePuedeCoger = sePuedeCoger;
    }
    
    public String getItemDescription(){
        return itemDescription;
    }
    
    public int getPeso(){
        return itemWeight;
    }
    
    public boolean sePuedeCoger(){
        return sePuedeCoger;
    }

    public String getDescripcion(){
        String aDevolver = "";
        if(itemDescription != null){
            aDevolver = "Objeto: " + itemDescription + "\n"+ "Peso objeto: " + itemWeight;
        }
        return aDevolver;
    }
}