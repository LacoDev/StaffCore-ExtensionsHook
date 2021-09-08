package de.lacodev.staffcore.extensionshook.utils;

import java.io.DataInputStream;
import java.io.IOException;

public class ChannelUtility {
	
    public static String getString(DataInputStream input){
        try {
            return input.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;    
    }
}
