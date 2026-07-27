/*
181. Employees Earning More Than Their Managers

Pattern: Self Join

Idea:
- gerente e funcionario estao na mesma tabela (Employee), entao pra
  comparar salario de um com o outro preciso "juntar a tabela com
  ela mesma": uma copia representa o funcionario, outra representa
  o gerente dele
- e1 = funcionario, e2 = gerente
- ON e1.managerId = e2.id -> conecta o managerId do funcionario com
  o id do gerente (linha de e2 que corresponde ao gerente dele)
- depois do join, as colunas de e1 e e2 ficam lado a lado na mesma
  linha, entao da pra comparar e1.salary > e2.salary no WHERE
- sem o self join, so existe o numero solto managerId, sem acesso
  ao salario do gerente pra comparar

Key trick:
alias de coluna (AS Employee) e necessario pq o LeetCode compara o
nome da coluna de saida, nao so os valores -- sem isso o resultado
sai com o nome original da coluna (name) e da erro mesmo com os
valores certos


Table: Employee
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| id          | int     |
| name        | varchar |
| salary      | int     |
| managerId   | int     |
+-------------+---------+
 */

SELECT
    e1.name AS Employee
FROM Employee e1
JOIN Employee e2 ON e1.managerId = e2.id
WHERE e1.salary > e2.salary