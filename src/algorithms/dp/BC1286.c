/*
1286 - Motoboy

Pattern: Dynamic Programming / 0-1 Knapsack

Time: O(n * p)
Space: O(n * p)

Idea:
- Cada pedido tem:
    - tempo (valor)
    - quantidade de pizzas (peso)

- O motoboy tem um limite de pizzas que pode carregar (capacidade p).

- Queremos escolher pedidos que maximizem o tempo total (valor),
  sem ultrapassar a capacidade de pizzas.

- Isso é exatamente o problema de Knapsack 0-1:
    - cada item pode ser escolhido no máximo uma vez

- Definição:
    f[i][j] → melhor tempo possível usando os primeiros i pedidos
              com capacidade j

- Transição:
    - Não pega o pedido i:
        f[i][j] = f[i-1][j]

    - Pega o pedido i (se couber):
        f[i][j] = f[i-1][j - peso] + valor

    - Escolhe o melhor:
        max(dos dois)

Key trick:
- Reconhecer padrão de mochila:
    capacidade + itens com peso/valor
- Estrutura clássica de DP 2D.
*/

#include "stdio.h"
#include "stdlib.h"

int n = -1;
int p = 0;

typedef struct pedido
{
    int tempo;
    int qtPizzas;
} Pedido;


int main(int argc, char const *argv[])
{
    while(n != 0){

        scanf("%d", &n);
        if(n == 0) return 0;

        scanf("%d", &p);

        Pedido pedidos[n+1];

        for(int i = 0; i <= n; i++){


            if(i > 0){

                setbuf(stdin, NULL);
                scanf("%d %d", &pedidos[i].tempo, &pedidos[i].qtPizzas);
            }
            else{
                pedidos[i].tempo= 0;
                pedidos[i].qtPizzas = 0;
            }
        }

        // for(int i = 0; i <= n; i++){
        //     printf("Pedido[%d] - Tempo: %d / Quantidades de Pizzas: %d\n", i, pedidos[i].tempo, pedidos[i].qtPizzas);
        // }

        int f[n+1][p+1];

        for(int i = 0; i <= n; i++){
            for(int j = 0; j <= p; j++){
                f[i][j] = 0;
            }
        }


        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= p; j++){

                if(pedidos[i].qtPizzas > j){
                    f[i][j] = f[i - 1][j];
                }
                else if(f[i - 1][j] > f[i - 1][j - pedidos[i].qtPizzas] + pedidos[i].tempo){
                    f[i][j] = f[i - 1][j];
                }else{
                    f[i][j] = f[i - 1][j - pedidos[i].qtPizzas] + pedidos[i].tempo;
                }
            }
        }


        // for(int i = 0; i <= n; i++){
        //     for(int j = 0; j <= p; j++){

        //         printf("%d ", f[i][j]);
        //     }
        //     printf("\n");
        // }

        printf("%d min.\n", f[n][p]);

    }
    return 0;
}