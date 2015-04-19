# JmusicLib

#### Proyecto de Juan José Salvador Piedra
###### 1º DAW IES Celia Viñas

Se trata de una aplicación que permite organizar y reproducir la música que tenemos descargada en nuestro equipo, utilizando los metadatos de esta (*es decir, la información extra contenida en los archivos MP3*).

### Funcionamiento
Lee los archivos MP3 contenidos en la carpeta que le indicaremos durante el primer inicio del programa, y con ello, lee sus metadatos.

Exporta los metadatos a una base de datos, o un fichero XML, y en base a la información contenida en esta base de datos (de ahora en adelante, llamado *biblioteca*) organiza alfabéticamente por autor y dentro de este, por album y número de pista.

A la hora de mostrarnos la música, nos permite listar toda la música contenida, o realizar una búsqueda por autor, por nombre de la pista, o por album.

Para la extracción de metadatos de MP3, utiliza la librería **Beaglebuddy**.

### Extras
**JmusicLib** también soporta la reproducción de la música que tenemos guardada, así como crear nuestras propias listas de reproducción y guardarlas.

Entre las funciones planeadas, se encuentra la reproducción de archivos MP3, OGG y FLAC, aunque estos dos últimos aparentemente aún no son soportados por Java. La reproducción de MP3 se llevará a cabo mediante la librería **JLayer**.

### Enlaces externos
* [Beaglebuddy](http://www.beaglebuddy.com/)
* [JLayer](http://www.javazoom.net/javalayer/javalayer.html)
* [#JmusicLib en vivo](https://twitter.com/search?q=%23JmusicLib)
