#include<iostream>
#include<Main.hpp>
using namespace std;

Searchable::Searchable(string name) {
  this->name = name;
}

string Searchable::getName() {
  return this->name;
}

Flag::Flag(string name, string explanation = "") : Searchable(name) {
  if(Flag::exists(name)) {
    throw FlagExistsException(name);
  }
  this->explanation = explanation;
  Flag::nameToObj[name] = this;  
}

bool Flag::exists(string name) {
  if(Flag::nameToObj.count(name) > 0) {
    return true;
  } else {
    return false;
  }
}

Task::Task(string name, string content = "") : Searchable(name) {
  this->content = content;
  Task::nameToObj[name] = this;  
}

bool Task::hasFlag(Flag flag) {
  if(this->checkedFlags.count(flag.name) > 0){
    return true;
  } else {
    return false;
  }
}

void Task::addFlag(Flag flag) {
  if(Task::hasFlag(flag)){
    throw HasFlagException(this, flag)
  }
  checkedFlags[flag.name] = flag;
}

void removeFlag(Flag flag) {
  if(!this->hasFlag()){
    throw NoFlagException(this, flag);
  }
}

int main() {
  //  
}
