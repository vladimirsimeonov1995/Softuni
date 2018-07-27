package p01_Cards.utils;

import p01_Cards.enums.CustomAnnotation;

public final class AnnotationOperations {

    public static String getDescription(Class<?> cl){

        CustomAnnotation annotation = cl.getAnnotation(CustomAnnotation.class);

        StringBuilder sb = new StringBuilder();

        sb.append("Type = ").append(annotation.type()).append(", Description = ").append(annotation.description());

        return sb.toString();
    }

}
