# Criptografía

**Cifrado César**: Funciona con cualquier desplazamiento de 1 a n configurado directamente desde la interfaz, aplicando la operación módulo sobre el tamaño real del alfabeto activo (26 en inglés, 27 en español), lo que garantiza que ninguna letra quede fuera del rango válido.

**Cifrado Atbash**: Produce correctamente el inverso especular de cada letra dentro del alfabeto seleccionado. Al ser un cifrado involutorio, cifrar y descifrar ejecutan exactamente la misma operación y el resultado es siempre coherente.

**Cifrado Vigenère**: Aplica el desplazamiento por clave de forma correcta: cada letra del texto se desplaza según la posición de la letra de clave correspondiente dentro del alfabeto activo, avanzando el índice de clave únicamente sobre caracteres alfabéticos y conservando espacios y signos intactos.

**Cifrado Playfair**: Genera la matriz 5×5 de manera válida para ambos idiomas, procesa el texto en pares correctos sin desbordamientos, y tanto el cifrado como el descifrado manejan sin error entradas en minúsculas, con espacios o con acentos.

**Cifrado Rail Fence**: Reorganiza los caracteres en patrón zigzag y los recupera fielmente en el descifrado, funcionando correctamente con cualquier número de rieles mayor o igual a 2 y con cualquier texto independientemente del idioma.

**El selector de idioma**: Aplica de forma global e inmediata a todos los métodos: al cambiar entre Español e Inglés, cada cifrado utiliza automáticamente el alfabeto correcto sin requerir ninguna acción adicional del usuario.
