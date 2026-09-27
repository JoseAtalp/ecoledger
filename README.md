# SaaS B2B de Gestión de huella de carbono y sostenibilidad para PYMES. 

## Descripción:
- Este proyecto nace como la oportunidad cada vez mas demandada a las PYMES y grandes empresas de aportar los datos acerca de su huella de carbono.
- Facilita a las empresas la creacion de informes de manera rapida y sencilla que faciliten los procesos de auditoria y la persistencia de datos.
- A la vez que orienta, evita el tedioso trabajo de revisar infinitas celdas de excel y hacer los calculos de manera manual, evitando asi posibles errores.

## Stack Tecnológico:
**Back-End:**
- Java 25 LTS 
- Spring Boot 3
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
[El uso Records nos dá esa inmutabildad a la hora de trabajar con DTOs. Nos aseguraba que el dato no fuera alterado durante el "viaje" o proceso.
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

## 🚧 Estado actual / Roadmap
**Terminado:**
- ✅ DTO (Creación) ← el "contrato" de qué datos entran/salen por la API.

**Pendiente / próximos pasos:**
- ⬜ Entity ← el "molde" de cómo se guarda en PostgreSQL.
- ⬜ Repository ← quién lee/escribe esa Entity en la BD.
- ⬜ DTO(Lectura) ← el "contrato" de qué datos entran/salen por la API
- ⬜ Service ← la lógica de negocio.
- ⬜ Controller ← el punto de entrada HTTP .

**Futuro / a evaluar:**
- ⬜ Creación de un modulo anexo para la gestión de los CREDITOS DE CARBONO