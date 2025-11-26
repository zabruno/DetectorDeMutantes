# 🧬 Mutant Detector (GlobalMutantsDetector)

![Java](https://img.shields.io/badge/Java-21-orange?logo=java)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-green?logo=springboot)
![Gradle](https://img.shields.io/badge/Gradle-Build-blue?logo=gradle)
![Coverage](https://img.shields.io/badge/Coverage-Jacoco%20%3E%2080%25-brightgreen)

API REST diseñada para detectar si un humano es un mutante basándose en su secuencia de ADN. Proyecto realizado como desafío técnico (MercadoLibre) e integrador de desarrollo.

> **Proyecto basado en el examen:** [ExamenMercado](https://github.com/cortezalberto/ExamenMercado.git)

## 👤 Información del Alumno

* **Nombre:** Bruno Zaupa
* **Legajo:** 51159
* **Repositorio:** [DetectorDeMutantes](https://github.com/zabruno/DetectorDeMutantes.git)

---

## 🚀 Descripción del Proyecto

Magneto quiere reclutar la mayor cantidad de mutantes para su ejército. Este sistema recibe una matriz de secuencias de ADN (Strings) y determina si el sujeto es mutante.

**Condición de Mutante:**
Se considera mutante si se encuentran **más de una secuencia de cuatro letras iguales** (A, T, C, G) de forma oblicua, horizontal o vertical.

---

## 🛠️ Tecnologías y Herramientas

El proyecto está construido con las siguientes especificaciones extraídas del `build.gradle`:

* **Lenguaje:** Java 21 (Toolchain configurado).
* **Framework:** Spring Boot 3 (Web, Data JPA, Validation).
* **Documentación API:** Swagger / OpenAPI 3 (`springdoc-openapi`).
* **Boilerplate:** Lombok.
* **Base de Datos:** H2 Database (En memoria).
* **Testing & Coverage:** JUnit 5 + Jacoco (Reportes XML/HTML habilitados).
* **Build Tool:** Gradle.

---

## ⚙️ Instalación y Ejecución

### Prerrequisitos
* Java 21 instalado (o dejar que Gradle Toolchain lo maneje).

### 1. Clonar el repositorio
```bash
git clone https://github.com/zabruno/DetectorDeMutantes.git
cd DetectorDeMutantes
```
### 2. Ejecutar la aplicación
Puedes levantar el proyecto directamente con el wrapper de Gradle:

```bash
./gradlew bootRun
```
O generar el .jar ejecutable (configurado como GlobalMutantsDetector.jar):
```bash
./gradlew bootJar
java -jar build/libs/GlobalMutantsDetector.jar
```
## 📖 Documentación de la API (Swagger)

Al iniciar la aplicación, puedes acceder a la interfaz visual de Swagger para probar los endpoints y ver la documentación interactiva:

* **URL Local:** [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
* **OpenAPI Json:** [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

---

## 🧪 Testing y Code Coverage

El proyecto utiliza **Jacoco** para medir la cobertura de los tests. Se ha configurado una regla de validación en el `build.gradle` que exige un **mínimo del 80% de cobertura**.

Para ejecutar los tests y generar el reporte:

```bash
./gradlew test jacocoTestReport
```
Los reportes se generan en: `build/reports/jacoco/test/html/index.html`

> **Nota:** La configuración de Jacoco excluye del reporte a la clase principal `MutantDetectorApplication`, configuraciones y clases generadas por Lombok para una medición más precisa de la lógica de negocio.

---

## 📡 Endpoints Principales

### 1. Detectar Mutante
Verifica si una secuencia de ADN corresponde a un mutante.

* **URL:** `/mutant/`
* **Método:** `POST`
* **Body (JSON):**

```json
{
    "dna": [
        "ATGCGA",
        "CAGTGC",
        "TTATGT",
        "AGAAGG",
        "CCCCTA",
        "TCACTG"
    ]
}
```
* **Respuestas:**
    * `200 OK`: Es un mutante.
    * `403 Forbidden`: No es un mutante (es humano).

### 2. Estadísticas
Devuelve un JSON con la cantidad de verificaciones de ADN mutantes y humanos, y el ratio.

* **URL:** `/stats`
* **Método:** `GET`
* **Respuesta (JSON):**

```json
{
    "count_mutant_dna": 40,
    "count_human_dna": 100,
    "ratio": 0.4
}
```