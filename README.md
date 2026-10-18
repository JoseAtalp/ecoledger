# SaaS B2B de Gestión de huella de carbono y sostenibilidad para PYMES. 

## Descripción:
- Este proyecto nace como la oportunidad cada vez mas demandada a las PYMES y grandes empresas de aportar los datos acerca de su huella de carbono.
- Facilita a las empresas la creacion de informes de manera rapida y sencilla que faciliten los procesos de auditoria y la persistencia de datos.
- A la vez que orienta, evita el tedioso trabajo de revisar infinitas celdas de excel y hacer los calculos de manera manual, evitando asi posibles errores.

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

## Decisiones de Arquitectura "ADR" (Architecture Decision Record):

### Record vs. Lombok (@Data/@Value) para DTOs inmutables
**Decisión:** 
[Record]
**Alternativas consideradas:** 
[Lombok]
**Razón:**
[El uso Records nos dá esa inmutabilidad a la hora de trabajar con DTOs. Nos aseguraba que el dato no fuera alterado durante el "viaje" o proceso.
Menor uso de lineas de codigo y legibilidad.]

### Autovalidación en el constructor compacto del Record
**Decisión:**
[Autovalidación del record.]
**Alternativas consideradas:**
[Barajamos la posibilidad de validar en el service y posteriormente en el controller]
**Razón:**
[Gracias al _fail-fast_ ningun objeto puede existir en estado invalido. Asi evitamos que ninguna capa clasica entre en juego]

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

##  Estado actual / Roadmap
**Terminado:**
- ✅ Infraestructura ← Spring Boot 4.1 con Maven y PostgreSQL 18 en Docker.
- ✅ DTO (Creación) ← el "contrato" de qué datos entran/salen por la API.
- ✅ Entity ← el "molde" de cómo se guarda en PostgreSQL.

**Pendiente / próximos pasos:**
- ⬜ Repository ← quién lee/escribe esa Entity en la BD.
- ⬜ DTO(Lectura) ←  el "contrato" de qué datos entran/salen por la API
- ⬜ Service ← la lógica de negocio.
- ⬜ Controller ← el punto de entrada HTTP.

**Futuro / a evaluar:**
-  Creación de un modulo anexo para la gestión de los CREDITOS DE CARBONO