package Sortfiles;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by loisgh on 12/30/15.
 */
    public class Person {
        String lastName;
        String firstName;
        SexType sex;
        Date birthDate;
        String favColor;

        public Person(String lastName, String firstName, String favColor, SexType sex, Date inDate){
            this.lastName = lastName.trim();
            this.firstName = firstName.trim();
            this.favColor = favColor.trim();
            this.sex = sex;
            this.birthDate = inDate;
        }

        public SexType getSex() {
            return sex;
        }

        public String getSexType() {
            return sex.toString();
        }

        public static boolean sexFormatValid(String sex){

            if (sex.toUpperCase().trim().equals("MALE"))
                return true;
            else if (sex.toUpperCase().trim().equals("M"))
                return true;
            else if (sex.toUpperCase().trim().equals("FEMALE"))
                return true;
            else if (sex.toUpperCase().trim().equals("F"))
                return true;
            else
                return false;
        }

        public String getLastName() {
            return lastName;
        }

        public String getFirstName() {
            return firstName;
        }

        public Date getbirthDate() {
            return birthDate;
        }

        public String getbirthDateFormat(){
            SimpleDateFormat out = new SimpleDateFormat("m/d/yyyy");
            return out.format(this.getbirthDate());
        }

        public String getFavColor(){
            return favColor;
        }

        public String toString(){
            return this.getLastName() + " " +
                    this.getFirstName() + " " +
                    this.getSex() + " " +
                    this.getbirthDateFormat() + " " +
                    this.getFavColor();
        }

        public static enum SexType {
            MALE,
            FEMALE,
            M,
            F;

            @Override
            public String toString(){
                String returnString = null;
                switch (this) {
                    case MALE: returnString = "Male";
                        break;
                    case M: returnString = "Male";
                        break;
                    case FEMALE: returnString = "Female";
                        break;
                    case F: returnString = "Female";
                        break;
                }
                return returnString;
            }
        }
}
