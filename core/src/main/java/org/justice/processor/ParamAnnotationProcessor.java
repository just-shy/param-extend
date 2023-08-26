package org.justice.processor;

import cn.hutool.json.JSONUtil;
import org.justice.annotation.ParamTypes;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.lang.model.util.Elements;
import javax.tools.*;
import java.io.*;
import java.util.*;

@SupportedAnnotationTypes("org.justice.annotation.ParamTypes")
@SupportedSourceVersion(SourceVersion.RELEASE_19)
public class ParamAnnotationProcessor extends AbstractProcessor{

    private final Map< TagClass, Set<TagField> > classAnnotationInfos = new HashMap<>();
    private Elements elementUtils;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        Logger.setMessager(processingEnv.getMessager());
        elementUtils = processingEnv.getElementUtils();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (TypeElement annotation : annotations) {
            processParamTypesAnnotation(roundEnv.getElementsAnnotatedWith(annotation));
        }
        return true;
    }

    private void processParamTypesAnnotation(Set<? extends Element> annotatedElements) {
        for (Element element : annotatedElements) {
            if (element.getKind() == ElementKind.FIELD && element instanceof VariableElement fieldElement) {
                String fieldName = fieldElement.getSimpleName().toString();
                String fieldType = fieldElement.asType().toString();
                Element outerClassElement = fieldElement.getEnclosingElement();
                String packageOf = elementUtils.getPackageOf(outerClassElement).toString();
                String className = outerClassElement.getSimpleName().toString();
                TagClass tagClass = new TagClass().setPackageName(packageOf).setClassName(className);
                ParamTypes paramTypesAnnotation = fieldElement.getAnnotation(ParamTypes.class);
                if (paramTypesAnnotation == null) {
                    continue;
                }

                List<String> params = Arrays.asList(paramTypesAnnotation.value());
                TagField tagField = new TagField().setType(fieldType).setName(fieldName).setParams(params);
                classAnnotationInfos.computeIfAbsent(tagClass,k -> new HashSet<>(Collections.singletonList(tagField))).add(tagField);
                Logger.warn(JSONUtil.toJsonStr(classAnnotationInfos));
            }
        }
    }

}

