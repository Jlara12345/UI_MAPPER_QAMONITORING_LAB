# UI_MAPPER — Business DSL

## 1. Objetivo

El Business DSL permite escribir flujos funcionales en Gherkin y generar scripts Java/Selenide automáticamente.

---

## 2. Ubicación de features

```text
src/test/resources/features/business
```

---

## 3. Runner recomendado

```text
RunFullPipeline BUSINESS
```

También existe runner individual:

```text
RunBusiness
```

---

## 4. Prerrequisitos

Antes de generar scripts Business:

```text
✔ SELECTOR_REPOSITORY.properties con contenido
✔ POM generadas
✔ Aliases copiados a ui-mapper-project.properties
✔ Features Business creados
```

---

## 5. Abrir aplicación

```gherkin
Given open app
```

Abre:

```text
project.baseUrl
```

---

## 6. Seleccionar página activa

```gherkin
When on page "LOGIN"
```

Debe existir:

```properties
project.business.page.LOGIN=FeaturesAuthLoginPage
```

---

## 7. Click

```gherkin
And click "logInButton"
```

Debe existir:

```properties
project.business.element.LOGIN.logInButton=FEATURES_LOGIN.LOG_IN_BUTTON
```

---

## 8. Type

```gherkin
And type "demo@test.com" into "emailInput"
```

---

## 9. Wait

```gherkin
And wait 2 seconds
```

---

## 10. Wait for element

```gherkin
And wait for element "logInButton" for 10 seconds
```

---

## 11. Verify visible

```gherkin
Then verify element "logInButton" is visible
```

---

## 12. Verify text

```gherkin
Then verify element "messageLabel" contains text "Bienvenido"
```

O:

```gherkin
Then verify text "Bienvenido" in "messageLabel"
```

---

## 13. Verify URL

```gherkin
Then verify current url contains "/home"
```

O con regex:

```gherkin
Then verify current url matches ".*/home.*"
```

---

## 14. Ejemplo completo

```gherkin
Feature: Login

  Scenario: Login exitoso

    Given open app
    When on page "LOGIN"
    And type "demo@test.com" into "emailInput"
    And type "123456" into "passwordInput"
    And click "logInButton"
    Then verify current url contains "/home"
```

---

## 15. Regla importante de página activa

Si estás en:

```gherkin
When on page "LOGIN"
```

Entonces los elementos deben existir bajo:

```properties
project.business.element.LOGIN.*
```

Si usas un elemento de otra página, el script puede fallar.

---

## 16. Buenas prácticas

```text
✔ Usar aliases legibles
✔ Mantener un page alias activo correcto
✔ Escribir features pequeños y claros
✔ Validar aliases antes de generar Business
✔ Evitar nombres técnicos en Gherkin
✔ Usar waits explícitos cuando la UI tenga carga dinámica
```

---

## 17. Errores comunes

### Método no existe en POM

Causa:

El elemento pertenece a otra página o el alias no está bien mapeado.

Solución:

Revisar:

```properties
project.business.element.PAGE_ALIAS.elementAlias
```

---

### No existe alias

Causa:

No se copió el alias al contrato.

Solución:

Ejecutar `BUSINESS_PREPARE` y copiar aliases.

---

### URL no coincide

Causa:

La navegación no ocurrió o la validación espera una ruta incorrecta.

Solución:

Agregar wait o ajustar la validación.
