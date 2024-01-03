#include<bits/stdc++.h>
using namespace std;
struct maze {
    int x;
    int y;
    int diffx;
    int diffy;
};
string str[30];
vector<maze>v[100000];
vector<pair<int,int>>finalPath, needsVisit;
int visited[35][35], finalVisit[35][35], flag=0, count1=0;
int dx[4] = {-1, 1, 0, 0};
int dy[4] = {0, 0, -1, 1};
bool checkSituation(maze var)
{
    if(visited[var.x][var.y]==1){
        return true;
    }
    return false;
}

bool checkWithPrevious(int x, int y)
{
    for(int i=0;i<finalPath.size();i++){
        if(finalPath[i].first==x && finalPath[i].second==y) return false;
    }
    return true;
}

double heuristic(int currentX, int currentY, int nextX, int nextY)
{
    return sqrt((currentX-nextX)*(currentX-nextX) + (currentY-nextY)*(currentY-nextY));
}

void printPath(vector<pair<int,int>>& m)
{
    for(int i=0;i<m.size();i++){
        finalPath.push_back(make_pair(m[i].first, m[i].second));
    }
}

bool DFS(int currentX, int currentY, int nextX, int nextY, vector<pair<int,int>>& m)
{
    if(currentX<0 || currentX>=29 || currentY<0 || currentY>=29) return false;
    if(currentX==nextX && currentY==nextY && flag==0){
       m.push_back(make_pair(nextX, nextY));
       printPath(m);
       flag=1;
       return true;
    }
    if(currentX==28 && currentY==28) return false;
    if(visited[currentX][currentY]==1) return false;
    count1++;
    visited[currentX][currentY]=1;
    m.push_back(make_pair(currentX, currentY));
    bool value=false;
    for(int k=0;k<4;k++){
        int x=currentX+dx[k];
        int y=currentY+dy[k];
        if(x>=0 && x<=28 && y>=0 && y<=28){
            if(str[x][y]=='g' || str[x][y]=='f' || str[x][y]=='d' || str[x][y]=='s' || str[x][y]=='k'){
                value = value | DFS(x, y, nextX, nextY, m);
            }
        }
    }
    m.pop_back();
    return value;
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
    cout<<flag<<endl;
    if(flag==1){
        for(int i=0;i<finalPath.size();i++){
         outputFile << finalPath[i].first <<" "<< finalPath[i].second << std::endl;
        }
    }
    outputFile << "Expansion " <<count1 << std::endl;
    outputFile.close();
}

void MakeClosestNodesList(int x, int y)
{
    double minimum=1e5;
    int tempX=-1, tempY=-1;
    for(int i=0;i<30;i++)
    {
        for(int j=0;j<30;j++){
            if((str[i][j]=='d' || str[i][j]=='s' || str[i][j]=='k') && finalVisit[i][j]==0){
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
    int prevX=1, prevY=1;
    for(int i=0;i<needsVisit.size();i++){
        memset(visited, 0, sizeof(visited));
        vector<pair<int,int>> m;
        flag=0;
        if(checkWithPrevious(needsVisit[i].first, needsVisit[i].second) && DFS(prevX, prevY, needsVisit[i].first, needsVisit[i].second, m)){
            prevX=needsVisit[i].first;
            prevY=needsVisit[i].second;
        }
    }
    if(prevX==28 && prevY==28) SaveToFile(1);
    else SaveToFile(0);
    cout<<count1<<endl;
}
