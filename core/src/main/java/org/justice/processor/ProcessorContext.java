package org.justice.processor;

import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

public class ProcessorContext {
    public static Elements elements;
    public static Messager messager;
    public static Filer filer;
    public static Types types;
    public static void init(ProcessingEnvironment environment){
        elements = environment.getElementUtils();
        messager = environment.getMessager();
        types = environment.getTypeUtils();
        filer = environment.getFiler();
    }
}
