#include<iostream>
#include<list>
using namespace std;

class Task {
private:
  string name;
  string content;
  list<::Frag> checkedFrags;
public:
  Task(string name, string content = "");
}

class Frag {
private:
  string name;
public:
  Frag(string name);
}
