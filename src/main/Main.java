/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import bo.DoctorBusinessObject;
import constans.Constant;
import entity.Doctor;
import java.util.List;
import java.util.Scanner;
import utils.Validation;

/**
 *
 * @author ngnqu
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DoctorBusinessObject doctorManager = new DoctorBusinessObject();

        while (true) {
            System.out.println("----- Doctor manage -----");
            System.out.println("1. Add doctor");
            System.out.println("2. Delete doctor");
            System.out.println("3. Update doctor");
            System.out.println("4. Search doctor");
            System.out.println("5. Display all doctor");
            System.out.println("6. Exit");

            int choice = Validation.getInt("Choose (1-6): ", "Out range", "Must be integer", 1, 6);

            switch (choice) {
                case 1:
                    while (true) {
                        System.out.println("======= Add doctor =======");
                        if (doctorManager.addDoctor() == true) {
                            System.out.println("add ok");
                        } else {
                            System.out.println("add not ok");
                        }
                        String choiceYorN = Validation.getString(
                                "Do you want to continue? (Y/N): ",
                                "messageErrorInvalid",
                                Constant.REGEX_YES_OR_NO);
                        if (choiceYorN.equalsIgnoreCase("N")) {
                            break; // Kết thúc vòng lặp nếu người dùng chọn "N" hoặc "n"
                        }
                    }
                    break;
                case 2:
                    System.out.println("======= Delete doctor =======");
                    if (doctorManager.deleteDoctor(Validation.getString("Enter code: ", "Enter again", Constant.REGEX_CODE)) == true) {
                        System.out.println("delete ok");
                    } else {
                        System.out.println("delete not ok");
                    }
                    break;

                case 3:
                    System.out.println("======= Update doctor =======");
                    if (doctorManager.updateDoctor() == true) {
                        System.out.println("update ok");
                    } else {
                        System.out.println("update not ok");
                    }
                    break;

                case 4:
                    System.out.println("======= Search doctor =======");
                    System.out.print("Enter doctor name: ");
                    String searchName = Validation.getString("Enter name: ", "Enter again", Constant.REGEX_NAME);
                    List<Doctor> list = doctorManager.searchDoctorsByName(searchName);
                    if (!list.isEmpty()) {
                        System.out.println("====================================================================================");
                        System.out.printf("%-20s %-30s %-20s %-10s%n", "Mã bác sĩ", "Tên bác sĩ", "Chuyên khoa", "Khả dụng");
                        list.forEach((doctorList) -> {
                            System.out.printf("%-20s %-30s %-20s %-10d%n", doctorList.getCode(), doctorList.getName(), doctorList.getSpecialization(), doctorList.getAvailability());
                        });
                        System.out.println("====================================================================================");
                    } else {
                        System.out.println("not found");
                    }
                    break;

                case 5:
                    doctorManager.displayAllDoctors();
                    break;

                case 6:
                    System.out.println("Thanks you!!!.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Tùy chọn không hợp lệ. Vui lòng chọn lại.");
            }
        }
    }
}
