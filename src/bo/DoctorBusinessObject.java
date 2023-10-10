


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import entity.Doctor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ngnqu
 */
public class DoctorBusinessObject {

    private Map<String, Doctor> doctorMap = new HashMap<>();

    public void setDoctorMap(Map<String, Doctor> doctorMap){
        this.doctorMap = doctorMap;
    }
    
    public void addDoctor(Doctor doctor) {
        String doctorCode = doctor.getCode();
        if (!checkDoctorExistByCode(doctorCode)) {
            doctorMap.put(doctorCode, doctor);
            System.out.println("Add doctor successful!");
        } else {
            System.out.println("Doctor code " + doctorCode + " already exists.");
        }
    }

    public void deleteDoctor(String code) {
        if (doctorMap.containsKey(code)) {
            doctorMap.remove(code);
            System.out.println("Delete successful!");
        } else {
            System.out.println("Doctor with code " + code + " not found.");
        }
    }

    public void updateDoctor(String code, String newName, String newSpecialization, int newAvailability) {
        if (checkDoctorExistByCode(code)) {
            Doctor doctor = doctorMap.get(code);
            doctor.setName(newName);
            doctor.setSpecialization(newSpecialization);
            doctor.setAvailability(newAvailability);
            doctorMap.put(code, doctor);
        } else {
            System.out.println("Doctor with code " + code + " not found.");
        }
    }
    
    

    public List<Doctor> searchDoctorsByName(String name) {
        List<Doctor> foundDoctors = new ArrayList<>();
        for (Doctor doctor : doctorMap.values()) {
            if (doctor.getName().toLowerCase().contains(name.toLowerCase())) {
                foundDoctors.add(doctor);
            }
        }

        if (!foundDoctors.isEmpty()) {
            System.out.println("Các bác sĩ được tìm thấy có tên chứa '" + name + "':");
            System.out.println("====================================================================================");
            System.out.printf("%-20s %-30s %-20s %-10s%n", "Mã bác sĩ", "Tên bác sĩ", "Chuyên khoa", "Khả dụng");
            for (Doctor doctor : foundDoctors) {
                System.out.printf("%-20s %-30s %-20s %-10d%n", doctor.getCode(), doctor.getName(), doctor.getSpecialization(), doctor.getAvailability());
            }
            System.out.println("====================================================================================");
        } else {
            System.out.println("Không tìm thấy bác sĩ nào có tên chứa '" + name + "'.");
        }
        return foundDoctors;
    }

    public void displayAllDoctors() {
        System.out.println("====================================================================================");
        System.out.printf("%-20s %-30s %-20s %-10s%n", "Mã bác sĩ", "Tên bác sĩ", "Chuyên khoa", "Khả dụng");
        for (Doctor doctor : doctorMap.values()) {
            System.out.printf("%-20s %-30s %-20s %-10d%n", doctor.getCode(), doctor.getName(), doctor.getSpecialization(), doctor.getAvailability());
        }
        System.out.println("====================================================================================");
    }

    public boolean checkDoctorExistByCode(String code) {
        return doctorMap.containsKey(code);
    }
}
