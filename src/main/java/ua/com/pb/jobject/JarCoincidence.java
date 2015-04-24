package ua.com.pb.jobject;

/**
 * Created by Baelousov Artur Igorevich. E-mail: g.infosecurity@gmail.com on 23.04.15.
 */
public class JarCoincidence {
    private String jarName_fst;
    private String jarName_scnd;
    private String[] coincidencesClasses;

    public String getJarName_scnd() {
        return jarName_scnd;
    }

    public void setJarName_scnd(String jarName_scnd) {
        this.jarName_scnd = jarName_scnd;
    }

    public String getJarName_fst() {
        return jarName_fst;
    }

    public void setJarName_fst(String jarName_fst) {
        this.jarName_fst = jarName_fst;
    }

    public String[] getCoincidencesClasses() {
        return coincidencesClasses;
    }

    public void setCoincidencesClasses(String[] coincidencesClasses) {
        this.coincidencesClasses = coincidencesClasses;
    }
}
