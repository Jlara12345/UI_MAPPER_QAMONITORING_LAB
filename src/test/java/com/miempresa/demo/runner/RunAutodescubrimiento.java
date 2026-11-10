package com.miempresa.demo.runner;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.qs.uimapper.autodiscovery.runner.AutodescubrimientoRunner;
import com.qs.uimapper.config.UiMapperProjectConfig;
import com.qs.uimapper.config.UiMapperProjectConfigLoader;

/**
 * Runner puente del proyecto consumidor para generar scripts de
 * autodescubrimiento a partir de archivos Gherkin.
 * <p>
 * Este runner lee la configuración del contrato:
 * </p>
 *
 * <pre>
 * src/test/resources/ui-mapper-project.properties
 * </pre>
 *
 * <p>
 * y procesa todos los archivos {@code .feature} ubicados en:
 * </p>
 *
 * <pre>
 * project.features.directory
 * </pre>
 *
 * <p>
 * Normalmente esta ruta apunta a:
 * </p>
 *
 * <pre>
 * src/test/resources/features/autodiscovery
 * </pre>
 *
 * <h2>Uso dentro del Pipeline de Generación</h2>
 *
 * <pre>
 * RunSetupValidation AUTODISCOVERY
 *        ↓
 * RunAutodescubrimiento
 *        ↓
 * Ejecutar scripts generados de autodescubrimiento
 *        ↓
 * SELECTOR_REPOSITORY.properties generado/actualizado
 * </pre>
 *
 * <p>
 * Esta clase no ejecuta directamente los scripts generados. Su responsabilidad
 * es generar los archivos Java de autodescubrimiento.
 * </p>
 *
 * @author UI_MAPPER
 * @see AutodescubrimientoRunner
 * @see UiMapperProjectConfig
 * @see UiMapperProjectConfigLoader
 */
public class RunAutodescubrimiento {

    /**
     * Punto de entrada del runner puente.
     * <p>
     * Busca todos los archivos {@code .feature} en el directorio configurado
     * para autodiscovery y delega la generación al runner oficial del framework.
     * </p>
     *
     * @param args argumentos de ejecución. Actualmente no se requieren argumentos.
     * @throws Exception si no existe el directorio de features, si no existen
     *                   archivos {@code .feature}, o si ocurre un error durante
     *                   la generación.
     */
    public static void main(String[] args) throws Exception {

        UiMapperProjectConfig projectConfig = UiMapperProjectConfigLoader.loadDefault();
        Path featuresDir = Paths.get(projectConfig.getFeaturesDirectory());

        if (!Files.exists(featuresDir) || !Files.isDirectory(featuresDir)) {
            throw new IllegalArgumentException(
                    "No existe el directorio de features de autodescubrimiento: "
                            + featuresDir.toAbsolutePath()
            );
        }

        List<Path> featureFiles = Files.list(featuresDir)
                .filter(Files::isRegularFile)
                .filter(path -> path.getFileName().toString().endsWith(".feature"))
                .sorted()
                .toList();

        if (featureFiles.isEmpty()) {
            throw new IllegalArgumentException(
                    "No se encontraron archivos .feature en: "
                            + featuresDir.toAbsolutePath()
            );
        }

        AutodescubrimientoRunner runner = new AutodescubrimientoRunner();

        for (Path featureFile : featureFiles) {
            String featureFileName = featureFile.getFileName().toString();

            System.out.println("[UI_MAPPER] Generando autodescubrimiento para: " + featureFileName);

            Path output = runner.run(featureFileName);

            System.out.println("[UI_MAPPER] Script generado en:");
            System.out.println(output.toAbsolutePath());
        }
    }
}