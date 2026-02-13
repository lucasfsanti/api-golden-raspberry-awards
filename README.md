# Golden Raspberry Awards - Worst Movie API
API RESTful com os indicados à categoria de Pior Filme da Framboesa de Ouro (Golden Raspberry Awards - Worst Movie).

## Sobre
A API avalia o intervalo entre os filmes vencedores do prêmio de Pior Filme de cada produtor, e retorna os produtores com maior e menor intervalo entre premiações.

Ao iniciar a aplicação, os dados dos filmes indicados são lidos do arquivo `src/main/resources/Movielist.csv` e inseridos no banco de dados em memória H2.

## Utilizando
### Requisitos
Este projeto requer Java 8 e Maven 4 instalados.

### Clonando o projeto: 
```
git clone https://github.com/lucasfsanti/api-golden-raspberry-awards
```

### Rodar o projeto:
```
mvn spring-boot:run
```

### Rodar testes de integração:
```
mvn -Dtest=ProducerControllerTest test
```

## Endpoints da API:
**Request**

`GET /golden-raspberry-awards/worst-movie/producers/awards-range`
```
curl 'localhost:8080/golden-raspberry-awards/worst-movie/producers/awards-range'
```

**Resposta**
```javascript
{
    "min": [
        {
            "producer": "Producer 1",
            "interval": 1,
            "previousWin": 2008,
            "followingWin": 2009
        },
        {
            "producer": "Producer 2",
            "interval": 1,
            "previousWin": 2018,
            "followingWin": 2019
        }
    ],
    "max": [
        {
            "producer": "Producer 1",
            "interval": 99,
            "previousWin": 1900,
            "followingWin": 1999
        },
        {
            "producer": "Producer 2",
            "interval": 99,
            "previousWin": 2000,
            "followingWin": 2099
        }
    ]
}
```
