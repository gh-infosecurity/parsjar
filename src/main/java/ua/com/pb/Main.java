package ua.com.pb;

import ua.com.pb.service.Service;

import java.io.IOException;

/**
 * Created by Baelousov Artur Igorevich. E-mail: g.infosecurity@gmail.com on 24.04.15.
 */
public class Main {
    public static void main(String[] args) {
        Service service = new Service();
        try {
            service.doany(args[0]);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
