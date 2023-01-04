*Projeto Prático*

## Decisões de projeto e de arquitetura

- Arquitetura baseada em microsserviços
- 

## Conceitos
- Modelagem estratégica sengindo o DDD (Domain Driven design)
- Clean Architecture


## Tecnologias utilizadas
- Java 17
- Spring boot
- 


## Microserviços Desenvolvidos

### Admin do catálogo de vídeos (backend Java)

O projeto *Admin-do-catalogo* será dividido em 3 módulos: **domain**, **application**, **infrastructure**.

**domain**: é o modulo de mais alto nível, onde contem as regras de negócio, entidades e value objects… não utilizo nenhum framework.

**application**: possui os casos de uso da aplicação, pode conter algum regra de negócio.

**infrastructure**: contem as injeções de dependencia, users inferfaces, gateways

*Obs: Camada de domain não pode conhecer a application. - Application não pode conhecer a infrastructure. - Mas a infrastructure conhece a application que conhece a domain  e a infrastructure pode conhecer a domain.*

Porque separar em módulos um projeto assim?

A idéia é não causar acoplamento/dependência. 

> TODO - Terminar documentação