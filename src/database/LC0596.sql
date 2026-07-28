/*
596. Classes With at Least 5 Students

Pattern: GROUP BY + HAVING

Idea:
- preciso saber quantos estudantes cada turma tem, entao agrupo por
  class e conto quantas linhas caem em cada grupo (COUNT(*))
- COUNT(*) e seguro aqui porque (student, class) e a primary key da
  tabela -- garante que nao existe linha duplicada de mesmo estudante
  na mesma turma, entao a contagem de linhas = contagem de estudantes
  unicos
- HAVING (nao WHERE) porque preciso filtrar DEPOIS de agrupar/contar,
  WHERE nao teria acesso ao resultado da agregacao

Key trick:
sempre conferir o schema/primary key antes de assumir que COUNT(*)
representa "contagem de entidades unicas" -- so e seguro quando a
constraint da tabela garante ausencia de duplicata

Table: Courses
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| student     | varchar |
| class       | varchar |
+-------------+---------+
(student, class) is the primary key (combination of columns with unique values) for this table.
Each row of this table indicates the name of a student and the class in which they are enrolled.
 */

select
    c.class
from Courses c
group by c.class
having count(*) > 4;