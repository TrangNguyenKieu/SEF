package Utilities;
import users.User;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import properties.Application;
import properties.Auction;
import properties.Bid;
import properties.Offer;
import properties.Property;

public class FileReadWrite {
    private static File f;
    
    //Users 
    public static void saveUserDetails(String fileName, ArrayList <User> userList) throws IOException {
        FileOutputStream file = new FileOutputStream(fileName);
        ObjectOutputStream out = new ObjectOutputStream(file); //since the array contains an object
        out.writeObject(userList);
        out.close();
        file.close();
    }

    @SuppressWarnings("unchecked")
    public static ArrayList < User > readUserDetails(String fileName) throws IOException, ClassNotFoundException {
        f = new File(fileName);
        ArrayList < User > userList = new ArrayList < > ();
        if (f.exists()) {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
            userList = (ArrayList < User > ) in .readObject(); in .close();
        } else {}
        return userList;
    }

    //Property 
    public static void savePropertyDetails(String fileName, ArrayList <Property> propertyList) throws IOException {
        FileOutputStream file = new FileOutputStream(fileName);
        ObjectOutputStream out = new ObjectOutputStream(file); //since the array contains an object
        out.writeObject(propertyList);
        out.close();
        file.close();
    }

    @SuppressWarnings("unchecked")
    public static ArrayList <Property> readPropertyDetails(String fileName) throws IOException, ClassNotFoundException {
        f = new File(fileName);
        ArrayList <Property> propertyList = new ArrayList < > ();
        if (f.exists()) {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
            propertyList = (ArrayList <Property> ) in .readObject(); in .close();
        } else {}
        return propertyList;
    }


}