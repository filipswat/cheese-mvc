package org.launchcode.cheesemvc.models;

public class Cheese {
    private String name;
    private String description;

    public Cheese(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String aName) {
        name = aName;
    }

    public void setDescription(String aDescription) {
        description = aDescription;
    }
}
