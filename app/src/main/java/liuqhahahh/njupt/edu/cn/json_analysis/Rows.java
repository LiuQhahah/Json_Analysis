package liuqhahahh.njupt.edu.cn.json_analysis;


/**
 * Created by root on 17-11-21.
 */

public class Rows {

        public String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public Doc getDoc() {
            return doc;
        }

        public void setDoc(Doc doc) {
            this.doc = doc;
        }

        public String key;
        public String value;
        public Doc doc;

    public class Doc{
        public String _id;
        public String _key;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String get_key() {
            return _key;
        }

        public void set_key(String _key) {
            this._key = _key;
        }

        public String getTopic() {
            return topic;
        }

        public void setTopic(String topic) {
            this.topic = topic;
        }

        public Payload getPayload() {
            return payload;
        }

        public void setPayload(Payload payload) {
            this.payload = payload;
        }

        public String topic;
        public Payload payload;
    }
    public class Payload{
        public D getD() {
            return d;
        }

        public void setD(D d) {
            this.d = d;
        }

        public D d;
    }

    public class D{
        public String temp;

        public String getTemp() {
            return temp;
        }

        public void setTemp(String temp) {
            this.temp = temp;
        }

        public String getHumidity() {
            return humidity;
        }

        public void setHumidity(String humidity) {
            this.humidity = humidity;
        }

        public Location getLocation() {
            return location;
        }

        public void setLocation(Location location) {
            this.location = location;
        }

        public String humidity;
        public Location location;
    }
    public class Location{
        public String latitude;

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String longitude;
    }





}
