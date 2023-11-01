/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import constans.Constant;
import entity.Doctor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import utils.Validation;

/**
 *
 * @author ngnqu
 */
public class DoctorBusinessObject {

    private Map<String, Doctor> doctorMap;

    public DoctorBusinessObject() {
        this.doctorMap = new HashMap<>();
    }

    public DoctorBusinessObject(Map<String, Doctor> doctorMap) {
        this.doctorMap = doctorMap;
    }

    public void setDoctorMap(Map<String, Doctor> doctorMap) {
        this.doctorMap = doctorMap;
    }

    public boolean addDoctor() {
        Doctor newDoctor = new Doctor();
        newDoctor.input(doctorMap);
        return doctorMap.put(newDoctor.getCode(), newDoctor) == null; // Thêm thành công
    }

    public boolean deleteDoctor(String code) {
        return doctorMap.remove(code) != null;
    }

    public boolean updateDoctor() {
        String inputCode;
        do {
            inputCode = Validation.getString("Enter code: ", "Invalid name format. Please enter again.", Constant.REGEX_CODE).toUpperCase();
            if (!doctorMap.containsKey(inputCode.toUpperCase())) {
                System.out.println("Code isn't exists. Please enter a different code.");
            }
        } while (!doctorMap.containsKey(inputCode.toUpperCase()));
        Doctor doctor = doctorMap.get(inputCode);
        doctor.setName(Validation.getString("Enter name: ", "Invalid name format. Please enter again.", Constant.REGEX_NAME));
        doctor.setSpecialization(Validation.getString("Enter specialization: ", "Invalid specialization format. Please enter again.", Constant.REGEX_NAME));
        doctor.setAvailability(Validation.getInt("Enter availability: ", "Invalid availability format", " Please enter again.", Integer.MIN_VALUE, Integer.MAX_VALUE));
        return true;
    }

    public List<Doctor> searchDoctorsByName(String name) {
        return doctorMap.values()
                .stream()
                .filter(doctor -> doctor.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    public void displayAllDoctors() {
        System.out.println("====================================================================================");
        System.out.printf("%-20s %-30s %-20s %-10s%n", "Mã bác sĩ", "Tên bác sĩ", "Chuyên khoa", "Khả dụng");
        doctorMap.values().forEach((doctor) -> {
            doctor.display();
        });
        System.out.println("====================================================================================");
    }

}
