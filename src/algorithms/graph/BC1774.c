/*
1774 - Roteadores

Pattern: Graph / Minimum Spanning Tree (MST) / Prim

Time: O(V^2)
Space: O(V^2)

Idea:
- O problema pede para conectar todos os roteadores com o menor custo possível.
- Isso é um problema clássico de Árvore Geradora Mínima (MST).

- O grafo é não direcionado e ponderado (custos das conexões).
- Utilizamos o algoritmo de Prim para construir a MST:
    - 'key' guarda o menor custo para conectar cada vértice
    - 'mstSet' marca os vértices já incluídos na MST
    - 'parent' armazena a estrutura da árvore

- A cada passo:
    - Escolhemos o vértice com menor custo ainda não incluído (minKey)
    - Atualizamos os custos dos vizinhos

- No final, somamos os pesos das arestas da MST.

Key trick:
- MST garante o menor custo total para conectar todos os vértices.
- Prim é eficiente para grafos densos com matriz de adjacência.
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

int main(int argc, char const *argv[]){

    int r, c;
    scanf("%d %d", &r, &c);

    Graph *G = createGraph(r);

    for(int i = 0; i < c; i++){
        int v, w, p;
        scanf("%d %d %d", &v, &w, &p);

        addEdge(G, v-1, w-1, p);
    }

    //printGraph(G);

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
    printf("%d\n", sum);
}