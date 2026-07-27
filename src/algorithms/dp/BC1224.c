/*
1224 - Cartões

Pattern: Dynamic Programming / Game Strategy / Interval DP

Time: O(n^2)
Space: O(n)

Idea:
- Dois jogadores pegam cartas das extremidades (início ou fim).
- Ambos jogam de forma ótima → problema de jogo competitivo.

- Para cada intervalo [i, f], queremos saber o melhor valor que o jogador atual pode garantir.

- Transição:
    - Escolher esquerda (i):
        cartas[i] + min(
            dp[i+2][f],     // adversário escolhe i+1
            dp[i+1][f-1]    // adversário escolhe f
        )

    - Escolher direita (f):
        cartas[f] + min(
            dp[i+1][f-1],   // adversário escolhe i
            dp[i][f-2]      // adversário escolhe f-1
        )

    - Pegamos o melhor dos dois:
        max(esquerda, direita)

- O uso de min ocorre porque o adversário joga de forma ótima para nos prejudicar.

- A DP é construída por tamanho de intervalo crescente.

Key trick:
- Problema de "game DP" (minimax implícito).
- Alternância de decisões → max (jogador atual) + min (adversário).
- Uso de %10 reduz memória (rolling array).
*/

#include <stdio.h>
#include <stdlib.h>

long long max(long long a, long long b){
    return a > b ? a : b;
}

long long min(long long a, long long b){
    return a < b ? a : b;
}

long long n = 0;

long long main() {

    while(scanf("%lld", &n) != EOF){

        long long cartas[n];

        for(long long i = 0; i < n; i++){
            scanf("%lld", &cartas[i]);
        }

        // for(long long i = 0; i < n; i++){
        //     printf("%ld ", cartas[i]);
        // }
        // printf("\n");


        long long placar[n][10];

        for(long long i = 0; i < n; i++){
            for(long long j = 0; j < 10; j++){
                placar[i][j] = 0;
            }
        }

        for(long long f = 0; f < n; f++){
            for(long long i = f; i >= 0; i--){

                if(f < i){
                    placar[i][f%10] = 0;
                }else{
                    placar[i][f%10] = max(cartas[i] + min(placar[i+2][f%10], placar[i+1][(f-1)%10]),
                                           cartas[f] + min(placar[i+1][(f-1)%10], placar[i][(f-2)%10]));
                }
            }
        }

        // for(long long i = 0; i < n; i++){
        //     for(long long j = 0; j < n; j++){

        //         printf("%ld ", placar[i][j]);
        //     }
        //     printf("\n");
        // }
        printf("%lld\n",placar[0][(n-1)%10]);
    }


    return 0;
}