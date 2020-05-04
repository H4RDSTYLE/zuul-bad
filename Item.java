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
    private String idObjeto;
    /**
     * Constructor for objects of class Item
     */
    public Item(String descripcion, int peso, String idObjeto)
    {
        this.itemWeight = peso;
        this.itemDescription = descripcion;
        this.idObjeto = idObjeto;
    }

    public String getId(){
        return idObjeto;
    }

    public int getPeso(){
        return itemWeight;
    }

    public String getDescripcion(){
        String aDevolver = "";
        if(itemDescription != null){
            aDevolver = "Objeto: " + itemDescription + "\n"+ "Peso objeto: " + itemWeight + "\n" + "Id: " + idObjeto + "\n";
        }
        return aDevolver;
    }
}