package model;

/**
 * Copyrights 2002-2011 Webb Fontaine
 * Developer: Antonio Oro Dungao
 * Date: 10/5/13
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 */
public enum GenderCategory {
    male,
    female;

    public static GenderCategory getGenderCategory(int i){
        switch (i){
            case 0:
                return male;
            case 1:
                return female;
        }
        return  null;
    }
}
