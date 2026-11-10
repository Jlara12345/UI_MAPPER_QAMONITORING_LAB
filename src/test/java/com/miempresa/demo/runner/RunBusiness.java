package com.miempresa.demo.runner;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.qs.uimapper.business.runner.BusinessScriptRunner;
import com.qs.uimapper.config.UiMapperProjectConfig;
import com.qs.uimapper.config.UiMapperProjectConfigLoader;

/**
 * Runner puente del proyecto consumidor para generar scripts de negocio
 * a partir de archivos Gherkin Business.
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
 * project.business.features.directory
 * </pre>
 *
 * <p>
 * Normalmente esta ruta apunta a:
 * </p>
 *
 * <pre>
 * src/test/resources/features/business
 * </pre>
 *
 * <h2>Uso dentro del Pipeline de Generación</h2>
 *
 * <pre>
 * RunBusinessAliasGeneration
 *        ↓
 * Copiar/validar aliases en ui-mapper-project.properties
 *        ↓
 * RunSetupValidation BUSINESS
 *        ↓
 * RunBusiness
 *        ↓
 * Scripts de negocio generados
 *        ↓
 * Ejecutar scripts de negocio
 * </pre>
 *
 * <h2>Prerrequisitos</h2>
 *
 * <ul>
 *   <li>El archivo {@code ui-mapper-project.properties} debe estar configurado.</li>
 *   <li>Debe existir {@code SELECTOR_REPOSITORY.properties} con contenido.</li>
 *   <li>Deben existir clases POM generadas.</li>
 *   <li>Deben estar configurados los aliases de página {@code project.business.page.*}.</li>
 *   <li>Deben estar configurados los aliases de elementos {@code project.business.element.*}.</li>
 *   <li>Debe existir al menos un archivo {@code .feature} en la carpeta Business.</li>
 * </ul>
 *
 * <h2>Resultado esperado</h2>
 *
 * <p>
 * Se generan scripts Java/Selenide en el paquete configurado por:
 * </p>
 *
 * <pre>
 * project.business.packageName
 * </pre>
 *
 * <p>
 * y en el directorio configurado por:
 * </p>
 *
 * <pre>
 * project.business.outputDirectory
 * </pre>
 *
 * <p>
 * Esta clase no ejecuta directamente los scripts generados. Su responsabilidad
 * es generar los archivos Java de negocio.
 * </p>
 *
 * @author UI_MAPPER
 * @see BusinessScriptRunner
 * @see UiMapperProjectConfig
 * @see UiMapperProjectConfigLoader
 */
public class RunBusiness {

    /**
     * Punto de entrada del runner puente.
     * <p>
     * Busca todos los archivos {@code .feature} en el directorio configurado
     * para Business y delega la generación al runner oficial del framework.
     * </p>
     *
     * @param args argumentos de ejecución. Actualmente no se requieren argumentos.
     * @throws Exception si no existe el directorio de features de negocio, si no
     *                   existen archivos {@code .feature}, o si ocurre un error
     *                   durante la generación.
     */
    public static void main(String[] args) throws Exception {

        UiMapperProjectConfig projectConfig = UiMapperProjectConfigLoader.loadDefault();
        Path featuresDir = Paths.get(projectConfig.getBusinessFeaturesDirectory());

        if (!Files.exists(featuresDir) || !Files.isDirectory(featuresDir)) {
            throw new IllegalArgumentException(
                    "No existe el directorio de features de negocio: "
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

        BusinessScriptRunner runner = new BusinessScriptRunner();

        for (Path featureFile : featureFiles) {
            String featureFileName = featureFile.getFileName().toString();

            System.out.println("[UI_MAPPER] Generando script de negocio para: " + featureFileName);

            Path output = runner.run(featureFileName);

            System.out.println("[UI_MAPPER] Script generado en:");
            System.out.println(output.toAbsolutePath());
        }
    }
}