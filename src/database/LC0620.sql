/*

620. Not Boring Movies

Pattern: Filtering with WHERE

Idea:
- preciso filtrar filmes com id impar e que nao sejam "boring"
- erro que cometi: usei aspas duplas em 'boring' (aspas duplas sao pra
  identificador em Postgres, nao string -- string usa aspas simples)
- segundo erro: comparei 'boring' com a coluna rating, mas rating e
  numerico (a nota do filme) -- o texto boring/not boring fica na
  coluna description, nao em rating
- id % 2 <> 0 -> pega ids impares (% e equivalente a MOD(), so sintaxe
  diferente)
- ORDER BY rating DESC -> ordena pela nota, do maior pro menor

Key trick:
sempre conferir o schema/tipo de cada coluna no enunciado antes de
escrever o filtro -- nome parecido (rating) nao significa que e a
coluna certa pro que voce quer comparar

Table: Cinema
+----------------+----------+
| Column Name    | Type     |
+----------------+----------+
| id             | int      |
| movie          | varchar  |
| description    | varchar  |
| rating         | float    |
+----------------+----------+
 */

select * from Cinema c
where c.id % 2 <> 0 and c.description <> 'boring'
order by c.rating desc;