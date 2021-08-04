/**
 * Station.java SUBMISSION 1
 * Name: Sabreen Mostafa
 * ID: 110968588
 * Programming Assignment 2
 * CSE214
 * R10
 * TA Name: Daniel Calabria
 */
import java.util.*;
import java.lang.String;

/**
 * This is a station class with track methods defined for the track nodes and below this
 * is the drivers program that will connect all three classes together
 * the station itself conceptually should hold a linked list of a track, having track nodes,
 * with a track head tail and cursor but in each track objec there is a linked list of trains
 * so train objects and the train head, tail, cursor. So essentially the station holds a linked list
 * within a linked list.
 */
public class Station {
    private Track head;
    private Track tail;
    private Track cursor;
    int numberoftracks;

    /**
     *Station constructor used to set the head, tail, and cursor of track to null
     */
    public Station() {
        head = null;
        tail = null;
        cursor = null;
    }
    /**
     * This is the Track head object so this return the head and its info
     * this is a getter
     * @return head
     */
    public Track getHead() {
        return head;
    }

    /**
     *this is the Track head object so this sets to the head
     * this is a setter
     * @param head
     */
    public void setHead(Track head) {
        this.head = head;
    }

    /**
     *This is the Track tail object so this return the tail of the track linked list and its info
     * this is a getter
     * @return tail
     */

    public Track getTail() {
        return tail;
    }

    /**
     *this is the Track tail object so this sets to the track
     *this is a setter
     * @param tail
     */

    public void setTail(Track tail) {
        this.tail = tail;
    }

    /**
     *This is the track cursor object so this will return the cursor of track linked list
     * this is a getter
     * @return
     */
    public Track getCursor() {

        return cursor;
    }

    /**
     * This is the track cursor object so this set the cursor
     *This is the setter
     * @param cursor
     */
    public void setCursor(Track cursor ) {

        this.cursor = cursor;
    }

    /**
     *This is a public new track method
     * this is used to add a track into a linked list
     * due to time constraints i was only able to do the scenario where
     * the node is added after the cursor so it will not sort based
     * i learned about how to impelment this method from TA recitation
     * I checked three condiitons
     * 1. new track null just means the object was never created so its null so this throws null exception
     * 2. when list is empty assign new track as head track and cursor since only one node
     * 3.if there is a node but if next to the node there is no other node so null then
     * we set the cursor to the next track which is next to the cursor
     * 4.this is a scenario of inserting the node in between
     * 5. incrementing the number of track in the list.
     * @param newTrack
     * @throws TrainAlreadyExists
     * @throws NullPointerException
     */
    //done
    public void addTrack(Track newTrack) throws NullPointerException, InvalidTrack {
        if (newTrack.getTrackNumber() > 0) {
            if (newTrack == null) {
                throw new NullPointerException();
            }


            if (head == null) {
                cursor = newTrack;
                head = newTrack;
                tail = newTrack;
                return;
            }
            if (cursor.getNext() == null) {
                cursor.setNext(newTrack);
                newTrack.setPrev(cursor);
                cursor = newTrack;
                tail = cursor;
                return;
            }
            cursor.getNext().setPrev(newTrack);
            newTrack.setNext(cursor.getNext());
            newTrack.setPrev(cursor);
            cursor.setNext(newTrack);
            cursor = newTrack;
            numberoftracks++;
        }
    }

    /**
     *  This is the basic method of removing a selected track
     *  I learned how to implement this method from TA recitation
     *  this looks at different scenarios
     *  1.if the cursor simply doesnt exist then throw exception
     *  2.if next to the cursor is null and previous is null
     *  then cursor itself is just null
     *  3.if only get prev to the train cursor is null
     *  then one must get next to the cursor and set it to prev being null
     *  4.if only get next to the train cursor is null
     *  then one must get prev to the cursor and set it to next being null
     *  5. this is removal in between of the nodes when getnext and getprev arent null
     *  decrement the number of track after this
     * @return the current cursor after the removal
     * @throws NullPointerException
     */
    //done
    public Track removeSelectedTrack() throws NullPointerException {
        if (cursor == null) {
            throw new NullPointerException();
        } else if (cursor.getNext() == null && cursor.getPrev() == null) {
            cursor = null;
        } else if (cursor.getPrev() == null) {
            cursor.getNext().setPrev(null);
            cursor = cursor.getNext();
        } else if (cursor.getNext() == null) {
            cursor.getPrev().setNext(null);
            cursor = cursor.getPrev();
        } else {
            cursor.getNext().setPrev(cursor.getPrev());
            cursor.getPrev().setNext(cursor.getNext());
            cursor = cursor.getNext();
        }
        numberoftracks--; // does this need to be added?
        System.out.println("Sucessfully removed the cursor ");
        return cursor;
    }

