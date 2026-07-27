/*
1835 - Promessa de Campanha

Pattern: Graph / DFS / Connected Components

Time: O(V^2)
Space: O(V^2)

Idea:
- O problema pede para verificar se todas as cidades estão conectadas.
- Modelamos como um grafo não direcionado.

- Usamos DFS para contar o número de componentes conexos:
    - Cada vez que iniciamos uma nova DFS em um vértice não visitado,
      encontramos um novo componente.

- Se o grafo tiver:
    - 1 componente → já está totalmente conectado
    - k componentes → precisamos de (k - 1) estradas para conectar tudo

- O resultado final é:
    connectedComponents - 1

Key trick:
- Contar componentes conexos com DFS.
- Em grafos não conectados, o mínimo de arestas para conectar tudo é (k - 1).
*/

#include <stdio.h>
#include <stdlib.h>

enum color {white, gray, black};

typedef struct graph{
    int V;
    int E;
    int **adj;
}Graph;

int **allocateMatrix(int order, int val);
Graph *createGraph (int V);
void addEdge(Graph *G, int v, int w);
void printGraph(Graph *G);
int DFS(Graph *G);
void DFSVisit(Graph *G, int u, int *visited, int *parent, int *d, int *f);

int main(int argc, char const *argv[]){

    int t;
    scanf("%d", &t);

    for(int i = 1; i <= t; i++){
        int n, m;
        scanf("%d", &n);
        scanf("%d", &m);


        Graph *G = createGraph(n);

        for(m; m > 0; m--){
            int x, y;
            scanf("%d %d", &x, &y);

            addEdge(G, x, y);
        }

        // printf("Grafo #%d:\n", i);
        // printGraph(G);
        int connectedComponents = DFS(G);
        // printf("------------------\n");

        printf(connectedComponents-1 > 0 ? "Caso #%d: ainda falta(m) %d estrada(s)\n" : "Caso #%d: a promessa foi cumprida\n", i, connectedComponents-1);

    }
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

void addEdge(Graph *G, int v, int w){
    if(G->adj[v-1][w-1] == 0){
        G->adj[v-1][w-1] = 1;
        G->adj[w-1][v-1] = 1;
        G->E++;
    }
}

void printGraph(Graph *G){
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

int DFS(Graph *G){
    int visited[G->V];
    int parent[G->V];
    int d[G->V];
    int f[G->V];

    int connectedComponents = 0;

    for(int i = 0; i < G->V; i++){
        visited[i] = white;
        parent[i] = -1;
    }

    for(int i = 0; i < G->V; i++){
        if(visited[i] == white){
            connectedComponents++;
            DFSVisit(G, i, visited, parent, d, f);
        }
    }

    return connectedComponents;
}

void DFSVisit(Graph *G, int u, int *visited, int *parent, int *d, int *f){

    static int time = 0;

    time++;
    d[u] = time;
    visited[u] = gray;

    for(int v = 0; v < G->V; v++){
        if(G->adj[u][v] != 0){
            if(visited[v] == white){
                parent[v] = u;
                DFSVisit(G, v, visited, parent, d, f);
            }
        }
    }

    visited[u] = black;
    time++;
    f[u] = time;

}