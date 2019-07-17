
#ifndef TEST_EMPLOYEECPP_H
#define TEST_EMPLOYEECPP_H

#include <string>

class EmployeeCpp {
    public:
         std::string id;
         std::string name;
         std::string managerId;

    public:
         EmployeeCpp(std::string id, std::string name, std::string managerId) {
            id = id;
            name = name;
            managerId = managerId;
        }

    public: std::string getId() {
        return id;
    }
    public: std::string getName() {
        return name;
    }
    public: std::string getManagerId() {
        return managerId;
    }
    public: void setManagerId(std::string newId) {
        managerId = newId;
    }
    public: void setName(std::string name) {
        name = name;
    }
    public: void setId(std::string newId) {
        id = newId;
    }
};

#endif //TEST_EMPLOYEECPP_H
