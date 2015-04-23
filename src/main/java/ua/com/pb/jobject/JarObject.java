package ua.com.pb.jobject;

/**
 * Created by Baelousov Artur Igorevich. E-mail: g.infosecurity@gmail.com on 23.04.15.
 */
public class JarObject {
    private String jarName;
    private String[] classes;

    public String getJarName() {
        return jarName;
    }

    public void setJarName(String jarName) {
        this.jarName = jarName;
    }

    public String[] getClasses() {
        return classes;
    }

    public void setClasses(String[] classes) {
        this.classes = classes;
    }
}
