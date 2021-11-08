/**
 * The Library class is responsible for creating and maintaining a "bag", or array, of type Book. 
 * It holds multiple methods that revolve around maintaining and manipulating this bag in order to serve as an effective library.
 * @author Anuraj Dubey, Chenghao Lin 
 */
public class Library {
    private Book[] books; // array-based implementation of the bag data structure private int numBooks;
    private int numBooks; // the number of books currently in the bag

    /**
     * This is a contructor to create an initial empty bag of type Book. 
     */
    public Library() { //default constructor to create an empty bag
        books = new Book[4];
    }

    public static final int BAG_GROW_VALUE = 4;
    public static final int NOT_FOUND = -1;

    /**
     * This is a method to find a certain book in the bag, given and input of type Book. 
     * @param book that is trying to be found
     * @return i, the index of the book being searched for.  
     */
    private int find(Book book) { // helper method to find a book in the bag
        for (int i = 0; i < books.length; i++){
            if (book == books[i]){
                return i;
            }
        }
        return NOT_FOUND; //book not found
    }

    /**
     * This is a method to grow the bag by a given value of 4, anytime the bag gets overfilled by books. 
     * It creates a new bag and copies the old bag into this new one. 
     */
    private void grow() {  // helper method to grow the capacity by 4
        Book[] temp = new Book[books.length + BAG_GROW_VALUE]; //temp to hold all books before copying into new bag

        for (int i = 0; i < numBooks; i++){
            temp[i] = books[i];
        }
        books=temp;
    }

    /**
     * This method adds a given inputted book at the end of the bag.
     * @param book being added into the bag
     */
    public void add(Book book) { //adds book to end of array. If full, calls grow()
        if (books[books.length-1]!= null){
            grow();
        }
        for (int i = 0; i < books.length; i++ ){
            if (books[i] == null){
                books[i] = book;
                numBooks++;
                break;
            }
        }
    }

    /**
     * This method removes a given book from the bag by setting that respective index to null.
     * Additionally, it shifts books further in the bag to the left following the removal. 
     * This readjustment is to make sure there is no empty spot in the middle of the bag. 
     * @param book being removed from the bag
     * @return true if the removal was successful, or false if the removal was unsuccessful. 
     */
    public boolean remove(Book book) { //also shifts and readjusts all interior elements after removal
        int index_to_remove = find(book);

        for ( int j = index_to_remove; j < books.length ; j++ ){//traverse through rest of bag and shift to left
            if ( j == (books.length - 1) ){ //check if traversal reaches last index of bag
                books[j] = null; //if last index of bag, set to null in order to accomodate for removed book
                numBooks--;
                return true;
            }
            if ( books[j+1] == null){ //make last element in list null to accomodate for removed book
                books[j] = null;
                numBooks--;
                return true; //also displays 'Book# xyz removed.'
            }
            books[j] = books[j + 1]; //set each element to value of next to preserve order of books
        }
        return false; //also displays "Unable to remove, the library does not have this book."
    }

    /**
     * This method checks out a book by setting the book's "checkedOut" value to true.
     * @param book being checked out
     * @return true if check out was successful, or false if it was unsuccessful. 
     */
    public boolean checkOut(Book book) {
        int index_to_find = find(book);

        if (index_to_find == NOT_FOUND) //if book is not owned return false
            return false;
        if (!books[index_to_find].getCheckedout()){ //if not checked out, set checkedOut to true
            books[index_to_find].setCheckedOut(true);
            return true; // return true if succesfully checked out
        }
        else if (books[index_to_find].getCheckedout()){ //if already checked out, return false
            return false; // return false if already checked out
        }
        return false; //return false if checkout failed for any other reason
    }

