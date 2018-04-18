package com.example.javalib;
import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        System.out.print("hello\n");

        boolean ret=JavaCompilerUtil.CompilerJavaFile("d:/test/Person.java","d:/test/");
        if(!ret){
            System.out.print("error");
        }else{
            System.out.print("ok");
        }

    }
}
