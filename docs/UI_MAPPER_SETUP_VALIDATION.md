# UI_MAPPER — Setup Validation

## 1. Objetivo

Este documento explica el proceso de validación de setup del proyecto consumidor de `UI_MAPPER`.

El objetivo de `RunSetupValidation` es revisar que el archivo de contrato:

```text
src/test/resources/ui-mapper-project.properties
```

contenga la configuración mínima necesaria para ejecutar correctamente las etapas del pipeline.

---

## 2. Runner

```text
RunSetupValidation
```

Modos disponibles:

```text
AUTODISCOVERY
BUSINESS
```

También puede ejecutarse de forma integrada mediante:

```text
RunFullPipeline AUTODISCOVERY
RunFullPipeline BUSINESS
```

---

## 3. Modo AUTODISCOVERY

Ejecutar:

```text
RunSetupValidation AUTODISCOVERY
```

Valida lo necesario para generar scripts de autodescubrimiento.

### Propiedades principales

```properties
project.name
project.applicationName
project.moduleName
project.baseUrl
project.pageBase
project.features.directory
project.selector.definitionFile
project.autodesc.packageName
project.autodesc.outputDirectory
project.autodesc.classNameSuffix
project.selenium.implicitWaitSeconds
```

### Qué revisa

```text
✔ Datos base del proyecto
✔ URL base válida
✔ Directorio de features de Autodiscovery
✔ Ruta del archivo de selectores
✔ Package Java de Autodiscovery
✔ Directorio de salida
✔ Timeout Selenium mayor a cero
✔ Valores que no parezcan plantilla
```

---

## 4. Modo BUSINESS

Ejecutar:

```text
RunSetupValidation BUSINESS
```

Valida lo necesario para generar scripts de negocio.

### Propiedades principales

```properties
project.business.features.directory
project.selector.definitionFile
project.pom.packageName
project.pom.outputDirectory
project.business.packageName
project.business.outputDirectory
project.business.classNameSuffix
project.selenide.timeoutMs
project.selenide.pageLoadTimeoutMs
project.business.page.*
project.business.element.*
```

### Qué revisa

```text
✔ Directorio de features Business
✔ Existencia de archivos .feature
✔ SELECTOR_REPOSITORY.properties existente y con contenido
✔ Packages Java válidos
✔ Directorios de salida válidos
✔ Timeouts Selenide mayores a cero
✔ Existencia de page aliases
✔ Existencia de element aliases
```

---

## 5. Interpretación del reporte

El reporte tiene tres secciones:

```text
[OK]
[WARNINGS]
[ERRORES]
```

### [OK]

Validaciones correctas.

Ejemplo:

```text
✔ project.baseUrl = http://localhost:4200
✔ project.baseUrl tiene formato URL válido.
```

### [WARNINGS]

Observaciones no bloqueantes.

Ejemplo:

```text
⚠ El archivo de selectores aún no existe. Se recomienda crearlo vacío.
```

### [ERRORES]

Problemas bloqueantes.

Ejemplo:

```text
✖ No existen project.business.page.*. Business requiere aliases de página.
```

---

## 6. Uso en Eclipse

1. Abrir `RunSetupValidation`.
2. Ir a `Run > Run Configurations`.
3. Seleccionar la clase como Java Application.
4. En `Program arguments` colocar:

```text
AUTODISCOVERY
```

o:

```text
BUSINESS
```

5. Ejecutar.

---

## 7. Uso desde línea de comandos

Ejemplo:

```bash
mvn exec:java -Dexec.mainClass="com.miempresa.demo.runner.RunSetupValidation" -Dexec.args="AUTODISCOVERY"
```

```bash
mvn exec:java -Dexec.mainClass="com.miempresa.demo.runner.RunSetupValidation" -Dexec.args="BUSINESS"
```

---

## 8. Errores comunes

### Falta `ui-mapper-project.properties`

Solución:

```text
Crear src/test/resources/ui-mapper-project.properties
```

---

### `project.baseUrl` inválida

Incorrecto:

```properties
project.baseUrl=localhost:4200
```

Correcto:

```properties
project.baseUrl=http://localhost:4200
```

---

### `SELECTOR_REPOSITORY.properties` vacío

Causa:

No se han ejecutado los scripts de Autodiscovery.

Solución:

```text
RunFullPipeline AUTODISCOVERY
Ejecutar scripts *_JUNIT_SELENIUM
```

---

### No existen aliases

Causa:

No se copiaron los aliases generados al contrato.

Solución:

```text
RunFullPipeline BUSINESS_PREPARE
Copiar project.business.page.*
Copiar project.business.element.*
```

---

## 9. Buenas prácticas

```text
✔ Validar AUTODISCOVERY antes de generar scripts Selenium
✔ Validar BUSINESS antes de generar scripts Selenide
✔ Revisar warnings
✔ No ignorar errores
✔ No dejar valores plantilla
✔ Mantener el contrato ordenado
```

---

## 10. Relación con RunFullPipeline

`RunFullPipeline` ya integra parte de estas validaciones:

```text
RunFullPipeline AUTODISCOVERY → valida AUTODISCOVERY
RunFullPipeline BUSINESS      → valida BUSINESS
```

`RunSetupValidation` sigue siendo útil para diagnóstico puntual.
