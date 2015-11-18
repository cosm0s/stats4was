package cosm0s.stats4was.utils;

import cosm0s.stats4was.log.L4j;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class UtilsFile {

    private UtilsFile(){}

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

    public static void print(String line, File file) {
        L4j.getL4j().info(line);
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(file, true);
            line = line.replace(Constants.boldText, "");
            line = line.replace(Constants.plainText, "");
            line = line + "\n";
            fileWriter.write(line);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static File getFile(String name) throws IOException {
        int count = 0;
        boolean fileNameFound = false;
        File file;
        do {
            String fileName = DaemonContext.instance().getProperty("PathListReturn") + name + ((count == 0)?"":String.valueOf(count)) + ".info";
            file = new File(fileName);
            if(!file.exists()){
                L4j.getL4j().info("Creating file: " + file.getAbsoluteFile());
                file.createNewFile();
                fileNameFound = true;
            } else {
                count++;
            }
        } while(!fileNameFound);
        return file;
    }
}
