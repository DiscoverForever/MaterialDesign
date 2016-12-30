package com.moon.niceweather.template;

import java.util.List;

/**
 * Created by adminr on 2016/11/18.
 */
public class MyLocation {


    /**
     * result : {"poiRegions":[{"tag":"房地产","name":"苇子坑2号院","direction_desc":"内"}],"addressComponent":{"distance":"1","direction":"1","street":"成仪路","province":"北京市","adcode":"110106","street_number":"","district":"丰台区","country_code":0,"city":"北京市","country":"中国"},"location":{"lng":116.45034299999993,"lat":39.85076108193579},"cityCode":131,"formatted_address":"北京市丰台区成仪路","pois":["1","2"],"sematic_description":"苇子坑2号院内,兴东南大厦西南93米","business":"成寿寺,小红门,东铁匠营"}
     * status : 0
     */
    private ResultEntity result;
    private int status;

    public void setResult(ResultEntity result) {
        this.result = result;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ResultEntity getResult() {
        return result;
    }

    public int getStatus() {
        return status;
    }

    public class ResultEntity {
        /**
         * poiRegions : [{"tag":"房地产","name":"苇子坑2号院","direction_desc":"内"}]
         * addressComponent : {"distance":"1","direction":"1","street":"成仪路","province":"北京市","adcode":"110106","street_number":"","district":"丰台区","country_code":0,"city":"北京市","country":"中国"}
         * location : {"lng":116.45034299999993,"lat":39.85076108193579}
         * cityCode : 131
         * formatted_address : 北京市丰台区成仪路
         * pois : ["1","2"]
         * sematic_description : 苇子坑2号院内,兴东南大厦西南93米
         * business : 成寿寺,小红门,东铁匠营
         */
        private List<PoiRegionsEntity> poiRegions;
        private AddressComponentEntity addressComponent;
        private LocationEntity location;
        private int cityCode;
        private String formatted_address;
        private List<String> pois;
        private String sematic_description;
        private String business;

        public void setPoiRegions(List<PoiRegionsEntity> poiRegions) {
            this.poiRegions = poiRegions;
        }

        public void setAddressComponent(AddressComponentEntity addressComponent) {
            this.addressComponent = addressComponent;
        }

        public void setLocation(LocationEntity location) {
            this.location = location;
        }

        public void setCityCode(int cityCode) {
            this.cityCode = cityCode;
        }

        public void setFormatted_address(String formatted_address) {
            this.formatted_address = formatted_address;
        }

        public void setPois(List<String> pois) {
            this.pois = pois;
        }

        public void setSematic_description(String sematic_description) {
            this.sematic_description = sematic_description;
        }

        public void setBusiness(String business) {
            this.business = business;
        }

        public List<PoiRegionsEntity> getPoiRegions() {
            return poiRegions;
        }

        public AddressComponentEntity getAddressComponent() {
            return addressComponent;
        }

        public LocationEntity getLocation() {
            return location;
        }

        public int getCityCode() {
            return cityCode;
        }

        public String getFormatted_address() {
            return formatted_address;
        }

        public List<String> getPois() {
            return pois;
        }

        public String getSematic_description() {
            return sematic_description;
        }

        public String getBusiness() {
            return business;
        }

        public class PoiRegionsEntity {
            /**
             * tag : 房地产
             * name : 苇子坑2号院
             * direction_desc : 内
             */
            private String tag;
            private String name;
            private String direction_desc;

            public void setTag(String tag) {
                this.tag = tag;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setDirection_desc(String direction_desc) {
                this.direction_desc = direction_desc;
            }

            public String getTag() {
                return tag;
            }

            public String getName() {
                return name;
            }

            public String getDirection_desc() {
                return direction_desc;
            }
        }

        public class AddressComponentEntity {
            /**
             * distance : 1
             * direction : 1
             * street : 成仪路
             * province : 北京市
             * adcode : 110106
             * street_number :
             * district : 丰台区
             * country_code : 0
             * city : 北京市
             * country : 中国
             */
            private String distance;
            private String direction;
            private String street;
            private String province;
            private String adcode;
            private String street_number;
            private String district;
            private int country_code;
            private String city;
            private String country;

            public void setDistance(String distance) {
                this.distance = distance;
            }

            public void setDirection(String direction) {
                this.direction = direction;
            }

            public void setStreet(String street) {
                this.street = street;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public void setAdcode(String adcode) {
                this.adcode = adcode;
            }

            public void setStreet_number(String street_number) {
                this.street_number = street_number;
            }

            public void setDistrict(String district) {
                this.district = district;
            }

            public void setCountry_code(int country_code) {
                this.country_code = country_code;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public void setCountry(String country) {
                this.country = country;
            }

            public String getDistance() {
                return distance;
            }

            public String getDirection() {
                return direction;
            }

            public String getStreet() {
                return street;
            }

            public String getProvince() {
                return province;
            }

            public String getAdcode() {
                return adcode;
            }

            public String getStreet_number() {
                return street_number;
            }

            public String getDistrict() {
                return district;
            }

            public int getCountry_code() {
                return country_code;
            }

            public String getCity() {
                return city;
            }

            public String getCountry() {
                return country;
            }
        }

        public class LocationEntity {
            /**
             * lng : 116.45034299999993
             * lat : 39.85076108193579
             */
            private double lng;
            private double lat;

            public void setLng(double lng) {
                this.lng = lng;
            }

            public void setLat(double lat) {
                this.lat = lat;
            }

            public double getLng() {
                return lng;
            }

            public double getLat() {
                return lat;
            }
        }
    }
}
