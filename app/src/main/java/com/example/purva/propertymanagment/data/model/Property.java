package com.example.purva.propertymanagment.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Property {
    private List<PropertyBean> Property;

    public List<PropertyBean> getProperty() {
        return Property;
    }

    public void setProperty(List<PropertyBean> Property) {
        this.Property = Property;
    }

    public static class PropertyBean implements Parcelable {
        /**
         * id : 1
         * propertyaddress : fla1234
         * propertycity : Noida
         * propertystate : UP
         * propertycountry : India
         * propertystatus : tenants
         * propertypurchaseprice : 12000
         * propertymortageinfo : no
         */
        public static final Creator<PropertyBean> CREATOR = new Creator<PropertyBean>() {
            @Override
            public PropertyBean createFromParcel(Parcel in) {
                return new PropertyBean(in);
            }

            @Override
            public PropertyBean[] newArray(int size) {
                return new PropertyBean[size];
            }
        };

        private String id; // propertyId

        private String propertycountry;

        public String getLandlordId() {
            return landlordId;
        }

        public void setLandlordId(String landlordId) {
            this.landlordId = landlordId;
        }

        private String landlordId;
        private String propertystate;
        private String propertycity;
        private String propertyaddress;
        private String propertystatus;
        private String propertypurchaseprice;
        private String propertymortageinfo;

        public PropertyBean(String id, String landlordId, String propertyaddress, String propertycity, String propertystate, String propertycountry, String propertystatus, String propertypurchaseprice, String propertymortageinfo) {
            this.id = id;
            this.landlordId = landlordId;
            this.propertyaddress = propertyaddress;
            this.propertycity = propertycity;
            this.propertystate = propertystate;
            this.propertycountry = propertycountry;
            this.propertystatus = propertystatus;
            this.propertypurchaseprice = propertypurchaseprice;
            this.propertymortageinfo = propertymortageinfo;
        }

        public PropertyBean(String id, String propertycountry, String propertystate, String propertycity, String propertyaddress, String propertystatus, String propertypurchaseprice, String propertymortageinfo) {
            this.id = id;
            this.propertycountry = propertycountry;
            this.propertystate = propertystate;
            this.propertycity = propertycity;
            this.propertyaddress = propertyaddress;
            this.propertystatus = propertystatus;
            this.propertypurchaseprice = propertypurchaseprice;
            this.propertymortageinfo = propertymortageinfo;
        }

        public PropertyBean(String propertyaddress, String propertycity, String propertystate, String propertycountry, String propertypurchaseprice) {
            this.propertyaddress = propertyaddress;
            this.propertycity = propertycity;
            this.propertystate = propertystate;
            this.propertycountry = propertycountry;
            this.propertypurchaseprice = propertypurchaseprice;
        }

        protected PropertyBean(Parcel in) {
            id = in.readString();
            propertycountry = in.readString();
            landlordId = in.readString();
            propertystate = in.readString();
            propertycity = in.readString();
            propertyaddress = in.readString();
            propertystatus = in.readString();
            propertypurchaseprice = in.readString();
            propertymortageinfo = in.readString();
        }



        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPropertyaddress() {
            return propertyaddress;
        }

        public void setPropertyaddress(String propertyaddress) {
            this.propertyaddress = propertyaddress;
        }

        public String getPropertycity() {
            return propertycity;
        }

        public void setPropertycity(String propertycity) {
            this.propertycity = propertycity;
        }

        public String getPropertystate() {
            return propertystate;
        }

        public void setPropertystate(String propertystate) {
            this.propertystate = propertystate;
        }

        public String getPropertycountry() {
            return propertycountry;
        }

        public void setPropertycountry(String propertycountry) {
            this.propertycountry = propertycountry;
        }

        public String getPropertystatus() {
            return propertystatus;
        }

        public void setPropertystatus(String propertystatus) {
            this.propertystatus = propertystatus;
        }

        public String getPropertypurchaseprice() {
            return propertypurchaseprice;
        }

        public void setPropertypurchaseprice(String propertypurchaseprice) {
            this.propertypurchaseprice = propertypurchaseprice;
        }

        public String getPropertymortageinfo() {
            return propertymortageinfo;
        }

        public void setPropertymortageinfo(String propertymortageinfo) {
            this.propertymortageinfo = propertymortageinfo;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(id);
            dest.writeString(propertycountry);
            dest.writeString(landlordId);
            dest.writeString(propertystate);
            dest.writeString(propertycity);
            dest.writeString(propertyaddress);
            dest.writeString(propertystatus);
            dest.writeString(propertypurchaseprice);
            dest.writeString(propertymortageinfo);
        }
    }

}
