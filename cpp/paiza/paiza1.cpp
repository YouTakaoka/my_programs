#include<stdio.h>


int main(int argc, char* argv[]){
  int h,w,n;
  scanf("%d %d %d\n",&h,&w,&n);
  int t[h][w];
  for(int i=0;i<h; i++){
    for(int j=0; j<w; j++){
      scanf("%d ",&t[i][j]);
    }
  }
  int l;
  scanf("%d\n", &l);
  int a,b,A,B;
  int player[n];
  for(int i=0;i<n; i++){
    player[i]=0;
  }

  //  printf("l=%d\n",l);
  
  int j=0;
  while(j<l){
    int i=0;
    while(i<n && j<l){ // i:player
      //  printf("data: %d player: %d\n", j,i);
      scanf("%d %d %d %d\n", &a, &b, &A, &B);
      if(t[a-1][b-1]==t[A-1][B-1]){
        player[i]+=2;
      }else{
        i++;
      }
      j++;
    }
  }

  // output
  for(int i=0; i<n; i++){
    printf("%d\n", player[i]);
  }
  return 0;
}
