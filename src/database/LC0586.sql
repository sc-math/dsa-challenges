/*
586. Customer Placing the Largest Number of Orders

Pattern: GROUP BY + ORDER BY + LIMIT

Idea:
- preciso saber quantos pedidos cada cliente fez, entao agrupo por
  customer_number e conto quantas linhas caem em cada grupo (COUNT(*))
- nao da pra usar WHERE MAX(...) porque agregacao (MAX, COUNT, etc)
  nao funciona dentro de WHERE, precisa agrupar primeiro
- como o enunciado garante que existe exatamente 1 cliente com o
  maior numero de pedidos (sem empate), ORDER BY count(*) DESC +
  LIMIT 1 e seguro -- se pudesse ter empate, precisaria de outra
  abordagem (tipo HAVING count(*) = subquery do max)

Key trick:
ORDER BY COUNT(*) DESC + LIMIT 1 pega o grupo com maior contagem sem
precisar calcular o maximo separado antes

Table: Orders
+-----------------+----------+
| Column Name     | Type     |
+-----------------+----------+
| order_number    | int      |
| customer_number | int      |
+-----------------+----------+
order_number is the primary key (column with unique values) for this table.
This table contains information about the order ID and the customer ID.
 */

select
    o.customer_number
from Orders o
group by o.customer_number
    order by count(*) desc
                      limit 1;