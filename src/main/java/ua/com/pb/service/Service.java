package ua.com.pb.service;

import ua.com.pb.input.Input;
import ua.com.pb.jobject.JarObject;

import java.io.IOException;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Created by Baelousov Artur Igorevich. E-mail: g.infosecurity@gmail.com on 23.04.15.
 */
public class Service {
    Map<String, String> map = new HashMap<String, String>();


    public void doany(String path) throws IOException {
        JarObject[] jObjs = getJarObjects(path);
        compareJarss(jObjs);


        System.out.println("---------------------------------------------------");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + "  |   " + entry.getValue());
        }
//
//        System.out.println(map.size());
    }


    public void compareJarss(JarObject[] jObj) {
        for (int i = 0; i < jObj.length; i++) {
            for (int j = 0; j < jObj.length; j++) {
                if (!jObj[i].getJarName().equals(jObj[j].getJarName())) {
                    compareClasses(jObj[i], jObj[j]);
                }
            }
        }
    }

    private void compareClasses(JarObject jObj_i, JarObject jObj_j) {
        boolean flag = false;
        ArrayList<String> coincidence = new ArrayList<String>();
        String[] i_cl = jObj_i.getClasses();
        String[] j_cl = jObj_j.getClasses();

        for (int i = 0; i < i_cl.length; i++) {
            for (int j = 0; j < j_cl.length; j++) {
                if (i_cl[i].equals(j_cl[j])) {
                    coincidence.add(i_cl[i]);
                    flag = true;
                }
            }
        }

        if (flag) {
            System.out.println(jObj_i.getJarName() + "    |    " + jObj_j.getJarName());

            if (jObj_i.getJarName().equals(map.get(jObj_j.getJarName()))){
                map.put(jObj_i.getJarName(), jObj_j.getJarName());
            }
        }

    }


    public JarObject[] getJarObjects(String path) throws IOException {
        JarObject jObj;
        Input input = new Input();
        ArrayList<JarFile> jFiles = input.getfiles(path);
        ArrayList<JarObject> jObjects = new ArrayList<JarObject>();
        Map<String, List<String>> map = new HashMap<String, List<String>>(jFiles.size());
        for (JarFile jf : jFiles) {
            jObj = getJarInfo(jf, map);
            if (jObj != null)
                jObjects.add(jObj);
        }
//        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
//            List<String> jarNames = entry.getValue();
//            if (jarNames != null && jarNames.size() > 1) {
//                System.out.println(entry.getKey() + "->" + entry.getValue());
//            }
//        }
//        System.out.println(map);
//        System.out.println("\n\n\n\n\n===========================================");
        return jObjects.toArray(new JarObject[jObjects.size()]);
    }


    public JarObject getJarInfo(JarFile jarFile, Map<String, List<String>> map) {
        JarObject jObj = null;
        ArrayList<String> classes = new ArrayList<String>();
        Enumeration e = jarFile.entries();

        while (e.hasMoreElements()) {
            JarEntry je = (JarEntry) e.nextElement();
            if (je.isDirectory() || !je.getName().endsWith(".class")) {
                continue;
            }
            String classPath = je.getName();
            classes.add(classPath);
            List<String> jarNames = map.get(je.getName());
            if (jarNames == null) {
                jarNames = new ArrayList<String>();
                map.put(classPath, jarNames);
            }
            jarNames.add(jarFile.getName());

        }

        if (!classes.isEmpty()) {
            jObj = new JarObject();
            jObj.setJarName(jarFile.getName());
            jObj.setClasses(classes.toArray(new String[classes.size()]));
        }

        return jObj;
    }
}
