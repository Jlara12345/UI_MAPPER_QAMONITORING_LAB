package com.miempresa.demo.runner;

import com.qs.uimapper.config.runner.ProjectSetupValidatorRunner;

/**
 * Runner puente del proyecto consumidor para ejecutar la validación de setup
 * del framework UI_MAPPER.
 * <p>
 * Esta clase permite invocar desde el proyecto consumidor el runner oficial
 * del framework:
 * </p>
 *
 * <pre>
 * com.qs.uimapper.config.runner.ProjectSetupValidatorRunner
 * </pre>
 *
 * <p>
 * Su objetivo es validar que el archivo de contrato del proyecto consumidor:
 * </p>
 *
 * <pre>
 * src/test/resources/ui-mapper-project.properties
 * </pre>
 *
 * contenga la configuración mínima requerida para ejecutar correctamente las
 * diferentes etapas del Pipeline de Generación.
 * </p>
 *
 * <h2>Modos de ejecución</h2>
 *
 * <pre>
 * AUTODISCOVERY
 * BUSINESS
 * </pre>
 *
 * <h2>Uso dentro del Pipeline de Generación</h2>
 *
 * <pre>
 * RunSetupValidation AUTODISCOVERY
 *        ↓
 * RunAutodescubrimiento
 *
 * RunSetupValidation BUSINESS
 *        ↓
 * RunBusiness
 * </pre>
 *
 * <p>
 * Esta clase no contiene lógica propia. Solo delega la ejecución al runner
 * oficial del framework.
 * </p>
 *
 * @author UI_MAPPER
 * @see ProjectSetupValidatorRunner
 */
public class RunSetupValidation {

    /**
     * Punto de entrada del runner puente.
     * <p>
     * Delega los argumentos recibidos al runner oficial de validación de setup.
     * </p>
     *
     * @param args argumentos de ejecución. Valores esperados:
     *             <ul>
     *               <li><code>AUTODISCOVERY</code></li>
     *               <li><code>BUSINESS</code></li>
     *             </ul>
     */
    public static void main(String[] args) {
        ProjectSetupValidatorRunner.main(args);
    }
}