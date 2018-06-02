#include<stdio.h>
#include<unistd.h>

int main(){
  for(int i=1; i<10; i++){
    printf("\033[2J");  //clear screen
    printf("\033[%d;%dH", i, i);  //move cursor
    printf("hello!\n");
    usleep(50000);
  }
  printf("\033[2J");  //clear screen
  printf("\033[1;1H");  //move cursor
  return 0;
}
