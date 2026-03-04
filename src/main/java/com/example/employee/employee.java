package com.example.employee;

import java.util.*;

public class employee {

  
    class Employee {
        String id;
        String name;
        String department;

        Employee(String id, String name, String dept) {
            this.id = id;
            this.name = name;
            this.department = dept;
        }

        @Override
        public String toString() {
            return name + " (" + id + ")";
        }
    }

    
    class Task {
        String taskId;
        String title;

        Task(String taskId, String title) {
            this.taskId = taskId;
            this.title = title;
        }

        @Override
        public String toString() {
            return taskId + ": " + title;
        }

        
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            Task t = (Task) o;
            return this.taskId.equals(t.taskId);
        }

        @Override
        public int hashCode() {
            return taskId.hashCode();
        }
    }

    
    Map<String, Employee> employees = new HashMap<>();
    Map<String, LinkedHashSet<Task>> tasks = new HashMap<>();
    

    

  
    public void addEmployee(String id, String name, String dept) {
        employees.put(id, new Employee(id, name, dept));
        tasks.put(id, new LinkedHashSet<>());
    }

    
    public void assignTask(String empId, String taskId, String title) {
        Task t = new Task(taskId, title);
        boolean added = tasks.get(empId).add(t);

        if (!added) {
            System.out.println("Duplicate task ignored: " + taskId);
        }
    }

    
    public void showTasks(String empId) {
        System.out.println("\nTasks for " + employees.get(empId) + ":");
        for (Task t : tasks.get(empId)) {
            System.out.println("- " + t);
        }
    }

    
    public void renameTask(String empId, String taskId, String newTitle) {
        for (Task t : tasks.get(empId)) {
            if (t.taskId.equals(taskId)) {
                t.title = newTitle;
                break;
            }
        }
    }

    
    public void filterTasks(String empId, String keyword) {
        System.out.println("\nFiltered tasks (" + keyword + "):");
        for (Task t : tasks.get(empId)) {
            if (t.title.contains(keyword)) {
                System.out.println("- " + t);
            }
        }
    }

   
    public void displayAll() {
        System.out.println("\n--- TASKS GROUPED BY EMPLOYEES ---");
        for (String id : employees.keySet()) {
            System.out.println(employees.get(id) + ": ");
            for (Task t : tasks.get(id)) {
                System.out.println("   - " + t);
            }
        }
    }

    
    public static void main(String[] args) {
    	employee sys = new employee();

        
        sys.addEmployee("E101", "Aarav", "Engineering");
        sys.addEmployee("E102", "Meera", "Testing");

        
        sys.assignTask("E101", "T1", "Fix login bug");
        sys.assignTask("E101", "T2", "Refactor code");
        sys.assignTask("E101", "T1", "Fix login bug"); // duplicate ignored
        sys.assignTask("E102", "T3", "Write test cases");

        
        sys.showTasks("E101");

        
        sys.renameTask("E101", "T2", "Refactor payment module");

        
        sys.filterTasks("E101", "Refactor");

        
        sys.displayAll();
    }
}