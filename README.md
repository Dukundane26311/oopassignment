answer of quetion 1 

Mission Management System, including all required classes, validations, and basic functionality.

  1. Personnel.java
    ________________

public class Personnel {
    private String personnelId;
    private String personnelName;
    private String personnelRole;

    public Personnel(String id, String name, String role) {
        this.personnelId = id;
        this.personnelName = name;
        this.personnelRole = role;
    }

    public String getPersonnelRole() {
        return personnelRole;
    }

    public String getPersonnelName() {
        return personnelName;
    }
}

 2. Resource.java
   ______________

public class Resource {
    private String resourceId;
    private String resourceName;
    private int quantity;
    private String resourceType;

    public Resource(String id, String name, int qty, String type) {
        this.resourceId = id;
        this.resourceName = name;
        this.quantity = qty;
        this.resourceType = type;
    }

    public boolean isAvailable(int needed) {
        return quantity >= needed;
    }

    public void allocate(int used) {
        if (used <= quantity) quantity -= used;
    }

    public String getResourceName() {
        return resourceName;
    }

    public int getQuantity() {
        return quantity;
    }
}


3. Mission.java (Abstract Class)
   _____________________________

import java.util.*;

public abstract class Mission {
    protected String missionId;
    protected String missionName;
    protected Date missionStartDate;
    protected Date missionEndDate;
    protected String status;
    protected List<Personnel> assignedPersonnel = new ArrayList<>();
    protected List<Resource> allocatedResources = new ArrayList<>();

    public Mission(String id, String name, Date start, Date end) {
        this.missionId = id;
        this.missionName = name;
        this.missionStartDate = start;
        this.missionEndDate = end;
        this.status = "PLANNED";
    }

    public void assignPersonnel(Personnel p) {
        if (!assignedPersonnel.contains(p)) {
            assignedPersonnel.add(p);
        }
    }

    public abstract void assignTask();
    public abstract void allocateResources(List<Resource> resources);
    public abstract void trackMissionProgress();
    public abstract void generateMissionReport();
}

4.ReconMission.java
________________________


import java.util.List;

public class ReconMission extends Mission {
    public ReconMission(String id, String name, Date start, Date end) {
        super(id, name, start, end);
    }

    @Override
    public void assignTask() {
        if (assignedPersonnel.size() < 2) {
            System.out.println("Error: At least 2 personnel required.");
            return;
        }
        System.out.println("Recon tasks assigned: surveillance, intelligence gathering.");
    }

    @Override
    public void allocateResources(List<Resource> resources) {
        for (Resource r : resources) {
            if (r.getResourceName().equalsIgnoreCase("Drone") && r.isAvailable(1)) {
                allocatedResources.add(r);
                r.allocate(1);
                System.out.println("Allocated Drone.");
                return;
            }
        }
        System.out.println("Error: No drones available.");
    }

    @Override
    public void trackMissionProgress() {
        status = "IN_PROGRESS";
        System.out.println("Tracking recon progress...");
    }

    @Override
    public void generateMissionReport() {
        System.out.println("Recon Mission Report");
        System.out.println("Name: " + missionName);
        System.out.println("Status: " + status);
        System.out.println("Personnel count: " + assignedPersonnel.size());
        System.out.println("Resources used: " + allocatedResources.size());
    }
}
5. Main.java
_____________

import java.util.*;

public class Main {
    public static void main(String[] args) {
        
        Date start = new Date(); 
        Calendar c = Calendar.getInstance();
        c.setTime(start);
        c.add(Calendar.DATE, 5);
        Date end = c.getTime(); 

        
        ReconMission mission = new ReconMission("M001", "Border Recon", start, end);

       
        Personnel p1 = new Personnel("P001", "John", "Scout");
        Personnel p2 = new Personnel("P002", "Alice", "Communications");
        mission.assignPersonnel(p1);
        mission.assignPersonnel(p2);

        
        List<Resource> allResources = new ArrayList<>();
        allResources.add(new Resource("R001", "Drone", 2, "Equipment"));

        
        mission.assignTask();
        mission.allocateResources(allResources);
        mission.trackMissionProgress();
        mission.generateMissionReport();
    }
}
==================================================================================================================================================================================================================================================

Answer quetion 2


LAND MANAGMENT SYSTEM
_____________________



import java.util.*;

abstract class Land {
    String landId;
    String ownerName;
    String location;
    double sizeInAcres;
    Date registrationDate;
    String landUseStatus;

