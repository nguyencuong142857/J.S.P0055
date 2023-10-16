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

    private Map<String, Doctor> doctorMap = new HashMap<>();

    public void setDoctorMap(Map<String, Doctor> doctorMap) {
        this.doctorMap = doctorMap;
    }

    public boolean addDoctor(Doctor doctor) {
        return doctorMap.putIfAbsent(doctor.getCode(), doctor) == null;
    }

    public boolean deleteDoctor(String code) {
        return doctorMap.remove(code) != null;
    }

    public boolean updateDoctor(Doctor doctor) {
        if (doctorMap.containsKey(doctor.getCode())) {
            Doctor doctorExist = doctorMap.get(doctor.getCode());
            doctorExist.setName(doctor.getName());
            doctorExist.setSpecialization(doctor.getSpecialization());
            doctorExist.setAvailability(doctor.getAvailability());
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
        for (Doctor doctor : doctorMap.values()) {
            System.out.printf("%-20s %-30s %-20s %-10d%n", doctor.getCode(), doctor.getName(), doctor.getSpecialization(), doctor.getAvailability());
        }
        System.out.println("====================================================================================");
    }   

    public static void main(String[] args) {
        DoctorBusinessObject dbo = new DoctorBusinessObject();
        Doctor doctor = new Doctor("W1", "Cuong", "asd", 23);
        dbo.addDoctor(doctor);
        Doctor doctor1 = new Doctor("W2", "Huy", "asd", 23);
        System.out.println(dbo.updateDoctor(doctor1));
    }
}
