package gui;

/**
 * Copyrights 2002-2011 Webb Fontaine
 * Developer: Antonio Oro Dungao
 * Date: 10/5/13
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 */
public class Util {
    public static String getExtension(String filename){
        int index = filename.lastIndexOf(".");

        if(index == -1){
            return  null;
        }
        return filename.substring(index, filename.length());
    }
}
