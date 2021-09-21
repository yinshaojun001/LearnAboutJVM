package com.ysj.learnjvm.firstweek.firstjvm;

import sun.misc.Launcher;

import java.net.URL;

/*
不是作业 just test
 */
public class ClassLoaderTest {
    public static void main(String[] args) {
        URL[] urLs = Launcher.getBootstrapClassPath().getURLs();
        System.out.println("Test ClassLoader");
        for (URL url:urLs) {
            System.out.println(url.toExternalForm());
        }

    }
}
