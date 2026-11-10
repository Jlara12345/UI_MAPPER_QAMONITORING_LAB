# UI_MAPPER — DSL Quick Start

## 1. Objetivo

Esta guía permite arrancar rápidamente un proyecto consumidor de `UI_MAPPER` usando Gherkin para generar automatización.

---

## 2. Flujo rápido

```text
1. Configurar ui-mapper-project.properties
2. Crear features de Autodiscovery
3. Ejecutar RunFullPipeline AUTODISCOVERY
4. Ejecutar scripts *_JUNIT_SELENIUM
5. Ejecutar RunFullPipeline BUSINESS_PREPARE
6. Copiar aliases
7. Crear features Business
8. Ejecutar RunFullPipeline BUSINESS
9. Ejecutar scripts *_JUNIT_SELENIDE
```

---

## 3. Crear feature de Autodiscovery

Ubicación:

```text
src/test/resources/features/autodiscovery
```

Ejemplo:

```gherkin
Feature: Autodescubrimiento Home

  Scenario: Descubrir página principal

    Given open page "/"
    And wait 2 seconds
    And capture page "HOME"
```

---

## 4. Ejecutar Autodiscovery

Runner:

```text
RunFullPipeline
```

Argumento:

```text
AUTODISCOVERY
```

Después ejecutar manualmente los scripts generados:

```text
*_JUNIT_SELENIUM
```

Resultado:

```text
SELECTOR_REPOSITORY.properties
```

---

## 5. Preparar Business

Ejecutar:

```text
RunFullPipeline BUSINESS_PREPARE
```

Esto genera:

```text
Clases POM
ui-mapper-business-aliases.generated.properties
```

Copiar los aliases generados al contrato:

```text
ui-mapper-project.properties
```

---

## 6. Crear feature Business

Ubicación:

```text
src/test/resources/features/business
```

Ejemplo:

```gherkin
Feature: Flujo Login

  Scenario: Validar navegación a login

    Given open app
    When on page "HOME"
    And wait for element "loginLink" for 10 seconds
    Then verify element "loginLink" is visible
    And click "loginLink"
    Then verify current url contains "/login"
```

---

## 7. Generar scripts Business

Ejecutar:

```text
RunFullPipeline BUSINESS
```

Resultado:

```text
*_JUNIT_SELENIDE.java
```

---

## 8. Ejecutar scripts Business

Ejecutar manualmente las clases generadas:

```text
*_JUNIT_SELENIDE
```

---

## 9. DSL Autodiscovery básico

```gherkin
Given open page "/ruta"
And wait 2 seconds
And click "xpath://button[normalize-space(.)='Aceptar']"
And capture page "PAGE_ALIAS"
```

---

## 10. DSL Business básico

```gherkin
Given open app
When on page "PAGE_ALIAS"
And wait for element "elementAlias" for 10 seconds
Then verify element "elementAlias" is visible
And click "elementAlias"
And type "valor" into "inputAlias"
Then verify current url contains "/ruta"
```

---

## 11. Regla clave

El elemento usado en Business debe pertenecer al page alias activo:

```gherkin
When on page "LOGIN"
And click "logInButton"
```

Debe existir:

```properties
project.business.element.LOGIN.logInButton=...
```

---

## 12. Resultado esperado

Al final:

```text
✔ Selectores generados
✔ POM generadas
✔ Aliases configurados
✔ Scripts Business generados
✔ Flujos ejecutables
```
