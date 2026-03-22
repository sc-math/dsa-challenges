/*
1790 - Detectando Pontes

Pattern: Graph / DFS / Bridges (Tarjan)

Time: O(V^2)
Space: O(V^2)

Idea:
- O problema pede para encontrar quantas arestas são "pontes" no grafo.
- Uma ponte é uma aresta que, se removida, aumenta o número de componentes conexos.

- Usamos DFS com controle de tempo para cada vértice:
    discovery[v] → tempo de descoberta
    low[v] → menor tempo alcançável a partir de v (back-edge)

- Durante a DFS:
    - Atualizamos discovery e low.
    - Para cada vizinho:
        - Se já visitado → atualiza low com discovery
        - Se não visitado → DFS recursivo

- Após visitar um filho w:
    - Se low[w] > discovery[v]
        → não existe caminho alternativo → aresta (v, w) é ponte

Key trick:
- Usar o conceito de low-link (low[v]).
- Comparação low[w] > discovery[v] identifica pontes.
- Ignorar a aresta que volta para o pai na DFS.
*/

#include <stdio.h>
#include <stdlib.h>

#define MIN(a,b) (((a)<(b))?(a):(b))

enum{
    WHITE,
    GRAY,
    BLACK
};

typedef struct graph{
    int V;
    int E;
    int **adj;
}Graph;

int **allocateMatrix(int order, int val);
Graph *createGraph (int V);
void addEdge(Graph *G, int v, int w);
void printGraph(Graph *G);
void DFS(Graph *G, int v, int *visited, int *discovery, int *lowExplorerTime, int parent, int *bridges);
void findBridges(Graph *G);

int main(int argc, char const *argv[]){

    int c, p;

    while(scanf("%d %d", &c, &p) != EOF){
        Graph *G = createGraph(c);

        for(int i = 0; i < p; i++){
            int x, y;
            scanf("%d %d", &x, &y);
            addEdge(G, x, y);
        }

        // printGraph(G);

        findBridges(G);
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

void findBridges(Graph *G){

    int bridges = 0;

    int parent = -1;
    int visited[G->V];
    int discovery[G->V];
    int lowExplorerTime[G->V];

    for(int i = 0; i < G->V; i++){
        visited[i] = WHITE;
        discovery[i] = -1;
        lowExplorerTime[i] = -1;
    }

    for(int i = 0; i < G->V; i++){
        if(visited[i] == WHITE){
            DFS(G, i, visited, discovery, lowExplorerTime, parent, &bridges);
        }
    }

    printf("%d\n", bridges);
}

void DFS(Graph *G, int v, int *visited, int *discovery, int *lowExplorerTime, int parent, int *bridges){

    static int time = 0;

    visited[v] = GRAY;
    time++;
    discovery[v] = time;
    lowExplorerTime[v] = time;

    for(int w = 0; w < G->V; w++){
        if(G->adj[v][w] != 0){

            if(w == parent)
                continue;

            if(visited[w] != WHITE)
                lowExplorerTime[v] = MIN(lowExplorerTime[v], discovery[w]);

            else{
                parent = v;
                DFS(G, w, visited, discovery, lowExplorerTime, parent, bridges);

                lowExplorerTime[v] = MIN(lowExplorerTime[v], lowExplorerTime[w]);

                if(lowExplorerTime[w] > discovery[v])
                    (*bridges)++;

            }
        }
    }

    visited[v] = BLACK;
    time++;
}