#include<iostream>
#include<list>
#include<map>
using namespace std;

class Searchable {
  /**
   * Class that contains map structure so it can be searched by name.
   */
private:
  string name;
public:
  string getName();
}

class Flag : public Searchable {
private:
  string explanation;
  static map<string, Flag*> nameToObj;
public:
  Flag(string name, string explanation = "");
  statc bool exists(string name);
}

class FlagExistsException : public exception {
private:
  string mes;
  string name; // flag name which has been attempted to create.
public:
  FlagExistsException(string name){
    this->name = name;
    this->mes = "Flag "+ name +" already exists.";
  }
  string getMes(){
    return this->mes;
  }
}

class Task : public Searchable {
private:
  string content;
  map<string, Flag> checkedFlags;
  static map<string, Task*> nameToObj;
public:
  Task(string name, string content = "");
  bool hasFlag(Flag flag);
  void addFlag(Flag flag);
  void removeFlag(Flag flag);
}

class TaskFlagException : public exception {
protected:
  string mes;
  string taskName;
  string flagName;
public:
  TaskFlagException(Task task, Flag flag) {
    this->taskName = task.getName();
    this->flagName = flag.getName();
  }
  string getMes() {
    return this->mes;
  }

}

class HasFlagException : public TaskFlagException {
public:
  HasFlagException(Task task, Flag flag) : TaskFlagException(task, flag) {
    this->mes = "Task "+ taskName + " already has flag "+ this->flagName;
  }
}

class NoFlagException : public TaskFlagException {
public:
  NoFlagException(Task task, Flag flag) : TaskFlagException(task, flag){
    this->mes = "Task "+ taskName + " does not have flag "+ flagName;
  }
}
