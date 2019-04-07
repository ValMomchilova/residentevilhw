package residentevil.web.validators;

import residentevil.domain.entities.Creator;

import java.util.Arrays;

public class ValidationMessages {
    private static final String CREATOR_MESSAGE = "value must be one of the following: ";

    public String creator;

    public ValidationMessages() {
        this.fillMessages();
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    private void fillMessages() {
        this.setCreator(CREATOR_MESSAGE + String.join(", ",
                Arrays.stream(Creator.values()).map(Enum::name).toArray(String[]::new)));
    }
}
