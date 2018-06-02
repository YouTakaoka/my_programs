#include<stdlib.h>
#include<stdio.h>
#include<string.h>

#define END "###end###"

int readUntil(FILE* fp, char* buf, char str[], const int max);

int main(int argc, char* argv[]){
  FILE* fp = fopen("input.txt", "r");
  if(fp == NULL){
    fprintf(stderr, "Failed to open file.\n");
    return 1;
  }
  
  char buf[100000];
  int len = readUntil(fp, buf, END, 100000);
  //printf("%d\n", len);
  buf[len] = '\0';
  printf("%s\n", buf);
  fclose(fp);
  return 0;
}


/**
* Read from file pointer fp into buffer buf until string str appears
* @ret length of the string (-1 if the string not found or reached to max)
*/
int readUntil(FILE* fp, char* buf, char str[], const int max){
  int l;
  int sl = strlen(str);
  
  for(l=0; fread(buf+l, 1, 1, fp)>0 && l<max; l++){
    if(l+1 >= sl && memcmp(buf+l-sl+1, str, sl)==0){
      return l-sl+1;
    }
  }

  return -1;
}
