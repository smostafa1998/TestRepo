/**
 * Train.java SUBMISSION1
 * Name: Sabreen Mostafa
 * ID: 110968588
 * Programming Assignment 2
 * CSE214
 * R10
 * TA Name: Daniel Calabria
 */

/**
 * This is the Train class
 * Cloneable is implemented in order to clone the object ie make
 * copies of the Train deep or shallow copy.
 *
 * This train class is a linked list
 * Details of the train object are the train number, destination, arrival time,
 * and transfer time.
 * Objects of the train like train next and train prev are made to move to
 * the next train object
 */
public class Train implements Cloneable {
    private Train next;
    private Train prev;
    private int trainNumber;
    private String destination;
    private int arrivalTime;
    private int transferTime;

    /**
     * blank default Train constructor
     */
    public Train() {
    }

    /**
     * This public constructor returned back the variables of the Train object
     * here Train next and Train prev are null on default
     * @param trainNumber
     * @param destination
     * @param arrivalTime
     * @param transferTime
     * @return the train number, destination, arrival time, and transfer time
     */
    public Train(int trainNumber, String destination, int arrivalTime, int transferTime) {
        this.trainNumber = trainNumber;
        this.destination = destination;
        this.arrivalTime = arrivalTime;
        this.transferTime = transferTime;
        next = null;
        prev = null;
    }

    /**
     * This acts like the link to the Trains next Train so it sets the next train
     * this is the setter
     * @param nextTrain
     */
    void setNext(Train nextTrain) {
        next = nextTrain;
    }

    /**
     * This acts like the link to the Trains prev Train so it sets the prev train
     * this is the setter
     * @param prevTrain
     */

    void setPrev(Train prevTrain) {
        prev = prevTrain;
    }

    /**
     * This gets the data of the next trains data so it will return that next train
     * this is the getter
     * @return next
     */
    Train getNext() {
        return next;
    }

    /**
     * This gets the data of the prev trains data so it will return that prev train
     * this is the getter
     *  @return prev
     */
    Train getPrev() {
        return prev;
    }

    /**
     * This returns the train number of the object train
     * this is the getter
     * @return trainNumber
     */

    public int getTrainNumber() {
        return trainNumber;
    }

    /**
     * This sets the train number to the object train
     * this is the setter
     * @param trainNumber
     */
    public void setTrainNumber(int trainNumber) {
        this.trainNumber = trainNumber;
    }

    /**
     * This returns the string of the destination for the train
     * this is a getter
     * @return destination
     */
    public String getDestination() {
        return destination;
    }

    /**
     *this is the setter for the destination for the train
     * this is a setter
     * @param destination
     */
    public void setDestination(String destination) {
        this.destination = destination;
    }

    /**
     * this returns the int for the arrival for the train
     * @return arrivalTime
     */

    public int getArrivalTime() {
        return arrivalTime;
    }

    /**
     * this is the setter for the arrival for the train
     * this is the setter
     * @param arrivalTime
     */
    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    /**
     * this returns the getter for the transfer time for the train
     * this is the getter
     * @return
     */

    public int getTransferTime() {
        return transferTime;
    }

    /**
     * this is the setter for the transfer time for the train
     * this is the setter
     * @param transferTime
     */
    public void setTransferTime(int transferTime) {
        this.transferTime = transferTime;
    }

    /**
     * this is the clone method it is used to make a deep object of the train
     * idek if this is really used
     * @return copy
     * @throws CloneNotSupportedException
     * @throws RuntimeException
     */
    public Train clone() {
        Train copy;

        try {
            copy = (Train) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("this class is not cloneable");
        }
        return copy;
    }

    /**
     * This is a public boolean equals method that checks if a certian
     * train object is equal to another train method
     * they are seen to be considered equal if their train number is the
     * same so the train number are compared. if the same true
     * @param obj
     * @return true if both objects equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {

        if (obj instanceof Train) {
            Train testing = (Train) obj;
            return (testing.trainNumber == trainNumber);
        } else
            return false;
    }

    /**
     * this is a boolean train method used to see if a certain train
     * already exists in the linked list
     * @param train
     * @return the train that exists
     * @throws IllegalArgumentException
     */
    public boolean exists(Train train) throws IllegalArgumentException {

        if (train instanceof Train) {
            Train o = (Train) train;
            return (train.getArrivalTime() == o.getArrivalTime() &&
                    train.getDestination() == o.getDestination() &&
                    train.getTrainNumber() == o.getTrainNumber() &&
                    train.getTransferTime() == o.getTransferTime());
        } else
            throw new IllegalArgumentException();
    }

    /**
     * This string method is used to output the string output of the train object
     * @return the string of the train object
     */
    public String toString() {
        String s1 = "Train Number:";
        String s2 = "Train Destination:";
        String s3 = "Arrival Time:";
        String s4 = "Departure Time:";
        String a=String.format("%04d", arrivalTime);
        String b=String.format("%04d",(transferTime + arrivalTime) );

        String s5 = (s1 + trainNumber + "\n" + s2 + destination + "\n" + s3 + a + "\n" + s4 + b + "\n");
        return s5;
    }


}
