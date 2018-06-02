#include<stdio.h>
#include<unistd.h>
#include<fcntl.h>
#include<curses.h>

int main(){
  int x = 10;
  int y = 10;
  char buf[1];
  fcntl(STDIN_FILENO, F_SETFL, O_NONBLOCK);
  while(1){
    //read(STDIN_FILENO, buf, 1);
    buf[0] = getch();
    switch(buf[0]){
    case 'h':
      x--;
      break;
    case 'l':
      x++;
      break;
    }
    printf("\033[2J");  //clear screen
    printf("\033[%d;%dH", y, x);  //move cursor
    printf("A\n");
    usleep(50000);
  }

  return 0;
}
