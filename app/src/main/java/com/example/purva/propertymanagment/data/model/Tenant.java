package com.example.purva.propertymanagment.data.model;


public class Tenant {

    public static class TenantBean{
        private String propetyId;
        private String landlordId;
        private String name;
        private String email;
        private String address;
        private String mobile;

        public TenantBean(String propetyId, String landlordId, String name, String email, String address, String mobile) {
            this.propetyId = propetyId;
            this.landlordId = landlordId;
            this.name = name;
            this.email = email;
            this.address = address;
            this.mobile = mobile;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPropetyId() {
            return propetyId;
        }

        public void setPropetyId(String propetyId) {
            this.propetyId = propetyId;
        }

        public String getLandlordId() {
            return landlordId;
        }

        public void setLandlordId(String landlordId) {
            this.landlordId = landlordId;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }
    }

}
