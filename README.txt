Ez egy Spring Bootban íródott alkalmazás, amely utazási célállomások, programok és utazók kezelését teszi lehetővé.

By Lali és Olivér

---

Használt Technológiák

-Nyelv & Framework: Java, Spring Boot
-Adatbázis & ORM: Spring Data JPA, Hibernate, H2 In-Memory / MySQL
-Frontend: Thymeleaf
-Segédkönyvtárak: Lombok, Jakarta Validation
-Tesztelés: JUnit 5, Mockito, Spring MockMvc, Postman, Integration

Projekt Architektúra

A projekt az alábbi szoftverarchitektúrát követi:

-controller: REST API végpontok JSON válaszokkal
-controller.webcontroller: Thymeleaf sablonokat kiszolgáló webes felület
-service: Üzleti logika, a CRUD műveleteket az absztrakt BaseServiceImpl biztosítja
-repository: JpaRepository interfészek az adatbázis-műveletekhez
-entity: JPA adatbázis entitások
-model: DTO osztályok validációs annotációkkal (@NotBlank, @Email, stb.)
-util: Mapperek az entitások és DTO-k közötti konverzióhoz

Alkalmazás Indítása

Előfeltételek:
-Java 17 vagy újabb
-Maven 3.8+

Alkalmazás indítása
mvn spring-boot:run

Az alkalmazás alapértelmezetten a http://localhost:8080 címen érhető el.