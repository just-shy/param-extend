package org.justice.processor;

import javax.annotation.processing.Messager;
import javax.tools.Diagnostic;

public class Logger {

   private static Messager messager;

   public static void setMessager(Messager messager){
       Logger.messager = messager;
   }

    public static void note(String note){
        messager.printMessage(Diagnostic.Kind.NOTE,note);
    }

    public static void warn(String warn){
        messager.printMessage(Diagnostic.Kind.WARNING,warn);
    }

    public static void error(String error){
        messager.printMessage(Diagnostic.Kind.ERROR,error);
    }

}
