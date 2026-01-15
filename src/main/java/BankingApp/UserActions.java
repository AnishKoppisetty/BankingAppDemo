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
	public static void signUp(String name, String id, double balance) throws Exception {

        Workbook workbook;
        Sheet sheet;
        File file = new File(filePath);

        if (file.exists()) {
            FileInputStream fis = new FileInputStream(file);
            workbook = new XSSFWorkbook(fis);
            fis.close();
        } else {
            workbook = new XSSFWorkbook();
        }

        sheet = workbook.getSheet(SHEET_NAME);
        if (sheet == null) {
            sheet = workbook.createSheet(SHEET_NAME);
        }

        if (sheet.getRow(0) == null) {
            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("Name");
            header.createCell(1).setCellValue("ID");
            header.createCell(2).setCellValue("Balance");
        }

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row == null) continue;

            Cell idCell = row.getCell(1);
            if (idCell == null) continue;

            idCell.setCellType(CellType.STRING);
            String existingId = idCell.getStringCellValue();

            if (existingId.equals(id)) {
                workbook.close();
                throw new IllegalArgumentException("ID already exists: " + id);
            }
        }

        int newRowIndex = sheet.getLastRowNum() + 1;
        Row newRow = sheet.createRow(newRowIndex);

        newRow.createCell(0).setCellValue(name);
        newRow.createCell(1).setCellValue(id);
        newRow.createCell(2).setCellValue(balance);

        FileOutputStream fos = new FileOutputStream(filePath);
        workbook.write(fos);
        fos.close();
        workbook.close();
    }

    // ---------------- LOGIN (NEW) ----------------
    /**
     * Login validates that BOTH name and id match a row in Excel.
     * If found, returns the balance.
     * If not found, returns -1.
     */
    public static double login(String name, String id) throws Exception {

        File file = new File(filePath);

        // If the file doesn't exist, nobody can log in
        if (!file.exists()) {
            return -1;
        }

        // Open the Excel file
        FileInputStream fis = new FileInputStream(file);
        Workbook workbook = new XSSFWorkbook(fis);
        fis.close();

        Sheet sheet = workbook.getSheet(SHEET_NAME);
        if (sheet == null) {
            workbook.close();
            return -1;
        }

        // Go through every user row (start at 1 because row 0 is header)
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row == null) continue;

            // Read name, id, balance cells
            Cell nameCell = row.getCell(0);
            Cell idCell = row.getCell(1);
            Cell balCell = row.getCell(2);

            if (nameCell == null || idCell == null || balCell == null) continue;

            // Convert name/id to STRING so comparisons work safely
            nameCell.setCellType(CellType.STRING);
            idCell.setCellType(CellType.STRING);

            String existingName = nameCell.getStringCellValue();
            String existingId = idCell.getStringCellValue();

            // If BOTH match → login success
            if (existingName.equalsIgnoreCase(name) && existingId.equals(id)) {
                double balance = balCell.getNumericCellValue();
                workbook.close();
                return balance;
            }
        }

        // No match found
        workbook.close();
        return -1;
    }

}