import java.util.*;
import java.time.*;
import java.text.*;
import java.text.SimpleDateFormat;
 
class formatter{
    static Scanner input = new Scanner(System.in);
 
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
 
    public static void refreshScreen() {
        clearScreen();
        System.out.println("\n\t\tTHEKUMOOTIL INSURANCE\n");
    }
 
    public static void transitionBuffer() {
        System.out.println(".....Press any key to continue!.....");
        input.nextLine();
    }
}
 
abstract class Insurance extends formatter{
    String name;
    String id;
    String client_pass;
    double premium;
    String start_d;
    String expiry_d;
    String type;
    double used;
    static Scanner input = new Scanner(System.in);
    
    public Insurance(String name, String id, String client_pass){
        this.name = name;
        this.id = id;
        this.client_pass = client_pass;
        Calendar cal = Calendar.getInstance();
        Date s = cal.getTime();
        cal.add(Calendar.YEAR, 1); 
        Date e = cal.getTime();
        SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
        start_d = DateFor.format(s);
        expiry_d = DateFor.format(e);
    }
 
    public void display(){
        System.out.printf("\n\n\t\tName: %s\n\t\tID: %s\n\t\tType: %s\n\t\tPremium: %.2f\n\t\tStart: %s\n\t\tExpiry: %s", name, id, type, premium, start_d, expiry_d);
    }
 
    public abstract void modify();
    public abstract void planChooser(int op);
    public abstract void claims();
}
 
class Health extends Insurance{
    double total_cov_amount;
    private String [] hospitals = {"NMC Hospitals", "Aster Hospitals/Clinics", "Al Noor Clinic", "Zulekha Hospital", "Thumbay Hospital", "Iranian Hospital", "International Modern Hospital", "Belhoul Speciality Hospital", "Prime Hospitals/Clinics"};
    private boolean dental = false;
    private boolean optical = false;
    private boolean maternity = false;
    private double consultation;
    private double medicine;
    private double diagnostics;
 
    // Functions: 
    public Health(String name, String id, String client_pass){
        super(name, id, client_pass);
        System.out.println("\n\tWhich type of Health Insurance do you want?\n\n");
        printDetails();
        System.out.print("\n\tEnter: ");
        int op = input.nextInt();
        this.planChooser(op);
    }
 
    public void getHospitals(){
        System.out.println("\n\t\tList of Hospitals:\n");
        for(int i=0;i<(this.hospitals).length;i++){
            System.out.println("\n\t");
            System.out.println(hospitals[i]);
        }
    }
 
    public boolean getDental(){
        return dental;
    }
 
    public boolean getOptical(){
        return dental;
    }
 
    public boolean getMaternity(){
        return maternity;
    }
 
    public static void printDetails(){
        System.out.println("[1] Gold \t\t\t [2] Silver \t\t\t [3] Bronze");
        System.out.println("    Coverage : AED 200,000 \t     Coverage : AED 100,000 \t     Coverage : AED 50,000");
        System.out.println("    Premium : AED 10,000 \t     Premium : AED 5,000 \t     Premium : AED 2,500");
        System.out.println("    5% Consultation \t\t     10% Consultation \t\t     20% Consultation");
        System.out.println("    7% Medicine \t\t     15% Medicine \t\t     25% Medicine");
        System.out.println("    10% Diagnostics \t\t     20% Diagnostics \t\t     30% Diagnostics");
        System.out.println("    Dental Covered : YES \t     Dental Covered : NO \t     Dental Covered : NO");
        System.out.println("    Optical Covered : YES \t     Optical Covered : YES \t     Optical Covered : NO");
        System.out.println("    Maternity Covered : YES \t     Maternity Covered : YES \t     Maternity Covered : NO");
    }
 
