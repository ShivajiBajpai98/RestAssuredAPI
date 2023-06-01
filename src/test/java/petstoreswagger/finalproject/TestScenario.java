package petstoreswagger.finalproject;

import java.io.IOException;
import org.testng.annotations.Test;

public class TestScenario extends PetStoreTest {

    @Test
    public void scenarioOne() throws IOException {
        test = extent.createTest("Scenario 1: Pet Post - Upload Image, Find Pet by ID (GET), Get - Find Pet by Status");

        // Step 1: Add a new pet to the store
        addNewPetToStore();

        // Step 2: Upload an image with the pet ID
        uploadImageWithPetId();

        // Step 3: Get the pet by ID
        getPetById();

        // Step 4: Get pets by status
        // Implement your code here

        test.pass("Scenario 1 - Test Passed");
    }

    @Test
    public void scenarioTwo() throws IOException {
        test = extent.createTest("Scenario 2: Add a Pet to the Store, Find Pet by ID, Find Pet by Status, Delete Pet");

        // Step 1: Add a new pet to the store
        addNewPetToStore();

        // Step 2: Get the pet by ID
        getPetById();

        // Step 3: Get pets by status
        // Implement your code here

        // Step 4: Delete the pet
        deletePetById();

        test.pass("Scenario 2 - Test Passed");
    }

    @Test
    public void scenarioThree() throws IOException {
        test = extent.createTest("Scenario 3: Add a Pet, Upload Images, Update an Existing Pet, Delete Pet");

        // Step 1: Add a new pet to the store
        addNewPetToStore();

        // Step 2: Upload images with the pet ID
        uploadImageWithPetId();

        // Step 3: Update the existing pet in the store
        updatePetInStoreWithFormData();

        // Step 4: Delete the pet
        deletePetById();

        test.pass("Scenario 3 - Test Passed");
    }
}
