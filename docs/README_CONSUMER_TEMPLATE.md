# UI_MAPPER Consumer Template

## 1. Objetivo

Este documento describe el proyecto **ui-mapper-consumer-template**, una plantilla base para crear proyectos consumidores del framework `UI_MAPPER`.

El template permite iniciar rápidamente un proyecto de automatización funcional basado en:

- Gherkin
- Autodescubrimiento de UI
- Repositorio central de selectores
- Generación automática de clases POM
- Generación automática de aliases de negocio
- Generación automática de scripts Java/Selenide
- Pipeline de generación simplificado con `RunFullPipeline`

La idea es que un nuevo proyecto consumidor pueda arrancar con una estructura estándar y ejecutar el flujo completo sin tener que crear manualmente runners, carpetas, archivos de configuración o documentación base.

---

## 2. ¿Qué problema resuelve?

En un proyecto tradicional de automatización, normalmente se requiere crear manualmente:

- Estructura Maven
- Configuración de dependencias
- Runners
- Page Objects
- Selectores
- Scripts Selenium/Selenide
- Mapeos entre elementos y páginas
- Documentación operativa

Con este template, el consumidor inicia con una base lista para ejecutar:

```text
Configuración → Autodiscovery → Selectores → POM → Aliases → Business → Ejecución
```

Esto reduce tiempo de arranque, errores de configuración y dependencia de perfiles técnicos especializados.

---

## 3. Prerrequisitos

Antes de usar este template, asegúrate de contar con:

- Java 17 o superior
- Maven 3.8+
- Eclipse IDE o IDE equivalente
- Acceso a GitHub Packages
- Configuración de `settings.xml` para resolver dependencias privadas
- Una aplicación web disponible para ejecutar pruebas, por ejemplo:

```text
http://localhost:4200
```

---

## 4. Configuración de GitHub Packages

El proyecto consumidor necesita descargar la dependencia del framework:

```xml
<dependency>
    <groupId>com.qs</groupId>
    <artifactId>ui-mapper</artifactId>
    <version>${ui.mapper.version}</version>
</dependency>
```

Para resolver esta dependencia, se debe configurar el archivo:

```text
~/.m2/settings.xml
```

Ejemplo:

```xml
<settings>
  <servers>
    <server>
      <id>github</id>
      <username>TU_USUARIO_GITHUB</username>
      <password>TU_TOKEN_GITHUB</password>
    </server>

    <server>
      <id>github-qs-toolkit</id>
      <username>TU_USUARIO_GITHUB</username>
      <password>TU_TOKEN_GITHUB</password>
    </server>
  </servers>
</settings>
```

El token debe tener permiso:

```text
read:packages
```

> Importante: nunca subir `settings.xml` ni tokens al repositorio.

---

## 5. Estructura oficial del proyecto

```text
ui-mapper-consumer-template/
│
├── pom.xml
│
├── README.md
│
├── docs/
│   ├── README_CONSUMER_TEMPLATE.md
│   ├── PIPELINE_GENERACION.md
│   ├── UI_MAPPER_SETUP_VALIDATION.md
│   ├── UI_MAPPER_BUSINESS_ALIAS_GENERATION.md
│   ├── DSL_QUICK_START.md
│   ├── UI_MAPPER_AUTODISCOVERY.md
│   ├── UI_MAPPER_BUSINESS_DSL.md
│   ├── UI_MAPPER_PROJECT_CONTRACT.md
│   ├── SELECTOR_REPOSITORY.md
│   ├── GITHUB_PACKAGES_SETUP.md
│   └── TROUBLESHOOTING.md
│
└── src/
    └── test/
        ├── java/
        │   └── com/
        │       └── miempresa/
        │           └── demo/
        │               └── runner/
        │                   ├── RunFullPipeline.java
        │                   ├── RunSetupValidation.java
        │                   ├── RunAutodescubrimiento.java
        │                   ├── RunPomGeneration.java
        │                   ├── RunBusinessAliasGeneration.java
        │                   └── RunBusiness.java
        │
        └── resources/
            ├── ui-mapper-project.properties
            │
            ├── selectors/
            │   └── SELECTOR_REPOSITORY.properties
            │
            └── features/
                ├── autodiscovery/
                │   └── AUTO_DESCUBRIMIENTO_EJEMPLO.feature
                │
                └── business/
                    └── BUSINESS_EJEMPLO.feature
```

---

## 6. Archivos principales

### 6.1 `pom.xml`

Define el proyecto Maven consumidor y la dependencia hacia el framework `UI_MAPPER`.

Debe incluir:

```xml
<dependency>
    <groupId>com.qs</groupId>
    <artifactId>ui-mapper</artifactId>
    <version>${ui.mapper.version}</version>
</dependency>
```