    public void planChooser(int op){
        switch(op){
            case 1:
                this.type = "Gold"; 
                this.premium = 10000;
                this.total_cov_amount = 200000;
                this.consultation = 0.05;
                this.medicine = 0.07;
                this.diagnostics = 0.10;
                this.dental = true;
                this.optical = true;
                this.maternity = true;
                this.used = 0;
            break;
            case 2:
                this.type = "Silver"; 
                this.premium = 5000;
                this.total_cov_amount = 100000;
                this.consultation = 0.10;
                this.medicine = 0.15;
                this.diagnostics = 0.20;
                this.dental = false;
                this.optical = true;
                this.maternity = true;
                this.used = 0;
            break;
            case 3:
                this.type = "Bronze";
                this.premium = 2500; 
                this.total_cov_amount = 50000;
                this.consultation = 0.20;
                this.medicine = 0.25;
                this.diagnostics = 0.30;
                this.dental = false;
                this.optical = false;
                this.maternity = false;
                this.used = 0;
            break;
            default:
                System.out.println("INVALID INPUT!\n");
        }
        this.display();
    }
 
    public void modify(){
        System.out.println("\n\tEnter from the plans below:\n");
        printDetails();
        System.out.print("\n\tEnter: ");
        int op = input.nextInt();
        switch(op){
            case 1:
                if((this.type).equals("Gold")){
                    System.out.println("\n\tThis Insurance is already having the Gold Plan\n");
                }
                else{
                    this.planChooser(op);
                }
            break;
            case 2:
                if((this.type).equals("Silver")){
                    System.out.println("\n\tThis Insurance is already having the Silver Plan\n");
                }
                else{
                    this.planChooser(op);
                }
            break;
            case 3:
                if((this.type).equals("Bronze")){
                    System.out.println("\n\tThis Insurance is already having the Bronze Plan\n");
                }
                else{
                    this.planChooser(op);
                }
            break;
            default:
                System.out.println("\n\tINVALID OPTION\n");
        }
    }
 
    public void claims(){
        System.out.println("\n\t\tCLAIMS MENU:\n\n");
        int has = 1;
        int op;
        double amount, amountToBePaid, amountInsurance;
        for(int i=0;has==1;i++){
            System.out.println("\nChoose from below:\n[1]: Consultation\n[2]: Medicine\n[3]: Diagnostics\n[4]: Dental\n[5]: Optical\n[6]: Maternity\n[7]: Exit");
            System.out.print("\n\tEnter: ");
            op = input.nextInt();
            switch(op){
                case 1:
                    System.out.print("\n\tEnter the amount for the claim: ");
                    amount = input.nextDouble();
                    amountToBePaid = amount * this.consultation;
                    amountInsurance = amount - amountToBePaid;
                    if((this.used + amountInsurance) > this.total_cov_amount){
                        System.out.println("\tThis Claim can't be made as the limit has exceeded!");
                    }
                    else{
                        this.used = amountInsurance;
                        System.out.println("\n\tCLAIMED!");
                        System.out.println("\tYou have to pay AED " + amountToBePaid);
                        this.used += amountInsurance;
                    }
                break;
                case 2:
                    System.out.print("\n\tEnter the amount for the claim: ");
                    amount = input.nextDouble();
                    amountToBePaid = amount * this.medicine;
                    amountInsurance = amount - amountToBePaid;
                    if((this.used + amountInsurance) > this.total_cov_amount){
                        System.out.println("\tThis Claim can't be made as the limit has exceeded!");
                    }
                    else{
                        this.used = amountInsurance;
                        System.out.println("\n\tCLAIMED!");
                        System.out.println("\tYou have to pay AED " + amountToBePaid);
                        this.used += amountInsurance;
                    }
                break;
                case 3:
                    System.out.print("\n\tEnter the amount for the claim: ");
                    amount = input.nextDouble();
                    amountToBePaid = amount * this.diagnostics;
                    amountInsurance = amount - amountToBePaid;
                    if((this.used + amountInsurance) > this.total_cov_amount){
                        System.out.println("\tThis Claim can't be made as the limit has exceeded!");
                    }
                    else{
                        this.used = amountInsurance;
                        System.out.println("\n\tCLAIMED!");
                        System.out.println("\tYou have to pay AED " + amountToBePaid);
                        this.used += amountInsurance;
                    }
                break;
                case 4:
                    if(this.dental){
                        System.out.println("\n\tDental is Covered!\nCLAIMED!");
                    }
                    else{
                        System.out.println("\n\tDental is not covered in your plan!");
                    }
                break;
                case 5:
                    if(this.optical){
                        System.out.println("\n\tOptical is Covered!\nCLAIMED!");
                    }
                    else{
                        System.out.println("\n\tOptical is not covered in your plan!");
                    }
                break;
                case 6:
                    if(this.maternity){
                        System.out.println("\n\tMaternity is Covered!\nCLAIMED!");
                    }
                    else{
                        System.out.println("\n\tMaternity is not covered in your plan!");
                    }
                break;
                case 7:
                    transitionBuffer();
                break;
                default:
                    System.out.println("\n\tINVALID OPTION!");
                    this.claims();
                    transitionBuffer();
            }
            if(this.used == this.total_cov_amount){
                System.out.println("\n\tTotal Coverage Amount Reached!");
            }
            System.out.print("\n\tDo you have more claims to make?\n\t[1]: YES\t[2]: NO\n\tEnter: ");
            has = input.nextInt();
        }
    }
 
