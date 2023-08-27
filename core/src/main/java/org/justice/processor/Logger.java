package org.justice.processor;

import javax.tools.Diagnostic;

@SuppressWarnings("unused")
public class Logger {


    public static void note(String note){
        ProcessorContext.messager.printMessage(Diagnostic.Kind.NOTE,note);
    }

    public static void warn(String warn){
        ProcessorContext.messager.printMessage(Diagnostic.Kind.WARNING,warn);
    }

    public static void error(String error){
        ProcessorContext.messager.printMessage(Diagnostic.Kind.ERROR,error);
    }

}
