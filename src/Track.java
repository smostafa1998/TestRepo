/**
 * Track.java SUBMISSION 1
 * Name: Sabreen Mostafa
 * ID: 110968588
 * Programming Assignment 2
 * CSE214
 * R10
 * TA Name: Daniel Calabria
 */

/**
 * This is the Track class
 * this class contains the the Train head, cursor, and tail because this
 * is where linked list manipulation of the train objects is done since
 * the trains exist within the Track class.
 * And since the Track object itself will be part of a track linked list
 * we have a Track next and Track prev
 * There is also a utilization rate which was the total transfer time/1440 for
 * each track
 * there is also the track number for identifying purposes
 */

import java.math.BigDecimal;
import java.math.RoundingMode;
public class Track implements Cloneable {
    private Train head;
    private Train tail;
    private Train cursor;

    private Track next;
    private Track prev;
    private double utilizationRate;
    private int trackNumber;
    int numberoftrains; //keeping track for the number of trains in the track

    /**
     * This constructor is used to intalize the trains head, tail, cursor to be
     * null
     */
    public Track() {
        head = null;
        tail = null;
        cursor = null;

    }

    /**
     * this stores the variables of the track objects so the
     * utilization rate and the track number
     * @param utilizationRate
     * @param trackNumber
     */
    public Track(double utilizationRate, int trackNumber) {

        this.utilizationRate = utilizationRate;
        this.trackNumber = trackNumber;
        numberoftrains = 0;
        next = null;
        prev = null;

    }

    /**
     * This is the Train head object so this return the head and its info
     * this is a getter
     * @return head
     */

    public Train getHead() {
        return head;
    }

    /**
     *this is the Train head object so this sets to the head
     * this is a setter
     * @param head
     */
    public void setHead(Train head) {
        this.head = head;
    }

    /**
     *This is the Train tail object so this return the tail of the train linked list and its info
     * this is a getter
     * @return tail
     */

    public Train getTail() {
        return tail;
    }

    /**
     *this is the Train tail object so this sets to the tail
     *this is a setter
     * @param tail
     */

    public void setTail(Train tail) {
        this.tail = tail;
    }

    /**
     *This is the train cursor object so this will return the cursor of train linked list
     * this is a getter
     * @return
     */
    public Train getCursor() {

        return cursor;
    }

    /**
     * This is the train cursor object so this set the cursor
     *This is the setter
     * @param cursor
     */
    public void setCursor(Train cursor) {
        this.cursor = cursor;
    }

    /**
     *this is a getter used to acesss the the tracks next track and its info
     * this acts like a node get data
     * @return
     */
    public Track getNext() {
        return next;
    }

    /**
     *this is a setter used to set the Track next track
     * this acts like a link
     * @param next
     */
    public void setNext(Track next) {
        this.next = next;
    }

    /**
     *this is a getter used to acess the track prev track and its info
     * this acts like the prev get data
     * @return prev
     */
    public Track getPrev() {
        return prev;
    }

    /**
     *this is the setter used to set the Tack prev track
     * this acts like a link
     * @param prev
     */
    public void setPrev(Track prev) {
        this.prev = prev;
    }