    public Land(String landId, String ownerName, String location, double sizeInAcres, Date registrationDate, String landUseStatus) {
        this.landId = landId;
        this.ownerName = ownerName;
        this.location = location;
        this.sizeInAcres = sizeInAcres;
        this.registrationDate = registrationDate;
        this.landUseStatus = landUseStatus;
    }

    abstract boolean validateOwnership();
    abstract boolean checkZoningCompliance();
    abstract double calculateTax();
    abstract void generateLandReport();
}

class AgriculturalLand extends Land {
    public AgriculturalLand(String landId, String ownerName, String location, double sizeInAcres, Date registrationDate, String landUseStatus) {
        super(landId, ownerName, location, sizeInAcres, registrationDate, landUseStatus);
    }

    boolean validateOwnership() {
        return ownerName != null && !ownerName.isEmpty();
    }

    boolean checkZoningCompliance() {
        return sizeInAcres >= 1;
    }

    double calculateTax() {
        return sizeInAcres * 5000 * 0.01;
    }

    void generateLandReport() {
        System.out.println("--- Agricultural Land Report ---");
        System.out.println("Land ID: " + landId);
        System.out.println("Owner: " + ownerName);
        System.out.println("Location: " + location);
        System.out.println("Size: " + sizeInAcres + " acres");
        System.out.println("Tax: $" + calculateTax());
        System.out.println("Zoning Compliant: " + checkZoningCompliance());
        System.out.println("Ownership Valid: " + validateOwnership());
        System.out.println("Usage Status: " + landUseStatus);
    }
}

class ResidentialLand extends Land {
    int residentialUnits;

    public ResidentialLand(String landId, String ownerName, String location, double sizeInAcres, Date registrationDate, String landUseStatus, int residentialUnits) {
        super(landId, ownerName, location, sizeInAcres, registrationDate, landUseStatus);
        this.residentialUnits = residentialUnits;
    }

    boolean validateOwnership() {
        return ownerName != null && !ownerName.isEmpty();
    }

    boolean checkZoningCompliance() {
        return residentialUnits <= sizeInAcres * 2;
    }

    double calculateTax() {
        return sizeInAcres * 8000 * 0.015;
    }

    void generateLandReport() {
        System.out.println("--- Residential Land Report ---");
        System.out.println("Land ID: " + landId);
        System.out.println("Owner: " + ownerName);
        System.out.println("Location: " + location);
        System.out.println("Size: " + sizeInAcres + " acres");
        System.out.println("Residential Units: " + residentialUnits);
        System.out.println("Tax: $" + calculateTax());
        System.out.println("Zoning Compliant: " + checkZoningCompliance());
        System.out.println("Ownership Valid: " + validateOwnership());
        System.out.println("Usage Status: " + landUseStatus);
    }
}

class CommercialLand extends Land {
    boolean inCommercialZone;

    public CommercialLand(String landId, String ownerName, String location, double sizeInAcres, Date registrationDate, String landUseStatus, boolean inCommercialZone) {
        super(landId, ownerName, location, sizeInAcres, registrationDate, landUseStatus);
        this.inCommercialZone = inCommercialZone;
    }

    boolean validateOwnership() {
        return ownerName != null && !ownerName.isEmpty();
    }

    boolean checkZoningCompliance() {
        return inCommercialZone;
    }

    double calculateTax() {
        return sizeInAcres * 10000 * 0.025;
    }

    void generateLandReport() {
        System.out.println("--- Commercial Land Report ---");
        System.out.println("Land ID: " + landId);
        System.out.println("Owner: " + ownerName);
        System.out.println("Location: " + location);
        System.out.println("Size: " + sizeInAcres + " acres");
        System.out.println("Tax: $" + calculateTax());
        System.out.println("Zoning Compliant: " + checkZoningCompliance());
        System.out.println("Ownership Valid: " + validateOwnership());
        System.out.println("Usage Status: " + landUseStatus);
    }
}

class IndustrialLand extends Land {
    boolean hasEnvironmentalClearance;

    public IndustrialLand(String landId, String ownerName, String location, double sizeInAcres, Date registrationDate, String landUseStatus, boolean hasEnvironmentalClearance) {
        super(landId, ownerName, location, sizeInAcres, registrationDate, landUseStatus);
        this.hasEnvironmentalClearance = hasEnvironmentalClearance;
    }

    boolean validateOwnership() {
        return ownerName != null && !ownerName.isEmpty();
    }

    boolean checkZoningCompliance() {
        return hasEnvironmentalClearance;
    }

    double calculateTax() {
        return sizeInAcres * 12000 * 0.03;
    }

