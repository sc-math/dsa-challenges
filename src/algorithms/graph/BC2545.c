/*
2545 - Manyfile

Pattern: Graph / Topological Sort / DFS / DAG

Time: O(V^2)
Space: O(V^2)

Idea:
- O problema modela dependências entre arquivos → um grafo direcionado.
- Se existe dependência, criamos uma aresta: u → v (u deve vir antes de v).

- Primeiro verificamos se o grafo possui ciclo usando DFS com cores:
    white → não visitado
    gray  → em processamento
    black → finalizado

- Se encontrarmos um ciclo → não é possível resolver → resposta = -1.

- Caso contrário, o grafo é um DAG:
    - Realizamos uma ordenação topológica.
    - Depois calculamos a maior "altura" (cadeia de dependências) usando DFS.

- A altura representa o maior número de etapas necessárias para processar os arquivos.

Key trick:
- Detectar ciclo com DFS (gray → gray = ciclo).
- Usar topological sort para garantir ordem válida.
- Calcular a maior profundidade (longest path em DAG) com DFS.
*/

#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

enum color {white, gray, black};

typedef struct graph{
    int V;
    int E;
    int **adj;
}Graph;

int MAX(int a, int b){
    return (a > b) ? a : b;
}

int **allocateMatrix(int order, int val){
    int i, j;
    int **m = malloc(order * sizeof(int *));
    for(i = 0; i < order; i++){
        m[i] = malloc(order * sizeof(int *));
        for(j = 0; j < order; j++){
            m[i][j] = val;
        }
    }

    return m;
}

Graph *createGraph (int V){
    Graph *G = malloc(sizeof(Graph));
    G->V = V;
    G->E = 0;
    G->adj = allocateMatrix(V, 0);
    return G;
}

void addEdge(Graph *G, int v, int w){
    if(G->adj[v-1][w-1] == 0){
        G->adj[v-1][w-1] = 1;
        G->E++;
    }
}

void printGraph(Graph *G){
    printf("---------------------------\n");
    int v, w;
    for(v = 0; v < G->V; v++){
        printf("%2d:", v+1);
        for(w = 0; w < G->V; w++){
            if(G->adj[v][w] != 0)
                printf("\t%d", w+1);
        }
        printf("\n");
    }
}

int heightDFS(Graph *G, int v, int *visited){

    visited[v] = true;
    int heightAux = 0;
    for(int w = 0; w < G->V; ++w)
        if(G->adj[v][w] && !visited[w])
            heightAux = MAX(heightAux, heightDFS(G, w, visited));

    return heightAux + 1;
}

bool topologicalUtil(Graph *G, int *num, int v, int *color, int *sort){

    color[v] = gray;

    for(int w = 0; w < G->V; w++){
        if(G->adj[v][w]){
            if (color[w] == gray)
                return true;
            else if(color[w] == white && topologicalUtil(G, num, w, color, sort)){
                return true;
            }
        }
    }

    sort[--(*num)] = v;
    color[v] = black;

    return false;
}

void topologicalSorting(Graph *G){

    bool hasCycle = false;
    int color[G->V];
    int visited[G->V];
    int sort[G->V];
    int numVertex = G->V;
    int maxHeight = 0;

    for(int i = 0; i < G->V; i++){
        color[i] = white;
        visited[i] = false;
        sort[i] = 0;
    }

    for(int i = 0; i < G->V && !hasCycle; ++i)
        if(color[i] != black)
            hasCycle = topologicalUtil(G, &numVertex, i, color, sort);

    if(hasCycle){
        printf("-1\n");
        return;
    }
    for(int i = 0; i < G->V; ++i)
        if(!visited[sort[i]])
            maxHeight = MAX(maxHeight, heightDFS(G, sort[i], visited));

    printf("%d\n", maxHeight);

}

int main(int argc, char const *argv[]){

    int n;

    while(scanf("%d", &n) != EOF){

        Graph *G = createGraph(n);

        for(int i = 1; i <= n; i++){
            int numPriority;
            scanf("%d", &numPriority);

            for(int j = 0; j < numPriority; j++){
                int temp;
                scanf("%d", &temp);
                addEdge(G, temp, i);
            }
        }

        topologicalSorting(G);
    }
    return 0;
}