    /**
     *this is a getter used to calculate the utilization rate of the track
     * this is calculated by transversing through the trains linked list and
     * taking the sum of every trains node transfer time , a while loop is used for this
     * purpose then after that the sum is divided by the total minutes so 1440 and * 100
     * for that percantage form. Because we wanted to round it up and have less bits to show
     * we use bigdecimal library for the rounding and returned the value
     * @return utilization rate for each track
     */
    public double getUtilizationRate() {

        double sum = 0;
        cursor = head;
        if (cursor == null) {
            throw new NullPointerException();
        }
        while (cursor != null) {
            // answer++;
            sum = sum + cursor.getTransferTime();
            cursor = cursor.getNext();

        }

        double ut = sum / 1440.0;

        utilizationRate = ut * 100;
        BigDecimal bd = new BigDecimal(Double.toString(utilizationRate));
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    /**
     *This is a setter it sets the Utilization rate  of the track
     * @param utilizationRate
     */
    public void setUtilizationRate(double utilizationRate) {
        this.utilizationRate = utilizationRate;
    }

    /**
     *this is a getter this obtains the value of the track number of the track
     * @return trackNumber
     */
    public int getTrackNumber() {
        return trackNumber;
    }

    /**
     *this is the setter this sets the value of the track number to the track
     * @param trackNumber
     */
    public void setTrackNumber(int trackNumber) {

        this.trackNumber = trackNumber;
    }

    /**
     *This is a public new train method
     * this is used to add a train into a linked list
     * due to time constraints i was only able to do the scenario where
     * the node is added after the cursor so it will not sort based on time,
     * i learned about how to impelment this method from TA recitation
     * I checked three condiitons
     * 1. new train null just means the object was never created so its null so this throws null exception
     * 2. when list is empty assign new train as head tail and cursor since only one node
     * 3.if there is a node but if next to the node there is no other node so null then
     * we set the cursor to the next train which is next to the cursor
     * 4.this is a scenario of inserting the node in between
     * 5. incrementing the number of trains in the list.
     * @param newTrain
     * @throws TrainAlreadyExists
     * @throws InvalidTime
     * @throws NullPointerException
     */
    public void addTrain(Train newTrain) throws TrainAlreadyExists, InvalidTime, NullPointerException {

        if (newTrain == null) {
            throw new NullPointerException();
        }

        if (head == null) {   //list is empty
            head = newTrain;
            tail = newTrain;
            cursor = newTrain;
            return;
        }
        if (cursor.getNext() == null) {
            // if (newTrain.getArrivalTime() > cursor.getArrivalTime()) {
            //when time for new train is greatr than current time
            cursor.setNext(newTrain); // has to be NT C to C NT
            newTrain.setPrev(cursor); // C  NT
            cursor = newTrain;
            tail = cursor;
            return;
        }
        cursor.getNext().setPrev(newTrain); //
        newTrain.setNext(cursor.getNext());
        newTrain.setPrev(cursor);
        cursor.setNext(newTrain);
        cursor = newTrain;
        numberoftrains++;
    }


    /**
     *This is used to print a selected cursor train from the current track
     * all thats done is assigning cursor to string
     * @throws NullPointerException if no cursor
     */
    public void printSelectedTrain() {
        try {
            if (cursor!=null) {
                System.out.println("Selected Track");
                System.out.println(cursor.toString());
            }
        }catch (NullPointerException ie){
            ie.printStackTrace();
        }
    }

    /**
     *  This is the basic method of removing a selected train
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
     *  decrement the number of trains after this
     * @return the current cursor after the removal
     * @throws NullPointerException
     */

    public Train removeSelectedTrain() throws NullPointerException {
        if (cursor == null) {
            throw new NullPointerException();
        } else if (cursor.getNext() == null && cursor.getPrev() == null) {
            cursor = null;
            //  return cursor;
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
        numberoftrains--; // does this need to be added?
        System.out.println("Sucessfully removed the cursor ");
        return cursor;
    }

    /**
     *This public boolean is used to select the next train of the linked list
     * which basically says if the cursor is not equal to tail then the cursor can
     * move
     * @return true of moving to next, false otherwise
     */

    public boolean selectNexttrain() {
        if (cursor != tail) {
            cursor = cursor.getNext();
            return true;
        } else
            return false;
    }

    /**
     *this public boolean is used to select the prev train of the linked list
     * which basically says if the cursor is not equal to the head then the cursor
     * move
     * @return true to moving to prev, false otherwise
     */
    public boolean selectPrevTrain() {
        if (cursor != head) {
            cursor = cursor.getPrev();
            return true;
        } else
            return false;
    }

    /**
     *This is a boolean exists track, this is implemented when a certain track
     * exists based off of the track number
     * @param track
     * @return the existing track so true false if not
     * @throws IllegalArgumentException
     */
    public boolean exists(Track track) throws IllegalArgumentException {

        if (track instanceof Track) { //track exists if track number is same and utilization
            Track o = (Track) track;
            return (track.getTrackNumber() == o.getTrackNumber() );
        } else
            throw new IllegalArgumentException();
    }

    /**
     *This is the boolean equals method this is used to compare a object to the track object
     * to see if they are equal and what detemines them to be equal is the track number
     * @param obj
     * @return true if the object is equal to track object
     */
    public boolean equals(Object obj) {

        if (obj instanceof Track) { //track is considered the same if only track number
            Track testing = (Track) obj;
            return (testing.trackNumber == trackNumber);
        } else
            return false;
    }

    /**
     *This is a clone method for the track this is used to make a deep copy of the object
     * @return
     */
    public Object clone() {

        Track copy;

        try {
            copy = (Track) super.clone();

        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("this class is not cloneable");
        }
        return copy;
    }

    /**
     *this listlength is used to measure the lengh of the train linked list
     * this is used for SI station info
     * @return the number of nodes in list
     */
    public int listLength1() {
        Train dummynode = head;
        int answer = 0;
        while (dummynode != null) {
            answer++;
            dummynode = dummynode.getNext();
        }
        return answer;
    }

    /**
     *String to String is very important for TPS AND TPA as it
     * will show the string format of the linked list for the current track
     * and it will show the string format of the linked list for all tracks
     * based on how its coded in each method
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
        String Selected = "Selected";
        String Train_Num = "Train Number:";
        String Track_Num = "Track Number";
        String U="% Utilization Rate";
        String Train_dest = "Train Destination";
        String Arrival_time = "Arrival Time";
        String Departure = "Departure Time";
        String s10 = "";

        Train cursor2;
        cursor2 = head;



        if (cursor2 == null) {
            return s10;
        }

        while (cursor2.getNext() != null) {
            s10 += "\n";
            s10 +=  String.format("%-21s%-26s %04d                         %04d", cursor2.getTrainNumber(), cursor2.getDestination(), cursor2.getArrivalTime(), (cursor2.getArrivalTime() + cursor2.getTransferTime()));
            s10 += "\n";

            cursor2 = cursor2.getNext();
        }

        if (cursor2.getNext() == null) {
/*
            s10 += String.format("%-21s%-22s%.2f%-20s",Track_Num,trackNumber, this.getUtilizationRate(),U);
            s10 += "\n";
            s10 += String.format("%-21s%-26s%-26s%-20s", Train_Num, Train_dest, Arrival_time, Departure);
            s10 += "\n";
            s10 += String.format("----------------------------------------------------------------------------------------------");*/
            s10 += "\n";
            s10 +=  String.format("%-21s%-26s %04d                         %04d", cursor2.getTrainNumber(), cursor2.getDestination(), cursor2.getArrivalTime(), (cursor2.getArrivalTime() + cursor2.getTransferTime()));
            s10 += "\n";
        }

        setCursor(cursor2);
        return s10;

    }
}