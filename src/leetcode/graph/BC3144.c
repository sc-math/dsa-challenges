/*
3144 - G de Grafo

Pattern: Graph / Minimum Spanning Tree (MST) / Prim

Time: O(V^2)
Space: O(V^2)

Idea:
- O problema pede para conectar todos os vértices com o menor custo possível,
  o que caracteriza um problema de Árvore Geradora Mínima (MST).

- O grafo é representado por matriz de adjacência.
- Utilizamos o algoritmo de Prim para construir a MST:
    - Mantemos um array 'key' com o menor custo para conectar cada vértice.
    - Um array 'mstSet' para marcar os vértices já incluídos na MST.
    - Um array 'parent' para guardar a estrutura da árvore.

- A cada passo:
    - Escolhemos o vértice com menor custo ainda não visitado (minKey).
    - Atualizamos os vizinhos com custos menores.

- Ao final, somamos os pesos das arestas da MST.

Key trick:
- Usar Prim com matriz de adjacência simplifica a implementação.
- O resultado é multiplicado por 2, pois o problema exige o custo de ida e volta.
*/

#include <stdio.h>
#include <stdlib.h>

typedef struct graph{
    int V;
    int E;
    int **adj;
}Graph;

int **allocateMatrix(int order, int val);
Graph *createGraph (int V);
void addEdge(Graph *G, int v, int w, int val);
void printGraph(Graph *G);
void primMST(Graph *G);
int minKey(Graph *G, int* key, int* mstSet);
void printMST(Graph *G, int* parent);
void sumMST(Graph *G, int* parent);

int main(int argc, char const *argv[])
{
    int vertex, edges;
    scanf("%d %d", &vertex, &edges);

    int initialClass;
    scanf("%d", &initialClass);

    Graph *G = createGraph(vertex);

    for(int i = 0; i < edges; i++){
        int u, v, d;
        scanf("%d %d %d", &u, &v, &d);

        addEdge(G, u-1, v-1, d);
    }

    primMST(G);

    return 0;
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

void addEdge(Graph *G, int v, int w, int val){
    if( v != w && G->adj[v][w] == 0){
        G->adj[v][w] = val;
        G->adj[w][v] = val;
        G->E++;
    }
}

void printGraph(Graph *G){
    int v, w;
    for(v = 0; v < G->V; v++){
        printf("%2d:", v+1);
        for(w = 0; w < G->V; w++){
            if(G->adj[v][w] != 0)
                printf("(%d, %d) -> %2d\t ", v+1, w+1, G->adj[v][w]);
        }
        printf("\n");
    }
}

void primMST(Graph *G){

    int parent[G->V];
    int key[G->V];
    int mstSet[G->V];

    for(int i = 0; i < G->V; i++){
        key[i] = 999999;
        mstSet[i] = 0;
    }

    key[0] = 0;
    parent[0] = -1;

    for(int count = 0; count < G->V - 1; count++){

        int u = minKey(G, key, mstSet);

        mstSet[u] = 1;

        for(int v = 0; v < G->V; v++){

            if(G->adj[u][v] && mstSet[v] == 0 && G->adj[u][v] < key[v]){
                parent[v] = u;
                key[v] = G->adj[u][v];
            }
        }
    }

    //printMST(G, parent);
    sumMST(G, parent);
}

int minKey(Graph *G, int* key, int* mstSet){
    int min = 999999;
    int min_index;

    for(int v = 0; v < G->V; v++){
        if(mstSet[v] == 0 && key[v] < min){
            min = key[v];
            min_index = v;
        }
    }

    return min_index;
}

void printMST(Graph *G, int* parent){
    printf("Aresta \tValor\n");
    for(int i = 1; i < G->V; i++){
        printf("(%d, %d) \t  %d \n", parent[i]+1, i+1, G->adj[i][parent[i]]);
    }
}

void sumMST(Graph *G, int* parent){
    int sum = 0;
    for(int i = 1; i < G->V; i++){
        sum += G->adj[i][parent[i]];
    }
    printf("%d\n", 2*sum);
}