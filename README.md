# InvestiTrace API

## Diagrama Entidade-Relacionamento

```mermaid
erDiagram
    USUARIO ||--o{ PESSOA : "cria"
    USUARIO ||--o{ LOCAL : "cria"
    USUARIO ||--o{ ITEM : "cria"
    USUARIO ||--o{ ACONTECIMENTO : "cria"

    PESSOA {
        UUID id PK
        UUID usuario_id FK
        string nome
        text descricao
        string imagem
    }

    LOCAL {
        UUID id PK
        UUID usuario_id FK
        string nome
        text descricao
        string imagem
    }

    ITEM {
        UUID id PK
        UUID usuario_id FK
        string nome
        text descricao
    }

    ACONTECIMENTO {
        UUID id PK
        UUID usuario_id FK
        string nome
        text descricao
        date data
    }

    ACONTECIMENTO ||--o{ ACONTECIMENTO_PESSOA : "envolve"
    ACONTECIMENTO ||--o{ ACONTECIMENTO_LOCAL : "ocorre em"
    ACONTECIMENTO ||--o{ ACONTECIMENTO_ITEM : "utiliza"

    ACONTECIMENTO_PESSOA {
        UUID id PK
        UUID acontecimento_id FK
        UUID pessoa_id FK
    }

    ACONTECIMENTO_LOCAL {
        UUID id PK
        UUID acontecimento_id FK
        UUID local_id FK
    }

    ACONTECIMENTO_ITEM {
        UUID id PK
        UUID acontecimento_id FK
        UUID item_id FK
    }

```