    public void display(){
        super.display();
        System.out.printf("\n\t\tTotal Coverage: %.2f\n\t\tDental: %B\n\t\tOptical: %B\n\t\tMaternity: %B\n\t\tConsultation Coverage: %.2f\n\t\tMedicine Coverage: %.2f\n\t\tDiagnostics Coverage: %.2f\n\n", total_cov_amount, dental, optical, maternity, consultation, medicine, diagnostics);
    }
}
 
class Vehicle extends Insurance{
    String car_type;          // [1]: Sedan, [2]: SUV, [3]: Sports, [4]: Others
    String car_name;
    double car_kms;
    double insured_amount;
    double collision;       // [1]: Full insurance = 1.0, [2]: Third-party = 0.5
    double medical;
    boolean emergency = true;
    boolean sdriver = true;
 
    // Functions:
    public Vehicle(String name, String id, String client_pass){
        super(name, id, client_pass);
        System.out.println("\n\tWhich type of Vehicle Insurance do you want?\n");
        printDetails();
        System.out.print("\n\tEnter: ");
        int op = input.nextInt();
        if(op>=1 && op<=2){
            this.planChooser(op);
        }
        else{
            System.out.println("\tINVALID OPTION!");
            transitionBuffer();
        }
    }
 
    public static void printDetails(){
        System.out.println("\t\t[1] Full Insurance \t\t\t  [2] Third - Party Insurance");
        System.out.println("------------------------------------------------------------------------------------------");
        System.out.println("      Insured Amount :  AED 50,000 for Sedan \t\tInsured Amount :  AED 25,000 for Sedan");
        System.out.println("\t\t\tAED 100,000 for SUV \t\t\t\t  AED 50,000 for SUV");
        System.out.println("\t\t\tAED 300,000 for Sports \t\t\t\t  AED 100,000 for Sports");
        System.out.println("   Collision Coverage:     Fully Covered \t     Collision Coverage:  50% of Expenses Covered");
        System.out.println("Medical Deductibility:     AED 10,000 \t\t  Medical Deductibility:      AED 50,000");
        System.out.println("   Emergency Roadside \t\t\t\t     Emergency Roadside");
        System.out.println("\t   Assistance:      Yes\t\t\t\t     Assistance:      Yes");
        System.out.println(" Safe Driver Discount:      Yes\t\t\t   Safe Driver Discount:      Yes");
        System.out.println("\t  Total Cost :   AED 750 for Sedan \t\t    Total Cost :    AED 500 for Sedan");
        System.out.println("\t                 AED 1,500 for SUV \t\t                    AED 1,000 for SUV");
        System.out.println("\t                 AED 6,000 for Sports \t\t                    AED 4,000 for Sports");
        System.out.println("\t                 AED 1,000 for Others \t\t                    AED 800 for Others");
    }
 
