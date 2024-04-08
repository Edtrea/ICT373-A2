/**
 * @Title: ICT 373 A1
 * @Author: Lim Wen Chao
 * @Date: 9/3/2024
 * @File: PayingCustomer.java
 * @Purpose: A child class of Customer that represents a paying customer
 * A paying customer has a payment method, payment detail and a list of associate customers
 * A payment method/detail could only be a specified credit card or a bank account
 * @Assumptions: 
 * @Limitations: 
 */

package customer;

import java.io.Serializable;
import java.util.ArrayList;

import magazine.Magazine;
import magazine.Supplement;

public class PayingCustomer extends Customer implements Serializable {
    private String paymentMethod;
    private Integer paymentDetail;
    private ArrayList<AssociateCustomer> associateCustomers;

    /**
     * Constructor for PayingCustomer without payment method and payment detail
     * 
     * @param name        The name of the customer
     * @param address     The address of the customer
     * @param email       The email address of the customer
     * @param supplements The list of supplements the customer is interested in
     */
    public PayingCustomer(String name, Address address, String email, ArrayList<Supplement> supplements) {
        super("Paying", name, address, email, supplements);
        this.paymentMethod = "";
        this.paymentDetail = 0;
        this.associateCustomers = new ArrayList<AssociateCustomer>();
    }

    /**
     * Constructor for PayingCustomer with payment method and payment detail
     * 
     * @param name          The name of the customer
     * @param address       The address of the customer
     * @param email         The email address of the customer
     * @param supplements   The list of supplements the customer is interested in
     * @param paymentMethod The payment method of the customer
     * @param paymentDetail The payment detail of the customer
     */
    public PayingCustomer(String name, Address address, String email,
            ArrayList<Supplement> supplements, String paymentMethod,
            Integer paymentDetail) {
        super("Paying", name, address, email, supplements);
        this.paymentMethod = paymentMethod;
        this.paymentDetail = paymentDetail;
        this.associateCustomers = new ArrayList<AssociateCustomer>();
    }

    /**
     * Constructor for PayingCustomer without supplements
     * 
     * @param name          The name of the customer
     * @param address       The address of the customer
     * @param email         The email address of the customer
     * @param paymentMethod The payment method of the customer
     * @param paymentDetail The payment detail of the customer
     */
    public PayingCustomer(String name, Address address, String email, String paymentMethod,
            Integer paymentDetail) {
        super("Paying", name, address, email);
        this.paymentMethod = paymentMethod;
        this.paymentDetail = paymentDetail;
        this.associateCustomers = new ArrayList<AssociateCustomer>();
    }

    /**
     * Get the payment method of the customer
     * 
     * @return The payment method of the customer
     */
    public String getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * Get the payment detail of the customer
     * 
     * @return The payment detail of the customer
     */
    public Integer getPaymentDetail() {
        return paymentDetail;
    }

    /**
     * Get the list of associate customers
     * 
     * @return The list of associate customers
     */
    public ArrayList<AssociateCustomer> getAssociateCustomers() {
        return associateCustomers;
    }

    /**
     * Set the payment method of the customer
     * 
     * @param paymentMethod The payment method of the customer
     */
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    /**
     * Set the payment detail of the customer
     * 
     * @param paymentDetail The payment detail of the customer
     */
    public void setPaymentDetail(Integer paymentDetail) {
        this.paymentDetail = paymentDetail;
    }

    /**
     * Set the list of associate customers
     * 
     * @param associateCustomers The list of associate customers
     */
    public void setAssociateCustomers(ArrayList<AssociateCustomer> associateCustomers) {
        this.associateCustomers = associateCustomers;
    }

    /**
     * Add an associate customer to the list of associate customers
     * 
     * @param associateCustomer The associate customer to be added
     */
    public void addAssociateCustomer(AssociateCustomer associateCustomer) {
        this.associateCustomers.add(associateCustomer);
    }

    /**
     * Remove an associate customer from the list of associate customers
     * 
     * @param associateCustomer The associate customer to be removed
     */
    public void removeAssociateCustomer(AssociateCustomer associateCustomer) {
        associateCustomers.remove(associateCustomer);
    }

    /**
     * Calculate the monthly payment for the customer
     * 
     * @param magazine The magazine service the customer is interested in
     * @return The monthly payment for the customer
     */
    public double calculateMonthlyPayment(Magazine magazine) {
        double monthlyPayment = magazine.getWeeklyCost() * 4;
        for (Supplement supplement : getSupplements()) {
            monthlyPayment += supplement.getWeeklyCost() * 4;
        }
        for (AssociateCustomer associateCustomer : associateCustomers) {
            monthlyPayment += associateCustomer.calculateMonthlyPayment(magazine);
        }
        return monthlyPayment;
    }

    /**
     * Calculate the weekly payment for the customer
     * 
     * @param magazine The magazine service the customer is interested in
     * @return The weekly payment for the customer
     */
    public double calculateWeeklyPayment(Magazine magazine) {
        double weeklyPayment = magazine.getWeeklyCost();
        for (Supplement supplement : getSupplements()) {
            weeklyPayment += supplement.getWeeklyCost();
        }
        for (AssociateCustomer associateCustomer : associateCustomers) {
            weeklyPayment += associateCustomer.calculateWeeklyPayment(magazine);
        }
        return weeklyPayment;
    }

    /**
     * Get the billing history for the customer
     * 
     * @param magazine The magazine service the customer is interested in
     * @return The billing history for the customer
     */
    public String getBillingHistory(Magazine magazine) {
        StringBuilder billingHistory = new StringBuilder();
        billingHistory.append("Bill last month: " + "\n");
        billingHistory.append("The total amount due for the month - " + this.calculateMonthlyPayment(magazine));
        // Itemized cost for the customer
        billingHistory.append("\n\nThe itemized cost for the month is:\n");
        billingHistory.append("Magazine cost - " + magazine.getWeeklyCost() * 4 + "\n");
        for (Supplement supplement : this.getSupplements()) {
            // Calculate monthly cost for the supplement from the weekly cost
            billingHistory.append(supplement.getName() + " cost - " + supplement.getWeeklyCost() * 4 + "\n");
        }
        // Get the list of associate customers from the paying customer's attribute
        ArrayList<AssociateCustomer> associateCustomers = ((PayingCustomer) this).getAssociateCustomers();
        if (associateCustomers.size() > 0) {
            billingHistory.append("\nAssociate customer's cost:\n");
        }
        for (AssociateCustomer associateCustomer : associateCustomers) {
            // Calculate monthly cost for the supplement from the weekly cost
            billingHistory.append("\nAssociate customer email - " + associateCustomer.getEmail());
            billingHistory.append("\nMagazine cost - " + magazine.getWeeklyCost() * 4 + "\n");
            for (Supplement supplement : associateCustomer.getSupplements()) {
                billingHistory.append(supplement.getName() + " cost - " + supplement.getWeeklyCost() * 4 + "\n");
            }
        }

        return billingHistory.toString();
    }
}
