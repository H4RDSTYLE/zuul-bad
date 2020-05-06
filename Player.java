import java.util.Stack;
import java.util.ArrayList;
public class Player
{
    private Stack<Room> rooms;
    private Room currentRoom;
    private ArrayList<Item> objetosRecogidos;
    private ArrayList<Item> objetosPuestos;
    private double fuerza;
    /**
     * Constructor for objects of class Player
     */
    public Player(double fuerza)
    {
        this.rooms = new Stack<Room>();
        objetosRecogidos = new ArrayList<Item>();
        objetosPuestos = new ArrayList<Item>();
        this.fuerza = fuerza;
    }

    public void setCurrentRoom(Room room){
        this.currentRoom = room;
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    public void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            rooms.push(currentRoom);
            currentRoom = nextRoom;
            look();
        }
    }

    public void setRoom(Room room){
        rooms.push(room);
        this.currentRoom = room;
    }

    public void back(){
        if(!rooms.empty()){
            currentRoom = rooms.pop();
            look();
        }
        else{
            System.out.println("You can't go back!");
        }
    }

    public void take(String itemDescription){
        ArrayList<Item> objetosDisponibles = currentRoom.getArrayObjetos();
        Boolean haEntrado = false;
        for (int i = 0; i < objetosDisponibles.size() && haEntrado == false; i++){
            Item item = objetosDisponibles.get(i);
            if(item.getItemDescription().equals(itemDescription)){
                haEntrado = true;
                if(item.getPeso()<fuerza){
                    fuerza -= item.getPeso();
                    objetosRecogidos.add(item);
                    currentRoom.removeItem(item);
                    System.out.println("You have took the item.");
                }
                else{
                    System.out.println("You aren´t strong enought, try to drop an object.");
                }
            }
        }
    }

    public void drop(String itemDescription){
        for (int i = 0; i < this.objetosRecogidos.size(); i++){
            Item item = this.objetosRecogidos.get(i);
            if(item.getItemDescription().equals(itemDescription) && item.sePuedeCoger()){
                fuerza += item.getPeso();
                currentRoom.addItem(item);
                objetosRecogidos.remove(item);
                System.out.println("Object dropped!");
            }
        }
    }

    public void items(){
        int pesoTotal = 0;
        if(!objetosRecogidos.isEmpty()){
            for(Item item : objetosRecogidos){
                System.out.println(item.getDescripcion());
                pesoTotal += item.getPeso();
            }
            System.out.println("Peso total: " + pesoTotal + "\n" + "Fuerza disponible: " + fuerza);
        }
        else{
            System.out.println("You don't have objects!");
        }
    }

    public void put(String descripcion){
        for(int i = 0; i < objetosRecogidos.size(); i++){
            if(objetosRecogidos.get(i).getItemDescription().equals(descripcion)){
                objetosPuestos.add(objetosRecogidos.get(i));
                if(objetosRecogidos.get(i).multiplicadorMasUno()){
                    fuerza = fuerza * objetosRecogidos.get(i).getMultiplicador();
                    System.out.println("Tu fuerza se ha multiplicado por un: " + objetosRecogidos.get(i).getMultiplicador());
                }
                objetosRecogidos.remove(objetosRecogidos.get(i));
            }
        }
    }

    public void remove(String descripcion){
        for(int i = 0; i < objetosPuestos.size(); i++){
            if(objetosRecogidos.get(i).getItemDescription().equals(descripcion)){
                objetosRecogidos.add(objetosRecogidos.get(i));
                fuerza = fuerza/objetosRecogidos.get(i).getMultiplicador();
                objetosPuestos.remove(objetosRecogidos.get(i));
                System.out.println("Tu fuerza ha disminuido.");
            }
        }
    }

    public void eat() {
        System.out.println("You have eaten now and you are not hungry any more!");
    }

    public void look() {
        System.out.println(currentRoom.getLongDescription());
    }
}
