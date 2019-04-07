package residentevil.domain.models.binding;


import org.springframework.format.annotation.DateTimeFormat;
import residentevil.domain.entities.Creator;
import residentevil.domain.entities.Magnitude;
import residentevil.domain.entities.Mutation;
import residentevil.web.validators.PastDate;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class VirusAddBindingModel {
    private Long id;
    private String name;
    private String description;
    private String sideEffects;
    private Creator creator;
    private Boolean isDeadly;
    private Boolean isCurable;
    private Mutation mutation;
    private Integer turnoverRate;
    private Integer hoursUntilTurn;
    private Magnitude magnitude;
    private LocalDate releasedOn;
    private List<Long> capitalIds;
    private List<CapitalsBindingModel> capitals;

    public VirusAddBindingModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 10)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull
    @NotEmpty
    @Size(min = 5, max = 100)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NotNull
    @NotEmpty
    @Size(min = 0, max = 50)
    public String getSideEffects() {
        return sideEffects;
    }

    public void setSideEffects(String sideEffects) {
        this.sideEffects = sideEffects;
    }

    @NotNull
    public Creator getCreator() {
        return creator;
    }

    public void setCreator(Creator creator) {
        this.creator = creator;
    }

    public Boolean getDeadly() {
        return isDeadly;
    }

    public void setDeadly(Boolean deadly) {
        isDeadly = deadly;
    }

    public Boolean getCurable() {
        return isCurable;
    }

    public void setCurable(Boolean curable) {
        isCurable = curable;
    }

    @NotNull(message = "mutation must be selected")
    public Mutation getMutation() {
        return mutation;
    }

    public void setMutation(Mutation mutation) {
        this.mutation = mutation;
    }

    @NotNull(message = "must not be empty")
    @Min(0)
    @Max(100)
    public Integer getTurnoverRate() {
        return turnoverRate;
    }

    public void setTurnoverRate(Integer turnoverRate) {
        this.turnoverRate = turnoverRate;
    }

    @NotNull(message = "must not be empty")
    @Min(1)
    @Max(12)
    public Integer getHoursUntilTurn() {
        return hoursUntilTurn;
    }

    public void setHoursUntilTurn(Integer hoursUntilTurn) {
        this.hoursUntilTurn = hoursUntilTurn;
    }

    @NotNull(message = "magnitude must be selected")
    public Magnitude getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(Magnitude magnitude) {
        this.magnitude = magnitude;
    }

    @NotNull(message = "must not be empty")
    @PastDate()
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public LocalDate getReleasedOn() {
        return releasedOn;
    }

    public void setReleasedOn(LocalDate releasedOn) {
        this.releasedOn = releasedOn;
    }

    @NotNull()
    @NotEmpty()
    public List<Long> getCapitalIds() {
        return capitalIds;
    }

    public void setCapitalIds(List<Long> capitalIds) {
        this.capitalIds = capitalIds;
    }

    public List<CapitalsBindingModel> getCapitals() {
        return capitals;
    }

    public void setCapitals(List<CapitalsBindingModel> capitals) {
        this.capitals = capitals;
    }

    public void setCapitalsByCapitalsIds(){
        if (this.capitalIds != null && this.capitalIds.size() > 0){
            List<CapitalsBindingModel> capitals = new ArrayList<>();
            for (Long capitalId : this.capitalIds) {
                CapitalsBindingModel capital = new CapitalsBindingModel();
                capital.setId(capitalId);
                capitals.add(capital);
            }

            this.setCapitals(capitals);
        }
    }

    public void setCapitalsIdsByCapitals(){
        if (this.capitals != null && this.capitals.size() > 0){
            List<Long> capitalIds = new ArrayList<>();
            for (CapitalsBindingModel capital : this.capitals) {
                capitalIds.add(capital.getId());
            }

            this.setCapitalIds(capitalIds);
        }
    }
}
