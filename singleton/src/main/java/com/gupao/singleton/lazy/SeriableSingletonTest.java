package com.gupao.singleton.lazy;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @Author:ChunJieRen
 * @Date:2019/3/10 15:03
 * @Description:
 */
public class SeriableSingletonTest {

    public static void main(String[] args) {

        SeriableSingleton s1= null;
        SeriableSingleton s2 = null;

        s1= SeriableSingleton.getInstance();

        FileOutputStream fos = null;
        try{
            fos = new FileOutputStream("SeriableSingleton.class");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(s1);
            oos.flush();
            oos.close();
            System.out.println("as");
            FileInputStream fis = new FileInputStream("SeriableSingleton.class");
            ObjectInputStream ois = new ObjectInputStream(fis);
            s2 = (SeriableSingleton) ois.readObject();
            ois.close();
            System.out.println(s1==s2);
        }catch (Exception e){

        }
    }
}
