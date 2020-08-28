
package dto;

import entities.Employee;

public class EmployeeDTO {
   private int id;
   private String name;
   private String address;

    public EmployeeDTO(Employee e){
        id = e.getId();
        name = e.getName();
        address = e.getAddress();
    }
   
   
}
