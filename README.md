# gestor-de-figuras-geometricas

# gestor-de-figuras

Aplicación de escritorio para trabajar con un **rectángulo** definido por dos vértices opuestos: calcular su área, moverlo en el plano y verificar si un punto está dentro de él. Está desarrollada con **Java y Swing/JFrame**, sin librerías externas, y aplica herencia, clases abstractas y polimorfismo.



## Justificación de la solución

El problema requiere una interacción visual, por lo que se implementó con **Java Swing**. Para cumplir con la cantidad de clases y con el uso de herencia y polimorfismo, el diseño se organizó con el patrón **MVC**:

- **Modelo:** se diseñó la clase `Punto` para manejar las coordenadas `(x, y)`. Se creó la superclase abstracta `FiguraGeometrica`, que define los métodos abstractos `calcularArea()` y `mover()`. La clase `Rectangulo` hereda de esta figura y está compuesta por dos objetos `Punto` (vértices opuestos).
- **Controlador:** `ControladorRectangulo` gestiona la lógica matemática (cálculo del área, movimiento y validación de punto dentro de la figura).
- **Vista:** `VistaRectangulo`, un `JFrame` de Swing con botones y campos de texto para las coordenadas y las acciones.
- **Principal:** `main`, que lanza la interfaz.

## Requisitos

- JDK 26
- Apache NetBeans 30 (o cualquier IDE compatible con Maven)
- Maven (incluido en NetBeans)

## Estructura del proyecto

```
src/main/java/
├── modelo/
│   ├── Punto.java                    (coordenadas x, y)
│   ├── FiguraGeometrica.java         (superclase abstracta)
│   └── Rectangulo.java               (subclase)
├── vista/
│   └── VistaRectangulo.java          (JFrame)
├── controlador/
│   └── ControladorRectangulo.java
└── principal/
    └── main.java                     (clase principal)
```

## Patrón MVC

| Capa | Clase | Responsabilidad |
|------|-------|-----------------|
| Modelo | `Punto`, `FiguraGeometrica`, `Rectangulo` | Datos y reglas de la figura (área y movimiento). |
| Vista | `VistaRectangulo` | Interfaz gráfica (campos de texto y botones). No contiene lógica matemática. |
| Controlador | `ControladorRectangulo` | Contiene la lógica de cálculo y validación y la conecta con el modelo. |
| Principal | `main` | Crea la vista e inicia la aplicación. |

## Descripción de las clases

- **`Punto`**: guarda las coordenadas `x` e `y` (privadas) con sus getters y setters.
- **`FiguraGeometrica`** (abstracta): declara los métodos abstractos `calcularArea()` y `mover(double dx, double dy)`.
- **`Rectangulo extends FiguraGeometrica`**: está compuesto por dos `Punto` (`p1` y `p2`, vértices opuestos). Implementa `calcularArea()` y `mover()`.
- **`ControladorRectangulo`**: mantiene el `Rectangulo` y ofrece `obtenerArea()`, `mover(dx, dy)` y `validarPuntoDentro(Punto p)`.
- **`VistaRectangulo`**: `JFrame` con los campos de texto para las coordenadas y los botones de acción.
- **`main`**: lanza la interfaz con `SwingUtilities.invokeLater`.

## Conceptos de POO aplicados

- **Herencia:** `Rectangulo` hereda de `FiguraGeometrica`, que es el contrato común de las figuras.
- **Clase abstracta:** `FiguraGeometrica` no se puede instanciar; obliga a las subclases a implementar `calcularArea()` y `mover()`.
- **Polimorfismo / Sobreescritura (`@Override`):** `Rectangulo` implementa los métodos abstractos con la fórmula propia del rectángulo. En `ControladorRectangulo` existe un atributo `FiguraGeometrica figura` que apunta al rectángulo, de modo que `figura.mover(dx, dy)` ejecuta la versión de `Rectangulo` en tiempo de ejecución (enlace dinámico).
- **Composición:** un `Rectangulo` *tiene* dos objetos `Punto` que definen sus vértices opuestos.
- **Encapsulamiento:** todos los atributos son privados y se acceden mediante getters y setters.

### Cálculo del área

El área se obtiene con la diferencia de coordenadas de los vértices opuestos:

```
base   = |x2 - x1|
altura = |y2 - y1|
área   = base × altura
```

### Movimiento

`mover(dx, dy)` desplaza **ambos** vértices sumando `dx` a las coordenadas X y `dy` a las coordenadas Y, de modo que el rectángulo conserva su tamaño.

### Lógica de "punto dentro del rectángulo"

Como `P1` y `P2` son vértices opuestos y pueden venir en cualquier orden, el controlador primero obtiene los límites con `min` y `max`:

```
minX = min(x1, x2)    maxX = max(x1, x2)
minY = min(y1, y2)    maxY = max(y1, y2)
```

Un punto `(x, y)` está **dentro** si cumple las cuatro condiciones a la vez (`AND`):

```
x >= minX  AND  x <= maxX  AND  y >= minY  AND  y <= maxY
```

Por la ley de De Morgan, el punto está **fuera** si se cumple al menos una de estas (`OR`):

```
x < minX  OR  x > maxX  OR  y < minY  OR  y > maxY
```

Los puntos que quedan exactamente sobre el borde se consideran dentro (se usan `>=` y `<=`).

### Ejemplo

Rectángulo con `P1(1, 1)` y `P2(5, 4)`:

| Operación | Resultado |
|-----------|-----------|
| Área | `4 × 3 = 12` |
| Punto `(3, 2)` | Dentro |
| Punto `(6, 2)` | Fuera (`x > maxX`) |
| `mover(2, 1)` | `P1(3, 2)` y `P2(7, 5)` |

## Diagrama de clases (UML)

El modelo refleja la **Composición** (un `Rectangulo` *tiene* `Punto`) y la **Herencia** (un `Rectangulo` *es una* `FiguraGeometrica`).



Relaciones y cardinalidades:

- **Herencia** (`FiguraGeometrica` ← `Rectangulo`): un `Rectangulo` es una `FiguraGeometrica`.
- **Composición** (`Rectangulo` ◆— `Punto`): un `Rectangulo` está formado por exactamente 2 `Punto` (`1..2`).
- **Asociación** (`ControladorRectangulo` → `Rectangulo` / `FiguraGeometrica`): el controlador gestiona un rectángulo y lo referencia también como `FiguraGeometrica` (polimorfismo).
- **Dependencia** (`ControladorRectangulo` ⇢ `Punto`): `validarPuntoDentro` recibe un `Punto` como parámetro.
- **Asociación** (`VistaRectangulo` → `ControladorRectangulo`, `1..1`): la vista usa un controlador.



## Diagrama de flujo: validación de punto dentro del rectángulo

Este diagrama detalla las comprobaciones lógicas (`AND` y `OR`) para determinar si las coordenadas ingresadas por el usuario caen dentro del área delimitada por los vértices opuestos.



El paso de leer los datos y mostrar el resultado lo realiza la vista; el cálculo (`min`, `max` y las comparaciones) está en `ControladorRectangulo.validarPuntoDentro`.




## Uso de la aplicación

1. Ejecutar la clase `main` (paquete `principal`) desde NetBeans con **Run Project**.
2. Escribir las coordenadas de los dos vértices opuestos del rectángulo (`x1`, `y1`, `x2`, `y2`). Los decimales se escriben con punto (`1.5`).
3. Usar los botones de la ventana para **calcular el área**, **mover** el rectángulo (indicando `dx` y `dy`) o **verificar si un punto está dentro** (indicando `x` e `y` del punto).
