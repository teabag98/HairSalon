# The Hair salon
A java application which enables user to add new stylists ,add new clients to specific stylist and update both.

## Technologies and frameworks used
    1. java 8
    2. Postgres database
    3. spark core 2.12
    4. Gradle 4.10
    5. Template Velocity
    5. Junit 5
    6. sql


## Database

Run PSQL:

     CREATE DATABASE hair_salon;
     CREATE TABLE stylists(id int,name varchar,contacts int,description varchar);
     CREATE TABLE clients(id int,name varchar,,phone varchar,email varchar,stylistid int);

## Testing

   ```java
    gradle test
```


## License
Licenced under [MIT](LICENSE)