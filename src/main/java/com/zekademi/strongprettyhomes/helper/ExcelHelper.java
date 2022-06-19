package com.zekademi.strongprettyhomes.helper;

import com.zekademi.strongprettyhomes.domain.Property;
import com.zekademi.strongprettyhomes.domain.User;
import com.zekademi.strongprettyhomes.domain.Review;
import com.zekademi.strongprettyhomes.domain.Agent;
import com.zekademi.strongprettyhomes.domain.TourRequest;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelHelper {

    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERS_USER = { "Id", "FirstName", "LastName", "PhoneNumber", "Email", "Address", "ZipCode", "Roles" };
    static String SHEET_USER = "Customers";

    static String[] HEADERS_PROPERTY = { "Id", "Title", "Description", "Category", "Type",
            "Bedrooms", "Bathrooms", "Garages", "Area", "location", "Country", "City", "district", "Status" };
    static String SHEET_PROPERTY = "Properties";

    static String[] HEADERS_AGENT = { "Id", "firstName", "lastName", "PhoneNumber", "Email","PropertyId", "PropertyTitle" };
    static String SHEET_AGENT = "Agents";

    static String[] HEADERS_REVIEW = { "Id", "Review", "ActivationDate", "Score", "Status","PropertyId", "Property_Title", "CustomerId", "CustomerFullName", };
    static String SHEET_REVIEW = "Reviews";

    static String[] HEADERS_TOURREQUEST = { "Id", "TourRequestTime", "Adult", "Child", "Status","PropertyId", "PropertyTitle", "CustomerId", "CustomerFullName" };
    static String SHEET_TOURREQUEST = "TourRequests";


    public static ByteArrayInputStream usersExcel(List<User> users)  {
        try (Workbook workbook = new XSSFWorkbook();
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet(SHEET_USER);
            Row headerRow = sheet.createRow(0);

            for (int column = 0; column < HEADERS_USER.length; column++) {
                Cell cell = headerRow.createCell(column);
                cell.setCellValue(HEADERS_USER[column]);
            }

            int rowId = 1;
            for (User user: users) {
                Row row = sheet.createRow(rowId++);

                row.createCell(0).setCellValue(user.getId());
                row.createCell(1).setCellValue(user.getFirstName());
                row.createCell(2).setCellValue(user.getLastName());
                row.createCell(3).setCellValue(user.getPhoneNumber());
                row.createCell(4).setCellValue(user.getEmail());
                row.createCell(5).setCellValue(user.getAddress());
                row.createCell(6).setCellValue(user.getZipCode());
                row.createCell(7).setCellValue(user.getRoles().toString());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());

        } catch (IOException e) {
            throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
        }

    }

    public static ByteArrayInputStream propertiesExcel(List<Property> properties)  {
        try (Workbook workbook = new XSSFWorkbook();
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet(SHEET_PROPERTY);
            Row headerRow = sheet.createRow(0);

            for (int column = 0; column < HEADERS_PROPERTY.length; column++) {
                Cell cell = headerRow.createCell(column);
                cell.setCellValue(HEADERS_PROPERTY[column]);
            }

            int rowId = 1;
            for (Property property: properties) {
                Row row = sheet.createRow(rowId++);

                row.createCell(0).setCellValue(property.getId());
                row.createCell(1).setCellValue(property.getTitle());
                row.createCell(2).setCellValue(property.getDescription());
                row.createCell(3).setCellValue(property.getCategory().toString());
                row.createCell(4).setCellValue(property.getType().toString());
                row.createCell(5).setCellValue(property.getBedrooms());
                row.createCell(6).setCellValue(property.getBathrooms());
                row.createCell(7).setCellValue(property.getGarages());
                row.createCell(8).setCellValue(property.getArea());
                row.createCell(9).setCellValue(property.getLocation());
                row.createCell(10).setCellValue(property.getStatus().toString());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());

        } catch (IOException e) {
            throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
        }

    }

    public static ByteArrayInputStream agentsExcel(List<Agent> agents)  {
        try (Workbook workbook = new XSSFWorkbook();
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet(SHEET_AGENT);
            Row headerRow = sheet.createRow(0);

            for (int column = 0; column < HEADERS_AGENT.length; column++) {
                Cell cell = headerRow.createCell(column);
                cell.setCellValue(HEADERS_AGENT[column]);
            }

            int rowId = 1;
            for (Agent agent: agents) {
                Row row = sheet.createRow(rowId++);

                row.createCell(0).setCellValue(agent.getId());
                row.createCell(1).setCellValue(agent.getFirstName());
                row.createCell(2).setCellValue(agent.getLastName());
                row.createCell(3).setCellValue(agent.getPhoneNumber());
                row.createCell(5).setCellValue(agent.getEmail());
                //row.createCell(6).setCellValue(agent.getProperty().getId());
                //row.createCell(7).setCellValue(agent.getProperty().getTitle());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());

        } catch (IOException e) {
            throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
        }

    }

    public static ByteArrayInputStream reviewsExcel(List<Review> reviews)  {
        try (Workbook workbook = new XSSFWorkbook();
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet(SHEET_REVIEW);
            Row headerRow = sheet.createRow(0);

            for (int column = 0; column < HEADERS_REVIEW.length; column++) {
                Cell cell = headerRow.createCell(column);
                cell.setCellValue(HEADERS_REVIEW[column]);
            }

            int rowId = 1;
            for (Review review: reviews) {
                Row row = sheet.createRow(rowId++);

                row.createCell(0).setCellValue(review.getId());
                row.createCell(1).setCellValue(review.getReview());
                row.createCell(2).setCellValue(review.getActivationDate().toString());
                row.createCell(3).setCellValue(review.getScore());
                row.createCell(5).setCellValue(review.getStatus().toString());
                row.createCell(6).setCellValue(review.getProperty().getId());
                row.createCell(7).setCellValue(review.getProperty().getTitle());
                row.createCell(8).setCellValue(review.getUser().getId());
                row.createCell(9).setCellValue(review.getUser().getFullName());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());

        } catch (IOException e) {
            throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
        }
    }

    public static ByteArrayInputStream tourrequestsExcel(List<TourRequest> tourrequests)  {
        try (Workbook workbook = new XSSFWorkbook();
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet(SHEET_TOURREQUEST);
            Row headerRow = sheet.createRow(0);

            for (int column = 0; column < HEADERS_TOURREQUEST.length; column++) {
                Cell cell = headerRow.createCell(column);
                cell.setCellValue(HEADERS_TOURREQUEST[column]);
            }

            int rowId = 1;
            for (TourRequest tourrequest: tourrequests) {
                Row row = sheet.createRow(rowId++);

                row.createCell(0).setCellValue(tourrequest.getId());
                row.createCell(1).setCellValue(tourrequest.getTourRequestTime().toString());
                row.createCell(2).setCellValue(tourrequest.getAdult());
                row.createCell(3).setCellValue(tourrequest.getChild());
                //row.createCell(5).setCellValue(tourrequest.getStatus().toString());
                row.createCell(6).setCellValue(tourrequest.getProperty().getId());
                row.createCell(7).setCellValue(tourrequest.getProperty().getTitle());
                row.createCell(8).setCellValue(tourrequest.getUser().getId());
                row.createCell(9).setCellValue(tourrequest.getUser().getFullName());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());

        } catch (IOException e) {
            throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
        }
    }

}
