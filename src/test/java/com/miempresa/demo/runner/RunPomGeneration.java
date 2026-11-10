package com.miempresa.demo.runner;

import com.qs.uimapper.pom.runner.PomGenerationRunner;

/**
 * Runner puente del proyecto consumidor para ejecutar la generación automática
 * de clases POM mediante el framework UI_MAPPER.
 * <p>
 * Este runner invoca al runner oficial:
 * </p>
 *
 * <pre>
 * com.qs.uimapper.pom.runner.PomGenerationRunner
 * </pre>
 *
 * <p>
 * Su objetivo es generar clases Page Object Model a partir del repositorio
 * central de selectores:
 * </p>
 *
 * <pre>
 * SELECTOR_REPOSITORY.properties
 * </pre>
 *
 * <h2>Uso dentro del Pipeline de Generación</h2>
 *
 * <pre>
 * Ejecutar scripts generados de autodescubrimiento
 *        ↓
 * SELECTOR_REPOSITORY.properties generado/actualizado
 *        ↓
 * RunPomGeneration
 *        ↓
 * Clases POM generadas
 * </pre>
 *
 * <h2>Prerrequisitos</h2>
 *
 * <ul>
 *   <li>El archivo {@code ui-mapper-project.properties} debe estar configurado.</li>
 *   <li>Debe existir {@code SELECTOR_REPOSITORY.properties}.</li>
 *   <li>El repositorio de selectores debe contener logicalIds descubiertos.</li>
 *   <li>Deben estar configurados:
 *     <ul>
 *       <li>{@code project.pom.packageName}</li>
 *       <li>{@code project.pom.outputDirectory}</li>
 *       <li>{@code project.selector.definitionFile}</li>
 *     </ul>
 *   </li>
 * </ul>
 *
 * <h2>Resultado esperado</h2>
 *
 * <p>
 * Se generan clases POM en el paquete configurado por:
 * </p>
 *
 * <pre>
 * project.pom.packageName
 * </pre>
 *
 * <p>
 * y en el directorio configurado por:
 * </p>
 *
 * <pre>
 * project.pom.outputDirectory
 * </pre>
 *
 * <p>
 * Esta clase no contiene lógica propia de generación. Solo delega la ejecución
 * al runner oficial del framework.
 * </p>
 *
 * @author UI_MAPPER
 * @see PomGenerationRunner
 */
public class RunPomGeneration {

    /**
     * Punto de entrada del runner puente.
     * <p>
     * Delega la ejecución al runner oficial del framework, encargado de leer el
     * contrato del proyecto consumidor, cargar el repositorio de selectores y
     * generar las clases POM correspondientes.
     * </p>
     *
     * @param args argumentos de ejecución. Actualmente no se requieren argumentos.
     * @throws Exception si ocurre un error durante la generación de clases POM.
     */
    public static void main(String[] args) throws Exception {
        PomGenerationRunner.main(args);
    }
}