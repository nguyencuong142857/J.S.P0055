/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import constans.Constant;
import java.util.Map;
import utils.Validation;

/**
 *
 * @author ngnqu
 */
public class Doctor {

    private String code;
    private String name;
    private String specialization;
    private int availability;

    public Doctor() {
    }

    public Doctor(String code, String name, String specialization, int availability) {
        this.code = code;
        this.name = name;
        this.specialization = specialization;
        this.availability = availability;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public int getAvailability() {
        return availability;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }

    public void input(Map<String, Doctor> doctorMap) {
        String inputCode;
        do {
            inputCode = Validation.getStringCode("Enter code: ", "Invalid name format. Please enter again.", Constant.REGEX_CODE).toUpperCase();
            if (doctorMap.containsKey(inputCode.toUpperCase())) {
                System.out.println("Code already exists. Please enter a different code.");
            }
        } while (doctorMap.containsKey(inputCode.toUpperCase()));
        this.code = inputCode;
        this.name = Validation.getString("Enter name: ", "Invalid name format. Please enter again.", Constant.REGEX_NAME);
        this.specialization = Validation.getString("Enter specialization: ", "Invalid specialization format. Please enter again.", Constant.REGEX_NAME);
        this.availability = Validation.getInt("Enter availability: ","", "Invalid availability format. Please enter again.", Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public void display() {
        System.out.printf("%-20s %-30s %-20s %-10d%n", code, name, specialization, availability);
    }
}
