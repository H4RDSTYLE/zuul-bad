import java.util.Stack;
/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Room backRoom;
    private Player jugador;
    /**
     * Create the game and initialise its internal map.
     */
    public Game(int fuerzaJugador) 
    {
        parser = new Parser();
        jugador = new Player(fuerzaJugador);
        createRooms();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room plaza, fronton, parque, vecindario, bar, casas, iglesia;
        Item espada, pocion, caramelo, armadura, anillo, pelota;
        // create the objects
        espada = new Item("espada", 1500, true);
        pocion = new Item("pocion", 500, true);
        caramelo = new Item("caramelo", 50, true);
        armadura = new Item("armadura", 1000, true);
        anillo = new Item("anillo", 5, true);
        pelota = new Item("pelota", 400, true);

        // create the rooms
        plaza = new Room("en la plaza del pueblo.");
        bar = new Room("en el reino de la cerveza (bar).");
        parque = new Room("en el parque.");
        vecindario = new Room("en el vecindario.");
        fronton = new Room("en tierras de pelota vasca.");
        casas = new Room("en la zona residencial del vecindario.");
        iglesia = new Room("en la iglesia del vecindario.");
        // initialise room exits
        plaza.setExit("north", vecindario);
        plaza.setExit("south", bar);
        plaza.setExit("east", fronton);
        plaza.setExit("west", parque);
        plaza.setExit("northwest", casas);
        fronton.setExit("west", plaza);
        fronton.setExit("ruta127", bar);
        vecindario.setExit("south", plaza);
        vecindario.setExit("east", iglesia);
        vecindario.setExit("west", casas);
        bar.setExit("north", plaza);
        bar.setExit("ruta127", fronton);
        bar.setExit("ruta2", parque);
        parque.setExit("east", plaza);
        parque.setExit("ruta2", bar);
        casas.setExit("east", vecindario);
        casas.setExit("southeast", plaza);
        iglesia.setExit("west", vecindario);
        // initialise room objects
        bar.addItem(pocion);
        bar.addItem(armadura);
        fronton.addItem(pelota);
        iglesia.addItem(espada);
        casas.addItem(anillo);
        parque.addItem(caramelo);

        jugador.setCurrentRoom(plaza);  // start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        jugador.look();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            jugador.goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        else if (commandWord.equals("look")) {
            jugador.look();
        }
        else if(commandWord.equals("eat")){
            jugador.eat();
        }
        else if(commandWord.equals("back")){
            jugador.back();
        }
        else if(commandWord.equals("items")){
            jugador.items();
        }
        else if(commandWord.equals("drop")){
            if(command.hasSecondWord()){
                jugador.drop(command.getSecondWord().toString());
            }
            else{
                System.out.println("Oops what object do you want to drop?");
            }
        }
        else if(commandWord.equals("take")){
            if(command.hasSecondWord()){
                jugador.take(command.getSecondWord().toString());
            }
            else{
                System.out.println("Oops what object do you want to take?");
            }
        }

        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}
