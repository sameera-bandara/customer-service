CREATE TABLE IF NOT EXISTS `customer` (
    `customer_id` varchar(255) NOT NULL PRIMARY KEY,
    `first_name` varchar(20),
    `last_name` varchar(20),
    `street` varchar(20),
    `state` varchar(10),
    `city` varchar(10),
    `zip` varchar(6)
)
