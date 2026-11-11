# SaaS B2B de Gestión de huella de carbono y sostenibilidad para PYMES.

## Descripción
EcoLedger ayuda a las PYMEs a registrar sus consumos energéticos, calcular su huella de carbono y generar informes de sostenibilidad de forma rápida y sencilla, sin hojas de cálculo ni cálculos manuales.

- **El problema:** las PYMEs no están obligadas por ley a publicar informes de sostenibilidad, pero sus clientes, bancos y grandes empresas de su cadena de suministro les piden cada vez más estos datos.
- **La solución:** centralizar los datos de consumo, evitar errores de cálculo y generar informes trazables, preparados para una auditoría.
- **El objetivo:** alinear los informes con el estándar europeo voluntario para PYMEs (VSME), que marca qué información se les puede pedir.

## Contexto normativo
- **CSRD tras la Directiva Ómnibus (UE) 2026/470**, en vigor desde el 18 de marzo de 2026: la obligación de informar solo afecta a empresas con más de 1.000 empleados **y** más de 450 millones de euros de facturación. Las PYMEs quedan fuera de la obligación directa.
- **VSME (Voluntary Sustainability Reporting Standard for SMEs)**, elaborado por EFRAG: estándar voluntario con un módulo básico y otro ampliado. Es la referencia para las empresas fuera del ámbito de la CSRD, y el máximo de información que se les puede exigir en la cadena de valor.

> ℹ️ La normativa de sostenibilidad cambia con frecuencia. Última revisión: noviembre de 2026.

## Stack Tecnológico:
**Back-End:**
- Java 25 LTS
- Spring Boot 4.1
- Spring Data JPA
- Spring Security(JWT)
- PostgreSQL

**Front-End**
- React
- Typescript
- Tailwind

**Infraestructura/Entorno**
- Docker
- IntelliJ IDEA

## Puesta en marcha

**Requisitos:** Java 25, Maven, Docker Desktop (con WSL 2 en Windows) e IntelliJ IDEA.

1. Clonar el repositorio y entrar en la carpeta:
```
   git clone https://github.com/JoseAtalp/ecoledger.git
   cd ecoledger
```
2. Crear el archivo de credenciales a partir de la plantilla y rellenar `POSTGRES_PASSWORD`:
```
   Copy-Item .env.example .env
```
> ⚠️ Usa una contraseña larga (32+ caracteres) formada solo por letras, números, `-` y `_`. Caracteres como `'` `"` `=` `$` `#` `\` se interpretan de forma distinta en Docker Compose y en Spring Boot.
3. Levantar la base de datos:
```
   docker compose up -d
