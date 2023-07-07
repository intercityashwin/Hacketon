package CucumberConfigs.DomainObjects.Entities;

public class BillingDetails {
    private String firstName;
    private String lastName;
    private String state;
    private String city;

    public BillingDetails(String firstName, String lastName, String state, String city) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.state = state;
        this.city = city;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }
}
