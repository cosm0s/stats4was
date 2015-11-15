package cosm0s.stats4was.utils;


import cosm0s.stats4was.log.L4j;

import java.io.File;
import java.util.List;

public class UtilsFile {

    public static boolean checkFile(String filePath){
        if(filePath != null){
            File file = new File(filePath);
            if(file.exists()) {
                L4j.getL4j().debug(filePath + " exist");
                return true;
            } else if(file.isDirectory()){
                L4j.getL4j().debug(filePath + " is directory");
                return false;
            } else if(file.canRead()){
                L4j.getL4j().debug(filePath + " cant read");
                return true;
            }
        }
        L4j.getL4j().info(filePath + " don't exist");
        return false;
    }

    public static < E extends Iterable<String>>  boolean checkFile(E collection){
        boolean check = true;
        for(String filePath: collection){
            if(!checkFile(filePath)){
                check =  false;
            }
        }
        return check;
    }
}
