Feature: Flujo de negocio ejemplo

  Scenario: Validar navegación básica desde la página principal

    # Abre la aplicación usando project.baseUrl configurado en ui-mapper-project.properties
    Given open app

    # Indica la página lógica actual.
    # Este PAGE_ALIAS debe existir en ui-mapper-project.properties:
    # project.business.page.HOME=HomePage
    When on page "HOME"

    # Espera a que un elemento esté visible.
    # Este elementAlias debe existir en ui-mapper-project.properties:
    # project.business.element.HOME.loginLink=HOME.LOGIN_LINK
    And wait for element "loginLink" for 10 seconds

    # Valida que el elemento esté visible
    Then verify element "loginLink" is visible

    # Da click sobre el elemento
    And click "loginLink"

    # Valida que la URL contenga una ruta esperada
    Then verify current url contains "/login"