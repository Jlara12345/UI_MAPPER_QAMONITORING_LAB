# UI_MAPPER — Business Alias Generation

## 1. Objetivo

Este documento explica cómo generar aliases de negocio para usar en los archivos `.feature` Business.

El proceso se ejecuta con:

```text
RunBusinessAliasGeneration
```

o de forma integrada con:

```text
RunFullPipeline BUSINESS_PREPARE
```

---

## 2. Qué genera

Genera el archivo:

```text
src/test/resources/ui-mapper-business-aliases.generated.properties
```

Este archivo contiene sugerencias para:

```properties
project.business.page.*
project.business.element.*
```

---

## 3. Prerrequisitos

Antes de generar aliases deben existir:

```text
✔ SELECTOR_REPOSITORY.properties con contenido
✔ Clases POM generadas
✔ ui-mapper-project.properties configurado
```

Propiedades mínimas:

```properties
project.pom.packageName
project.pom.outputDirectory
project.selector.definitionFile
```

---

## 4. Page Aliases

Un page alias conecta un nombre funcional usado en Gherkin con una clase POM real.

Ejemplo:

```properties
project.business.page.LOGIN=FeaturesAuthLoginPage
```

Uso en Gherkin:

```gherkin
When on page "LOGIN"
```

---

## 5. Element Aliases

Un element alias conecta un nombre funcional usado en Gherkin con un logicalId del repositorio de selectores.

Ejemplo:

```properties
project.business.element.LOGIN.logInButton=FEATURES_LAYOUT_AUTH_LOGIN.LOG_IN_BUTTON
```

Uso en Gherkin:

```gherkin
And click "logInButton"
```

---

## 6. Cómo se genera el mapeo

El generador cruza información entre POM y selectores usando:

```text
POM.PAGE_ID == SELECTOR_REPOSITORY.<logicalId>.page
```

Flujo:

```text
Leer clases POM
        ↓
Extraer PAGE_ID
        ↓
Leer SELECTOR_REPOSITORY.properties
        ↓
Buscar logicalIds con el mismo .page
        ↓
Generar page aliases y element aliases
```

---

## 7. Agrupación por SECTION / CARD

Cuando el selector tiene `.scope`, el generador agrupa elementos por sección o card.

Ejemplo:

```properties
FEATURES_FORMS.EMAIL_INPUT.scope=xpath://nb-card[.//*[normalize-space(.)='Inline form']]
```

Se muestra como:

```text
PAGE_ALIAS
   └── Inline Form
          └── ELEMENT_ALIASES
```

Esto facilita localizar elementos en páginas grandes.

---

## 8. Uso del archivo generado

1. Abrir:

```text
src/test/resources/ui-mapper-business-aliases.generated.properties
```

2. Revisar page aliases.
3. Revisar element aliases.
4. Ajustar nombres si es necesario.
5. Copiar al contrato:

```text
src/test/resources/ui-mapper-project.properties
```

los bloques:

```properties
project.business.page.*
project.business.element.*
```

6. Ejecutar:

```text
RunFullPipeline BUSINESS
```

---

## 9. Qué revisar antes de copiar

```text
✔ PAGE_ALIAS claro
✔ Clase POM correcta
✔ Elementos bajo la página correcta
✔ Secciones/cards útiles
✔ Aliases legibles para escribir Gherkin
✔ Sin aliases demasiado técnicos
```

---

## 10. Errores comunes

### No existen POM

Solución:

```text
RunFullPipeline BUSINESS_PREPARE
```

o ejecutar antes:

```text
RunPomGeneration
```

---

### No existe SELECTOR_REPOSITORY.properties

Solución:

```text
RunFullPipeline AUTODISCOVERY
Ejecutar scripts *_JUNIT_SELENIUM
```

---

### Elemento aparece en página incorrecta

Revisar:

```properties
<logicalId>.page
```

El cruce depende de:

```text
POM.PAGE_ID == logicalId.page
```

---

### Alias poco legible

Ajustar manualmente antes de copiar al contrato.

Ejemplo:

```properties
project.business.element.LOGIN.twitterLink=FEATURES_LOGIN.HTTPS_TWITTER_COM_LINK
```

---

## 11. Buenas prácticas

```text
✔ Ejecutar después de generar POM
✔ Revisar antes de copiar
✔ Mantener aliases funcionales
✔ Agrupar por página
✔ Regenerar si cambia SELECTOR_REPOSITORY.properties
✔ Regenerar si cambian las POM
```
