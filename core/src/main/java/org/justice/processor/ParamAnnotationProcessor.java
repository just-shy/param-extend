package org.justice.processor;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import java.util.*;

@SupportedAnnotationTypes("org.justice.annotation.ParamTypes")
@SupportedSourceVersion(SourceVersion.RELEASE_19)
public class ParamAnnotationProcessor extends AbstractProcessor{

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        ProcessorContext.init(processingEnv);
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (TypeElement annotation : annotations) {
            processParamTypesAnnotation(roundEnv.getElementsAnnotatedWith(annotation));
        }
        return true;
    }

    private void processParamTypesAnnotation(Set<? extends Element> annotatedElements) {
        Map<String,TagClass> classMap = new HashMap<>();
        for (Element element : annotatedElements) {
            if (element.getKind() == ElementKind.FIELD && element instanceof VariableElement fieldElement) {
                TagClass tagClass = new TagClass(fieldElement);
                TagField tagField = new TagField(fieldElement);
                classMap.computeIfAbsent(tagClass.getAllName(),k -> tagClass).getFields().add(tagField);
            }
        }
        classMap.forEach(CodeGenerator::generator);
    }
}

