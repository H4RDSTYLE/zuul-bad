import java.util.HashMap;
/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael KÃ¶lling and David J. Barnes
 * @version 2011.07.31                                                                      
 */
public class Room 
{
    private String description, itemDescription, itemWeight;
    private HashMap<String, Room> direcciones;
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description, String itemDescription, String itemWeight) 
    {
        this.description = description;
        this.itemDescription = itemDescription;
        this.itemWeight = itemWeight;
        direcciones = new HashMap<String, Room>();
    }

    /**
     * Devuelve la sala vecina a la actual que esta ubicada en la direccion indicada como parametro.
     *
     * @param salida Un String indicando la direccion por la que saldriamos de la sala actual
     * @return La sala ubicada en la direccion especificada o null si no hay ninguna salida en esa direccion
     */
    public Room getExit(String direction){
        Room nextRoom = null;
        nextRoom = direcciones.get(direction);
        return nextRoom;
    }

    /**
     * Devuelve la información de las salidas existentes
     * Por ejemplo: "Exits: north east west" o "Exits: south" 
     * o "Exits: " si no hay salidas disponibles
     *
     * @return Una descripción de las salidas existentes.
     */
    public String getExitString(){
        String aDevolver = direcciones.keySet().toString().substring(1, direcciones.keySet().toString().length()-1).replaceAll(",", "");
        return aDevolver;
    }

    /**
     * Devuelve un texto con la descripcion completa de la habitacion, que 
     * incluye la descripcion corta de la sala y las salidas de la misma. Por ejemplo:
     *     You are in the lab
     *     Exits: north west southwest
     * @return Una descripcion completa de la habitacion incluyendo sus salidas
     */
    public String getLongDescription(){
        String aDevolver = "Estas " + getDescription() + "\n" + "Exits: " + getExitString() + "\n" + getObjetoSala();
        return aDevolver;
    }

    /**
     * Define una salida para esta sala
     * 
     * @param direccion La direccion de la salida (por ejemplo "north" o "southEast")
     * @param sala La sala que se encuentra en la direccion indicada
     */
    public void setExit(String direccion, Room sala){
        direcciones.put(direccion, sala);
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }

    public String getObjetoSala(){
        String aDevolver = "";
        if(itemDescription != null){
            aDevolver = "En esta sala hay: \n" + "Objeto: " + itemDescription + "\n"+ "Peso objeto: " + itemWeight;
        }
        return aDevolver;
    }
}
