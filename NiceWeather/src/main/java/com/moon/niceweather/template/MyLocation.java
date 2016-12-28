package com.moon.niceweather.template;

import java.util.ArrayList;

/**
 * Created by adminr on 2016/11/18.
 */
public class MyLocation {
    private Result result;
    private int status;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static class Result {
        public AddressComponent addressComponent;
        public String business;
        public int cityCode;
        public String formatted_address;
        public ALocation location;
        public ArrayList<String> poiRegions;
        public ArrayList<String> pois;
        public String businesematic_descriptionss;

        public AddressComponent getAddressComponent() {
            return addressComponent;
        }

        public void setAddressComponent(AddressComponent addressComponent) {
            this.addressComponent = addressComponent;
        }

        public String getBusiness() {
            return business;
        }

        public void setBusiness(String business) {
            this.business = business;
        }

        public int getCityCode() {
            return cityCode;
        }

        public void setCityCode(int cityCode) {
            this.cityCode = cityCode;
        }

        public String getFormatted_address() {
            return formatted_address;
        }

        public void setFormatted_address(String formatted_address) {
            this.formatted_address = formatted_address;
        }

        public ALocation getLocation() {
            return location;
        }

        public void setLocation(ALocation location) {
            this.location = location;
        }

        public ArrayList<String> getPoiRegions() {
            return poiRegions;
        }

        public void setPoiRegions(ArrayList<String> poiRegions) {
            this.poiRegions = poiRegions;
        }

        public ArrayList<String> getPois() {
            return pois;
        }

        public void setPois(ArrayList<String> pois) {
            this.pois = pois;
        }

        public String getBusinesematic_descriptionss() {
            return businesematic_descriptionss;
        }

        public void setBusinesematic_descriptionss(String businesematic_descriptionss) {
            this.businesematic_descriptionss = businesematic_descriptionss;
        }

        public static class AddressComponent {
            private String adcode;
            private String city;
            private String country;
            private int country_code;
            private String direction;
            private String distance;
            private String district;
            private String province;
            private String street;
            private String street_number;

            public String getAdcode() {
                return adcode;
            }

            public void setAdcode(String adcode) {
                this.adcode = adcode;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getCountry() {
                return country;
            }

            public void setCountry(String country) {
                this.country = country;
            }

            public int getCountry_code() {
                return country_code;
            }

            public void setCountry_code(int country_code) {
                this.country_code = country_code;
            }

            public String getDirection() {
                return direction;
            }

            public void setDirection(String direction) {
                this.direction = direction;
            }

            public String getDistance() {
                return distance;
            }

            public void setDistance(String distance) {
                this.distance = distance;
            }

            public String getDistrict() {
                return district;
            }

            public void setDistrict(String district) {
                this.district = district;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getStreet() {
                return street;
            }

            public void setStreet(String street) {
                this.street = street;
            }

            public String getStreet_number() {
                return street_number;
            }

            public void setStreet_number(String street_number) {
                this.street_number = street_number;
            }
        }

        public static class ALocation {
            private double lng;
            private double lat;

            public double getLng() {
                return lng;
            }

            public void setLng(double lng) {
                this.lng = lng;
            }

            public double getLat() {
                return lat;
            }

            public void setLat(double lat) {
                this.lat = lat;
            }
        }
    }


}
