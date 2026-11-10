# UI_MAPPER — Troubleshooting

## 1. Objetivo

Este documento concentra errores comunes al crear y operar un proyecto consumidor de UI_MAPPER.

---

## 2. Eclipse: Project already exists

### Síntoma

Eclipse indica:

```text
Project ui-mapper-consumer-template already exists
```

### Causa

El proyecto creado desde template conserva metadata de Eclipse o el mismo artifact/name.

### Solución

Editar `pom.xml`:

```xml
<artifactId>nuevo-proyecto</artifactId>
<name>nuevo-proyecto</name>
```

Eliminar metadata si existe:

```bash
rm -f .project
rm -f .classpath
rm -rf .settings
```

Reimportar como Maven Project.

---

## 3. Maven no resuelve UI_MAPPER

### Síntoma

```text
Could not resolve artifact com.qs:ui-mapper
```

### Causa

Falta configuración de GitHub Packages.

### Solución

Validar:

```text
pom.xml repositories
~/.m2/settings.xml
token con read:packages
```

---

## 4. Error 401 Unauthorized

### Causa

Token inválido o sin permisos.

### Solución

Crear PAT con:

```text
read:packages
```

Actualizar `~/.m2/settings.xml`.

---

## 5. SELECTOR_REPOSITORY.properties existe pero falla

### Síntoma

```text
MalformedInputException
```

### Causa

Archivo con encoding incorrecto o caracteres especiales.

### Solución

Recrear archivo en UTF-8:

```properties
# UI_MAPPER - SELECTOR REPOSITORY
# Initial empty selector repository.
```

---

## 6. No se generan POM

### Causa probable

`SELECTOR_REPOSITORY.properties` está vacío o no tiene logicalIds.

### Solución

Ejecutar:

```text
RunFullPipeline AUTODISCOVERY
Ejecutar scripts *_JUNIT_SELENIUM
RunFullPipeline BUSINESS_PREPARE
```

---

## 7. No se generan aliases

### Causas posibles

```text
No existen POM
No existe SELECTOR_REPOSITORY.properties
project.pom.packageName incorrecto
project.pom.outputDirectory incorrecto
```

### Solución

Validar configuración y ejecutar:

```text
RunFullPipeline BUSINESS_PREPARE
```

---

## 8. No existen aliases en Business

### Síntoma

```text
No existen project.business.page.*
No existen project.business.element.*
```

### Causa

No se copiaron aliases generados al contrato.

### Solución

Abrir:

```text
ui-mapper-business-aliases.generated.properties
```

Copiar al contrato:

```properties
project.business.page.*
project.business.element.*
```

---

## 9. Elemento no existe en la página actual

### Síntoma

El código generado intenta llamar un método que no existe en la POM actual.

### Causa

El feature tiene un `When on page` incorrecto.

Ejemplo incorrecto:

```gherkin
When on page "MODAL_OVERLAYS_WINDOW"
And click "openDialogWithEscCloseButton"
```

Si el alias está en:

```properties
project.business.element.MODAL_OVERLAYS_DIALOG.openDialogWithEscCloseButton=...
```

Debe usarse:

```gherkin
When on page "MODAL_OVERLAYS_DIALOG"
```

---

## 10. Archivo .feature no encontrado

### Causa

El archivo no está en la carpeta configurada.

Validar:

```properties
project.features.directory
project.business.features.directory
```

---

## 11. No hay archivos .feature

### Síntoma

```text
No se encontraron archivos .feature
```

### Solución

Crear archivos en:

```text
src/test/resources/features/autodiscovery
src/test/resources/features/business
```

---

## 12. Alias poco legible

### Causa

El HTML tiene texto técnico o URLs.

### Solución

Ajustar alias manualmente antes de copiarlo.

Ejemplo:

```properties
project.business.element.LOGIN.twitterLink=FEATURES_LOGIN.HTTPS_TWITTER_COM_LINK
```

---

## 13. Buenas prácticas para evitar errores

```text
✔ Ejecutar el pipeline en orden
✔ Revisar mensajes de RunFullPipeline
✔ No saltar pasos manuales
✔ Mantener contrato ordenado
✔ Revisar aliases antes de copiar
✔ Mantener archivos .properties en UTF-8
✔ Validar Maven antes de importar en Eclipse
