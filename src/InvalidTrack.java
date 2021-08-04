/**
 * InvalidTrack.java SUBMISSION1
 * Name: Sabreen Mostafa
 * ID: 110968588
 * Programming Assignment 2
 * CSE214
 * R10
 * TA Name: Daniel Calabria
 */
public class InvalidTrack extends Throwable {
    public InvalidTrack(String s){
        System.out.print("Could not switch tracks ");
    }

    public InvalidTrack() {
        System.out.print("nope");
    }
}
