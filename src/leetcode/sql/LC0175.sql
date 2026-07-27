/*
175. Combine Two Tables

Pattern: LEFT JOIN

Idea:
- preciso manter TODAS as pessoas da tabela Person, mesmo as que
  nao tem endereco cadastrado (retornando null nesse caso)
- INNER JOIN so retorna quando existe correspondencia nas duas tabelas,
  entao pessoa sem endereco desapareceria do resultado -> errado
- LEFT JOIN resolve isso: pega tudo da tabela da esquerda (Person) e,
  quando nao acha correspondencia na tabela da direita (Address),
  preenche as colunas dela com null em vez de descartar a linha

Key trick:
tabela da esquerda do LEFT JOIN = a que precisa aparecer inteira
no resultado, com ou sem correspondencia na outra

==== INPUT Table ====
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| personId    | int     |
| lastName    | varchar |
| firstName   | varchar |
+-------------+---------+

+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| addressId   | int     |
| personId    | int     |
| city        | varchar |
| state       | varchar |
+-------------+---------+

==== OUTPUT Table ====
+-----------+----------+---------------+----------+
| firstName | lastName | city          | state    |
+-----------+----------+---------------+----------+
 */

SELECT
    p.firstName,
    p.lastName,
    a.city,
    a.state
FROM Person p
LEFT JOIN Address a ON p.personId = a.personID;