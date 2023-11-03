# <h1 align="center"> desafio-votes-session </h1>
### <h2>Sistema de votação</h2>

Esta é uma API Rest com objetivo de realizar votação em determinadas pautas por meio de sessões.<br/>

Esta API foi desenvolvida utilizando Java 17 e o framework Spring Boot.<br/>
Os testes unitários foram feitos utilizando JUnit 5 e Mockito.<br/>
Foi utilizado o Banco de Dados H2 para armazenamento dos dados.<br/>
A API foi desenvolvida toda em inglês por motivos de preferência.<br/>

### <h2>Requisitos iniciais</h2>

* Cadastrar uma nova pauta.
* Abrir uma sessão de votação em uma pauta (a sessão de votação deve ficar aberta por um tempo
determinado na chamada de abertura ou 1 minuto por default).
* Receber votos dos associados em pautas (os votos são apenas 'Sim'/'Não'. Cada associado é
identificado por um id único e pode votar apenas uma vez por pauta);
Contabilizar os votos e dar o resultado da votação na pauta. 

### <h2>Executando a solução</h2>

Para executar localmente:
1. Verifique se está com o ambiente configurado para o Maven e Java 17;
2. Clone o repositório na sua máquina;
3. Execute o comando mvn clean, mvn install;
4. Na pasta _/target/_ execute o comando **java -jar sicreditest-0.0.1-SNAPSHOT.jar**.

Após realizar estes passos, a aplicação estará rodando e será possível testá-la localmente utilizando sua plataforma de envio de requisição para API desejada.

### Criar uma nova pauta
```bash
curl --location 'http://localhost:8080/api/v1/topics/' \
--header 'Content-Type: application/json' \
--data '{
    "description": "Topic to be voted"
}'
```

### Criar uma nova sessão usando o ID da pauta gerado no passo anterior
```bash
curl --location 'http://localhost:8080/api/v1/sessions/' \
--header 'Content-Type: application/json' \
--data '{
    "topic": {
        "id":"replace with the topic ID"
        }
}'
```

### Criar um novo associado
```bash
curl --location 'http://localhost:8080/api/v1/associates/' \
--header 'Content-Type: application/json' \
--data '{
    "name": "John Doe",
    "cpf": "12332112312"
}'
```

### Votar (usar o ID do associado e o ID da sessão)
```bash
curl --location 'http://localhost:8080/api/v1/votes/' \
--header 'Content-Type: application/json' \
--data '{
    "associate": {
        "id":"replace with the asssociate ID"
    },
    "session": {
        "id":"replace with the session ID"
    },
    "vote": "YES"
}'
```

### Buscar resultado da votação
```bash
curl --location 'http://localhost:8080/api/v1/sessions/votesCount/{sessionID}'
```

### <h2>Tarefa Bonus 1: Integração com sistemas externos</h2>

O sistema externo fornecido estava fora do ar, impossibilitando testar. Porém, foi implementado o **_CpfValidationService_**, o qual realizaria as ações solicitadas.<br/>

### <h2>Tarefa Bonus 2: Mensageria e filas</h2>

Não implementado ainda.<br/>

### <h2>Tarefa Bonus 3: Performance</h2>

Não implementado.<br/>

### <h2>Tarefa Bonus 4: Versionamento da API</h2>

Há diversas formas de versionamento de API amplamente utilizadas atualmente. Nesse projeto, foi optado por utilizar o versionamento pela URL (**/api/v1/example**),
deixando explícita qual versão está sendo acessada.<br/>
