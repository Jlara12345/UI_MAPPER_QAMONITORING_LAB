# UI_MAPPER — Selector Repository

## 1. Objetivo

`SELECTOR_REPOSITORY.properties` es el repositorio central de selectores descubiertos por UI_MAPPER.

Ubicación recomendada:

```text
src/test/resources/selectors/SELECTOR_REPOSITORY.properties
```

---

## 2. Quién lo genera

Se genera o actualiza al ejecutar los scripts de Autodiscovery:

```text
*_JUNIT_SELENIUM
```

Estos scripts son generados por:

```text
RunFullPipeline AUTODISCOVERY
```

---

## 3. Para qué sirve

Sirve como fuente de verdad para:

```text
✔ Generación de POM
✔ Validación de selectores
✔ Selector healing
✔ Generación de aliases
✔ Scripts Business
```

---

## 4. Estructura de logicalId

Ejemplo:

```properties
LOGIN.USERNAME_INPUT.primary=css:#username
LOGIN.USERNAME_INPUT.alt1=xpath://input[@name='username']
LOGIN.USERNAME_INPUT.type=INPUT
LOGIN.USERNAME_INPUT.page=login
LOGIN.USERNAME_INPUT.scope=xpath://form
```

---

## 5. Propiedades principales

### primary

Selector principal.

```properties
LOGIN.USERNAME_INPUT.primary=css:#username
```

### alt1 / alt2

Selectores alternativos.

```properties
LOGIN.USERNAME_INPUT.alt1=xpath://input[@name='username']
```

### type

Tipo de elemento.

```properties
LOGIN.USERNAME_INPUT.type=INPUT
```

### page

PageId donde pertenece el elemento.

```properties
LOGIN.USERNAME_INPUT.page=login
```

### scope

Sección o card asociada.

```properties
LOGIN.USERNAME_INPUT.scope=xpath://form
```

---

## 6. Relación con POM

Las POM se generan a partir del repository.

Cada clase POM tiene un `PAGE_ID` que se cruza contra:

```text
logicalId.page
```

---

## 7. Relación con aliases

BusinessAliasGeneration usa:

```text
POM.PAGE_ID == logicalId.page
```

para generar:

```properties
project.business.element.PAGE_ALIAS.elementAlias=logicalId
```

---

## 8. Archivo vacío en template

En un proyecto nuevo puede iniciar vacío o con comentarios simples.

Recomendado:

```properties
# UI_MAPPER - SELECTOR REPOSITORY
# Initial empty selector repository.
```

---

## 9. Encoding

Mantener el archivo en UTF-8.

Evitar emojis o caracteres especiales innecesarios.

Si aparece:

```text
MalformedInputException
```

recrear el archivo como UTF-8.

---

## 10. Buenas prácticas

```text
✔ No editar manualmente si no es necesario
✔ Versionarlo si se requiere trazabilidad
✔ Regenerar POM si cambia
✔ Regenerar aliases si cambia
✔ Revisar page y scope si hay mapeos incorrectos
```
