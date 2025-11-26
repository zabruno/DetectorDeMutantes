# 🧬 Mutant Detector (GlobalMutantsDetector)

![Java](https://img.shields.io/badge/Java-21-orange?logo=java)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-green?logo=springboot)
![Gradle](https://img.shields.io/badge/Gradle-Build-blue?logo=gradle)
![Coverage](https://img.shields.io/badge/Coverage-Jacoco%20%3E%2080%25-brightgreen)

API REST diseñada para detectar si un humano es un mutante basándose en su secuencia de ADN. Proyecto realizado como desafío técnico (MercadoLibre) e integrador de desarrollo.

> **Proyecto basado en el examen:** [ExamenMercado](https://github.com/cortezalberto/ExamenMercado.git)

## 👤 Información del Alumno

* **Nombre:** Bruno
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
git clone [https://github.com/zabruno/DetectorDeMutantes.git](https://github.com/zabruno/DetectorDeMutantes.git)
cd DetectorDeMutantes