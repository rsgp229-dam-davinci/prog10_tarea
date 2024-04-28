CREATE TABLE persons (
    --Los ID de las tablas son UUIDv4 autogenerados con la función indicada
    id uuid primary key default gen_random_uuid(),
    name string not null,
    surname string,
    nif string not null
);

CREATE TABLE vehicles(
    id uuid primary key default gen_random_uuid(),
    plate string not null,
    brand string not null,
    model string,
    year int4,
    kilometers int4,
    notes string,
    -- id es una clave foránea. En caso de borrado, no hacer nada y en caso de actualizar, también actualizar esta referencia.
    ownerId uuid references persons(id) on delete no action on update cascade
);

insert into persons (name, surname, nif)
values
    ('Antonio', 'Moreno','70612286D'),
    ('María','Núñez', '44600625Z'),
    ('Alfredo', 'Pérez', '20144895T'),
    ('Carlos','Árias','33488887J'),
    ('Pedro', 'Lima', '57703002G');

INSERT INTO persons (name, nif)
VALUES
    ('Tech Nova', 'A11420304'),
    ('Berry Electronics', 'B23456789'),
    ('Quantum Dynamics', 'C34567890'),
    ('Orion Software', 'D45678901'),
    ('Peak AI Technologies', 'E56789012');


INSERT INTO vehicles (plate, brand)
VALUES
    ('ABC123', 'Toyota'),
    ('DEF456', 'Ford'),
    ('GHI789', 'Chevrolet'),
    ('JKL012', 'Nissan'),
    ('MNO345', 'Honda'),
    ('PQR678', 'Subaru'),
    ('STU901', 'BMW'),
    ('VWX234', 'Audi'),
    ('YZA567', 'Mercedes'),
    ('BCD890', 'Tesla');