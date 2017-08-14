#include<iostream>
#include<Main.hpp>
using namespace std;

// class Searchable

Searchable::Searchable(string name) {
  this->name = name;
}

string Searchable::getName() {
  return this->name;
}

// class Flag

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

// class Task

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

void Task::removeFlag(Flag flag) {
  if(!this->hasFlag()){
    throw NoFlagException(this, flag);
  }
}

// class TaskFlagException
TaskFlagException::TaskFlagException(Task task, Flag flag) {
  this->taskName = task.getName();
  this->flagName = flag.getName();
}

string TaskFlagException::getMes() {
  return this->mes;
}

// class HasFlagException
HasFlagException::HasFlagException(Task task, Flag flag) : TaskFlagException(task, flag) {
  this->mes = "Task "+ taskName + " already has flag "+ this->flagName;
}

// class NoFlagException
NoFlagException::NoFlagException(Task task, Flag flag) : TaskFlagException(task, flag){
  this->mes = "Task "+ taskName + " does not have flag "+ flagName;
}

// main

int main() {
  //  
}