    /**
     *This is used to print a selected cursor track from the station
     * all that is done is assignin cursor to string
     * @throws NullPointerException if no cursor
     */
    // need to figure this oout
    public void printSelectedTrack() { //tps
        try {
            if (cursor != null) {
                String Selected = "Selected";
                String Train_Num = "Train Number:";
                String Track_Num = "Track Number";
                String U="% Utilization Rate";
                String Train_dest = "Train Destination";
                String Arrival_time = "Arrival Time";
                String Departure = "Departure Time";
                String s10 = "";
                s10 += String.format("%-21s%-21s%.2f%-20s",Track_Num,cursor.getTrackNumber(), cursor.getUtilizationRate(),U);
                s10 += "\n";
                s10 += String.format("%-21s%-26s%-26s%-20s", Train_Num, Train_dest, Arrival_time, Departure);
                s10 += "\n";
                s10 += String.format("----------------------------------------------------------------------------------------------");
                System.out.println(s10 + cursor.toString());
            }
        } catch (NullPointerException ie) {
            ie.printStackTrace();
        }
    }

    /**
     *This is used to print all the tracks with their
     * trains in the station over here i just print a toString()
     */
    //need to figure this out
    public void printAllTracks() { //tpa
        System.out.print(toString());
    }

    /**
     *This is a boolean method to select the track from the station to switch too
     * a track cursor is made and this cursor is set at head intially there
     * three scenarios
     * 1. if cursor is null there is nothing so dont bother
     * 2. if cursor next is not null there is more nodes so u go to the next one
     * by using the while loop and at leach loop a condition is set to see
     * if the cursors track number is equal to the tracktoselect user input
     * if it is set the cursor to it and return true
     * 3. is if the track we are looking for is at the end and if is use
     * setCursor to select it
     * @param tracktoSelect
     * @return track selected so now updated track cursor if true
     * @throws InvalidTrack
     */
    public boolean selectTrack(int tracktoSelect) throws InvalidTrack {
        // cursor=head;
        Track cursor3;
        cursor3 = head;

        if (cursor3 == null) {
            return false;
        }

        while (cursor3.getNext() != null) {
            if (cursor3.getTrackNumber() == tracktoSelect) {
                System.out.println("Switched to track" + tracktoSelect);
                setCursor(cursor3);
                return true;
            } else {
                cursor3 = cursor3.getNext();
            }
        }

        if (cursor3.getNext() == null) {
            if (tracktoSelect == cursor3.getTrackNumber()) {
                System.out.println("Switched to track" + tracktoSelect);
                setCursor(cursor3);
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     *This is a listlength method which uses a dummy node sets it to head
     * and uses a while loop to go through the list and count the number the
     * nodes in the track linked list and returns it
     * @return answer
     */
    public int listLength() {
        Track dummynode = head;
        int answer = 0;
        while (dummynode != null) {
            answer++;
            dummynode = dummynode.getNext();
        }
        return answer;
    }

    //need to figure this out

    /**
     *String to String is very important for TPS AND TPA as it
     * will show the string format of the linked list for the  tracks
     * based on how its coded
     * there are three scenerios presented here
     * 1.if cursor is just null return a blank string
     * 2.if cursors next node is not null then the while is used to loop
     * through all the nodes and print out each node and its info
     * 3. is when cursor is null and it prints the last nodes info
     * After this for some reason it moves the current cursor to the next
     * one which would make it null for other methods so i had to add
     * a setCursor(cursor2) so that it stayed at its current cursor position
     * still dont know why it wouldnt work without it
     * @return
     */
    public String toString() {
        Track cursor2;
        cursor2 = head;
        String s10 = "";

        if (cursor2 == null) {
            return s10;
        }

        while (cursor2.getNext() != null) {
            String Selected = "Selected";
            String Train_Num = "Train Number:";
            String Track_Num = "Track Number";
            String U="% Utilization Rate";
            String Train_dest = "Train Destination";
            String Arrival_time = "Arrival Time";
            String Departure = "Departure Time";

            s10 += String.format("%-21s%-21s%.2f%-20s",Track_Num,cursor2.getTrackNumber(), cursor2.getUtilizationRate(),U);
            s10 += "\n";
            s10 += String.format("%-21s%-26s%-26s%-20s", Train_Num, Train_dest, Arrival_time, Departure);
            s10 += "\n";
            s10 += String.format("----------------------------------------------------------------------------------------------");
            s10 += cursor2.toString();
            s10 += "\n";
            cursor2 = cursor2.getNext();
        }

        if (cursor2.getNext() == null) {

            String Selected = "Selected";
            String Train_Num = "Train Number:";
            String Track_Num = "Track Number";
            String U="% Utilization Rate";
            String Train_dest = "Train Destination";
            String Arrival_time = "Arrival Time";
            String Departure = "Departure Time";

            s10 += String.format("%-21s%-21s%.2f%-20s",Track_Num,cursor2.getTrackNumber(), cursor2.getUtilizationRate(),U);
            s10 += "\n";
            s10 += String.format("%-21s%-26s%-26s%-20s", Train_Num, Train_dest, Arrival_time, Departure);
            s10 += "\n";
            s10 += String.format("----------------------------------------------------------------------------------------------");
            s10 += cursor2.toString();
            s10 += "\n";
        }
        setCursor(cursor2);

        return s10;
    }

    /**
     *This is the driver class that will print out the menu of the train options
     * three objects are declared here the Track, the train, and the station
     * a while loop with true in the parameters is used to do a infinite loop so it
     * doesnt simply exit
     * user is given a option to pick from the menu
     * convert the operation to upper case so that user input can be lower case
     * and upper case
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        Track tr = new Track();
        Station st = new Station();
        Train t = new Train();


        Scanner input = new Scanner(System.in);

        System.out.println("|-----------------------------------------------------------------------|");
        System.out.println("| Train Options                  | Track Options                        |");
        System.out.println("|    A. Add New Train            |    TA. Add Track                     |");
        System.out.println("|    N. Select next Train        |    TR. Remove selected Track         |");
        System.out.println("|    V. Select Previous Train    |    TS. Switch Track                  |");
        System.out.println("|    R. Remove selected Train    |   TPS. Print selected Track          |");
        System.out.println("|    P. Print selected Train     |   TPA. Print all Tracks              |");
        System.out.println("|-----------------------------------------------------------------------|");
        System.out.println("| Station Options                                                       |");
        System.out.println("|   SI. Print Station Information                                       |");
        System.out.println("|    Q. Quit                                                            |");
        System.out.println("|-----------------------------------------------------------------------|");

        while (true) {
            System.out.println("Choose an operation:");
            String selection = input.next();
            String operation = selection.toUpperCase();
            switch (operation) {
                /**
                 *switch is used to switch between each case based on the operation
                 * requested by the user, when operation is done it will break and go to
                 * the beginning
                 * so case A is used when adding a train into a train object and then
                 * adding that train to the track cursor of the station
                 * train time validity is checked using parse and substring and
                 * string format is used to convert the arrival time to a proper
                 * time format
                 * @throws TrainAlreadyExists
                 * @throws InvalidTime
                 * @throws NullPointerException
                 * @throws IllegalArgumentException
                 */
                case "A": //done
                    System.out.print("Enter train number:");
                    int trainNumber = input.nextInt();
                    System.out.print("Enter train destination:");
                    String d = input.nextLine();
                    String destination = input.nextLine();
                    System.out.print("Enter train arrival time:");
                    int arrivalTime = input.nextInt();
                    System.out.print("Enter train transfer time:");
                    int transferTime = input.nextInt();
                    String a=String.format("%04d", arrivalTime);
                    String hh = a.substring(0, 2); //HH MM   00 00   0 0
                    String mm = a.substring(2);
                    int HH = Integer.parseInt(hh);
                    int MM = Integer.parseInt(mm);
                    t = new Train(trainNumber, destination, arrivalTime, transferTime);

                    try {
                        if (st.cursor != null) { //how to do this //add a throw
                            if (HH >= 0 && HH < 24) {
                                if (MM >= 0 && MM < 60) {
                                    //tr.addTrain(t); // this adds to the track
                                    st.getCursor().addTrain(t); //to current track
                                    System.out.println("Train No. " + trainNumber + " to " + destination + " added to Track:" + st.cursor.getTrackNumber());
                                }
                            } else {
                                throw new InvalidTime();
                            }
                        } else {
                            System.out.println("Cant add train because there are no track");
                        }
                    } catch (TrainAlreadyExists te) {
                        te.printStackTrace();
                    } catch (InvalidTime ie) {
                        ie.printStackTrace();
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }
                    catch(IllegalArgumentException ie){
                        ie.printStackTrace();
                    }
                    break;
                /**
                 * Case N is used to select next train from the cursor track in the station
                 * @throws NullPointerException if cursor.getNext()==null for train
                 */
                case "N": //done
                    try {
                        st.cursor.selectNexttrain();
                        System.out.println("Cursor has been moved to next train");
                    }
                    catch(NullPointerException ie){
                        ie.printStackTrace();
                    }
                    break;
                /**
                 * Case V is used to select next train from the cursor track in the station
                 * @throws NullPointerException if cursor.getPrev()==null for train
                 */
                case "V": //done
                    try {
                        st.cursor.selectPrevTrain();
                        System.out.println("Cursor has been moved to previous train");
                    }
                    catch(NullPointerException ie){
                        ie.printStackTrace();
                    }
                    break;
                /**
                 *Case R is used to remove the cursor train at the cursor track in the
                 * station
                 * @throws NullPointerException if cursor is null
                 */
                case "R": //done
                    try {
                        st.cursor.removeSelectedTrain();
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }
                    break;
                /**
                 * Case P is used to print the cursor train at the cursor track in the station
                 * need to to get cursor to get the track cursor and then in that track cursor
                 * print the train cursor
                 * @throws NullPointerException if cursor is null
                 */
                case "P": //done
                    try {
                       st.getCursor().printSelectedTrain();
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }
                    break;
                /**
                 *This is adding a track to the station
                 * so creating a track object where utilization is
                 * set to default o and mention the track number
                 * @throws NullPointerException if cursor is null
                 * @throws InvalidTrack if track already exists
                 */
                case "TA": //done
                    System.out.println("Enter track number:");
                    int trackNumber = input.nextInt();
                    tr = new Track(0, trackNumber);
                    try {
                        st.addTrack(tr);
                        System.out.println("Track " + trackNumber + " is added to the station");

                    } catch (NullPointerException | InvalidTrack e) {
                        e.printStackTrace();
                    }
                    break;
                /**
                 *Case TR is used to remove a track from the station and so it looks for
                 * the cursor track and removes it and updates the track cursor
                 * @throws NullPointerException when cursor is null
                 */
                case "TR": //done
                    try {
                        st.removeSelectedTrack();
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }
                    break;
                /**
                 *Case TS is used to switch a track so user input the track
                 * number to switch to using the boolean select track method
                 * it should update to the switched track cursor
                 * so from track a to b the cursor should now be on b
                 * @throws InvalidTrack if track to switch to doesnt exist
                 */
                case "TS"://done
                    System.out.print("Enter the track number: ");
                    int tracknum = input.nextInt();
                    try {
                        if (st.selectTrack(tracknum))
                        {
                          System.out.println("track selected");
                        }

                    } catch (InvalidTrack ie) {
                        ie.printStackTrace();
                    }
                    break;
                /**
                 *Case TPS is used to print the selected Track cursor using the method
                 * and the toString in track I wasnt able to add the * to indicate
                 * where the cursor is at
                 */
                case "TPS":

                    StringBuilder str = new StringBuilder("*");
                    st.printSelectedTrack();
                    break;
                /**
                 *Case TPA is used to print all the Tracks in the station object
                 * using the printAllTracks and toString in station
                 * The information in the table is correct but it doesnt look like
                 * the sample io table
                 */
                case "TPA":
                    st.printAllTracks();
                    break;
                /**
                 * Case SI Is the station information of each track with their utilization
                 * rate and the number of trains arriving in each tracks
                 */
                case "SI": //works
                    System.out.println("Station (" + st.listLength() + " tracks )");
                    String s1 = "";
                    Track dummy;
                    dummy = st.head;
                    if (dummy == null) {
                        throw new NullPointerException();
                    }
                    while (dummy.getNext() != null) {
                        System.out.println("Track " + dummy.getTrackNumber() + ":" + dummy.listLength1()
                                + " trains arriving " + " Utilization rate " + dummy.getUtilizationRate() + "%");
                        dummy = dummy.getNext();
                    }
                    if (dummy.getNext() == null) {
                        System.out.println("Track " + dummy.getTrackNumber() + ":" + dummy.listLength1()
                                + " trains arriving " + " Utilization rate " + dummy.getUtilizationRate() + "%");
                    }
                    st.setCursor(dummy);
                    break;
                /**
                 * Case Q just quits the program
                 * using system exit
                 */
                case "Q":
                    System.out.println("Terminating sucessfully");
                    System.exit(0);
                    break;
                /**
                 *If a user mistypes it goes back to asking for operation
                 */
                default:
                    break;
            }
        }
    }
}