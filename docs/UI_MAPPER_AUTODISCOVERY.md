# UI_MAPPER — Autodiscovery

## 1. Objetivo

Autodiscovery permite recorrer una aplicación web desde un archivo Gherkin y generar scripts Selenium capaces de capturar elementos de UI.

El resultado principal es alimentar:

```text
SELECTOR_REPOSITORY.properties
```

---

## 2. Ubicación de features

```text
src/test/resources/features/autodiscovery
```

---

## 3. Runner recomendado

```text
RunFullPipeline AUTODISCOVERY
```

También existe runner individual:

```text
RunAutodescubrimiento
```

---

## 4. Sintaxis soportada

### Abrir página

```gherkin
Given open page "/pages"
```

Abre:

```text
project.baseUrl + /pages
```

---

### Esperar

```gherkin
And wait 2 seconds
```

---

### Click con selector explícito

```gherkin
And click "xpath://button[normalize-space(.)='Login']"
```

También puede usarse CSS si el generador lo soporta:

```gherkin
And click "css:#login"
```

---

### Capturar página

```gherkin
And capture page "LOGIN"
```

Esto invoca el recorder para registrar elementos visibles.

---

## 5. Ejemplo completo

```gherkin
Feature: Autodescubrimiento Login

  Scenario: Descubrir pantalla de login

    Given open page "/login"
    And wait 2 seconds
    And capture page "LOGIN"
```

---

## 6. Resultado generado

Después de ejecutar:

```text
RunFullPipeline AUTODISCOVERY
```

se generan clases:

```text
*_JUNIT_SELENIUM.java
```

Estas clases deben ejecutarse manualmente.

---

## 7. Ejecución de scripts generados

Ejecutar:

```text
*_JUNIT_SELENIUM
```

Resultado:

```text
src/test/resources/selectors/SELECTOR_REPOSITORY.properties
```

---

## 8. Qué guarda el selector repository

Ejemplo:

```properties
LOGIN.USERNAME_INPUT.primary=css:#username
LOGIN.USERNAME_INPUT.alt1=xpath://input[@name='username']
LOGIN.USERNAME_INPUT.type=INPUT
LOGIN.USERNAME_INPUT.page=login
LOGIN.USERNAME_INPUT.scope=xpath://form
```

---

## 9. Buenas prácticas

```text
✔ Capturar después de abrir página
✔ Capturar después de navegar a una nueva sección
✔ Usar nombres de captura claros
✔ Separar un feature por pantalla o flujo de descubrimiento
✔ Evitar capturas innecesarias
✔ Revisar SELECTOR_REPOSITORY.properties después de ejecutar
```

---

## 10. Errores comunes

### No se genera repository

Causa:

No se ejecutaron los scripts `*_JUNIT_SELENIUM`.

---

### No aparece un elemento

Causa posible:

El elemento no estaba visible al momento de captura.

Solución:

Agregar wait o navegar/interactuar antes de capturar.

---

### Selector incorrecto

Causa:

La UI cambió o el elemento se capturó en estado incorrecto.

Solución:

Reejecutar Autodiscovery.
