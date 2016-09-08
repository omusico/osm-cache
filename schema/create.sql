
CREATE TABLE tile_image(
        x INT NOT NULL,
        y INT NOT NULL,
        zoom INT NOT NULL,
        image MEDIUMBLOB NOT NULL,
        created BIGINT NOT NULL,
        PRIMARY KEY(x, y, zoom)
) ENGINE = InnoDB, DEFAULT CHARSET=utf8mb4;
