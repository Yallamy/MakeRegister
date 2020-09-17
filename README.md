# MakeRegister

Execução da API REST:
```
Executar a classe RegisterAPI.class
```

Execução do Angular:
```
Executar o seguinte comando no node.js: 
npm install -g npm 
npm install -g @angular/cli
npm install --save-dev @angular-devkit/build-angular
ng serve
```


Swagger:
```
http://localhost:8080//swagger-ui.html

```


Criar uma pessoa:

**POST http://localhost:8080/api/v1/register/pessoa/** 

Body:
```
{
    "nome": "Dom Pedro I",
    "genero": "Masculino",
    "email": "dompedro@gmail.com",
    "dtNascimento": "1970-10-01",
    "naturalidade": "Rio de Janeiro",
    "nacionalidade": "Brasil",
    "cpf": "63334342212"
}
```

Alterar uma pessoa:

**PUT http://localhost:8080/api/v1/register/pessoa/{id}**

Exemplo:
```
http://localhost:8080/api/register/pessoa/1
```
Body:
```
{
    "nome": "Dom Pedro II",
    "genero": "Masculino",
    "email": "dompedro@gmail.com",
    "dtNascimento": "1970-10-01",
    "naturalidade": "Rio de Janeiro",
    "nacionalidade": "Brasil",
    "cpf": "63334342212"
```


Recuperar uma pessoa:

**GET http://localhost:8080/api/v1/register/pessoa/{id}**

Exemplo:
```
http://localhost:8080/api/register/pessoa/1
```

Deletar uma pessoa:

**DELETE http://localhost:8080/api/v1/register/pessoa/{id}**

Exemplo:
```
http://localhost:8080/api/register/pessoa/1
```

Listar pessoas de acordo com os filtros:

**GET http://localhost:8080/api/v1/register/pessoa/** 

Body - filtros possíveis:
```
{
    "id": 1,
    "nome": "Dom Pedro II",
    "genero": "Masculino",
    "email": "dompedro@gmail.com",
    "dtNascimento": "1970-10-01",
    "naturalidade": "Rio de Janeiro",
    "nacionalidade": "Brasil",
    "cpf": "63334342212"
}
```
