package ua.com.pb.service;

import org.junit.Ignore;
import org.junit.Test;
import ua.com.pb.input.Input;
import ua.com.pb.jobject.JarObject;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.jar.JarFile;
import java.util.zip.ZipException;

import static org.junit.Assert.*;

/**
 * Created by Baelousov Artur Igorevich. E-mail: g.infosecurity@gmail.com on 23.04.15.
 */
public class ServiceTest {
    Service service = new Service();

    @Test(expected = ZipException.class)
    public void testGetJarInfo() throws Exception {
        String pathToJar = "/tmp/test.jar";
        File jFile = new File(pathToJar);
        jFile.createNewFile();
        JarFile jf = new JarFile(pathToJar);
        JarObject jarObject = service.getJarInfo(jf, new HashMap<String, List<String>>());
        assertEquals(jarObject.getJarName(), pathToJar);
    }


    @Test
    public void doany() throws Exception {
        String pathToJar = "/tmp/lib/";
        service.doany(pathToJar);
    }
}