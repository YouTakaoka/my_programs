#include<stdio.h>

int main(){
  char buf[64];
  printf("Enter your name: ");
  scanf("%s\n", buf);
  printf("Hi, %s!\n", buf);
  return 0;
}