    /**
     * This method returns a checked out book by setting its checkedOut() value to false. 
     * @param book being returned
     * @return true if return was successful, or false if return was unsuccessful. 
     */
    public boolean returns(Book book) {
        int index_to_find = find(book);

        if (index_to_find == NOT_FOUND) //if book is not owned, return false
            return false;
        if (books[index_to_find].getCheckedout()){ //if it is indeed checked out, check in and return true
            books[index_to_find].setCheckedOut(false);
            return true; // return true if succesfully returned
        }
        else if (!books[index_to_find].getCheckedout()){ //if book is not checked out, return false
            return false; // return false if not checked out
        }
        return false; //return false if book return failed for any other reason
    }

    /**
     * This method prints out all books in the bag in a sequence. 
     */
    public void print() {  //print the list of books in the bag
        for(int i = 0; i < books.length; i++){
            if (books[i]!= null) {
                System.out.println(books[i].toString());
            }
        }
    }

    /**
     * This method prints out all books in the bag, by ascending order of the published dates.
     * It uses a selection sort algorithm to sort all the books in the bag. 
     */
    public void printByDate() { //print the list of books by datePublished (ascending)
        //Using a simple selection sort algorithm to sort books by date in ascending order
        for (int i = 0; i < books.length-1; i++) {   //keep track of sorted part of bag
            int min_idx = i;
            for (int j = i + 1; j < books.length; j++) {
                if ( books[j] == null
                        || books[min_idx] == null) //if space in bag is empty, ignore and continue
                    continue;
                if (books[j].getDatePublished().getYear()
                        < books[min_idx].getDatePublished().getYear()){ //if year of index j is less than that of min_idx
                    min_idx = j;
                }
                if (books[j].getDatePublished().getYear()
                        == books[min_idx].getDatePublished().getYear()){ //if year of index j is equal to that of min_idx, check month
                    if ( books[j].getDatePublished().getMonth()
                            < books[min_idx].getDatePublished().getMonth()){ //continue to compare month values
                        min_idx = j;
                    }
                    if ( books[j].getDatePublished().getMonth()
                            == books[min_idx].getDatePublished().getMonth()){ //if months are equal, compare day
                        if ( books[j].getDatePublished().getDay()
                                < books[min_idx].getDatePublished().getDay()){ //compare day values
                            min_idx = j;
                        }
                    }
                }
            }
            //swapping the minimum element into the sorted region to complete iteration
            Book temp = books[min_idx];
            books[min_idx] = books[i];
            books[i] = temp;
        }
        print(); //print the sorted bag
    }

    /**
     * This method prints out all books in the bag, by ascending order of the serial numbers.
     * It uses a selection sort algorithm to sort all the books in the bag. 
     */
    public void printByNumber() { //print the list of books by number (ascending) }
        //Using a simple selection sort algorithm to sort books by serial number in ascending order
        for (int i = 0; i < books.length - 1; i++) //keep track of sorted part of bag
        {
            int min_idx = i;
            for (int j = i + 1; j < books.length; j++) {
                if (books[j] == null || books[min_idx] == null) //if space in bag is empty, ignore and continue
                    continue;
                if (Integer.parseInt(books[j].getSerial()) < Integer.parseInt(books[min_idx].getSerial())) //find smallest SN in unsorted region of bag
                    min_idx = j;
            }
            //swapping the minimum element into the sorted region to complete iteration
            Book temp = books[i];
            books[i] = books[min_idx];
            books[min_idx] = temp;
        }
        print(); //print the sorted bag
    }

    /**
     * This method is used to search for a book in the bag with an inputted serial/book number for convenience if needed. 
     * @param number (serial number) of the book
     * @return this returns the book object being searched for, or returns null if not found.
     */
    public Book search(int number){
        for (int i = 0;i < numBooks; i++){
            if (Integer.parseInt(books[i].getSerial()) == number){
                return books[i];
            }
        }
        return null;
    }

    /**
     * This method returns the number of books in the bag if needed. 
     * @return returns the number of books. 
     */
    public int getNumBooks(){
        return numBooks;
    }
}
