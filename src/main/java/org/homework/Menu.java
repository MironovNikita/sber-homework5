package org.homework;

import java.util.Scanner;

import static org.homework.TaskRunner.*;

public class Menu {
    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean isWorking = true;

        while (isWorking) {
            printMenu();
            int userCommand = checkIntInput(scanner);

            switch (userCommand) {
                case 1 -> {
                    task1();
                    isWorking = wishToContinue();
                }
                case 2 -> {
                    task2();
                    isWorking = wishToContinue();
                }
                case 3 -> {
                    task3();
                    isWorking = wishToContinue();
                }
                case 4 -> {
                    task4();
                    isWorking = wishToContinue();
                }
                case 5 -> {
                    task5();
                    isWorking = wishToContinue();
                }
                case 6 -> {
                    task6();
                    isWorking = wishToContinue();
                }
                case 7 -> {
                    task7();
                    isWorking = wishToContinue();
                }
                case 0 -> {
                    System.out.println("До свидания! Завершение программы...");
                    isWorking = false;
                }
                default -> System.out.println("Извините, такая команда отсутствует :с");
            }
        }
    }

    private void printMenu() {
        System.out.println(
                """
                        Задание №5
                        Пожалуйста, выберите одну из цифр (0-7) для выбора проверки задания:
                        1 - Простая проверка расчёта факториала числа
                        2 - Проверка работы вывода всех методов класса, включая родительские методы (+ приватные)
                        3 - Проверка работы вывода всех геттеров класса
                        4 - Проверка работы метода, сравнивающего имена констант с их значением
                        5 - Проверка кеширующего прокси
                        6 - Проверка прокси, рассчитывающего время выполнения метода
                        7 - Проверка реализации класса BeanUtils
                        0 - Выход
                                                
                        Ввод:"""
        );
    }

    private int checkIntInput(Scanner scanner) {
        int num;
        do {
            if (scanner.hasNextInt()) {
                num = scanner.nextInt();
                break;
            } else {
                System.out.print("Не могу распознать число. Введите числовое значение: ");
                scanner.nextLine();
            }
        } while (true);
        return num;
    }

    private boolean wishToContinue() {
        System.out.println("\nЖелаете продолжить?\n1 - Вывести меню\n0 - Выйти");
        int answer;
        while (true) {
            System.out.println("Ввод: ");
            answer = checkIntInput(new Scanner(System.in));
            switch (answer) {
                case 1 -> {
                    return true;
                }
                case 0 -> {
                    return false;
                }

                default -> System.out.println("Извините, такая команда отсутствует :с");
            }
        }
    }
}
