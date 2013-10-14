package model;

import java.sql.SQLException;

/**
 * Copyrights 2002-2011 Webb Fontaine
 * Developer: Antonio Oro Dungao
 * Date: 10/14/13
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 */
public class TestDatabase {
    public static void main(String[] args) {
        final Database db = new Database();
        try {
            db.connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.disconnect();
    }
}
