# InvestiTrace API

## Diagrama Entidade-Relacionamento

```mermaid
erDiagram
    USER ||--o{ ITEM : "cria"
    USER ||--o{ LOCATION : "cria"
    USER ||--o{ PERSON : "cria"
    USER ||--o{ EVENT : "cria"
    USER ||--o{ RELATION : "cria"

    ITEM {
        Long id PK
        Long userId FK
        String name
        String description
        String imageUrl
    }

    LOCATION {
        Long id PK
        Long userId FK
        String name
        String description
        String imageUrl
    }

    PERSON {
        Long id PK
        Long userId FK
        String name
        String description
        String imageUrl
    }

    EVENT {
        Long id PK
        Long userId FK
        String name
        String description
        String imageUrl
        Date date
    }

    RELATION {
        Long id PK
        Long userId FK
        String name
        String originType
        Long originId
        String targetType
        Long targetId
        String description
    }

    PERSON ||--o{ RELATION : "é relacionada com"
    LOCATION ||--o{ RELATION : "é relacionada com"
    ITEM ||--o{ RELATION : "é relacionada com"
    EVENT ||--o{ RELATION : "é relacionada com"

```