    public void calculateInsuredAmount(){
        switch(type){
            case "Full Insurance":                          // Full insurance
                switch(car_type){
                    case "Sedan":                           // Sedan
                        this.insured_amount = 50000;
                    break;
                    case "SUV":             
                        this.insured_amount = 100000;
                    break;
                    case "Sports":
                        this.insured_amount = 300000;
                    break;
                    case "Others":
                        this.insured_amount = 75000;
                    break;
                }
                if(car_kms > 200000){
                    this.insured_amount *= 0.1;
                }
                else if(car_kms > 100000){
                    this.insured_amount *= 0.3;
                }
                else{
                    this.insured_amount *= 0.8;
                }
            break;
            
            case "Third-Party Insurance":                                              // Third-party
                switch(car_type){
                    case "Sedan":                                     // Sedan
                        this.insured_amount = 25000;
                    break;
                    case "SUV":             
                        this.insured_amount = 50000;
                    break;
                    case "Sports":
                        this.insured_amount = 100000;
                    break;
                    case "Others":
                        this.insured_amount = 35000;
                    break;
                }
                if(car_kms > 200000){
                    this.insured_amount *= 0.1;
                }
                else if(car_kms > 100000){
                    this.insured_amount *= 0.3;
                }
                else{
                    this.insured_amount *= 0.8;
                }            
            break;
        }
    }
 
    public void modify(){
        System.out.println("\n\tEnter from the plans below:\n");
        printDetails();
        System.out.print("\n\tEnter: ");
        int op = input.nextInt();
        switch(op){
            case 1:
                if((this.type).equals("Full Insurance")){
                    System.out.println("\n\tThis Insurance is already having the Full Insurance Plan\n");
                }
                else{
                    this.planChooser(op);
                }
            break;
            case 2:
                if((this.type).equals("Third-Party Insurance")){
                    System.out.println("\n\tThis Insurance is already having the Third-Party Insurance Plan\n");
                }
                else{
                    this.planChooser(op);
                }
            break;
            default:
                System.out.println("\n\tINVALID OPTION\n");
        }
    }
 
    public void planChooser(int op){
        System.out.println("\n[1]: Sedan\t[2]: SUV\t[3]: Sports\t[4]: Others");
        System.out.print("\n\tEnter: ");
        int ct = input.nextInt();
        if(ct>=1 && ct<=4){
            System.out.print("\n\tEnter the Total Kilometres: ");
            this.car_kms = input.nextDouble();
            input.nextLine();
            System.out.print("\n\tEnter the name of the Car: ");
            this.car_name = input.nextLine();
            switch(ct){
                case 1:
                    this.car_type = "Sedan";
                    if(op == 1){
                        this.premium = 750;
                    }
                    else{
                        this.premium = 500;
                    }
                break;
                case 2:
                    this.car_type = "SUV";
                    if(op == 1){
                        this.premium = 1500;
                    }
                    else{
                        this.premium = 1000;
                    }
                break;
                case 3:
                    this.car_type = "Sports";
                    if(op == 1){
                        this.premium = 6000;
                    }
                    else{
                        this.premium = 4000;
                    }
                break;
                case 4:
                    this.car_type = "Others";
                    if(op == 1){
                        this.premium = 1000;
                    }
                    else{
                        this.premium = 800;
                    }
                break;
            }
            switch(op){
                case 1:
                    this.type = "Full Insurance";
                    this.collision = 0;
                    this.medical = 10000;
                    this.calculateInsuredAmount();
                    this.used = 0;
                break;
                case 2:
                    this.type = "Third-Party Insurance";
                    this.collision = 50;
                    this.medical = 50000;
                    this.calculateInsuredAmount();
                    this.used = 0;
                break;
                default:
                    System.out.println("\n\tINVALID OPTION");
            }
        }
        else{
            System.out.println("\n\tINVALID OPTION!\n");
            transitionBuffer();
        }
        this.display();
    }
 
