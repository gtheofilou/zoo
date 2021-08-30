package gr.gt.zoo.entity;

import java.io.Serializable;


public class AnimalTricksAssocId implements Serializable {

    private String animalId;
    private String trickId;

    public AnimalTricksAssocId() {
    }

    public AnimalTricksAssocId(String animalId, String trickId) {
        this.animalId = animalId;
        this.trickId = trickId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AnimalTricksAssocId that = (AnimalTricksAssocId) o;

        if (!animalId.equals(that.animalId)) return false;
        return trickId.equals(that.trickId);
    }

    @Override
    public int hashCode() {
        int result = animalId.hashCode();
        result = 31 * result + trickId.hashCode();
        return result;
    }
}
