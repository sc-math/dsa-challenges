/*
 1141. User Activity for the Past 30 Days I

Pattern: Date Range Filter + GROUP BY + COUNT DISTINCT

Idea:
- preciso contar usuarios UNICOS ativos por dia, dentro de uma janela
  fixa de 30 dias terminando em 2019-07-27 inclusive (comeca em
  2019-06-28)
- primeira tentativa deu errado porque nao filtrei o periodo -- tabela
  tinha atividade fora da janela (2019-06-25) que nao deveria entrar
- WHERE ... BETWEEN filtra as linhas ANTES de agrupar, reduzindo o
  volume que entra no GROUP BY
- COUNT(DISTINCT user_id) e essencial aqui: um mesmo usuario pode ter
  varias linhas de atividade no mesmo dia (open_session, scroll_down,
  end_session, etc), COUNT(*) contaria cada atividade, nao cada
  usuario -- preciso contar o usuario uma vez por dia, nao por evento

Key trick:
"periodo de N dias terminando em DATA inclusive" = comeca em
DATA - (N-1) dias, nao DATA - N -- contar os dois extremos

 Table: Activity
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| user_id       | int     |
| session_id    | int     |
| activity_date | date    |
| activity_type | enum    |
+---------------+---------+
This table may have duplicate rows.
The activity_type column is an ENUM (category) of type ('open_session', 'end_session', 'scroll_down', 'send_message').
The table shows the user activities for a social media website.
Note that each session belongs to exactly one user.
 */

select
    a.activity_date as day,
    count(distinct a.user_id) as active_users
from Activity a
where a.activity_date between '2019-06-28' and '2019-07-27'
group by a.activity_date;