    public void claims(){
        System.out.println("\t\tCLAIMS MENU:\n");
        int has = 1;
        int op;
        double amount, amountToBePaid, amountInsurance;
        for(int i = 0;has == 1;i++){
            System.out.println("\t[1]: Collision\t[2]: Total Loss\t[3]: Exit");
            System.out.print("\n\tEnter: ");
            op = input.nextInt();
            switch(op){
                case 1:
                    System.out.print("\n\tEnter the amount for the claim: ");
                    amount = input.nextDouble();
                    amountToBePaid = amount * (this.collision * 0.01);
                    amountInsurance = amount - amountToBePaid;
                    System.out.println("\n\tCLAIMED!");
                    System.out.println("\tYou have to pay AED " + amountToBePaid);
                    this.used += amountInsurance;
                break;
                case 2:
                    System.out.print("\n\tEnter the amount for the claim: ");
                    amount = input.nextDouble();
                    amountToBePaid = amount * (this.collision * 0.01);
                    amountInsurance = amount - amountToBePaid;
                    System.out.println("\n\tCLAIMED!");
                    System.out.println("\tYou have to pay AED " + amountToBePaid);
                    this.used += amountInsurance;
                break;
                case 3:
                    transitionBuffer();
                break;
                default:
                    System.out.println("\tINVALID OPTION!");
                    this.claims();
            }
            System.out.print("\n\tDo you have more claims to make? [1]: Yes\t[2]: No");
            System.out.print("\n\tEnter: ");
            has = input.nextInt();
        }
    }
    public void display(){
        super.display();
        System.out.printf("\n\t\tCar Name: %s\n\t\tCar Type: %s\n\t\tKms: %.2f\n\t\tInsured Amount: %.2f\n\t\tCollision Coverage (Percent): %.2f\n\t\tMedical Deductibility: %.2f\n\t\tEmergency Roadside Assistance: %B\n\t\tSafe Driver Discount: %B\n\t\t\n\n", car_name, car_type, car_kms, insured_amount, collision, medical, emergency, sdriver);
    }
}
 
/************************************************************
                        START HERE    
                 // MAIN CLASS STARTS HERE
************************************************************/
class Program extends formatter{
    private static String USERNAME = "Admin";
    private static String PASSWORD = "abc123";
    private static Scanner input = new Scanner(System.in);
    private static String ID_h = "ThkH02020/";  //Default ID (Health)
    private static String ID_v = "ThkV02020/";  //Default ID (Vehicle)
 
    // ID for health : ThkH02020/t/h
    // ID for vehicle : ThkV02020/t/v
 
    private static int ID_t_count = 0;  //Total no of Insurances
    private static int ID_H_count = 0;  //Total no of Health Insurances
    private static int ID_V_count = 0;  //Total no of Vehicle Insurances
 
    private static ArrayList <Health> all_health  = new ArrayList <Health>();     //Array List to hold all Health Insurances (Health Clients)
    private static ArrayList <Vehicle> all_vehicle = new ArrayList <Vehicle>();   //Array List to hold all Vehicle Insurances (Vehicle Clients)
 
    public static void main(String [] args) {
        login();
    }
 
    //LOGIN PAGE......FIRST MENU
    public static void login() {                            
        refreshScreen();
        System.out.println("\n[1]: Admin Login\n[2]: Client Login\n[3]: Exit\n[4]: Help\n");
        System.out.println("\tSelect An Option [1-4]");
        System.out.print("\tOption : ");
 
        try{
            int option = input.nextInt();
            input.nextLine();
 
            switch (option) {
                case 1:
                    adminLogin();
                break;
                case 2:
                    clientLogin();
                break;
                case 3:
                    leave();
                break;
                case 4:
                    termsAndConditions();
                    login();
                break;
                default:
                    System.out.println("\n......Invalid Option........\nPlease enter a valid option...\n");
                    transitionBuffer();
                    login();
            }
        }
        catch (InputMismatchException E) {
            System.out.println("\n......Invalid Option........\nPlease enter a valid option...\n");
        input.nextLine();
            transitionBuffer();
            login();
        }
    }
 
    public static void adminLogin() {
        refreshScreen();
        System.out.println("\tADMIN LOGIN\n");
        System.out.print("USERNAME :  ");
        String username = input.nextLine();
 
        System.out.print("PASSWORD :  ");
        String password = input.nextLine();
 
        if (username.equals(USERNAME) && password.equals(PASSWORD)) {
            System.out.println("\n.....................Logged in succesfully!");
            transitionBuffer();
            adminMenu();
        } 
        else {
            System.out.println("\n.....................Invalid Username or Password");
            transitionBuffer();
            login();
        }
    }
 
