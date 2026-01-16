package BankingApp;

import java.io.*;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class UserActions extends ObjectsPageFactory {

    /*public static void runLogin() {
        System.out.print("Enter Name: ");
        String name = s.nextLine();
        System.out.print("Enter ID: ");
        int id = Integer.parseInt(s.nextLine());

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean found = false;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].trim().equalsIgnoreCase(name) && Integer.parseInt(parts[1].trim()) == id) {
                    System.out.println("Login Successful! Balance: " + parts[2].trim());
                    found = true;
                    break;
                }
            }
            if (!found) System.out.println("Invalid Credentials.");
        } catch (IOException e) {
            System.out.println("File error: " + e.getMessage());
        }
    }

    public static void runSignUp() {
        System.out.print("Enter Name: ");
        String name = s.nextLine();
        System.out.print("Enter ID: ");
        int id = s.nextInt();
        System.out.print("Initial Deposit: ");
        double deposit = s.nextDouble();

        SavingAccount newCustomer = new SavingAccount(name, id, deposit);

        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.write(newCustomer.customerName + ", " + newCustomer.accountNumber + ", " + newCustomer.balance + "\n");
            System.out.println("Account created for " + name);
        } catch (IOException e) {
            System.out.println("Error saving user.");
        }
    } */
	  
  
    public static void signUp(
            String username,
            String firstName,
            String lastName,
            String dob,
            String email,
            String address,
            String phone,
            double balance
    ) throws Exception {

        Workbook workbook;
        Sheet sheet;
        File file = new File(FILE_NAME);

        if (file.exists()) {
            FileInputStream fis = new FileInputStream(file);
            workbook = new XSSFWorkbook(fis);
            fis.close();
        } else {
            workbook = new XSSFWorkbook();
        }

        sheet = workbook.getSheet(SHEET_NAME);
        if (sheet == null) sheet = workbook.createSheet(SHEET_NAME);

        if (sheet.getRow(0) == null) {
            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("Username");
            header.createCell(1).setCellValue("Password (common for now)");
            header.createCell(2).setCellValue("First Name");
            header.createCell(3).setCellValue("Last Name");
            header.createCell(4).setCellValue("DOB");
            header.createCell(5).setCellValue("ID");
            header.createCell(6).setCellValue("Email");
            header.createCell(7).setCellValue("Address");
            header.createCell(8).setCellValue("Phone");
            header.createCell(9).setCellValue("Balance");
        }

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row == null) continue;

            Cell uCell = row.getCell(0);
            if (uCell == null) continue;

            uCell.setCellType(CellType.STRING);
            String existingUsername = uCell.getStringCellValue();

            if (existingUsername.equalsIgnoreCase(username)) {
                workbook.close();
                throw new IllegalArgumentException("Username already exists.");
            }
        }

        String id = generateId(firstName, lastName, dob);

        int newRowIndex = sheet.getLastRowNum() + 1;
        Row newRow = sheet.createRow(newRowIndex);

        newRow.createCell(0).setCellValue(username);
        newRow.createCell(1).setCellValue(COMMON_PASSWORD);
        newRow.createCell(2).setCellValue(firstName);
        newRow.createCell(3).setCellValue(lastName);
        newRow.createCell(4).setCellValue(dob); 
        newRow.createCell(5).setCellValue(id);
        newRow.createCell(6).setCellValue(email);
        newRow.createCell(7).setCellValue(address);
        newRow.createCell(8).setCellValue(phone);
        newRow.createCell(9).setCellValue(balance);

        FileOutputStream fos = new FileOutputStream(FILE_NAME);
        workbook.write(fos);
        fos.close();
        workbook.close();
    }

    public static double login(String username, String password) throws Exception {

        if (!COMMON_PASSWORD.equals(password)) {
            return -1;
        }

        File file = new File(FILE_NAME);
        if (!file.exists()) return -1;

        FileInputStream fis = new FileInputStream(file);
        Workbook workbook = new XSSFWorkbook(fis);
        fis.close();

        Sheet sheet = workbook.getSheet(SHEET_NAME);
        if (sheet == null) {
            workbook.close();
            return -1;
        }

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row == null) continue;

            Cell uCell = row.getCell(0);
            Cell balCell = row.getCell(9);

            if (uCell == null || balCell == null) continue;

            uCell.setCellType(CellType.STRING);
            String existingUsername = uCell.getStringCellValue();

            if (existingUsername.equalsIgnoreCase(username)) {
                double balance = balCell.getNumericCellValue();
                workbook.close();
                return balance;
            }
        }

        workbook.close();
        return -1;
    }

    protected static String generateId(String firstName, String lastName, String dob) {
        char f = Character.toUpperCase(firstName.trim().charAt(0));
        char l = Character.toUpperCase(lastName.trim().charAt(0));

        String dobDigits = dob.replaceAll("[^0-9]", "");

        return "" + f + l + dobDigits;
    }
}