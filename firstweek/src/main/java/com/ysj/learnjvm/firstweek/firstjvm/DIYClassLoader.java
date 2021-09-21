package com.ysj.learnjvm.firstweek.firstjvm;

import java.io.*;

/**
 * 自定义一个ClassLoader 继承ClassLoader并重写findClass
 * 简易版。。。
 * @author  YSJ
 */
public class DIYClassLoader extends ClassLoader{
    private final String path;

    public DIYClassLoader(String path){
        this.path=path;
    }

    public static void main(String[] args) {
        String classPath = "";
        DIYClassLoader ysjClassLoader = new DIYClassLoader(classPath);

    }
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] data = new byte[0];
        try {
            data = readBytes(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return defineClass(name,data,0,data.length);
    }

    /**
     * 读取文件字节码
     * @param name
     * @return
     */
    private byte[] readBytes(String name) throws IOException {
        //全量名称
        name = name.replaceAll("\\.", "\\\\");
        String suffix =".class";
        String classFilePath = path +name+suffix;
        File classFile = new File(classFilePath);
        InputStream fis = null;
        //读取byte文件流
        ByteArrayOutputStream bos =null;

        byte[] bytes =new byte[1024];

        byte[] readBytes = null;

        try{
            bos = new ByteArrayOutputStream();
            fis = new FileInputStream(classFile);
            int length;
            while((length =fis.read(bytes))!=-1){
                bos.write(bytes,0,length);
            }
            readBytes = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(fis != null){
                fis.close();
            }
            if(bos!=null){
                bos.close();
            }
        }
        return readBytes;

    }
}
