#include<bits/stdc++.h>
using namespace std;
struct maze {
    int x;
    int y;
    int diffx;
    int diffy;
    int parent;
    double distance;

    bool operator < ( const maze& p ) const {  return distance > p.distance;   }
};
string str[30];
vector<maze>v[100000];
vector<pair<int,int>>finalPath;
int visited[35][35], indx=0;
int dx[4] = {-1, 1, 0, 0};
int dy[4] = {0, 0, -1, 1};
bool checkSituation(maze var)
{
    if(visited[var.x][var.y]==1){
        return true;
    }
    return false;
}

double heuristic(int currentX, int currentY, int nextX, int nextY)
{
    return sqrt((currentX-nextX)*(currentX-nextX) + (currentY-nextY)*(currentY-nextY));
}

void printPath(int indx)
{
   if(v[indx][0].parent!=-1){
     printPath(v[indx][0].parent);
     finalPath.push_back(make_pair(v[indx][0].x, v[indx][0].y));
   }
}

bool AStarSearch(int prevX, int prevY, int nextX, int nextY)
{
    for(int i=0;i<100000;i++) v[i].clear();
    priority_queue<maze>q;
    memset(visited, 0, sizeof(visited));
    maze mz;
    mz.x=prevX;
    mz.y=prevY;
    mz.parent=-1;
    mz.distance=heuristic(mz.x, mz.y, nextX, nextY);
    q.push(mz);
    memset(visited, 0,sizeof(visited));
    while(!q.empty()){
        maze var=q.top();
        q.pop();

        if(var.x<0 || var.y<0 || var.x>28 || var.y>28){
            continue;
        }
        if(checkSituation(var)) continue;
        visited[var.x][var.y]=1;
        if(var.x==nextX && var.y==nextY){
            v[indx].push_back(var);
            printPath(indx);
            return true;
        }
        v[indx].push_back(var);
        maze m;
        m.parent=indx;
        for(int k=0;k<4;k++){
            m.x=var.x+dx[k];
            m.y=var.y+dy[k];
            m.diffx=dx[k];
            m.diffy=dy[k];
            if(m.x>=0 && m.x<=28 && m.y>=0 && m.y<=28){
                if(str[m.x][m.y]=='g' || str[m.x][m.y]=='f' || str[m.x][m.y]=='d'){
                    m.distance=heuristic(m.x, m.y, nextX, nextY);
                    q.push(m);
                }
            }
        }
        indx++;
    }
    return false;
}

void FileRead()
{
    std::ifstream inputFile("Maze Paths//NewMaze.txt");

    std::string line;
    int i=0;
    while (std::getline(inputFile, line)) {
        str[i]=line;
        i++;
    }

    inputFile.close();
}

void SaveToFile(int flag)
{
    std::ofstream outputFile("Maze Paths//output.txt");

    if (!outputFile.is_open()) {
        std::cerr << "Error opening the file!" << std::endl;
        return;
    }
    if(flag==1){
        for(int i=0;i<finalPath.size();i++){
         outputFile << finalPath[i].first <<" "<< finalPath[i].second << std::endl;
        }
    }
    outputFile.close();
}

int main()
{
    FileRead();
    int prevX=1, prevY=1;
    for(int i=0;i<30;i++){
        for(int j=0;j<30;j++){
            if(str[i][j]=='d' || (i==28 && j==28)){
                if(AStarSearch(prevX, prevY, i, j)){
                    prevX=i;
                    prevY=j;
                }
            }
        }
    }
    if(prevX==28 && prevY==28) SaveToFile(1);
    else SaveToFile(0);
}
