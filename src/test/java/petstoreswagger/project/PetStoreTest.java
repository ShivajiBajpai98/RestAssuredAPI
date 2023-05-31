package petstoreswagger.project;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PetStoreTest {
    private PetActions petActions;

    @BeforeClass
    public void setup() {
        petActions = new PetActions();
    }

    // Scenario 1: Pet Post - upload image, find pet by id (get), Get - find pet by status
    @Test(priority = 1)
    public void scenario1() {
        petActions.addPetToStore();
        petActions.findPetById();
        petActions.findPetByStatus();
    }

    // Scenario 2: Add a pet to the store - find pet by id - find pet by status - delete pet
    @Test(priority = 2)
    public void scenario2() {
        petActions.addPetToStore();
        petActions.findPetById();
        petActions.findPetByStatus();
        petActions.deletePet();
    }

    // Scenario 3: Add a pet upload images - update an existing pet - delete pet
    @Test(priority = 3)
    public void scenario3() {
        petActions.addPetToStore();
        petActions.uploadFile();
        petActions.updatePet();
        petActions.deletePet();
    }
}
