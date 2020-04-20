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
    private String description;
    private HashMap<String, Room> direcciones;
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
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
        if(direction.equals("north")) {
            nextRoom = direcciones.get(direction);
        }
        if(direction.equals("east")) {
            nextRoom = direcciones.get(direction);
        }
        if(direction.equals("south")) {
            nextRoom = direcciones.get(direction);
        }
        if(direction.equals("west")) {
            nextRoom = direcciones.get(direction);
        }
        if(direction.equals("southwest"))
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
        String aDevolver = "";
        if(direcciones.containsKey("north")) {
            aDevolver += "north ";
        }
        if(direcciones.containsKey("south")) {
            aDevolver += "south ";
        }
        if(direcciones.containsKey("east")) {
            aDevolver += "east ";
        }
        if(direcciones.containsKey("west")) {
            aDevolver += "west ";
        }
        if(direcciones.containsKey("southwest")){
            aDevolver += "southwest ";
        }
        return aDevolver;
    }
    /**
     * Define una salida para esta sala
     * 
     * @param direccion La direccion de la salida (por ejemplo "north" o "southEast")
     * @param sala La sala que se encuentra en la direccion indicada
     */
    public void setExit(String direccion, Room sala){
        if(direccion.equals("north")) {
            direcciones.put(direccion, sala);
        }
        if(direccion.equals("south")) {
            direcciones.put(direccion, sala);
        }
        if(direccion.equals("east")) {
            direcciones.put(direccion, sala);
        }
        if(direccion.equals("west")) {
            direcciones.put(direccion, sala);
        }
        if (direccion.equals("southwest")){
            direcciones.put(direccion, sala);
        }
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }

}
