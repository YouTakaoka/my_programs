#include<stdio.h>
#include<unistd.h>
#include<fcntl.h>
#include<ncurses.h>

int main(){
  int x = 10;
  int y = 10;
  int buf;

  WINDOW* win = initscr();
  cbreak();
  noecho();
  nodelay(win, TRUE);
  
  while(1){
    buf = getch();

    if(buf == 'q') break;
    
    switch(buf){
    case 'h':
      x--;
      break;
    case 'l':
      x++;
      break;
    case 'j':
      y++;
      break;
    case 'k':
      y--;
      break;
    }
    printf("\033[2J");  //clear screen
    printf("\033[%d;%dH", y, x);  //move cursor
    printf("A\n");
    usleep(50000);
  }
  endwin();

  return 0;
}
