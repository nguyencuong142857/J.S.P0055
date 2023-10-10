/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ngnqu
 */
public class Doctor {

    private List<Doctor> doctorList = new ArrayList<>();

    public List<Doctor> getListDoctor() {
        return doctorList;
    }

    public void setListDoctor(List<Doctor> listDoctor) {
        this.doctorList = listDoctor;
    }

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

    public boolean checkDoctorExistByCode(String code) {
        // Trả về true nếu tìm thấy bác sĩ với mã code
        // Trả về false nếu không tìm thấy bác sĩ với mã code
        
        return doctorList.stream().anyMatch((doctor) -> (doctor.getCode().equals(code))); 
    }

    public void addDoctors(Doctor doctor) {
        String doctorCode = doctor.getCode();
        if (!checkDoctorExistByCode(doctorCode)) {
            doctorList.add(doctor);
            System.out.println("Add doctor successfull!");
        } else {
            System.out.println("Doctor code " + doctorCode + " exist.");
        }
    }

    public void deleteDoctors(String code) {
        Doctor doctorToRemove = null;
        for (Doctor doctor : doctorList) {
            if (doctor.getCode().equals(code)) {
                doctorToRemove = doctor;
                break;
            }
        }
        if (doctorToRemove != null) {
            doctorList.remove(doctorToRemove);
            System.out.println("Delete successful!");
        } else {
            System.out.println("Not found doctor with code: " + code);
        }
    }

    public void updateDoctor(String code, String newName, String newSpecialization, int newAvailability) {
        if (checkDoctorExistByCode(code)) {
            for (Doctor doctor : doctorList) {
                if (doctor.getCode().equals(code)) {
                    doctor.setName(newName);
                    doctor.setSpecialization(newSpecialization);
                    doctor.setAvailability(newAvailability);
                    break;
                }
            }
        } else {
            System.out.println("Not found doctor with code: " + code);
        }
    }

    public List<Doctor> searchDoctorsByName(String name) {
        List<Doctor> foundDoctors = new ArrayList<>();
        doctorList.stream().filter((doctor) -> (doctor.getName().toLowerCase().contains(name.toLowerCase()))).forEachOrdered((doctor) -> {
            foundDoctors.add(doctor);
        });
        if (!foundDoctors.isEmpty()) {
            System.out.println("Các bác sĩ được tìm thấy có tên chứa '" + name + "':");
            System.out.println("====================================================================================");
            System.out.printf("%-20s %-30s %-20s %-10s%n", "Mã bác sĩ", "Tên bác sĩ", "Chuyên khoa", "Khả dụng");
            foundDoctors.forEach((doctor) -> {
                System.out.printf("%-20s %-30s %-20s %-10d%n", doctor.getCode(), doctor.getName(), doctor.getSpecialization(), doctor.getAvailability());
            });
            System.out.println("====================================================================================");
        } else {
            System.out.println("Không tìm thấy bác sĩ nào có tên chứa '" + name + "'.");
        }
        return foundDoctors;
    }

    public void displayAllDoctors() {
        System.out.println("====================================================================================");
        System.out.printf("%-20s %-30s %-20s %-10s%n", "Mã bác sĩ", "Tên bác sĩ", "Chuyên khoa", "Khả dụng");
        doctorList.forEach((doctor) -> {
            System.out.printf("%-20s %-30s %-20s %-10d%n", doctor.getCode(), doctor.getName(), doctor.getSpecialization(), doctor.getAvailability());
        });
        System.out.println("====================================================================================");
    }

}
