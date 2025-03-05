INTRODUCCIÓN DEL PROYECTO

¿Qué es un Tamagotchi? 
Un Tamagotchi es un tipo de videojuego de simulación en el que el jugador cuida y gestiona el bienestar de una entidad virtual. Su jugabilidad se basa en la interacción con diferentes parámetros (como alimentación, sueño, etc) que afectan el estado del personaje.

¿En qué consiste nuestro Tamagotchi?
Para crear nuestro Tamagotchi, nos hemos inspirado en el conocido juego “POU”. En nuestro caso, el juego consiste en una persona ludópata la cual quiere convertirse en la persona más rica del mundo. Mientras tanto, tendremos que tener en cuenta el estado de nuestro Tamagotchi, es decir, vigilar el hambre, el sueño, el dinero y las deudas. Para ganar dinero, el Tamagotchi tendrá que apostar dinero en diferentes juegos.

¿Cuál es el propósito del juego?
El propósito del juego es entretener al jugador. Hemos creado un juego donde la interacción con el Tamagotchi es constante, para que así el jugador no se aburra.


CONCEPTO Y MECANICAS DEL JUEGO

¿Cómo funciona el hambre, sueño y dinero?
En cuanto al hambre: (0-100)
Cada vez que el Tamagotchi se vaya a dormir, sumará +8 de hambre
Cada vez que el Tamagotchi juega a un juego, se sumará +6 de hambre
Cada vez que el Tamagotchi paga una deuda, se sumará +3 de hambre

En cuanto al sueño: (0-100)
Cada vez que el Tamagotchi coma, se sumará +8 de sueño
Cada vez que el Tamagotchi juega a un juego, se sumará +10 de sueño
Cada vez que el Tamagotchi paga una deuda, se sumará +5 de sueño

En cuanto al dinero: (0-...)
Perderá dinero cada vez que tenga que pagar una deuda
Podrá perder dinero cada vez que no gane en un juego
Podrá perder dinero cuando tenga que comprar comida para tener que alimentar al Tamagotchi
Podrá ganar dinero jugando a juegos

¿Cómo afectan las decisiones del jugador al estado del Tamagotchi?
Para el juego hemos colocado 3 excepciones:
Si el jugador se queda sin dinero PIERDE
Si el jugador llega a 100 de hambre PIERDE
Si el jugador llega a 100 de sueño PIERDE

IMPLEMENTACIÓN TÉCNICA

¿Qué lenguaje y herramientas hemos utilizado para la creación?
Para desarrollar el juego hemos utilizado el lenguaje de Java, lo hemos aplicado a través de Visual Studio. Para completar el código hemos utilizado varias herramientas: por un lado, hemos utilizado los apuntes que nos ha proporcionado el profesor, y por otro lado, hemos utilizado la IA de Copiloto, la cual está especializada en programación, y nos ha ayudado con algunos problemas que hemos encontrado. 

¿Cómo se estructura el código?
El codigo lo tenemos estructurado en dos archivos: Tamagotchi.java y Main.java

Archivo 1 → Tamagotchi.java

El archivo define una clase `Tamagotchi` que simula la vida de un Tamagotchi con varias actividades y estados. Los principales componentes son:

1. Atributos de Clase:
Atributos como `nombre`, `dinero`, `sueno`, `hambre`, `dia`, `deuda`, `horaDelDia` y `random` que representan el estado del Tamagotchi.

2. Constructor:
Inicializa los atributos del Tamagochi y genera una deuda inicial aleatoria.

3. Métodos Públicos:
Métodos para obtener el dinero (`getDinero`), mostrar el estado (`mostrarEstado`), dormir (`dormir`), comer (`comer`), jugar a la ruleta (`jugarRuleta`), jugar a los dados (`jugarDados`), jugar al juego del 26 (`jugar26`), jugar al juego del mapa (`jugarMapa`) y pagar la deuda (`pagarDeuda`).

4. Métodos Privados:
Métodos para generar deuda (`generarDeuda`), evaluar el estado del Tamagochi (`evaluarEstado`) y mostrar mensajes con una pausa (`mostrarConPausa`).

Archivo 2 → Main.java

El archivo define la clase `Main` que contiene el flujo principal del juego Tamagochi. Los principales componentes son:

1. Atributos de Clase:
Atributo `tamagochi` que representa al Tamagochi del jugador.

2. Constructor:
Inicializa el atributo `tamagochi` a `null`.


3. Métodos Privados:
Métodos para mostrar un mensaje de bienvenida (`mostrarMensajeBienvenida`), iniciar el juego (`iniciarJuego`), mostrar el menú (`mostrarMenu`) y ejecutar la opción seleccionada por el jugador (`ejecutarOpcion`).

4. Método Público:
Método `run` que controla el ciclo principal del juego, mostrando el estado del Tamagochi, el menú y procesando la opción seleccionada por el jugador hasta que el jugador decide finalizar el juego.

¿Cómo funciona el juego?
Nuestro juego consiste en cuidar al Tamagotchi e intentar hacerse la persona con más dinero del mundo. Al principio del juego aparecerá un mensaje, donde se podrá ver los objetivos del juego y como está construido. Una vez empezado el juego, preguntaran el nombre del Tamagotchi, seguidamente aparecerá la información del Tamagotchi (Dia, Hora, Sueño, Hambre, Dinero y Deuda), en la inferior de este mensaje encontraremos el menú el cual tendrá que elegir una opción cada vez el jugador. El menú contiene: Ir a dormir, Dar de comer, Jugar a la ruleta, Jugar al 26, Jugar a los gemelos, Jugar al CaBoom, Pagar deuda o Fin del juego. Si seleccionamos el apartado de ir a dormir, nos aparecerán 3 opciones: la primera será dormir 3 horas, la segunda será dormir 6 horas y la tercera será dormir 9 horas. Si seleccionamos el apartado de dar de comer, nos aparecerán 3 opciones: la primera será comer sopa, la segunda será comer macarrones y la tercera será comer un bistec. Para estas dos opciones (Ir a dormir y dar de comer), dependiendo de la opción que se elija tendrá unos beneficios u otros. Después encontraremos la opción de jugar a juegos, donde encontramos 4 opciones: la primera el juego de la ruleta, la segunda el juego del 26, la tercera el juego de los gemelos y por último el juego de CaBoom. Después, encontraremos la opción de pagar la deuda. Para finalizar, encontraremos la opción de fin de juego. 

Si quieres averiguar en qué consisten los juegos, queremos que juegues a nuestro juego.

POSIBLES MEJORAS Y FUTURO DEL PROYECTO

¿Agregarías alguna interfaz gráfica?
Si, tenemos pensado agregar una interfaz gráfica al juego. Nos gustaría que el juego se viera de esta manera: `inspirado en el juego PewDiePie`

¿Tenemos alguna novedad pensada para nuestro Tamagotchi?
Si, próximamente teníamos pensado añadir al juego un apartado donde el jugador puede comprar objetos para decorar la casa. Por otro lado, realizaremos algunos cambios en el código para que el juego sea más fluido.
