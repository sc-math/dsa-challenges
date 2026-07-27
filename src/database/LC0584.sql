/*

584. Find Customer Referee

Pattern: Filtering with WHERE + NULL handling

Idea:
- preciso pegar clientes que NAO foram indicados pelo cliente de id = 2
- se filtrar so com referee_id <> 2, clientes com referee_id NULL
  desaparecem do resultado -- em SQL, qualquer comparacao (<>, =, etc)
  com NULL retorna NULL (nem verdadeiro nem falso), entao o WHERE
  descarta essas linhas
- por isso preciso do OR c.referee_id IS NULL, pra incluir de volta
  os clientes sem referee cadastrado

Key trick:
nunca comparar com = ou <> quando pode ter NULL envolvido -- sempre
tratar NULL separadamente com IS NULL / IS NOT NULL

Table: Customer
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| id          | int     |
| name        | varchar |
| referee_id  | int     |
+-------------+---------+
 */

select
    c.name
from Customer c
where c.referee_id <> 2 OR c.referee_id IS NULL;