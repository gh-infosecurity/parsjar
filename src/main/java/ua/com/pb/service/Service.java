package ua.com.pb.service;

import ua.com.pb.input.Input;
import ua.com.pb.jobject.JarCoincidence;
import ua.com.pb.jobject.JarObject;

import java.io.*;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Created by Baelousov Artur Igorevich. E-mail: g.infosecurity@gmail.com on 23.04.15.
 */
public class Service {
    Map<String, List<String>> map;


    public void doany(String path) throws IOException {
        JarObject[] jObjs = getJarObjects(path);
        JarCoincidence[] jarCoincs = compareJarss(jObjs);
        print(jarCoincs);
    }

    private void print(JarCoincidence[] jarCoincs) throws IOException {

        try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("/tmp/index.html"), "utf-8"))) {

            writer.write("<!DOCTYPE html>");
            writer.write("<head>");
            writer.write("<meta charset=\"utf-8\">");
            writer.write("</head>");
            writer.write("<body>");
            writer.write("<table width=\"80%\" align=center border=\"1\">");

            for (JarCoincidence j : jarCoincs) {
                writer.write("<tr>");
                writer.write("<td align=center><h3>");
                writer.write(j.getJarName_fst());
                writer.write("</h3></td>");
                writer.write("<td align=center><h3>");
                writer.write(j.getJarName_scnd());
                writer.write("</h3></td>");
                writer.write("</tr>");

                writer.write("<tr>");
                writer.write("<td colspan=\"2\">");
                for (String s : j.getCoincidencesClasses()) {
                    writer.write("<table width=\"50%\" align=center>");
                    writer.write("<tr>");
                    writer.write("<td>");
                    writer.write(s);
                    writer.write("</td>");
                    writer.write("</tr>");
                    writer.write("</table>");
                }
                writer.write("</td>");
                writer.write("</tr>");
            }


            writer.write("<tr>" +
                    "<td colspan=\"2\" align=center>" +
                    "<h2>Second Variant:</h2></td>" +
                    "</tr>");

            for (Map.Entry<String, List<String>> entry : map.entrySet()) {
                List<String> jarNames = entry.getValue();
                if (jarNames != null && jarNames.size() > 1) {
                    writer.write("<tr>");
                    writer.write("<td>");
                    writer.write(entry.getKey());
                    writer.write("</td>");
                    writer.write("<td>");
                    writer.write(String.valueOf(entry.getValue()));
                    writer.write("</td>");
                    writer.write("</tr>");
                }
            }

            writer.write("</table>");
            writer.write("</body>");
            writer.write("</html>");
        }
    }


    public JarCoincidence[] compareJarss(JarObject[] jObj) {
        ArrayList<JarCoincidence> jarCoincsList = new ArrayList<>();

        for (int i = 0; i < jObj.length; i++) {
            for (int j = 0; j < jObj.length; j++) {
                if (!jObj[i].getJarName().equals(jObj[j].getJarName())) {
                    String[] tmp = compareClasses(jObj[i], jObj[j]);
                    if (tmp.length != 0)
                        jarCoincsList.add(fillJarConcidence(tmp, jObj, i, j));
                }
            }
        }
        return jarCoincsList.toArray(new JarCoincidence[jarCoincsList.size()]);
    }

    private JarCoincidence fillJarConcidence(String[] tmp, JarObject[] jObj, int i, int j) {
        JarCoincidence jarCoin = new JarCoincidence();
        jarCoin.setCoincidencesClasses(tmp);
        jarCoin.setJarName_fst(jObj[i].getJarName());
        jarCoin.setJarName_scnd(jObj[j].getJarName());

        return jarCoin;
    }

    private String[] compareClasses(JarObject jObj_i, JarObject jObj_j) {
        ArrayList<String> coincidence = new ArrayList<>();
        String[] i_cl = jObj_i.getClasses();
        String[] j_cl = jObj_j.getClasses();

        for (int i = 0; i < i_cl.length; i++) {
            for (int j = 0; j < j_cl.length; j++) {
                if (i_cl[i].equals(j_cl[j])) {
                    coincidence.add(i_cl[i]);
                }
            }
        }
        return coincidence.toArray(new String[coincidence.size()]);
    }


    public JarObject[] getJarObjects(String path) throws IOException {
        JarObject jObj;
        Input input = new Input();
        ArrayList<JarObject> jObjects = new ArrayList<>();
        ArrayList<JarFile> jFiles = input.getfiles(path);
        Map<String, List<String>> map = new HashMap<>(jFiles.size());

        for (JarFile jf : jFiles) {
            jObj = getJarInfo(jf, map);
            if (jObj != null)
                jObjects.add(jObj);
        }
        this.map = map;
        return jObjects.toArray(new JarObject[jObjects.size()]);
    }


    public JarObject getJarInfo(JarFile jarFile, Map<String, List<String>> map) {
        JarObject jObj = null;
        ArrayList<String> classes = new ArrayList<>();
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
                jarNames = new ArrayList<>();
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
