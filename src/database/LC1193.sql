/*
1193. Monthly Transactions I

Pattern: GROUP BY + Conditional Aggregation (CASE WHEN)

Idea:
- preciso agrupar por mes E pais juntos -> GROUP BY com 2 expressoes
- mes nao existe como coluna, precisa extrair de trans_date com
  TO_CHAR(trans_date, 'YYYY-MM')
- trans_count e trans_total_amount sao agregacoes simples (COUNT(*)
  e SUM(amount)) de TODAS as linhas do grupo
- approved_count e approved_total_amount precisam contar/somar so
  as linhas aprovadas, dentro do MESMO grupo -- resolvido com CASE
  WHEN dentro da agregacao: quando a condicao nao bate, o CASE
  retorna NULL implicitamente (sem ELSE), e COUNT/SUM ignoram NULL,
  entao so conta/soma o que realmente e 'approved'

Bug que caí:
- SUM() de um conjunto onde TODAS as linhas viraram NULL (nenhuma
  aprovada) retorna NULL, nao 0 -- diferente de COUNT() que sempre
  retorna numero. Resolvido com COALESCE(sum(...), 0) pra garantir
  0 em vez de NULL quando nao ha nenhuma transacao aprovada no grupo

Key trick:
CASE WHEN dentro de COUNT/SUM = agregacao condicional, permite
calcular varias metricas filtradas diferentes na mesma linha de
resultado sem precisar de subqueries separadas ou UNION

Table: Transactions
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| id            | int     |
| country       | varchar |
| state         | enum    |
| amount        | int     |
| trans_date    | date    |
+---------------+---------+
id is the primary key of this table.
The table has information about incoming transactions.
The state column is an enum of type ["approved", "declined"].
*/

select
    to_char(t.trans_date, 'YYYY-MM') as month,
    t.country,
    count(*) as trans_count,
    count(case when t.state = 'approved' then 1 end) as approved_count,
    sum(t.amount) as trans_total_amount,
    coalesce(sum(case when t.state = 'approved' then t.amount end), 0) as approved_total_amount
from Transactions t
group by to_char(t.trans_date, 'YYYY-MM'), t.country