También debe declarar los repositorios de GitHub Packages necesarios para resolver:

- `UI_MAPPER`
- `qs-toolkit`

---

### 6.2 `ui-mapper-project.properties`

Es el contrato principal entre el proyecto consumidor y el framework.

Ubicación:

```text
src/test/resources/ui-mapper-project.properties
```

Aquí se configuran:

- Identidad del proyecto
- URL base
- Directorios de features
- Repositorio de selectores
- Paquetes Java de salida
- Timeouts
- Page aliases
- Element aliases

Este archivo es obligatorio.

---

### 6.3 `SELECTOR_REPOSITORY.properties`

Repositorio central de selectores descubiertos por `UI_MAPPER`.

Ubicación:

```text
src/test/resources/selectors/SELECTOR_REPOSITORY.properties
```

Puede iniciar vacío.

Será poblado o actualizado cuando se ejecuten los scripts generados de autodescubrimiento.

---

### 6.4 Features de Autodiscovery

Ubicación:

```text
src/test/resources/features/autodiscovery
```

Aquí se colocan los archivos `.feature` que describen cómo navegar por la aplicación para descubrir páginas, cards, formularios y elementos.

---

### 6.5 Features de Business

Ubicación:

```text
src/test/resources/features/business
```

Aquí se colocan los archivos `.feature` que describen flujos funcionales de negocio usando aliases.

---

## 7. Runners incluidos

### 7.1 `RunFullPipeline`

Runner principal recomendado para operar el pipeline.

Modos:

```text
AUTODISCOVERY
BUSINESS_PREPARE
BUSINESS
```

Uso:

```text
RunFullPipeline AUTODISCOVERY
RunFullPipeline BUSINESS_PREPARE
RunFullPipeline BUSINESS
```

Este runner reduce el número de ejecuciones manuales y guía al usuario por las etapas del pipeline.

---

### 7.2 `RunSetupValidation`

Runner individual para validar el setup del proyecto consumidor.

Modos:

```text
AUTODISCOVERY
BUSINESS
```

Uso:

```text
RunSetupValidation AUTODISCOVERY
RunSetupValidation BUSINESS
```

Este runner sigue siendo útil para diagnóstico o validaciones puntuales.

---

### 7.3 `RunAutodescubrimiento`

Genera scripts Java/Selenium de autodescubrimiento a partir de los `.feature` ubicados en:

```text
src/test/resources/features/autodiscovery
```

En el flujo actual, esta acción ya queda integrada en:

```text
RunFullPipeline AUTODISCOVERY
```

---

### 7.4 `RunPomGeneration`

Genera clases POM a partir del archivo:

```text
SELECTOR_REPOSITORY.properties
```

En el flujo actual, esta acción ya queda integrada en:

```text
RunFullPipeline BUSINESS_PREPARE
```

---

### 7.5 `RunBusinessAliasGeneration`

Genera automáticamente:

```text
src/test/resources/ui-mapper-business-aliases.generated.properties
```

Este archivo contiene aliases sugeridos para:

```properties
project.business.page.*
project.business.element.*
```

En el flujo actual, esta acción ya queda integrada en:

```text
RunFullPipeline BUSINESS_PREPARE
```

---

### 7.6 `RunBusiness`

Genera scripts Java/Selenide de negocio a partir de los `.feature` ubicados en:

```text
src/test/resources/features/business
```

En el flujo actual, esta acción ya queda integrada en:

```text
RunFullPipeline BUSINESS
```

---

## 8. Pipeline de Generación oficial

### 8.1 Versión resumida

```text
Configurar contrato
        │
        ▼
RunFullPipeline AUTODISCOVERY
        │
        ▼
Ejecutar scripts autodiscovery generados
        │
        ▼
RunFullPipeline BUSINESS_PREPARE
        │
        ▼
Copiar/validar aliases
        │
        ▼
RunFullPipeline BUSINESS
        │
        ▼
Ejecutar scripts Business generados
```

---

### 8.2 Versión detallada

```text
0. Configurar ui-mapper-project.properties
        │
        ▼
1. RunFullPipeline AUTODISCOVERY
        │
        ▼
2. Ejecutar manualmente scripts *_JUNIT_SELENIUM
        │
        ▼
SELECTOR_REPOSITORY.properties generado/actualizado
        │
        ▼
3. RunFullPipeline BUSINESS_PREPARE
        │
        ▼
Clases POM + aliases generados
        │
        ▼
4. Copiar/validar aliases en ui-mapper-project.properties
        │
        ▼
5. RunFullPipeline BUSINESS
        │
        ▼
Scripts Java/Selenide de negocio generados
        │
        ▼
6. Ejecutar manualmente scripts *_JUNIT_SELENIDE
```

---

## 9. Primer uso del template

### Paso 1 — Crear proyecto desde template

Desde GitHub:

```text
Use this template
```

Crear un nuevo repositorio, por ejemplo:

```text
ui-mapper-angular-consumer
```

---

### Paso 2 — Clonar el proyecto

```bash
git clone https://github.com/TU_USUARIO/ui-mapper-angular-consumer.git
cd ui-mapper-angular-consumer
```

---

### Paso 3 — Importar en Eclipse

```text
File > Import > Maven > Existing Maven Projects
```

Seleccionar la carpeta del proyecto clonado.

---

### Paso 4 — Actualizar identidad Maven

Editar `pom.xml`.

Ejemplo:

```xml
<groupId>com.qs.demo</groupId>
<artifactId>ui-mapper-angular-consumer</artifactId>
<version>1.0.0</version>

<name>ui-mapper-angular-consumer</name>
```

---

### Paso 5 — Configurar contrato

Editar:

```text
src/test/resources/ui-mapper-project.properties
```

Actualizar al menos:

```properties
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

### Paso 6 — Crear features de Autodiscovery

Crear o editar archivos en:

```text
src/test/resources/features/autodiscovery
```

Ejemplo:

```gherkin
Feature: Autodescubrimiento ejemplo

  Scenario: Descubrir página principal

    Given open page "/"
    And wait 2 seconds
    And capture page "HOME"
```

---

### Paso 7 — Ejecutar `RunFullPipeline AUTODISCOVERY`

Ejecutar:

```text
RunFullPipeline
```

Argumento:

```text
AUTODISCOVERY
```

Resultado esperado:

```text
Scripts Java/Selenium de autodescubrimiento generados
```

---

### Paso 8 — Ejecutar scripts generados de Autodiscovery

Ejecutar manualmente las clases generadas:

```text
*_JUNIT_SELENIUM
```

Resultado esperado:

```text
SELECTOR_REPOSITORY.properties actualizado
```

---

### Paso 9 — Ejecutar `RunFullPipeline BUSINESS_PREPARE`

Ejecutar:

```text
RunFullPipeline
```

Argumento:

```text
BUSINESS_PREPARE
```

Resultado esperado:

```text
Clases POM generadas
ui-mapper-business-aliases.generated.properties generado
```

---

### Paso 10 — Copiar y validar aliases

Abrir:

```text
src/test/resources/ui-mapper-business-aliases.generated.properties
```

Copiar al contrato:

```text
src/test/resources/ui-mapper-project.properties
```

los bloques:

```properties
project.business.page.*
project.business.element.*
```

---

### Paso 11 — Crear features Business

Crear o editar archivos en:

```text
src/test/resources/features/business
```

Ejemplo:

```gherkin
Feature: Flujo Login

  Scenario: Validar acceso a login

    Given open app
    When on page "HOME"
    And wait for element "loginLink" for 10 seconds
    Then verify element "loginLink" is visible
    And click "loginLink"
    Then verify current url contains "/login"
