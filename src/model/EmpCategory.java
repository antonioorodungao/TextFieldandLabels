package model;

/**
 * Copyrights 2002-2011 Webb Fontaine
 * Developer: Antonio Oro Dungao
 * Date: 10/5/13
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 */
public enum EmpCategory {
    employed,
    selfEmployed,
    unemployed,
    other;

    public static EmpCategory getEmpCat(int i){
        switch(i){
            case 0:
                return employed;
            case 1:
                return selfEmployed;
            case 2:
                return unemployed;
            case 3:
                return other;
            default:
                return null;
        }
    }
}
