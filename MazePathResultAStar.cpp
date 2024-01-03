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
vector<maze>v[10000];
vector<pair<int,int>>finalPath;
int visited[35][35], indx=0, count1=0;
int dx[4] = {-1, 1, 0, 0};
int dy[4] = {0, 0, -1, 1};
bool checkSituation(maze var)
{
    if(visited[var.x][var.y]==1){
        return true;
    }
    return false;
}

double heuristic(int currentX, int currentY)
{
    return sqrt((currentX-28)*(currentX-28) + (currentY-28)*(currentY-28));
}

void printPath(int indx)
{
   if(v[indx][0].parent!=-1){
     printPath(v[indx][0].parent);
     finalPath.push_back(make_pair(v[indx][0].x, v[indx][0].y));
   }
}

void AStarSearch()
{
    priority_queue<maze>q;
    memset(visited, 0, sizeof(visited));
    maze mz;
    mz.x=1;
    mz.y=1;
    mz.parent=-1;
    mz.distance=heuristic(mz.x, mz.y);
    q.push(mz);
    while(!q.empty()){
        maze var=q.top();
        q.pop();

        if(var.x<0 || var.y<0 || var.x>28 || var.y>28){
            continue;
        }
        if(checkSituation(var)) continue;
        count1++;
        visited[var.x][var.y]=1;
        if(var.x==28 && var.y==28){
            v[indx].push_back(var);
            printPath(indx);
            break;
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
                if(str[m.x][m.y]=='g' || str[m.x][m.y]=='f' || str[m.x][m.y]=='d' || str[m.x][m.y]=='s' || str[m.x][m.y]=='k'){
                    m.distance=heuristic(m.x, m.y);
                    q.push(m);
                }
            }
        }
        indx++;
    }
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

void SaveToFile()
{
    std::ofstream outputFile("Maze Paths//output.txt");

    if (!outputFile.is_open()) {
        std::cerr << "Error opening the file!" << std::endl;
        return;
    }

    for(int i=0;i<finalPath.size();i++){
     outputFile << finalPath[i].first <<" "<< finalPath[i].second << std::endl;
    }
    outputFile << "Expansion " <<count1 << std::endl;
    outputFile.close();
}

int main()
{
    FileRead();
    AStarSearch();
    SaveToFile();
}
