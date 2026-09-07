package fr.euphyllia.fidorial.server.util.annotations.processor;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.util.Map;
import java.util.Set;

@SupportedAnnotationTypes("fr.euphyllia.fidorial.server.util.annotations.NeedsToBeRevisited")
@SupportedSourceVersion(SourceVersion.RELEASE_25)
public final class NeedsToBeRevisitedProcessor extends AbstractProcessor {

    private static final String ANNOTATION_NAME =
            "fr.euphyllia.fidorial.server.util.annotations.NeedsToBeRevisited";

    @Override
    public boolean process(final Set<? extends TypeElement> annotations, final RoundEnvironment roundEnv) {
        final Messager messager = processingEnv.getMessager();

        for (final TypeElement annotationType : annotations) {
            if (!annotationType.getQualifiedName().contentEquals(ANNOTATION_NAME)) {
                continue;
            }

            for (final Element element : roundEnv.getElementsAnnotatedWith(annotationType)) {
                final String reason = valueOf(element, annotationType);
                messager.printMessage(
                        Diagnostic.Kind.MANDATORY_WARNING,
                        "@NeedsToBeRevisited " + element.getSimpleName() + "(): Needs revisiting because: " + reason,
                        element);
            }
        }

        return false;
    }

    private static String valueOf(final Element element, final TypeElement annotationType) {
        for (final AnnotationMirror mirror : element.getAnnotationMirrors()) {
            if (!mirror.getAnnotationType().asElement().equals(annotationType)) {
                continue;
            }
            for (final Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> entry :
                    mirror.getElementValues().entrySet()) {
                if (entry.getKey().getSimpleName().contentEquals("value")) {
                    return String.valueOf(entry.getValue().getValue());
                }
            }
        }
        return "<no reason given>";
    }
}