    public static void clientLogin(){
        refreshScreen();
        System.out.println("\t\tCLIENT LOGIN\n");
        System.out.print("\n\tUSERNAME: ");
        String username = input.nextLine();
 
        System.out.print("\n\tPASSWORD: ");
        String password = input.nextLine();
             
        int i = finder(username);                           // Index
        int op = kidharHai(username);                       // Health or Vehicle
        if(i>=0){
            switch(op){
                case 1:
                    if(password.equals(all_health.get(i).client_pass)){
                        transitionBuffer();
                        clientMenu(i,op);
                    }
                    else{
                        System.out.println("\n\tWRONG PASSWORD!");
                        input.nextLine();
                        transitionBuffer();
                    }
                break;
                case -1:
                    if(password.equals(all_vehicle.get(i).client_pass)){
                        transitionBuffer();
                        clientMenu(i,op);
                    }
                    else{
                        System.out.println("\n\tWRONG PASSWORD!");
                        input.nextLine();
                        transitionBuffer();
                    }
                break;
            }
        }
        else{
            System.out.println("\n\tWRONG USERNAME!");
            input.nextLine();
            transitionBuffer();
            login();
        }
    }
 
    public static void leave() {
        refreshScreen();
        System.out.println("\n\t\t*********\n");
        System.out.println("\n\t\tTHANK YOU \n\tFor Using The Thekumootil Portal!\n\tWe Hope To See You Again!\n");
        transitionBuffer();
        System.exit(0);
    }
 
    public static void adminMenu(){
        refreshScreen();
        System.out.println("\tADMIN MENU\n");
        System.out.println("[1]: Make New Client\n[2]: Change Client Plan\n[3]: Delete Client\n[4]: Logout from Admin\n\n");
        System.out.println("\tSelect An Option [1-4]");
        System.out.print("\tOption : ");
        int option = input.nextInt();
        switch(option){
            case 1:
                makeClient();
                transitionBuffer();
                adminMenu();
            break;
            case 2:
                transitionBuffer();
                modifyClient();
                transitionBuffer();
                adminMenu();
            break;
            case 3:
                transitionBuffer();
                deleteClient();
                transitionBuffer();
                adminMenu();
            break;
            case 4:
                transitionBuffer();
                login();
            break;
            default:
                System.out.println("\n.......Invalid Option.........\nPlease enter a valid option...\n");
                transitionBuffer();
                adminMenu();
        }
    }
 
    public static void clientMenu(int ind, int kh){
        refreshScreen();
        System.out.println("\t\tCLIENT MENU:\n");
        System.out.println("\t[1]: Make a Claim\n\t[2]: Display details\n\t[3]: Exit Client Menu");
        System.out.print("\n\tEnter: ");
        int op = input.nextInt();
        switch(op){
            case 1:
                switch(kh){
                    case 1:
                        transitionBuffer();
                        all_health.get(ind).claims();
                        transitionBuffer();
                        clientMenu(ind, kh);
                    break;
                    case -1:
                        transitionBuffer();
                        all_vehicle.get(ind).claims();
                        transitionBuffer();
                        clientMenu(ind, kh);
                    break;
                }
            break;
            case 2:
                switch(kh){
                    case 1:
                        transitionBuffer();
                        all_health.get(ind).display();
                        transitionBuffer();
                        clientMenu(ind, kh);
                    break;
                    case -1:
                        transitionBuffer();
                        all_vehicle.get(ind).display();
                        transitionBuffer();
                        clientMenu(ind, kh);
                    break;
                }
            break;
            case 3:
                transitionBuffer();
                login();
            break;
            default:
                System.out.println("\n\tINVALID OPTION! TRY AGAIN\n");
                transitionBuffer();
                clientMenu(ind,kh);
        }
    }
 
    public static void makeClient(){
        refreshScreen();
        input.nextLine();
        System.out.println("\n\tNEW CLIENT:\n\t");
        System.out.println("\tEnter The Client Details: ");
        System.out.print("\n\n\tName: ");
        String name = input.nextLine();
        System.out.print("\n\tPassword: ");
        String pass = input.nextLine();
        System.out.println("\tClient Insurance For........");
        System.out.print("\n\t[1]Health \t[2]Vehicle: ");
        int option = input.nextInt();
        
        String temp_ID;
        switch (option){
            case 1:
                ID_H_count++;     // Increase the count of Health Insurance
                ID_t_count++;     // Increase the count of total no of Insurances
                temp_ID = ID_h + ID_t_count + "/" + ID_H_count;
                all_health.add(new Health(name, temp_ID, pass));
            break;
            case 2:
                ID_V_count++;
                ID_t_count++;
                temp_ID = ID_v + ID_t_count + "/" + ID_V_count;
                all_vehicle.add(new Vehicle(name,temp_ID,pass));
            break;
            default:
                System.out.println("\tPlease enter a valid option!");
                transitionBuffer();
                makeClient();
        }
    }
 
