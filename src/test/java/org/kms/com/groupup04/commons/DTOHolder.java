package org.kms.com.groupup04.commons;

import lombok.Getter;
import lombok.Setter;
import org.kms.com.groupup04.data.dto.EmployeeInfo;
import org.kms.com.groupup04.data.dto.Property;

public class DTOHolder {
    private static DTOHolder instance;
    private EmployeeInfo employeeInfo;
    @Setter
    @Getter
    private Property skillProperty;
    @Setter
    @Getter
    private Property languageProperty;

    private DTOHolder() {
        employeeInfo = new EmployeeInfo();
        skillProperty = new Property();
        languageProperty = new Property();
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