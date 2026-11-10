Feature: Autodescubrimiento ejemplo

  Scenario: Descubrir elementos principales de la aplicación

    # Abre la página base de la aplicación configurada en project.baseUrl
    Given open page "/"

    # Espera inicial para permitir que cargue la aplicación
    And wait 2 seconds

    # Captura los elementos visibles de la página actual
    And capture page "HOME"

    # Ejemplo de navegación usando un selector explícito.
    # Ajustar este selector según la aplicación real del consumidor.
    # And click "css:a[href='/login']"

    # Espera después de una interacción o navegación
    # And wait 2 seconds

    # Captura una segunda página o estado de la UI
    # And capture page "LOGIN"