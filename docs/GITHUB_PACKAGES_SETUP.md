# UI_MAPPER — GitHub Packages Setup

## 1. Objetivo

Este documento explica cómo configurar Maven para consumir `UI_MAPPER` desde GitHub Packages.

---

## 2. Dependencia principal

En el `pom.xml` del consumidor:

```xml
<dependency>
    <groupId>com.qs</groupId>
    <artifactId>ui-mapper</artifactId>
    <version>${ui.mapper.version}</version>
</dependency>
```

---

## 3. Repositorios Maven

Agregar:

```xml
<repositories>
    <repository>
        <id>github</id>
        <url>https://maven.pkg.github.com/Jlara12345/UI_MAPPER</url>
        <releases>
            <enabled>true</enabled>
        </releases>
        <snapshots>
            <enabled>true</enabled>
        </snapshots>
    </repository>

    <repository>
        <id>github-qs-toolkit</id>
        <url>https://maven.pkg.github.com/Jlara12345/qs-toolkit</url>
        <releases>
            <enabled>true</enabled>
        </releases>
        <snapshots>
            <enabled>true</enabled>
        </snapshots>
    </repository>
</repositories>
```

---

## 4. settings.xml

Ubicación:

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

---

## 5. Permiso del token

El token debe tener:

```text
read:packages
```

Si también vas a publicar paquetes desde esa máquina:

```text
write:packages
```

---

## 6. Validar acceso

Ejecutar:

```bash
mvn clean install
```

Resultado esperado:

```text
BUILD SUCCESS
```

---

## 7. Errores comunes

### 401 Unauthorized

Causa:

Token incorrecto o sin permisos.

Solución:

```text
Validar username
Validar token
Validar read:packages
```

---

### Could not find artifact

Causa:

Repositorio mal configurado o versión inexistente.

Solución:

```text
Validar URL del repository
Validar versión de ui-mapper
Validar que el paquete fue publicado
```

---

### Server id no coincide

El `<id>` del repository debe coincidir con el `<id>` del server.

Ejemplo:

```xml
<repository>
  <id>github</id>
</repository>
```

debe tener:

```xml
<server>
  <id>github</id>
</server>
```

---

## 8. Seguridad

```text
✔ No subir settings.xml
✔ No subir tokens
✔ No pegar tokens en README
✔ Usar variables/credenciales en Jenkins
```
