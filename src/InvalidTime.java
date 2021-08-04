/**
 * InvalidTime.java SUBMISSION1
 * Name: Sabreen Mostafa
 * ID: 110968588
 * Programming Assignment 2
 * CSE214
 * R10
 * TA Name: Daniel Calabria
 */
public class InvalidTime extends Throwable {
    public InvalidTime(String s){
        System.out.print("too big");
    }

    public InvalidTime() {
        System.out.print("nope");
    }
}
