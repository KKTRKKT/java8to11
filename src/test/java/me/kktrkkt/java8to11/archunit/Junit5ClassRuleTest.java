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

    // Controller는 Service와 Repository에 접근할 수 있다.
    @ArchTest
    ArchRule controllerRule = classes().that().haveSimpleNameEndingWith("Controller")
            .should().accessClassesThat().haveSimpleNameEndingWith("Service")
            .orShould().accessClassesThat().haveSimpleNameEndingWith("Repository");

    // Service는 Controller에 접근하면 안된다.
    @ArchTest
    ArchRule serviceRule = noClasses().that().haveSimpleNameEndingWith("Service")
            .should().accessClassesThat().haveSimpleNameEndingWith("Controller");

    // Repository는 Service와 Controller에 접근해서는 안된다.
    @ArchTest
    ArchRule repositoryRule = noClasses().that().haveSimpleNameEndingWith("Repository")
            .should().accessClassesThat().haveSimpleNameEndingWith("Service")
            .orShould().accessClassesThat().haveSimpleNameEndingWith("Controller");

    // study 관련 클래스들은 study package 안에 있어야 한다, 단 enum과 entity는 제외
    @ArchTest
    ArchRule studyRule = classes().that().haveSimpleNameContaining("Study")
            .and().areNotEnums()
            .and().areNotAnnotatedWith(Entity.class)
            .should().resideInAPackage("..study..");

    // member 관련 클래스는 member package 안에 있어야한다, 단 entity는 제외
    @ArchTest
    ArchRule memberRule = classes().that().haveSimpleNameContaining("Member")
            .and().areNotAnnotatedWith(Entity.class)
            .should().resideInAPackage("..member..");

}
