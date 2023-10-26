/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import entity.Doctor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        newDoctor.input();
        if (doctorMap.containsKey(newDoctor.getCode())) {
            return false; // Mã đã tồn tại
        }
        doctorMap.put(newDoctor.getCode(), newDoctor);
        return true; // Thêm thành công
    }

    public boolean existsDoctorWithCode(String code) {
        return doctorMap.containsKey(code);
    }

    public boolean deleteDoctor(String code) {
        return doctorMap.remove(code) != null;
    }

    public boolean updateDoctor() {
        Doctor doctor = new Doctor();
        doctor.input();
        if (doctorMap.containsKey(doctor.getCode())) {
            doctorMap.get(doctor.getCode()).setName(doctor.getName());
            doctorMap.get(doctor.getCode()).setSpecialization(doctor.getSpecialization());
            doctorMap.get(doctor.getCode()).setAvailability(doctor.getAvailability());
            return true;
        } else {
            return false;
        }
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