    void generateLandReport() {
        System.out.println("--- Industrial Land Report ---");
        System.out.println("Land ID: " + landId);
        System.out.println("Owner: " + ownerName);
        System.out.println("Location: " + location);
        System.out.println("Size: " + sizeInAcres + " acres");
        System.out.println("Tax: $" + calculateTax());
        System.out.println("Zoning Compliant: " + checkZoningCompliance());
        System.out.println("Ownership Valid: " + validateOwnership());
        System.out.println("Usage Status: " + landUseStatus);
    }
}

class Main {
    public static void main(String[] args) {
        Date now = new Date();

        Land agri = new AgriculturalLand("AG101", "John Doe", "Green Valley", 2.5, now, "In Use");
        Land res = new ResidentialLand("RE202", "Jane Smith", "Sunrise Hills", 1.5, now, "Vacant", 2);
        Land comm = new CommercialLand("CO303", "M. Johnson", "Market Street", 3.0, now, "In Use", true);
        Land ind = new IndustrialLand("IN404", "Global Inc.", "Industrial Park", 5.0, now, "Under Development", true);

        agri.generateLandReport();
        res.generateLandReport();
        comm.generateLandReport();
        ind.generateLandReport();
    }
}

==================================================================================================================================================================================================================================================


QUETION 3 ANSWER



Nursery School System
______________________


import java.util.*;

abstract class NurseryClass {
    String classId, className;
    int maxCapacity;
    Teacher assignedTeacher;
    List<Student> students = new ArrayList<>();

    abstract void enrollStudent(Student student);
    abstract void trackProgress();
    abstract void conductActivity(String activityName);
    abstract void generateClassReport();
}

class BabyClass extends NurseryClass {
    List<String> activities = new ArrayList<>();

    BabyClass(String id, Teacher teacher) {
        classId = id;
        className = "Baby Class";
        maxCapacity = 15;
        assignedTeacher = teacher;
    }

    void enrollStudent(Student student) {
        if (students.size() < maxCapacity && student.age >= 2 && student.age <= 3)
            students.add(student);
    }

    void trackProgress() {
        System.out.println("Tracking motor skill development.");
    }

    void conductActivity(String activityName) {
        activities.add(activityName);
    }

    void generateClassReport() {
        System.out.println(className + ", Teacher: " + assignedTeacher.teacherName);
        System.out.println("Students: " + students.size());
        System.out.println("Activities: " + activities);
    }
}

class MiddleClass extends NurseryClass {
    List<String> activities = new ArrayList<>();

    MiddleClass(String id, Teacher teacher) {
        classId = id;
        className = "Middle Class";
        maxCapacity = 20;
        assignedTeacher = teacher;
    }

    void enrollStudent(Student student) {
        if (students.size() < maxCapacity && student.age >= 3 && student.age <= 4)
            students.add(student);
    }

    void trackProgress() {
        System.out.println("Tracking language and counting skills.");
    }

    void conductActivity(String activityName) {
        activities.add(activityName);
    }

    void generateClassReport() {
        System.out.println(className + ", Teacher: " + assignedTeacher.teacherName);
        System.out.println("Students: " + students.size());
        System.out.println("Activities: " + activities);
    }
}

class TopClass extends NurseryClass {
    List<String> activities = new ArrayList<>();

    TopClass(String id, Teacher teacher) {
        classId = id;
        className = "Top Class";
        maxCapacity = 25;
        assignedTeacher = teacher;
    }

    void enrollStudent(Student student) {
        if (students.size() < maxCapacity && student.age >= 4 && student.age <= 5)
            students.add(student);
    }

    void trackProgress() {
        System.out.println("Tracking reading and writing skills.");
    }

    void conductActivity(String activityName) {
        activities.add(activityName);
    }

    void generateClassReport() {
        System.out.println(className + ", Teacher: " + assignedTeacher.teacherName);
        System.out.println("Students: " + students.size());
        System.out.println("Activities: " + activities);
    }
}

class Teacher {
    String teacherId, teacherName, teacherRole;

    Teacher(String id, String name, String role) {
        teacherId = id;
        teacherName = name;
        teacherRole = role;
    }
}

class Student {
    String studentId, studentName, guardianName;
    int age;

    Student(String id, String name, int a, String guardian) {
        studentId = id;
        studentName = name;
        age = a;
        guardianName = guardian;
    }
}

public class Main {
    public static void main(String[] args) {
        Teacher t1 = new Teacher("T01", "Alice", "Early Childhood Educator");
        NurseryClass baby = new BabyClass("C01", t1);
        Student s1 = new Student("S01", "John", 2, "Mr. Smith");
        baby.enrollStudent(s1);
        baby.conductActivity("Painting");
        baby.trackProgress();
        baby.generateClassReport();
    }
}
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------