    public static void modifyClient(){
        refreshScreen();
        System.out.println("\n\t\tMODIFY INSURANCE\n\n");
        System.out.print("\n\tEnter the Insurance ID: ");
        input.nextLine();
        String temp_id = input.nextLine();
        int op = kidharHai(temp_id);
        int i;
        switch(op){
            case 1:
                i = finder(temp_id);
                if(i>=0){
                    all_health.get(i).modify();
                }
                else{
                    System.out.println("\n\tINVALID ID\n\t");
                }
            break;
            case -1:
                i = finder(temp_id);
                if(i>=0){
                    all_vehicle.get(i).modify();
                }
                else{
                    System.out.println("\n\tINVALID ID\n\t");
                }
            break;
            default:
                System.out.println("\n\tINVALID OPTION\n");
        }
    }
 
    public static void deleteClient(){
        refreshScreen();
        System.out.println("\n\t\tDELETE INSURANCE\n\n");
        input.nextLine();
        System.out.print("\n\n\tEnter the Insurance ID: ");
        String temp_id = input.nextLine();
        int op = kidharHai(temp_id);
        int i;
        int finalCheck = 0;
        switch(op){
            case 1:
                i = finder(temp_id);
                if(i>=0){
                    System.out.print("\n\tAre you sure you want to delete? [1]: YES  [2]: NO ");
                    System.out.print("\n\tEnter: ");
                    finalCheck = input.nextInt();
                    if(finalCheck == 1){
                        all_health.remove(i);
                        System.out.print("\n\tDELETED!\n\n");
                    }
                }
                else{
                    System.out.println("\n\tINVALID ID\n\t");
                }
            break;
            case -1:
                i = finder(temp_id);
                if(i>=0){
                    System.out.print("\n\tAre you sure you want to delete? [1]: YES  [2]: NO ");
                    System.out.print("\n\tEnter: ");
                    finalCheck = input.nextInt();
                    if(finalCheck == 1){
                        all_vehicle.remove(i);
                        System.out.print("\n\tDELETED!\n\n");
                    }
                }
                else{
                    System.out.println("\n\tINVALID ID\n\t");
                }
            break;
            default:
                System.out.println("\n\tINVALID OPTION\n");
        }
    }
 
    public static int finder(String temp_id){
        if(kidharHai(temp_id) == 1){
            for(int i = 0; i < all_health.size(); i++){
                if((all_health.get(i).id).equals(temp_id)){
                    return i;
                }
            }
            return -1;
        }
 
        else if(kidharHai(temp_id) == -1){
            for(int i = 0; i < all_vehicle.size(); i++){
                if((all_vehicle.get(i).id).equals(temp_id)){
                    return i;
                }
            }
            return -1;
        }
    
        else{
            return -1;
        }
    }
 
    public static int kidharHai(String id){  //0->No   1->H   -1->V
        if(id.charAt(3) == 'H'){
            return 1;
        }
        else if(id.charAt(3) == 'V'){
            return -1;
        }
        else{
            return 0;
        }
    }
    /// At the end
    public static void termsAndConditions() {
        refreshScreen();
        System.out.println("\n\t\tTERMS AND CONDITIONS:\n\n");
        System.out.println("\nPLAN DETAILS OF HEALTH INSURANCE:\n");
        Health.printDetails();
        System.out.println("\nPLAN DETAILS OF VEHICLE INSURANCE: \n");
        Vehicle.printDetails();
        System.out.println("\n\tTHANK YOU FOR USING THEKUMOOTIL INSURANCE!");
        System.out.println("\n\tWe will soon cover dog also!");
        System.out.println("\nCheck our advertisement in youtube!: https://www.youtube.com/watch?v=MV4_LbYRh8k\n\n");
        transitionBuffer();
    }
}
