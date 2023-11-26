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
vector<pair<int,int>>finalPath, needsVisit;
int visited[35][35], finalVisit[35][35], indx=0;
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


bool checkWithPrevious(int x, int y)
{
    for(int i=0;i<finalPath.size();i++){
        if(finalPath[i].first==x && finalPath[i].second==y) return false;
    }
    return true;
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

void MakeClosestNodesList(int x, int y)
{
    double minimum=1e5;
    int tempX=-1, tempY=-1;
    for(int i=0;i<30;i++)
    {
        for(int j=0;j<30;j++){
            if(str[i][j]=='d' && finalVisit[i][j]==0){
                double tempValue = heuristic(x, y, i, j);
                if(tempValue<minimum){
                    minimum=tempValue;
                    tempX=i;
                    tempY=j;
                }
            }
        }
    }
    if(tempX!=-1 && tempY!=-1){
        needsVisit.push_back(make_pair(tempX, tempY));
        finalVisit[tempX][tempY]=1;
        MakeClosestNodesList(tempX, tempY);
    }
}

int main()
{
    FileRead();
    memset(finalVisit, 0, sizeof(finalVisit));
    finalVisit[1][1]=1;
    MakeClosestNodesList(1, 1);
    needsVisit.push_back(make_pair(28, 28));
    cout<<needsVisit.size()<<endl;
    int prevX=1, prevY=1;
    for(int i=0;i<needsVisit.size();i++){
        if(checkWithPrevious(needsVisit[i].first, needsVisit[i].second) && AStarSearch(prevX, prevY, needsVisit[i].first, needsVisit[i].second)){
            prevX=needsVisit[i].first;
            prevY=needsVisit[i].second;
        }
    }
    cout<<prevX<<" "<<prevY<<endl;
    if(prevX==28 && prevY==28) SaveToFile(1);
    else SaveToFile(0);
}
