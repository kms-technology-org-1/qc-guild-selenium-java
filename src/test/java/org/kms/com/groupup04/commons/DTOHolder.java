package org.kms.com.groupup04.commons;

import org.kms.com.groupup04.data.dto.EmployeeInfo;

public class DTOHolder {
    private static DTOHolder instance;
    private EmployeeInfo employeeInfo;


    private DTOHolder() {
        employeeInfo = new EmployeeInfo();
    }

    public static DTOHolder getInstance() {
        if (instance == null) {
            instance = new DTOHolder();
        }
        return instance;
    }

    public EmployeeInfo getEmployeeInfoDTO() {
        return employeeInfo;
    }


    public void setEmployeeInfoDTO(EmployeeInfo dto) {
        this.employeeInfo = dto;
    }
}
