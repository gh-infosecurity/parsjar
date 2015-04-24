package ua.com.pb.input;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.jar.JarFile;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Baelousov Artur Igorevich. E-mail: g.infosecurity@gmail.com on 23.04.15.
 */
public class InputTest {
    Input input = new Input();

    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @Test
    public void testGetfiles() throws IOException {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Incorrect input data");
        input.getfiles("");
    }

    @Test
    public void testGetfiles_Any() throws IOException {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Directory contains any *.jar file");
        input.getfiles("/tmp/");
    }

    @Test
    public void testListFilesForFolder() throws Exception {
        File dir = new File("/tmp/lib");
        ArrayList<JarFile> files;
        files = input.listFilesForFolder(dir);

        for (JarFile file:files){
            input.identJarType(file.getName());
        }
    }

    @Test
    public void identFileType_true() throws  Exception{
        assertTrue(input.identJarType("woodstox.jar"));
    }

    @Test
    public void identFileType_false() throws  Exception{
        assertFalse(input.identJarType("woodstox.zip"));
    }
}