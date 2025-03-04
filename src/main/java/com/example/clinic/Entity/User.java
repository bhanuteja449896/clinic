package com.example.clinic.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "user")
public class User {
    @Id
    private String id;
    private String name;
    private String mobile;
    private String password;
    private String gmail;
    private List<Transactions> history;
    private List<Report> reports;
    private String age;
    private String gender;

    public static class Report {
        private String date;
        private String description;
        private String medicines;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getMedicines() {
            return medicines;
        }

        public void setMedicines(String medicines) {
            this.medicines = medicines;
        }
    }

    public List<Report> getReports() {
        return reports;
    }

    public void setReports(List<Report> reports) {
        this.reports = reports;
    }

    public List<Transactions> getHistory() {
        return history;
    }

    public void setHistory(List<Transactions> history) {
        this.history = history;
    }

    public void addTransactionToHistory(Transactions transaction) {
        this.history.add(transaction);
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }


    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