```

---

### Paso 12 — Ejecutar `RunFullPipeline BUSINESS`

Ejecutar:

```text
RunFullPipeline
```

Argumento:

```text
BUSINESS
```

Resultado esperado:

```text
Scripts Java/Selenide de negocio generados
```

---

### Paso 13 — Ejecutar scripts Business generados

Ejecutar manualmente las clases generadas:

```text
*_JUNIT_SELENIDE
```

Resultado esperado:

```text
Flujos de negocio ejecutados correctamente
```

---

## 10. Documentación incluida en `docs/`

Toda la documentación funcional y operativa del template debe estar centralizada en:

```text
docs/
```

Documentos recomendados:

```text
README_CONSUMER_TEMPLATE.md
PIPELINE_GENERACION.md
UI_MAPPER_SETUP_VALIDATION.md
UI_MAPPER_BUSINESS_ALIAS_GENERATION.md
DSL_QUICK_START.md
UI_MAPPER_AUTODISCOVERY.md
UI_MAPPER_BUSINESS_DSL.md
UI_MAPPER_PROJECT_CONTRACT.md
SELECTOR_REPOSITORY.md
GITHUB_PACKAGES_SETUP.md
TROUBLESHOOTING.md
```

---

## 11. Estado de documentación

### Documentación principal

```text
[ ] docs/README_CONSUMER_TEMPLATE.md
```

Debe explicar:

- Qué es el template
- Para qué sirve
- Qué problema resuelve
- Prerrequisitos
- Estructura del proyecto
- Archivos principales
- Runners disponibles
- Pipeline de Generación con `RunFullPipeline`
- Primer uso paso a paso
- Buenas prácticas
- Checklist rápido para nuevo consumidor
- Referencias a docs internas

---

### Documentación del Pipeline

```text
[ ] docs/PIPELINE_GENERACION.md
```

Debe incluir:

- Objetivo del pipeline
- Diagrama Mermaid
- Versión lineal del flujo
- Argumentos de `RunFullPipeline`
- Acciones manuales obligatorias
- Entradas y salidas de cada etapa
- Errores comunes
- Resultado esperado

---

### Documentación de Setup Validation

```text
[ ] docs/UI_MAPPER_SETUP_VALIDATION.md
```

Debe incluir:

- Objetivo de `RunSetupValidation`
- Modos `AUTODISCOVERY` y `BUSINESS`
- Qué valida cada modo
- Cómo ejecutarlo en Eclipse
- Cómo ejecutarlo por línea de comandos
- Interpretación del reporte
- Errores comunes
- Buenas prácticas

---

### Documentación de Business Alias Generation

```text
[ ] docs/UI_MAPPER_BUSINESS_ALIAS_GENERATION.md
```

Debe incluir:

- Objetivo de `RunBusinessAliasGeneration`
- Prerrequisitos
- Qué archivo genera
- Qué son los Page Aliases
- Qué son los Element Aliases
- Cómo cruza `POM.PAGE_ID` con `selector.page`
- Agrupación por `SECTION / CARD`
- Qué revisar antes de copiar aliases
- Errores comunes
- Buenas prácticas

---

### Documentación funcional del DSL

```text
[ ] docs/DSL_QUICK_START.md
[ ] docs/UI_MAPPER_AUTODISCOVERY.md
[ ] docs/UI_MAPPER_BUSINESS_DSL.md
```

Debe incluir:

- Sintaxis soportada
- Ejemplos mínimos
- Ejemplos completos
- Relación con selectores, POM y aliases
- Errores comunes

---

### Documentación técnica del consumidor

```text
[ ] docs/UI_MAPPER_PROJECT_CONTRACT.md
[ ] docs/SELECTOR_REPOSITORY.md
[ ] docs/GITHUB_PACKAGES_SETUP.md
[ ] docs/TROUBLESHOOTING.md
```

Debe incluir:

- Contrato del proyecto
- Repositorio de selectores
- Configuración Maven/GitHub Packages
- Errores frecuentes y soluciones

---

## 12. Checklist rápido para nuevo proyecto consumidor

```text
[ ] Crear/copiar proyecto desde ui-mapper-consumer-template
[ ] Actualizar groupId/artifactId/name en pom.xml
[ ] Validar acceso a GitHub Packages
[ ] Ejecutar mvn clean install
[ ] Actualizar ui-mapper-project.properties
[ ] Crear features de autodiscovery
[ ] Ejecutar RunFullPipeline AUTODISCOVERY
[ ] Ejecutar scripts generados de autodiscovery
[ ] Validar que SELECTOR_REPOSITORY.properties tenga contenido
[ ] Ejecutar RunFullPipeline BUSINESS_PREPARE
[ ] Revisar ui-mapper-business-aliases.generated.properties
[ ] Copiar aliases válidos a ui-mapper-project.properties
[ ] Crear features de negocio
[ ] Ejecutar RunFullPipeline BUSINESS
[ ] Ejecutar scripts de negocio generados
```

---

## 13. Buenas prácticas

- Usar `RunFullPipeline` como flujo principal.
- Mantener los runners individuales como apoyo o diagnóstico.
- No ejecutar `BUSINESS_PREPARE` antes de ejecutar scripts de autodiscovery.
- No ejecutar `BUSINESS` antes de copiar aliases.
- Revisar aliases generados antes de copiarlos.
- Mantener `ui-mapper-project.properties` ordenado por página.
- Mantener separados features de autodiscovery y features de negocio.
- Evitar subir tokens o `settings.xml`.
- Usar archivos `.properties` en UTF-8 y sin caracteres especiales innecesarios.
- No versionar archivos generados si el objetivo es mantener limpio el template.
- Documentar cada flujo funcional agregado al consumidor.

---

## 14. Resultado esperado

Al finalizar el flujo completo, el consumidor debe tener:

```text
✔ SELECTOR_REPOSITORY.properties generado/actualizado
✔ Clases POM generadas
✔ Aliases de negocio generados
✔ Aliases copiados/validados en el contrato
✔ Features Business traducidos a scripts Selenide
✔ Scripts Business ejecutables
```

---

## 15. Valor del template

El template reduce el tiempo de arranque de nuevos proyectos consumidores porque ya incluye:

- Estructura estándar
- Runners puente
- Contrato base
- Repositorio de selectores inicial
- Features ejemplo
- Documentación base
- Pipeline definido
- Flujo simplificado con `RunFullPipeline`

El objetivo final es permitir que un proyecto consumidor avance rápidamente desde:

```text
Gherkin funcional → Automatización ejecutable
```

con menos código manual, menos configuración repetitiva y menor dependencia de perfiles técnicos especializados.
