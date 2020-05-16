package Utilities;

public enum PropertyStatus {
Pending, //when branch manager has not assigned employee yet
Available, //after assigned employee to property
Let,// when rent deposit has been received
UnderContract, //when sale deposit has been received
InProcess,//after application/bid/offer has been accepted and before any deposit has been made
Sold; //after final payment has been made to sale property
}
