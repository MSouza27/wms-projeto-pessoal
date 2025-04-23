# 📦 WMS - Sistema de Gestão de Armazém (Recebimento)

Este projeto é um módulo de um **sistema WMS (Warehouse Management System)**, focado na **gestão de recebimentos de produtos**. Desenvolvido em **Java com Spring Boot**, ele automatiza a conferência de volumes e unidades, cálculo de divergências e controle de estoque com base nos dados recebidos.

---

## 🚀 Tecnologias Utilizadas

- Java 17
- Spring Boot 3
- Spring Data JPA
- MySQL
- API REST
- Jakarta Validation (em breve)
- Lombok

---

## 📚 Funcionalidades

- Cadastro de recebimento de produto via API REST
- Cálculo automático de divergência entre sistema e produto conferido
- Mensagens de status do recebimento
- Controle de datas: validade, entrada e movimentação
- Persistência com Spring Data JPA
- Lógica de liberação de produto para o estoque

---

## 🔧 Como Executar o Projeto

### Pré-requisitos

- Java 17+
- Maven
- MySQL (com banco configurado e rodando)

### Passos

```bash
# Clone o repositório
git clone https://github.com/MSouza27/wms-projeto-pessoal
cd wms-projeto-pessoal

# Configure o application.properties com as credenciais do seu MySQL

# Execute o projeto
./mvnw spring-boot:run
