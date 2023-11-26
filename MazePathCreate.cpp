#include<bits/stdc++.h>
using namespace std;
int main()
{
    srand(time(0));
    string str[30];
    vector<pair<int,int>>v;
    for(int i=0;i<30;i++){
        string str1="";
        for(int j=0;j<30;j++){
            if(i==0 || i==29 || j==0 || j==29){
                str1+='w';
                continue;
            }
            int x=rand()%5;
            if(x==0) str1+='w';
            else str1+='g';
            if(str1[j]=='g' && i!=1 && j!=1){
                v.push_back(make_pair(i,j));
            }
        }
        str[i]=str1;
    }
    int x=rand()%30, n=v.size();
    for(int i=0;i<max(x,10);i++){
        int p=rand()%n;
        str[v[p].first][v[p].second]='d';
    }
    str[28][28]='f';
    std::ofstream outputFile("Maze Paths//NewMaze.txt");

    for(int i=0;i<30;i++){
        outputFile << str[i] << std::endl;
    }

    outputFile.close();
}
