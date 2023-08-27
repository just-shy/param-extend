package github.just.shy.processor;

import cn.hutool.core.util.StrUtil;
import com.squareup.javapoet.*;
import javax.lang.model.element.Modifier;
import java.util.*;
import java.util.stream.Collectors;

public class CodeGenerator {

    @SuppressWarnings("unused")
    public static void generator(String allName, TagClass tagClass) {
        if(tagClass.isAbstract())
            return;
        TreeSet<TagField> tagFields = tagClass.getFields();
        Map<String, List<TagField>> innerClassMap = tagFields.stream()
                .flatMap(tagField ->
                        tagField.getParams()
                                .stream()
                                .map(param -> new AbstractMap.SimpleEntry<>(param, tagField))
                )
                .collect(Collectors.groupingBy(Map.Entry::getKey, Collectors.mapping(Map.Entry::getValue, Collectors.toList())));
        CodeGenerator.generatorInner(innerClassMap, tagClass);

    }


    private static void generatorInner(Map<String, List<TagField>> innerClassMap, TagClass tagClass){

        // 获取外部类的名称
        String outerClassName = tagClass.getClassName() + "Param";
        // 获取外部类的包名
        String packageName = tagClass.getPackageName();
        TypeSpec.Builder classBuilder = TypeSpec.classBuilder(outerClassName);
        classBuilder.addModifiers(tagClass.getModifiers().toArray(new Modifier[0]));
        innerClassMap.forEach(
                (className,fields) ->{
                    //内部类的限定名
                    String qualifiedName = outerClassName + "." + className;
                    //内部类的类名和修饰符
                    TypeSpec.Builder typeBuilder = TypeSpec.classBuilder(className)
                            .addModifiers(tagClass.getModifiers().toArray(new Modifier[0]));
                    //内部类字段和方法
                    fields.forEach( field-> {
                        //字段类型
                        TypeName typeName = ClassName.get(field.getElement().asType());
                        //字段名称
                        String name = field.getName();
                        //字段修饰符
                        Modifier[] modifiers = field.getModifiers().toArray(new Modifier[0]);
                        //组装
                        typeBuilder.addField(FieldSpec.builder(typeName, name, modifiers)
                                        .build())
                                .addMethod(MethodSpec.methodBuilder(StrUtil.toCamelCase("get_" + name))
                                        .addModifiers(Modifier.PUBLIC)
                                        .returns(typeName)
                                        .addStatement("return $L",name)
                                        .build())
                                .addMethod(MethodSpec.methodBuilder(StrUtil.toCamelCase("set_" + name))
                                        .addModifiers(Modifier.PUBLIC)
                                        .returns(ClassName.get(packageName, qualifiedName)) // 返回内部类自身的 TypeName
                                        .addStatement("this.$L = $L", name, name) // 设置字段的值
                                        .addStatement("return this") // 返回内部类自身
                                        .build());

                    });
                    classBuilder.addType(typeBuilder.build());
                }
        );

        // 创建源文件
        JavaFile javaFile = JavaFile.builder(packageName,classBuilder.build()).build();
        // 写入文件
        try {
            javaFile.writeTo(ProcessorContext.filer);
        } catch (Exception e) {
            Logger.error(e.toString());
        }
    }

}
