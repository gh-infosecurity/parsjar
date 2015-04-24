package ua.com.pb.input;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.jar.JarFile;

/**
 * Created by Baelousov Artur Igorevich. E-mail: g.infosecurity@gmail.com on 23.04.15.
 */
public class Input {

    public ArrayList<JarFile> getfiles(String path) throws IOException {
        ArrayList<JarFile> jFiles;
        File dir = new File(path);
        if (dir.isDirectory()) {
            jFiles = listFilesForFolder(dir);
        } else
            throw new IllegalArgumentException("Incorrect input data");

        if (jFiles.isEmpty())
            throw new IllegalArgumentException("Directory contains any *.jar file");

        return jFiles;
    }

    public ArrayList<JarFile> listFilesForFolder(File folder) throws IOException {
        ArrayList<JarFile> jarFiles = new ArrayList<JarFile>();
        for (File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
                if (identJarType(fileEntry.getAbsolutePath()))
                    jarFiles.add(new JarFile(fileEntry.getAbsolutePath()));
            }
        }
        return jarFiles;
    }


    public boolean identJarType(String fileName) {
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
        if (extension.equals("jar"))
            return true;
        return false;
    }


}
