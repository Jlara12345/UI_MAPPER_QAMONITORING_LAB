package com.miempresa.demo.runner;

import com.qs.uimapper.business.alias.runner.BusinessAliasGenerationRunner;

/**
 * Runner puente del proyecto consumidor para ejecutar la generación automática
 * de aliases de negocio del framework UI_MAPPER.
 * <p>
 * Esta clase permite invocar desde el proyecto consumidor el runner oficial:
 * </p>
 *
 * <pre>
 * com.qs.uimapper.business.alias.runner.BusinessAliasGenerationRunner
 * </pre>
 *
 * <p>
 * El objetivo de este runner es generar automáticamente el archivo:
 * </p>
 *
 * <pre>
 * src/test/resources/ui-mapper-business-aliases.generated.properties
 * </pre>
 *
 * a partir de:
 *
 * <ul>
 *   <li>Las clases POM generadas previamente.</li>
 *   <li>El archivo central de selectores {@code SELECTOR_REPOSITORY.properties}.</li>
 * </ul>
 *
 * <h2>Qué genera</h2>
 *
 * <p>
 * El archivo generado contiene sugerencias de configuración para:
 * </p>
 *
 * <ul>
 *   <li>{@code project.business.page.*}: aliases de páginas hacia clases POM.</li>
 *   <li>{@code project.business.element.*}: aliases de elementos hacia logicalIds.</li>
 * </ul>
 *
 * <h2>Uso dentro del Pipeline de Generación</h2>
 *
 * <p>
 * Este runner debe ejecutarse después de generar las clases POM:
 * </p>
 *
 * <pre>
 * RunPomGeneration
 *        ↓
 * RunBusinessAliasGeneration
 * </pre>
 *
 * <p>
 * Y antes de validar/generar scripts de negocio:
 * </p>
 *
 * <pre>
 * RunBusinessAliasGeneration
 *        ↓
 * Copiar/validar aliases en ui-mapper-project.properties
 *        ↓
 * RunSetupValidation BUSINESS
 *        ↓
 * RunBusiness
 * </pre>
 *
 * <h2>Importante</h2>
 *
 * <p>
 * Esta clase no modifica directamente el archivo
 * {@code ui-mapper-project.properties}. Únicamente genera un archivo auxiliar
 * con aliases sugeridos para que el usuario los revise, ajuste si es necesario
 * y posteriormente los copie al contrato principal.
 * </p>
 *
 * <p>
 * Esta clase no contiene lógica propia de generación. Su única responsabilidad
 * es actuar como punto de entrada del proyecto consumidor hacia el runner
 * oficial del framework.
 * </p>
 *
 * @author UI_MAPPER
 * @see BusinessAliasGenerationRunner
 */
public class RunBusinessAliasGeneration {

    /**
     * Punto de entrada del runner puente.
     * <p>
     * Delega la ejecución al runner oficial del framework, encargado de leer la
     * configuración del proyecto consumidor, analizar las clases POM generadas,
     * analizar el repositorio de selectores y construir el archivo de aliases de
     * negocio sugeridos.
     * </p>
     *
     * @param args argumentos de ejecución. Actualmente no se requieren
     *             argumentos para este runner.
     * @throws Exception si ocurre un error durante la generación de aliases.
     */
    public static void main(String[] args) throws Exception {
        BusinessAliasGenerationRunner.main(args);
    }
}