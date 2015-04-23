package ua.com.pb.service;

import org.junit.Test;
import ua.com.pb.input.Input;
import ua.com.pb.jobject.JarObject;

import java.util.HashMap;
import java.util.List;
import java.util.jar.JarFile;

import static org.junit.Assert.*;

/**
 * Created by Baelousov Artur Igorevich. E-mail: g.infosecurity@gmail.com on 23.04.15.
 */
public class ServiceTest {
    Service service = new Service();

//    @Test
//    public void testGetJarInfo() throws Exception {
//        String pathToJar = "/tmp/lib/activation-1.1.jar";
//        JarFile jf = new JarFile(pathToJar);
//        JarObject jarObject = service.getJarInfo(jf, new HashMap<String, List<String>>());
//        assertEquals(jarObject.getJarName(), pathToJar);
//    }


    @Test
    public void doany() throws Exception {
        String pathToJar = "/tmp/lib/";
        service.doany(pathToJar);
    }
}