# Similar Products API

API REST desarrollada en **Spring Boot 4**, **Java 17** y **Maven**, que permite consultar productos similares a partir del identificador de un producto base.

## 📌 Descripción

Dado un `productId`, la API retorna una lista de productos similares, incluyendo información detallada como:

- id
- name
- price
- availability

## 🛠️ Tecnologías

- Java 17
- Spring Boot 4
- Maven 3.9+
- REST API


---

## 🚀 Endpoints

### Obtener productos similares

**GET** `/product/{productId}/similar`

#### Parámetros
| Nombre | Tipo | Descripción |
|------|------|-------------|
| productId | String | ID del producto base |

#### Ejemplo de Request

`GET http://localhost:5000/product/1/similar`

#### Ejemplo de Response

```
{
    "similarProducts": [
        {
            "availability": true,
            "id": "2",
            "name": "Dress",
            "price": 19.99
        },
        {
            "availability": false,
            "id": "3",
            "name": "Blazer",
            "price": 29.99
        },
        {
            "availability": true,
            "id": "4",
            "name": "Boots",
            "price": 39.99
        }
    ]
}
```

### Configuracion local
```
git clone https://github.com/jedaro/AppProducts.git

cd AppProducts

mvn clean install

mvn spring-boot:run

java -jar target/AppProducts-0.0.1-SNAPSHOT.jar

```
### Acceso app
```
 http://localhost:5000/product/{productId}/similar
```


