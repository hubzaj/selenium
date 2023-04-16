package org.housecallpro.test.architecture;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.core.domain.properties.CanBeAnnotated.Predicates.annotatedWith;
import static com.tngtech.archunit.lang.conditions.ArchPredicates.have;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = "org.housecallpro.test")
public class ArchitectureTest {

    @ArchTest
    public static final ArchRule rule = classes()
            .that()
            .containAnyMethodsThat(have(annotatedWith(Test.class)))
            .should()
            .haveNameMatching(".*Test")
            .because("It's required to run tests in the correct maven build phase");

}
