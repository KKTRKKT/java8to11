package me.kktrkkt.java8to11.archunit;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

// 아키텍처 테스트를 적용할 패키지 경로등의 옵션을 설정할 수 있다.
@AnalyzeClasses(packages = "me.kktrkkt.java8to11.junit5", importOptions = { ImportOption.DoNotIncludeTests.class })
public class Junit5PackageRuleTest {

    // domain 패키지는 study, member, domain에서 접근할 수 있다.
    @ArchTest
    ArchRule domainPackageRule = classes().that().resideInAPackage("..domain..")
            .should().onlyBeAccessed().byClassesThat()
            .resideInAnyPackage("..study..", "..member..", "..domain..");

    // member 패키지는 study, member에서 접근할 수 있다.
    @ArchTest
    ArchRule memberPackageRule = classes().that().resideInAPackage("..member..")
            .should().onlyBeAccessed().byClassesThat()
            .resideInAnyPackage("..study..", "..member..");

    // study 패키지는 오직 study에서만 접근 할 수 있다.
    @ArchTest
    ArchRule studyPackageRule = classes().that().resideInAPackage("..study..")
            .should().onlyBeAccessed().byClassesThat()
            .resideInAnyPackage("..study..");

    // junit5 패키지의 모든 클래스는 순환 참조가 없어야한다.
    @ArchTest
    ArchRule freeOfCycles = slices().matching("..junit5.(*)..")
            .should().beFreeOfCycles();
}
