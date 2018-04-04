#include<stdio.h>
#include<stdlib.h>

#define BUF_SIZE 1000000

int main(int argc, char* argv[]){
  // Validation of command line arguments
  if(argc != 3){
    printf("div - divide file at the specified position(byte)\n");
    printf("usage: div file position\n");
    exit(1);
  }
  char* fname = argv[1];
  int pos = atoi(argv[2]);

  // Open file
  FILE* fp = fopen(fname, "r");
  if(fp == NULL){
    printf("Failed to open file.\n");
    goto exit;
  }

  // Read from file
  char buf[BUF_SIZE];
  int len = fread(buf, 1, pos, fp);
  if(len != pos){
    printf("Failed to read file.\n");
    goto exit;
  }

  // Names of output files
  char out1[100];
  char out2[100];
  sprintf(out1, "%s-0", fname);
  sprintf(out2, "%s-1", fname);
  
  // Create the first output file
  FILE* ofp1 = fopen(out1, "w");
  len = fwrite(buf, 1, pos, ofp1);
  if(len != pos){
    printf("Failed to write the first output file.\n");
    fclose(ofp1);
    goto exit;
  }
  fclose(ofp1);  

  // Read the remaining part
  len = fread(buf, 1, BUF_SIZE, fp);
  if(len <= 0){
    printf("Failed to read the remaining part of file.\n");
    goto exit;
  }

  // Create the second output file
  FILE* ofp2 = fopen(out2, "w");
  fwrite(buf, 1, len, ofp2);
  fclose(ofp2);  

  // Close input file
  fclose(fp);

  return 0;
  
 exit: // Close file and exit with exit status 1
  // Close file
  fclose(fp);
  return 1;
}
