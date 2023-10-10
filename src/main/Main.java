/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import entity.Doctor;
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
        Doctor doctorManager = new Doctor();

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
                    System.out.println("======= Add doctor =======");
                    String code = Validation.getSring("Enter code: ", "Enter again", "^[A-Za-z0-9]*$");
                    String name = Validation.getSring("Enter name: ", "Enter again", "^[A-Za-z\\s]*$");
                    String specialization = Validation.getSring("Enter specialization: ", "Enter again", "^[A-Za-z\\s]*$");
                    int availability = Validation.getInt("Enter availability: ", "ErrorOutOfRange", "ErrorInvalidNumber", Integer.MIN_VALUE, Integer.MAX_VALUE);

                    Doctor newDoctor = new Doctor(code, name, specialization, availability);
                    doctorManager.addDoctors(newDoctor);
                    break;

                case 2:
                    System.out.println("======= Delete doctor =======");
                    doctorManager.deleteDoctors(Validation.getSring("Enter code: ", "Enter again", "^[A-Za-z0-9]*$"));
                    break;

                case 3:
                    System.out.println("======= Update doctor =======");
                    String updateCode = Validation.getSring("Enter doctor code to update: ", "Enter again", "^[A-Za-z0-9]*$");
                    if (doctorManager.checkDoctorExistByCode(updateCode)) {
                        String newName = Validation.getSring("Enter doctor name to update: ", "Enter again", "^[A-Za-z\\s]*$");
                        String newSpecialization = Validation.getSring("Enter doctor Specialization to update: ", "Enter again", "^[A-Za-z\\s]*$");
                        int newAvailability = Validation.getInt("Enter doctor availability to update: ", "ErrorOutOfRange", "ErrorInvalidNumber", Integer.MIN_VALUE, Integer.MAX_VALUE);

                        doctorManager.updateDoctor(updateCode, newName, newSpecialization, newAvailability);
                        System.out.println("Update doctor successfull!");
                    } else {
                        System.out.println("Not found doctor with code: " + updateCode);
                    }
                    break;

                case 4:
                    System.out.println("======= Search doctor =======");
                    System.out.print("Enter doctor name: ");
                    String searchName = scanner.nextLine();
                    doctorManager.searchDoctorsByName(searchName);
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