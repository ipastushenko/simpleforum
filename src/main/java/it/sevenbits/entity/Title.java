package it.sevenbits.entity;

/**
 * Title
 * @author Ivan Pastushenko @sevenbits
 * @version 1.0.0 30.08.2013
 */
public class Title {
    private String name;

    /**
     * Create new title
     */
    public Title() {
    }

    /**
     * Create new title
     * @param name name of title
     */
    public Title(final String name) {
        this.name = name;
    }

    /**
     * Get name of title
     * @return name of title
     */
    public String getName() {
        return name;
    }

    /**
     * Set name
     * @param name new name of title
     */
    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Title title = (Title)obj;

        return name.equals(title.name);
    }

    @Override
    public int hashCode() {
        return 31*name.hashCode();
    }
}
