/**
 * This class defines type Book, which encapsulates the data fields and methods of a book.
 * Each book has private variables number, name, checkedOut, and datePublished
 * Books are uniquely identified by their number
 * @author Anuraj Dubey, Chenghao Lin
 */
public class Book {
    private String number; //a 5-digit serial number unique to the book
    private String name;
    private boolean checkedOut;
    private Date datePublished;

    /**
     * Constructor for Book object which will always have a serial number, name, and publishing date. Books are not checked out by default.
     * @param number The unique Serial Number of the book
     * @param name   The title of the book
     * @param datePublished The publishing date of the book in MM/DD/YYYY format
     */
    public Book(String number, String name, Date datePublished){
        this.number=number;
        this.name=name;
        this.datePublished=datePublished;
        this.checkedOut=false;
    }

    //Getter methods
    /**
     * This is a getter method to get the Book's number.
     * @return the number value of the Book.
     */
    public String getSerial() {
        return number;
    }

    /**
     * This is a getter method to get the Book's name.
     * @return the name value of the Book.
     */
    public String getName() {
        return name;
    }

    /**
     * This is a getter method to get the Book's checked-out status.
     * @return the boolean value of the Book's checked-out status.
     */
    public boolean getCheckedout() {
        return checkedOut;
    }

    /**
     * This is a getter method to get the Book's published date.
     * @return the published date value of the Book.
     */
    public Date getDatePublished() {
        return datePublished;
    }

    //setter method
    /**
     * This method is a setter method to change the book's checked-out status.
     * @param checkedOut sets checkedOut value to true or false
     */
    public void setCheckedOut(boolean checkedOut) {
        this.checkedOut = checkedOut;
    }

    /**
     * This method checks if the serial numbers for 2 book objects are the same.
     * @param obj of type Book that is being compared to another book
     * @return areEqual value (which is true or false based on the .equals() method), or just false if cannot confirm equality.
     */
    @Override
    public boolean equals(Object obj){
        if(obj instanceof Book){
            Book otherBook=(Book) obj;
            boolean areEqual=otherBook.number.equals(this.number);
            return areEqual;
        } else {
            return false;
        }
    }

    /**
     * This method returns the textual representation of a Book that resides in the Library class' bag.
     * @return the formatted textual representation of a Book.
     */
    @Override
    public String toString() {
        String dateStr=datePublished.toString();
        String checkedOut;
        if (this.checkedOut==true){
            checkedOut="is checked out.";
        }
        else{
            checkedOut="is available.";
        }
        return "Book#"+number+"::"+name+"::"+datePublished.getMonth()+"/"+datePublished.getDay()+"/"+datePublished.getYear()+"::"+checkedOut;
    }
}
