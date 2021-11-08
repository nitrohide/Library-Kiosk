import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * The kiosk class processes command lines from the console using Scanner and String Tokenizer class.
 * The user can interact with the Library bag from the Kiosk class
 * @author Anuraj Dubey, Chenghao Lin
*/
public class Kiosk {
    final int STARTING_SERIAL = 10001; //The first serial number available
    final int ADD_ARGS = 3;            //The required number of arguments to add a book
    final int MOD_ARGS = 2;            //The required number of arguments to remove,checkout,return a book
    final int DISPLAY_ARGS = 1;        //The required number of arguments to print the library, or quit

    /**
     * Initiates interface and displays status messages as inputs are scanned
     * Session runs indefinitely until command Q is inputted
     */
    public void run() {

        //setting up variables, creating Library object to be used in this session
        Library Library = new Library();
        System.out.println("Library Kiosk running");
        boolean kioskStatus = true;
        int serialNumber = STARTING_SERIAL;
        Scanner scanner = new Scanner(System.in);

        while (kioskStatus && scanner.hasNext()){ //will keep running until command Q is inputted
            String nextLine = scanner.nextLine();
            if (nextLine.equals("")){                     //To prevent String Tokenizer class error
                nextLine=" ";
            }
            StringTokenizer tokens = new StringTokenizer(nextLine,",");

            int numArgs = tokens.countTokens();
            String command = tokens.nextToken();          //the command flag, first parameter

            switch(command){
                case "Q":                                 //Q command terminates the program
                    if (numArgs == DISPLAY_ARGS) {        //compare number of inputted arguments with required arguments
                        kioskStatus = false;
                        break;
                    }
                    else{
                        System.out.println("Invalid Command!");
                    }

                case "A":                     //A command adds another book into the library
                    if (numArgs == ADD_ARGS){
                        String number = String.valueOf(serialNumber);
                        String name = tokens.nextToken();
                        String datePublished = tokens.nextToken();
                        Date date = new Date(datePublished);
                        if (date.isValid()) {
                            Book newBook = new Book(number, name, date );
                            Library.add(newBook);
                            System.out.println(name + " added to the Library");
                            serialNumber++;
                        }
                        else{
                            System.out.println("Invalid Date!");
                        }
                    }
                    else{
                        System.out.println("Invalid command!");
                    }
                    break;

                case "R":                     //R command removes a book from the library
                    if (numArgs == MOD_ARGS){
                        String numberStr = tokens.nextToken();
                        int bookNumber = Integer.parseInt(numberStr);
                        Book Book = Library.search(bookNumber);
                        if (Book == null){
                            System.out.println("Unable to remove, the library does not have this book.");
                        }
                        else{
                            Library.remove(Book);
                            System.out.println("Book# " + numberStr + " removed.");
                        }
                    }
                    else{
                        System.out.println("Invalid command!");
                    }
                    break;

                case "O":                      //O command checks out a book from the Library
                    if (numArgs == MOD_ARGS) {
                        String numberStr = tokens.nextToken();
                        int bookNumber = Integer.parseInt(numberStr);
                        Book Book = Library.search(bookNumber);
                        if (Book == null || Book.getCheckedout()){
                            System.out.println("Book#" + numberStr + " is not available.");
                        }
                        else{
                            //Library.remove(Book); //only checkout? or remove as well
                            Library.checkOut(Book);
                            System.out.println("You’ve checked out Book#" + numberStr + " Enjoy!.");
                        }
                    }
                    else{
                        System.out.println("Invalid command!");
                    }
                    break;

                case "I":                     //I command returns a book into the Library
                    if (numArgs == MOD_ARGS){
                        String numberStr = tokens.nextToken();
                        int bookNumber = Integer.parseInt(numberStr);
                        Book Book = Library.search(bookNumber);
                        if (Book == null || !Book.getCheckedout()){
                            System.out.println("Unable to return Book#"+numberStr+".");
                        }
                        else{
                            //Library.remove(Book); //only checkout? or remove as well
                            Library.returns(Book);
                            System.out.println("Book#" + numberStr + " return has completed. Thanks!");
                        }
                    }
                    else{
                        System.out.println("Invalid command!");
                    }
                    break;

                case "PA":         //outputs the list of books to the console with the current sequence
                    if (numArgs == DISPLAY_ARGS){
                        if(Library.getNumBooks() == 0){
                            System.out.println("Library catalog is empty!");
                        }
                        else{
                            System.out.println("**List of books in the library.");
                            Library.print();
                            System.out.println("**End of list");
                        }
                    }
                    else{
                        System.out.println("Invalid command!");
                    }
                    break;

                case "PD":         //output the list of books by the dates published in ascending order
                    if (numArgs == DISPLAY_ARGS){
                        if(Library.getNumBooks() == 0){
                            System.out.println("Library catalog is empty!");
                        }
                        else{
                            System.out.println("**List of books by the dates published.");
                            Library.printByDate();
                            System.out.println("**End of list");
                        }
                    }
                    else{
                        System.out.println("Invalid command!");
                    }
                    break;

                case "PN":        //output the list of books by the book numbers in ascending order
                    if (numArgs == DISPLAY_ARGS){
                        if(Library.getNumBooks() == 0){
                            System.out.println("Library catalog is empty!");
                        }
                        else{
                            System.out.println("**List of books by the book numbers.");
                            Library.printByNumber();
                            System.out.println("**End of list");
                        }
                    }
                    else{
                        System.out.println("Invalid command!");
                    }
                    break;

                case " ":         //passes the empty input values
                    break;

                default:          //any other command that is not a case passes through here
                    System.out.println("Invalid command!");
            }
        }
        System.out.println("Kiosk session ended.");
    }
}
