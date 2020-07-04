package org.docreg.app;

class PatientModel {
    private final String id;
    private final String imageUrl;
    private final String name;
    private final String aadhar;
    private final int age;
    private final String gender;
    private final String address;
    private final String phone;
    private final double height;
    private final double weight;
    private final String bloodGroup;

    public PatientModel(String id, String imageUrl, String name, String aadhar, int age, String gender, String address, String phone, double height, double weight, String bloodGroup) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.name = name;
        this.aadhar = aadhar;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.phone = phone;
        this.height = height;
        this.weight = weight;
        this.bloodGroup = bloodGroup;
    }

    public String getId() {
        return id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getName() {
        return name;
    }

    public String getAadhar() {
        return aadhar;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }
}
