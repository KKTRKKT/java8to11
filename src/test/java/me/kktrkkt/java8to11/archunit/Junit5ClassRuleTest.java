package me.kktrkkt.java8to11.archunit;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import javax.persistence.Entity;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses(packages = "me.kktrkkt.java8to11.junit5", importOptions = {ImportOption.DoNotIncludeTests.class})
public class Junit5ClassRuleTest {

    @ArchTest
    ArchRule controllerRule = classes().that().haveSimpleNameEndingWith("Controller")
            .should().accessClassesThat().haveSimpleNameEndingWith("Service")
            .orShould().accessClassesThat().haveSimpleNameEndingWith("Repository");

    @ArchTest
    ArchRule serviceRule = noClasses().that().haveSimpleNameEndingWith("Service")
            .should().accessClassesThat().haveSimpleNameEndingWith("Controller");

    @ArchTest
    ArchRule repositoryRule = noClasses().that().haveSimpleNameEndingWith("Repository")
            .should().accessClassesThat().haveSimpleNameEndingWith("Service");

    @ArchTest
    ArchRule studyRule = classes().that().haveSimpleNameContaining("Study")
            .and().areNotEnums()
            .and().areNotAnnotatedWith(Entity.class)
            .should().resideInAPackage("..study..");

    @ArchTest
    ArchRule memberRule = classes().that().haveSimpleNameContaining("Member")
            .and().areNotAnnotatedWith(Entity.class)
            .should().resideInAPackage("..member..");

}
