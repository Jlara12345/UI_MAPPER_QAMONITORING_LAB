# UI_MAPPER — Project Contract

## 1. Objetivo

El archivo `ui-mapper-project.properties` es el contrato entre el proyecto consumidor y el framework `UI_MAPPER`.

Ubicación:

```text
src/test/resources/ui-mapper-project.properties
```

---

## 2. Identidad del proyecto

```properties
project.name=UI_MAPPER_ANGULAR_CONSUMER
project.applicationName=NGX_ADMIN_ANGULAR
project.moduleName=AUTOMATION_WEB
```

Sirve para identificar el consumidor, la aplicación y el módulo.

---

## 3. Navegación / aplicación

```properties
project.baseUrl=http://localhost:4200
project.pageBase=features
```

`project.baseUrl` es la URL principal de la aplicación.

`project.pageBase` se usa para construir PageIds durante capturas.

---

## 4. Features de Autodiscovery

```properties
project.features.directory=src/test/resources/features/autodiscovery
```

Carpeta de features para descubrir UI.

---

## 5. Selector Repository

```properties
project.selector.definitionFile=src/test/resources/selectors/SELECTOR_REPOSITORY.properties
project.selector.repositoryPath=target/ui-mapper/selectors
project.selector.specPath=target/ui-mapper/specs
```

`project.selector.definitionFile` es la ruta principal del repositorio de selectores.

---

## 6. Autodiscovery

```properties
project.autodesc.packageName=com.miempresa.demo.autodesc
project.autodesc.outputDirectory=src/test/java
project.autodesc.classNameSuffix=_JUNIT_SELENIUM
```

Define dónde se generan scripts Selenium de Autodiscovery.

---

## 7. POM Generation

```properties
project.pom.packageName=com.miempresa.demo.pom
project.pom.outputDirectory=src/test/java
```

Define dónde se generan las clases POM.

---

## 8. Business Generation

```properties
project.business.features.directory=src/test/resources/features/business
project.business.packageName=com.miempresa.demo.business
project.business.outputDirectory=src/test/java
project.business.classNameSuffix=_JUNIT_SELENIDE
```

Define dónde se leen features Business y dónde se generan scripts Selenide.

---

## 9. Timeouts

```properties
project.selenium.implicitWaitSeconds=60
project.selenide.timeoutMs=60000
project.selenide.pageLoadTimeoutMs=60000
```

---

## 10. Page Aliases

```properties
project.business.page.LOGIN=FeaturesAuthLoginPage
```

Mapea:

```text
PAGE_ALIAS -> Clase POM
```

Uso:

```gherkin
When on page "LOGIN"
```

---

## 11. Element Aliases

```properties
project.business.element.LOGIN.logInButton=FEATURES_LOGIN.LOG_IN_BUTTON
```

Mapea:

```text
PAGE_ALIAS.elementAlias -> logicalId
```

Uso:

```gherkin
And click "logInButton"
```

---

## 12. Valores que debe cambiar el consumidor

```text
project.name
project.applicationName
project.moduleName
project.baseUrl
project.pageBase
project.autodesc.packageName
project.pom.packageName
project.business.packageName
```

---

## 13. Valores que puede dejar por defecto

```text
project.autodesc.outputDirectory=src/test/java
project.pom.outputDirectory=src/test/java
project.business.outputDirectory=src/test/java
project.autodesc.classNameSuffix=_JUNIT_SELENIUM
project.business.classNameSuffix=_JUNIT_SELENIDE
project.selenium.implicitWaitSeconds=60
project.selenide.timeoutMs=60000
project.selenide.pageLoadTimeoutMs=60000
```

---

## 14. Buenas prácticas

```text
✔ Mantener el archivo ordenado por secciones
✔ No dejar valores CAMBIAR_SEGUN_PROYECTO
✔ Revisar aliases antes de copiar
✔ Usar rutas relativas
✔ Usar / en rutas
✔ No subir secretos
✔ Mantener UTF-8
```