```
4. Arrancar `EcoledgerApplication` desde IntelliJ.

> ℹ️ `POSTGRES_PASSWORD` solo se aplica la primera vez que se crea el volumen `ecoledger-data`. Para cambiar la contraseña después, hay que hacerlo dentro de PostgreSQL (`\password jopla`) y actualizar el `.env`.

## Decisiones de Arquitectura "ADR" (Architecture Decision Record):

### Record vs. Lombok (@Data/@Value) para DTOs inmutables
**Decisión:**
[Record]
**Alternativas consideradas:**
[Lombok]
**Razón:**
[El uso Records nos dá esa inmutabilidad a la hora de trabajar con DTOs. Nos aseguraba que el dato no fuera alterado durante el "viaje" o proceso. Menor uso de lineas de codigo y legibilidad.]

### Autovalidación en el constructor compacto del Record
**Decisión:**
[Validar los datos dentro del constructor compacto del record.]
**Alternativas consideradas:**
[Validar en el Service o en el Controller.]
**Razón:**
[Gracias al _fail-fast_, ningún objeto puede existir en un estado inválido: si los datos no son correctos, el record lanza una excepción en el mismo momento de crearse. Así, la validación no depende de que cada Service o Controller se acuerde de hacerla. Da igual desde qué capa se cree el objeto: la regla se cumple siempre, y está escrita en un único sitio.]

### Exclusión del campo `id` en el DTO de creación
**Decisión:**
[Eliminacion del campo id en nuestro record]
**Alternativas consideradas:**
[Existencia de un campo id en nuestro record]
**Razón:**
[La inclusión de un id en un record de creacion, carece de sentido ya que el id 'nace' de forma autoincremental en nuestra base de datos. Se añadirá en el @Entity]

### BigDecimal para las cantidades de consumo
**Decisión:**
[Uso de la clase BigDecimal]
**Alternativas consideradas:**
[El tipo primitivo 'double']
**Razón:**
[La inexactitud de double a la hora de ejecutar cálculos del tipo decimal como por ejemplo (0.1 + 0.2 =  0.30000000000000004). Este resultado sería inadmisible ya que habría errores por lo que nuestra aplicación perdería credibilidad en caso de auditoria.]

### EnumType.STRING para guardar la unidad de medida
**Decisión:**
[Uso de STRING como EnumType]
**Alternativas consideradas:**
[EnumType ORDINAL]
**Razón:**
[Con ORDINAL, si alguien añade un valor nuevo al principio del enum, cambian las posiciones de los valores existentes. Por ejemplo: los 150 kWh de "Compañía X" guardados como 0, pasarían a leerse como 150 MWh, es decir, 1000 veces más. Por lo que el informe de emisiones sería erróneo, y lo peor es que la aplicación seguiría ejecutándose sin dar ningún aviso.]
**Nota extra:**
[mWh = milivatio hora / MWh = megavatio hora]

### Credenciales fuera del código mediante `.env`
**Decisión:**
[Las credenciales viven en un archivo `.env` en la raíz, ignorado por Git, con `.env.example` como plantilla versionada. `compose.yaml` lee la variable con `${POSTGRES_PASSWORD}`, y Spring Boot importa el mismo archivo con `spring.config.import: optional:file:.env[.properties]`. La contraseña expuesta se ha rotado.]
**Alternativas consideradas:**
[Mantener la contraseña en `compose.yaml` y `application.yaml`, justificándolo por estar limitada a `127.0.0.1`. Configurar la variable a mano en IntelliJ. Reescribir el historial de Git para borrar la contraseña antigua.]
**Razón:**
[Aunque el puerto solo acepte conexiones locales, una contraseña en el repositorio es una mala práctica y un riesgo si el proyecto se despliega. Un único `.env` es la única fuente de verdad para Docker Compose y Spring Boot, y el mismo `application.yaml` servirá en producción, donde la variable llegará como variable de entorno del servidor (p. ej., desde AWS Secrets Manager). Se descartó reescribir el historial: rotar la contraseña la invalida igualmente, sin alterar commits ni Pull Requests.]
**Nota extra:**
[El import es opcional y relativo al directorio de trabajo: si la aplicación se lanza desde otra carpeta, no encontrará el `.env`.]

### Idioma de ramas, commits y código
**Decisión:**
[Desde noviembre de 2026, ramas y commits en inglés siguiendo Conventional Commits. El dominio se tradujo al inglés en la rama `refactor/translate-domain-to-english`: `UnidadMedida` → `MeasurementUnit`, `ConsumoEnergeticoEntity` → `EnergyConsumptionEntity` y la tabla `consumos_energeticos` → `energy_consumptions`. La documentación se mantiene en español.]
**Alternativas consideradas:**
[Mantener todo en español. Escribir el código nuevo en inglés sin traducir el existente.]
**Razón:**
[El inglés es el estándar de la industria y el idioma de las librerías del stack (`findAll`, `save`...). Hacer la traducción en una rama dedicada, mientras el proyecto es pequeño, evita mezclar idiomas en el código y mezclar el refactor con funcionalidades nuevas.]

### Nombres de los DTO según su dirección
**Decisión:**
[Los DTO se nombran según la dirección de los datos: `...Request` para los que entran en la API (p. ej., `CreateEnergyConsumptionRequest`) y `...Response` para los que salen.]
**Alternativas consideradas:**
[Un único DTO con el mismo nombre que la entidad (`EnergyConsumption`).]
**Razón:**
[El DTO de creación no tiene `id`, porque la lectura aún no existe, y el de lectura sí lo tendrá. Son contratos distintos, y el sufijo deja claro a primera vista cuál es cada uno, además de evitar confundirlos con la entidad.]

##  Estado actual / Roadmap
**Terminado:**
- ✅ Infraestructura ← Spring Boot 4.1 con Maven y PostgreSQL 18 en Docker.
- ✅ DTO (Creación) ← el "contrato" de qué datos entran/salen por la API.
- ✅ Entity ← el "molde" de cómo se guarda en PostgreSQL.
- ✅ Credenciales ← fuera del código, en un `.env` ignorado por Git.
- ✅ Refactor ← dominio traducido al inglés.

**Pendiente / próximos pasos:**
- ⬜ Repository ← quién lee/escribe esa Entity en la BD.
- ⬜ DTO (Lectura) ← `EnergyConsumptionResponse`, el "contrato" de qué datos salen por la API.
- ⬜ Service ← la lógica de negocio.
- ⬜ Controller ← el punto de entrada HTTP.

**Futuro / a evaluar:**
- Creación de un modulo anexo para la gestión de los CREDITOS DE CARBONO
- Alinear el modelo de datos con el módulo básico del estándar VSME.