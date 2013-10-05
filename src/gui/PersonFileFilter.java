package gui;

import javax.swing.filechooser.FileFilter;
import java.io.File;

/**
 * Copyrights 2002-2011 Webb Fontaine
 * Developer: Antonio Oro Dungao
 * Date: 10/5/13
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 */
public class PersonFileFilter extends FileFilter {
    @Override
    public boolean accept(File f) {
        String extension = Util.getExtension(f.getAbsolutePath());
        if(extension != null && extension.equals(".per")){
            return true;
        }else
            return false;
    }

    @Override
    public String getDescription() {
        return "Person file (.per)";  //To change body of implemented methods use File | Settings | File Templates.
    }
}
