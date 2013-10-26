package model;

/**
 * Copyrights 2002-2011 Webb Fontaine
 * Developer: Antonio Oro Dungao
 * Date: 10/26/13
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 */
public class Message {

    private String title;
    private String contents;

    public Message(String title,String contents){
        this.title = title;
        this.contents = contents;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}
